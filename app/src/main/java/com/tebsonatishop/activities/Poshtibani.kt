package com.tebsonatishop.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.R
import kotlinx.android.synthetic.main.address_ha.imgExit
import kotlinx.android.synthetic.main.poshtibani.*


class Poshtibani : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poshtibani)


        txShomareTamas.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data =
                Uri.parse("tel:+" + "98 9397310149")
            startActivity(callIntent)
        }

        txEmail.setOnClickListener {
            val emailintent = Intent(Intent.ACTION_SEND)
            emailintent.type = "plain/text"
            emailintent.putExtra(
                Intent.EXTRA_EMAIL, arrayOf("mahsolat_salem@gmail.com")
            )
            emailintent.putExtra(Intent.EXTRA_SUBJECT, "پشتیبانی محصولات سالم")
            emailintent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(emailintent, ""))

        }


        imgExit.setOnClickListener { finish() }

    }
}