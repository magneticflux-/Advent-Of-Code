package com.github.magneticflux.aoc.year2017.day1

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val lineChars = input().lines().next().toCharArray()

        var count = 0

        lineChars.forEachIndexed { index, c ->
            if (lineChars[(index + 1) % lineChars.size] == c)
                count += c.toString().toInt()
        }

        println("Count: $count")
    }
}