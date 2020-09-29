package trading.statisics

import com.google.common.truth.Truth
import org.junit.Test
import java.math.BigDecimal
import java.math.MathContext

class HistogramBuilderTest {

    @Test
    fun `build histogram`() {
        Truth.assertThat(HistogramBuilder(listOf(1, 1, 1, 5, 5, 6, 7, 8).toBigDecimal()).buildHistogram())
            .isEqualTo(
                listOf(
                    HistogramBar(BigDecimal(1), BigDecimal(5), count = 3),
                    HistogramBar(BigDecimal(5), BigDecimal(9), count = 5)
                )
            )

        val mc = MathContext(2)
        Truth.assertThat(
            HistogramBuilder(
                listOf(
                    BigDecimal(1.1, mc),
                    BigDecimal(1.2, mc),
                    BigDecimal(1.3, mc),
                    BigDecimal(5.6, mc),
                    BigDecimal(5.1, mc),
                    BigDecimal(6.4, mc),
                    BigDecimal(7.2, mc),
                    BigDecimal(8.1, mc)
                )
            ).buildHistogram()
        )
            .isEqualTo(
                listOf(
                    HistogramBar(BigDecimal(1.1, mc), BigDecimal(4.6, mc), 3),
                    HistogramBar(BigDecimal(4.6, mc), BigDecimal(8.1, mc), 4)
                )
            )
    }

    @Test
    fun `print`() {
        Truth.assertThat(HistogramBuilder(listOf(1, 1, 1, 5, 5, 6, 7, 8).toBigDecimal()).printConsole())
            .isEqualTo(
                "1 ======\n" +
                    "5 =========="
            )
    }

    private fun List<Int>.toBigDecimal() = map { BigDecimal(it) }
}