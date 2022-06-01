package com.igorwojda.matrix.spiralmatrixgenerator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private enum class Direction {Up,Down,Left,Right}

private fun generateSpiralMatrix(n: Int): List<MutableList<Int?>> {
    var list = MutableList(n){
        MutableList<Int?>(n){null}
    }

    var max = n*n
    var dir = Direction.Right
    var row = 0
    var column = 0
    var offset = 0

    (1..max).forEach {
        list[row][column] = it
        when (dir){

            Direction.Right->{
                if(column < n-1 - offset)
                    column++
                else{
                    dir = Direction.Down
                    row++
                }

            }
            Direction.Down->{
                if(row < n-1 - offset)
                    row++
                else{
                    dir = Direction.Left
                    column--
                }

            }
            Direction.Left->{
                if(column > 0 + offset)
                    column--
                else{
                    offset++
                    dir = Direction.Up
                    row--
                }

            }
            Direction.Up->{
                if(row > 0 + offset)
                    row--
                else{
                    dir = Direction.Right
                    column++
                }

            }

        }

    }
    return list
}

private class Test {
    @Test
    fun `generateSpiralMatrix generates a 2x2 matrix`() {
        val matrix = generateSpiralMatrix(2)
        matrix.size shouldBeEqualTo 2
        matrix[0] shouldBeEqualTo listOf(1, 2)
        matrix[1] shouldBeEqualTo listOf(4, 3)
    }

    @Test
    fun `generateSpiralMatrix generates a 3x3 matrix`() {
        val matrix = generateSpiralMatrix(3)
        matrix.size shouldBeEqualTo 3
        matrix[0] shouldBeEqualTo listOf(1, 2, 3)
        matrix[1] shouldBeEqualTo listOf(8, 9, 4)
        matrix[2] shouldBeEqualTo listOf(7, 6, 5)
    }

    @Test
    fun `generateSpiralMatrix generates a 4x4 matrix`() {
        val matrix = generateSpiralMatrix(4)
        matrix.size shouldBeEqualTo 4
        matrix[0] shouldBeEqualTo listOf(1, 2, 3, 4)
        matrix[1] shouldBeEqualTo listOf(12, 13, 14, 5)
        matrix[2] shouldBeEqualTo listOf(11, 16, 15, 6)
        matrix[3] shouldBeEqualTo listOf(10, 9, 8, 7)
    }
}
