package com.github.magneticflux.aoc

import java.io.InputStream

fun Any.input(): InputStream {
    return this::class.java.getResourceAsStream("input.txt")
}

fun Any.example(index: Int? = null): InputStream {
    return this::class.java.getResourceAsStream("example${index?.toString() ?: ""}.txt")
}