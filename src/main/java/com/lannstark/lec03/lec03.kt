package com.lannstark.lec03

fun main(){
    var dd :Int = 0
    val number1: Int? = 3
    val number2: Long = number1?.toLong() ?: 0

    pringAgeIfPerson(Person("dd",11))

    val str = "ABC"
    println(str[1])
    println(str[2])
}

fun pringAgeIfPerson(obj : Any?){
    if(obj is Person){
        val person = obj as Person
        println(person.age)
    }
    if(obj !is Person){
        val person = obj as? Person
        println(person?.age)
    }
    val log = "ddddd${obj.hashCode()}gggg"
    println(log)
}
class lec03 {
}