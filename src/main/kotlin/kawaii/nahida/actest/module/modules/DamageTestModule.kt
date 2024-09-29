package kawaii.nahida.actest.module.modules
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.PlayerExtensions.sendActionBar
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent

@Suppress("Unused")
class DamageTestModule : Module("DamageTest") {

    @EventHandler
    fun onPlayerAttack(event: EntityDamageByEntityEvent) {
        if (event.isCancelled) return
        
        (event.damager as? Player)
            ?.sendActionBar(
                arrayOf(
                    "预测伤害: ${String.format("%.1f", event.damage)}",
                    "实际伤害: ${String.format("%.1f", event.finalDamage)}",
                    "攻击疾跑: ${if((event.damager as? Player)?.isSprinting == true) "是" else "否"}",
                    "攻击潜行: ${if((event.damager as? Player)?.isSneaking == true) "是" else "否"}"
                ).joinToString(" ")
            )
    }
}