open class Operation() {
    open fun sum(addend1: Double, addend2: Double): Double {
        return addend1 + addend2
    }

}

class MultiOperations:Operation()  {


   override fun sum(addend1: Double, addend2: Double): Double {
        return addend1 + addend2 *3
    }

    fun divide(divident: Double, divisor: Double): Double {
        //quotient
        return divident / divisor
    }

    fun difference(minuend: Double, subtrahend: Double): Double {
        return minuend - subtrahend
    }

    fun product(factor1: Double, factor2: Double): Double {
        return factor1 * factor2
    }

}
    fun main(){
        val operation=MultiOperations() as Operation
        val operation2=Operation()
        //it doesn't work -> veyr bad castig example
        println(operation!!.sum(20.1,30.6))
        println(operation2.sum(20.1,30.6))


    }
