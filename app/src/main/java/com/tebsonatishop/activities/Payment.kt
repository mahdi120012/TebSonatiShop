package com.tebsonatishop.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.R
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.customClasses.UrlEncoderClass
import com.thefinestartist.finestwebview.FinestWebView
import com.thefinestartist.finestwebview.listeners.WebViewListener


class Payment : AppCompatActivity() {
    var price:String = ""
    var address:String = ""
    var phoneNumber:String = ""
    var codeKharid:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        price = intent.getStringExtra("price")
        address = intent.getStringExtra("address")
        phoneNumber = intent.getStringExtra("phone_number")
        codeKharid = intent.getStringExtra("code_kharid")

        var userName = SharedPrefClass.getUserId(this,"user")

        var priceEncode = UrlEncoderClass.urlEncoder(price)
        var addressEncode = UrlEncoderClass.urlEncoder(address)
        var phoneNumberEncode = UrlEncoderClass.urlEncoder(phoneNumber)
        var userNameEncode = UrlEncoderClass.urlEncoder(userName)
        var CodeKharidEncode = UrlEncoderClass.urlEncoder(codeKharid)

        val url = "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=pay&price=" + priceEncode + "&address=" + addressEncode + "&phone_number=" + phoneNumberEncode + "&username=" + userNameEncode + "&code_kharid=" + CodeKharidEncode

        val builder = FinestWebView.Builder(this)
        builder.setWebViewListener(object : WebViewListener() {
            override fun onPageStarted(url:String) {
                super.onPageStarted(url)
                if (url.contains("tavasi_tebsonati_success_payment")) {

                    val intent = Intent(this@Payment, AfterPayment::class.java)
                    startActivity(intent)
                }
            }
        })
        builder
            .rtl(true)
            .showSwipeRefreshLayout(false)
            .showMenuShareVia(false)
            .showMenuOpenWith(false)
            .showMenuCopyLink(false)
            .show(url)

    }
}