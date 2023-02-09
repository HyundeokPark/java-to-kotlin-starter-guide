package com.lannstark.lec02

fun main(){
    val person = Person("공부")
    startsWithA(person.name)
}

fun startsWithA(str: String): Boolean{
    return str.startsWith("A")
}
