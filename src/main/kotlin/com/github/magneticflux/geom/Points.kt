package com.github.magneticflux.geom

import com.github.magneticflux.aoc.Direction

interface Point2D<T : Number> {
    val x: T
    val y: T

    operator fun minus(other: Vector2D<T>): Point2D<T>

    operator fun plus(other: Vector2D<T>): Point2D<T>
}

/**
 * Created by Mitchell Skaggs on 12/3/2017.
 */
data class IntPoint2D(override val x: Int, override val y: Int) : Point2D<Int>, Vector2D<Int> by IntVector2D(x, y) {
    val adjacentPoints by lazy {
        arrayOf(
                this.copy(x = x + 1),
                this.copy(x = x - 1),
                this.copy(y = y + 1),
                this.copy(y = y - 1)
        )
    }

    val diagonalPoints by lazy {
        arrayOf(
                this.copy(x = x + 1, y = y + 1),
                this.copy(x = x + 1, y = y - 1),
                this.copy(x = x - 1, y = y + 1),
                this.copy(x = x - 1, y = y - 1)
        )
    }

    val surroundingPoints by lazy {
        adjacentPoints + diagonalPoints
    }

    fun move(direction: Direction): IntPoint2D {
        return when (direction) {
            Direction.UP -> copy(y = y + 1)
            Direction.DOWN -> copy(y = y - 1)
            Direction.LEFT -> copy(x = x - 1)
            Direction.RIGHT -> copy(x = x + 1)
        }
    }

    override operator fun minus(other: Vector2D<Int>): IntPoint2D {
        return IntPoint2D(x - other.dx, y - other.dy)
    }

    override operator fun plus(other: Vector2D<Int>): IntPoint2D {
        return IntPoint2D(x + other.dx, y + other.dy)
    }

    companion object {
        val ORIGIN = IntPoint2D(0, 0)
    }
}