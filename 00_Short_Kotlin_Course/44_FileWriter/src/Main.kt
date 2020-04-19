import java.io.FileWriter

fun main(args: Array<String>) {
    var str= readLine()!!.toString()
    WriteToFile(str)
}

fun WriteToFile(str: String) {

    try {
        var fo = FileWriter("test.txt",true)//open the file
        //true parameter means if have the file... just append the data to the current file... don't override the existing data
        fo.write(str)//write the string to the file
        fo.write("\n")
        fo.close()// close the file
    } catch (ex: Exception) {
        print(ex.message)
    }


}