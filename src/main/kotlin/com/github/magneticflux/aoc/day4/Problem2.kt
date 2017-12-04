package com.github.magneticflux.aoc.day4

import com.github.magneticflux.aoc.input
import java.util.*

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        input.useDelimiter("\n")

        val count = input.iterator().asSequence().filter {
            val list = it.split(" ").map { String(it.toList().sorted().toCharArray()) }
            val set = list.toSet()
            set.size == list.size
        }.count()

        println("Count= $count")
    }
}