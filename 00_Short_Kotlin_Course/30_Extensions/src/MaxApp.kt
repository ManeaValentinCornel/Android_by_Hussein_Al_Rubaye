fun main(){
    val listOfElements =ArrayList<Int>()
    listOfElements.add(13)
    listOfElements.add(9)
    listOfElements.add(0)
    listOfElements.add(11)

    listOfElements.findMax()

}



fun ArrayList<Int>.findMax(){
    var maxNumber=0
    for(item in this){
        if (item>maxNumber)maxNumber=item
    }
    print(maxNumber)
}