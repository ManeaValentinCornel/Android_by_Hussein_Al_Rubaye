fun main() {

    print("Enter your grade: ")
    val grade = readLine()!!.toInt()
    //regular if
    if (grade >= 90) {
        println("Your grade is A")
    }
    //in range
    // else if for another concrete condition
    else if (grade in 70..89) {
        println("Your grade is B")
    } else if (grade in 50..70) {
        println("Your grade is C")
    } else {
        println("Your grade is F")
    }
}