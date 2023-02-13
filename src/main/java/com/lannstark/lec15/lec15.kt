package com.lannstark.lec15

/**
 * 드디어 함수형프로그래밍 섹션!
 * 코틀린에서 배열과 컬렉션을 다루는 법!!
 * 1. 배열
 * - 배열을 만드는 방법, for문을 사용하는 방법 조금 달랐다. arrayOf()
 * 2. 코틀린에서의 컬렉션 - List, Set, Map
 * - map은 바뀐 키워드가 있었고, List,Set은 전반적으로 java -> Kotlin으로 변한 맥락과 비슷하게 변했다. -> 그냥 아래 예시르 보자..
 * 3. 컬렉션에서의 Null가능성, Java와 함께 ㅅ ㅏ용하기
 * - 플랫폼 타입과 읽기전용 객체에 따른 주의점이 있다.
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

    println("mapFunc")
    mapFunc()


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

/**
 * set
 * - 집합은 List와 다르게 순서가 없고, 원소가 중복되지 않는다.
 * - 자료구조적 의미만을 제외하면 모든기능과 용법이 list와 비슷하다!
 * - list와 마찬가지로 mutable 하게 만들고 싶다면 mutable set 키워드를 사용하면된다!
 */
fun setFunc(){
    val numbers = setOf(100,200)

    //For Each
    for(number in numbers){
        println(number)
    }

    //Traditional For Loop
    for((index,value) in numbers.withIndex()){
        println("$index $value")
    }
}

/**
 * Map
 * - 자바에선 map을 두가지 방식으로 사용한다.
 * 1. jdk8까지의
 *   - map.put(k,v)을 이용하는 방법
 * 2. jdk 9부터의
 *   - Map.of(k,v,k,v...)를 활용하는 방법이다.
 *
 *   그럼 코틀린으로 옮겨 보자!
 */
fun mapFunc(){
    //이런 식으로 java와 같은 기능을 기본적으로 제공하며, 이때는 put으로 넣기 때문에 mutalbe로 써야한다.
    val oldMap = mutableMapOf<Int,String>()
    oldMap.put(1,"1")
    //put 을 아래처럼 바꿔 표현 할 수도 있다.
    oldMap[1] = "1"
    oldMap[2] = "2"

    //아래 방식처럼 mapOf()도 지원하지만, kv를 표기하는 방식이 조금 달라졌다.
    //자바에선 ,로 모든것을 구분했다면, 이제 k와v는 to 라는 키워드로 매핑되며 , 로는 kv한쌍을 구분한다.
    val newMap = mapOf(1 to "1" , 2 to "2")


    // 그럼 반복문에 map을 쓸 경우 어떻게 되는지 알아보자
    for(key in oldMap.keys){ //keys == keyset
        println(key) //key
        println(oldMap[key]) //value
    }

    //내가 생각한 버전
    for(entry in oldMap.entries){
        println("${entry.key}  ${entry.value}")
    }

    //강의자가 말한 버전전
    for((key,value) in oldMap.entries){
        println("${key}  ${value}")
    }

}

/**
 * 컬렉션의 null 가능성, 그리고 Java와 함께 쓰기
 *
 * 다음과 같은 3가지 경우가 있다.
 * - List<Int?> : 원소가 null 일 수 있따.
 * - List<Int>? : List 자체가 null 일 수 있다.
 * - List<Int?>? : 원소도, 리스트도 null 일 수 있따.
 *
 * '?' 위치에 따라 null 가능성이 달리지므로 유의하자!!
 *
 * - Java와 함께 사용할 때 주의할 점
 *   - Java는 읽기전용과 변경가능한 컬렉션을 구분하지 않는다. 마찬가지로 nullable과 nonnull도 구분하지 않는다.그래서 아래와 같은 일이 일어날 수 있다.
 *    - 읽기전용 객체가 변경이 되었다.
 *    - nonnull 객체가 null이 되었다.
 * -> 따라서 코틀린을 사용하는 개발자는 자바와 함께 사용할 때는 데이터가 변경이 될 수 있음을 감안해야 한다. -> 방어적복사를 하면 될듯!..
 * 또는, Java에도 존재하는 Collections.unmodifiableXXX() 를 활용해서 막아야 한다.
 *
 *    - 또 반대로, 코틀린에서 Java를 가져다 사용할 때는 플랫폼 타입을 주의해야 한다. List<Int?>, List<Int>? 등의...
 *     - 이럴 경우 Java코드를 보며 코드를 파악하고 Java코드를 호출하는 부분을 랩핑해서 사용해야 한다. -> 개불편.. ㅅㅂ..
 */
