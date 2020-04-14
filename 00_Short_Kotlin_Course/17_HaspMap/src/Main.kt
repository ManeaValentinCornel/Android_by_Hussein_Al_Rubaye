fun main(){
    //Hasmat key, value pairs
    var listOfUsers=HashMap<Int,String>()
    listOfUsers[1]="Valentin"
    listOfUsers[2]="Cornel"
    listOfUsers[1]="Radu"


    for (key in listOfUsers.keys){
        println("Print ID 123: ${listOfUsers[key]} ")
    }
}