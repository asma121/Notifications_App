package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etText:EditText
    lateinit var button: Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etText=findViewById(R.id.etText)
        button=findViewById(R.id.button)

        button.setOnClickListener {
            if (etText.text.isNotEmpty()){
                createNotification(etText.text.toString())
            }else{
                Toast.makeText(this,"Enter message please",Toast.LENGTH_LONG).show()
            }
            etText.text.clear()
        }
    }

    fun createNotification(m:String){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channelId,description,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            var builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Notification")
                .setContentText(m)
            notificationManager.notify(1234, builder.build())
        } else {
            var builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Notification")
                .setContentText(m)
            notificationManager.notify(1234, builder.build())
        }
    }
}