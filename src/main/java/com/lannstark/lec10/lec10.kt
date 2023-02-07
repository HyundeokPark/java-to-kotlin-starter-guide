package com.lannstark.lec10

/**
 * 1. 추상클래스
 * - 자바와 코틀린 모두 추상클래스는 인스턴스화 할 수 없다! 어차피 익명객체는 쌉간엉
 * 2. 인터페이스
 * - default 키워드 없어도 자동적용, 바디가 없으면 추상메소드!
 * 3. 상속 시 주의할 점
 * - 상위클래스의 생성자나 init 블록에 open키워드를 쓰면 안된다!
 * 4. 지시어(키워드)
 * - final : override 할수없다! 코틀린에서는 default 값!
 * - open : override 가능!
 * - abstract : override 키워드 붙여서 구현해줘야한다!
 * - override : 상위타입을 오버라이드 하고 있으며, 특정 상위 함수 호출 시 super<상위타입명>.함수명 으로 호출한다!
 *
 */
class lec10 {
}

/**
 * Animal 추상클래스를 구현한 cat,Penguin
 */
abstract class Animal(
    protected val species : String,
    protected open val legCount: Int
){
    abstract fun move()
//    abstract fun getLegCount()
}
/**
 * cat 클래스를 옮겨 보자!
 * 상속을 받을때도 ':' 을 쓰는데,(타입 선언처럼) 이는 컨벤션의 차이다.
 * 타입 선언시에는 붙여서, val name: String 상속 시에는 한칸 띄어서! class Cat : Animal
 *
 * 또, 마찬가지로 자바처럼 구현클래스 생성자 호출 시 추상클래스의 생성자를 호출하게 되는데.
 * 이는 상속을 받는다고 표기한 부분에서 바로 ()로 호출할 수 있다.
 */
class Cat(
    species: String
) : Animal(species, 4){
    /**
     * 자바와 다르게 overrride가 어노테이션이 아닌, 지시어다.(즉, 필수)
     */
    override fun move() {
        TODO("Not yet implemented")
        println("고양이가 걸어간다.")
    }
}

/**
 * Interface도 클래스 상속과 똑같이 ':' 키워드를 쓴다! 그래서 상속을 이미 하고 있을 경우 , 로 이어주면 된다.
 */
class Penguin(
    species: String, override val swimAbility: Int,
//     val wingCount: Int = 2 -> 난 생성자에서 디폴트로 박아버림...
) : Animal(species,2), Swimmable, Flyable {
    private val wingCount: Int = 2
    override fun move() {
        TODO("Not yet implemented")
        println("팽귄이 걸어감")
    }

    /**
     * 처음 나는 이렇게 재구성?.. 했지만  우선 Int로 변환이 안됨..
     * + get()을 쓰는게 아직 안익숙 ㅋ
     */
//    override fun getLegCount()   {
//        TODO("Not yet implemented")
////        println("팽귄의 날개 수는 ${this.wingCount}입니다.")
//        return super.legCount + this.wingCount
//    }
    /**
     * 게터는 요렇게 그냥 쓸 수 있을지도!
     * 그런데 코틀린에서는 기본적으로 override를 위해서는
     * open이라는 키워드를 붙여줘야 한다. 그렇지 않으면 다 막혀있음;;
     * 코틀린은 프로퍼티도 오버라이드가 된다;; 자바도 됬나?...
     */
    override val legCount: Int
        get() = super.legCount + this.wingCount

    /**
     * super<상위타입명>.함수 로 호출! 자바와 문법이 다르다!
     */
    override fun act() {
        TODO("Not yet implemented")
        super<Swimmable>.act()
        super<Flyable>.act()
    }

    /**
     * 코틀린에서는 backingField가 없는 프로퍼티를 Interface에 만들 수 있다.
     * - 구현클래스의 backfing Field가 없는걸 인터페이스에 넣는다?.. 는 아니겠고..
     * - 애초에 backingField  지원? 이 안되는 걸 인터페이스에서 프로퍼티로 가지고 있을 수 있다. 일듯
     */
}


/**
 * 인터페이스
 * Flayable,Swimmable 두 인터페이스를 Penguin이 구현
 */
interface Flyable{
    /**
     * default 키워드 없어도 default다!
     * 바디가 없으면 추상메소드됨 ㅇㅇ
     */
    fun act(){
        println("파닥")
    }
}

interface Swimmable{
    /**
     * 아래의 swimAbility가 backing Field없는 프로퍼티!
     * val 이기 때문에 get()을 구현클래스에서 구현해주길 기대한다.
     *
     */
    val swimAbility: Int
    fun act(){
        println(swimAbility)
        println("수영 ~ ")
    }
}

/**
 * 상속 시 주의할 점!!
 * 아래처럼 코드가 작성 되었을때, Derieved 생성자를 호출하면 어떻게 될까??
 * 예상 : 일단 슈퍼클래스가 먼저 호출 되니까!.. Base init은 되는데. number를 overried 했으므로
 * Derived의 number가 들어간다!.. 그리고 Derived도 나올듯!
 *
 * 실제 : Base클래스 Init 호출, 그리고 Number는 0이 나왔다.. 초기화 안된 값이 들어갔다?..
 * - IDE에서도 Accessing non-final property number in constructor 이렇게 경고해주고 있다.
 *  위 예상에서 하위 클래스가 Override 하기전에 상위클래스 init이 실행되므로 Int 기본값인 0이 나온다.
 *  이런 이유로 상위클래스 constructor나 init 블록에서는 하위클래스에 접근하면 안된다!
 */
open class Base(
    open val number: Int = 100
){
    init{
        println("Base Class")
        println(number)
    }
}

class Derieved(
    override val number: Int
) : Base(number) {
    init{
        println("Derived Class")
    }
}
fun main(){
    Derieved(300)
}
