//you can't have exactly the samefunction with the same parameters and with the same name
//fun showInfo(number:String){}
//global variable
var name:String?=null
fun showUserInfo(){
    println("Name: $name")
}
//you can call the class level(global ones) from another class within the same packet methods
fun main(){
    //local variable
     name="Valentin"
//    showInfo("Bobo")
    showUserInfo()
}