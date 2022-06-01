package com.igorwojda.string.reverse

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun reverse(str: String): String {

    if (str.isNotEmpty())
    {
        return str.last() + reverse(str.dropLast(1))
    }

    return ""
}
fun test (activities:Array<Int>, killActivities:Array<Int>):Int {

    var result = 0
    var actList = activities.toMutableList()
    var index = 0

    killActivities.forEach { kill->
        if(actList.indexOf(kill) == 0){
            result++
            actList.remove(kill)
        } else {
            if(actList.indexOf(kill) < index){
                result++
                actList.remove(kill)
                index--
            } else {
                index = actList.indexOf(kill)
                result += 2 * index + 1
                actList.remove(kill)
                index--
            }
        }

    }


    return result;
}
private class CodingTest{
    @Test
    fun `codeChallenge`(){
        test(
            arrayOf(3,1,2),
            arrayOf(3,2,1)
        ) shouldBeEqualTo 5
    }
    @Test
    fun `codeChallenge2`(){
        test(
            arrayOf(11,17,4,7,12,10,1,6,14,16,3,15,9,5,13,8,18,2),
            arrayOf(18,15,9,4,7,1,14,13,11,12,8,2)
        ) shouldBeEqualTo 56
    }
    @Test
    fun `codeChallenge3`(){
        test(
            arrayOf(2,1,7,3,4,5,6),
            arrayOf(3,1)
        ) shouldBeEqualTo 8
    }
}
private class Test {
    @Test
    fun `Reverse of "abcd" is "dcba"`() {
        reverse("abcd") shouldBeEqualTo "dcba"
    }

    @Test
    fun `Reverse of "aabbccdd" is "ddccbbaa"`() {
        reverse("aabbccdd") shouldBeEqualTo "ddccbbaa"
    }
}
