//function which have same names, but with diffent number of parameters or with parameters that are another type
fun add(number1:Int,number2:Int):Int{
    return number1+number2;
}

fun add(number1:Int,number2: Int, number3:Int):Int{
    return number1+number1+number3
}

fun add(number1:Int,number2: Int, number3:Int,number4:Int):Int{
    return (number1+number1+number3+number4)
}


fun main(){
    print(add(1,2))
    println(add(1,10,20))
    println(add(1,2,3,4))

}
