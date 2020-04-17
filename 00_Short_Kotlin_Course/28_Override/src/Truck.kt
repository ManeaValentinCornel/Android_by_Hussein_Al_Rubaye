class Truck(name: String, var year: Int) : Car(name) {


    override fun getCarName(): String {
        return this.name
    }
}