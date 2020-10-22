package com.tebsonatishop.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import com.tebsonatishop.LoadData
import com.tebsonatishop.R
import com.tebsonatishop.customClasses.EnglishNumberToPersian
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.address.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.profile_edit.*
import java.util.*

class ProfileEdit : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)
        Locale.setDefault(Locale("en", "US"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#c82c15"))
        }

        var username = SharedPrefClass.getUserId(this,"user");

        LoadData.loadUserProfile(this,clWifiState ,username,imgProfilePicture,imgTikAgha,imgTikKhanom,etName,etPhoneNumber,etEmail,etTarikhTavalod)



        clAgha.setOnClickListener {
            imgTikAgha.visibility = View.VISIBLE
            imgTikKhanom.visibility = View.GONE
        }

        clKhanom.setOnClickListener {
            imgTikKhanom.visibility = View.VISIBLE
            imgTikAgha.visibility = View.GONE
        }

        imgSaveChange.setOnClickListener {
            var gensiyat = ""
            if (etName.text.toString() == ""){
                Toast.makeText(this,"لطفا نام خود را وارد کنید",Toast.LENGTH_SHORT).show()
            }else{
                if (imgTikAgha.visibility == View.VISIBLE){
                    gensiyat = "آقا"
                }else{
                    gensiyat = "خانم"
                }

                LoadData.updateUserProfile(this, username,gensiyat,etName.text.toString(),etPhoneNumber.text.toString(),etEmail.text.toString(),etTarikhTavalod.text.toString())
            }
        }


        etTarikhTavalod.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val persianCalendar = PersianCalendar()
                val datePickerDialog = DatePickerDialog.newInstance(
                    this@ProfileEdit,
                    persianCalendar.persianYear,
                    persianCalendar.persianMonth,
                    persianCalendar.persianDay
                )
                datePickerDialog.show(fragmentManager, "TarikhShoro")
                return@OnTouchListener true
            }
            false
        })

        etChangePassword.setOnClickListener {

        }

        clChangeProfilePicture.setOnClickListener {
            Toast.makeText(this,"امکان تغییر عکس وجود ندارد",Toast.LENGTH_SHORT).show()
        }

        imgClose.setOnClickListener {
            finish()
        }

    }

     override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        val month = monthOfYear + 1
        var fm = "" + month
        var fd = "" + dayOfMonth
        if (month < 10) {
            fm = "0$month"
        }
        if (dayOfMonth < 10) {
            fd = "0$dayOfMonth"
        }

        val date = "$year/$fm/$fd"

        etTarikhTavalod.setText(EnglishNumberToPersian().convert(date))
    }

    override fun onBackPressed() {
        finish()
    }

}