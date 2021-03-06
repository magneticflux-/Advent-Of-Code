package com.github.magneticflux.aoc.year2018.day5

import com.github.magneticflux.aoc.Helpers._

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem2 {
  def main(args: Array[String]): Unit = {
    val polymer = this.input().toSeq

    val usedLetters = polymer.view.map(_.toLower).distinct

    val min = usedLetters
      .map(toIgnore => {
        reactPolymer(polymer, toIgnore).size
      })
      .min

    println(min)
  }

  def reactPolymer(input: Seq[Char], toIgnore: Char): Seq[Char] = {
    var newPolymer = Seq[Char]()

    for (c <- input; if c.toLower != toIgnore) {
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

    newPolymer
  }
}
