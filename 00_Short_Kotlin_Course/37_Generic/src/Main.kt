fun main() {
    var userAdmin = UserAdmins<String>("Valentin")
    var userAdmin2 = UserAdmins<Int>(20)
    val client = Client<String, Int>("S33ff", 20405)
    display("Hei")
    display(50)
}
//template T

class UserAdmins<T>(var credit: T) {
    init {
        println(credit)
    }
}

//templates T,F
class Client<T, F>(var key: T, var code: F) {
    init {
        println("Key is ${this.key} and the code is ${this.code}")
    }
}

//function template
fun <T> display(process: T) {
    println(process)
}