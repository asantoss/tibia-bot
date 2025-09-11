# Emoji Files Directory

This directory contains all the emoji image files used by the Tibia Bot.

## File Requirements

- **Static emojis**: PNG format, recommended 128x128 pixels
- **Animated emojis**: GIF format, recommended 128x128 pixels  
- **File size limit**: 256KB maximum (Discord requirement)
- **Naming convention**: Use the same name as defined in `emoji-mapping.conf`

## Current Emojis

The following emoji files should be placed in this directory:

### Combat/Creature Emojis
- `nemesis.png` - Nemesis creatures (⚔️ fallback)
- `archfoe.png` - Archfoe creatures (🗡️ fallback)
- `bane.png` - Bane creatures (💀 fallback)
- `summon.png` - Boss summons (👤 fallback)
- `primal.gif` - Primal creatures (💫 fallback) *animated*
- `hazard.png` - Hazard creatures (⚠️ fallback)

### Guild/Player Emojis
- `ally.png` - Allied guilds/players (🤝 fallback)
- `enemy.png` - Enemy guilds/players (⚔️ fallback)
- `neutral.png` - Neutral guilds/players (🌐 fallback)

### Boss/Location Emojis
- `mortalkombat.png` - Mortal Kombat bosses (🥊 fallback)
- `cube.png` - Cube bosses (📦 fallback)
- `zelos.gif` - Zelos bosses (🌊 fallback) *animated*
- `library.gif` - Library bosses (📚 fallback) *animated*
- `hod.gif` - Heart of Destruction bosses (🏛️ fallback) *animated*
- `feru.gif` - Ferumbras bosses (🌋 fallback) *animated*
- `inq.png` - Inquisition bosses (🔥 fallback)
- `kilmaresh.png` - Kilmaresh bosses (🏺 fallback)

### Svar Emojis
- `greenhorn.png` - Svar Green bosses (🟢 fallback)
- `scrapper.png` - Svar Scrapper bosses (🔧 fallback)
- `warlord.png` - Svar Warlord bosses (👑 fallback)

### Utility Emojis
- `dart.png` - Exiva spell (🎯 fallback)
- `indent.png` - Indentation (◦ fallback)
- `levelup.png` - Level up (⬆️ fallback)

### Interaction Emojis
- `yes.png` - Positive response (✅ fallback)
- `no.png` - Negative response (❌ fallback)

### Item/Game Emojis
- `bagdesire.gif` - Bag of Desire (💼 fallback) *animated*
- `bagcovet.gif` - Bag of Covet (💰 fallback) *animated*
- `letter.png` - Letter/Mail (📧 fallback)
- `gold.png` - Gold coins (🪙 fallback)
- `boss.png` - Boss creature (👹 fallback)
- `creature.png` - Regular creature (🐉 fallback)
- `satchel.png` - Satchel/Bag (🎒 fallback)

### Boosted Emojis
- `boostedboss.gif` - Boosted boss (👹 fallback) *animated*
- `boostedcreature.gif` - Boosted creature (🐉 fallback) *animated*

### Torch Emojis
- `torchon.png` - Torch on (🔥 fallback)
- `torchoff.png` - Torch off (🌫️ fallback)

### Additional
- `tcstack.gif` - TC stack (💎 fallback) *animated*

## Sources

To obtain these emoji files:

1. **Extract from Discord**: Use Discord developer tools to download existing emojis
2. **Create custom**: Design new emojis following Tibia's art style
3. **Community resources**: Use Tibia community-created emojis with permission
4. **Tibia assets**: Extract and modify game assets (ensure compliance with CipSoft's terms)

## Installation

1. Place emoji files in this directory following the naming convention
2. Ensure files meet size requirements (≤256KB)
3. Restart the bot to load new emojis
4. The bot will automatically upload emojis to guilds as needed

## Testing

To test emoji loading:

1. Check bot logs for emoji upload messages
2. Verify emojis appear in Discord guild emoji settings
3. Test fallback behavior by removing emoji files temporarily
4. Use `EmojiManager.getEmojiStats` to check status

## Note

This directory should contain actual image files. Currently, it contains only this README file. 
You'll need to add the actual emoji image files for the system to work properly.