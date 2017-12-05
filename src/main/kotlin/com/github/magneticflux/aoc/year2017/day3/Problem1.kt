package com.github.magneticflux.aoc.year2017.day3

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.github.magneticflux.aoc.spiralSequenceOf

object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()
        val location = input.next().toInt()

        val point = spiralSequenceOf()
                .take(location)
                .last()

        println("Point= $point, Manhattan distance= ${point.toVector().manhattanDistance}")
    }
}