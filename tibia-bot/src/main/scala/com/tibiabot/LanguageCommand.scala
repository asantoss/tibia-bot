package com.tibiabot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.{CommandData, Commands, OptionData}
import com.typesafe.scalalogging.StrictLogging

/**
 * Handles language configuration commands for the bot
 */
object LanguageCommand extends StrictLogging {
  
  /**
   * Get flag emoji for a language
   */
  private def getLanguageFlag(language: I18nService.Language): String = {
    language match {
      case I18nService.English => "🇬🇧"
      case I18nService.Spanish => "🇪🇸"
      case I18nService.Portuguese => "🇧🇷"
      case I18nService.German => "🇩🇪"
      case I18nService.Polish => "🇵🇱"
      case _ => "🏳️"
    }
  }
  
  /**
   * Create the slash command data for language configuration
   */
  def createCommand(): CommandData = {
    val languageOptions = I18nService.supportedLanguages.map { lang =>
      s"${lang.code} (${lang.locale.getDisplayLanguage})"
    }.mkString(", ")
    
    Commands.slash("language", I18nService.getMessage("en", MessageKeys.Commands.LANGUAGE_DESCRIPTION))
      .addOptions(
        new OptionData(OptionType.STRING, "set", s"Set language. Options: $languageOptions")
          .setRequired(false),
        new OptionData(OptionType.BOOLEAN, "show", "Show current language setting")
          .setRequired(false)
      )
  }
  
  /**
   * Handle language command execution
   */
  def handleCommand(event: SlashCommandInteractionEvent): Unit = {
    val guild = event.getGuild
    if (guild == null) {
      // Default to English since we don't have a guild ID
      val errorMessage = I18nService.getMessage("en", MessageKeys.Commands.SERVER_ONLY) 
      event.getHook.sendMessage(errorMessage).setEphemeral(true).queue()
      return
    }
    
    val member = event.getMember
    if (member == null || !member.hasPermission(Permission.ADMINISTRATOR)) {
      val errorMessage = I18nService.getMessage(guild.getId, MessageKeys.Commands.PERMISSION_DENIED)
      event.getHook.sendMessage(errorMessage).setEphemeral(true).queue()
      return
    }
    
    val setOption = event.getOption("set")
    val showOption = event.getOption("show")
    
    if (setOption != null) {
      val languageCode = setOption.getAsString.toLowerCase.trim
      I18nService.parseLanguage(languageCode) match {
        case Some(language) =>
          if (I18nService.setGuildLanguage(guild.getId, language)) {
            val flag = getLanguageFlag(language)
            val successMessage = I18nService.getMessage(language, "commands.language.set_success", s"$flag ${language.locale.getDisplayLanguage}")
            val embed = new EmbedBuilder()
              .setTitle("🌐 Language Settings")
              .setDescription(successMessage)
              .setColor(0x00FF00)
              .build()
            event.getHook.sendMessageEmbeds(embed).queue()
          } else {
            val errorMessage = I18nService.getMessage(guild.getId, "commands.language.set_error")
            event.getHook.sendMessage(errorMessage).setEphemeral(true).queue()
          }
        case None =>
          val currentLanguage = I18nService.getGuildLanguage(guild.getId)
          val supportedList = I18nService.supportedLanguages.map { lang =>
            val flag = getLanguageFlag(lang)
            s"• $flag `${lang.code}` - ${lang.locale.getDisplayLanguage}"
          }.mkString("\n")
          val errorMessage = I18nService.getMessage(currentLanguage, "commands.language.invalid_language", languageCode, supportedList)
          event.getHook.sendMessage(errorMessage).setEphemeral(true).queue()
      }
    } else if (showOption != null && showOption.getAsBoolean) {
      showCurrentLanguage(event, guild)
    } else {
      showLanguageHelp(event, guild)
    }
  }
  
  private def showCurrentLanguage(event: SlashCommandInteractionEvent, guild: Guild): Unit = {
    val currentLanguage = I18nService.getGuildLanguage(guild.getId)
    val languageName = currentLanguage.locale.getDisplayLanguage
    val flag = getLanguageFlag(currentLanguage)
    val message = I18nService.getMessage(currentLanguage, "commands.language.current", s"$flag $languageName")
    
    val embed = new EmbedBuilder()
      .setTitle("🌐 Current Language Settings")
      .setDescription(message)
      .setColor(0x3498DB)
      .build()
    
    event.getHook.sendMessageEmbeds(embed).queue()
  }
  
  private def showLanguageHelp(event: SlashCommandInteractionEvent, guild: Guild): Unit = {
    val currentLanguage = I18nService.getGuildLanguage(guild.getId)
    
    val supportedList = I18nService.supportedLanguages.map { lang =>
      val indicator = if (lang == currentLanguage) "✅" else "  "
      val flag = getLanguageFlag(lang)
      s"$indicator $flag `${lang.code}` - ${lang.locale.getDisplayLanguage}"
    }.mkString("\n")
    
    val usageExamples = I18nService.supportedLanguages.map { lang =>
      val flag = getLanguageFlag(lang)
      s"• `/language set ${lang.code}` - $flag ${lang.locale.getDisplayLanguage}"
    }.mkString("\n") + "\n• `/language show true` - Show current language"
    
    val embed = new EmbedBuilder()
      .setTitle("🌐 Language Configuration")
      .setDescription(I18nService.getMessage(currentLanguage, "commands.language.help"))
      .addField("Available Languages", supportedList, false)
      .addField("Usage Examples", usageExamples, false)
      .setColor(0x3498DB)
      .build()
    
    event.getHook.sendMessageEmbeds(embed).queue()
  }
}