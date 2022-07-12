package com.igorwojda.pochallenges

import com.igorwojda.string.reverse.test
import org.amshove.kluent.should
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

//linklist class
private class linkList<E> : Iterable<Node<E>>{
    var head:Node<E>? = null

    fun add(data:E){

        if(head == null){
            head = Node(data)
        }
        else {

            var position: Node<E>? = head
            while (position?.next != null) {
                position = position.next
            }
            position?.next = Node(data)
        }
    }
    fun add(node:Node<E>){

        if(head == null){
            head = node
        }
        else {

            var position: Node<E>? = head
            while (position?.next != null) {
                position = position.next
            }
            position?.next = node
        }
    }
    fun getNodeAt(index:Int):Node<E>?{
        var count = 0
        var currentNode :Node<E>? = head
        while (count < index){
            count++
            currentNode = currentNode?.next
        }
        return currentNode

    }


    override fun iterator() = object : Iterator<Node<E>> {
        var node = head
        override fun hasNext(): Boolean {
            return (node != null)
        }

        override fun next(): Node<E> {
            val current = node
            node = node?.next
            return  current!!
        }
    }
}

private fun <E> linkListOf(vararg elements:E):linkList<E>{
    val list = linkList<E>()

    elements.forEach { ele->
        list.add(ele)
    }
    return list
}

private data class Node<E>(
    var data: E,
    var next : Node<E>? = null
){
    override fun toString(): String {
        return data.toString()
    }
}

private fun Intersection(list1:linkList<*>,list2:linkList<*>):Node<*>?{

    list1.forEach { index1->

        list2.forEach { index2->
            if(index1 == index2)
                return index1
        }
    }
    return null
}

class `Intersection Test`{

    @Test
    fun `Test`(){
        val list1 = linkListOf(1,4,5,7)
        val list2 = linkListOf(2,3,7)

        Intersection(list1,list2) shouldBeEqualTo Node(7)
    }
}

private fun LoopDetection(list:linkList<*>):Node<*>?{
    val nodeList = mutableListOf<Node<*>>()
    list.forEach { node->
        if(nodeList.contains(node))
            return node
        else
            nodeList.add(node)
    }


    return null
}

class `Loop Detection Test`{
    @Test
    fun `Test`(){
        val list = linkListOf(1,3,4,6,7,8)
        val testNode = list.getNodeAt(2)!!
        list.add(testNode)

        LoopDetection(list) shouldBeEqualTo testNode
    }
    @Test
    fun `Test2`(){
        val list = linkListOf(1,3,4,6,7,8)
//        val testNode = list.getNodeAt(2)!!
//        list.add(testNode)

        LoopDetection(list) shouldBeEqualTo null
    }
}