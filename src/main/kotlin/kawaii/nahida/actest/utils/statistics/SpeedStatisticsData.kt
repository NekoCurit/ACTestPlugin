package kawaii.nahida.actest.utils.statistics

import kawaii.nahida.actest.utils.kotlin.ListExtensions.removeFirst
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class SpeedStatisticsData() {
    private var speeds: MutableList<Double> = Collections.synchronizedList(ArrayList())
    private var lastLocation: Location? = null

    fun add(player: Player) {
        lastLocation?.let {
            speeds.add(listOf(
                abs(player.location.x - it.x),
                abs(player.location.y - it.y),
                abs(player.location.z - it.z)
            ).sum())
        }

        lastLocation = player.location
    }
    fun get(count: Int) = speeds.takeLast(count).average()
    fun getFormated(count: Int, f: Int) = String.format("%.${f}f", get(count))

    fun reset() = speeds.clear()
    fun reset(count: Int) = speeds.removeFirst(count)
}
