package be.marche.www.utils


import android.app.*
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import be.marche.www.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MessagingUtils(val application: Application) {

    fun createNotificationChannelinit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = application.getString(R.string.default_notification_channel_id)
            val channelName = application.getString(R.string.default_notification_channel_name)
            val notificationManager = application.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }

    fun subscribe() {
        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener(application as Activity)
            { task ->
                var msg = application.getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = application.getString(R.string.msg_subscribe_failed)
                }
                Log.d("zeze subscr ici", msg)
                Toast.makeText(application, msg, Toast.LENGTH_LONG).show()
            }
    }

    fun getToken() {
        Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener
        { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "zeze miain ici",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "token $token"
            Log.d("zeze miain ici", msg)
            Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
        })
    }

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = application.getString(R.string.default_notification_channel_id)
            val name = application.getString(R.string.channel_name)
            val descriptionText = application.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}

