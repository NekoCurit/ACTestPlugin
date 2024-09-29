package kawaii.nahida.actest.module.modules
import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.PlayerExtensions.sendActionBar
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent

@Suppress("Unused")
class DamageTestModule : Module("DamageTest") {

    @EventHandler
    fun onPlayerAttack(event: EntityDamageByEntityEvent) {
        (event.damager as? Player)
            ?.let { player ->
                player.sendActionBar(
                    arrayOf(
                        "预测伤害: ${String.format("%.1f", event.damage)}",
                        "实际伤害: ${String.format("%.1f", event.finalDamage)}",
                        "预测距离: ${String.format("%.2f", event.damager.location.distance(event.entity.location))}",
                        "攻击疾跑: ${if(player.isSprinting) "是" else "否"}",
                        "攻击潜行: ${if(player.isSneaking) "是" else "否"}",
                        "当前移速: ${ACTest.statisticsManager.getPlayerData(player).bps.getFormated(1, 2)}"
                    ).joinToString("  ")
                )
            }
    }
}