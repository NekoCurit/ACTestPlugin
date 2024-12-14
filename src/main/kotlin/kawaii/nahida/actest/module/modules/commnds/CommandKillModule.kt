package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandKillModule : Module("CommandKill") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("kill")) {
            event.isCancelled = true
            event.player.health = 0.0
        }
    }
}