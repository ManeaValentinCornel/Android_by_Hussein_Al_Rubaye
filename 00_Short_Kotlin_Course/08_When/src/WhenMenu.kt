fun main() {
    print("Choose your food : ")
    val foodID = readLine()!!.toInt()

    when (foodID) {
        1 -> {
            println("You got Sandwich, ")
            print("Salad")
        }

        10 -> {
            println(" You got a burger")
        }

        else -> {
            print("You did not order anything")
        }
    }

    //Expression condition
    val isMarried=false
    val gpa=3.8
    val isQualified=if(isMarried==true && gpa>=3.8) 1 else 0
    print(isQualified)
    //Expression condition + when
    val isGood : Boolean=when(gpa){
        4.0 -> true
        else-> false
    }
}