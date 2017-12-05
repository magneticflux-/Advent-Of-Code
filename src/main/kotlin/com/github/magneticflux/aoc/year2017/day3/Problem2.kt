package com.github.magneticflux.aoc.year2017.day3

import com.github.magneticflux.aoc.geom.IntPoint2D
import com.github.magneticflux.aoc.geom.Point2D
import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.github.magneticflux.aoc.spiralSequenceOf

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()
        val location = input.next().toInt()

        val map = mutableMapOf(IntPoint2D(0, 0).to(1))

        val firstLarger = spiralSequenceOf()
                .drop(1) // Exclude first tile
                .find {
                    val value = it.surroundingPoints.map { map[it] ?: 0 }.sum()

                    map[it] = value

                    return@find value > location
                }
        println("First larger than $location at $firstLarger = ${map[firstLarger]}")
    }
}

private val Point2D<Int>.surroundingPoints
    get() = arrayOf(
            copy(x = x + 1, y = y),
            copy(x = x - 1, y = y),
            copy(x = x, y = y + 1),
            copy(x = x, y = y - 1),
            copy(x = x + 1, y = y + 1),
            copy(x = x + 1, y = y - 1),
            copy(x = x - 1, y = y + 1),
            copy(x = x - 1, y = y - 1)
    )
