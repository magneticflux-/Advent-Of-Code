package com.github.magneticflux.aoc.year2018.day1

import com.github.magneticflux.aoc.InputHelpers._

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem1 {
  def main(args: Array[String]): Unit = {
    val answer = this.input()
      .getLines()
      .map(_.toInt)
      .sum

    println(answer)
  }
}

