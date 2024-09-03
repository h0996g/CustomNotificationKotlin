package com.example.costum_notification

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.costum_notification.R


class CustomNotificationService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var notificationView: View

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        return START_STICKY
    }

    private fun showNotification() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        notificationView = inflater.inflate(R.layout.custom_notification_layout, null)

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

        notificationView.setOnClickListener {
            windowManager.removeView(notificationView)
            stopSelf()
        }

        windowManager.addView(notificationView, params)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        if (::notificationView.isInitialized) {
            windowManager.removeView(notificationView)
        }
    }
}
