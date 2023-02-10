package com.lannstark.lec13

/**
 * 코틀린에서 중첩 클래스를 다루는 방법!!!
 * 1. 중첩클래스의 종류
 * - static을 쓰느냐 안쓰느냐!...
 * - static을 쓰는게 권장된다!!!!
 * 2. 코틀린의 중첩클래스와 내부클래스
 * - static 키워드를 생략하고 class 작성하면 자동으로 static 중첩 클래스가 된다.
 * - inner class를 만들고 싶다면, inner 키워드를 class 앞에 붙여줘야 한다!
 * - inner class에서 외부클래스를 참조할 대는 this@'외부클래스명' 으로 접근할 수 있다!
 */
class lec13 {
}

/**
 * 중첩클래스의 종류
 * 1. static을 사용하는 중첩 클래스 : 밖의 클래스를 직접적으로 참조가 불가능했다. static이니까.. 당연히 인스턴스화 되기 전엔 참조가 불가능함.. 맞나?..
 * 2. static을 사용하지 않는 중첩 클래스
 *  - 내부클래스(Inner Class) : 클래스 안의 클래스로 밖의 클래스를 직접적으로 참조하는게 가능하다. -> 쓰지 않도록 가이드되어 있고, 코틀린도 이를 지양한다.
 *  - 지역클래스(Local Class) : 메소드 내부에 클래스를 선언, 그런데 실제로 안쓰임
 *  - 익명클래스(Annonymous Class) : 12강에서 배웠던 것으로 일회성!
 *
 *  그럼 static을 사용하는 권장버전의 중첩클래스를 kotlin으로 작성해보자!
 *  - static 문법이 없는데, 어떻게??/ companion object?... 는 그냥 class를 쓰면 자동으로 해준다.
 */
class JavaHouse(
    private val address: String,
    private val livingRoom : LivingRoom
){
    fun getLivingRoom(): LivingRoom{
        return  livingRoom
    }

    class LivingRoom(
        private val area: Double
    ){
    }

    // 권장되지 않는 버전으로, inner 키워드를 명시해줘야 한다!
    inner class InnerRoom(
        private val innerArea: Double
    ){
        val address: String
            //외부 클래스를 참조할때 this 키워드와 @외부클래스명 어노테이션을 붙여야 한다!
            get() = this@JavaHouse.address
    }
}

