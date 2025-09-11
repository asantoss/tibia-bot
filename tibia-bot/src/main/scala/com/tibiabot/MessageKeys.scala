package com.tibiabot

/**
 * Constants for translation keys used throughout the bot.
 * This centralizes all message keys and provides compile-time checking.
 */
object MessageKeys {
  
  // Command responses
  object Commands {
    val SETUP_SUCCESS = "commands.setup.success"
    val SETUP_ERROR = "commands.setup.error"
    val SETUP_ALREADY_CONFIGURED = "commands.setup.already_configured"
    val INVALID_WORLD = "commands.invalid_world"
    val PERMISSION_DENIED = "commands.permission_denied"
    val COMMAND_ERROR = "commands.error"
  }
  
  // Screenshot functionality
  object Screenshots {
    val ADDED_SUCCESS = "screenshots.added_success"
    val UPLOAD_REQUEST = "screenshots.upload_request"
    val UPLOAD_INSTRUCTIONS = "screenshots.upload_instructions"
    val DELETE_SUCCESS = "screenshots.delete_success"
    val DELETE_PERMISSION_ERROR = "screenshots.delete_permission_error"
    val NOT_FOUND = "screenshots.not_found"
    val FOOTER_ADDED_BY = "screenshots.footer_added_by"
    val BUTTON_ADD = "screenshots.button_add"
    val BUTTON_DELETE = "screenshots.button_delete"
  }
  
  // Death tracking
  object Deaths {
    val DEATH_DETECTED = "deaths.death_detected"
    val LEVEL_LOSS = "deaths.level_loss"
    val NO_LEVEL_LOSS = "deaths.no_level_loss"
    val KILLED_BY = "deaths.killed_by"
    val AT_LEVEL = "deaths.at_level"
    val ASSISTED_BY = "deaths.assisted_by"
  }
  
  // Online tracking
  object Online {
    val PLAYER_ONLINE = "online.player_online"
    val PLAYER_OFFLINE = "online.player_offline"
    val LEVEL_UP = "online.level_up"
    val LEVEL_DOWN = "online.level_down"
    val VOCATION_CHANGE = "online.vocation_change"
  }
  
  // Guild wars
  object GuildWars {
    val WAR_STARTED = "guild_wars.war_started"
    val WAR_ENDED = "guild_wars.war_ended"
    val WAR_INVITED = "guild_wars.war_invited"
    val WAR_ACCEPTED = "guild_wars.war_accepted"
    val WAR_REJECTED = "guild_wars.war_rejected"
  }
  
  // Boosted creatures/bosses
  object Boosted {
    val CREATURE_BOOSTED = "boosted.creature_boosted"
    val BOSS_BOOSTED = "boosted.boss_boosted"
    val NOTIFICATION_ENABLED = "boosted.notification_enabled"
    val NOTIFICATION_DISABLED = "boosted.notification_disabled"
    val NOTIFICATION_ALL = "boosted.notification_all"
  }
  
  // Error messages
  object Errors {
    val WORLD_NOT_CONFIGURED = "errors.world_not_configured"
    val INVALID_BUTTON_FORMAT = "errors.invalid_button_format"
    val DATABASE_ERROR = "errors.database_error"
    val API_ERROR = "errors.api_error"
    val PERMISSION_ERROR = "errors.permission_error"
    val GENERIC_ERROR = "errors.generic_error"
  }
  
  // Embed titles
  object Embeds {
    val DEATH_TITLE = "embeds.death_title"
    val ONLINE_TITLE = "embeds.online_title"
    val GUILD_WAR_TITLE = "embeds.guild_war_title"
    val BOOSTED_TITLE = "embeds.boosted_title"
    val CONFIGURATION_TITLE = "embeds.configuration_title"
    val HELP_TITLE = "embeds.help_title"
  }
  
  // Field labels
  object Fields {
    val CHARACTER = "fields.character"
    val LEVEL = "fields.level"
    val VOCATION = "fields.vocation"
    val WORLD = "fields.world"
    val GUILD = "fields.guild"
    val TIME = "fields.time"
    val KILLER = "fields.killer"
    val EXPERIENCE = "fields.experience"
    val REASON = "fields.reason"
    val STATUS = "fields.status"
  }
  
  // Button labels
  object Buttons {
    val YES = "buttons.yes"
    val NO = "buttons.no"
    val NEXT = "buttons.next"
    val PREVIOUS = "buttons.previous"
    val DELETE = "buttons.delete"
    val ADD = "buttons.add"
    val CONFIGURE = "buttons.configure"
    val HELP = "buttons.help"
  }
  
  // Navigation
  object Navigation {
    val PAGE_INFO = "navigation.page_info"
    val NO_RESULTS = "navigation.no_results"
    val TOTAL_RESULTS = "navigation.total_results"
  }
  
  // Help and documentation
  object Help {
    val GENERAL_HELP = "help.general_help"
    val COMMAND_USAGE = "help.command_usage"
    val SETUP_INSTRUCTIONS = "help.setup_instructions"
    val SCREENSHOT_HELP = "help.screenshot_help"
    val CONFIGURATION_HELP = "help.configuration_help"
  }
  
  // Time and date formatting
  object Time {
    val SECONDS_AGO = "time.seconds_ago"
    val MINUTES_AGO = "time.minutes_ago"
    val HOURS_AGO = "time.hours_ago"
    val DAYS_AGO = "time.days_ago"
    val JUST_NOW = "time.just_now"
  }
  
  // Validation messages
  object Validation {
    val REQUIRED_FIELD = "validation.required_field"
    val INVALID_FORMAT = "validation.invalid_format"
    val OUT_OF_RANGE = "validation.out_of_range"
    val DUPLICATE_ENTRY = "validation.duplicate_entry"
  }
}