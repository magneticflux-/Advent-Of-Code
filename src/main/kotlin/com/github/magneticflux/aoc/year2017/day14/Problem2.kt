package com.github.magneticflux.aoc.year2017.day14

import com.github.magneticflux.aoc.geom.IntPoint2D
import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import com.github.magneticflux.aoc.year2017.day10.Problem2

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines().next()

        val grid = mutableMapOf<IntPoint2D, Boolean>()

        (0..127).forEach {
            Problem2.denseHash("$input-$it".asIterable())
                    .forEachIndexed { hexIndex, hexNumber ->
                        hexNumber.toString(2).padStart(8, '0')
                                .apply {
                                    println("binary for $hexNumber at $hexIndex = $this")
                                }
                                .forEachIndexed { binaryIndex, c ->
                                    grid[IntPoint2D(it, hexIndex * 8 + binaryIndex)] = c == '1'
                                }
                    }
        }

        for (row in 0..127) {
            println((0..127).map { if (grid[IntPoint2D(row, it)]!!) '#' else '.' }.joinToString(separator = ""))
        }

        fun recursiveRemoveAdjacent(start: IntPoint2D) {
            grid[start] = false
            start.touching.filter {
                grid.getOrDefault(it, false)
            }.forEach {
                recursiveRemoveAdjacent(it)
            }
        }

        println(grid.map {
            if (it.value) {
                recursiveRemoveAdjacent(it.key)
                1
            } else
                0
        }.sum())
    }
}

private val IntPoint2D.touching: Iterable<IntPoint2D>
    get() {
        return listOf(
                this.copy(x = x + 1, y = y),
                this.copy(x = x - 1, y = y),
                this.copy(x = x, y = y + 1),
                this.copy(x = x, y = y - 1)
        )
    }