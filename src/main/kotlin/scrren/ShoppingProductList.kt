package scrren

import data.Product

// Category 별 상품 List(목록)을 관리하고, 사용자가 요청한 상품 표시하는 클래스
class ShoppingProductList {
    private val product = arrayOf(
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
    private val categories : Map<String, List<Product>> = product.groupBy { product ->
        product.categoryLavel
    }

    fun showProducts(selectedCategory : String){
        val categoryProducts = categories[selectedCategory]
        if (!categoryProducts.isNullOrEmpty()){
            println("""
                ***===================================***
                선택하신 [$selectedCategory] 카테고리 상품입니다.
                """.trimIndent())
            val productSize = categoryProducts.size
            for (index in 0 until productSize){
                println("${index}. ${categoryProducts[index].name}")
            }
        } else {
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String){
        println("[$selectedCategory] 카테고리의 상품이 등록되기 전입니다.")
    }
}