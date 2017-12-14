package com.github.magneticflux.aoc.year2017.day10

import com.github.magneticflux.aoc.collections.asCircular
import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val hash = denseHash(input.next().asIterable())
                .joinToString(separator = "") {
                    it.toString(16)
                            .padStart(2, '0')
                }

        println(hash)
    }

    fun denseHash(chars: Iterable<Char>): List<Int> {
        val lengths = chars.map { it.toInt() }.toList() + listOf(17, 31, 73, 47, 23)

        var currentPosition = 0
        var skipSize = 0
        val list = (0..255).toMutableList()
        val circularList = list.asCircular()

        lengths.asCircular()
                .take(lengths.size * 64)
                .forEach {
                    circularList.subList(currentPosition, currentPosition + it).reverse()
                    currentPosition += it + skipSize
                    skipSize++
                }

        val denseHash = list
                .chunked(16) {
                    it.reduce { acc, i -> acc xor i }
                }
        return denseHash
    }
}