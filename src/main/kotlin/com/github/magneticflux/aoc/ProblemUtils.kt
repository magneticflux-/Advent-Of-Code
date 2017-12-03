package com.github.magneticflux.aoc

import java.io.InputStream
import kotlin.reflect.KMutableProperty0

fun Any.input(): InputStream {
    return this::class.java.getResourceAsStream("input.txt")
}

fun Any.example(index: Int? = null): InputStream {
    return this::class.java.getResourceAsStream("example${index?.toString() ?: ""}.txt")
}

/**
 * Implemented around midnight using my own algorithm. It was probably discovered a few hundred years ago, but I'm stubborn.
 *
 * @author Mitchell Skaggs
 */
fun <T> List<T>.combinations(k: Int): Sequence<List<T>> {
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

/**
 * Adapted from [A Simple, Efficient P(n,k) Algorithm](https://alistairisrael.wordpress.com/2009/09/22/simple-efficient-pnk-algorithm/)
 *
 * @author Mitchell Skaggs
 */
fun <T> List<T>.permutations(k: Int = this.size): Sequence<List<T>> {
    return when {
        k > size || k < 0 -> emptySequence()
        k == 0 -> sequenceOf(emptyList())
        else -> object : Iterator<List<T>> {
            val mainIndices = (0..this@permutations.lastIndex).toMutableList()
            var hasNext = true

            override fun hasNext(): Boolean {
                return hasNext
            }

            override fun next(): List<T> {
                val toReturn = mainIndices.take(k).map { this@permutations[it] }

                var edgeIndex = k - 1
                var nextLargestIndex = k
                // find smallest nextLargestIndex > k - 1 where mainIndices[nextLargestIndex] > mainIndices[k - 1]
                while (nextLargestIndex < this@permutations.size && mainIndices[edgeIndex] >= mainIndices[nextLargestIndex]) {
                    nextLargestIndex++
                }

                if (nextLargestIndex < this@permutations.size) {
                    mainIndices.swap(edgeIndex, nextLargestIndex)
                } else {
                    mainIndices.reverseRightOf(edgeIndex + 1)
                    // edgeIndex = (k - 1) - 1
                    edgeIndex--
                    while (edgeIndex >= 0 && mainIndices[edgeIndex] >= mainIndices[edgeIndex + 1]) {
                        edgeIndex--
                    }
                    if (edgeIndex < 0) {
                        hasNext = false
                        return toReturn
                    }
                    // nextLargestIndex = n - 1
                    nextLargestIndex--
                    while (nextLargestIndex > edgeIndex && mainIndices[edgeIndex] >= mainIndices[nextLargestIndex]) {
                        nextLargestIndex--
                    }
                    mainIndices.swap(edgeIndex, nextLargestIndex)
                    mainIndices.reverseRightOf(edgeIndex + 1)
                }

                return toReturn
            }
        }.asSequence()
    }
}

private fun <E> MutableList<E>.reverseRightOf(fromIndex: Int) {
    this.subList(fromIndex, this.size).reverse()
}

private fun <E> MutableList<E>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
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