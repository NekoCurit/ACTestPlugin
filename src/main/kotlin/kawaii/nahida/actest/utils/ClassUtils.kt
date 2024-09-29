package kawaii.nahida.actest.utils

import org.reflections.Reflections


object ClassUtils {

    private val cachedClasses = mutableMapOf<String, Boolean>()

    /**
     * Allows you to check for existing classes with the [className]
     */
    @JvmStatic
    fun hasClass(className: String): Boolean {
        return if (cachedClasses.containsKey(className))
            cachedClasses[className]!!
        else try {
            Class.forName(className)
            cachedClasses[className] = true

            true
        } catch (e: ClassNotFoundException) {
            cachedClasses[className] = false

            false
        }
    }

    fun getObjectInstance(clazz: Class<*>): Any {
        clazz.declaredFields.forEach {
            if (it.name.equals("INSTANCE")) {
                return it.get(null)
            }
        }
        throw IllegalAccessException("This class not a kotlin object")
    }


    fun <T : Any> resolvePackage(packagePath: String, klass: Class<T>): MutableList<Class<out T>> {
        val reflections = Reflections(packagePath)
        val subTypes = reflections.getSubTypesOf(klass)
        return subTypes.toMutableList()
    }

}