package kawaii.nahida.actest.ac.acs

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.ac.ACToggle
import kawaii.nahida.actest.utils.bukkit.ServerExtensions.executeCommandAsConsole
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class ACToggleGrimAC : ACToggle(
    name = "GrimAC",
    displayName = "${ChatColor.AQUA}GrimAC"
) {
    companion object {
        const val PERMISSION_GRIM_AC_EXEMPT = "grim.exempt"
    }
    override fun onEnable(player: Player) {
        ACTest.instance.server.executeCommandAsConsole("lp user ${player.name} permission set $PERMISSION_GRIM_AC_EXEMPT false")
    }

    override fun onDisable(player: Player) {
        ACTest.instance.server.executeCommandAsConsole("lp user ${player.name} permission set $PERMISSION_GRIM_AC_EXEMPT true")
    }
}