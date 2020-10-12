package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.tebsonatishop.R
import kotlinx.android.synthetic.main.address_ha.*

class AddressHa : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_ha)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#c82c15"))
        }

        clAddAddress.setOnClickListener {
            startActivity(Intent(this, Address::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

        imgExit.setOnClickListener { finish() }




    }
}