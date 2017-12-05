package com.github.magneticflux.aoc.year2017.day1

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val lineChars = input().lines().next().toCharArray()

        var count = 0

        lineChars.forEachIndexed { index, c ->
            if (lineChars[(index + lineChars.size / 2) % lineChars.size] == c)
                count += c.toString().toInt()
        }

        println("Count: $count")
    }
}