package com.github.magneticflux.aoc.day3

import com.github.magneticflux.aoc.day3.Problem1.Direction.*
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
        val location = input.next().toInt()

        var currentStep = 0
        var currentLocation = 1
        var currentLength = 0
        fun getMaxLength(): Int = (currentStep / 2) + 1
        var direction = RIGHT
        var x = 0
        var y = 0

        outside@ while (currentLocation < location) {
            while (currentLength < getMaxLength()) {
                println("loc: $currentLocation x= $x y= $y")
                if (currentLocation == location)
                    break@outside
                currentLength++
                currentLocation++
                when (direction) {
                    UP -> y++
                    DOWN -> y--
                    LEFT -> x--
                    RIGHT -> x++
                }
            }
            direction = direction.next
            currentLength = 0
            currentStep++
        }

        println("x: $x y:$y")
        println(x.absoluteValue + y.absoluteValue)
    }

    enum class Direction(nextFun: () -> Direction) {
        UP({ LEFT }), DOWN({ RIGHT }), LEFT({ DOWN }), RIGHT({ UP });

        val next: Direction by lazy { nextFun() }
    }
}