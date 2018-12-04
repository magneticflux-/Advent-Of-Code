package com.github.magneticflux.aoc

import scala.io.{BufferedSource, Source}

/**
  * Created by Mitchell Skaggs on 12/4/2018.
  */
object InputHelpers {

  implicit class AnyHasInput(any: Any) {
    def input(): BufferedSource = Source.fromInputStream(any.getClass.getResourceAsStream("input.txt"))

    def ex(): BufferedSource = Source.fromInputStream(any.getClass.getResourceAsStream("example.txt"))
  }

}