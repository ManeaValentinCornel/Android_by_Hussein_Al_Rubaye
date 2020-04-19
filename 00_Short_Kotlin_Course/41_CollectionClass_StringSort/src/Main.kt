import java.util.*
import kotlin.collections.ArrayList

fun main(){

    var listOfName=ArrayList<String>()
    listOfName.add("Valentin")
    listOfName.add("Abi")
    listOfName.add("Manea")
    //sorthing the elemts by using Collection class
    Collections.sort(listOfName)
    for (name in listOfName){
        println(name)
    }


}