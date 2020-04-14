fun main() {

    println("Continue in KOTLIN")
    for (number in 1..5) {
        if (number == 3) continue
        println("Continue: $number")
    }

    println("Break in KOTLIN")
    bobo@ for (number in 1..3) {
        //inner break example
        println("outerloop $number")

        for (i in 10..50 step 10) {
            println("innerLoop $i")
            if (i == 30) break@bobo
        }

        if (number == 3) break


    }
}