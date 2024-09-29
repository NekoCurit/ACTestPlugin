package kawaii.nahida.actest.utils.kotlin

object ListExtensions {
    fun <T> MutableList<T>.removeFirst(count: Int) {
        repeat(count.coerceAtMost(size)) {
            removeAt(0)
        }
    }

    fun <T> MutableList<T>.removeLast(count: Int) {
        repeat(count.coerceAtMost(size)) {
            removeAt(size - 1)
        }
    }
}