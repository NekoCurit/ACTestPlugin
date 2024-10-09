package kawaii.nahida.actest.module.modules

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.module.Module
import kawaii.nahida.actest.utils.bukkit.PlayerExtensions.sendActionBar
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

@Suppress("Unused")
class PositionTeleportInfoModule : Module("PositionTeleportInfo") {
    val lastPositionPacketTime: HashMap<Player, PositionData> = hashMapOf()

    data class PositionData(
        val time: Long,
        val x: Double,
        val y: Double,
        val z: Double
    )

    private val listener = PositionTeleportInfoModuleListener(this)
    private val task = object : BukkitRunnable() {
        override fun run() {
            val currentTime = System.currentTimeMillis()

            lastPositionPacketTime.forEach { (player, data) ->
                when (val time = currentTime - data.time) {
                    in 0..5500 -> player.sendActionBar(
                        arrayOf(
                            "${ChatColor.LIGHT_PURPLE}接收到 Server Position 包",
                            "${ChatColor.WHITE}|",
                            "${ChatColor.LIGHT_PURPLE}位置: ${data.x}, ${data.y}, ${data.z}",
                            "${ChatColor.WHITE}|",
                            "${ChatColor.LIGHT_PURPLE}时间差: ${String.format("%.2f", time / 1000.0)}s"
                        ).joinToString(" ")
                    )
                    in 5501..6000 -> player.sendActionBar("")
                }
            }
        }
    }

    override fun onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(listener)
        task.runTaskTimer(ACTest.instance, 2L, 2L)
    }
    override fun onDisable() {
        ProtocolLibrary.getProtocolManager().removePacketListener(listener)
        task.cancel()
    }

    class PositionTeleportInfoModuleListener(val module: PositionTeleportInfoModule) : PacketAdapter(
        ACTest.instance,
        ListenerPriority.NORMAL,
        PacketType.Play.Server.POSITION
    ) {
        override fun onPacketSending(event: PacketEvent) {
            module.lastPositionPacketTime[event.player] = PositionData(
                System.currentTimeMillis(),
                event.packet.doubles.read(0),
                event.packet.doubles.read(1),
                event.packet.doubles.read(2)
            )
        }
    }
}