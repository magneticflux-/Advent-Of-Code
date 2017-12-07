package com.github.magneticflux.aoc.year2017.day7

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines
import java.util.regex.Pattern

object Problem1 {
    val PATTERN: Pattern = Pattern.compile("(\\w+) \\((\\d+)\\)(?: -> )?(.+)?")

    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val programs = input.asSequence().map {
            val matcher = PATTERN.matcher(it)
            matcher.find()
            val name = matcher.group(1)
            val weight = matcher.group(2).toInt()
            val holding = matcher.group(3).orEmpty().split(", ").toSet()

            Program(name, weight, holding)
        }.toList()

        val notBottom = programs.flatMap { it.holding }.toSet()
        val allNames = programs.map { it.name }.toSet()

        println(allNames - notBottom)
    }

    data class Program(val name: String, val weight: Int, val holding: Set<String> = emptySet())
}