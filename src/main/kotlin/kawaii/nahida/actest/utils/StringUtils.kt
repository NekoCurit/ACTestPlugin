package kawaii.nahida.actest.utils

import org.bukkit.ChatColor

object StringUtils {
    private val YES = "${ChatColor.GREEN}✔"
    private val NO = "${ChatColor.RED}✖"

    fun yesOrNo(state: Boolean) = when (state) {
        true -> YES
        false -> NO
    }
}