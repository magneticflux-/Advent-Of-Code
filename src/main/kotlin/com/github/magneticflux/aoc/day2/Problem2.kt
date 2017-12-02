package com.github.magneticflux.aoc.day2

import com.github.magneticflux.aoc.input
import java.util.*

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        input.useDelimiter("\n")
        val grid = input.asSequence().map { it.split('\t').map { it.toInt() } }.toList()
        println(grid.map {
            var first: Int? = null
            var second: Int? = null

            it.forEach { a ->
                it.forEach { b ->
                    if (a % b == 0 && a != b) {
                        first = a
                        second = b
                    }
                }
            }
            first!! / second!!
        }.sum())
    }
}