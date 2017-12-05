package com.github.magneticflux.aoc.year2017.day2

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import kotlin.math.absoluteValue

object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val grid = input.asSequence().map { it.split('\t').map { it.toInt() }.sorted() }.toList()
        println(grid.map { (it.first() - it.last()).absoluteValue }.sum())
    }
}