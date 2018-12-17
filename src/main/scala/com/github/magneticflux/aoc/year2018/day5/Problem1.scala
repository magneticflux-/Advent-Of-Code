package com.github.magneticflux.aoc.year2018.day5

import com.github.magneticflux.aoc.Helpers._

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem1 {
  def main(args: Array[String]): Unit = {
    val polymer = this.input().toSeq
    var newPolymer = Seq[Char]()

    for (c <- polymer) {
      newPolymer.lastOption match {
        case Some(lastChar) =>
          if (lastChar.toUpper == c.toUpper && (lastChar.isUpper ^ c.isUpper)) {
            newPolymer = newPolymer.init
          } else {
            newPolymer = newPolymer :+ c
          }
        case None => newPolymer = newPolymer :+ c
      }
    }

    println(newPolymer.mkString)
    println(newPolymer.size)
  }


}
