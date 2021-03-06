package com.igorwojda.tree.classic.traversal

import com.igorwojda.pochallenges.node
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private class BinarySearchTree<E : Comparable<E>> {
    var root: BinaryNode<E>? = null
        private set

    fun add(element: E) {
        val newNode = BinaryNode(element)

        if (root == null) {
            root = newNode
            return
        }

        var current: BinaryNode<E> = root ?: return

        while (true) {
            when {
                current.data == element -> {
                    return
                }
                element < current.data -> {
                    if (current.left == null) {
                        current.left = newNode
                        return
                    }

                    current.left?.let { current = it }
                }
                element > current.data -> {
                    if (current.right == null) {
                        current.right = newNode
                        return
                    }

                    current.right?.let { current = it }
                }
            }
        }
    }

    fun contains(element: E): Boolean {
        var current = root

        while (true) {
            if (current == null) {
                break
            } else if (current.data == element) {
                return true
            } else if (element < current.data) {
                current = current.left
            } else if (element > current.data) {
                current = current.right
            }
        }

        return false
    }

    fun isEmpty() = root == null

    fun traverseBreathFirst(): List<E> {
        val list = mutableListOf<E>()
        val queue = Queue<BinaryNode<E>>()
        if(isEmpty())
            return list
        else
            queue.add(root!!)
        while (queue.isNotEmpty()){

            val node = queue.remove() ?: break

            list.add(node.data)

            if(node.left != null)
                queue.add(node.left!!)

            node.right?.let { queue.add(it) }


        }
        return list
    }

    fun traverseDepthFirstPreOrder(): List<E> {
        fun traverse(node:BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()

            return listOf(node.data) + traverse(node.left) + traverse(node.right)
        }
        return traverse(root)
    }

    fun traverseDepthFirstInOrder(): List<E> {
        fun traverse(node:BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()

            return traverse(node.left) + listOf(node.data) + traverse(node.right)
        }
        return traverse(root)
    }

    fun traverseDepthFirstPostOrder(): List<E> {
        fun traverse(node:BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()
            return traverse(node.left) + traverse(node.right) + listOf(node.data)
        }
        return traverse(root)
    }

    fun traverseDepthFirstPreOrderReversed(): List<E> {
        fun traverse(node: BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()
            return listOf(node.data)+traverse(node.right)+traverse(node.left)

        }
        return traverse(root)
    }

    fun traverseDepthFirstInOrderReversed(): List<E> {
        fun traverse(node: BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()
            return traverse(node.right) + listOf(node.data)+traverse(node.left)
        }
        return traverse(root)
    }

    fun traverseDepthFirstPostOrderReverse(): List<E> {
        fun traverse(node:BinaryNode<E>?):List<E>{
            if(node == null)
                return emptyList()
            return traverse(node.right)+traverse(node.left)+listOf(node.data)
        }
        return traverse(root)
    }
}

private data class BinaryNode<E : Comparable<E>>(
    val data: E,
    var left: BinaryNode<E>? = null,
    var right: BinaryNode<E>? = null
)

/*
We can use queue as helper class to implement breath first traversal. This is not most optimal queue implementation,
however it's enough for this task. Check "Queue puzzle" solution for more details and more efficient queue
implementation.
*/
private class Queue<E> {
    private val list = mutableListOf<E>()

    fun add(element: E) {
        list.add(element)
    }

    fun remove() = if (list.isEmpty()) null else list.removeAt(0)

    fun peek() = list.firstOrNull()

    fun isEmpty() = list.isEmpty()

    fun isNotEmpty() = list.isNotEmpty()

    val size get() = list.size
}

private class Test {
    @Test
    fun `traverse breath first`() {
        getTree().traverseBreathFirst() shouldBeEqualTo listOf(
            'F',
            'B',
            'G',
            'A',
            'D',
            'I',
            'C',
            'E',
            'H'
        )
    }

    @Test
    fun `traverse depth first pre order`() {
        getTree().traverseDepthFirstPreOrder() shouldBeEqualTo listOf(
            'F',
            'B',
            'A',
            'D',
            'C',
            'E',
            'G',
            'I',
            'H'
        )
    }

    @Test
    fun `traverse depth first in order`() {
        getTree().traverseDepthFirstInOrder() shouldBeEqualTo listOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I'
        )
    }

    @Test
    fun `traverse depth first post order`() {
        getTree().traverseDepthFirstPostOrder() shouldBeEqualTo listOf(
            'A',
            'C',
            'E',
            'D',
            'B',
            'H',
            'I',
            'G',
            'F'
        )
    }

    @Test
    fun `traverse depth first pre order reversed`() {
        getTree().traverseDepthFirstPreOrderReversed() shouldBeEqualTo listOf(
            'F',
            'G',
            'I',
            'H',
            'B',
            'D',
            'E',
            'C',
            'A'
        )
    }

    @Test
    fun `traverse depth first in order reversed`() {
        getTree().traverseDepthFirstInOrderReversed() shouldBeEqualTo listOf(
            'I',
            'H',
            'G',
            'F',
            'E',
            'D',
            'C',
            'B',
            'A'
        )
    }

    @Test
    fun `traverse depth first post order reverse`() {
        getTree().traverseDepthFirstPostOrderReverse() shouldBeEqualTo listOf(
            'H',
            'I',
            'G',
            'E',
            'C',
            'D',
            'A',
            'B',
            'F'
        )
    }

    // ---------Tree------------
    //
    //           F
    //         /   \
    //        B     G
    //       / \     \
    //      A   D     I
    //         / \   /
    //        C   E H
    //
    // --------------------------
    private fun getTree() = BinarySearchTree<Char>().apply {
        add('F')
        add('B')
        add('A')
        add('D')
        add('C')
        add('E')
        add('G')
        add('I')
        add('H')
    }
}
