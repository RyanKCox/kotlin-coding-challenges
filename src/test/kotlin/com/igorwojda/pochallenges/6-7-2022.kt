package com.igorwojda.pochallenges


import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test


private fun secondMax(sets:List<List<Int>>):List<Int>{

    val result = mutableListOf<Int>()

    sets.forEach { set->

//        Built In list sorting
//        result.add(set.sorted()[1])

//        Without built-in function
        var max = 0
        var secondMax = 1
        (1 until set.size).forEach { index->
            if(set[index] > set[max]){
                secondMax = max
                max = index
            }
            else if( set[index] > set[secondMax]){
                secondMax = index
            }
        }
        result.add(set[secondMax])

    }

    return result
}

private fun digitStringCount(sString:String):Int{
    var result = 0

    sString.toCharArray().forEach { char->

        if(char in '1'..'9'){
            result += (char.toInt() - '0'.toInt())
        }

    }

    return result
}

private class `6-7-22 test`(){

    @Test
    fun `Test secondMax 1`(){
        val testSets = mutableListOf<List<Int>>()
        testSets.add(listOf(1,2,3))
        testSets.add(listOf(10,15,5))
        testSets.add(listOf(100,999,500))

        secondMax(testSets) shouldBeEqualTo listOf(2,10,500)
    }
    @Test
    fun `Test secondMax 2`(){
        val testSets = mutableListOf<List<Int>>()
        testSets.add(listOf(3,2,1))
        testSets.add(listOf(10,15,5))
        testSets.add(listOf(100,999,500))

        secondMax(testSets) shouldBeEqualTo listOf(2,10,500)
    }

    @Test
    fun `Test digitStringCount 1`(){
        digitStringCount("ab1231da") shouldBeEqualTo 7
    }
    @Test
    fun `Test digitStringCount 2`(){
        digitStringCount("asd2as5aaw3e7") shouldBeEqualTo 17
    }


}