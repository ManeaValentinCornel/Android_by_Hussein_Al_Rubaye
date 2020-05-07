package com.vcmanea.alarmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notitfyMe=Notifications(this)
        notitfyMe.createNotificationChannel()
        setContentView(R.layout.activity_main)
        btnSetTime.setOnClickListener {
            val popTime = PopTime()
            val fm = supportFragmentManager

            popTime.show(fm, "Select Time")

        }
    }

    fun setTime(hours: Int, minute: Int) {
        val s = "$hours : $minute"
        tvShowTime.text = s

        val saveData = SaveData(applicationContext)
        saveData.saveData(hours,minute)
        saveData.setAlarm(hours, minute)

    }


}
