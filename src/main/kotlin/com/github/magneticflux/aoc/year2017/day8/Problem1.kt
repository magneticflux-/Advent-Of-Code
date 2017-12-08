package com.github.magneticflux.aoc.year2017.day8

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import java.util.regex.Pattern

object Problem1 {
    val PATTERN: Pattern = Pattern.compile("(\\w+) (inc|dec) ([-\\d]+) if (\\w+) ([<>=!]+) ([-\\d]+)")

    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val registers = mutableMapOf<String, Int>().withDefault { 0 }

        input.forEach {
            val matcher = PATTERN.matcher(it)

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

            if (comparisonSucceeded)
                registers[firstRegister] = registers.getValue(firstRegister) + if (operation == "inc") quantity else -quantity
        }

        registers.maxBy { it.value }.let {
            println(it)
        }
    }
}