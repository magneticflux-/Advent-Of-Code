package com.github.magneticflux.aoc.year2017.day8

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val registers = mutableMapOf<String, Int>().withDefault { 0 }

        var maxSoFar = Int.MIN_VALUE

        input.forEach {
            val matcher = Problem1.PATTERN.matcher(it)

            matcher.find()

            val firstRegister = matcher.group(1)
            val operation = matcher.group(2)
            val quantity = matcher.group(3).toInt()
            val testRegister = matcher.group(4)
            val testComparison = matcher.group(5)
            val testQuantity = matcher.group(6).toInt()

            val comparisonSucceeded = when (testComparison) {
                ">" -> registers.getValue(testRegister) > testQuantity
                "<" -> registers.getValue(testRegister) < testQuantity
                ">=" -> registers.getValue(testRegister) >= testQuantity
                "<=" -> registers.getValue(testRegister) <= testQuantity
                "==" -> registers.getValue(testRegister) == testQuantity
                "!=" -> registers.getValue(testRegister) != testQuantity
                else -> throw IllegalStateException("Unknown comparison $testComparison")
            }

            if (comparisonSucceeded) {
                val newValue = registers.getValue(firstRegister) + if (operation == "inc") quantity else -quantity
                registers[firstRegister] = newValue
                if (newValue > maxSoFar)
                    maxSoFar = newValue
            }
        }

        println(maxSoFar)
    }
}