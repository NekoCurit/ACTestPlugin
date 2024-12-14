package kawaii.nahida.actest.ac

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.utils.ClassUtils
import java.util.*

class ACToggleManager {

    @JvmField
    val acToggles = TreeSet<ACToggle> { ac1, ac2 -> ac1.name.compareTo(ac2.name) }

    /**
     * 注册全部反作弊切换器
     */
    fun registerACs() {
        ClassUtils.resolvePackage("${this.javaClass.`package`.name}.acs", ACToggle::class.java)
            .sortedBy { it.name }
            .forEach(this::registerModule)

    }

    /**
     * 注册反作弊切换器
     *
     * @param clazz 反作弊切换器 Class
     */
    fun registerModule(clazz: Class<out ACToggle>) {
        try {
            registerModule(clazz.getDeclaredConstructor().newInstance())
        } catch (e: IllegalAccessException) {
            // kotlin object
            registerModule(ClassUtils.getObjectInstance(clazz) as ACToggle)
        } catch (e: Throwable) {
            ACTest.logger.warning("[反作弊切换器管理] 反作弊切换器加载失败(javaClass=${e.javaClass.name}, e=${e.message})")
        }
    }

    /**
     * 注册反作弊切换器
     *
     * @param acToggle 反作弊切换器
     */
    fun registerModule(acToggle: ACToggle) {
        acToggles += acToggle

        ACTest.instance.server.pluginManager.registerEvents(acToggle, ACTest.instance)
        ACTest.logger.info("[反作弊切换器管理] 已加载切换器(名称=${acToggle.name})")
    }

}