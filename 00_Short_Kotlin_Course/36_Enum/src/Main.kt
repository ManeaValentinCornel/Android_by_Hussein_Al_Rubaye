enum class Direction {
    //collection of similar information, you can add it to enum
    NORTH,
    SOUTH, EAST, WEST
}

fun main() {
    var direction: Direction = Direction.NORTH

    when (direction) {
        Direction.NORTH -> println("NORTH")
        Direction.EAST -> println("East")
        Direction.SOUTH -> println("South")
        else -> {
            println("West")

        }
    }


}