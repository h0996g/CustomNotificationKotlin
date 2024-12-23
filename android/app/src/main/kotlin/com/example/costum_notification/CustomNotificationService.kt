package com.example.costum_notification

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.example.costum_notification.R

class CustomNotificationService : Service() {
    private lateinit var windowManager: WindowManager
    private var notificationView: View? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val content = intent?.getStringExtra("content") ?: "Custom Notification"
        showNotification(content)
        return START_STICKY
    }

    private fun showNotification(content: String) {
        if (notificationView != null) {
            // Notification already showing, don't create a new one
            return
        }

        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        notificationView = inflater.inflate(R.layout.custom_notification_layout, null)

        notificationView?.findViewById<TextView>(R.id.notification_text)?.text = content

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START
        params.x = 0
        params.y = 100

        notificationView?.setOnClickListener {
            removeNotification()
        }

        try {
            windowManager.addView(notificationView, params)
        } catch (e: Exception) {
            // Handle exception (e.g., permission not granted)
            removeNotification()
        }
    }

    private fun removeNotification() {
        notificationView?.let {
            try {
                windowManager.removeView(it)
            } catch (e: IllegalArgumentException) {
                // View not attached, ignore
            }
            notificationView = null
        }
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        removeNotification()
    }
}
