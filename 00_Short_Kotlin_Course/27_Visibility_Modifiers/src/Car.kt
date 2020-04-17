open class Car {
    var name:String?=null
    private var age:Int?=null
    protected var color: String?=null
    constructor(name:String,age:Int,color:String){
        print("Constructor Car")
        this.age=age
        this.name=name
        this.color=color
    }

    init{
        println("Init Car")
    }


}