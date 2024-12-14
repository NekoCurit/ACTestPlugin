package kawaii.nahida.actest.ac

import org.bukkit.entity.Player
import org.bukkit.event.Listener

/**
 * 反作弊切换器
 *
 * @param name 反作弊名称
 * @param displayName 反作弊显示名称
 * @param isSingleActive 是否不可以和其它反作弊同时开启
 */
open class ACToggle(
    val name: String,
    val displayName: String,
    val isSingleActive: Boolean = false
) : Listener {
    open fun onEnable(player: Player) {}
    open fun onDisable(player: Player) {}
}