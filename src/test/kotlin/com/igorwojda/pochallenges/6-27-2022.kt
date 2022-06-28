package com.igorwojda.pochallenges

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun AxisWalk(vararg cases:Int):List<Int>{
    val results = mutableListOf<Int>()

    //for each case
    for(case in cases) {

        //set the count to the initial case value
        var count = case

        //store the min and max values
        var max = case
        var min = 0

        //and the direction
        var bToMax = true

        //Loop until max is lower or equal to min
        while(max>=min){
            //add the difference of max and min to count
            count += max-min

            //If we are going to the max, we've already hit the min value and vis versa
            if(bToMax)
                min++
            else
                max--
            //change our direction
            bToMax = !bToMax
        }

        results.add(count)
    }
    return results
}

class AxisWalkTests(){
    @Test
    fun `Test 1`(){
        AxisWalk(1,2) shouldBeEqualTo listOf(2,5)
    }
    @Test
    fun `Test 2`(){
        AxisWalk(3)shouldBeEqualTo listOf(9)
    }
}

private fun NameIsMine(vararg cases:Pair<String,String>):List<String>{

    //Results returned as a list of strings
    val results = mutableListOf<String>()

    //loop through the passed in pairs
    for (case in cases) {

        //Set our result to false initially
        var bYes = false

        //Find the long vs short name
        val shortName = if(case.first.length < case.second.length) case.first else case.second
        var longName = if(shortName == case.first) case.second else case.first

        //Filter the long name down to just the characters in short name, ignoring case
        longName = longName.filter { shortName.contains(it,true) }

        //Check if long name contains short name, ignoring case
        if(longName.contains(shortName,true))
            bYes = true

        //Check result and add to the results list
        if(bYes)
            results.add("YES")
        else
            results.add("NO")
    }

    return results
}

class NameIsMineTests(){
    @Test
    fun `Test 1`(){
        val name1 = "John"
        val name2 = "johana"
        NameIsMine(Pair(name1,name2)) shouldBeEqualTo listOf("YES")
    }
    @Test
    fun `Test 2`(){
        NameIsMine(
            Pair("john", "johanna"),
            Pair("ira","ira"),
            Pair("kayla", "Jayla")) shouldBeEqualTo listOf("YES","YES","NO")
    }
}