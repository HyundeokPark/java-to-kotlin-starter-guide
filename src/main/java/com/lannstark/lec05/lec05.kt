package com.lannstark.lec05

import java.lang.IllegalArgumentException

fun validateScoreIsNotNegative(score : Int){
    if(score < 0){
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }
}

fun getPassOrFail(score :Int) : String  {
    return if(score >= 50){
        return "P"
    } else{
        return "F"
    }
}

class lec05 {
}