@file:Suppress("USELESS_CAST")

package com.github.magneticflux.geom

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other

/**
 * Created by Mitchell Skaggs on 12/3/2017.
 */


@Suppress("UNCHECKED_CAST")
operator fun <T : Number> T.plus(other: T): T {
    return when {
        this is Byte -> (this as Byte + other as Byte).toByte() as T
        this is Short -> (this as Short + other as Short).toShort() as T
        this is Int -> (this as Int + other as Int) as T
        this is Long -> (this as Long + other as Long) as T
        this is Float -> (this as Float + other as Float) as T
        this is Double -> (this as Double + other as Double) as T
        else -> throw ArithmeticException("Unsupported number type: $this + $other")
    }
}

@Suppress("UNCHECKED_CAST")
operator fun <T> Number.times(other: T): T {
    return when {
        this is Byte -> (this as Byte * other as Byte).toByte() as T
        this is Short -> (this as Short * other as Short).toShort() as T
        this is Int -> (this as Int * other as Int) as T
        this is Long -> (this as Long * other as Long) as T
        this is Float -> (this as Float * other as Float) as T
        this is Double -> (this as Double * other as Double) as T
        else -> throw ArithmeticException("Unsupported number type: $this + $other")
    }
}

operator fun <T : Number> T.compareTo(other: T): Int {
    return when {
        this is Byte -> (this as Byte).compareTo(other as Byte)
        this is Short -> (this as Short).compareTo(other as Short)
        this is Int -> (this as Int).compareTo(other as Int)
        this is Long -> (this as Long).compareTo(other as Long)
        this is Float -> (this as Float).compareTo(other as Float)
        this is Double -> (this as Double).compareTo(other as Double)
        else -> throw ArithmeticException("Unsupported number type: $this + $other")
    }
}

fun <T : Number> T.absoluteValue(): T {
    return if (this > 0) this else -this
}

val Number.absoluteValue get() = this.absoluteValue()

@Suppress("UNCHECKED_CAST")
private operator fun <T : Number> T.unaryMinus(): T {
    return when {
        this is Byte -> this.unaryMinus() as T
        this is Short -> this.unaryMinus() as T
        this is Int -> this.unaryMinus() as T
        this is Long -> this.unaryMinus() as T
        this is Float -> this.unaryMinus() as T
        this is Double -> this.unaryMinus() as T
        else -> throw ArithmeticException("Unsupported number type: $this + $other")
    }
}
