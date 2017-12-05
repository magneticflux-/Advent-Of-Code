package com.github.magneticflux.aoc.geom

import com.google.common.math.IntMath
import org.apache.commons.math3.util.FastMath

interface IVector2D<T : Number> {
    val dx: T
    val dy: T
    val manhattanDistance: T get() = dx.absoluteValue() + dy.absoluteValue()
    val distance: Double get() = FastMath.sqrt((dx * dx + dy * dy).toDouble())

    fun copy(dx: T = this.dx, dy: T = this.dy): Vector2D<T>

    operator fun minus(other: IVector2D<T>): IVector2D<T> {
        return copy(dx = dx - other.dx, dy = dy - other.dy)
    }

    operator fun plus(other: IVector2D<T>): IVector2D<T> {
        return copy(dx = dx + other.dx, dy = dy + other.dy)
    }

    operator fun times(other: T): IVector2D<T> {
        return copy(dx = dx * other, dy = dy * other)
    }

    operator fun div(other: T): IVector2D<T> {
        return copy(dx = dx / other, dy = dy / other)
    }

    operator fun unaryPlus(): IVector2D<T> = this

    operator fun unaryMinus(): IVector2D<T> {
        return copy(dx = -dx, dy = -dy)
    }

    fun toByteVector(): ByteVector2D
    fun toShortVector(): ShortVector2D
    fun toIntVector(): IntVector2D
    fun toLongVector(): LongVector2D
    fun toFloatVector(): FloatVector2D
    fun toDoubleVector(): DoubleVector2D
    fun toPoint(): Point2D<T>
}

sealed class Vector2D<T : Number> : IVector2D<T> {

    override fun toByteVector(): ByteVector2D = ByteVector2D(dx.toByte(), dy.toByte())

    override fun toShortVector(): ShortVector2D = ShortVector2D(dx.toShort(), dy.toShort())

    override fun toIntVector(): IntVector2D = IntVector2D(this.dx.toInt(), this.dy.toInt())

    override fun toLongVector(): LongVector2D = LongVector2D(this.dx.toLong(), this.dy.toLong())

    override fun toFloatVector(): FloatVector2D = FloatVector2D(dx.toFloat(), dy.toFloat())

    override fun toDoubleVector(): DoubleVector2D = DoubleVector2D(dx.toDouble(), dy.toDouble())
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class ByteVector2D(override val dx: Byte, override val dy: Byte) : Vector2D<Byte>() {
    override fun toPoint(): BytePoint2D = BytePoint2D(dx, dy)
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class ShortVector2D(override val dx: Short, override val dy: Short) : Vector2D<Short>() {
    override fun toPoint(): ShortPoint2D = ShortPoint2D(dx, dy)
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class IntVector2D(override val dx: Int, override val dy: Int) : Vector2D<Int>() {
    override fun toPoint(): IntPoint2D = IntPoint2D(dx, dy)
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class LongVector2D(override val dx: Long, override val dy: Long) : Vector2D<Long>() {
    override fun toPoint(): LongPoint2D = LongPoint2D(dx, dy)
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class FloatVector2D(override val dx: Float, override val dy: Float) : Vector2D<Float>() {
    override fun toPoint(): FloatPoint2D = FloatPoint2D(dx, dy)
}

@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class DoubleVector2D(override val dx: Double, override val dy: Double) : Vector2D<Double>() {
    override fun toPoint(): DoublePoint2D = DoublePoint2D(dx, dy)
}

private fun Int.pow(i: Int): Int {
    return IntMath.pow(this, i)
}