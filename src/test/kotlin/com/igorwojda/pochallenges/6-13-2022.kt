package com.igorwojda.pochallenges


import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

/**
 * Median of sorted array challenge
 */
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

        val left1 = if(part1 == 0) Int.MIN_VALUE else num1[part1-1]
        val left2 = if(part2 == 0) Int.MIN_VALUE else num2[part2-1]

        val right1 = if(part1 == num1Size) Int.MAX_VALUE else num1[part1]
        val right2 = if(part2 == num2Size) Int.MAX_VALUE else num2[part2]

        if(left1 <= right2 && left2 <= right1){
            return if((num1Size + num2Size)%2 == 0)
                (max(left1,left2) + min(right1, right2))/2.0
            else
                max(left1,left2).toDouble()
        }
        else if(left1 > right2){
            end = part1 -1
        }
        else{
            start = part1 + 1
        }
    }
    throw java.lang.IllegalArgumentException()
}
private class MedianTest {

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

/**
 * Link List Merge Challenge
 */

class Link(val value:Int){
//    var prev:Link? = null
    var next:Link? = null

    fun toList():List<Int>{
        val list = mutableListOf(value)
        var pointer:Link? = this
        while(pointer!!.next != null){
            list.add(pointer.next!!.value)
            pointer = pointer.next
        }
        return list
    }
}
fun linkListOf(vararg elements:Int):Link?{
    var header:Link? = null

    if(elements.isNotEmpty()){
        var prev:Link? = null
        elements.forEach { value->
            if(header == null){
                header = Link(value)
                prev = header
            }
            else{
                prev!!.next = Link(value)
                prev = prev!!.next
            }
        }
    }

    return header
}


private fun mergeLinkListSort(vararg elements:Link?):Link?{
    var header:Link? = null
    val linkLists = mutableListOf<Link?>()

    elements.forEach { list ->
        linkLists.add(list)
    }

    var position:Link? = null
    var bContinue = true
    while (bContinue){

        bContinue = false
        var min = Int.MAX_VALUE
        var minIndex = 0

        linkLists.forEachIndexed { index,list->
            if(list != null){

                bContinue = true

                if(list.value < min) {
                    min = list.value
                    minIndex = index
                }
            }
        }
        if(bContinue){
            if(header == null) {
                header = Link(min)
                position = header
            }
            else{
                position!!.next = Link(min)
                position = position.next
            }
            linkLists[minIndex] = linkLists[minIndex]!!.next
        }


    }
    return header
}
private class LinkListSortTest {

    @Test
    fun `LinkListTest 1`(){
        val list1 = linkListOf(1,4,5)
        val list2 = linkListOf(1,3,4)
        val list3 = linkListOf(2,6)

        mergeLinkListSort(list1,list2,list3)?.toList() shouldBeEqualTo listOf(1,1,2,3,4,4,5,6)
    }

    @Test
    fun `LinkListTest 2`(){
        val list1 = linkListOf(1,6,7)
        val list2 = linkListOf(3,9,20)
        mergeLinkListSort(list1,list2)?.toList() shouldBeEqualTo listOf(1,3,6,7,9,20)
    }
    @Test
    fun `LinkListTest 3`(){
        val list1 = linkListOf(12,41,322)
        mergeLinkListSort(list1)?.toList() shouldBeEqualTo listOf(12,41,322)
    }
    @Test
    fun `LinkListTest 4`(){
        mergeLinkListSort()?.toList() shouldBeEqualTo null
    }
    @Test
    fun `LinkListTest 5`(){
        val list1 = linkListOf(1,6,9,38,234,999)
        val list2 = linkListOf(11,15,56)
        val list3 = linkListOf(167,452,888,1234)
        val list4 = linkListOf(25,76,786)

        mergeLinkListSort(list1,list2,list3,list4)?.toList() shouldBeEqualTo listOf(1,6,9,11,15,25,38,56,76,167,234,452,786,888,999,1234)
    }
}