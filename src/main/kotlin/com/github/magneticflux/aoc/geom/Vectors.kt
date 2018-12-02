package com.github.magneticflux.aoc.geom

import com.google.common.math.IntMath
import org.apache.commons.math3.util.FastMath

interface IVector2D<T : Number> {
    val dx: T
    val dy: T
    val manhattanDistance: T get() = dx.absoluteValue() + dy.absoluteValue()
    val distance: Double get() = FastMath.sqrt((dx * dx + dy * dy).toDouble())

    //fun copy(dx: T = this.dx, dy: T = this.dy): Vector2D<T>

    operator fun minus(other: IVector2D<T>): IVector2D<T> // = copy(dx = dx - other.dx, dy = dy - other.dy)

    operator fun plus(other: IVector2D<T>): IVector2D<T> // = copy(dx = dx + other.dx, dy = dy + other.dy)

    operator fun times(other: T): IVector2D<T> // = copy(dx = dx * other, dy = dy * other)

    operator fun div(other: T): IVector2D<T> // = copy(dx = dx / other, dy = dy / other)

    operator fun unaryPlus(): IVector2D<T> = this

    operator fun unaryMinus(): IVector2D<T> // = copy(dx = -dx, dy = -dy)

    fun toIntVector(): IntVector2D
    fun toPoint(): Point2D<T>
}

sealed class Vector2D<T : Number> : IVector2D<T> {
    override fun toIntVector(): IntVector2D = IntVector2D(this.dx.toInt(), this.dy.toInt())
}

data class IntVector2D(override val dx: Int, override val dy: Int) : Vector2D<Int>() {

    override operator fun minus(other: IVector2D<Int>): IntVector2D = copy(dx = dx - other.dx, dy = dy - other.dy)

    override operator fun plus(other: IVector2D<Int>): IntVector2D = copy(dx = dx + other.dx, dy = dy + other.dy)

    override operator fun times(other: Int): IntVector2D = copy(dx = dx * other, dy = dy * other)

    override operator fun div(other: Int): IntVector2D = copy(dx = dx / other, dy = dy / other)

    override operator fun unaryPlus(): IntVector2D = this

    override operator fun unaryMinus(): IntVector2D = copy(dx = -dx, dy = -dy)

    override fun toPoint(): IntPoint2D = IntPoint2D(dx, dy)
}

private fun Int.pow(i: Int): Int {
    return IntMath.pow(this, i)
}