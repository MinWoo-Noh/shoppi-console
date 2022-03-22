fun main(args: Array<String>) {
    /*
    Step 1. 인사말 출력 // 검색 키워드 : how to print string in kotlin
    Step 2. 사용자 이름 받기 // 검색 키워드 : how to get input from user in kotlin
    Step 3. 입력 받은 사용자 이름 출력
     */

    // Step 1
    println("Hello, Shoppi 에 오신 것을 환영합니다!")
    println("쇼핑을 계속 하시려면 이름을 입력하세요.")

    // Step 2 : 사용자에게 받은 입력값을 널 너블 String Type 값으로 반환하는 함수(readLine()) 사용
    val name = readLine()
    // Step 3 : 개행으로 생기는 빈공강을 없애주는 기능 함수 사용(trimIndent())
    println("""
        감사합니다. 반갑습니다, $name 님
        원하시는 카테고리를 입력해 주세요.
        ***===================================***
    """.trimIndent())
}