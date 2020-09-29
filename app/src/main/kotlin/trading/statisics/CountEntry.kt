package trading.statisics

data class CountEntry<T>(
    val value: T,
    val count: Int
)

fun <T> Iterable<T>.toCountMap(): List<CountEntry<T>> {
    val map = mutableMapOf<T, Int>()
    this.forEach {
        map[it] = (map[it] ?: 0) + 1
    }
    return map.entries.map { CountEntry(it.key, it.value) }
}
