package constructorWay

import constructorWay.Car

class Truck: Car {
    var color:String?=null

    constructor(name:String,price:Int, color:String):super(name,price){

    }
    init{
        println("firstWay.Truck")
    }

}