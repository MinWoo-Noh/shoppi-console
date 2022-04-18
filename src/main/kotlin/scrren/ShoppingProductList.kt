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
    //  배열에서 제공하는 groupBy() 연산 사용
    // groupBy() 는 Map <key,value> 타입으로 만들어 주며
    // key : group 을 묶어줄 조건, value : key 조건에 만족하는 원소들 리스트
    // categories : Map<String, List<Product>> = groupBy(keySelector: (T) -> K)
    // categories[key] 키에 맞는 원소의 리스트를 불러오는 구조
    private val categories : Map<String, List<Product>> = product.groupBy { product ->
        product.categoryLavel
    }

    // 사용자가 요청한 상품을 출력하는 함수
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