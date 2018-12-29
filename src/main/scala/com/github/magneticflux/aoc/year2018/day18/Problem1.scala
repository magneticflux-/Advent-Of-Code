package com.github.magneticflux.aoc.year2018.day18

import com.github.magneticflux.aoc.Helpers._

import scala.language.implicitConversions

/**
  * Created by Mitchell Skaggs on 12/29/2018.
  */
object Problem1 {

  def main(args: Array[String]): Unit = {
    val mapData = this.input().getLines()
      .zipWithIndex
      .flatMap {
        tuple =>
          val (line, row) = tuple
          line.zipWithIndex
            .map {
              case ('.', col) => ((row, col), Open)
              case ('|', col) => ((row, col), Forest)
              case ('#', col) => ((row, col), Lumberyard)
            }
      }.toMap
    var terrain = TerrainMap(mapData)

    println("Start:")
    println(terrain)

    for (i <- 0 until 2000) {
      terrain = simulate(terrain)
      println(s"After minute ${i + 1}:")
      //println(terrain)


      val numForest = terrain.data.count(_._2 == Forest)
      val numLumberyard = terrain.data.count(_._2 == Lumberyard)

      println(s"Value: ${numForest * numLumberyard}")
    }
  }


  def simulate(terrain: TerrainMap): TerrainMap = {
    val changes = (for {
      col <- 0 until terrain.cols
      row <- 0 until terrain.rows
      adjacent = terrain.adjacentTerrain((row, col))
      result: Option[((Int, Int), Terrain)] = terrain(row, col) match {
        case Open if adjacent.count(_ == Forest) >= 3 => Some((row, col), Forest)
        case Forest if adjacent.count(_ == Lumberyard) >= 3 => Some((row, col), Lumberyard)
        case Lumberyard if adjacent.count(_ == Lumberyard) < 1 || adjacent.count(_ == Forest) < 1 => Some((row, col), Open)
        case _ => None
      }
    } yield result)
      .flatten

    terrain + changes
  }

  implicit def terrainMapToMap(it: TerrainMap): Map[(Int, Int), Terrain] = it.data
}


case class TerrainMap(data: Map[(Int, Int), Terrain]) {

  lazy val rows: Int = data.view.map(_._1._1).max + 1
  lazy val cols: Int = data.view.map(_._1._2).max + 1

  def +(newData: Seq[((Int, Int), Terrain)]): TerrainMap = copy(data ++ newData)

  def adjacentTerrain(tile: (Int, Int)): Seq[Terrain] = {
    tile.adjacent.flatMap(data.get)
  }

  override def toString: String = {
    val builder = new StringBuilder

    for (row <- 0 until rows) {
      for (col <- 0 until cols) {
        builder += (data(row, col) match {
          case Open => '.'
          case Forest => '|'
          case Lumberyard => '#'
        })
      }
      builder += '\n'
    }

    builder.mkString
  }

  implicit class PairHasAdjacent(it: (Int, Int)) {
    lazy val adjacent: Seq[(Int, Int)] = {
      (r + 1, c) :: (r + 1, c + 1) :: (r, c + 1) :: (r - 1, c + 1) :: (r - 1, c) :: (r - 1, c - 1) :: (r, c - 1) :: (r + 1, c - 1) :: Nil
    }

    @inline def r: Int = it._1

    @inline def c: Int = it._2
  }

}

sealed trait Terrain

case object Open extends Terrain

case object Forest extends Terrain

case object Lumberyard extends Terrain