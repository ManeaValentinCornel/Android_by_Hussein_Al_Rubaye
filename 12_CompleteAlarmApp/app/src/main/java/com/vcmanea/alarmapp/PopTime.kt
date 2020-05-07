package com.vcmanea.alarmapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.pop_time.*
import kotlinx.android.synthetic.main.pop_time.view.*
import kotlinx.android.synthetic.main.pop_time.view.btnDone

class PopTime:DialogFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myView=inflater.inflate(R.layout.pop_time,container,false)
        return myView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDone.setOnClickListener {
            val ma=activity as MainActivity
            if(Build.VERSION.SDK_INT>=23) {
                ma.setTime(timePicker.hour, timePicker.minute)
            }
            else{
                ma.setTime(timePicker.currentHour, timePicker.currentMinute)
            }
            this.dismiss()
        }
    }




}