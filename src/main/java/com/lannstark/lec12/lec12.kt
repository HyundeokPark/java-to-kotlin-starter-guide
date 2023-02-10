package com.lannstark.lec12

/**
 * 코틀린에서 obejct 키워드를 다루는 방법!
 * 1. static 함수와 변수
 * - 코틀린에서 static 함수와 변수를 만드려면, companion object 키워드를 써야한다! -> 그럼 static 클래스는?...
 * - 자바에서 코틀린의 static 함수 변수를 사용하려면 Companion으로 호출하거나, 코틀린 코드에서 @jvmstatic 어노테이션을 붙여주면 이름을 생략하고 호출 할 수 있다!
 * - 이름이 있으면 이름을 통해서 호출하면됨 ㅇㅇ 이름이 없는건 사실 Companion이라는게 생략된것일분!
 * - companion object도 객체기 때문에 이름을 붙일 수도, 다른 객체를 상속 받을 수 도 있다!!!!
 * 2. 싱글톤
 * - object 키워드 하나만 붙여주면ㅋㅋㅋㅋ 된ㅁㅋㅋㅋㅋ 개꿀ㅋㅋㅋㅋ
 * 3. 익명 클래스
 * - 얘도 obejct키워드로 구현한다. 기존 new'클래스명'(){..} 이부분이 object : '구현할 클래스/인터페이스명' {..} 로 바뀌었다!
 */
class lec12 {
}

fun main(){
    //싱글톤 객체 바로접근 ㄷㄷ
    println(Singleton.a)
    //set도 쌉가능 ㄷㄷㄷㄷ
    Singleton.a += 2
    println(Singleton.a)

    // 기존 자바에서는 new Movavle(){...} 이렇게 구현했지만!...
    //보다 싶이 object 키워드를 사용한다!...
    moveSomething(object : Movable {
        override fun move() {
            TODO("Not yet implemented")
            println("뭅뭅")
        }

        override fun fly() {
            TODO("Not yet implemented")
            println("푸라이~푸라이~")
        }

    })

}





class Person private constructor(
    val name: String,
    val age: Int =1){

    /**
     * 코틀린에는 static이 없다!
     * 대신 companion object 키워드를 쓰고, 새로운 블록을 만들어 안에 변수와 함수를 선언한다.
     * 클래스와 동행하는 유일한 오브젝트다!.. 라고 생각해주면 된다.
     *
     * java와 다른 점 한가지!! companion object 도 하나의 객체로 간주된다.
     * 그래서 이름을 붙일수도 있고, interface를 구현할 수 도있다.
     *
     * companion object에 유틸서 함수를 넣을 수도 있으나, 파일 최상단을 추천!
     *
     * 그리고 java에서 코틀린의 static을 어떻게 부르냐? 두가지가 있다.
     * 1. 이름이 없는 경우 :  Person.Companion.newBaby().. -> 사실은 Companion 이라는 이름이 생략 된 것!!
     * -> 또 다른 경우로 코틀린에서 @jvmstatic 이라는 어노테이션을 붙이면 Person.newBabay()로 바로 호출 가능!!!!!
     * 2. 이름이 있는 경우 :  Person.Factory.fly(); -> 이름으로 부르면 된다!!
     */
    companion object Factory : Movable{
        //그냥 val만 쓰면 runtime시 변수가 할당, 그러나 const를 붙이면 컴파일시에 값이 할당 된다.
        //즉, 진.짜 상수 라는 뜻으로 강조?.. 같은 느낌인듯 기본타입과 String 타입에만 붙일 수 있다!
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun move() {
            TODO("Not yet implemented")
            println("움직인당")
        }

        override fun fly() {
            TODO("Not yet implemented")
            println("난당 ㅎㅎ")
        }
    }
}

/**
 * 자바의 싱글톤이란?
 * - 단 하나의 인스턴스만을 갖는 클래스를 싱글톤이라 한다!
 * 동시성에 대한 처리를 하거나 상속을 할 필요 없다면 enum 클래스를 활용할 수도 있었다!.. -> 뭔솔?? 첨 듣는데
 *
 *
 * 그럼 코틀린에서는 싱글톤을 어떻게 만드냐??
 * - obeject Singleton 이렇게 object 키워드를 붙여주면 된다.. 끝... 개 간단.. 미쳣 ㄷㄷ..
 *
 * 그래서 바~로 이름으로 접근가능 Singleton.a 이런식으로 ! main함수 예제를 보자!
 *
 * 자바 디컴파일 시 내용
 *  public final class Singleton {
        private static int a;

        @NotNull
        public static final Singleton INSTANCE;

        public final int getA() {
            return a;
        }

        public final void setA(int var1) {
            a = var1;
        }

        private Singleton() {

        }

        static {
            Singleton var0 = new Singleton();
            INSTANCE = var0;
        }
    }
 */
object Singleton {
    var a: Int = 0
}

/**
 * 익명클래스!
 * 특정 구현체나 인터페이스를 일회성으로 사용할 때 쓰는 클래스였다!
 * Movavle 인터페이스를 어떻게 익명으로 받아서 처리하는지 main 함수를 보자!
 */
private fun moveSomething(movable: Movable){
    movable.move()
    movable.fly()
}
