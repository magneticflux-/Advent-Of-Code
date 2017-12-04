package com.github.magneticflux.aoc.day3

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.spiralSequenceOf
import java.util.*

object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        val location = input.next().toInt()

        val point = spiralSequenceOf()
                .take(location)
                .last()

        println("Point= $point, Manhattan distance= ${point.manhattanDistance}")
    }
}