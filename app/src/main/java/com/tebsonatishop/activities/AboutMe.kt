package com.tebsonatishop.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.R
import kotlinx.android.synthetic.main.address_ha.imgExit
import kotlinx.android.synthetic.main.poshtibani.*


class AboutMe : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_me)

        imgExit.setOnClickListener { finish() }

    }
}