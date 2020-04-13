fun main() {
    print("Insert number one : ")
    val number1 = readLine()!!.toDouble()
    print("Insert number two : ")
    val number2 = readLine()!!.toDouble()
    //Kotlin math operators
    val sum = number1 + number2
    print("sum : $sum \n")
    val subtraction = number1 - number2
    print("subtraction : $subtraction \n")
    val multiplication = number1 * number2
    print("multiplication : $multiplication \n")
    //very important in Kotlin division by 0 doesn't throw division by 0 exception
    val division=number1/number2
    print("division : $division \n")

}