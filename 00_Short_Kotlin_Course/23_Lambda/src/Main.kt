fun sum(num1: Int, num2: Int): Int {
    return num1 + num2
}
//converting the previous function as lambda
//exactly the same ass the sum function
//lambda is a way of writing your functions, is makes it easier

//first declare the function-> variable for us
//after that declaring the variables
val difference = { minuend: Int, subtrahend: Int ->
    minuend - subtrahend
}
val sum = { addend: Int, addend2: Int ->
    addend + addend2
}

val product = { multiplicand: Int, multiplier: Int ->
    multiplicand * multiplier
}

val quotient = { divident: Double, divisor: Double ->
    divident / divisor
}


fun main() {
    val addNumbers = sum(3, 5)
    //calling the regular function
    println(addNumbers)
    val mySum = sum(1, 2)
    //calling the lambda function
    println(mySum)

    val listOfNumbers = Array(6) { 0 }
    //regular way of aprsing a List
    for (number in listOfNumbers) {
        println(number)
    }
    //Lambda way of parsing a List
    listOfNumbers.forEach { number ->
        println(number)
    }
}

