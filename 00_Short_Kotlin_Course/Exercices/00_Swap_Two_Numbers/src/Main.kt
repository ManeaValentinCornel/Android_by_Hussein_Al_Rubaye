fun main(){

    print("Insert x : \n")
    var x= readLine()!!.toInt()

    print("Insery y : \n")
    var y= readLine()!!.toInt()

    var temp : Int
    temp=x
    x=y
    y=temp
    println("x = $x and y = $y")

}