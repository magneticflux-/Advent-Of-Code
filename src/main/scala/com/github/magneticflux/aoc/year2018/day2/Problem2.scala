package com.github.magneticflux.aoc.year2018.day2

import com.github.magneticflux.aoc.InputHelpers._

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem2 {
  def main(args: Array[String]): Unit = {
    val lines = this.input()
      .getLines()
      .toSeq

    val pair = lines.combinations(2)
      .map { case Seq(s1, s2) => (s1, s2) }
      .find(p => {
        val (s1, s2) = p

        val numDiffs = s1.zip(s2)
          .map(p => {
            if (p._1 != p._2) 1 else 0
          })
          .sum

        numDiffs == 1
      })
      .get

    println(
      pair._1.zip(pair._2).filter(p => p._1 == p._2).map(_._1).mkString
    )
  }
}

