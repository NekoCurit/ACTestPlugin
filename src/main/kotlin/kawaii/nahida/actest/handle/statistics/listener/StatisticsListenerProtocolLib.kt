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

    // 监听全部发包会导致服务端崩溃/断开连接等问题
    PacketType.Play.Client.TELEPORT_ACCEPT,
    PacketType.Play.Client.TILE_NBT_QUERY,
    PacketType.Play.Client.DIFFICULTY_CHANGE,
    PacketType.Play.Client.CHAT_ACK,
    PacketType.Play.Client.CHAT_COMMAND,
    PacketType.Play.Client.CHAT,
    PacketType.Play.Client.CHAT_SESSION_UPDATE,
    PacketType.Play.Client.CHUNK_BATCH_RECEIVED,
    PacketType.Play.Client.CLIENT_COMMAND,
    PacketType.Play.Client.SETTINGS,
    PacketType.Play.Client.TAB_COMPLETE,
    PacketType.Play.Client.CONFIGURATION_ACK,
    PacketType.Play.Client.ENCHANT_ITEM,
    PacketType.Play.Client.WINDOW_CLICK,
    PacketType.Play.Client.CLOSE_WINDOW,
    PacketType.Play.Client.CONTAINER_SLOT_STATE_CHANGED,
    PacketType.Play.Client.CUSTOM_PAYLOAD,
    PacketType.Play.Client.B_EDIT,
    PacketType.Play.Client.ENTITY_NBT_QUERY,
    PacketType.Play.Client.USE_ENTITY,
    PacketType.Play.Client.JIGSAW_GENERATE,
    PacketType.Play.Client.KEEP_ALIVE,
    PacketType.Play.Client.DIFFICULTY_LOCK,
    PacketType.Play.Client.POSITION,
    PacketType.Play.Client.POSITION_LOOK,
    PacketType.Play.Client.LOOK,
    PacketType.Play.Client.GROUND,
    PacketType.Play.Client.VEHICLE_MOVE,
    PacketType.Play.Client.BOAT_MOVE,
    PacketType.Play.Client.PICK_ITEM,
    PacketType.Play.Client.PING_REQUEST,
    PacketType.Play.Client.AUTO_RECIPE,
    PacketType.Play.Client.ABILITIES,
    PacketType.Play.Client.BLOCK_DIG,
    PacketType.Play.Client.ENTITY_ACTION,
    PacketType.Play.Client.STEER_VEHICLE,
    PacketType.Play.Client.PONG,
    PacketType.Play.Client.RECIPE_SETTINGS,
    PacketType.Play.Client.RECIPE_DISPLAYED,
    PacketType.Play.Client.ITEM_NAME,
    PacketType.Play.Client.RESOURCE_PACK_STATUS,
    PacketType.Play.Client.ADVANCEMENTS,
    PacketType.Play.Client.TR_SEL,
    PacketType.Play.Client.BEACON,
    PacketType.Play.Client.HELD_ITEM_SLOT,
    PacketType.Play.Client.SET_COMMAND_BLOCK,
    PacketType.Play.Client.SET_COMMAND_MINECART,
    PacketType.Play.Client.SET_CREATIVE_SLOT,
    PacketType.Play.Client.SET_JIGSAW,
    PacketType.Play.Client.STRUCT,
    PacketType.Play.Client.UPDATE_SIGN,
    PacketType.Play.Client.ARM_ANIMATION,
    PacketType.Play.Client.SPECTATE,
    PacketType.Play.Client.USE_ITEM,
    PacketType.Play.Client.BLOCK_PLACE,
    PacketType.Play.Client.TRANSACTION,
    PacketType.Play.Client.FLYING,
    PacketType.Play.Client.CHAT_PREVIEW
    ) {

    override fun onPacketReceiving(event: PacketEvent) {
        try {
            manager.getPlayerData(event.player).also { data ->
                data.ppsc.add()

                when(event.packet.type) {
                    PacketType.Play.Client.ARM_ANIMATION -> data.cps.add()
                }
            }
        } catch (_: Throwable) { }
    }
}