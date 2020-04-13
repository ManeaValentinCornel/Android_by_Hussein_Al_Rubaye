fun main() {
    //more like a small exercise
    for (i in 1..2) {
        print("Enter your name: ")
        val name = readLine()!!.toString()
        print("Where do you live: ")
        val livePlace = readLine()!!.toString()
        println("Enter number of pets: ")
        val petsCount = readLine()!!.toInt()
        var petName: String? = ""
        for (petID in 1..petsCount) {
            print("Enter the Pet $petID: ")
            petName += readLine()!!.toString() + ", "
        }


        println("===== User Info ====")
        println("Name : $name")
        println("Live place : $livePlace")
        println("petName: $petName")
        if (petName!!.contains("Rabbit")) {
            println("$name loves the rabbits")
        }

    }
}