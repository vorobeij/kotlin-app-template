package trading.infrastructure

import com.google.common.truth.Truth
import org.junit.Test

class SubsetProcessorTest {

    @Test
    fun `process by max`() {
        val res = SubsetProcessor().process(
            (1..100).toList().shuffled(),
            10,
            5
        ) { first: List<Int>, other: List<Int> ->
            val l = mutableListOf<Int>()
            l.addAll(first)
            l.addAll(other)
            l.sortedByDescending { it }.take(5)
        }
        println("res = $res")
        Truth.assertThat(res.sorted()).isEqualTo((96..100).toList().sorted())
    }
}