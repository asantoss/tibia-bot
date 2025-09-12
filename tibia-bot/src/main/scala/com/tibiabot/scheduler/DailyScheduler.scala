package com.tibiabot.scheduler

import com.tibiabot.{BotApp, Config, EmojiManager, I18nService, MessageKeys}
import com.tibiabot.tibiadata.TibiaDataClient
import com.typesafe.scalalogging.StrictLogging
import net.dv8tion.jda.api.{EmbedBuilder, JDA}
import net.dv8tion.jda.api.entities.{Guild, MessageEmbed}

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}
import java.util.concurrent.{Executors, ScheduledExecutorService, TimeUnit}
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success}

/**
 * Daily scheduler for automated bot announcements
 */
object DailyScheduler extends StrictLogging {
  
  private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(2)
  private implicit val ec: ExecutionContext = ExecutionContext.global
  private val tibiaDataClient = new TibiaDataClient()
  
  private var jda: Option[JDA] = None
  
  /**
   * Initialize the scheduler with JDA instance
   */
  def initialize(jdaInstance: JDA): Unit = {
    jda = Some(jdaInstance)
    scheduleDaily845AMTask()
    logger.info("DailyScheduler initialized with 8:45 AM UTC boosted announcements")
  }
  
  /**
   * Schedule task to run daily at 8:45 AM UTC
   */
  private def scheduleDaily845AMTask(): Unit = {
    val now = ZonedDateTime.now(ZoneId.of("UTC"))
    val targetTime = now.withHour(8).withMinute(45).withSecond(0).withNano(0)
    
    // If we've already passed today's 8:45 AM, schedule for tomorrow
    val nextRun = if (now.isAfter(targetTime)) {
      targetTime.plusDays(1)
    } else {
      targetTime
    }
    
    val initialDelay = java.time.Duration.between(now, nextRun).toMillis
    val period = TimeUnit.DAYS.toMillis(1) // 24 hours
    
    logger.info(s"Scheduling daily boosted announcements. Next run: ${nextRun} (in ${initialDelay}ms)")
    
    scheduler.scheduleAtFixedRate(
      () => {
        try {
          sendDailyBoostedAnnouncements()
        } catch {
          case e: Exception =>
            logger.error("Error in daily boosted announcements task", e)
        }
      },
      initialDelay,
      period,
      TimeUnit.MILLISECONDS
    )
  }
  
  /**
   * Send daily boosted announcements to all configured guilds
   */
  private def sendDailyBoostedAnnouncements(): Unit = {
    logger.info("Starting daily boosted announcements")
    
    jda.foreach { j =>
      val guilds = j.getGuilds.asScala.toList
      
      guilds.foreach { guild =>
        try {
          sendBoostedAnnouncementToGuild(guild)
        } catch {
          case e: Exception =>
            logger.error(s"Error sending boosted announcement to guild ${guild.getName}: ${e.getMessage}", e)
        }
      }
    }
  }
  
  /**
   * Send boosted announcement to a specific guild
   */
  private def sendBoostedAnnouncementToGuild(guild: Guild): Unit = {
    // Get guild's boosted channel configuration
    val discordConfig = BotApp.discordRetrieveConfig(guild)
    if (discordConfig.nonEmpty) {
      val boostedChannelId = discordConfig("boosted_channel")
        
        if (boostedChannelId != "0" && boostedChannelId.nonEmpty) {
          val boostedChannel = guild.getTextChannelById(boostedChannelId)
          
          if (boostedChannel != null && boostedChannel.canTalk()) {
            // Fetch boosted data and send announcement
            fetchAndSendBoostedData(guild, boostedChannel)
          } else {
            logger.warn(s"Cannot send to boosted channel in guild ${guild.getName}: channel not found or no permissions")
          }
        } else {
          logger.debug(s"No boosted channel configured for guild ${guild.getName}")
        }
    } else {
      logger.debug(s"No discord configuration found for guild ${guild.getName}")
    }
  }
  
  /**
   * Fetch all daily data and send formatted announcement
   */
  private def fetchAndSendBoostedData(guild: Guild, channel: net.dv8tion.jda.api.entities.channel.concrete.TextChannel): Unit = {
    val guildId = guild.getId
    
    // Fetch all required data: boosted monsters, news, and news ticker
    val boostedBossFuture = tibiaDataClient.getBoostedBoss()
    val boostedCreatureFuture = tibiaDataClient.getBoostedCreature()
    val latestNewsFuture = tibiaDataClient.getLatestNews()
    val newsTickerFuture = tibiaDataClient.getNewsTicker()
    
    for {
      bossResult <- boostedBossFuture
      creatureResult <- boostedCreatureFuture
      newsResult <- latestNewsFuture
      tickerResult <- newsTickerFuture
    } yield {
      val embed = createDailyAnnouncementEmbed(bossResult, creatureResult, newsResult, tickerResult, guildId)
      
      channel.sendMessageEmbeds(embed).queue(
        _ => logger.info(s"Successfully sent daily announcement to ${guild.getName}"),
        error => logger.error(s"Failed to send daily announcement to ${guild.getName}: ${error.getMessage}")
      )
    }
  }
  
  /**
   * Create the comprehensive daily announcement embed
   */
  private def createDailyAnnouncementEmbed(
    bossResult: Either[String, com.tibiabot.tibiadata.response.BoostedResponse],
    creatureResult: Either[String, com.tibiabot.tibiadata.response.CreatureResponse],
    newsResult: Either[String, com.tibiabot.tibiadata.response.NewsResponse],
    tickerResult: Either[String, com.tibiabot.tibiadata.response.NewsTickerResponse],
    guildId: String
  ): MessageEmbed = {
    
    val embed = new EmbedBuilder()
    embed.setTitle(I18nService.getMessage(guildId, MessageKeys.Daily.TITLE))
    embed.setColor(0x1E90FF) // Dodger blue
    embed.setTimestamp(java.time.Instant.now())
    
    val boostedBossEmoji = Config.boostedBossEmoji(guildId)
    val boostedCreatureEmoji = Config.boostedCreatureEmoji(guildId)
    
    // Process boosted boss
    bossResult match {
      case Right(boostedResponse) =>
        val boostedBoss = boostedResponse.boostable_bosses.boosted
        val bossName = boostedBoss.name
        val bossImageUrl = boostedBoss.image_url
        
        embed.addField(
          s"${boostedBossEmoji} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTED_BOSS)}",
          s"**[${bossName}](${BotApp.creatureWikiUrl(bossName)})**\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.LOCATION_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTABLE_BOSSES_LINK)}\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.BONUS_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOSS_BONUS)}\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.TIP_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOSS_HUNTING_PERFECT)}",
          true
        )
        
        // Set thumbnail to boss image if available - prefer GIF over static
        if (bossImageUrl.nonEmpty) {
          val gifUrl = bossImageUrl.replace(".png", ".gif")
          embed.setThumbnail(gifUrl)
        }
        
      case Left(error) =>
        embed.addField(
          s"${boostedBossEmoji} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTED_BOSS)}",
          I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_BOSS),
          true
        )
        logger.warn(s"Failed to fetch boosted boss: $error")
    }
    
    // Process boosted creature
    creatureResult match {
      case Right(creatureResponse) =>
        val boostedCreature = creatureResponse.creatures.boosted
        val creatureName = boostedCreature.name
        val creatureImageUrl = creatureResponse.creatures.boosted.image_url
        
        embed.addField(
          s"${boostedCreatureEmoji} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTED_CREATURE)}",
          s"**[${creatureName}](${BotApp.creatureWikiUrl(creatureName)})**\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.LOCATION_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.CREATURES_LIBRARY_LINK)}\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.BONUS_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.CREATURE_BONUS)}\n" +
          s"${I18nService.getMessage(guildId, MessageKeys.Daily.TIP_LABEL)} ${I18nService.getMessage(guildId, MessageKeys.Daily.CREATURE_HUNTING_PERFECT)}",
          true
        )
        
        // Set image to creature if no boss image - prefer GIF over static
        if (embed.build().getThumbnail == null && creatureImageUrl.nonEmpty) {
          val gifUrl = creatureImageUrl.replace(".png", ".gif")
          embed.setThumbnail(gifUrl)
        }
        
      case Left(error) =>
        embed.addField(
          s"${boostedCreatureEmoji} ${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTED_CREATURE)}",
          I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_CREATURE),
          true
        )
        logger.warn(s"Failed to fetch boosted creature: $error")
    }
    
    // Add Latest News section
    newsResult match {
      case Right(newsResponse) =>
        try {
          val latestNews = newsResponse.news.news.take(3) // Show only latest 3 news items
          if (latestNews.nonEmpty) {
            val newsText = latestNews.map { newsItem =>
              val truncatedTitle = if (newsItem.news.length > 80) {
                newsItem.news.take(77) + "..."
              } else {
                newsItem.news
              }
              s"• [${truncatedTitle}](${newsItem.url})"
            }.mkString("\n")
            
            embed.addField(
              I18nService.getMessage(guildId, MessageKeys.Daily.NEWS_HEADER),
              newsText,
              false
            )
          } else {
            // No news items available
            embed.addField(
              I18nService.getMessage(guildId, MessageKeys.Daily.NEWS_HEADER),
              I18nService.getMessage(guildId, MessageKeys.Daily.NO_NEWS_AVAILABLE),
              false
            )
          }
        } catch {
          case e: Exception =>
            logger.warn(s"Error processing news data: ${e.getMessage}")
            embed.addField(I18nService.getMessage(guildId, MessageKeys.Daily.NEWS_HEADER), I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_NEWS), false)
        }
      case Left(error) =>
        logger.warn(s"Failed to fetch latest news: $error")
        embed.addField(I18nService.getMessage(guildId, MessageKeys.Daily.NEWS_HEADER), I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_NEWS), false)
    }
    
    // Add News Ticker section
    tickerResult match {
      case Right(tickerResponse) =>
        try {
          val recentTickers = tickerResponse.news.news.take(2) // Show only 2 most recent
          if (recentTickers.nonEmpty) {
            val tickerText = recentTickers.map { ticker =>
              val truncatedMessage = if (ticker.news.length > 100) {
                ticker.news.take(97) + "..."
              } else {
                ticker.news
              }
              s"📢 ${truncatedMessage}"
            }.mkString("\n")
            
            embed.addField(
              I18nService.getMessage(guildId, MessageKeys.Daily.TICKER_HEADER),
              tickerText,
              false
            )
          } else {
            // No ticker items available
            embed.addField(
              I18nService.getMessage(guildId, MessageKeys.Daily.TICKER_HEADER),
              I18nService.getMessage(guildId, MessageKeys.Daily.NO_ANNOUNCEMENTS_AVAILABLE),
              false
            )
          }
        } catch {
          case e: Exception =>
            logger.warn(s"Error processing ticker data: ${e.getMessage}")
            embed.addField(I18nService.getMessage(guildId, MessageKeys.Daily.TICKER_HEADER), I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_ANNOUNCEMENTS), false)
        }
      case Left(error) =>
        logger.warn(s"Failed to fetch news ticker: $error")
        embed.addField(I18nService.getMessage(guildId, MessageKeys.Daily.TICKER_HEADER), I18nService.getMessage(guildId, MessageKeys.Daily.FAILED_LOAD_ANNOUNCEMENTS), false)
    }
    
    // Add footer with helpful information
    embed.setFooter(
      I18nService.getMessage(guildId, MessageKeys.Daily.FOOTER_RESET_INFO),
      "https://tibia.fandom.com/wiki/Special:Redirect/file/Tibia_logo.png"
    )
    
    // Add description with general info
    embed.setDescription(
      s"${I18nService.getMessage(guildId, MessageKeys.Daily.UPDATE_DESCRIPTION)}" +
      s"${I18nService.getMessage(guildId, MessageKeys.Daily.BOOSTED_MONSTERS_INFO)}" +
      s"${I18nService.getMessage(guildId, MessageKeys.Daily.LATEST_NEWS_INFO)}" +
      s"${I18nService.getMessage(guildId, MessageKeys.Daily.NEWS_TICKER_INFO)}\n\n" +
      s"${I18nService.getMessage(guildId, MessageKeys.Daily.TIP_BOOSTED_BONUSES)}"
    )
    
    embed.build()
  }
  
  /**
   * Manual trigger for testing (can be called via admin command)
   */
  def triggerManualAnnouncement(guild: Guild): Unit = {
    logger.info(s"Manual daily announcement triggered for guild ${guild.getName}")
    sendBoostedAnnouncementToGuild(guild)
  }
  
  /**
   * Get next scheduled announcement time
   */
  def getNextAnnouncementTime: ZonedDateTime = {
    val now = ZonedDateTime.now(ZoneId.of("UTC"))
    val targetTime = now.withHour(8).withMinute(45).withSecond(0).withNano(0)
    
    if (now.isAfter(targetTime)) {
      targetTime.plusDays(1)
    } else {
      targetTime
    }
  }
  
  /**
   * Shutdown the scheduler
   */
  def shutdown(): Unit = {
    scheduler.shutdown()
    logger.info("DailyScheduler shutdown")
  }
}