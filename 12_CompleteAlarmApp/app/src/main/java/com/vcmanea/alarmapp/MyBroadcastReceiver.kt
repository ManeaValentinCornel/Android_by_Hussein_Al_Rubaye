package com.vcmanea.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {

       if(intent?.action.equals("com.vcmanea.alarmapp")){
           val b=intent?.extras
           Toast.makeText(context,b?.getString("message"),Toast.LENGTH_LONG).show()
           val notitfyMe= context?.let { Notifications(it) };
           notitfyMe?.createNotification()
       }

        else if(intent?.action.equals("android.intent.action.BOOT_COMPLETED")){
           val saveData=SaveData(context!!)
           saveData.setAlarm(saveData.getHour(),saveData.getMinute())
           Toast.makeText(context,saveData.getHour().toString(),Toast.LENGTH_LONG).show()
       }
    }




}