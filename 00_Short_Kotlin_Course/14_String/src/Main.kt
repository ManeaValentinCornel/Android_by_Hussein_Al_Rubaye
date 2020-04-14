fun main() {

    val message: String = " Welcome to New York"
    val name = " Valentin"
    val allMessage = "$name,$message"
    println(allMessage[0])
    println(allMessage.toLowerCase())
    println(allMessage.toUpperCase())
    println(allMessage.trim())
    println(allMessage)
    var listOfTokens = message.trim().split(" ")//list of tokens
    for (token in listOfTokens) {
        if (!token.contains("to") && !token.contains("is")) {
            println("token: $token")
        }

    }
}