fun main() {
    print("Please insert your number : ")
    val number = readLine()!!.toInt()
    val parity = if (number % 2 == 0) "even" else "odd"
    println("Your number is $parity")
}