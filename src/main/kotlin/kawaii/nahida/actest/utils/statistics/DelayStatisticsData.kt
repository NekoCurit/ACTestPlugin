package kawaii.nahida.actest.utils.statistics

import java.util.*
import kotlin.collections.ArrayList

class DelayStatisticsData() {
    private var delays: MutableList<Long> = Collections.synchronizedList(ArrayList<Long>())

    fun add() = delays.add(System.currentTimeMillis())
    fun get(millis: Long) = (System.currentTimeMillis() - millis).let { time ->
        delays.count { it >= time }
    }
    fun reset() = delays.clear()
    fun reset(millis: Long) = (System.currentTimeMillis() - millis).let { time ->
        delays.removeIf { it < time }
    }
}
