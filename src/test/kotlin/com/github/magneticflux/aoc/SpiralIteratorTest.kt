package com.github.magneticflux.aoc

import com.github.magneticflux.aoc.geom.IntPoint2D
import com.google.common.math.IntMath
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.math.RoundingMode

/**
 * Created by Mitchell Skaggs on 12/3/2017.
 */
object SpiralSequenceTest : Spek({
    given("default settings") {
        on("initial spiral iterated over") {
            val spiral = spiralSequenceOf().iterator()

            it("should have the first element at (0, 0)") {
                assert.that(spiral.next(), equalTo(IntPoint2D(0, 0)))
            }

            it("should have the second element at (1, 0)") {
                assert.that(spiral.next(), equalTo(IntPoint2D(1, 0)))
            }

            it("should have the third element at (1, 1)") {
                assert.that(spiral.next(), equalTo(IntPoint2D(1, 1)))
            }

            it("should have the fourth element at (0, 1)") {
                assert.that(spiral.next(), equalTo(IntPoint2D(0, 1)))
            }
        }
        on("large spiral iterated over") {
            val spiral = spiralSequenceOf()

            spiral.take(10000).forEachIndexed { index, point ->
                val tileNumber = index + 1
                if (tileNumber.isOdd() && tileNumber.isSquare()) {
                    it("should have an odd, square number '$tileNumber' (at index $index)on the down-right diagonal") {
                        assert.that(point.x, equalTo(-point.y))
                    }
                }

                if (index.isEven() && index.isSquare()) {
                    it("should have an odd, square-plus-one number '$tileNumber' (at index $index) on the up-left diagonal") {
                        assert.that(point.x, equalTo(-point.y))
                    }
                }
            }
        }
    }
})


private fun Int.isOdd(): Boolean {
    return !this.isEven()
}

private fun Int.isEven(): Boolean {
    return this % 2 == 0
}

private fun Int.isSquare(): Boolean {
    return this.sqrtOrNull() != null
}

private fun Int.sqrtOrNull(): Int? {
    return try {
        IntMath.sqrt(this, RoundingMode.UNNECESSARY)
    } catch (e: ArithmeticException) {
        null
    }
}
