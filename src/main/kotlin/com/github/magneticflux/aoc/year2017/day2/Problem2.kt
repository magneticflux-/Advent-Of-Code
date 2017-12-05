package com.github.magneticflux.aoc.year2017.day2

import com.github.magneticflux.aoc.combinations
import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.github.magneticflux.aoc.toSortedPair

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val grid = input.asSequence().map { it.split('\t').map { it.toInt() } }.toList()

        val checksum = grid.map { row ->
            val divisible = row.combinations(2)
                    .map { it.toSortedPair() }
                    .first {
                        return@first it.larger % it.smaller == 0
                    }
            return@map divisible.larger / divisible.smaller
        }.sum()

        println(checksum)
    }
}