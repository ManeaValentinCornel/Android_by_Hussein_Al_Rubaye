fun main() {
    //every variable has address and value
    //whenever you want to access that value you need to point to the address
    val str = "Valentin, Welcome to Kotlin"
    println(str)
    println(str)
    println(str)
    //explicitly specify the type whenever you want just to declarare the variable, not to define it
    val str2: String
    //val immutable variable
    val name = "Valentin"
    val age = 30
    val GPA = 3.8

    println("==== User info ====")
    println("Name: $name")
    println("Age: $age")
    println("GPA: $GPA")

    //var is mutable variable
    var count = 1
    println("Count : $count")
    count = 2

    //? allow a variable to be defined as null
    var departement: String?
    departement = null
    //null safety operator in --> doesn't print null, throws a null pointer exception instead
    print("Department ${departement!!}")


}
