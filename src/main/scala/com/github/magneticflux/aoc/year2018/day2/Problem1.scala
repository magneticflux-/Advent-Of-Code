package com.github.magneticflux.aoc.year2018.day2

import com.github.magneticflux.aoc.Helpers._

import scala.collection.mutable

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem1 {
  def main(args: Array[String]): Unit = {
    val answer = this.input()
      .getLines()
      .map(s => {
        val chars = mutable.Map[Char, Int]()
        s.foreach(c => {
          chars.put(c, chars.getOrElse(c, 0) + 1)
        })

        (chars.exists(_._2 == 2), chars.exists(_._2 == 3))
      })
      .foldLeft((0, 0))((totals, b) => {
        totals.copy(totals._1 + (if (b._1) 1 else 0), totals._2 + (if (b._2) 1 else 0))
      })

    println(answer._1 * answer._2)
  }
}

