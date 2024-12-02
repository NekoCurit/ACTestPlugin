package kawaii.nahida.actest.module.modules

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.StringUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective

@Suppress("Unused")
class ScoreboardModule : Module("Scoreboard") {
    private val tasks = mutableMapOf<Player, BukkitTask>()
    private val objectives = mutableMapOf<Player, Pair<Objective, HashMap<Int, String>>>()

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val scoreboard = Bukkit.getScoreboardManager().newScoreboard

        player.scoreboard = scoreboard
        val objective = scoreboard.registerNewObjective("Scoreboard", "dummy").apply {
            displaySlot = DisplaySlot.SIDEBAR
            displayName = "猫猫的测试服务器"
        }

        objectives[player] = Pair(objective, hashMapOf())

        val task = object : BukkitRunnable() {
            override fun run() {
                onUpdateScoreboard(player)
            }
        }.runTaskTimer(ACTest.instance, 2L, 2L)

        tasks[player] = task
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player

        tasks[player]?.cancel()
        tasks.remove(player)
    }

    private fun onUpdateScoreboard(player: Player) {
        Bukkit.getScoreboardManager().let {
            val objective = objectives[player] ?: return

            // 更新计分板的分数
            try {
                arrayOf(
                    "${ChatColor.WHITE}${ChatColor.WHITE}",
                    "${ChatColor.WHITE}Health: ${ChatColor.GREEN}${String.format("%.2f", player.health)}/${player.maxHealth}",
                    "${ChatColor.WHITE}HurtTime: ${ChatColor.GREEN}${player.noDamageTicks}/${player.maximumNoDamageTicks}",
                    "${ChatColor.WHITE}CPS: ${ChatColor.GREEN}${ACTest.statisticsManager.getPlayerData(player).cps.get(1000)}",
                    "${ChatColor.WHITE}BPS: ${ChatColor.GREEN}${ACTest.statisticsManager.getPlayerData(player).bps.getFormated(1, 2)}",
                    "${ChatColor.WHITE}PPS: ${ChatColor.GREEN}${ACTest.statisticsManager.getPlayerData(player).ppsc.get(1000)}",
                    "${ChatColor.WHITE}",
                    "${ChatColor.WHITE}在地面: ${StringUtils.yesOrNo(player.isOnGround)}",
                    "${ChatColor.WHITE}潜行中: ${StringUtils.yesOrNo(player.isSneaking)}",
                    "${ChatColor.WHITE}疾跑中: ${StringUtils.yesOrNo(player.isSprinting)}",
                    "${ChatColor.WHITE}格挡中: ${StringUtils.yesOrNo(player.isBlocking)}",
                    "${ChatColor.WHITE}能飞行: ${StringUtils.yesOrNo(player.allowFlight)}",
                    "${ChatColor.WHITE}飞行中: ${StringUtils.yesOrNo(player.isFlying)}",
                    "${ChatColor.YELLOW}@NekoCurit"
                    )
                    .reversed()
                    .also {
                        // 主线程执行
                        // 避免计分板频闪
                        Bukkit.getScheduler().runTask(ACTest.instance, Runnable {
                            it.forEachIndexed { index, message ->
                                // 仅更新不同消息
                                // 好处很多 比如说减少发包频率
                                if (objective.second[index] != message) {
                                    // 在这里 分数 相当于 排序/索引
                                    // 添加新的值
                                    objective.first.getScore(message).score = index
                                    // 清除相同分数的值
                                    objective.first.scoreboard?.entries?.forEach { entry ->
                                        if (
                                            objective.first.getScore(entry).score == index &&
                                            objective.first.getScore(entry).entry != message
                                        ) {
                                            objective.first.scoreboard?.resetScores(entry)
                                        }
                                    }
                                }
                            }
                        })
                    }

            } catch (e: Exception) {
                ACTest.logger.warning("[计分板显示] 发生异常(e=${e.javaClass.simpleName}: ${e.message})")
            }
        }
    }
}