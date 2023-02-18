package com.lannstark.lec18

/**
 * Chapter18 kotlin에서 컬렉션을 함수형으로 다루는 방법
 * 1. 필터와 맵
 * 2. 다양한 컬렉션 처리 기능
 * 3. List를 Map으로
 */
fun main(){
    val fruits =  listOf(Fruit(1L,"apple",100L,200L),
        Fruit(1L,"apple",100L,200L),
        Fruit(1L,"apple",100L,200L),
        Fruit(1L,"apple",100L,200L),)

    //통상적인 filter 사용법, java의 스트림 필터와 비슷
    val apples = fruits.filter { frutit -> frutit.name == "apple" }

    // filter에서 인덱스가 필요할 경우
    val applesWithIndex = fruits.filterIndexed{ idx, fruit ->
        println(idx)
        fruit.name == "apple"
    }

    //사과의 가격들을 알려주세요! 메소드 체이닝이 가능하다!
    val applePrices = fruits.filter { fruit -> fruit.name =="apple" }
        .map { fruit -> fruit.currentPrice }

    //마찬가지로 인덱스가 필요하다면?
    val applePriceWithIndex = fruits.filter { fruit -> fruit.name == "apple" }
        .mapIndexed{idx, fruit->
            println(idx)
            fruit.currentPrice
        }

    //맵핑의의 결과가 null이 아닌 것만 가져오고 싶다면?
    val values = fruits.filter { fruit -> fruit.name =="apple" }
        .mapNotNull { fruit ->  fruit.currentPrice}
}

/**
 * 이런 요구사항들이 있다고 가정해보자.
 * - 사과 만 주세요
 * - 사과의 가격들을 알려주세요.
 * 이때, kotlin에서는 ㅇ떻게 사용할 수 있을까?
 */
data class Fruit(
 val id: Long,
 val name: String,
 val factoryPrice: Long,
 val currentPrice: Long
){}

/**
 * 위에서 배운 용법을 토대로, 17강의 filterFruits의 함수를 리펙토링 해보자!
 * - 여기서 인자로 받는 filter는 predicate와 달리 그냥 함수 자체로 때려박는 boolean 함수다
 *
 * - 강사 설명
 *  fruits.filter{fruit -> filter(fruit) } -> fruits.filter({람다})라는 뜻이다. 왜지?... 이건 잘 모르겠는데..
 *  또 람다이기 때문에 그 자체로 함수를 집어넣어줄 수 있다.
 *  즉, 인자로 받은 함수인 변수 filter를 넣을 수 있다는것!... -> fruits.filter(filter)
 *  -> 이해가 잘 되지 않는다. 17강을 보고 와도.. 될듯 하면서 안되넴
 *  아니다. 다시 생각해보자, 람다라는것 자체가
 *  1.처음에 인자로 받을 파라미터를 선언해주고 (fruit)
 *  2.-> 표기로 인자와 함수를 구분하고
 *  3.다음 함수 바디를 선언 해주는 것이다.(filter(fruit))
 *  ~~4.그런데 인자를 받는 파라미터 자체가 함수라면?..~~
 *  ~~5.그리고 그 함수가 어차피 람다로 쓸 경우 인자로 선언하는 값을 인자로 받는 함수라면?... (여기선 어차피 fruits의 원소인 fruit을 받는다면?..)~~
 *  ~~6.대충 느낌은 알겠는데, 그런다고 인자를 마음대로 생략이 가능하다고?...~~
 *  4. 어떤 함수의 마지막 파라미터가 함수라면 이를 소괄호 밖으로 빼내어 쓸 수 있다.
 *   -> filter {람다..} 는 결국 filter({람다..}) 인것이다.
 *  5. 그리고 코틀린에서 함수는 1급시민 이기때문에, 함수 그자체를 넘길 수 있다.
 *  -> filter({람다..}) 는 filter(변수명) 여기서 변수는 람다함수를 받고 있는 변수인것!
 *  그래서 함수의 인자로 받은 filter 변수를 filter() 람다에 그대로 집어 넣을 수 있는것이다!
 */
private fun filterFruitsRefactored(fruits: List<Fruit>, filter: (Fruit) -> Boolean)
        : List<Fruit> {
    //이게 내가 생각한 최초 버전
    return fruits.filter{fruit -> filter(fruit) }
    //이게 강사가 말한 버전
    return fruits.filter(filter)

}

/**
 * 17강의 filterFruits 함수
 */
private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean)
        : List<Fruit> {
    val result = mutableListOf<Fruit>()
    for(fruit in fruits){
        if(filter(fruit)) {
            result.add(fruit)
        }
    }
    return result
}


