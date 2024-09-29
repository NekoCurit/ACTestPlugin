package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandEnderChestModule : Module("CommandEnderChest") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (
            arrayOf(
                event.message.startsWith("/ec", true),
                event.message.startsWith("/enderChest", true)
            ).any { it }
            ) {
            event.isCancelled = true
            event.player.openInventory(event.player.enderChest)
        }
    }
}