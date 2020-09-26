package trading

import com.google.common.truth.Truth
import org.junit.Test
import java.time.OffsetDateTime

class ExtKtTest {

    @Test
    fun `splitTimeIntervalByYears`() {
        Truth.assertThat(
            splitTimeIntervalByYears(
                OffsetDateTime.parse("2019-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        ).isEqualTo(
            listOf(
                OffsetDateTime.parse("2019-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        )

        Truth.assertThat(
            splitTimeIntervalByYears(
                OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        ).isEqualTo(
            listOf(
                OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2019-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        )

        Truth.assertThat(
            splitTimeIntervalByYears(
                OffsetDateTime.parse("2018-12-06T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        ).isEqualTo(
            listOf(
                OffsetDateTime.parse("2018-12-06T10:15:30+01:00"),
                OffsetDateTime.parse("2019-12-03T10:15:30+01:00"),
                OffsetDateTime.parse("2020-12-03T10:15:30+01:00")
            )
        )
    }
}