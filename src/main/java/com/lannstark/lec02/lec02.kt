package com.lannstark.lec02

import java.lang.IllegalArgumentException

fun main(){
    var str: String? = "avv"
    println(str?.length)
}

class lec02 {


    fun startsWithA1(str: String?): Boolean{
        return str?.startsWith("A") ?: false

    }

    fun startsWithA2(str: String?): Boolean? {
       return str?.startsWith("A")
    }

    fun startsWithA3(str: String?): Boolean{
        return str?.startsWith("A") ?: false
    }

    fun startWith(str: String?):Boolean{
        return str!!.startsWith("A")
    }
}