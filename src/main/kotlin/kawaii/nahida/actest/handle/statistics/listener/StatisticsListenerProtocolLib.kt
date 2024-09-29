package kawaii.nahida.actest.handle.statistics.listener

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.handle.statistics.StatisticsManager

class StatisticsListenerProtocolLib(private val manager: StatisticsManager) : PacketAdapter(
    ACTest.instance,
    ListenerPriority.NORMAL,
    PacketType.values()
) {

    override fun onPacketSending(event: PacketEvent) {
        manager.getPlayerData(event.player).also { data ->
            data.ppss.add()
        }
    }

    override fun onPacketReceiving(event: PacketEvent) {
        manager.getPlayerData(event.player).also { data ->
            data.ppsc.add()

            when(event.packet.type) {
                PacketType.Play.Client.ARM_ANIMATION -> data.cps.add()
            }
        }
    }
}