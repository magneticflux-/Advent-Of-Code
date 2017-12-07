package com.github.magneticflux.aoc.year2017.day7

import com.github.magneticflux.aoc.input
import com.github.magneticflux.aoc.lines

object Problem2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = input().lines()

        val programs = input.asSequence().map {
            val matcher = Problem1.PATTERN.matcher(it)
            matcher.find()
            val name = matcher.group(1)
            val weight = matcher.group(2).toInt()
            val holding = matcher.group(3).orEmpty().split(", ").toSet() - ""

            Problem1.Program(name, weight, holding)
        }.toList()
        programs.filter {
            !it.isBalanced(programs)
        }.forEach {
            println("$it ${it.holding.map { name -> programs.first { it.name == name }.run { this.name.to(this.getWeight(programs)) } }}")
        }

        programs.first { it.name == "cwwwj" }.let {
            println("$it ${it.holding.map { name -> programs.first { it.name == name }.run { this.name.to(this.getWeight(programs)) } }}")
        }
    }
}

private fun Problem1.Program.isBalanced(programs: List<Problem1.Program>): Boolean {
    return this.holding
            .map { name ->
                programs.first { it.name == name }.getWeight(programs)
            }
            .toSet()
            .count() < 2
}

private fun Problem1.Program.getWeight(programs: List<Problem1.Program>): Int {
    return this.weight + this.holding.map { name -> programs.first { it.name == name }.getWeight(programs) }.sum()
}
