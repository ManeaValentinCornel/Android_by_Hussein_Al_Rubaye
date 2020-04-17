package constructorWay

open class Car {
    init{
        println("firstWay.Car")
    }
    var name:String?=null
    var price:Int?=null
    constructor(name : String, price:Int){
        this.name=name
        this.price=price
    }
}