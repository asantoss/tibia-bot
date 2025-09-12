package com.tibiabot

import com.typesafe.scalalogging.StrictLogging
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.buttons.Button

import java.util.concurrent.{ConcurrentHashMap, Executors, ScheduledExecutorService, TimeUnit}
import scala.collection.mutable
import scala.jdk.CollectionConverters._

/**
 * Manages rate-limited message operations to prevent Discord API 429 errors.
 * Implements a delayed update strategy for message components.
 */
object RateLimitManager extends StrictLogging {
  
  private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(2)
  private val pendingUpdates = new ConcurrentHashMap[String, PendingUpdate]()
  
  // Discord rate limits: 5 edits per 5 seconds per message
  private val EDIT_DELAY_MS = 1500L // 1.5 seconds between edits
  private val MAX_RETRIES = 3
  
  case class PendingUpdate(
    message: Message,
    actionRow: ActionRow,
    retries: Int = 0,
    timestamp: Long = System.currentTimeMillis()
  )
  
  /**
   * Schedule a delayed component update to avoid immediate edits after sending.
   * This prevents rate limiting when updating button IDs with message IDs.
   */
  def scheduleComponentUpdate(message: Message, actionRow: ActionRow, delayMs: Long = EDIT_DELAY_MS): Unit = {
    val updateKey = s"${message.getId}_${System.currentTimeMillis()}"
    pendingUpdates.put(updateKey, PendingUpdate(message, actionRow))
    
    scheduler.schedule(new Runnable {
      override def run(): Unit = {
        try {
          executeUpdate(updateKey)
        } catch {
          case e: Exception =>
            logger.error(s"Error executing scheduled update for message ${message.getId}: ${e.getMessage}", e)
        }
      }
    }, delayMs, TimeUnit.MILLISECONDS)
  }
  
  /**
   * Execute the pending update with retry logic
   */
  private def executeUpdate(updateKey: String): Unit = {
    Option(pendingUpdates.get(updateKey)).foreach { update =>
      try {
        update.message.editMessageComponents(update.actionRow).queue(
          _ => {
            pendingUpdates.remove(updateKey)
            logger.debug(s"Successfully updated components for message ${update.message.getId}")
          },
          error => {
            handleUpdateError(updateKey, update, error)
          }
        )
      } catch {
        case e: Exception =>
          logger.error(s"Failed to execute update for message ${update.message.getId}: ${e.getMessage}", e)
          pendingUpdates.remove(updateKey)
      }
    }
  }
  
  /**
   * Handle update errors with exponential backoff retry
   */
  private def handleUpdateError(updateKey: String, update: PendingUpdate, error: Throwable): Unit = {
    if (error.getMessage != null && error.getMessage.contains("429")) {
      // Rate limited - retry with exponential backoff
      if (update.retries < MAX_RETRIES) {
        val newUpdate = update.copy(retries = update.retries + 1)
        pendingUpdates.put(updateKey, newUpdate)
        
        val retryDelay = EDIT_DELAY_MS * Math.pow(2, update.retries).toLong
        logger.warn(s"Rate limited on message ${update.message.getId}, retrying in ${retryDelay}ms (attempt ${update.retries + 1}/$MAX_RETRIES)")
        
        scheduler.schedule(new Runnable {
          override def run(): Unit = executeUpdate(updateKey)
        }, retryDelay, TimeUnit.MILLISECONDS)
      } else {
        logger.error(s"Max retries exceeded for message ${update.message.getId}, abandoning update")
        pendingUpdates.remove(updateKey)
      }
    } else {
      logger.error(s"Failed to update message ${update.message.getId}: ${error.getMessage}")
      pendingUpdates.remove(updateKey)
    }
  }
  
  /**
   * Create a button with a temporary ID that doesn't require the message ID.
   * This avoids the need for immediate edits after sending.
   */
  def createDeferredButton(buttonType: String, charName: String, deathTime: String, label: String): Button = {
    // Use a timestamp-based ID that can be resolved later without needing the message ID immediately
    val tempId = s"${buttonType}_${charName}_${deathTime}_pending_${System.currentTimeMillis()}"
    Button.secondary(tempId, label)
  }
  
  /**
   * Clean up old pending updates (housekeeping)
   */
  def cleanupOldUpdates(): Unit = {
    val cutoffTime = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5)
    val keysToRemove = pendingUpdates.asScala.collect {
      case (key, update) if update.timestamp < cutoffTime => key
    }.toList
    
    keysToRemove.foreach { key =>
      pendingUpdates.remove(key)
      logger.debug(s"Removed stale update: $key")
    }
  }
  
  /**
   * Initialize cleanup task
   */
  def initialize(): Unit = {
    // Schedule cleanup every 5 minutes
    scheduler.scheduleAtFixedRate(new Runnable {
      override def run(): Unit = cleanupOldUpdates()
    }, 5, 5, TimeUnit.MINUTES)
    
    logger.info("RateLimitManager initialized")
  }
  
  /**
   * Shutdown the manager
   */
  def shutdown(): Unit = {
    scheduler.shutdown()
    pendingUpdates.clear()
    logger.info("RateLimitManager shutdown")
  }
}