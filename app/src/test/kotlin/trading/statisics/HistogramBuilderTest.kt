package trading.statisics

import com.google.common.truth.Truth
import org.junit.Test
import trading.statisics.printer.ConsoleHistogramPrinter
import java.math.BigDecimal
import java.math.MathContext
import kotlin.random.Random

class HistogramBuilderTest {

    val mc = MathContext(2)

    @Test
    fun `build histogram`() {
        Truth.assertThat(HistogramBuilder(listOf(1, 1, 1, 5, 5, 6, 7, 8).toBigDecimal()).buildHistogram())
            .isEqualTo(
                listOf(
                    HistogramBar(BigDecimal(1), BigDecimal(5), count = 3),
                    HistogramBar(BigDecimal(5), BigDecimal(9), count = 5)
                )
            )

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
        val hist = HistogramBuilder(listOf(1, 1, 1, 5, 5, 6, 7, 8).toBigDecimal()).buildHistogram()
        Truth.assertThat(ConsoleHistogramPrinter().print(hist))
            .isEqualTo(
                "1 ======\n" +
                    "5 =========="
            )
    }

    @Test
    fun `random test`() {

        val data = (0..10000).map { BigDecimal(Random.nextDouble(), mc) }
        val hist = HistogramBuilder(data).buildHistogram()
        println(ConsoleHistogramPrinter().print(hist))
    }

    private fun List<Int>.toBigDecimal() = map { BigDecimal(it) }
}
