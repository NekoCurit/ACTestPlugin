package kawaii.nahida.actest.handle.message

import kawaii.nahida.actest.ACTest
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

object MessageExtend {
    fun Player.sendMessageWithPrefix(message: String) = this.sendMessage(Component.text(ACTest.messageManager.prefix + message))
    fun Player.sendMessageCommandUsage(message: String) = this.sendMessageWithPrefix("用法: $message")
}