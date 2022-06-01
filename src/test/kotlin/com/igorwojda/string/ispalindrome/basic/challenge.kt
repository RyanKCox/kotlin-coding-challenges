package com.igorwojda.string.ispalindrome.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun isPalindrome(str: String): Boolean {

    return (str == str.reversed())
}
private fun intPalindromeByString(num:Int):Boolean{
    val reversed = num.toString().reversed().toInt()

    return (num == reversed)
}
private fun intPalindrome(num:Int):Boolean{
    var original = num
    var reversed = 0
    while(original != 0){
        var remainder = original % 10
        reversed = reversed * 10 + remainder
        original /= 10
    }
    return (reversed == num)
}

private class Test {
    @Test
    fun intPalindrome_131_true(){
        intPalindrome(131) shouldBeEqualTo true
    }
    @Test
    fun intPalindrome_1312_false(){
        intPalindrome(1312) shouldBeEqualTo false
    }



    @Test
    fun `"aba" is a palindrome`() {
        isPalindrome("aba") shouldBeEqualTo true
    }

    @Test
    fun `" aba" is not a palindrome`() {
        isPalindrome(" aba") shouldBeEqualTo false
    }

    @Test
    fun `"aba " is not a palindrome`() {
        isPalindrome("aba ") shouldBeEqualTo false
    }

    @Test
    fun `"greetings" is not a palindrome`() {
        isPalindrome("greetings") shouldBeEqualTo false
    }

    @Test
    fun `"1000000001" a palindrome`() {
        isPalindrome("1000000001") shouldBeEqualTo true
    }

    @Test
    fun `"Fish hsif" is not a palindrome`() {
        isPalindrome("Fish hsif") shouldBeEqualTo false
    }

    @Test
    fun `"pennep" a palindrome`() {
        isPalindrome("pennep") shouldBeEqualTo true
    }
}
