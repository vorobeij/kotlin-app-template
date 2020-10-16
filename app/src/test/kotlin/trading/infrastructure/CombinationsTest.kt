package trading.infrastructure

import org.junit.Test
import kotlin.system.measureTimeMillis

class CombinationsTest {

    @Test
    fun `combinations`() {
        // Truth.assertThat(listOf<Int>().combinations(3)).isEqualTo(listOf<Int>())
        // Truth.assertThat(listOf(1, 2, 3).combinations(1)).containsAtLeastElementsIn(listOf(listOf(1), listOf(2), listOf(3)))
        // Truth.assertThat(listOf(1, 2, 3).combinations(2)).containsAtLeastElementsIn(listOf(listOf(1, 2), listOf(1, 3), listOf(2, 3)))

        val N = 30
        val k = 5
        val t = measureTimeMillis {
            // (1..N).toList().combinations(k) {}
        }
        println("time ($N) = $t ms")
    }
}