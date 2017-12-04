package com.github.magneticflux.aoc.day4

import com.github.magneticflux.aoc.input
import java.util.*

/**
 * Created by Mitchell Skaggs on 12/1/2017.
 */
object Problem1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(input())
        input.useDelimiter("\n")

        val count = input.iterator().asSequence().filter {
            val list = it.split(" ")
            val set = list.toSet()
            set.size == list.size
        }.count()

        println("Count= $count")
    }
}