//parallel processing or multi-threading  in Java
//UI interface doesn't freeze when we do something... maybe in the background
//no block works, while doing another job
//fetching data from the server will block the UI... if you don't use parallel processing
// Asynchronous vs synchronous
//Synchronous -> First task need to finish for the following one to start
//Asynchronous-> Both task can work in the same time --> can save time execution


fun main(){
    val myThread=UserThread()
    val mySecondThread=UserThread()
    myThread.start()
    myThread.join()//this thread needs to finish before the next one will be executed
    //if you call the run metrhod this particualr one will just call the run method with our class
    //without being considered as a thread ->> that will run synchronous
    mySecondThread.start()


}

class UserThread():Thread(){
    //everything will be run in the run function will be executed asynchronous
    override fun run() {
        var count:Int=0
        while(count<10){
          println("Count is $count + $name")
            count++
            try {
                sleep(3000)
            }
            catch (ex:Exception){
                ex.message
            }
        }

    }

}