package com.github.magneticflux.aoc.year2017.day4

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val count = input.asSequence().filter {
            val list = it.split(" ").map { String(it.toList().sorted().toCharArray()) }
            val set = list.toSet()
            set.size == list.size
        }.count()

        println("Count=$count")
    }
}