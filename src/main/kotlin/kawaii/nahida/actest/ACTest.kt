package kawaii.nahida.actest

import kawaii.nahida.actest.handle.message.MessageManager
import kawaii.nahida.actest.handle.statistics.StatisticsManager
import kawaii.nahida.actest.module.ModuleManager
import java.util.logging.Logger

object ACTest {
    lateinit var instance: ACTestPlugin

    val logger: Logger
        get() = instance.server.logger

    lateinit var moduleManager: ModuleManager
    lateinit var statisticsManager: StatisticsManager
    lateinit var messageManager: MessageManager

}