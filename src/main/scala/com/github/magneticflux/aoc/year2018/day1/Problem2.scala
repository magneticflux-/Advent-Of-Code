package com.github.magneticflux.aoc.year2018.day1

import com.github.magneticflux.aoc.Helpers._

import scala.collection.mutable
import scala.util.control.Breaks._

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem2 {
  def main(args: Array[String]): Unit = {
    val numbers = this.input()
      .getLines()
      .map(_.toInt)
      .toSeq

    val infiniteNumbers = Iterator.continually(numbers).flatten

    val seenSoFar = mutable.Set[Int]()

    var total = 0

    breakable {
      for (i <- infiniteNumbers) {
        if (!seenSoFar.add(total)) {
          println(s"Found $total twice!")
          break
        }
        total += i
      }
    }
  }
}

