package com.github.magneticflux.aoc.year2017.day5

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()
        val instructions = input.asSequence().map { it.toInt() }.toMutableList()

        var currentStep = 1
        var currentIndex = 0
        fun nextIndex() = instructions[currentIndex] + currentIndex
        while (nextIndex() >= 0 && nextIndex() < instructions.size) {
            currentStep++
            val oldIndex = currentIndex
            val oldOffset = instructions[currentIndex]
            currentIndex += instructions[currentIndex]
            if (oldOffset >= 3)
                instructions[oldIndex]--
            else
                instructions[oldIndex]++
        }

        println("Step=$currentStep")
    }
}