//Step over and step into differences
//Step into doesn't go into the function


fun main(){
    var temp=subNumbers(20.2,10.5)
    temp=subNumbers(42.2,10.1)
    //by default kotlin will assume your first argument will be passed to first parameter
    temp=addNumbers(20.5)
    //but you can explicitly assign the value by call the variable
    temp=addNumbers(y=20.1)
    //
    displayInfo(names = *arrayOf("Jena","Leia","Dora"))

}


//function with default value
fun addNumbers(x:Double=50.0,y:Double=0.0):Double{
    return (x+y)
}
//returning function
fun subNumbers(x:Double,y:Double):Double {
    val temp = x + y;
    return temp;
}
//undetermined number of arguments
fun displayInfo(vararg names: String){
    for (name in names){
        println(name)
    }

}
