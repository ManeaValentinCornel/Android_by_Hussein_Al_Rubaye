class Truck(name:String,age:Int, color:String,val miles:Double):Car(name,age,color) {

    fun showPrice(){
        //cannot be accessed not even from its child class
//        this.age=20
    }

}