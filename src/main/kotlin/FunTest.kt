//open class Shape(vertexCount : Int){
//    init {
//        println("ShaperClass 초기화, vertexCount : $vertexCount ")
//    }
//
//    open fun draw(){
//        println("Drawing a shape")
//    }
//}
//
//class Rectangle(vertexCount: Int) : Shape(vertexCount){
//    init {
//        println("Rectangle 초기화, vertexCount : $vertexCount ")
//    }
//
//    override fun draw() {
//        super.draw()
//        println("Drawing a rectangle")
//    }
//}
//
//fun main() {
//    val rectangle = Rectangle(4)
//    rectangle.draw()
//}

fun main(){
    val padding = Product("패션","겨울패딩")
    val jacket = padding.copy(name = "자켓")
    println(jacket)
}

data class Product(val category : String, val name : String = "")