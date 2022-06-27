package com.igorwojda.tree.classic.levelwidth

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun levelWidth(tree: Node): List<Int> {
    val result = mutableListOf<Int>()

    val nodes = mutableListOf(tree)

    var rowCount = 0
    var rowTotal = 1
    var nextRowTotal = 0

    while (nodes.isNotEmpty()){
        val current =nodes.removeAt(0)
        rowCount++
        nodes.addAll(current.children)
        nextRowTotal += current.children.size

        if(rowCount == rowTotal){
            result.add(rowCount)
            rowCount = 0
            rowTotal = nextRowTotal
            nextRowTotal = 0
        }

    }

    return result
}

private class Test {
    @Test
    fun `levelWidth returns 1, 2`() {
        // ----------Tree------------
        //
        //           A
        //         /   \
        //        B     C
        //
        // --------------------------

        val root = Node("A")
        root.add("B")
        root.add("C")

        levelWidth(root) shouldBeEqualTo listOf(1, 2)
    }

    @Test
    fun `levelWidth returns 1, 3, 2`() {
        // ----------Tree------------
        //
        //           A
        //         / | \
        //        B  C  D
        //        |     |
        //        E     F
        //
        // --------------------------

        val root = Node("A")
        val nodeB = Node("B")
        root.add(nodeB)
        root.add("C")
        val nodeD = Node("D")
        root.add(nodeD)
        nodeB.add("E")
        nodeD.add("F")

        levelWidth(root) shouldBeEqualTo listOf(1, 3, 2)
    }

    @Test
    fun `levelWidth returns 1, 1, 2, 1`() {
        // ------------Tree----------
        //
        //             A
        //            /
        //           B
        //          / \
        //         C   D
        //        /
        //       E
        //
        // --------------------------

        val root = Node("A")
        val nodeB = Node("B")
        root.add(nodeB)
        val nodeC = Node("C")
        nodeB.add(nodeC)
        nodeB.add("D")
        nodeC.add("E")

        levelWidth(root) shouldBeEqualTo listOf(1, 1, 2, 1)
    }
}

private class Node(val data: String, val children: MutableList<Node> = mutableListOf()) {
    fun add(data: String) {
        add(Node(data))
    }

    fun add(node: Node) {
        children.add(node)
    }

    fun remove(data: String) {
        children.removeAll { it.data == data }
    }
}
