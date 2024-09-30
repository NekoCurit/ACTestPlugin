package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageCommandUsage
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.inventory.ItemStack

@Suppress("Unused")
class CommandGiveModule : Module("CommandGive") {
    companion object {
        val BLOCK_MATERIALS = arrayOf(
            Material.AIR, // 空气
            Material.COMMAND_BLOCK, // 命令方块
            Material.COMMAND_BLOCK_MINECART, // 命令方块矿车
            Material.BEDROCK, // 基岩
            Material.BARRIER, // 屏障
            Material.LEGACY_MOB_SPAWNER, // 刷怪笼
            Material.PISTON_HEAD, // 活塞
            Material.LEGACY_PISTON_EXTENSION, // 活塞
            Material.LEGACY_PISTON_STICKY_BASE, // 活塞
            Material.LEGACY_PISTON_MOVING_PIECE, // 活塞
            Material.LEGACY_PISTON_BASE, // 活塞
            Material.TNT, // 炸药
            Material.TNT_MINECART, // TNT矿车
            Material.DISPENSER, // 发射器 (出图倒水
            Material.WRITTEN_BOOK, // 书与笔 (数据溢出ban人, 局域回档, 丢给管理员管理员打开强制执行命令
            Material.MAP, // 空地图 (刷存档占用
            Material.FILLED_MAP // 地图
        )
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/give", true)) {
            event.isCancelled = true
            val args = event.message.split(" ")

            val material = try {
                Material.getMaterial(args[1].uppercase())
            } catch (_: Throwable) {
                event.player.sendMessageCommandUsage("/give <物品名称> [数量]")
                return
            } ?: run {
                event.player.sendMessageWithPrefix("物品名不存在")
                return
            }
            val count = try {
                (args[2].toIntOrNull() ?: 1).coerceIn(1, 64)
            } catch (_: Throwable) {
                1
            }

            if (BLOCK_MATERIALS.contains(material)) {
                event.player.sendMessageWithPrefix("该物品处于黑名单")
                return
            }
            event.player.inventory.addItem(ItemStack(material, count))
            event.player.sendMessageWithPrefix("成功将 ${material.name.lowercase()} *${count} 给予 ${event.player.name}")
        }
    }
}