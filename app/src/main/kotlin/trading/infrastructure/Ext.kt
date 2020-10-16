package trading.infrastructure

import java.time.OffsetDateTime

fun splitTimeIntervalByYears(
    from: OffsetDateTime,
    to: OffsetDateTime
): List<OffsetDateTime> {
    val intervals = mutableListOf<OffsetDateTime>()
    intervals.add(to)
    while (intervals.last().minusYears(1).isAfter(from)) {
        intervals.add(intervals.last().minusYears(1))
    }
    intervals.add(from)
    return intervals.reversed()
}

fun <T> List<T>.firstAfter(
    start: Int = 0,
    end: Int = this.size - 1,
    isAfterOrEqual: (it: T) -> Boolean
): Int {
    var low = start
    var high = end

    while (low <= high) {
        val mid = (low + high).ushr(1) // safe from overflows
        val midVal = get(mid)
        val isAfterOrEqualX = isAfterOrEqual(midVal)

        if (isAfterOrEqualX) {
            high = mid
        } else {
            low = mid
        }

        if (high == low || high - 1 == low) return high
    }
    return -1  // key not found
}
