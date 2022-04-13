package scrren

class ShoppingHome {
    fun start() {
        showWelcomeMessage()
        showCategories()
    }

    /**
     *  인사를 출력하는 함수
     */
    private fun showWelcomeMessage() {

        // Step 1
        println("Hello, Shoppi 에 오신 것을 환영합니다!")
        println("쇼핑을 계속 하시려면 이름을 입력하세요.")

        // Step 2 : 사용자에게 받은 입력값을 널 너블 String Type 값으로 반환하는 함수
        // (readLine() :: Reads a line of input from the standard input stream)
        val name = readLine()
        // Step 3 : 개행으로 생기는 빈공강을 없애주는 기능 함수 사용(trimIndent())
        println(
            """
            감사합니다. 반갑습니다, $name 님
            원하시는 카테고리를 입력해 주세요.
            ***===================================***
        """.trimIndent()
        )
    }

    fun showCategories() {
        val showCategory = ShoppingCategory()
        showCategory.showCategories()
    }
}