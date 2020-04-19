package com.vcmanea.findmyage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnGetAgeClick(view: View){
        buttonGetAgeClick()
    }

    fun buttonGetAgeClick(){
        val userDOB=Integer.parseInt(etDOB.text.toString())
        val currentYear=Calendar.getInstance().get(Calendar.YEAR)
        val userAgeInYears=currentYear-userDOB
        twResult.text="Your age is : $userAgeInYears"
        Log.d("Logs me","Your age is $userAgeInYears years ")
    }


}
