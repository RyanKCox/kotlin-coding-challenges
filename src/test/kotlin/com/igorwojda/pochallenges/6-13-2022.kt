package com.igorwojda.pochallenges


import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

private fun medianSortedArray(num1:IntArray,num2:IntArray):Double
{
    if(num1.size > num2.size)
        return medianSortedArray(num2,num1)

    val num1Size = num1.size
    val num2Size = num2.size

    var start = 0
    var end = num1Size

    while (start <= end){
        val part1 = (start + end) /2
        val part2 = (num1Size + num2Size+1)/2 - part1

        val Left1 = if(part1 == 0) Int.MIN_VALUE else num1[part1-1]
        val Left2 = if(part2 == 0) Int.MIN_VALUE else num2[part2-1]

        val Right1 = if(part1 == num1Size) Int.MAX_VALUE else num1[part1]
        val Right2 = if(part2 == num2Size) Int.MAX_VALUE else num2[part2]

        if(Left1 <= Right2 && Left2 <= Right1){
            return if((num1Size + num2Size)%2 == 0)
                (max(Left1,Left2) + min(Right1, Right2))/2.0
            else
                max(Left1,Left2).toDouble()
        }
        else if(Left1 > Right2){
            end = part1 -1
        }
        else{
            start = part1 + 1
        }
    }
    throw java.lang.IllegalArgumentException()
}




private class `Weekly Test 6-14-22`(){

    @Test
    fun `MedianTest 1`(){
        val num1 = intArrayOf(1,3)
        val num2 = intArrayOf(2)
        medianSortedArray(num1,num2) shouldBeEqualTo 2.0
    }
    @Test
    fun `MedianTest 2`(){
        val num1 = intArrayOf(1,2)
        val num2 = intArrayOf(3,4)
        medianSortedArray(num1,num2) shouldBeEqualTo  2.5
    }
}