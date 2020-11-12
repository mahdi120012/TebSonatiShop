package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.address_ha.*
import kotlinx.android.synthetic.main.address_ha.imgExit
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.row_addressha.*
import kotlinx.android.synthetic.main.setting.*
import java.util.*

class Setting : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)


        clAboutMe.setOnClickListener {
            val i = Intent(this, AboutMe::class.java)
            startActivity(i)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

        clPoshtibani.setOnClickListener {
            val i = Intent(this, Poshtibani::class.java)
            startActivity(i)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

        imgExit.setOnClickListener { finish() }

    }
}