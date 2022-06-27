package com.igorwojda.linkedlist.doubly.base

import org.junit.jupiter.api.Test
import org.amshove.kluent.shouldBeEqualTo

private class DoublyLinkedList<E> : Iterable<Node<E>> {
    // implement
    var head:Node<E>? = null

    val size:Int
        get() {
            var count = 0
            var node = head
            while(node != null)
            {
                count++
                node = node.next
            }
            return count
        }
    val first:Node<E>?
        get() = head
    val last:Node<E>?
        get()  {
            var node = head
            while (node?.next != null){
                node = node.next
            }
            return node
        }

    fun insertFirst(value:E){
        val newNode = Node(value)

        newNode.next = head
        head?.prev = newNode
        head = newNode
    }

    fun clear(){
        head = null
    }
    fun removeFirst(){
        head = head?.next
        head?.prev = null
    }
    fun removeLast(){
        if(head?.next == null )
            head = null
        else
            last?.prev?.next = null
    }
    fun insertLast(value:E){
        if(head == null)
            insertFirst(value)
        else {
            val newNode = Node(value, last)
            last?.next = newNode
        }
    }
    fun getAt(index:Int):Node<E>?{
        var node = head
        var count = 0
        while(node != null && count != index){
            node = node.next
            count++
        }
        return node
    }
    fun setAt(value:E,index:Int){
        getAt(index)?.data = value
    }
    fun removeAt(index:Int){

        if(index == 0 ) {
            head = head?.next
            head?.prev = null
        }
        else {
            val node = getAt(index)
            node?.next?.prev = node?.prev
            node?.prev?.next = node?.next
        }
    }
    fun insertAt(value:E,index:Int){

        val newNode = Node(value)
        if(head == null || index == 0){
            head?.prev = newNode
            newNode.next = head
            head = newNode
        }
        else if(index >= size){
            newNode.prev = last
            last?.next = newNode
        }else
        {
            val node = getAt(index)
            newNode.next = node
            newNode.prev = node?.prev

            node?.prev?.next = newNode
            node?.prev = newNode
        }
    }

    override fun iterator() = object  : Iterator<Node<E>>{
        var node = head
        override fun hasNext(): Boolean {
            return (node!= null)
        }

        override fun next(): Node<E> {
            val currentNode = node
            node = node?.next
            return currentNode!!
        }

    }
    operator fun plus(list:DoublyLinkedList<E>):DoublyLinkedList<E>{
        val newList = DoublyLinkedList<E>()
        this.forEach { newList.insertLast(it.data) }
        list.forEach { newList.insertLast(it.data) }
        return newList
    }


}

private data class Node<T>(
    var data: T,
    var prev: Node<T>? = null,
    var next: Node<T>? = null
){
    override fun toString(): String {
        return data.toString()
    }
}


private class Test {
    @Test
    fun `when list is created head node is null`() {
        DoublyLinkedList<Int>().apply {
            head shouldBeEqualTo null
        }
    }

    @Test
    fun `append a node to the start of the list`() {
        DoublyLinkedList<Int>().apply {
            insertFirst(1)
            head?.data shouldBeEqualTo 1
            head?.next shouldBeEqualTo null
            insertFirst(2)
            head?.data shouldBeEqualTo 2
            head?.next?.prev shouldBeEqualTo head
            head?.next?.data shouldBeEqualTo 1
        }
    }

    @Test
    fun `return the number of items in the linked list`() {
        DoublyLinkedList<Int>().apply {
            size shouldBeEqualTo 0
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            size shouldBeEqualTo 4
        }
    }

    @Test
    fun `return the first element`() {
        DoublyLinkedList<Int>().apply {
            insertFirst(1)
            first?.data shouldBeEqualTo 1
            insertFirst(2)
            first?.data shouldBeEqualTo 2
        }
    }

    @Test
    fun `return the last element`() {
        DoublyLinkedList<Int>().apply {
            insertFirst(2)
            last?.data shouldBeEqualTo 2
            last?.next shouldBeEqualTo null
            insertFirst(1)
            last?.data shouldBeEqualTo 2
            last?.next shouldBeEqualTo null
        }
    }

    @Test
    fun `empty the list`() {
        DoublyLinkedList<Int>().apply {
            size shouldBeEqualTo 0
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            size shouldBeEqualTo 4
            clear()
            size shouldBeEqualTo 0
        }
    }

    @Test
    fun `remove the first node when the list has a size of one`() {
        DoublyLinkedList<String>().apply {
            insertFirst("a")
            removeFirst()
            size shouldBeEqualTo 0
            first shouldBeEqualTo null
        }
    }

    @Test
    fun `remove the first node when the list has a size of three`() {
        DoublyLinkedList<String>().apply {
            insertFirst("c")
            insertFirst("b")
            insertFirst("a")
            removeFirst()
            size shouldBeEqualTo 2
            first?.data shouldBeEqualTo "b"
            first?.prev shouldBeEqualTo null
            removeFirst()
            size shouldBeEqualTo 1
            first?.data shouldBeEqualTo "c"
            first?.prev shouldBeEqualTo null
        }
    }

    @Test
    fun `remove the last node when list is empty`() {
        DoublyLinkedList<Any>().apply {
            removeLast()
        }
    }

    @Test
    fun `remove the last node when list is length 1`() {
        DoublyLinkedList<String>().apply {
            insertFirst("a")
            removeLast()
            head shouldBeEqualTo null
        }
    }

    @Test
    fun `remove the last node when list is length 2`() {
        DoublyLinkedList<String>().apply {
            insertFirst("b")
            insertFirst("a")
            removeLast()
            size shouldBeEqualTo 1
            head?.data shouldBeEqualTo "a"
            last?.next shouldBeEqualTo null
        }
    }

    @Test
    fun `remove the last node when list is length 3`() {
        DoublyLinkedList<String>().apply {
            insertFirst("c")
            insertFirst("b")
            insertFirst("a")
            removeLast()
            size shouldBeEqualTo 2
            last?.data shouldBeEqualTo "b"
            last?.next shouldBeEqualTo null
        }
    }

    @Test
    fun `add to the end of the list`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            size shouldBeEqualTo 1
            head?.data shouldBeEqualTo "a"

            insertLast("b")
            size shouldBeEqualTo 2
            last?.data shouldBeEqualTo "b"
            last?.prev?.data shouldBeEqualTo "a"
            head?.data shouldBeEqualTo "a"
        }
    }

    @Test
    fun `return the node at given index`() {
        DoublyLinkedList<Char>().apply {
            getAt(10) shouldBeEqualTo null

            insertLast('A')
            insertLast('B')
            insertLast('C')
            insertLast('D')

            getAt(0)?.data shouldBeEqualTo 'A'
            getAt(1)?.data shouldBeEqualTo 'B'
            getAt(2)?.data shouldBeEqualTo 'C'
            getAt(3)?.data shouldBeEqualTo 'D'
            getAt(4)?.data shouldBeEqualTo null
        }
    }

    @Test
    fun `set node data at index 0`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            setAt("new", 0)
            getAt(0)?.data shouldBeEqualTo "new"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(2)?.data shouldBeEqualTo "c"
        }
    }

    @Test
    fun `set node data at index 1`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            setAt("new", 1)
            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "new"
            getAt(2)?.data shouldBeEqualTo "c"
        }
    }

    @Test
    fun `set node data at index 2`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            setAt("new", 2)
            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(2)?.data shouldBeEqualTo "new"
        }
    }

    @Test
    fun `set node data at non existing index`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            setAt("new", 3)
            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(2)?.data shouldBeEqualTo "c"
        }
    }

    @Test
    fun `remove from empty list`() {
        DoublyLinkedList<Int>().apply {
            removeAt(0)
            removeAt(1)
            removeAt(2)
        }
    }

    @Test
    fun `remove with index out of bounds`() {
        DoublyLinkedList<String>().apply {
            insertFirst("a")
            removeAt(1)
        }
    }

    @Test
    fun `remove the first node`() {
        DoublyLinkedList<Int>().apply {
            insertLast(1)
            insertLast(2)
            insertLast(3)
            insertLast(4)
            getAt(0)?.data shouldBeEqualTo 1
            removeAt(0)
            getAt(0)?.data shouldBeEqualTo 2
            getAt(0)?.prev shouldBeEqualTo null
            getAt(0)?.next?.data shouldBeEqualTo 3
            getAt(1)?.data shouldBeEqualTo 3
        }
    }

    @Test
    fun `remove the node at given index`() {
        DoublyLinkedList<Int>().apply {
            insertLast(1)
            insertLast(2)
            insertLast(3)
            insertLast(4)
            getAt(1)?.data shouldBeEqualTo 2
            removeAt(1)
            getAt(0)?.data shouldBeEqualTo 1
            getAt(0)?.next?.data shouldBeEqualTo 3
            getAt(1)?.data shouldBeEqualTo 3
            getAt(1)?.prev?.data shouldBeEqualTo 1
            getAt(1)?.next?.data shouldBeEqualTo 4
            getAt(2)?.data shouldBeEqualTo 4
            getAt(2)?.prev?.data shouldBeEqualTo 3

        }
    }

    @Test
    fun `remove the last node`() {
        DoublyLinkedList<Int>().apply {
            insertLast(1)
            insertLast(2)
            insertLast(3)
            insertLast(4)
            getAt(3)?.data shouldBeEqualTo 4
            removeAt(3)
            getAt(1)?.data shouldBeEqualTo 2
            getAt(1)?.next?.data shouldBeEqualTo 3
            getAt(2)?.data shouldBeEqualTo 3
            getAt(2)?.next shouldBeEqualTo null
            getAt(2)?.prev?.data shouldBeEqualTo 2
            getAt(3) shouldBeEqualTo null
        }
    }

    @Test
    fun `insert a new node with data at index 0 when the list is empty`() {
        DoublyLinkedList<String>().apply {
            insertAt("hi", 0)
            first?.data shouldBeEqualTo "hi"
            first?.prev shouldBeEqualTo null
            first?.next shouldBeEqualTo null
        }
    }

    @Test
    fun `insert a new node with data at index 0 when the list has elements`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            insertAt("hi", 0)
            getAt(0)?.data shouldBeEqualTo "hi"
            getAt(0)?.prev shouldBeEqualTo null
            getAt(0)?.next?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "a"
            getAt(1)?.prev?.data shouldBeEqualTo "hi"
            getAt(2)?.data shouldBeEqualTo "b"
            getAt(3)?.data shouldBeEqualTo "c"
        }
    }

    @Test
    fun `insert a new node with data at a middle index`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertLast("c")
            insertLast("d")
            insertAt("hi", 2)
            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(1)?.next?.data shouldBeEqualTo "hi"
            getAt(2)?.prev?.data shouldBeEqualTo "b"
            getAt(2)?.data shouldBeEqualTo "hi"
            getAt(2)?.next?.data shouldBeEqualTo "c"
            getAt(3)?.prev?.data shouldBeEqualTo "hi"
            getAt(3)?.data shouldBeEqualTo "c"
            getAt(4)?.data shouldBeEqualTo "d"
        }
    }

    @Test
    fun `inserts a new node with data at a last index`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertAt("hi", 2)
            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(1)?.next?.data shouldBeEqualTo "hi"
            getAt(2)?.prev?.data shouldBeEqualTo "b"
            getAt(2)?.data shouldBeEqualTo "hi"
            getAt(2)?.next?.data shouldBeEqualTo null
        }
    }

    @Test
    fun `insert a new node when index is out of bounds`() {
        DoublyLinkedList<String>().apply {
            insertLast("a")
            insertLast("b")
            insertAt("hi", 30)

            getAt(0)?.data shouldBeEqualTo "a"
            getAt(1)?.data shouldBeEqualTo "b"
            getAt(1)?.next?.data shouldBeEqualTo "hi"
            getAt(2)?.data shouldBeEqualTo "hi"
            getAt(2)?.prev?.data shouldBeEqualTo "b"
        }
    }

    @Test
    fun `sum all the nodes`() {
        DoublyLinkedList<Int>().apply {
            insertLast(1)
            insertLast(2)
            insertLast(3)
            insertLast(4)

            sumBy { it.data } shouldBeEqualTo 10
        }
    }

    @Test
    fun `add two empty lists`() {
        val l1 = DoublyLinkedList<Int>()
        val l2 = DoublyLinkedList<Int>()
        val result = l1 + l2

        result.size shouldBeEqualTo 0
    }

    @Test
    fun `add two lists`() {
        val l1 = DoublyLinkedList<Int>().apply {
            insertLast(1)
            insertLast(2)
            insertLast(3)
        }
        val l2 = DoublyLinkedList<Int>().apply {
            insertLast(4)
            insertLast(5)
            insertLast(6)
            insertLast(7)
        }
        val result = l1 + l2

        result.apply {
            size shouldBeEqualTo 7
            getAt(0)?.data shouldBeEqualTo 1
            getAt(1)?.data shouldBeEqualTo 2
            getAt(2)?.data shouldBeEqualTo 3
            getAt(3)?.data shouldBeEqualTo 4
            getAt(4)?.data shouldBeEqualTo 5
            getAt(5)?.data shouldBeEqualTo 6
            getAt(6)?.data shouldBeEqualTo 7
        }
    }
}
