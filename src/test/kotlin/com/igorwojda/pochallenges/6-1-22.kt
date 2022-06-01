package com.igorwojda.pochallenges

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun sortAscDesc(unSorted: IntArray,bAsc:Boolean):IntArray{

    var sortedList = mutableListOf<Int>()
    unSorted.forEach { value->
        var temp = value
            .toString()
            .split("")
            .let {
                if(bAsc) it.sorted() else it.sortedDescending()
            }
            .joinToString("")
            .toInt()
        sortedList.add(temp)
    }


    return sortedList.toIntArray()
}
private fun canPartition(input:IntArray):Boolean{

    var total = 1
    input.forEach {
        total *= it
    }
    input.forEach {
        if( it == total/it)
            return true
    }

    return false
}

private class Test {

    @Test
    fun `Sort Asc`(){
        val testArray = intArrayOf(515,341,98,44,211)
        val answers = intArrayOf(155,134,89,44,112)

        sortAscDesc(testArray,true) shouldBeEqualTo answers
    }
    @Test
    fun `Sort Desc`(){
        val testArray = intArrayOf(515,341,98,44,211)
        val answers = intArrayOf(551,431,98,44,211)

        sortAscDesc(testArray,false) shouldBeEqualTo answers
    }

    @Test
    fun `Partition true 1`(){
        val testArray = intArrayOf(2, 8, 4, 1)

        canPartition(testArray) shouldBeEqualTo true
    }
    @Test
    fun `Partition false 1`(){
        val testArray = intArrayOf(-1,-10,1,-2,20)
        canPartition(testArray) shouldBeEqualTo false
    }
    @Test
    fun `Partition true 2`(){
        val testArray = intArrayOf(-1,-20,5,-1,-2,2)
        canPartition(testArray) shouldBeEqualTo true
    }
}