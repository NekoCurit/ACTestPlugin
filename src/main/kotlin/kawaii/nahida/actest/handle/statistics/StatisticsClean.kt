package kawaii.nahida.actest.handle.statistics

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.utils.kotlin.HashMapExtensions.removeIf
import org.bukkit.scheduler.BukkitRunnable

class StatisticsClean(val manager: StatisticsManager) {
    /**
     * 自动执行清理工作
     * 清理掉不在线的玩家数据
     */
    private val task = object : BukkitRunnable() {
        override fun run() {
            manager.data.removeIf { (player,_) ->
                !ACTest.instance.server.onlinePlayers.contains(player)
            }
        }
    }

    fun onEnable() {
        // 20Tick = 1秒
        task.runTaskTimer(ACTest.instance, 20L, 20L)
    }
    fun onDisable() {
        task.cancel()
    }
}