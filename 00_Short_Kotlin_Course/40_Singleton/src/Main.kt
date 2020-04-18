class Singleton {
    var name: String? = null

    private constructor() {
        println("Singleton instance is created")
    }

    //lazy function when the constructor will be first called will create an instance
    //because is private can be called within the class
    //lazy-- > says he already has an instance of this class--> and will return the previous one
    //so the constructor will be called only once
    companion object {
        val instace: Singleton by lazy { Singleton() }
    }

}


fun main() {
    //every instance call will return same object
    var s1 = Singleton.instace
    s1.name = "Bobo"
    var s2 = Singleton.instace
    var s3 = Singleton.instace
    println(s1.name)
    println(s2.name)
    println(s3.name)

}