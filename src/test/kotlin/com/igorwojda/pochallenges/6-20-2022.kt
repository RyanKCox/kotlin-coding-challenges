package com.igorwojda.pochallenges

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

private fun squaresInRange(a:Int,b:Int):Int{
    var result = 0

    var sqrB = floor(sqrt(b.toDouble())).toInt()
    var sqrA = ceil( sqrt(a.toDouble())).toInt()

    result = sqrB-sqrA + 1

    return result
}

private class SquareRangeTest(){

    @Test
    fun `Test 1`(){
        squaresInRange(24,49) shouldBeEqualTo 3
    }
    @Test
    fun `Test 2`(){
        squaresInRange(3,9) shouldBeEqualTo 2
    }
    @Test
    fun `Test 3`(){
        squaresInRange(17,24) shouldBeEqualTo 0
    }
    @Test
    fun `Test 4`(){
        squaresInRange(1,1000000000) shouldBeEqualTo 31622
    }
    @Test
    fun `Test 5`(){
        squaresInRange(4,9) shouldBeEqualTo 2
    }
}