package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandClearModule : Module("CommandHealth") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/clear", true)) {
            event.isCancelled = true
            event.player.inventory.clear()
            event.player.sendMessageWithPrefix("清空背包成功")
        }
    }
}