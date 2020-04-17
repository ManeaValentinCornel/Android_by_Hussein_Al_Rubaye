class Truck(name:String,price:Int,val color:String):Car(name, price){
    override fun printPrice(){
        println("The truck price is 1.2x, due color: ${1.2*price}" )
    }

    fun printPriceWrapper(){
        super.printPrice()
    }
}