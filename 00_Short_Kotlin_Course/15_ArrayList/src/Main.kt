fun main() {
    val list = ArrayList<String>()
    do {
        println("Please enter yor pet name ")
        var petName = readLine()!!.toString()
        list.add(petName)
    } while (petName.trim().toLowerCase() != "quit")

    for (pet in list) {
        println("Pet $pet")
    }

    if(list.contains("Abi")){
        println("Abi was a great dog.")
    }

}