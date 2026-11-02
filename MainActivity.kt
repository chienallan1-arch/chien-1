package com.example.lineautorecorder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#0B0F17"))
            setPadding(64, 96, 64, 64)
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val title = TextView(this).apply {
            text = "LINE 通話錄音 (小米專用版)"
            textSize = 22f
            setTextColor(Color.parseColor("#06C755"))
        }

        val desc = TextView(this).apply {
            text = "\n• 狀態：背景監聽服務待命中\n" +
                   "• 觸發條件：LINE 接通滿 3 秒自動開始錄音\n" +
                   "• 停止條件：LINE 掛斷立即停止並存檔\n" +
                   "• 儲存位置：MIUI/sound_recorder/app_rec/\n" +
                   "• 隱私安全：本 App 無網路權限，不讀取訊息內容\n"
            textSize = 14f
            setTextColor(Color.parseColor("#E2E8F0"))
        }

        val btnAuth = Button(this).apply {
            text = "檢查/開啟「通知存取權限」"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
            }
        }

        layout.addView(title)
        layout.addView(desc)
        layout.addView(btnAuth)
        setContentView(layout)
    }
}