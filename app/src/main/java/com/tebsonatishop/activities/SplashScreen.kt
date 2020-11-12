package com.tebsonatishop.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.R


class SplashScreen : AppCompatActivity() {
    @SuppressLint("ResourceAsColor") override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#5EB14E"))
        }


        Handler().postDelayed(Runnable { /* Create an Intent that will start the Main Activity. */
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            this.finish()
        }, 500)

    }
}