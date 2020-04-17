interface CreditCard {
    val cardNumber: String
    fun score(age: Int)
}

//the classes don't inherit nothing...
//implementing the interface base on its own structure
//a way of designing your code when you have something shared --> for example a structure
class MasterCard(override val cardNumber: String) : CreditCard {
    override fun score(age: Int) {
        if (age > 50) {
            println("Negative")
        } else {
            println("Positive")
        }
    }
}

class VisaCard(override val cardNumber: String) : CreditCard {
    override fun score(age: Int) {
        if (age > 60) {
            println("Negative")
        } else {
            println("Positive")
        }
    }
}

