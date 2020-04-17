package com.car.maintain

//init will be called automatically anytime someone creates a instance of this class
class Car(var type: String, val model: Int, val price: Double, val milesDrive: Int, val owner: String) {
    init {
        println("Class object is created")
    }

    fun getCarPrice(): Double {
        return this.price - (this.milesDrive.toDouble() * 10)
    }
}

fun main() {
    //car is an instance of the car class
    val car = Car("BMW", 2015, 10000.0, 105, "Valentin")
    //getters and setter buildin Kotlin functionality
    car.type = "Honda"
    println(car.type)
    println(car.owner)
    println(car.getCarPrice())
    val car2 = Car("Toyota", 2019, 67000.0, 10, "Jena")
    println(car2.type)
    println(car2.owner)
    //listOfCars

    val listOfCar = arrayListOf<Car>()
    //listOfCar.add(car)
    listOfCar.add(Car("Toyota", 2019, 67000.0, 10, "Jena"))
    listOfCar.add(car)
    listOfCar.add(car2)

    for (car in listOfCar) {
        print("-------------")
        println(car.owner)
        print(car.getCarPrice())
    }

}