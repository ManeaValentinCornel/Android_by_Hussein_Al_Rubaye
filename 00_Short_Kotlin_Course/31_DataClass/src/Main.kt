//data class doesn't have any methdos
//and has extra set of features
data class Person(val name:String,val age:Int,val gender:String)

fun main(){

    val person=Person("Valentin",30,"Male")
    val person2=Person("Valentin",30,"Male")
    val person3=person2.copy()

    println(person.toString())
    println(person.equals(person2))

}