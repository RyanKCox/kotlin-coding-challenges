package com.igorwojda.list.sumzero

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun sumZero(list: List<Int>): Pair<Int, Int>? {
    var result :Pair<Int,Int>? = null
    list.forEachIndexed() { index,x->

        ((index+1)..list.lastIndex).forEach { y->
            if(x + list[y] == 0) {
                result = Pair(x, list[y])
                return result
            }
        }

    }
    return result
}

private class Test {
    @Test
    fun `sumZero empty list return null`() {
        sumZero(listOf()) shouldBeEqualTo null
    }

    @Test
    fun `sumZero 1, 2 return null`() {
        sumZero(listOf(1, 2)) shouldBeEqualTo null
    }

    @Test
    fun `sumZero 1, 2, 4, 7 return null`() {
        sumZero(listOf(1, 2, 4, 7)) shouldBeEqualTo null
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 3, 5 return Pair(-3, 3)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 3, 5)) shouldBeEqualTo Pair(-3, 3)
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 5 return Pair(-2, 2)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 5)) shouldBeEqualTo Pair(-2, 2)
    }
}
