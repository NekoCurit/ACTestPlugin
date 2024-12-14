package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.StringExtensions.commandStartWith
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.inventory.ItemStack

@Suppress("Unused")
class CommandItemModule : Module("CommandItem") {
    companion object {
        val CONTAINER_TITLE = "${ChatColor.BLUE}获取物品"
        val ITEMS = arrayOf(
            ItemStack(Material.GLASS, 64), // 玻璃
            ItemStack(Material.LEAVES, 64), // 树叶
            ItemStack(Material.STONE, 64), // 石头
            ItemStack(Material.WOOL, 64, 0), // 羊毛 白色
            ItemStack(Material.WOOL, 64, 5), // 羊毛 淡绿色
            ItemStack(Material.WOOL, 64, 3), // 羊毛 淡蓝色
            ItemStack(Material.WOOL, 64, 14), // 羊毛 红色
            ItemStack(Material.WOOL, 64, 4), // 羊毛 黄色
            ItemStack(Material.WOOL, 64, 6), // 羊毛 粉色

            ItemStack(Material.DIAMOND_SWORD),
            ItemStack(Material.DIAMOND_PICKAXE),
            ItemStack(Material.DIAMOND_AXE),
            ItemStack(Material.DIAMOND_SPADE),
            ItemStack(Material.DIAMOND_HOE),
            ItemStack(Material.DIAMOND_HELMET),
            ItemStack(Material.DIAMOND_CHESTPLATE),
            ItemStack(Material.DIAMOND_LEGGINGS),
            ItemStack(Material.DIAMOND_BOOTS),

            ItemStack(Material.BOW), // 弓
            ItemStack(Material.ARROW), // 箭
            ItemStack(Material.FISHING_ROD), // 鱼竿
            ItemStack(Material.SHEARS), // 剪刀
            ItemStack(Material.STICK).apply { // 木棍 - 击退2
                itemMeta = itemMeta?.apply {
                    addEnchant(Enchantment.KNOCKBACK, 2, true)
                }
            },
            ItemStack(Material.GOLDEN_APPLE, 64, 0), // 金苹果 - 普通
            ItemStack(Material.GOLDEN_APPLE, 64, 1), // 金苹果 - 附魔
            ItemStack(Material.WATER_BUCKET), // 水桶
            ItemStack(Material.ENDER_PEARL, 64) // 末影珍珠
        )
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.commandStartWith("i", "item")) {
            event.isCancelled = true

            // 创建一个新的虚拟容器
            Bukkit.createInventory(null, InventoryType.CHEST, CONTAINER_TITLE)
                .also { container ->
                    ITEMS.forEach {
                        container.addItem(it)
                    }
                }
                .also {
                    event.player.openInventory(it)
                }
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        // 检查点击的容器标题 (Vulcan提权同款 (不是
        if (event.view.title == CONTAINER_TITLE) {
            if (event.clickedInventory != event.whoClicked.inventory) {
                event.whoClicked.inventory.addItem(event.currentItem ?: return) // 给予玩家物品
            }
            event.isCancelled = true // 取消事件
        }
    }
}