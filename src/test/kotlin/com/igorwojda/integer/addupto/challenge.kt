package com.igorwojda.integer.addupto

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun addUpTo(n: Int): Int {

    return if( n >0) n + addUpTo(n-1) else n

}

private class Test {
    @Test
    fun `add up to 1`() {
        addUpTo(1) shouldBeEqualTo 1
    }

    @Test
    fun `add up to 3`() {
        addUpTo(3) shouldBeEqualTo 6
    }

    @Test
    fun `add up to 10`() {
        addUpTo(10) shouldBeEqualTo 55
    }
}
