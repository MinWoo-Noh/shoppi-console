package scrren

class ShoppingCategory {
    /**
     * 상품 카테고리 표기 함수
     */
    fun showCategories() {
        // Step 1. 상품 카테고리 표기
        val categories = arrayOf("패션", "전자기기", "반려동물용품")
        for (category in categories) {
            println(category)
        }
        println("=> 장바구니로 이동하시려면 #을 입력해주세 ")

        var selectedCategory = readLine() // Step 2. 사용자 입력 받기
        while (selectedCategory.isNullOrBlank()) {// Step 3. 사용자가 기대하는 값을 입력하지 않는 경우에 대한 처리
            println("값을 입력해주세요")
            selectedCategory = readLine()
        }
        if (selectedCategory == "#") {
            // TODO 장바구니이동
        } else {
            if (categories.contains(selectedCategory)) {
                // TODO 카테고리 상품 목록 보여주기
            } else {
                showErrorMessage(selectedCategory)
            }
        }
    }

    /**
     * 카테고리 목록에 없는 값을 입력하는 경우 오류 메세지 함수
     */
    private fun showErrorMessage(selectedCategory: String?) {
        println("[$selectedCategory] : 존재하지 않는 카테고리입니다. 다시 입력해주세요")
        showCategories()
    }
}