//same name different dataype
fun showInfo(cardID: Int) {
    //check if the card is valid
    print("CardID: $cardID")
}

fun showInfo(name: String) {
    //check is the user is active
    print("Name $name")
}

fun main() {
    showInfo("Manea Valetin")
    showInfo(27)
}