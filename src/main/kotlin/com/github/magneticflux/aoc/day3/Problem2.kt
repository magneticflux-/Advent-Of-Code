package com.github.magneticflux.aoc.day3

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.spiralSequenceOf
import com.github.magneticflux.geom.IntPoint2D
import java.util.*

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
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