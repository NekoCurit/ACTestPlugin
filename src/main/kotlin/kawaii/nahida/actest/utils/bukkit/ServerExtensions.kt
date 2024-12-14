package kawaii.nahida.actest.utils.bukkit

import org.bukkit.Server

object ServerExtensions {
    fun Server.executeCommandAsConsole(command: String) = this.dispatchCommand(this.consoleSender, command)
}