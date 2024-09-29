package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageCommandUsage
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandFoodModule : Module("CommandFood") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/food", true)) {
            event.isCancelled = true

            val args = event.message.split(" ")

            when (args.size) {
                2 -> {
                    args[1].toIntOrNull()
                        ?.takeIf {
                            it in 0..20
                        }
                        ?.also {
                            event.player.foodLevel = it
                            event.player.sendMessageWithPrefix("饥饿值已更新")
                        }
                        ?: run {
                            event.player.sendMessageWithPrefix("无效的饥饿值")
                        }
                }
                else -> event.player.sendMessageCommandUsage("/food <饥饿值>")
            }
        }
    }
}