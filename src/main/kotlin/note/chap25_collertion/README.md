# 25강 - 단일 객체 생성 & 입력 요청 공통화

- collections, object, Extension functions, Scope functions

## Collection 의 interface 의 관계

![img.png](img.png)

1. List (순서를 가진 컬렉션)

- ordered collection
- index 로 원소에 접근 가능 listOf(1, 2, 2) // [1, 2, 2]

2. Set (순서 X, 원소는 중복 X)

- unique elements setOf(1, 2, 2) // [1, 2]

3. Map

- key-value pairs mapOf("first" to 1, "second" to 2, "second" to 3) // {first = 1, second = 3}

Type

- list, set, Map 은 Immutable Type 이기 떄문에 생성한 값을 이후에 생성할 수 없다.
  (1) read - only

~~~kotlin
val numbers = listOf("one", "two", "three", "four")
val numbersIterator = numbers.iterator()
while (numbersIterator.hasNext()) {
    println(numbersInterator.next())
}

// 결과
/*
one
two
three
four
 */
~~~

(2) mutable

- MutableList, MutableSet, MutableMap 은 원소를 수정할 수 있다.

~~~kotlin
// mutableListOf는 순서를 가지면서 변경이 가능한 컬렉션이다.
val numbers = mutableListOf("one", "two", "three", "four")
val mutableInterator = numbers.iterator()

mutableInterator.next()
mutableInterator.remove()
println("After removal: $numbers")

//결과 ::  "After removal: [two, three, four]
~~~

### Collection operations - Retrieve

#### position

- index

~~~kotlin
val list = listOf(1, 2, 3, 4, 5)
println("${list.elementAt(0)}") // 해당 인덱스 출력
println("${list.elementAtOrNull(5)}") // 해당 인덱스가 없을 경우 Null 반환
println("${list.elementAtOrElse(5) { -1 }}") // 해당 인덱스가 없을 경우 -1(지정) 반환
~~~

- 확장함수 (빈번하게 요창하는 특정 데이터)

~~~kotlin
val list = listOf(1, 2, 3, 4, 5)
println("${list.first()}, ${list.firstOrNull()}") // 0번째 데이터가 없는 경우 null 반환
println("${list.last()}, ${list.lastOrNull()}") // 마지막 데이터가 없는 경우 null 반환
~~~

- condition (선언한 조건에 일치하는 데이터 요청)
  함수의 argument 로 조건 전달

~~~kotlin
val list = listOf(1, 2, 3, 4, 5)
// it은 list 의 원소를 의미하고, list 의 원소를 첫번째 부터 꺼내 3보다 큰 값인지 확인한다. 3보다 큰 첫번째 값을 반환하며, 존재하지 않으면 null 을 반환한다.
println("${list.firstOrNull { it > 3 }}")
println("${list.find { it > 3 }}") // 의와 같은 동작을 함.

// 결과 (3보다 큰 첫번째 값 -> 4)
// 4
// 4

// {} 중괄호로 표현한 표현식은 람다식이다.
// list 의 원소를 마지막번째 부터 꺼내 3보다 큰 값인지 확인한다. 3보다 큰 첫번째 값을 반환하며, 존재하지 않으면 null 을 반환한다.
println("${list.lastOrNull { it > 3 }}")
println("${list.findLast { it > 3 }}")

// 결과 (3보다 큰 첫번째 값 -> 5)
// 5
// 5
~~~

### Collection operations - Transformation(변신, 변화)

- Map

~~~kotlin
val numbers = setOf(1, 2, 3)
println(numbers.map { it * 3 }) // 원소에 3씩 곱합
println(numbers.mapIndexed { index, value -> value * idx })

// 결과
// [3, 6, 9]
// [0, 2, 6]
~~~

- String representation(묘사, 나타내다)
~~~kotlin
val numbers = listOf("one", "tow", "three", "four")
// joinToString() 문자열을 조작하는 함수
println(numbers.joinToString(
    separator = " | ", // 문자열 구분자
    prefix = "start: ", // 첫번째 붙일 문구 
    postix = ": end" // 마지막에 붙일 문구
))

// 결과
// start: "one" | "tow" | "three" | "four": end
~~~

### Collection operations - Filtering

- predicate (조건의 참, 거짓을 판별하고 연산에따라 데이터를 분류)
~~~kotlin
val numbers = listOf("one", "tow", "three", "four")
// 문자열 길이가 3보다 큰지 확인하고, 조건에 맞는 문자열만 반환
val longerThan3 = numbers.filter { it.length > 3 }
println(longerThan3)

// 결과
// [three, four]
~~~

~~~kotlin
val numbers = listOf("one", "tow", "three", "four")

println(numbers.any { it.endsWith("e") }) // 조건중 하나라도 맞는 원소가 있다면 true 반환
println(numbers.none { it.endsWith("a") }) // 조건중 원소가 하나도 맞지않으면 true 반환
println(numbers.all { it.endsWith("e") }) // 모든 원소가 조건을 만족해야만 true

// 결과
// true
// true
// false
~~~

- partition (파티션 :: 칸막이, 분할)
~~~kotlin
val numbers = listOf("one", "tow", "three", "four")
// partition() 는 조건의 막족하는 값과 만족하지 않는 값을 분류
val (match, rest) = numbers.partition { it.length > 3 }

// 결과
// [three, four]
// [one, tow]
~~~

### Collection operations - Grouping

~~~kotlin
private val products = arrayOf(
        Product("패션","겨울 패딩"),
        Product("패션","겨울 바지"),
        Product("전자기기","블루투스 이어폰"),
        Product("전자기기","노트북"),
        Product("반려동물용품","건식사료"),
        Product("반려동물용품","건식사료"),
        Product("반려동물용품","습식사료"),
        Product("반려동물용품","치약"),
        Product("반려동물용품","간식")
    )

// 그루핑은 데이터를 그룹핑 할수있는 기준값을 Key 로갖는 Map Type 으로 변환해주는 연산인데 groupBy 가 이에 해당한다.
// products 를 groupBy 함수로 호출하고, 기준 값을 categoryLabel 지정하면, categoryLabel 값을 key 값으로 갖는 Map type 으로 변환 
private val categories : Map<String, List<Product>> = product.groupBy { product ->
    product.categoryLabel // data class Product의 
}

// 결과
{
    패션 = [
        Product(categoryLabel = 패션, name = 겨울 패딩),
        Product(categoryLabel = 패션, name = 겨울 바지)
    ],
    전자기기 = [
        Product(categoryLabel = 전자기기, name = 블루투스 이어폰),
        Product(categoryLabel = 전자기기, name = 노트북)
    ],
    전자기기 = [
        Product(categoryLabel = 반려동물용품, name = 건식사료),
        Product(categoryLabel = 반려동물용품, name = 건식사료),
        Product(categoryLabel = 반려동물용품, name = 습식사료),
        Product(categoryLabel = 반려동물용품, name = 치약),
        Product(categoryLabel = 반려동물용품, name = 간식)
    ]
}
~~~
