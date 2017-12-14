package com.github.magneticflux.aoc.collections

/**
 * Created by Mitchell Skaggs on 12/13/2017.
 */
private class CircularMutableList<T>(val delegate: MutableList<T>) : AbstractMutableList<T>() {
    override fun add(index: Int, element: T) {
        delegate.add(index % delegate.size, element)
    }

    override fun removeAt(index: Int): T {
        return delegate.removeAt(index % delegate.size)
    }

    override val size: Int = Int.MAX_VALUE

    override fun set(index: Int, element: T): T {
        return delegate.set(index % delegate.size, element)
    }

    override fun get(index: Int): T {
        return delegate[index % delegate.size]
    }
}

private class CircularList<out T>(val delegate: List<T>) : AbstractList<T>() {
    override val size: Int = Int.MAX_VALUE

    override fun get(index: Int): T {
        return delegate[index % delegate.size]
    }
}

/**
 * Returns a circular read-only view of the original List.
 * All changes made in the original list will be reflected in the circular one.
 */
public fun <T> List<T>.asCircular(): List<T> = CircularList(this)

/**
 * Returns a circular mutable view of the original mutable List.
 * All changes made in the original list will be reflected in the circular one and vice versa.
 */
@JvmName("asCircularMutable")
public fun <T> MutableList<T>.asCircular(): MutableList<T> = CircularMutableList(this)

fun main(args: Array<String>) {
    val list = listOf(0, 1, 2, 3, 4, 5)

    println(list)

    val mutableList = list.toMutableList().asCircular()
    mutableList.subList(5, 5 + 3).reverse()

    println(mutableList.take(6))
}