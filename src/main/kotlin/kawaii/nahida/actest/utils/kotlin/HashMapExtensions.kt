package kawaii.nahida.actest.utils.kotlin

object HashMapExtensions {

    fun <K, V> HashMap<K, V>.removeIf(predicate: (Map.Entry<K, V>) -> Boolean) {
        val iterator = this.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (predicate(entry)) {
                iterator.remove()
            }
        }
    }

}