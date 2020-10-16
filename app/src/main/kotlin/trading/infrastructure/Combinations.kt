package trading.infrastructure

import trading.PowerSetProcessor

fun <T> List<T>.combinations(n: Int): List<List<T>> { // todo stream
    if (this.isEmpty()) return emptyList()
    val sets = mutableListOf<List<T>>()
    var counter = 0
    CombinationsProcessor().process(this, n) { set ->
        println(set)
        counter++
    }
    println(counter)
    return sets
}

class CombinationsProcessor {

    fun <T> process(
        set: List<T>,
        n: Int,
        action: (set: List<T>) -> Unit
    ) {
        PowerSetProcessor.processPowerSet(set.toSet()) { ipSet ->
            if (ipSet.size == n) {
                action(ipSet.toList())
            }
            false
        }
    }
}

class SubsetProcessor {

    fun <T> process(
        list: List<T>,
        maxN: Int,
        k: Int,
        reduce: (first: List<T>, other: List<T>) -> List<T>
    ): List<T> {

        if (list.size <= maxN) {
            return list
        } else {
            val split = list.size / 2
            val left: List<T> = process(list.subList(0, split), maxN, k, reduce)
            val right: List<T> = process(list.subList(split, list.size), maxN, k, reduce)
            return reduce(left, right)
        }
    }
}

/*fun <T> List<T>.combinations(
    k: Int,
    action: (set: List<T>) -> Unit
) {
    if (this.isEmpty()) return
    var counter = 0
    val t = System.currentTimeMillis()

    printSubsets(size, k) { indices ->
        // println(indices)
        counter++
        if (counter % 1000 == 0) {
            val dt = (System.currentTimeMillis() - t) / 1000
            logger.info("${dt} sec, $counter, wait for ${((1200000.0 / counter) * dt).toLong() / 60} min")
        }
        action(indices.map { get(it) })
    }
    println(counter)
}*/

fun printSubsets(
    n: Int,
    k: Int,
    action: (indices: List<Int>) -> Unit
) {

    for (i in 0 until (1 shl n)) {
        // print("{ ")
        var m = 1 // m is used to check set bit in binary representation.
        val indicesList = mutableListOf<Int>()
        for (j in 0 until n) {
            if (i and m > 0L) {
                indicesList.add(j)
                // print(set[j].toString() + " ")
            }
            m = m shl 1
        }
        if (indicesList.size == k) {
            action.invoke(indicesList)
        }
        // println("}")
    }
}