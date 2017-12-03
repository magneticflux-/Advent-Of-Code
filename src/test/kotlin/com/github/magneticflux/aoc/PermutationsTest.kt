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
object PermutationsTest : Spek({
    given("a list of size 2") {
        val list = listOf("One", "Two")

        on("permutations with k = 0") {
            val permutations = list.permutations(0).toList()

            it("should return 1 item") {
                assert.that(permutations, hasSize(equalTo(1)))
            }

            it("should have the first element be an empty list") {
                assert.that(permutations.first(), isEmpty)
            }
        }

        on("permutations with k = 1") {
            val permutations = list.permutations(1).toList()

            it("should return 2 items") {
                assert.that(permutations, hasSize(equalTo(2)))
            }

            it("should have the first element be the same as the first list item") {
                assert.that(permutations.first()[0], equalTo(list.first()))
            }

            it("should have the second element be the same as the second list item") {
                assert.that(permutations.second()[0], equalTo(list.second()))
            }
        }

        on("permutations with k = 2") {
            val permutations = list.permutations(2).toList()

            it("should return 2 items") {
                assert.that(permutations, hasSize(equalTo(2)))
            }

            it("should have the first element be the same as the initial list") {
                assert.that(permutations.first(), equalTo(list))
            }

            it("should have the last element be the same as the reversed initial list") {
                assert.that(permutations.last(), equalTo(list.asReversed()))
            }
        }

        on("permutations with k = 3") {
            val permutations = list.permutations(3).toList()

            it("should return 0 items") {
                assert.that(permutations, isEmpty)
            }
        }
    }
    given("a list of size 4") {
        val list = listOf("One", "Two", "Three", "Four")

        on("permutations with k = 0") {
            val permutations = list.permutations(0).toList()

            it("should return 1 item") {
                assert.that(permutations, hasSize(equalTo(1)))
            }

            it("should have the first element be an empty list") {
                assert.that(permutations.first(), isEmpty)
            }
        }

        on("permutations with k = 1") {
            val permutations = list.permutations(1).toList()

            it("should return 4 items") {
                assert.that(permutations, hasSize(equalTo(4)))
            }

            it("should have the first element be the same as the first list item") {
                assert.that(permutations.first()[0], equalTo(list.first()))
            }

            it("should have the second element be the same as the second list item") {
                assert.that(permutations.second()[0], equalTo(list.second()))
            }

            it("should have the third element be the same as the third list item") {
                assert.that(permutations.third()[0], equalTo(list.third()))
            }

            it("should have the fourth element be the same as the fourth list item") {
                assert.that(permutations.fourth()[0], equalTo(list.fourth()))
            }
        }

        on("permutations with k = 2") {
            val permutations = list.permutations(2).toList()

            it("should return 12 items") {
                assert.that(permutations, hasSize(equalTo(12)))
            }
        }

        on("permutations with k = 4") {
            val permutations = list.permutations(4).toList()

            it("should return 24 items") {
                assert.that(permutations, hasSize(equalTo(24)))
            }

            it("should have the first element be the same as the initial list") {
                assert.that(permutations.first(), equalTo(list))
            }

            it("should have the last element be the same as the reversed initial list") {
                assert.that(permutations.last(), equalTo(list.asReversed()))
            }
        }
    }
})
