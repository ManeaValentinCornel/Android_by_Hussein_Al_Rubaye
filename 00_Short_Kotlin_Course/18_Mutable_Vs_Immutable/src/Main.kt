//mutable can be updated
//im-mutable : cannot be updated
fun main() {
    //1- list
    val listImmutable = listOf("Hei", "Hello")
    for (name in listImmutable) {
        println(name)
    }

    var muttableList = mutableListOf("Java", "Kotlin")
    for (name in muttableList) {
        println(name)
    }

    //Mmutable map
    val listOfUsersMap = mapOf(1 to "Bobo", 2 to "Rici")
    //Immutable map
    var listOfUsersMapImmutable = mutableMapOf(1 to "Bobo", 2 to "Cosim")
    



}