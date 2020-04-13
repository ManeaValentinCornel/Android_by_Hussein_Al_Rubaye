fun main(){
    // $ --> String template
    print("Enter name : ")
    val name= readLine()!!.toString()
    print("Enter age : ")
    val age= readLine()!!.toInt()
    print("Enter GPA : ")
    val GPA= readLine()!!.toDouble()
    print("==== User Info ==== \n")
    println("Name : $name ")
    println("Age : $age")
    println("GPA : $GPA")
}