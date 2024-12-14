package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandTrashModule : Module("CommandTrash") {
    companion object {
        val CONTAINER_TITLE = "${ChatColor.BLUE}垃圾桶 ${ChatColor.RED}(关闭后物品将无法找回)"
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("trash")) {
            event.isCancelled = true

            Bukkit.createInventory(null, InventoryType.CHEST, CONTAINER_TITLE)
                .also {
                    event.player.openInventory(it)
                }
        }
    }
}