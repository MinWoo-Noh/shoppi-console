
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