import java.io.FileReader
import java.io.FileWriter

fun main() {
    println("1-write\n2-read \n")
    //val means read only
    val op = readLine()!!.toInt()
    println()
    when (op) {
        1 -> writeToFile(readLine()!!.toString())
        2 -> readFromFile()
        else -> println("Wrong")
    }


}

fun writeToFile(str: String) {
    try {
        val fo = FileWriter("test.txt", true)
        fo.write(str)
        fo.write("\n")
        fo.close()

    } catch (ex: Exception) {
        print(ex.message)
    }
}

fun readFromFile() {
    try {
        val fin = FileReader("test.txt")
        var c: Int?
        //allow us to read character by character the file
        //why do while ? -> because we need first reed the charcater and after check fi the character is != -1
        do {
            c = fin.read()
            print(c.toChar())


        } while (c != -1)//whenever there would be some data... the c will be different from -1


    } catch (ex: Exception) {
        println(ex.message)
    }
}
