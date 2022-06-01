package com.igorwojda.string.capitalizesentence

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.lang.Float.max
import kotlin.math.max

private fun capitalizeSentence(str: String): String {

    //new String function capitalizing each word
    //split separates string at every char passed in
    //joinToString adds each word back with the passed in char
    //it.capitalize will capitalize each word that is created from split
    fun String.capitalizeEachWord():String =
        split(" ").joinToString(" ") { it.capitalize() }


    return str.capitalizeEachWord()
}

fun resumeTrick(list:List<Float>):Int{
    var result = 0
    val tempList = List(list.size){ 1 }.toMutableList()

    (list.lastIndex downTo 0).forEach { x->
        ((x+1)..list.lastIndex).forEach { y->
            if(list[x] < list[y])
                tempList[x] = max(tempList[x],1+tempList[y])
        }
    }

    return tempList.maxOf { it }
}

private class Test {

    @Test
    fun `resumeTrick_answer 3`(){

        var list = listOf( 2f,20f,15f,14f,16f )
        resumeTrick(list) shouldBeEqualTo 3
    }


    @Test
    fun `"flower" is capitalized to "Flower"`() {
        capitalizeSentence("flower") shouldBeEqualTo "Flower"
    }

    @Test
    fun `"this is a house" is capitalised to "This Is A House"`() {
        capitalizeSentence("this is a house") shouldBeEqualTo "This Is A House"
    }
}
