package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.after_payment_act.*
import kotlinx.android.synthetic.main.cat1.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.sabad_kharid_act.*
import kotlinx.android.synthetic.main.sabad_kharid_act.imgSabtSefaresh
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.sefareshat.rvSefareshat
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.*


class AfterPayment : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.after_payment_act)

        imgIconToolbarTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.trash))
        toolbarTopaa.setBackgroundColor(Color.parseColor("#efefef"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#bfbfbf")
        }

        txOnvanInActionBar.setTextColor(Color.parseColor("#4a4a4a"))
        txOnvanInActionBar.text = "محصولات سالم"
        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back1))

        imgNavigationTop.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(
                R.transition.enter_left_to_right,
                R.transition.exit_left_to_right
            )
            finish()
        }

        imgIconToolbarTop.setOnClickListener {

        }

        var userName = SharedPrefClass.getUserId(this, "user")
        LoadData.resultAfterPayment(
            this, clWifiState, txVaziyat,txDate,txSaAt,txVaziyat2,txDate2,txSaAt2,txCodeErja,txCodeTarakonesh,txMablagh
        ,userName)


        imgSabtSefaresh.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )

        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }
}
