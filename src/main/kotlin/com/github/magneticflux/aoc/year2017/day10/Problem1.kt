package com.github.magneticflux.aoc.year2017.day10

import com.github.magneticflux.aoc.collections.asCircular
import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()
        val lengths = input.next().split(',').map { it.toInt() }

        var currentPosition = 0
        var skipSize = 0
        val list = (0..255).toMutableList().asCircular()

        lengths.forEach {
            list.subList(currentPosition, currentPosition + it).reverse()
            currentPosition += it + skipSize
            skipSize++
        }

        println("Result=${list[0] * list[1]}")
    }
}