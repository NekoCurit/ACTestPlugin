package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandVersionModule : Module("CommandHealth") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/version", true)) {
            event.isCancelled = true
            event.player.sendMessageWithPrefix("猫猫的反作弊测试服 版本:NaN")
        }
    }
}