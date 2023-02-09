package com.lannstark.lec04

fun main(){
    val money1 = JavaMoney(2_000L)
    val money2 = money1
    val money3 = JavaMoney(2_000L)

    println(money1 == money2)
    println(money1 === money2)
    println(money1 == money3)
    println(money1 === money3)
    println(money2 == money3)
    println(money2 === money3)



    if(money1>money2){
        println("1이 2보다 크다!")
    }

    val moneys1 = Money(1_000L)
}
class lec04 {
}