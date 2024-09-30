package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.module.Module
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
        val CONTAINER_GIVE_COMMAND = "${ChatColor.BLUE}获取物品"
        val ITEMS = arrayOf(
            ItemStack(Material.GLASS, 64), // 玻璃
            ItemStack(Material.OAK_LEAVES, 64), // 树叶
            ItemStack(Material.STONE, 64), // 石头
            ItemStack(Material.WHITE_WOOL, 64), // 羊毛 白色
            ItemStack(Material.LIME_WOOL, 64), // 羊毛 淡绿色
            ItemStack(Material.LIGHT_BLUE_WOOL, 64), // 羊毛 淡蓝色
            ItemStack(Material.RED_WOOL, 64), // 羊毛 红色
            ItemStack(Material.YELLOW_WOOL, 64), // 羊毛 黄色
            ItemStack(Material.PINK_WOOL, 64), // 羊毛 粉色

            ItemStack(Material.DIAMOND_SWORD),
            ItemStack(Material.DIAMOND_PICKAXE),
            ItemStack(Material.DIAMOND_AXE),
            ItemStack(Material.DIAMOND_SHOVEL),
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
            ItemStack(Material.GOLDEN_APPLE, 64), // 金苹果 - 普通
            ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64), // 金苹果 - 附魔
            ItemStack(Material.WATER_BUCKET), // 水桶

        )
    }
    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        if (event.message.equals("/item", true)) {
            event.isCancelled = true


            // 创建一个新的虚拟容器
            Bukkit.createInventory(null, InventoryType.CHEST, CONTAINER_GIVE_COMMAND)
                .also { container ->
                    ITEMS.forEach {
                        container.addItem(it)
                    }
                }
                .also {
                    // 为玩家打开容器
                    player.openInventory(it)
                }
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        // 检查点击的容器标题 (Vulcan提权同款 (不是
        if (event.view.originalTitle == CONTAINER_GIVE_COMMAND) {
            if (event.clickedInventory != event.whoClicked.inventory) {
                event.whoClicked.inventory.addItem(event.currentItem ?: return) // 给予玩家物品
            }
            event.isCancelled = true // 取消事件
        }
    }
}