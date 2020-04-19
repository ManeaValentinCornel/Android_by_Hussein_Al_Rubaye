fun main() {

    try {
        var dividend: Int = readLine()!!.toInt()
        var divisor: Int = readLine()!!.toInt()//if the divisor is there will be throw an exception
        println("Quotient: ${dividend / divisor}")
    } catch (ex: Exception) {
        //catch the exception and will applciation will not get frozen
        print(ex.message)
    }
}

