package com.igorwojda.string.ispalindrome.permutation

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.math.sin

private fun isPermutationPalindrome(str: String): Boolean {

    var list = str.groupingBy { it }.eachCount()
    var singles = list.filter { it.value % 2 == 1 }

    return (singles.size <= 1)

}

private class Test {
    @Test
    fun `"gikig" is a palindrome`() {
        isPermutationPalindrome("gikig") shouldBeEqualTo true
    }

    @Test
    fun `"ookvk" is a palindrome`() {
        isPermutationPalindrome("ookvk") shouldBeEqualTo true
    }

    @Test
    fun `"sows" is not a palindrome`() {
        isPermutationPalindrome("sows") shouldBeEqualTo false
    }

    @Test
    fun `"tami" is not a palindrome`() {
        isPermutationPalindrome("tami") shouldBeEqualTo false
    }
}
