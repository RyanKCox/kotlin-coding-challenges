package com.igorwojda.integer.pyramidgenerator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.text.DecimalFormat

fun generatePyramid(n: Int): List<String> {
    var list = mutableListOf<String>()
    var rowLength = n * 2 - 1
    val char = "#"
    for (i in 1..n){
        var length = i * 2 - 1
        var str = ""
        str = char.repeat(length)
        length = rowLength - length
        str = " ".repeat(length/2) + str + " ".repeat(length/2)
        list.add(str)
    }




    return list
}
fun regexLength(S: String, x: Int, y: Int): Int {
    //this is default OUTPUT. You can change it.
    var result = 0
    //write your Logic here:
    var str = S

    var max = if (y>x) "YX" else "XY"
    var min = if (x>=y) "XY" else "YX"

    var count = max.toRegex().findAll(str).count()
    str = str.replace(max,"")

    result += count * (if(y>x) y else x)

    count = min.toRegex().findAll(str).count()
    str = str.replace(min,"")

    result += count *( if(y>x) x else y)

    str.forEach { c->
        result += if(c == 'Y') y else x
    }


    return result
}
fun test(X:Float,Y:Float,Z:Int):String{


//    return DecimalFormat("#.00").format( (3*X*Y*( if(Z == 0) 1 else 2) ) )

    var result = "0.00"

    val fmt = DecimalFormat("#.00")

    var cost = 3 * X * Y

    if(Z == 1){
        cost *= 2
    }

    result = fmt.format(cost)

    return result


}

private class Test {

    @Test
    fun `testChallenge1`(){
        test(2.2f,2.5f,1).also {it shouldBeEqualTo "33.00"}
    }

    @Test
    fun `dailyChallenge 1`(){
        regexLength("XYXYXYXX",2,3).also { it shouldBeEqualTo 13 }
    }
    @Test
    fun `dailyChallenge 2`(){
        regexLength("XYXYXYXX",3,2).also { it shouldBeEqualTo 15 }
    }

    @Test
    fun `pyramid n = 2`() {
        generatePyramid(2).also {
            it.size shouldBeEqualTo 2
            it[0] shouldBeEqualTo " # "
            it[1] shouldBeEqualTo "###"
        }
    }

    @Test
    fun `pyramid n = 3`() {
        generatePyramid(3).also {
            it.size shouldBeEqualTo 3
            it[0] shouldBeEqualTo "  #  "
            it[1] shouldBeEqualTo " ### "
            it[2] shouldBeEqualTo "#####"
        }
    }

    @Test
    fun `pyramid n = 4`() {
        generatePyramid(4).also {
            it.size shouldBeEqualTo 4
            it[0] shouldBeEqualTo "   #   "
            it[1] shouldBeEqualTo "  ###  "
            it[2] shouldBeEqualTo " ##### "
            it[3] shouldBeEqualTo "#######"
        }
    }
}
