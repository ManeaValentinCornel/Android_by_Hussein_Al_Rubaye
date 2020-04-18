class Outer {
    private val name: String? = null

    class NestedClass {
        fun Show() {
            println("Nested")
        }
    }

    inner class InnerClas {
        fun show() {
            println(name)
        }
    }
}
//Difference between initialising a nested and a nested inner class
//Nested class cannnot acces outer class properties when the inner one can
fun main() {
    val outer = Outer()
    val nested = Outer.NestedClass()
    val inner = Outer().InnerClas()

}


