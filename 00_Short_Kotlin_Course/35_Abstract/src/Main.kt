abstract class CreditCard(val ID:Int){
    fun CreditID():Int{
        return this.ID
    }

    abstract fun updateCreditID():Int
}
class UserInfo(ID: Int,val name:String):CreditCard(ID){
    override fun updateCreditID(): Int {
        return ID+1000
    }
}
fun main(){
    val user=UserInfo(20,"Valentin")
    println(user.CreditID())
    println(user.updateCreditID())
}