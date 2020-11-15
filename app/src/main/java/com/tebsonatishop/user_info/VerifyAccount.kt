package com.tebsonatishop.user_info

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.LoadData
import com.tebsonatishop.R
import com.tebsonatishop.activities.MainActivity
import kotlinx.android.synthetic.main.verify_account.*
import java.util.*

class VerifyAccount : AppCompatActivity() {
    var phoneNumber:String = ""
    lateinit var next:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verify_account)

        phoneNumber = intent.getStringExtra("phone_number")

        val random = Random()
        val randomNumber = java.lang.String.format("%04d", random.nextInt(10000))
        var matnSms = " کد فعالسازی: " + randomNumber + " «محصولات سالم» "

        txTimer.isClickable = false
        LoadData.reciveVerifyCode(this,  matnSms,phoneNumber,txTimer)
        object : CountDownTimer(90000, 1000) {

            override fun onTick(millisUntilFinished:Long) {

                txTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                txTimer.isClickable = true
                txTimer.text = "ارسال دوباره!"
            }
        }.start()



        txTimer.setOnClickListener {

            txTimer.isClickable = false
            LoadData.reciveVerifyCode(this, matnSms,phoneNumber,txTimer)

            object : CountDownTimer(60000, 1000) {

                override fun onTick(millisUntilFinished:Long) {

                    txTimer.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    txTimer.isClickable = true
                    txTimer.text = "ارسال دوباره!"
                }
            }.start()


        }

        textView48.setText("لطفا کد فعالسازی ارسال شده به شماره "+ phoneNumber + " را وارد نمایید")

        etNum1.addTextChangedListener(object : TextWatcher {
             override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (etNum1 != null && etNum1.length() > 0)
                {
                    next = etNum1.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                   /* doSearch(); // Or whatever*/
                }

            }
        })

        etNum2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if (etNum2 != null && etNum2.length() > 0)
                {
                    next = etNum2.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                    /* doSearch(); // Or whatever*/
                }

            }
        })

        etNum3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if (etNum3 != null && etNum3.length() > 0)
                {
                    next = etNum3.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                    /* doSearch(); // Or whatever*/
                }

            }
        })




        imgBack.setOnClickListener {
            finish()
        }

        btnVerifyAccount.setOnClickListener {
            val num1 = etNum1.text.toString()
            val num2 = etNum2.text.toString()
            val num3 = etNum3.text.toString()
            val num4 = etNum4.text.toString()

            var allNumber = num1+num2+num3+num4





            if (num1.length <= 0 || num1 == null || num2.length <= 0 || num2 == null || num3.length <= 0 || num3 == null || num4.length <= 0 || num4 == null) {
                Toast.makeText(this@VerifyAccount,"لطفا همه فیلدها را وارد کنید",Toast.LENGTH_SHORT).show()

            }else {

                if (randomNumber == allNumber){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@VerifyAccount,"فعالسازی با موفقیت انجام شد",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@VerifyAccount,"کد فعالسازی را به درستی وارد نکرده اید",Toast.LENGTH_SHORT).show()
                }

/*              var familyEncode = UrlEncoderClass.urlEncoder(etNameInRegisterPage.text.toString())
                var phoneNumberEncode = UrlEncoderClass.urlEncoder(etPhoneNumberInRegisterPage.text.toString())
                var passwordEncode = UrlEncoderClass.urlEncoder(etPassInRegisterPage.text.toString())

                val timeKononi = TimeKononi()
                var time = timeKononi.gregorianTime

                val url = "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=register&family=$familyEncode&phone_number=$phoneNumberEncode&pass1=$passwordEncode&time=$time"
                Registeration_helper(
                    this,
                    url,
                    etNameInRegisterPage,
                    etPhoneNumberInRegisterPage,
                    etPassInRegisterPage,
                    txStateUserRegister,
                    clRegister
                ).execute()*/
            }
        }

/*        txLoginInRegisterPage.setOnClickListener {
            val intent = Intent(this, Main_user_login_activity::class.java)
            startActivity(intent)
            finish()
        }*/


    }

    override fun onBackPressed() {
            finish()
    }
}