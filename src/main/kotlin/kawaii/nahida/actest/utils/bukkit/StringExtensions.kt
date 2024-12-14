package kawaii.nahida.actest.utils.bukkit

object StringExtensions {
    fun String.commandStartWith(vararg commands: String): Boolean = commands.any {
        this.startsWith("/$it ", ignoreCase = true) || this.equals("/$it", ignoreCase = true)
    }
}