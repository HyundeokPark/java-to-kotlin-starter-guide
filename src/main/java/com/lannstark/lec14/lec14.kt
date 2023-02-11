package com.lannstark.lec14

/**
 * kotlin에서 다양한 클래스를 다루는 방법
 * 1. Data class
 * 2. enum class
 * 3. Sealed Calss, Sealed Interface
 */
class lec14 {
}

fun main() {
    val dto1 = KotlinDto("dd",100)
    val dto2 = KotlinDto("dd",100)
    println(dto1 == dto2) // true 로 그냥 나놔벌임 ㄷㄷ..
    println(dto1) // toString도 바로 나와 벌임...
}
/**
 * Data class?...
 * - 자바의 계층간의 데이터를 전달하기 위한 DTO 클래스!....
 * - IDE를 활용할 수도 있고, 롬복을 황용할 수도 있지만, 클래스가 장황해지거나, 클래스 생성 이후 추가적인 처리를 해줘야 하는 단점이 있다.
 *
 * 그럼 코틀린에서 함 만들어 보자!
 *
 * 여기에 8강에서 배운 named arg 까지 쓰면 빌더패턴을 쓰는 효과까지 누린다!.. -> 기억안남;; ㅅㅂ ㅈ됨됨
 * -> 함수 호출 코드의 인자부분에서 바아로 파라미터 값을 할당 해버리기! ex) calledFunc(param1, parma2 = "넣어버렷!")
 *  자바역시 data class 와 비슷한 record를 jdk16 이상부터 도입했다고 한다!
 *
 */

data class KotlinDto(
    val name: String,
    val age: Int
){
    //TODO: 이퀄즈 구현은 어떻게 할까?.... 이퀄즈 이전까지는 그냥 뚝딱 했음!
    //TODO: 바로 class 앞에 'data' ㅋ키워드를 붙여주면 equals, hashcode toString을 만들어준다! ㄷㄷㄷㄷ,,,,
    //TODO: 바아로 메인함수에서 확인하기!
}

/**
 * enum class!!!!!!!
 *
 * 딱히 다를거 없이 생성자 부분만 코틀린식으로 작성하면 끝 ㅋ
 *
 * 그.런.데
 *
 * when + Sealed class와 함께 사용 시 더 강려크하다!!
 */
enum class KotlinCountry (
    private val code: String
    ){
    //TODO: 흠... 미리 선언해두는건 어떻게 하지?.. -> 그냥 똑같당...
    KOREA("KO"),
    AMERICA("US"),
}

/**
 * 이렇게 when을 사용해서 if-else보다 쉽게 구현 가능!.. -> 근데 굳이?... swtch랑 뭐가 크게 다른지...
 * -> 기본적으로 컴파일러가 eunm값을 알고 미리 전처리?.. 해주는 편의기능이 있다고 느껴짐. 막 엄청 크~게 크리티컬하지는 앟ㄴ음
 *
 * enum은 컴파일 단계에서 이미 값을 알고 있다고 한담..
 *
 *
 */
fun handleCountry(country: KotlinCountry){
    when(country){
        KotlinCountry.KOREA -> println("꼬랴")
        KotlinCountry.AMERICA -> println("암메 ~ ")
        //TODO: else문이 없어도 되는이유 : enum클래스는 컴파일 단계에서 값을 알고 있기 때문이다. 만약 값이 추가되어도 IDE가 알려줌 ㅋ
    }
}

/**
 * Sealed Class 와 Interface
 * - 상속이 가능하도록 추상클래스를 만들까 하는데... 외부에서는 이 클래스를 상속받지 않았음 좋겠음!...
 * -> 무슨 경우지?...
 * -> 쨌든... 하위클래스를 봉인하자!... 라는 발상에서 나오게됨!...
 *
 * 컴파일때 하위클래스 타입을 모두 기억하고, 런타임때 클래스 타입이 추가될 수 없다.
 *
 * 하위클래스는 같은 패키지에 있어야한다.
 * Enum과 다른점
 * - 클래스를 상속 받을 수 있다.
 * - 하위 클래스는 멀티 인스턴스가 가능함!..
 * 사용법은 class 앞에 sealed 키워드만 붙이면 된다!
 *
 * 그래서 강의자는 추상화가 필요한 Entity 혹은 DTO에 sealed class를 활용 한다!
 *
 * 추가로 jdk17에서도 sealed class 가 추가되었다!
 *
 * 아직은 잘 모르겠는데.. sealed class/.. 정말 저렇게 외부 노출 외에 쓸 필요가 있나?.. 어차피 api나 제공해 줄탠데/...
 */
sealed class HyundaiMotor(){

}

class Avante : HyundaiMotor(){}

class Sonata : HyundaiMotor(){}

class Grandeur : HyundaiMotor(){}

fun handleCar(car: HyundaiMotor){
    when(car){
        is Avante -> println("이돈주고?")
        is Sonata -> println("좀더 보태서")
        is Grandeur -> println("국산차를 ?")
    }
}
