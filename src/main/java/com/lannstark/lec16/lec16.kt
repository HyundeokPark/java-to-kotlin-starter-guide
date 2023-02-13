package com.lannstark.lec16

import javax.print.attribute.standard.MediaSize

/**
 * 코틀린에서 다양한 함수를 다루는 법
 * 1. 확장함수
 * - private, proteced로 선언된 수신객체 타입의 함수는 호출 불가능!
 * - 맴버함수와 시그니쳐가 같을 경우 맴버함수가 우선적으로 호출 된다.
 * - 오버라이드 역시 가능하며 다형성도 지원한다.
 * 2. infix함수
 * - '변수명 함수명 파라미터' 형식으로 함수를 호출 할 수 있다.
 * - 해당 함수 앞에 infix 키워드를 붙여야 가능! infix fun add(){... 처ㅓㄻ!
 * 3. inline함수
 * - 디컴파일 해보면, 함수를 호출하는게 아니라, 아예 함수로직자체를 복붙해둔다.
 * - 왜?? 파라미터 전달에 대한 오버헤드를 줄이기 위해, 성능향상 목적!
 * - 반드시 성능측정과 함께 신중히 쓰여야 한다.
 * 4. 지역함수
 * - 함수안의 함수로 해당 함수내에서만 중복을 제거하고 싶을때 사용하나, 별로 가독성도 안좋고
 * - 뎁스도 깊어져 잘 안쓰임
 */
class lec16 {
}

fun main(){
    val str: String = "aasdf"
    //와... 이게됨;;;
    println(str.lastChar())

    //이게 되긴하는데.. 너무 가독성 떨어져 보임임
   val number: Int = 1
    number.add(1)
    number.add2(1)
    number add2 1
    //아래 inline함수 add3이 java에서 어떨지!>,,
    number.add3(4)
    /**
     * 뭐지 이렇게 나와벌임;;;
     *  int other$iv = 4;
        int $i$f$add3 = false;
        int var10000 = number + other$iv;

     함수를 호출하는게 아니라, 아예 함수의 로직이 메인함수에 복붙이 되어버렸다. 이게 뭐가 좋은?..
     -> 기본적으로 파라미터를 전달할 때의 오버헤드를 줄일 수 있다! 성.능.향.상
     그러나 inline 함수는 신중히 사용되어야 한다.
     */
}


/**
 * 확장함수가 나오게 된 배경
 * : 코틀린은 자바와 100%호환을 목표로 하고 있다. 그래서 기존 자바 코드에 자연스럽게 코틀린을 추가 할 수 없을까??
 * 자바로된 레거시를 코틀린코드로 유지보수하고 싶다! 가 목표였다. 그래서 나온 방법이
 * 어떤 클래스안에 있는 메소드처럼 호출할 수 있지만, 함수는 클래스 외부에 선언할 수 있게 하자!
 * 즉, 함수자체는 클래스 밖에 선언되어있는데, 호출할때는 클래스에서 호출하듯이 사용!...
 * 그리고 그게 확장함수임 ㅇㅇ
 *
 * 자, 그럼 이제 String 클래스에 대해서 확장함수를 선언하고,사용해보겠다!
 *
 * 아래의 함수에서 this자체를 수신객체라 부르며, 확장하려는 클래스(여기선 String)은 수신객체 타입 이라 부른다!
 *
 * 여기서 이렇게 생각해보자, 확장함수가 public이고, 만약 수신객체 클래스의 private 함수를 가져오면?... 그건 어찌?!...
 * -> 그래서 애당초 문법적으로 private이나 proteced는 가져 올 수 없다 ㅋ
 *
 * 그리고 확장함수와 맴버 함수의 시그니쳐가 같다면?... 이때 어떤게 먼저 호출 될까??
 * -> 맴법함수가 우선적으로 호출된다 ㅋ 그러므로 주의!
 *
 *
 * 또 확장함수가 오버라이드 될수 도 있다!
 *
 * - 자바에서는 코틀린함수를 마치 정적메소드를 호출하듯이 사용이 가능하다!
 *
 * 또 확장함수와 동일한 원리로, 확장 프로퍼티도 가능하다!
 */
fun String.lastChar(): Char{
    return this[this.length-1]
}
// 이런식으로 확장 프로퍼티로도 선언이 가능하다!
val String.lastChar2: Char
    get() = this[this.length-1]

/**
 * 중위함수! Infix, 함수를 호출하는 새로운 방법이다!
 * 변수.함수명(파라미터) 대신, '변수 함수이름 파라미터' 로 그냥 호출 할 수 있다.
 * 아래 두가지 경우를 보자.
 * infix 키워드를 붙일 경우 호출방법이 다양해 진다!
 */
fun Int.add(other: Int): Int{
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}

/**
 * Inline 함수
 * - 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우!
 * -> 익명함수?.. 클래스같은 느낌인가ㅛ?.. 그럴꺼면 굳이 왜?..
 *
 */
inline fun Int.add3(other: Int): Int{
    return this+other
}

/**
 * 지역함수
 * - 함수안에 함수를 선언할 수 있는걸 지역함수라고 한다!! 파일에도 선언하고.. 클래스밖에서도 붙이는데 뭔들 못하리...
 *
 * 아래의 코드를 지역함수를 사용해 리펙토링해보겠다.
 *
 * 중복을 제거하고 싶은데, 그 함수에서만 스고 싶을대 쓰면 되는데 사실 별로 안깔끔해서 잘 안쓰임 ㅋ
 */
fun createPerson(firstName: String, lastName: String): Person{
    if(firstName.isEmpty()){
        throw IllegalArgumentException("첫 이름은 빌 수 없다.")
    }
    if(lastName.isEmpty()){
        throw IllegalArgumentException("마지막 이름도 빌 수 없다.")
    }
    return Person(firstName, lastName, 1)
}

fun createPerson2(firstName: String, lastName: String): Person{
    fun validateName(name: String, fieldName: String){
        if(name.isEmpty()){
            throw IllegalArgumentException("${fieldName} 은 빌수 없다 ㅎ")
        }
    }
    validateName(firstName,"firstName")
    validateName(lastName,"lastName")
    return Person(firstName, lastName, 1)
}