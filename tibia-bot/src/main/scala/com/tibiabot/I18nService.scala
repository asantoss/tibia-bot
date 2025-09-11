package com.tibiabot

import java.util.{Locale, ResourceBundle}
import scala.util.{Failure, Success, Try}
import com.typesafe.scalalogging.StrictLogging

/**
 * Service for handling internationalization (i18n) in the bot.
 * Provides methods to get translated messages based on guild language preferences.
 */
object I18nService extends StrictLogging {
  
  // Supported languages
  sealed trait Language {
    def code: String
    def locale: Locale
  }
  
  case object English extends Language {
    val code = "en"
    val locale = Locale.ENGLISH
  }
  
  case object Spanish extends Language {
    val code = "es" 
    val locale = new Locale("es")
  }
  
  case object Portuguese extends Language {
    val code = "pt"
    val locale = new Locale("pt")
  }
  
  case object German extends Language {
    val code = "de"
    val locale = Locale.GERMAN
  }
  
  case object Polish extends Language {
    val code = "pl"
    val locale = new Locale("pl")
  }
  
  val supportedLanguages: List[Language] = List(English, Spanish, Portuguese, German, Polish)
  val defaultLanguage: Language = English
  
  // Cache for ResourceBundles to avoid repeated loading
  private val bundleCache = scala.collection.mutable.Map[Language, ResourceBundle]()
  
  /**
   * Get a ResourceBundle for the specified language
   */
  private def getBundle(language: Language): ResourceBundle = {
    bundleCache.getOrElseUpdate(language, {
      Try {
        ResourceBundle.getBundle("messages", language.locale)
      } match {
        case Success(bundle) => bundle
        case Failure(ex) =>
          logger.warn(s"Failed to load resource bundle for language ${language.code}, falling back to English: ${ex.getMessage}")
          ResourceBundle.getBundle("messages", defaultLanguage.locale)
      }
    })
  }
  
  /**
   * Get translated message for a guild
   */
  def getMessage(guildId: String, key: String, args: Any*): String = {
    val language = getGuildLanguage(guildId)
    getMessage(language, key, args: _*)
  }
  
  /**
   * Get translated message for a specific language
   */
  def getMessage(language: Language, key: String, args: Any*): String = {
    try {
      val bundle = getBundle(language)
      val message = bundle.getString(key)
      
      // Handle string formatting with arguments
      if (args.nonEmpty) {
        String.format(message, args.map(_.toString): _*)
      } else {
        message
      }
    } catch {
      case ex: Exception =>
        logger.error(s"Failed to get message for key '$key' in language ${language.code}: ${ex.getMessage}")
        s"[MISSING: $key]"
    }
  }
  
  /**
   * Get language preference for a guild
   */
  def getGuildLanguage(guildId: String): Language = {
    BotApp.getGuildLanguage(guildId) match {
      case Some(code) => parseLanguage(code).getOrElse(defaultLanguage)
      case None => defaultLanguage
    }
  }
  
  /**
   * Set language preference for a guild
   */
  def setGuildLanguage(guildId: String, language: Language): Boolean = {
    BotApp.setGuildLanguage(guildId, language.code)
  }
  
  /**
   * Parse language from string code
   */
  def parseLanguage(code: String): Option[Language] = {
    supportedLanguages.find(_.code.equalsIgnoreCase(code))
  }
}