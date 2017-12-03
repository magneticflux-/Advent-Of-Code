package com.github.magneticflux.aoc

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by Mitchell Skaggs on 12/2/2017.
 */
object CombinationsTest : Spek({
    given("a list of size 2") {
        val list = listOf("One", "Two")

        on("combinations with k = 0") {
            val combinations = list.combinations(0).toList()

            it("should return one item") {
                assert.that(combinations, hasSize(equalTo(1)))
            }

            it("should have the first element be an empty list") {
                assert.that(combinations.first(), isEmpty)
            }
        }

        on("combinations with k = 1") {
            val combinations = list.combinations(1).toList()

            it("should return two items") {
                assert.that(combinations, hasSize(equalTo(2)))
            }

            it("should have the first element be the same as the first list item") {
                assert.that(combinations.first()[0], equalTo(list.first()))
            }

            it("should have the second element be the same as the second list item") {
                assert.that(combinations.second()[0], equalTo(list.second()))
            }
        }

        on("combinations with k = 2") {
            val combinations = list.combinations(2).toList()

            it("should return one item") {
                assert.that(combinations, hasSize(equalTo(1)))
            }

            it("should have the first element be the same as the original list") {
                assert.that(combinations.first(), equalTo(list))
            }
        }

        on("combinations with k = 3") {
            val combinations = list.combinations(3).toList()

            it("should return zero items") {
                assert.that(combinations, isEmpty)
            }
        }
    }
})

