# 12강. data class

## 1. inheritance (상속)

~~~kotlin
open class Any // 최상위 클래스
~~~
- Class 에 Data 를 담는용도로 사용할때에는 Any Class 의 3가지 함수를 모두 override 해줘야한다
~~~kotlin
// Any 의 overriding 가능한 함수
open operator fun equals(other: Any?) : Boolean
open fun hashCode() : Int
open fun toString() : String
~~~

~~~kotlin
fun main(){
    val product = Product("패션","겨울패딩")
    print("$product")  
}

class Product(val category : String, val name : String = "") {
}
// 출력 결과 :: Product@6bdf28bb
~~~

- 위와 같은 문자열 표현은 인스턴스 정보를 업ㄷ는데 도움이 안된다.

## 2. toString override
~~~kotlin
fun main(){
    val product = Product("패션","겨울패딩")
    print("$product")
}

class Product(val category : String, val name : String = "") {
    override fun toString(): String{
        return "Product(categoryLababel=$categoryLabel, name=$name)"
    }
}
// 결과 :: Product(categoryLababel=패션, name=겨울패딩)
~~~
- 출력 결과로 인스턴스의 정보를 얻었다.

## 3. equals override
~~~kotlin
val product1 = Product("패션","겨울패딩")
val product2 = Product("패션","겨울패딩")
println(product1 == product2)
// 결과 :: false
~~~
- 넘어가는 파라미터는 같은데 왜 false 일까?

  - 이유는 heep 영역에서 다른 주소값을 가지고 저장되어있기 떄문이다.

### 두 객체가 모두 같은 정보를 담고있을때 두 객체는 '같다' 라고 정의를 해주려면 어떻게 해야할까?

- equals 를 override 해 재 정의해줘야한다.

~~~kotlin
class Product(val categoryLabel: String, val name: String = "") {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Product) {
            return false
        }
        return categoryLabel == other.categoryLabel && name == other.name
    }
}

fun main(){
  val product1 = Product("패션","겨울패딩")
  val product2 = Product("패션","겨울패딩")
  
  println(product1 == product2)
}
// 결과 :: true
~~~
## 4. hashCode override

~~~kotlin
val product1 = Product("패션","겨울패딩")
val product2 = Product("패션","겨울패딩")

// Set() 은 중복을 허용하지 않는 자료구조, 중복된 값이 들어오면 값이 입력되지 않는다.
// productSet 는 HashSet Type 이며 이는 유니크한 원소만을 같는 자료구조이다.
// hashSetOf()로 생성하면서 product1 하나를 원소로 같도록 추가.
val productSet = hashSetOf(product1)
// contains() 함수는 컬렉션 타입의 데이터 중 특정 데이터가 있는지를 판단하는 함수이다.
println(productSet.contains(product2)) 
// 결과 false
~~~
>- 한번 읽고가기
>  - [📌 컬렉션과 배열](https://medium.com/depayse/kotlin-collections-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-54ef2d60c61a)

- HashCode
>- 해쉬 함수 : 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수
>- 해시 값, 해시 코드, 해시 체크섬, 해시 : 해시함수로 얻어지는 값
>- 객체의 해시 코드 비교 후, 해시 코드가 같은 경우에만 실제값 비교

- HashSet 
  - hashCode를 고유한 식별자로 사용해 검색 최적화.
  - 객체의 hashCode 비교 후, 해쉬 코드가 같은 경우에만 실제 값 비교
  
~~~kotlin
class Product(val categoryLabel: String, val name: String = "") {
    override fun hashCode(): Int{
        return categoryLabel.hashCode() + name.hashCode()    
    }
}

fun main(){
  val product1 = Product("패션","겨울패딩")
  val product2 = Product("패션","겨울패딩")
  
  val productSet = hashSetOf(product1)
  println(productSet.contains(product2))
}
// 결과 :: true
~~~

- Class - toString, equals, hashCode 모두 override

~~~kotlin
class Product(val category : String, val name : String = "") {
    override fun toString(): String{
        return "Product(categoryLababel=$categoryLabel, name=$name)"
    }
}

class Product(val categoryLabel: String, val name: String = "") {
  override fun equals(other: Any?): Boolean {
    if (other == null || other !is Product) {
      return false
    }
    return categoryLabel == other.categoryLabel && name == other.name
  }
}

class Product(val categoryLabel: String, val name: String = "") {
  override fun hashCode(): Int{
    return categoryLabel.hashCode() + name.hashCode()
  }
}
~~~

### data class
- Kotlin 에서는 toString, equals, hashCode 를 모두 override 하지 않아도 된다.
- data 키워드를 사용하면 내부적으로 만들어 주기때문이다.
~~~kotlin
data class Product(val category : String, val name : String = "")

fun main(){
  val product = Product("패션","겨울패딩")
  print("$product")
  // 결과 :: Product(categoryLababel=패션, name=겨울패딩)
  
  val product1 = Product("패션","겨울패딩")
  val product2 = Product("패션","겨울패딩")
  println(product1 == product2)
  // 결과 true
  
  val productSet = hashSetOf(product1)
  println(productSet.contains(product2))
  // 결과 true
}
~~~
- 따라서 toString, equals, hashCode 를 모두 override 할 필요가 없다.

### copy() : 동일한 값을 가진 프로퍼티는 유지한 채, 지정한 값만 변경한다.
~~~kotlin
fun main(){
  val padding = Product("패션","겨울패딩")
  val jacket = padding.copy(name = "자켓")
  println(jacket)
}

data class Product(val category : String, val name : String = "")
~~~
- data class의 프로퍼티는 data의 원본이 이후 연산을로부터 영향을 받지않도록 val 을 권장 
