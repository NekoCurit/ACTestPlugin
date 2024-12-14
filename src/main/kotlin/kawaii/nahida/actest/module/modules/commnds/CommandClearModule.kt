package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandClearModule : Module("CommandHealth") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("clear")) {
            event.isCancelled = true
            event.player.inventory.clear()
            event.player.sendMessageWithPrefix("清空背包成功")
        }
    }
}