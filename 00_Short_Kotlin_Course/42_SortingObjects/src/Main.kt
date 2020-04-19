//because Person is not an comprable object as the String is(Comparable being an interace)
class Person(var name: String, var age: Int) : Comparable<Person> {
    //Comparable interface to sort the Onject by a criteria into an array
    override fun compareTo(other: Person): Int {
        //ascending order
        return this.age - other.age

    }

}

fun main() {
    val listName = ArrayList<Person>()
    listName.add(Person("Monica", 25))
    listName.add(Person("Valentin", 21))
    listName.add(Person("Cornel", 20))
    listName.add(Person("Bobo", 5))
    listName.sort()
    for (name in listName) {
        println(name.name)
    }

}

