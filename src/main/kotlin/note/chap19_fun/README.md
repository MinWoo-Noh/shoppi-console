# 19강. 함수

## 1. 선언

~~~kotlin
fun powerOf(
    // 파라미터
    number: Int,
    exponent: Int,
    bar: Int = 0 // 디폴트값 가능
) {
/* 함수 내부*/
}
~~~

## 2. Argument (함수를 사용할때 넘겨주는 파라미터값)

### 1) Default Argument

~~~kotlin
// 배스킨 31
// amount 주문 갯수, option 맛 이라고 할 때. 
fun makeIce(amount: Int = 1, option: String = "초코나무숲"): IceCream {
    return IceCream(amount, option)
}
// 이를 실제로 활용하게 될 때 
// 1. 함수만 호출할 경우, 설정한 Default Argument 에 의해 디폴트 값 반환
makeIce() // 반환값 ::  1 "초코나무숲"
// 2. 상수 3만 넘겨주면 amount 값은 1 -> 3 으로 변경, option 변경 X
makeIce(3) // 반환값 :: 3 "초코나무숲"
// 3. amount 1 -> 2, option "초코나무숲" -> "민트초코" 변경
makeIce(2, "민트초코") // 빈환값 :: 2 "민트초코"
~~~

### 2) Named Argument

- Default Argument 와 더불어서 유용하게 쓰기 좋은 요소다.
- function 및 일반 class 에도 동일하게 사용 가능하기 때문에 
  default data class 값을 설정해주느라 번거로웠을 사람들에겐 매우 편한 기능이 아닐 수 없다.

~~~kotlin
fun makeIce(
    amount: Int = 1, 
    option : String = "초코나무숲", 
    single : Boolean = true,
    pint : Boolean = false,
    quarter : Boolean = false,
    family : Boolean = false)
: IceCream { 
    return IceCream(amount, option, single, pint, quarter, family) 
} 
// 원하는 Argument 를 바꾸고자할때나,
makeIce(option = "민트초코")
// 파라미터가 여러개인 경우 Named Argument 를 사용하면 실수를 줄일 수 있다.
// 위 예제같은 경우 Boolean 타입이 여러개이므로 파라미터 명을 모르고선 구분하기 쉽지않다.
// 따라서 함수를 호출할때 Named Argument 로 시각인 구분을 활용하면 도움이 된다.
makeIce(option = "민트초코", single = false, pint = true, quarter = false)
~~~

### Superclass , subclass 실행 순서
~~~kotlin

open class Shape(vertexCount : Int){
    init {
        println("ShaperClass 초기화, vertexCount : $vertexCount ")
    }

    open fun draw(){
        println("Drawing a shape")
    }
}

class Rectangle(vertexCount: Int) : Shape(vertexCount){
    init {
        println("Rectangle 초기화, vertexCount : $vertexCount ")
    }

    override fun draw() {
        super.draw()
        println("Drawing a rectangle")
    }
}

fun main() {
    val rectangle = Rectangle(4)
    rectangle.draw()
}

// 호출순서
// 1. SuperClass 인 Shape init(초기화) 살행
// 2. SuperClass 인 Rectangle(초기화) 실행
// 3. Shape draw 함수 실행
// 4. Rectangle draw 함수 실행
~~~
- 실행 결과
>ShaperClass 초기화, vertexCount : 4
> 
>Rectangle 초기화, vertexCount : 4
> 
>Drawing a shape
> 
>Drawing a rectangle

### 프로퍼티 Override

~~~kotlin
open class Shape{
  open val vertexCount : Int = 0
  open var vertex : Int = 0
  open val valTest : Int = 0
}

class Rectangle(vertexCount: Int) : Shape(){
    override val vertexCount: Int = 4 // open 키워드를 사용해야 재정의 가능
    // override val vertex: Int = 3 // var 에서 val 로 변경 불가
   
    override var valTest: Int = 5 // val -> var  변경 가능
}
~~~

- **val -> var 이 변경 가능한 이유**
> val 은 getter 만 정의 가능하므로 var 변수로 변경하면서 setter 의 정의를 추가하는것이 가능하기 떄문이다.