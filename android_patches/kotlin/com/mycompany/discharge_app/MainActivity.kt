package com.yourname.discharge_app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import io.flutter.embedding.android.FlutterActivity
import android.os.Build
import androidx.core.content.ContextCompat
import com.yourname.discharge_app.R

class MainActivity : FlutterActivity() {
    private lateinit var splashView: View
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.cover)
        }

        // 加载 splash 布局
        splashView = LayoutInflater.from(this).inflate(R.layout.splash_layout, null)
        val content = findViewById<FrameLayout>(android.R.id.content)
        content.addView(splashView)

        handler.postDelayed({
            content.removeView(splashView)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = android.graphics.Color.TRANSPARENT
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            }
        }, 2000) // 2秒后移除遮盖页
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
