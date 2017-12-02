package com.github.magneticflux.aoc.day2

import com.github.magneticflux.aoc.input
import java.util.*
import kotlin.math.absoluteValue

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        input.useDelimiter("\n")
        val grid = input.asSequence().map { it.split('\t').map { it.toInt() }.sorted() }.toList()
        println(grid.map { (it.first() - it.last()).absoluteValue }.sum())
    }
}