package com.github.magneticflux.aoc

import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

/**
  * Created by Mitchell Skaggs on 12/4/2018.
  */
object Helpers {

  implicit class AnyHasInput(any: Any) {
    def input(): BufferedSource = Source.fromInputStream(any.getClass.getResourceAsStream("input.txt"))

    def example(): BufferedSource = Source.fromInputStream(any.getClass.getResourceAsStream("example.txt"))
  }

  implicit class MyRegex(sc: StringContext) {
    def r: Regex = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
  }

}