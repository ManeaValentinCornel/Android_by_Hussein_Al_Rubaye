import java.util.*

fun main() {
    //!! null assertion operator
    val yearInDevice = Calendar.getInstance().get(Calendar.YEAR)
    print("Enter the year of birth: ")
    val yearOfBirth = readLine()!!.toInt()
    val age = yearInDevice - yearOfBirth
    print("You are $age years old")
}