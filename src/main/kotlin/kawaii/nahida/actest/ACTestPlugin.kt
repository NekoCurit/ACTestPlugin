package kawaii.nahida.actest

import kawaii.nahida.actest.ac.ACToggleManager
import kawaii.nahida.actest.handle.message.MessageManager
import kawaii.nahida.actest.handle.statistics.StatisticsManager
import kawaii.nahida.actest.module.ModuleManager
import org.bukkit.plugin.java.JavaPlugin


class ACTestPlugin : JavaPlugin(){
    init {
        ACTest.instance = this
    }

    override fun onEnable() {
        ACTest.moduleManager = ModuleManager()
        ACTest.statisticsManager = StatisticsManager()
        ACTest.messageManager = MessageManager()
        ACTest.acToggleManager = ACToggleManager()

        ACTest.moduleManager.registerModules()
        ACTest.moduleManager.callOnEnable()

        ACTest.statisticsManager.onEnable()

        ACTest.acToggleManager.registerACs()

        ACTest.logger.info("所有初始化工作已全部完成.")
    }

    override fun onDisable() {
        ACTest.statisticsManager.onDisable()
        ACTest.moduleManager.callOnDisable()
        ACTest.logger.info("所有卸载工作已全部完成.")
    }

}
