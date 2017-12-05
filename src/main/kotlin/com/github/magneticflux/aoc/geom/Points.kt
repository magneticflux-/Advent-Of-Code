@file:Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")

package com.github.magneticflux.aoc.geom

private interface IPoint2D<T : Number> {
    val x: T
    val y: T

    fun copy(x: T = this.x, y: T = this.y): Point2D<T>

    operator fun minus(other: Vector2D<T>): Point2D<T> = copy(x = x - other.dx, y = y - other.dy)

    operator fun minus(other: Point2D<T>): Vector2D<T>

    operator fun plus(other: Vector2D<T>): Point2D<T> = copy(x = x + other.dx, y = y + other.dy)

    operator fun times(other: T) = copy(x = x * other, y = y * other)

    operator fun div(other: T) = copy(x = x / other, y = y / other)

    fun toBytePoint(): BytePoint2D
    fun toShortPoint(): ShortPoint2D
    fun toIntPoint(): IntPoint2D
    fun toLongPoint(): LongPoint2D
    fun toFloatPoint(): FloatPoint2D
    fun toDoublePoint(): DoublePoint2D
    fun toVector(): Vector2D<T>
}

sealed class Point2D<T : Number> : IPoint2D<T> {

    override fun toBytePoint(): BytePoint2D = BytePoint2D(x.toByte(), y.toByte())

    override fun toShortPoint(): ShortPoint2D = ShortPoint2D(x.toShort(), y.toShort())

    override fun toIntPoint(): IntPoint2D = IntPoint2D(this.x.toInt(), this.y.toInt())

    override fun toLongPoint(): LongPoint2D = LongPoint2D(this.x.toLong(), this.y.toLong())

    override fun toFloatPoint(): FloatPoint2D = FloatPoint2D(x.toFloat(), y.toFloat())

    override fun toDoublePoint(): DoublePoint2D = DoublePoint2D(x.toDouble(), y.toDouble())

    companion object {
        val ORIGIN = IntPoint2D(0, 0)
    }
}

data class BytePoint2D(override val x: Byte, override val y: Byte) : Point2D<Byte>() {

    override operator fun plus(other: Vector2D<Byte>) = copy(x = (x + other.dx).toByte(), y = (y + other.dy).toByte())

    override operator fun minus(other: Vector2D<Byte>) = copy(x = (x - other.dx).toByte(), y = (y - other.dy).toByte())

    override operator fun times(other: Byte) = copy(x = (x * other).toByte(), y = (y * other).toByte())

    override operator fun div(other: Byte) = copy(x = (x / other).toByte(), y = (y / other).toByte())

    override fun minus(other: Point2D<Byte>): ByteVector2D = ByteVector2D((x - other.x).toByte(), (y - other.y).toByte())

    override fun toVector(): ByteVector2D = ByteVector2D(x, y)
}

data class ShortPoint2D(override val x: Short, override val y: Short) : Point2D<Short>() {

    override operator fun plus(other: Vector2D<Short>) = copy(x = (x + other.dx).toShort(), y = (y + other.dy).toShort())

    override operator fun minus(other: Vector2D<Short>) = copy(x = (x - other.dx).toShort(), y = (y - other.dy).toShort())

    override operator fun times(other: Short) = copy(x = (x * other).toShort(), y = (y * other).toShort())

    override operator fun div(other: Short) = copy(x = (x / other).toShort(), y = (y / other).toShort())

    override fun minus(other: Point2D<Short>): ShortVector2D = ShortVector2D((x - other.x).toShort(), (y - other.y).toShort())

    override fun toVector(): ShortVector2D = ShortVector2D(x, y)
}

data class IntPoint2D(override val x: Int, override val y: Int) : Point2D<Int>() {

    override operator fun plus(other: Vector2D<Int>) = copy(x = x + other.dx, y = y + other.dy)

    override operator fun minus(other: Vector2D<Int>) = copy(x = x - other.dx, y = y - other.dy)

    override operator fun minus(other: Point2D<Int>): IntVector2D = IntVector2D(x - other.x, y - other.y)

    operator fun plus(byteVector2D: ByteVector2D): IntPoint2D = this + byteVector2D.toIntVector()
    operator fun plus(shortVector2D: ShortVector2D): IntPoint2D = this + shortVector2D.toIntVector()
    operator fun plus(longVector2D: LongVector2D): LongPoint2D = this.toLongPoint() + longVector2D
    operator fun plus(floatVector2D: FloatVector2D): FloatPoint2D = this.toFloatPoint() + floatVector2D
    operator fun plus(doubleVector2D: DoubleVector2D): DoublePoint2D = this.toDoublePoint() + doubleVector2D

    operator fun minus(bytePoint2D: BytePoint2D): IntVector2D = this - bytePoint2D.toIntPoint()
    operator fun minus(shortPoint2D: ShortPoint2D): IntVector2D = this - shortPoint2D.toIntPoint()
    operator fun minus(longPoint2D: LongPoint2D): LongVector2D = this.toLongPoint() - longPoint2D
    operator fun minus(floatPoint2D: FloatPoint2D): FloatVector2D = this.toFloatPoint() - floatPoint2D
    operator fun minus(doublePoint2D: DoublePoint2D): DoubleVector2D = this.toDoublePoint() - doublePoint2D

    override fun toVector(): IntVector2D = IntVector2D(x, y)
}

data class LongPoint2D(override val x: Long, override val y: Long) : Point2D<Long>() {

    override operator fun plus(other: Vector2D<Long>) = copy(x = x + other.dx, y = y + other.dy)

    override operator fun minus(other: Vector2D<Long>) = copy(x = x - other.dx, y = y - other.dy)

    override operator fun times(other: Long) = copy(x = x * other, y = y * other)

    override operator fun div(other: Long) = copy(x = x / other, y = y / other)

    override fun minus(other: Point2D<Long>): LongVector2D = LongVector2D(this.x - other.x, this.y - other.y)

    operator fun plus(byteVector2D: ByteVector2D): LongPoint2D = this + byteVector2D.toLongVector()
    operator fun plus(shortVector2D: ShortVector2D): LongPoint2D = this + shortVector2D.toLongVector()
    operator fun plus(intVector2D: IntVector2D): LongPoint2D = this + intVector2D.toLongVector()
    operator fun plus(floatVector2D: FloatVector2D): FloatPoint2D = this.toFloatPoint() + floatVector2D
    operator fun plus(doubleVector2D: DoubleVector2D): DoublePoint2D = this.toDoublePoint() + doubleVector2D

    operator fun minus(bytePoint2D: BytePoint2D): LongVector2D = this - bytePoint2D.toLongPoint()
    operator fun minus(shortPoint2D: ShortPoint2D): LongVector2D = this - shortPoint2D.toLongPoint()
    operator fun minus(intPoint2D: IntPoint2D): LongVector2D = this - intPoint2D.toLongPoint()
    operator fun minus(floatPoint2D: FloatPoint2D): FloatVector2D = this.toFloatPoint() - floatPoint2D
    operator fun minus(doublePoint2D: DoublePoint2D): DoubleVector2D = this.toDoublePoint() - doublePoint2D

    override fun toVector(): LongVector2D = LongVector2D(x, y)
}

data class FloatPoint2D(override val x: Float, override val y: Float) : Point2D<Float>() {

    override operator fun plus(other: Vector2D<Float>) = copy(x = x + other.dx, y = y + other.dy)

    override operator fun minus(other: Vector2D<Float>) = copy(x = x - other.dx, y = y - other.dy)

    override operator fun times(other: Float) = copy(x = x * other, y = y * other)

    override operator fun div(other: Float) = copy(x = x / other, y = y / other)

    override fun minus(other: Point2D<Float>): FloatVector2D = FloatVector2D(this.x - other.x, this.y - other.y)

    operator fun plus(byteVector2D: ByteVector2D): FloatPoint2D = this + byteVector2D.toFloatVector()
    operator fun plus(shortVector2D: ShortVector2D): FloatPoint2D = this + shortVector2D.toFloatVector()
    operator fun plus(intVector2D: IntVector2D): FloatPoint2D = this + intVector2D.toFloatVector()
    operator fun plus(longVector2D: LongVector2D): FloatPoint2D = this + longVector2D.toFloatVector()
    operator fun plus(doubleVector2D: DoubleVector2D): DoublePoint2D = this.toDoublePoint() + doubleVector2D

    operator fun minus(bytePoint2D: BytePoint2D): FloatVector2D = this - bytePoint2D.toFloatPoint()
    operator fun minus(shortPoint2D: ShortPoint2D): FloatVector2D = this - shortPoint2D.toFloatPoint()
    operator fun minus(intPoint2D: IntPoint2D): FloatVector2D = this - intPoint2D.toFloatPoint()
    operator fun minus(longPoint2D: LongPoint2D): FloatVector2D = this - longPoint2D.toFloatPoint()
    operator fun minus(doublePoint2D: DoublePoint2D): DoubleVector2D = this.toDoublePoint() - doublePoint2D

    override fun toVector(): FloatVector2D = FloatVector2D(x, y)
}

data class DoublePoint2D(override val x: Double, override val y: Double) : Point2D<Double>() {

    override operator fun plus(other: Vector2D<Double>) = copy(x = x + other.dx, y = y + other.dy)

    override operator fun minus(other: Vector2D<Double>) = copy(x = x - other.dx, y = y - other.dy)

    override operator fun times(other: Double) = copy(x = x * other, y = y * other)

    override operator fun div(other: Double) = copy(x = x / other, y = y / other)

    override fun minus(other: Point2D<Double>): DoubleVector2D = DoubleVector2D(this.x - other.x, this.y - other.y)

    operator fun plus(byteVector2D: ByteVector2D): DoublePoint2D = this + byteVector2D.toDoubleVector()
    operator fun plus(shortVector2D: ShortVector2D): DoublePoint2D = this + shortVector2D.toDoubleVector()
    operator fun plus(intVector2D: IntVector2D): DoublePoint2D = this + intVector2D.toDoubleVector()
    operator fun plus(longVector2D: LongVector2D): DoublePoint2D = this + longVector2D.toDoubleVector()
    operator fun plus(floatVector2D: FloatVector2D): DoublePoint2D = this.toDoublePoint() + floatVector2D

    operator fun minus(bytePoint2D: BytePoint2D): DoubleVector2D = this - bytePoint2D.toDoublePoint()
    operator fun minus(shortPoint2D: ShortPoint2D): DoubleVector2D = this - shortPoint2D.toDoublePoint()
    operator fun minus(intPoint2D: IntPoint2D): DoubleVector2D = this - intPoint2D.toDoublePoint()
    operator fun minus(longPoint2D: LongPoint2D): DoubleVector2D = this - longPoint2D.toDoublePoint()
    operator fun minus(floatPoint2D: FloatPoint2D): DoubleVector2D = this.toDoublePoint() - floatPoint2D

    override fun toVector(): DoubleVector2D = DoubleVector2D(x, y)
}

fun main(args: Array<String>) {
    val intPoint = IntPoint2D(1, 2)
    val intVec = IntVector2D(1, 1)
    val longPoint = LongPoint2D(1, 1)
    println(intPoint)
    println(intPoint + intVec)
    println(intPoint - longPoint)
}
