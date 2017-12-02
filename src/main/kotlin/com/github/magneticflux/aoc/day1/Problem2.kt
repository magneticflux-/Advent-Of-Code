package com.github.magneticflux.aoc.day1

import com.github.magneticflux.aoc.input
import java.util.*

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val lineChars = Scanner(input()).nextLine().toCharArray()

        var count = 0

        lineChars.forEachIndexed { index, c ->
            if (lineChars[(index + lineChars.size / 2) % lineChars.size] == c)
                count += c.toString().toInt()
        }

        println("Count: $count")
    }
}