package com.lannstark.lec09

/**
 * 1. 클래스와 프로퍼티
 * - 필드를 만들면 게터와 세터가 자동 생성되어 프로퍼티가 된다!
 * 2. 생성자와 init
 * - 주 생성자는 클래스 선언 시 선언 할 수 있고, 주생성자는 필수다.
 * - init으로 주 생성자의 초기화 구문을 실행한다. -> 의미없음.. 그냥
 * - 부생정자는 constructor 라는 키워드로 만들며, 최종적으로는 this.클래스명 으로 주생성자를 호출 해야한다.
 * - 부생성자 호출 시 호출 순서는 주생성자 init - 부생성자 바디 순으로 역순이다.
 * 3. 커스텀 getter, setter
 * - 실제 메모리에 존재하는 것과 무관한게 커스텀 게터와 세터를 만들 수 있다.
 * -> 이말이 이해 안가는게 결국 자바로 디컴파일 하면 함수로 나오지 않나?..
 *
 * 4. backing field
 * - 프로퍼티를 호출 시 get()이 호출되는데, 커스텀 게터에서 본인을 호출하면 다시 get()이 호출 되고
 * 이는 무한반복이 된다. 따라서 이를 막기위해 backfingField라 불리는 field나 value를 사용한다.
 * 하지만 커스텀 게터나 세터 자체가 별로 안쓰임..
 */
class lec09 {

}

fun main(){
    /**
     * getter와 setter가 이렇게도 쓰일 수 있다.
     * Java의 클래스를 생성해도 getter와 setter 사용 가능!
     */
//    val person = Person("te",45)
    val person = JavaPerson("te",33)
    println(person.name)

    println(person.age)
    person.age = 10
    println(person.age)
}

/**
 * 클래스 만들기
 * 1. 생성자는 클래스 명 정의 후 써줘야함
 * 1-1. constructor 지시어는 생략 가능하다. -> public class Person (name:String,age:Int){
 * 2. 필드만 만들면 getter,setter가 자동적으로 만들어진다.
 * 3. 생성자 내에서 필드를 만들 수 있다. 취향따라!..
 * 4. getter,setter 호출 시 객체.필드 로 그냥 호출 가능!
 * 5. init블록은 생성자 호출 시 1번 실행 되는 블록으로 해당 블록에서 Java에서 해줬던 초기화 가능!
 *   - 그러나 굳이 그렇게 해야하나?.. 그냥 로직을 넣어도 되지 않나 싶고.. 디컴파일 해보면 STATIC블록일듯
 */
public class Person(
    name: String,
    age:Int){

    /**
     * 커스텀 게터를 활용하면 자기 자신도 변형 할 수 있다!
     * name.uppercase()는 불가능..
     * 위 생성자의 val을 아래로 내렸는데 왜?... 굳이 상관 있나?
     * -> 프로퍼티로 선언 시 코틀린이 자동으로 게터를 만들어준다. 우리는 커스텀 게터를 만들기 때문에 선언을 취소한다.
     * 게터 오버라이딩은 안되나봄 ㅇㅇ..
     *
     * 왜 name.uppercase()말고 field.uppercase()가?..
     * 클래스 밖에서 person.name을 호출 시 get()이 호출되는데. 이는
     * 클래스 내부에서도 마찬가지다. get() = name.uppercase()를 호출 시
     * name 에서 다시 get()을 호출하게 되고, 이게 반복되어 무한 반복이 된다.
     * 그래서 field 키워드를 사용하고, 또 이를 backingField라고 한다!
     *
     * 그런데, 커스텀 게터에서는 잘 안쓰임
     */
    val name = name
        get() = field.uppercase()

     /**
     * backingField보다는 이렇게 함수 2가지로 선언가능..
     * 음?.. 그런데 name 호출 시 get()이 불린다고 하지 않았나?..
     * 이건 커스텀 게터 안썼을때인가..
     */
    fun getUppercaseName(): String {
        return this.name.uppercase()
    }

    fun getUppercaseNam2(): String = this.name.uppercase()

    /**
     * 세터도 커스텀 세터 간응!
     * 그러나 세터는 지양된다!... 그래서 커스텀 세터도 안씀
     * update() 같은 함수를 쓰곤 한다!...
     */
    var age: Int = age
        set(value){
            field = value.plus(1)
        }
    init{
        if(age <= 0){
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }

    /**
     * 기본생성자 외에 다른 생성자는 이렇게 constructor라는 키워드로 선언한다.
     * 반환?.. 타입을 저렇게 바로 지정하는게 좀 신기하다. -> 부 생성자는 최종적으로 주 생성자를 호출해야 한다고 한다. 그냥 문법적인 거인듯?..
     * 클래스 선언 시 함께 만드는 생성자는 주 생성자로, 반드시 존재해야함
     * 부생성자는 바디를 가질 수 있다.
     * 만약 부생성자를 호출하면, 주생성자의 init이 먼저 실행, 그 이후 부생성자의 바디가 실행 된다.
     * 약간 super 비슷한 느낌이다.
     * 그런데 코틀린에서는 부 생성자 보다 defaultParameter를 권장한다!
     * converting과 같은 경우 부생성자를 사용할 순 있지만, 그보다는 정적 팩토리 메소드를 추천한다.
     * 여기서 정적 팩토리 메소드가 왜 나오지?
     */
    constructor(name: String): this(name,1)

    /**
     * 아래처럼 함수를 선언 할 수도 있고,
     */
    fun isAdult(): Boolean{
        return this.age>= 20
    }

    /**
     * 커스텀 게터! 로 아래처럼 선언도 가능하다.. 왜 하는거?..
     * 마치 필드가 있는것 마냥, get을 사용 할 수 있다.
     * get()은 = 도 가능하지만. {} 블록으로 함수도 작성 할 수있다. 어차피 expression이라!...
     */
    val isAdult2: Boolean
    get() = this.age>=20

}