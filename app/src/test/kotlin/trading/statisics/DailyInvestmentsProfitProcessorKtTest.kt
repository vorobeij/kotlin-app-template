package trading.statisics

import com.google.common.truth.Truth
import org.junit.Test
import trading.infrastructure.firstAfter

class DailyInvestmentsProfitProcessorKtTest {

    @Test
    fun `binary search`() {
        Truth.assertThat(listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8).firstAfter { it >= 5 }).isEqualTo(5)
    }
}