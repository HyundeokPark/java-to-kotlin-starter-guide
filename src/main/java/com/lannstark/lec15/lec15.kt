package com.lannstark.lec15

/**
 * 드디어 함수형프로그래밍 섹션!
 * 코틀린에서 배열과 컬렉션을 다루는 법!!
 * 1. 배열
 * 2. 코틀린에서의 컬렉션 - List, Set, Map
 * 3. 컬렉션에서의 Null가능성, Java와 함께 ㅅ ㅏ용하기
 */
class lec15 {
}
fun main(){
    val array = arrayOf(100,200)

    array.plus(300) //리스트마냥 원소 추가가능 ㄷㄷ...

    for(i in array.indices){
        println("$i ${array[i]}")
    }

    for((i,value) in array.withIndex()){
        println("$i $value")
    }


}
/**
 * 사실 배열은 잘 안씀 ㅎ..
 *  arrayOf() 메소드로 배열을 생성한다!...
 */

/**
 * 코틀린에서의 collection
 * - 불변인지 가변인지 꼭! 선언해줘야함
 *  - 가변 : 컬렉션에 원소를 추가.삭제 가능
 *  - 불변 : 컬렉션에 원소를 추가하거나 삭제할 수  없다. ㄷㄷ...
 * - 불변 컬렉션이라 하더라도, Reference 타입의 원소는 바꿀수 있다. -> Reference 객체의 값은 바뀔 수 있다. 그뜻인듯?.. 그 객체의 주소값은 그대로겠지만...
 */

/**
 * List!!
 *
 * 아래처럼 listOf()로 만들 수 있다.
 * 빈 리스트는 emptyList() 로!... 또 <>가 뜨는데, 타입추론이 불가능하기 대문에 적어줘야함!
 * 기본적으로 그냥 ㄹlist는 불변 이며, 가변 list를 만들고 싶다면, mutable이라는 키워드를 앞에 붙여줘야함
 */
fun listFUnc(){
    val numbers = listOf(100,200)
    val emptylist = emptyList<Int>()

    //TODO: 그런데 아래처럼 타입추론이 되면 <> 생략가능 오 ㅋㅋㅋ
    printNumbes(emptyList())

    numbers.get(0) //이렇게 가져올 수도 있지만,
    numbers[0] // 이렇게 배열처럼 가져오기 가능

    for(number in numbers){
        println(number)
    }
    //배열처럼 for문을 쓰는것도 간응!...
    for((number,value) in numbers.withIndex()){
        println("$number $value")
    }
    //가변 List!
    val mutableNumbers = mutableListOf(100,200,300)
}

private fun printNumbes(numbers: List<Int>){

}

