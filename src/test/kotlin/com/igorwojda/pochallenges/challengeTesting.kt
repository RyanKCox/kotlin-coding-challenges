package com.igorwojda.pochallenges

import java.util.*


fun maxInPath(arr: IntArray, x: Int, y: Int): Int {
    var result = -404
    //this is default OUTPUT. You can change it.

    var header:node = node(arr[0])

    arr.forEach{ pop->
        if(pop != header.value){
            var prev:node? = null
            var target:node? = header

            while(target != null){
                prev = target
                if(target.value < pop){
                    target = target.prev
                }else{
                    target = target.next
                }
            }
            if(prev == null){
                target = node(pop)
            } else{
                if(prev.value < pop){
                    prev.next = node(pop)
                }else{
                    prev.prev = node(pop)
                }
            }
        }
    }

    var max = 0

    var spot = header!!
    while(spot.value != x){
        if(spot.value >= max)
            max = spot.value

        if(spot.value < x){
            spot = spot.prev!!
        }else
            spot = spot.next!!
    }

    spot = header!!
    while(spot.value != y){
        if(spot.value >= max)
            max = spot.value

        if(spot.value < y){
            spot = spot.prev!!
        }else
            spot = spot.next!!
    }




    return max
}
data class node(var value:Int,var prev:node? = null,var next:node? = null)

fun main(args: Array<String>) {
    //INPUT [uncomment & modify if required]
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val arr = IntArray(n)
    for (i in 0 until n) {
        arr[i] = sc.nextInt()
    }
    val x = sc.nextInt()
    val y = sc.nextInt()
    sc.close()
    //OUTPUT [uncomment & modify if required]
    println(maxInPath(arr, x, y))
}