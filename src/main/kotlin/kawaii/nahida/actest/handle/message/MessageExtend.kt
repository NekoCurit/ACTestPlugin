package kawaii.nahida.actest.handle.message

import kawaii.nahida.actest.ACTest
import org.bukkit.entity.Player

object MessageExtend {
    fun Player.sendMessageWithPrefix(message: String) = this.sendMessage(ACTest.messageManager.prefix + message)
    fun Player.sendMessageCommandUsage(message: String) = this.sendMessageWithPrefix("用法: $message")
    fun Player.sendCommandResult(t: Throwable, c: String) = t.message?.also { message ->
        this.sendMessageWithPrefix(message)
    } ?: this.sendMessageCommandUsage(c)
}