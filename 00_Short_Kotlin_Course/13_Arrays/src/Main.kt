/*
search time= o(n)
access time->1
insert -> !0(n)= first you shift the elemnts after you add the element
delete-> first you delete than you move the elemet -> !O(n)
 */
fun main() {

    //Array of Strings
    val listOfPets: Array<String> = Array(5) { "" }//if it is a string initializes it with double quotes
    val maxSize = listOfPets.size
    //instead of 0..maxsize-1 -> 0 until maxsize
    for (i in 0 until maxSize) {
        listOfPets[i] = readLine()!!.toString()
    }
    for (i in listOfPets) {
        print("Pet $i: ")
    }

    //Array if Int
    val listOfNumbers: Array<Int> = Array(5){0}
    for(i in 0 until listOfNumbers.size){
        println(i)
    }

}