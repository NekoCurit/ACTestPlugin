package kawaii.nahida.actest.utils.bukkit

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.WrappedChatComponent
import kawaii.nahida.actest.utils.ColorUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PlayerExtensions {
    /**
     * 发送小标题消息
     * @param message 消息内容
     * @param color 颜色 默认随机颜色(便于区分两条一模一样的消息)
     */
    fun Player.sendActionBar(message: String, color: ChatColor = ColorUtils.COLORS.random()) {
        ProtocolLibrary.getProtocolManager()?.let { protocolManager ->
            val packet = protocolManager.createPacket(PacketType.Play.Server.CHAT)
            packet.chatComponents.write(0, WrappedChatComponent.fromLegacyText("${color}${message}"))
            packet.bytes.write(0, 2.toByte())
            protocolManager.sendServerPacket(player, packet)
        }
    }

}