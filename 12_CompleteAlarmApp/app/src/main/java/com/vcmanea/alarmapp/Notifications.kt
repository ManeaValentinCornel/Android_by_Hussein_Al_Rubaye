package com.vcmanea.alarmapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notifications(var context: Context) {

    private val CHANNEL_ID="My channel"
    fun createNotificationChannel()
    {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name=context.getString(R.string.channel);
            val descriptionText = context.getString(R.string.channel_desc)
            val importance=NotificationManager.IMPORTANCE_DEFAULT
            val channel=NotificationChannel(CHANNEL_ID,name,importance).apply { description=descriptionText }


            // Register the channel with the system
            val notificationManeger:NotificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            notificationManeger.createNotificationChannel(channel)

        }





    }

    fun createNotification() {
        val NOTIFICATION_ID = 1000
        var builder = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My notification")
            .setContentText("Much longer text than can feet one line...")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Much longer text than can feet one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager=NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID,builder.build())


    }


}