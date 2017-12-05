package com.github.magneticflux.aoc.geom

import com.google.common.math.IntMath
import org.apache.commons.math3.util.FastMath

interface Vector2D<T : Number> {
    val dx: T
    val dy: T
    val manhattanDistance: T get() = dx.absoluteValue() + dy.absoluteValue()
    val distance: Double get() = FastMath.sqrt((dx * dx + dy * dy).toDouble())

    operator fun minus(other: Vector2D<T>): Vector2D<T>

    operator fun plus(other: Vector2D<T>): Vector2D<T>

    operator fun times(other: T): Vector2D<T>

    operator fun div(other: T): Vector2D<T>

    operator fun unaryPlus(): Vector2D<T>

    operator fun unaryMinus(): Vector2D<T>
}

data class IntVector2D(override val dx: Int, override val dy: Int) : Vector2D<Int> {
    override val distance by lazy { FastMath.sqrt((dx.pow(2) + dy.pow(2)).toDouble()) }

    override operator fun minus(other: Vector2D<Int>): IntVector2D {
        return IntVector2D(dx - other.dx, dy - other.dy)
    }

    override operator fun plus(other: Vector2D<Int>): IntVector2D {
        return IntVector2D(dx + other.dx, dy + other.dy)
    }

    override operator fun times(other: Int): IntVector2D {
        return IntVector2D(dx * other, dy * other)
    }

    override operator fun div(other: Int): IntVector2D {
        return IntVector2D(dx / other, dy / other)
    }

    override operator fun unaryPlus(): IntVector2D = this

    override operator fun unaryMinus(): IntVector2D = IntVector2D(-dx, -dy)
}

private fun Int.pow(i: Int): Int {
    return IntMath.pow(this, i)
}