package kawaii.nahida.actest.handle.statistics

import kawaii.nahida.actest.utils.statistics.DelayStatisticsData
import kawaii.nahida.actest.utils.statistics.SpeedStatisticsData

data class StatisticsData(
    var ppsc: DelayStatisticsData = DelayStatisticsData(),
    var ppss: DelayStatisticsData = DelayStatisticsData(),
    var cps: DelayStatisticsData = DelayStatisticsData(),
    var bps: SpeedStatisticsData = SpeedStatisticsData()
)
