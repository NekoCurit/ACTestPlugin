package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandFlyModule : Module("CommandFly") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("fly")) {
            event.isCancelled = true
            event.player.allowFlight = !event.player.allowFlight
            event.player.sendMessageWithPrefix("已${if (event.player.allowFlight) "开启" else "关闭"}飞行")
        }
    }
}