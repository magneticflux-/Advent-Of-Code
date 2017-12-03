package com.github.magneticflux.aoc.day3

import com.github.magneticflux.aoc.day3.Problem1.Direction.*
import com.github.magneticflux.aoc.input
import java.util.*

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        val location = input.next().toInt()

        val map = mutableMapOf<Pair<Int, Int>, Int>()
        map[0.to(0)] = 1

        var currentLine = 0
        fun getLineLength() = (currentLine / 2) + 1
        var currentDirection = RIGHT
        var x = 0
        var y = 0

        while (true) {
            for (i in 1..getLineLength()) {
                println("x=$x y=$y")
                when (currentDirection) {
                    UP -> y++
                    DOWN -> y--
                    LEFT -> x--
                    RIGHT -> x++
                }
                map[x.to(y)] = (map.getOrDefault((x + 1).to(y + 1), 0)
                        + map.getOrDefault((x + 1).to(y), 0)
                        + map.getOrDefault((x + 1).to(y - 1), 0)
                        + map.getOrDefault((x).to(y + 1), 0)
                        + map.getOrDefault((x).to(y - 1), 0)
                        + map.getOrDefault((x - 1).to(y + 1), 0)
                        + map.getOrDefault((x - 1).to(y), 0)
                        + map.getOrDefault((x - 1).to(y - 1), 0))
                if (map[x.to(y)]!! > location) {
                    println("value: ${map[x.to(y)]}")
                    return
                }
            }
            currentDirection = currentDirection.next
            currentLine++
        }
    }
}