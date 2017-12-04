package com.github.magneticflux.aoc.day1

import com.github.magneticflux.aoc.input
import java.util.*

object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val lineChars = Scanner(input()).nextLine().toCharArray()

        var count = 0

        lineChars.forEachIndexed { index, c ->
            if (lineChars[(index + 1) % lineChars.size] == c)
                count += c.toString().toInt()
        }

        println("Count: $count")
    }
}