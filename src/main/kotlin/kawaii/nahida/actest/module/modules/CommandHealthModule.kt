package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageCommandUsage
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandHealthModule : Module("CommandHealth") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/health", true)) {
            event.isCancelled = true

            val args = event.message.split(" ")

            when (args.size) {
                2 -> {
                    args[1].toDoubleOrNull()
                        ?.takeIf {
                            it in 0.0..event.player.maxHealth
                        }
                        ?.also {
                            event.player.health = it
                            event.player.sendMessageWithPrefix("生命值已更新")
                        }
                        ?: run {
                            event.player.sendMessageWithPrefix("无效的生命值")
                        }
                }
                else -> event.player.sendMessageCommandUsage("/health <生命值>")
            }
        }
    }
}