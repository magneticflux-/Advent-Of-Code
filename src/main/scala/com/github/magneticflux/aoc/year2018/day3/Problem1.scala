package com.github.magneticflux.aoc.year2018.day3


import com.github.magneticflux.aoc.Helpers._

import scala.collection.mutable

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem1 {

  def main(args: Array[String]): Unit = {
    val claims = this.input()
      .getLines()
      .map { case r"""#(\d+)$id @ (\d+)$col,(\d+)$row: (\d+)${width}x(\d+)$height""" => Claim(id.toInt, col.toInt, row.toInt, width.toInt, height.toInt) }
      .toSeq

    val fabric = mutable.Map[(Int, Int), Int]()

    for (c <- claims;
         row <- c.rowRange;
         col <- c.colRange) {
      val point = (row, col)
      fabric(point) = fabric.getOrElse(point, 0) + 1
    }

    val totalOverlapping = fabric.count(p => p._2 > 1)

    println(f"$totalOverlapping square inches overlapping")
  }

  case class Claim(id: Int, col: Int, row: Int, width: Int, height: Int) {
    val colRange: Range = col until (col + width)
    val rowRange: Range = row until (row + height)
  }

}
