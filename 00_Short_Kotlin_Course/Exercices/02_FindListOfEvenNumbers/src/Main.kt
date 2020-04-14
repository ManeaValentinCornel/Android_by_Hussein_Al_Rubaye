fun main(){
    loop@for(i in 0..100 step 2){
      if(i==0)continue@loop
        println(i)
    }
}