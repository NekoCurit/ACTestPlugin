package kawaii.nahida.actest.handle.statistics

import com.comphenix.protocol.ProtocolLibrary
import kawaii.nahida.actest.handle.statistics.listener.StatisticsListenerBukkitUpdate
import kawaii.nahida.actest.handle.statistics.listener.StatisticsListenerProtocolLib
import org.bukkit.entity.Player

class StatisticsManager {
    /**
     * 所有的玩家统计数据
     */
    var data: HashMap<Player, StatisticsData> = hashMapOf()
    /**
     * 获取玩家统计数据
     * 如果不存在会自动创建
     *
     * @param player 玩家实例
     */
    fun getPlayerData(player: Player): StatisticsData = data[player] ?: let {
        StatisticsData().also {
            data[player] = it
        }
    }

    private var cleanManager = StatisticsClean(this)

    private val listenerProtocolLib = StatisticsListenerProtocolLib(this)
    private val listenerBukkitUpdate = StatisticsListenerBukkitUpdate(this)

    fun onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(listenerProtocolLib)
        listenerBukkitUpdate.onEnable()
        cleanManager.onEnable()
    }
    fun onDisable() {
        ProtocolLibrary.getProtocolManager().removePacketListener(listenerProtocolLib)
        listenerBukkitUpdate.onDisable()
        cleanManager.onDisable()
    }

}