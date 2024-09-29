package kawaii.nahida.actest.module

import org.bukkit.event.Listener

open class Module(
    val name: String
) : Listener {
    open fun onEnable() {}
    open fun onDisable() {}
}