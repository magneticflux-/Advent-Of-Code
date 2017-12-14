package com.github.magneticflux.aoc.year2017.day14

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.github.magneticflux.aoc.year2017.day10.Problem2.denseHash

object Problem1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines().next()

        println((0..127).map {
            denseHash("$input-$it".asIterable())
                    .map {
                        it.toString(2).count { it == '1' }
                    }.sum()
        }.sum())
    }
}