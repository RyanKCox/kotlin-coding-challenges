package com.igorwojda.matrix.findrectangle

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun findRectangle(image: List<List<Int>>): List<Int>? {
    var list = mutableListOf<Int>()

    var rowIndex = 0
    var columnIndex = 0

    image.forEachIndexed{ index, row->
        if(row.contains(0)){
            if(list.isEmpty()) {
                list.add(index)
                list.add(row.indexOfFirst { it == 0 })
                columnIndex = row.indexOfLast { it == 0 }
                if (index == image.size - 1) {
                    rowIndex = index
                    list.add(rowIndex)
                    list.add(columnIndex)
                    return list
                }
            }
            rowIndex = index
        }

    }
    list.add(rowIndex)
    list.add(columnIndex)



    return list
}

private class Test {
    @Test
    fun `find rectangle in image 1`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(2, 3, 3, 5)
    }

    @Test
    fun `find rectangle in image 2`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 0)
        )

        findRectangle(image) shouldBeEqualTo listOf(4, 6, 4, 6)
    }

    @Test
    fun `find rectangle in image 3`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 0, 0),
            listOf(1, 1, 1, 1, 1, 0, 0)
        )

        findRectangle(image) shouldBeEqualTo listOf(3, 5, 4, 6)
    }

    @Test
    fun `find rectangle in image 4`() {
        val image = listOf(
            listOf(0, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(0, 0, 0, 0)
    }

    @Test
    fun `find rectangle in image 5`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(0, 0, 1, 1, 1, 1, 1),
            listOf(0, 0, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(2, 0, 3, 1)
    }

    @Test
    fun `find rectangle in image that has no background`() {
        val image = listOf(
            listOf(0)
        )

        findRectangle(image) shouldBeEqualTo listOf(0, 0, 0, 0)
    }
}
