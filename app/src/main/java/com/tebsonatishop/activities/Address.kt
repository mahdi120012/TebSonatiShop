package com.tebsonatishop.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.tebsonatishop.LoadData
import com.tebsonatishop.R
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.address.*

class Address : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address)
        var username = SharedPrefClass.getUserId(this,"user")
        imgExit.setOnClickListener {
            finish()
        }

/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#c82c15"))
        }*/

        var id = if (intent.getExtras() == null) {}else{intent.extras!!.getString("id")}
        var onvan = if (intent.getExtras() == null) {}else{intent.extras!!.getString("onvan")}
        var address = if (intent.getExtras() == null) {}else{intent.extras!!.getString("address")}
        var telephone = if (intent.getExtras() == null) {}else{intent.extras!!.getString("telephone")}
        var add_address = if (intent.getExtras() == null) {}else{intent.extras!!.getString("add_address")}

        if (add_address.toString() == "add_address"){

            btnSabt.setOnClickListener {

                if (etOnvan.text.toString() == "" || etAddress.text.toString() == ""){
                    Toast.makeText(this,"لطفا همه موارد را تکمیل کنید",Toast.LENGTH_SHORT).show()
                }else{
                    LoadData.addAddress(this, username,etOnvan.text.toString(),etAddress.text.toString(),etTelephone.text.toString())
                }
            }

        }else{
        textView37.setText("ویرایش آدرس")
        etOnvan.setText(onvan.toString())
        etAddress.setText(address.toString())
        etTelephone.setText(telephone.toString())

        btnSabt.setOnClickListener {
            LoadData.updateAddress(this, id.toString(), username,etOnvan.text.toString(),etAddress.text.toString(),etTelephone.text.toString())
        }

        }
    }

    override fun onBackPressed() {
        finish()
    }
}