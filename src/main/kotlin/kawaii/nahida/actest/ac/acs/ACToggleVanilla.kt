package kawaii.nahida.actest.ac.acs

import kawaii.nahida.actest.ac.ACToggle
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class ACToggleVanilla : ACToggle(
    name = "Vanilla",
    displayName = "${ChatColor.GREEN}Vanilla",
    isSingleActive = true
) {

    override fun onEnable(player: Player) { }

    override fun onDisable(player: Player) { }
}