package kawaii.nahida.actest.module

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.utils.ClassUtils
import java.util.*

class ModuleManager {

    private val modules = TreeSet<Module> { module1, module2 -> module1.name.compareTo(module2.name) }
    private val moduleClassMap = hashMapOf<Class<*>, Module>()

    /**
     * 注册全部模块
     */
    fun registerModules() {
        ClassUtils.resolvePackage("${this.javaClass.`package`.name}.modules", Module::class.java)
            .sortedBy { it.name }
            .forEach(this::registerModule)

    }

    /**
     * 注册模块
     *
     * @param moduleClass 模块 Class
     */
    fun registerModule(moduleClass: Class<out Module>) {
        try {
            registerModule(moduleClass.getDeclaredConstructor().newInstance())
        } catch (e: IllegalAccessException) {
            registerModule(ClassUtils.getObjectInstance(moduleClass) as Module)
        } catch (e: Throwable) {
            ACTest.logger.warning("[模块管理] 模块加载失败(javaClass=${e.javaClass.name}, e=${e.message})")
        }
    }

    /**
     * 注册模块
     *
     * @param module 模块实例
     */
    fun registerModule(module: Module) {
        modules += module
        moduleClassMap[module.javaClass] = module

        ACTest.instance.server.pluginManager.registerEvents(module, ACTest.instance)
        ACTest.logger.info("[模块管理] 已加载模块(名称=${module.name})")
    }

    /**
     * 通知全部模块插件已加载
     */
    fun callOnEnable() = modules.forEach { it.onEnable() }
    /**
     * 通知全部模块插件插件即将卸载
     */
    fun callOnDisable() = modules.forEach { it.onDisable() }

}