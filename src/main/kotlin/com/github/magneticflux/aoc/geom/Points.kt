package com.github.magneticflux.aoc.geom

private interface IPoint2D<T : Number> {
    val x: T
    val y: T

    //fun copy(x: T = this.x, y: T = this.y): Point2D<T>

    operator fun minus(other: Vector2D<T>): Point2D<T>// = copy(x = x - other.dx, y = y - other.dy)

    operator fun minus(other: Point2D<T>): Vector2D<T>

    operator fun plus(other: Vector2D<T>): Point2D<T>// = copy(x = x + other.dx, y = y + other.dy)

    operator fun times(other: T): Point2D<T> // = copy(x = x * other, y = y * other)

    operator fun div(other: T): Point2D<T> // = copy(x = x / other, y = y / other)

    fun toIntPoint(): IntPoint2D
    fun toVector(): Vector2D<T>
}

sealed class Point2D<T : Number> : IPoint2D<T> {

    companion object {
        val ORIGIN = IntPoint2D(0, 0)
    }
}

data class IntPoint2D(override val x: Int, override val y: Int) : Point2D<Int>() {
    override fun toIntPoint(): IntPoint2D = this

    override operator fun plus(other: Vector2D<Int>) = copy(x = x + other.dx, y = y + other.dy)

    override operator fun minus(other: Vector2D<Int>) = copy(x = x - other.dx, y = y - other.dy)

    override operator fun minus(other: Point2D<Int>): IntVector2D = IntVector2D(x - other.x, y - other.y)

    override operator fun times(other: Int) = copy(x = x * other, y = y * other)

    override operator fun div(other: Int) = copy(x = x / other, y = y / other)

    override fun toVector(): IntVector2D = IntVector2D(x, y)
}

fun main(args: Array<String>) {
    val intPoint = IntPoint2D(1, 2)
    val intVec = IntVector2D(1, 1)
    val intPoint2 = IntPoint2D(1, 1)
    println(intPoint)
    println(intPoint + intVec)
    println(intPoint - intPoint2)
}
