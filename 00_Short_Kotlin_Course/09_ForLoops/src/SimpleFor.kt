fun main() {

    println("Increment --> Ascending --> Upwards")
    // simple for loop in Kotlin
    // with step - > ascending --> upward
    for (i in 0..100 step 10) {
        println("Ascending loop with step  $i ")
    }
    println("Decrement --> Descending --> Downwards")
    // simple for loop with step descending --> downward
    for (i in 100 downTo 0 step 10) {
        println("Descending loop with step $i")
    }
    println("End App")
}