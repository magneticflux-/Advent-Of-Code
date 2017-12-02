package com.github.magneticflux.aoc

import java.io.InputStream
import kotlin.reflect.KMutableProperty0

fun Any.input(): InputStream {
    return this::class.java.getResourceAsStream("input.txt")
}

fun Any.example(index: Int? = null): InputStream {
    return this::class.java.getResourceAsStream("example${index?.toString() ?: ""}.txt")
}

fun <T> List<T>.combinations(k: Int = this.size): Sequence<List<T>> {
    return when {
        k > size || k < 0 -> emptySequence()
        k == 0 -> sequenceOf(listOf())
        else -> object : Iterator<List<T>> {
            val mainIndices = IntArray(k, { it })

            fun getMaxIndexValue(mainIndicesIndex: Int): Int {
                return this@combinations.size - (k - mainIndicesIndex)
            }

            override fun hasNext(): Boolean {
                return mainIndices.filterIndexed { index, i -> i > getMaxIndexValue(index) }.isEmpty()
            }

            override fun next(): List<T> {
                val toReturn = mainIndices.map { this@combinations[it] }

                mainIndices[mainIndices.lastIndex]++
                for (i in mainIndices.indices.drop(1).reversed()) {
                    if (mainIndices[i] > getMaxIndexValue(i)) {
                        mainIndices[i - 1]++

                        for (resetIndex in i..mainIndices.lastIndex)
                            mainIndices[resetIndex] = mainIndices[resetIndex - 1] + 1
                    }
                }

                return toReturn
            }
        }.asSequence()
    }
}

data class SortedPair<T : Comparable<T>>(var larger: T, var smaller: T) {
    constructor(items: Pair<T, T>) : this(items.first, items.second)

    init {
        if (larger < smaller)
            ::larger.swapWith(::smaller)
    }

}

fun <T : Comparable<T>> Pair<T, T>.toSortedPair(): SortedPair<T> {
    return SortedPair(this)
}

fun <T : Comparable<T>> Iterable<T>.toSortedPair(): SortedPair<T> {
    return this.asSequence().zipWithNext().first().toSortedPair()
}

private fun <R> KMutableProperty0<R>.swapWith(other: KMutableProperty0<R>) {
    val thisTemp = this.get()
    this.set(other.get())
    other.set(thisTemp)
}