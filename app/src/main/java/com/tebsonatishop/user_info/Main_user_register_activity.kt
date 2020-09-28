package com.tebsonatishop.user_info

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.tebsonatishop.R
import com.tebsonatishop.activities.MainActivity
import com.tebsonatishop.activities.Search
import com.tebsonatishop.customClasses.AppVersionName
import com.tebsonatishop.customClasses.TimeKononi
import com.tebsonatishop.customClasses.ProgressDialogClass
import com.tebsonatishop.customClasses.UrlEncoderClass
import kotlinx.android.synthetic.main.navigation_items.*
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*

class Main_user_register_activity : AppCompatActivity() {

    internal lateinit var constraintSet: ConstraintSet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_user_register)

        nav_footer_txVesionCode.text = "نسخه " + AppVersionName.getVersionName(this)
        imgProfile.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.user_red))

        imgZarbdarInRegisterPage.setOnClickListener{
            etPhoneNumberInRegisterPage.setText("")
        }

        etPhoneNumberInRegisterPage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                etPhoneNumberInRegisterPage.setBackgroundResource(R.drawable.edittext_style)
                txStateUserRegister.visibility = View.GONE

                constraintSet = ConstraintSet()
                constraintSet.clone(clRegister)

                constraintSet.connect(
                    R.id.etPassInRegisterPage,
                    ConstraintSet.TOP,
                    R.id.etPhoneNumberInRegisterPage,
                    ConstraintSet.BOTTOM
                )
                constraintSet.applyTo(clRegister)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        imgRegister.setOnClickListener {
            val name = etNameInRegisterPage.text.toString()
            val phoneNumber = etPhoneNumberInRegisterPage.text.toString()
            val password = etPassInRegisterPage.text.toString()

            if (name.length <= 0 || name == null || phoneNumber.length <= 0 || phoneNumber == null || password.length <= 0 || password == null) {
                Toast.makeText(
                    this@Main_user_register_activity,
                    "لطفا همه فیلدها را وارد کنید",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (phoneNumber.length < 11) {
                constraintSet = ConstraintSet()
                constraintSet.clone(clRegister)

                constraintSet.connect(
                    R.id.etPassInRegisterPage,
                    ConstraintSet.TOP,
                    R.id.txStateUserRegister,
                    ConstraintSet.BOTTOM
                )
                constraintSet.applyTo(clRegister)

                txStateUserRegister.visibility = View.VISIBLE
                txStateUserRegister.text = "شماره همراه خود را صحیح وارد نمایید."
            } else {
                var familyEncode = UrlEncoderClass.urlEncoder(etNameInRegisterPage.text.toString())
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
                ).execute()
            }
        }

        txLoginInRegisterPage.setOnClickListener {
            val intent = Intent(this, Main_user_login_activity::class.java)
            startActivity(intent)
            finish()
        }

        imgHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        }
        imgSabadKharid.setOnClickListener {
            Toast.makeText(this,"imgSabadKharid", Toast.LENGTH_SHORT).show()

        }
        imgSearch.setOnClickListener{
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
            overridePendingTransition(R.transition.enter_right_to_left, R.transition.exit_right_to_left)
        }
        imgListSefareshat.setOnClickListener{
            Toast.makeText(this,"imgListSefareshat", Toast.LENGTH_SHORT).show()

        }
        imgNavigationTop.setOnClickListener{
            if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                drawer_layout.closeDrawer(Gravity.RIGHT)
            } else {
                drawer_layout.openDrawer(Gravity.RIGHT)
            }

        }

    }

    override fun onBackPressed() {
        /*if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            drawer_layout.closeDrawer(Gravity.RIGHT)
        } else {*/
            finish()
        /*}*/
    }
}