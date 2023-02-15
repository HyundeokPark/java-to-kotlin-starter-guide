package com.lannstark.lec17

/**
 * 코틀린에서 람다를 다루는 방법
 * 1. Java에서 람다를 다루기 위한 노력
 * 2. 코틀린에서의  람다
 * - java에선 함수가 2급시민이지만, kotlin에서는 1급시민이기 때문에 실제로 함수를 전달할 수 있다.
 * - 람다를 두가지 방법으로 만들 수 있고, {}로 바로 쓰이는 방법이 더 선호된다. (간편하니꼐..)
 * - 마지막 파라미터인 람다를 쓸 때는 소괄호 밖으로 빼낼 수 있다.
 * - 또 파라미터가 1개인 람다를 쓸때는 'it'키워드로 접근 할 수 있다. {it.name == "asd"}
 * - 람다의 마지막 expression이 람다함수의 반환값이다! return을 굳이 명시 안해도 그렇게 됨!
 * 3. Closure
 * - 코틀린은 java와 달리 final, effectivly final이 아니여도 사용이 가능하다!
 * - 왜냐하면 코틀린은 모든 변수에 대한 정보를 이미 가지고 시작하기 때문!
 * 4. try with resources
 * - 7강에서 배웠던 use함수의 시그니처를 해석해보자. 16강을 보면 확장함수,inline함수 등을 다시 볼 수 있다.
 * - public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
 *  - 먼저 Closeable의 확장 함수임을 T : .. 으로 알수있다. : 가 상속을 하는 키워드였음을 기억하자!
 *  - 확장함수는 타입.함수명 으로 확장함수를 사용했기 때문에, T.use() 를 보면 Closeable 타입의 확장함수인 use를 사용하는것을 알 수있다.
 *  - 그리고 inline 함수이며 (함수를 호출하지 않고, 아예 로직을 복붙하는 성능향상을 위해서 쓰는 함수!)
 *  - 그리고 받고 있는 파라미터가 block이라는 이름을 가진 함수다.
 *  - T타입을 파라미터로 받고, R타입을 반환한다. 즉, 람다를 받도록 만들어진 함수이다.  아래 실제 use를 호출하는 코드도 확인하자
 *
 *      BufferedReader(FileReader(path)).use {
            reader -> println(reader.readLine())
        }
 *  - 한가지 궁금한건 fun 다음 <>로 받는데 저건 제네릭인가?...
 */
class lec17 {
}

/**
 * 코틀린에서 람다를 사용한느법!!!
 */
fun main(){
    val fruits =listOf(
        Fruit("사과",1000),
        Fruit("사과",1200),
        Fruit("사과",1200),
        Fruit("사과",1500),
        Fruit("바나나",3000),
        Fruit("바나나",3200),
        Fruit("바나나",2500),
        Fruit("수박",10000)
    )
    //람다1
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    //람다2
    val isApple2 = {fruit: Fruit -> fruit.name == "사과"}
    //람다함수를 호출하는 방법!
    isApple(fruits[0])
    //invoke() 를 명시적으로 호출할 수도 있다.
    isApple.invoke(fruits[0])

    //그리고 람다 함수들의 반환타입을 표기해주기 위해서는!...
    //람다1
    val isApple3: (Fruit) -> Boolean  = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    //람다2
    val isApple4: (Fruit) -> Boolean = {fruit: Fruit -> fruit.name == "사과"}

    //filter를 호출해보자!!
    filterFruits(fruits, isApple)

    //변수가 아닌 함수 자체를 집어넣어도 OK
    filterFruits(fruits, fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    })
    filterFruits(fruits, {fruit: Fruit -> fruit.name == "사과"})
    //위와 같은 경우엔 함수를 밖으로 빼는것도 가능하다. 마지막 파라미터가 같기 때문에 추론이 가능한것!
    filterFruits(fruits) {fruit: Fruit -> fruit.name == "사과"}
    //그럼 이렇게도 가능하다. Fruit이 들어가는걸 알기 때문에(추론가능하기 때문에)
    filterFruits(fruits) {fruit -> fruit.name == "사과"}
    //여기서 코틀린은 익명함수의 파라미터가 1개일 경우, it 키워드로 아래처럼 더 줄일 수 도 있다.
    filterFruits(fruits) {it.name == "사과"}



}
//filter를 사용해보자!!
//Java와 달리 Predicate을 받지 않고, 그냥 함수로 때려박기 가능
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
