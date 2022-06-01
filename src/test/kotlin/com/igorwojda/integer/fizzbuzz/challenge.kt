package com.igorwojda.integer.fizzbuzz

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun fizzBuzz(n: Int): List<String> {

    var fizz = "Fizz"
    var buzz = "Buzz"
    var list = mutableListOf<String>()

    (1 .. n).forEach{

        var str = ""

        if(it % 3 != 0 && it % 5 != 0){
            str = it.toString()
        } else {

            if( it % 3 == 0){
                str += fizz
            }
            if(it % 5 == 0){
                str += buzz
            }

        }


        list.add(str)
    }

    return list

}

private class Test {

    @Test
    fun `Calling fizzbuzz with "5" returns list with 5 items`() {
        fizzBuzz(5) shouldBeEqualTo listOf("1", "2", "Fizz", "4", "Buzz")
    }

    @Test
    fun `Calling fizzbuzz with 16 returns out the correct values`() {

        val list = listOf(
            "1", "2", "Fizz", "4", "Buzz", "Fizz",
            "7", "8", "Fizz", "Buzz", "11", "Fizz",
            "13", "14", "FizzBuzz", "16"
        )

        fizzBuzz(16) shouldBeEqualTo list
    }
}
