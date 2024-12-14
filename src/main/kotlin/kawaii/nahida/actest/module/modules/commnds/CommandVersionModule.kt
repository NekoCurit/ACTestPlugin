package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandVersionModule : Module("CommandHealth") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("version")) {
            event.isCancelled = true
            event.player.sendMessageWithPrefix("猫猫的反作弊测试服 版本:NaN")
        }
    }
}