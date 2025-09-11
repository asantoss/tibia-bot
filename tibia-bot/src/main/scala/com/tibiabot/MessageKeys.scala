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
    val INVALID_SUBCOMMAND = "commands.invalid_subcommand"
    val INVALID_SUBCOMMAND_GROUP = "commands.invalid_subcommand_group"
    
    // Command descriptions
    val SETUP_DESCRIPTION = "commands.setup.description"
    val REMOVE_DESCRIPTION = "commands.remove.description"
    val HUNTED_DESCRIPTION = "commands.hunted.description"
    val ALLIES_DESCRIPTION = "commands.allies.description"
    val NEUTRAL_DESCRIPTION = "commands.neutral.description"
    val FULLBLESS_DESCRIPTION = "commands.fullbless.description"
    val LEADERBOARDS_DESCRIPTION = "commands.leaderboards.description"
    val FILTER_DESCRIPTION = "commands.filter.description"
    val ADMIN_DESCRIPTION = "commands.admin.description"
    val EXIVA_DESCRIPTION = "commands.exiva.description"
    val HELP_DESCRIPTION = "commands.help.description"
    val REPAIR_DESCRIPTION = "commands.repair.description"
    val GALTHEN_DESCRIPTION = "commands.galthen.description"
    val ONLINE_DESCRIPTION = "commands.online.description"
    val BOOSTED_DESCRIPTION = "commands.boosted.description"
    val LANGUAGE_DESCRIPTION = "commands.language.description"
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
    val DM_SENT = "screenshots.dm_sent"
    val DM_INSTRUCTIONS = "screenshots.dm_instructions"
    val DM_FALLBACK = "screenshots.dm_fallback"
    val INVALID_URL = "screenshots.invalid_url"
    val NO_URL = "screenshots.no_url"
    val SAVE_FAILED = "screenshots.save_failed"
    val WORLD_ERROR = "screenshots.world_error"
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
    val NOTIFICATION_ALL_ENABLED = "boosted.notification_all_enabled"
    val NOTIFICATION_ALL_DISABLED = "boosted.notification_all_disabled"
    val LIST_EMPTY = "boosted.list_empty"
    val LIST_MESSAGE = "boosted.list_message"
    val ALREADY_EXISTS = "boosted.already_exists"
    val ITEM_ADDED = "boosted.item_added"
    val ITEM_REMOVED = "boosted.item_removed"
    val NOT_ON_LIST = "boosted.not_on_list"
    val INVALID_ITEM = "boosted.invalid_item"
    val COMMAND_FAILED = "boosted.command_failed"
  }
  
  // Error messages
  object Errors {
    val WORLD_NOT_CONFIGURED = "errors.world_not_configured"
    val INVALID_BUTTON_FORMAT = "errors.invalid_button_format"
    val DATABASE_ERROR = "errors.database_error"
    val API_ERROR = "errors.api_error"
    val PERMISSION_ERROR = "errors.permission_error"
    val GENERIC_ERROR = "errors.generic_error"
    val MUST_SUPPLY_LABEL_EMOJI = "errors.must_supply_label_emoji"
    val INVALID_EMOJI = "errors.invalid_emoji"
    val CUSTOM_EMOJIS_NOT_SUPPORTED = "errors.custom_emojis_not_supported"
    val INVALID_OPTION = "errors.invalid_option"
    val FAILED_TO_ADD_ROLE = "errors.failed_to_add_role"
    val FAILED_TO_REMOVE_ROLE = "errors.failed_to_remove_role"
    val ROLE_HIERARCHY_ISSUE = "errors.role_hierarchy_issue"
    val FAILED_TRIGGER_ANNOUNCEMENT = "errors.failed_trigger_announcement"
    val FAILED_UPLOAD_SCREENSHOT = "errors.failed_upload_screenshot"
  }
  
  // Admin success messages
  object AdminSuccess {
    val MANUAL_ANNOUNCEMENT_TRIGGERED = "admin.manual_announcement_triggered"
    val ANNOUNCEMENT_INCLUDES = "admin.announcement_includes"
    val NEXT_SCHEDULED = "admin.next_scheduled"
  }
  
  // Embed titles
  object Embeds {
    val DEATH_TITLE = "embeds.death_title"
    val ONLINE_TITLE = "embeds.online_title"
    val GUILD_WAR_TITLE = "embeds.guild_war_title"
    val BOOSTED_TITLE = "embeds.boosted_title"
    val CONFIGURATION_TITLE = "embeds.configuration_title"
    val HELP_TITLE = "embeds.help_title"
    val EXISTING_COOLDOWNS = "embeds.existing_cooldowns"
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
    val REMOVE = "buttons.remove"
    val CLEAR = "buttons.clear"
    val CLEAR_ALL = "buttons.clear_all"
    val ADD_COOLDOWN = "buttons.add_cooldown"
    val UNLOCK = "buttons.unlock"
    val LOCK = "buttons.lock"
    val COLLECTED = "buttons.collected"
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
  
  // Galthen's Satchel
  object Satchel {
    val DESCRIPTION = "satchel.description"
    val MANAGE_DESCRIPTION = "satchel.manage_description"
    val INSTRUCTIONS = "satchel.instructions"
    val FOOTER = "satchel.footer"
    val COLLECTED = "satchel.collected"
    val COOLDOWN_SET = "satchel.cooldown_set"
    val COOLDOWN_EXPIRED = "satchel.cooldown_expired"
  }
  
  // Setup and Configuration
  object Setup {
    val NEED_SETUP = "setup.need_setup"
    val ALREADY_CONFIGURED = "setup.already_configured"
    val SETTING_UPDATED = "setup.setting_updated"
    val CHANNEL_HELP_LEVELS = "setup.channel_help_levels"
    val CHANNEL_HELP_DEATHS = "setup.channel_help_deaths"
    val CHANNEL_HELP_ACTIVITY = "setup.channel_help_activity"
  }
  
  // Admin notifications
  object Admin {
    val GUILD_ADDED_HUNTED = "admin.guild_added_hunted"
    val PLAYER_ADDED_HUNTED = "admin.player_added_hunted"
    val GUILD_ADDED_ALLIES = "admin.guild_added_allies"
    val PLAYER_ADDED_ALLIES = "admin.player_added_allies"
    val GUILD_REMOVED_HUNTED = "admin.guild_removed_hunted"
    val PLAYER_REMOVED_HUNTED = "admin.player_removed_hunted"
    val GUILD_REMOVED_ALLIES = "admin.guild_removed_allies"
    val PLAYER_REMOVED_ALLIES = "admin.player_removed_allies"
    val AUTO_ENEMY_DETECTION_SET = "admin.auto_enemy_detection_set"
    val CHANNEL_SETTING_CHANGED = "admin.channel_setting_changed"
    
    // Automated admin actions
    val ENEMY_JOINED_ALLIED = "admin.enemy_joined_allied"
    val ENEMY_AUTO_DETECTED = "admin.enemy_auto_detected"
    val HUNTED_LIST_CLEANUP = "admin.hunted_list_cleanup"
    val ALLIED_LIST_CLEANUP = "admin.allied_list_cleanup"
    val PLAYER_REMOVED_JOINED_ENEMY = "admin.player_removed_joined_enemy"
    val PLAYER_ADDED_LEFT_HUNTED = "admin.player_added_left_hunted"
    val PLAYER_REMOVED_JOINED_GUILD = "admin.player_removed_joined_guild"
    val PLAYER_ADDED_KILLER = "admin.player_added_killer"
    val PLAYER_REMOVED_LEFT_ALLIED = "admin.player_removed_left_allied"
    val PLAYER_REMOVED_JOINED_ALLIED = "admin.player_removed_joined_allied"
    val PLAYER_REMOVED_JOINED_ENEMY_GUILD = "admin.player_removed_joined_enemy_guild"
  }
  
  // Activity notifications
  object Activity {
    val NAME_CHANGE = "activity.name_change"
    val LEFT_GUILD = "activity.left_guild"
    val LEFT_JOINED_GUILD = "activity.left_joined_guild"
    val JOINED_GUILD = "activity.joined_guild"
  }
  
  // Daily announcements and boosted creatures
  object Daily {
    val TITLE = "daily.title"
    val UPDATE_DESCRIPTION = "daily.update_description"
    val BOOSTED_MONSTERS_INFO = "daily.boosted_monsters_info"
    val LATEST_NEWS_INFO = "daily.latest_news_info"
    val NEWS_TICKER_INFO = "daily.news_ticker_info"
    val BOOSTED_CREATURE = "daily.boosted_creature"
    val BOOSTED_BOSS = "daily.boosted_boss"
    val RASHID_LOCATION = "daily.rashid_location"
    val SERVER_SAVE = "daily.server_save"
    val RAPID_RESPAWN = "daily.rapid_respawn"
    val DOUBLE_EXP = "daily.double_exp"
    val NO_BOOSTED_INFO = "daily.no_boosted_info"
    val CREATURE_DESCRIPTION = "daily.creature_description"
    val BOSS_DESCRIPTION = "daily.boss_description"
    val CREATURE_BONUS = "daily.creature_bonus"
    val CREATURE_SPAWN = "daily.creature_spawn"
    val BOSS_BONUS = "daily.boss_bonus"
    val BOSS_RESPAWN = "daily.boss_respawn"
    val LOCATION_LABEL = "daily.location_label"
    val BONUS_LABEL = "daily.bonus_label"
    val TIP_LABEL = "daily.tip_label"
    val BOSS_HUNTING_TIP = "daily.boss_hunting_tip"
    val CREATURE_HUNTING_TIP = "daily.creature_hunting_tip"
    val FAILED_LOAD_BOSS = "daily.failed_load_boss"
    val FAILED_LOAD_CREATURE = "daily.failed_load_creature"
    val FAILED_LOAD_NEWS = "daily.failed_load_news"
    val FAILED_LOAD_ANNOUNCEMENTS = "daily.failed_load_announcements"
    val NO_NEWS_AVAILABLE = "daily.no_news_available"
    val NO_ANNOUNCEMENTS_AVAILABLE = "daily.no_announcements_available"
    val NEWS_HEADER = "daily.news_header"
    val TICKER_HEADER = "daily.ticker_header"
    val BOOSTABLE_BOSSES_LINK = "daily.boostable_bosses_link"
    val SERVER_SAVE_BUTTON = "daily.server_save_button"
    val CREATURES_LIBRARY_LINK = "daily.creatures_library_link"
    val BOSS_HUNTING_PERFECT = "daily.boss_hunting_perfect"
    val CREATURE_HUNTING_PERFECT = "daily.creature_hunting_perfect"
    val FOOTER_RESET_INFO = "daily.footer_reset_info"
    val TIP_BOOSTED_BONUSES = "daily.tip_boosted_bonuses"
  }
  
  // Role management
  object Roles {
    val ADD_CONFIRM = "roles.add_confirm"
    val REMOVE_CONFIRM = "roles.remove_confirm"
    val ADDED_SUCCESS = "roles.added_success"
    val REMOVED_SUCCESS = "roles.removed_success"
    val PERMISSION_ERROR = "roles.permission_error"
    val ROLE_NOT_FOUND = "roles.role_not_found"
    val OPERATION_FAILED = "roles.operation_failed"
  }
  
  // Modal forms and inputs
  object Modal {
    val TAG_TITLE = "modal.tag_title"
    val TAG_PLACEHOLDER = "modal.tag_placeholder"
    val TAG_LABEL = "modal.tag_label"
    val TAG_DESCRIPTION = "modal.tag_description"
    val WORLD_TITLE = "modal.world_title"
    val WORLD_PLACEHOLDER = "modal.world_placeholder"
    val WORLD_LABEL = "modal.world_label"
    
    // Galthen's Satchel modals
    val GALTHEN_ADD_TITLE = "modal.galthen_add_title"
    val GALTHEN_ADD_LABEL = "modal.galthen_add_label"
    val GALTHEN_ADD_PLACEHOLDER = "modal.galthen_add_placeholder"
    val GALTHEN_REMOVE_TITLE = "modal.galthen_remove_title"
    val GALTHEN_REMOVE_LABEL = "modal.galthen_remove_label"
    val GALTHEN_REMOVE_PLACEHOLDER = "modal.galthen_remove_placeholder"
    
    // Boosted notifications modals
    val BOOSTED_ADD_TITLE = "modal.boosted_add_title"
    val BOOSTED_ADD_LABEL = "modal.boosted_add_label"
    val BOOSTED_ADD_PLACEHOLDER = "modal.boosted_add_placeholder"
    val BOOSTED_REMOVE_TITLE = "modal.boosted_remove_title"
    val BOOSTED_REMOVE_LABEL = "modal.boosted_remove_label"
  }
  
  // Loading and status messages
  object Loading {
    val FETCHING_DATA = "loading.fetching_data"
    val PROCESSING = "loading.processing"
  }
  
  object Status {
    val ENABLED = "status.enabled"
    val DISABLED = "status.disabled"
    val ACTIVE = "status.active"
    val INACTIVE = "status.inactive"
  }
  
  // Tips and instructions
  object Tips {
    val SCREENSHOT_PASTE = "tips.screenshot_paste"
    val DM_UPLOAD = "tips.dm_upload"
    val CANCEL_INSTRUCTION = "tips.cancel_instruction"
    val TIMEOUT_WARNING = "tips.timeout_warning"
    val UPLOAD_CANCELLED = "tips.upload_cancelled"
    val UPLOADS_CANCELLED = "tips.uploads_cancelled"
    val NO_PENDING_UPLOADS = "tips.no_pending_uploads"
    val DM_HELP_MESSAGE = "tips.dm_help_message"
    val DM_UPLOAD_INSTRUCTIONS = "tips.dm_upload_instructions"
    val SCREENSHOT_UPLOADED_SUCCESS = "tips.screenshot_uploaded_success"
  }
  
  // UI Elements
  object UI {
    val TAG_PREFIX = "ui.tag_prefix"
    val ADD_SCREENSHOT_BUTTON = "ui.add_screenshot_button"
  }
  
  // Location and world info
  object Location {
    val RASHID_ANKRAHMUN = "location.rashid_ankrahmun"
    val RASHID_DARASHIA = "location.rashid_darashia"
    val RASHID_EDRON = "location.rashid_edron"
    val RASHID_LIBERTY_BAY = "location.rashid_liberty_bay"
    val RASHID_PORT_HOPE = "location.rashid_port_hope"
    val RASHID_SVARGROND = "location.rashid_svargrond"
    val RASHID_VENORE = "location.rashid_venore"
    val RASHID_CARLIN = "location.rashid_carlin"
  }
  
  // Notifications and alerts
  object Notifications {
    val BOOSTED_ENABLED = "notifications.boosted_enabled"
    val BOOSTED_DISABLED = "notifications.boosted_disabled"
    val ALL_DISABLED = "notifications.all_disabled"
    val REMINDER_SET = "notifications.reminder_set"
    val REMINDER_EXPIRED = "notifications.reminder_expired"
  }
}