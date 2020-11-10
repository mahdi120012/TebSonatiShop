package com.tebsonatishop.activities

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid_act.*
import kotlinx.android.synthetic.main.sabad_kharid_khali.*
import kotlinx.android.synthetic.main.sefareshat.rvSefareshat
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.*


class SabadKharidKhali : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sabad_kharid_khali)

/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#bfbfbf")
        }*/

        imgHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(
                R.transition.enter_left_to_right, R.transition.exit_left_to_right
            )
        }

        btnShoroeKharid.setOnClickListener { finish() }

        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this,"user")
            if (userName.length <= 0) {

                startActivity(Intent(this, BeforLogin::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
                finish()


            } else {
                //Toast.makeText(this, "شما وارد برنامه شده اید.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Profile::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
                finish()
            }
        }

        imgSearch.setOnClickListener {
            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }
        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left, R.transition.exit_right_to_left
            )

        }

    }

    override fun onBackPressed() {
        finish()
    }
}
