package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.max
import kotlin.math.min

@Suppress("Unused")
class CleanBlockModule : Module("CleanBlock") {
    companion object {
        const val DEFAULT_CLEAN_TIME = 600

        const val DEFAULT_WORLD_NAME = "world"

        const val DEFAULT_POSITION1_X = 291
        const val DEFAULT_POSITION1_Y = 62
        const val DEFAULT_POSITION1_Z = 206

        const val DEFAULT_POSITION2_X = 236
        const val DEFAULT_POSITION2_Y = 255
        const val DEFAULT_POSITION2_Z = 166
    }

    private var cleanTime = 0

    private val task = object : BukkitRunnable() {
        override fun run() {
            cleanTime--

            when (cleanTime) {
                30 -> sendMessage("所有搭路区域将在30秒后清除")
                15 -> sendMessage("所有搭路区域将在15秒后清除")
                0 -> reload()
            }
        }
    }

    fun reload() {
        replaceNonAirBlocks(
            Bukkit.getWorld(DEFAULT_WORLD_NAME) ?: return,
            DEFAULT_POSITION1_X, DEFAULT_POSITION1_Y, DEFAULT_POSITION1_Z,
            DEFAULT_POSITION2_X, DEFAULT_POSITION2_Y, DEFAULT_POSITION2_Z
        )
        sendMessage("所有搭路区域已清除完毕")

        cleanTime = DEFAULT_CLEAN_TIME
    }

    override fun onEnable() {
        cleanTime = DEFAULT_CLEAN_TIME
        task.runTaskTimer(ACTest.instance, 20L, 20L)
    }
    override fun onDisable() {
        task.cancel()
    }

    fun sendMessage(message: String) = ACTest.instance.server.onlinePlayers.forEach { player ->
        player.sendMessageWithPrefix(message)
    }

    private fun replaceNonAirBlocks(world: World, x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
        for (x in min(x1, x2)..max(x1, x2)) {
            for (y in min(y1, y2)..max(y1, y2)) {
                for (z in min(z1, z2)..max(z1, z2)) {
                    val block = world.getBlockAt(x, y, z)
                    if (block.type != Material.AIR) {
                        block.type = Material.AIR // 替换为空气
                    }
                }
            }
        }
    }

}