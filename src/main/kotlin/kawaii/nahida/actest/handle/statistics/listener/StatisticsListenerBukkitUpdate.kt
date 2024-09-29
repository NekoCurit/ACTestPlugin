package kawaii.nahida.actest.handle.statistics.listener

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.handle.statistics.StatisticsManager
import org.bukkit.scheduler.BukkitRunnable

class StatisticsListenerBukkitUpdate(private val manager: StatisticsManager){

    private val task = object : BukkitRunnable() {
        override fun run() {
            manager.data.forEach { (player, data) ->
                if (ACTest.instance.server.onlinePlayers.contains(player)) {
                    data.bps.add(player)
                }
            }
        }
    }

    fun onEnable() {
        // 4Tick = 0.2秒
        task.runTaskTimer(ACTest.instance, 4L, 4L)
    }
    fun onDisable() {
        task.cancel()
    }
}