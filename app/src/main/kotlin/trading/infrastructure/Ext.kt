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
