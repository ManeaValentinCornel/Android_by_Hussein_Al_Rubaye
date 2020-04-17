//interface can't have a constructor
interface CreditCard {
    val cardNumber: Int;
    fun score(age: Int)

}

class Visa(override val cardNumber: Int) : CreditCard {
    override fun score(age: Int) {
        if (age > 40) {
            println("Score negative")
        } else {
            println("Score positive")
        }
    }
}

class MasterCard(override val cardNumber: Int) : CreditCard {

    override fun score(age: Int) {
        if (age > 50) {
            println("Score negative")
        } else {
            println("Score positive")
        }
    }
}

//paypall company doesn't know the who is the client
class PayPall(client: CreditCard) : CreditCard by client {
    fun print() {
        println(this.cardNumber)
    }

}

fun main() {
    val visa = Visa(2020231321)
    visa.score(45)

    val masterCard = MasterCard(43214214)
    masterCard.score(45)

    val payPal = PayPall(masterCard)
    val payPal2 = PayPall(visa)
    payPal.print()
    payPal2.print()

}
