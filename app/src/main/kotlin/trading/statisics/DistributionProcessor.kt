package trading.statisics

interface DistributionProcessor<T : Comparable<T>> {

    fun values(): List<T>
}