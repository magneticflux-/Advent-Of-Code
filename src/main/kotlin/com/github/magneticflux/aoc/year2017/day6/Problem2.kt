package com.github.magneticflux.aoc.year2017.day6

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.google.common.collect.HashMultiset

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()
        val currentBank = input.next().split("\t").map { it.toInt() }.toMutableList()
        val history = HashMultiset.create<List<Int>>()

        var cycle = 0
        var seenFirst = true

        while (history.count(currentBank) < 2) {
            if (history.count(currentBank) == 1 && seenFirst) {
                seenFirst = false
                cycle = 0
            }
            history.add(currentBank.toList())

            val index = currentBank.indexOf(currentBank.max())
            var leftToRedistribute = currentBank[index]
            currentBank[index] = 0

            var currentIndex = index
            while (leftToRedistribute > 0) {
                currentIndex++
                currentBank[currentIndex % currentBank.size]++
                leftToRedistribute--
            }

            cycle++
        }

        println("Cycle=$cycle")
    }
}