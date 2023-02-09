package com.lannstark.lec11

import java.io.StringBufferInputStream

/**
 * 1. 자바와 코틀린의 가시성 제어
 * - 자바와 코틀린의 가시성 제어가 다른 부분이 있다. protected, default가 다르다!
 * - protected : 같은 패키지 X, 선언한 클래스또는 하위클래스에서만!
 * - default : internal이라는 키워드로 변경, 같은 패키지 말고, 같은 모듈에서만 가능하다!
 * - 생성자에 접근 지시어를 붙일 때는 constructor를 붙여야 한다.(주생성자)
 * 2. 코틀린 파일의 접근 제어
 * - 유틸 함수는 파일 최상단에 만들면 편하다.
 * 3. 다양한 구성요소의 접근 제어
 * - 커스텀 setter를 사용해서 접근지시어를 다르게 할 수 있다!
 * 4. java 와 kotlin을 함께 사용할 경우 주의할 점
 * - internl, protected 를 주의해야한다 java는 코틀린의 키워드와 다르게 다 java로 인식한다.
 */
class lec11 {
}

fun main(){

}

/**
 * 자바와 코틀린의 접근 지시어!
 * - public -> 같다
 * - protected -> 선언된 클래스 또는 하위클래스에서만 접근 가능 (같은 패키지가 안된다 패키지를 namespace를 관리하기 위한 용도로만 사용한다!)
 * - default(package) -> 같은 패키지 : internal 키워드로 변경, 같은 모듈 안에서만 접근 가능
 *      - 모듈? : 한 번에 컴파일 되는 코틀린 코드
 *      - eg) IEDA Module, Maven Project, Gradel Source Set, Ant Task<kotlinc>의 호출로 컴파일 파일의 집합
 * - private -> 동일
 *
 * - 자바의 기본지시어 default VS 코틀린의 기본지시어 public
 * - 코틀린은 .kt파일에 변수,함수,클래스 여러개를 바로 만들 수 있다. -> 무슨 소리지?.. 바로?... 뭐 익명함수.. 이런건가?...
 *  - 자바스크립트 마냥 그냥 .kt 파일에 변수 함수 이것저것 선언 가능, java에선 클래스 내에서만 가능했다!...
 *
 * - 주 생성자에 접근지시어를 붙이려면 constructor 키워드를 써야하단다.
 */
/**
 * 주생성자에 접근지시어를 붙이려면 constructor 키워드를 써야한다/
 * 이런 용례중 하나가 자바에서 유틸성 코드를 만들 때, 추상클래스 + private 생성자를 통해서 인스턴스 화를 막았다!
 * 그리고 코틀린에서도 가능하다! 자바처럼 하거나, 아니면 그~~냥 파일 최상단에 함수를 작성해버리면 편하다!
 * 왜냐면 어디에도 속하지 않았고, 파일에 퍼블릭이라 아무데너사 접근이 가넝;; ㄷㄷㄷㄷ
 */
class cat private constructor(){

}

/**
 * 접근지시어를 다양하게 활욜 할 수 있다.
 * get과 set 을 호출할때 get 은 public, set은 private이 가능하다! -> 사실 당연한거.. 어차피 자바에선 함수 호출이라;;;
 * class car는 name,owner,_price의 getter 3개, owner,_price 2개의 setter를 가지고 있다.
 */
class car(
//    name에대한 internal을 붙여주고 싶다? 이렇게!
//    internal val name: String
    val name: String,
//    owner의 getter, setter를 private으로 만들고 싶다?
//    private var onwer: String
    var onwer: String,
    _price: Int //생성자에 파라미터를 만든다?.. 이게 뭔 개소리지 ㅅㅂ...
){
    var price = _price
        private set // _price는 setter만 private 설정하기!!
}

/**
 * java 와 kotlin을 함께 사용 시 주의할 점
 * internal은 바이트코드 상 public이다. 때문에 java 코드에서는 Kotiln모듈의 interal 코드를 가져올 수 있다.
 * java의 protected 와 kotlin의 protected는 다르다. 즉 java는 같은 패키지에 있으면 kotlin protected를 가져올 수 있다.
 */