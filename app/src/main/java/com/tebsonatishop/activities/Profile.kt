package com.tebsonatishop.activities

import android.content.Intent
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
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.toolbar_button.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#e5effc"))
        }

        var username = SharedPrefClass.getUserId(this,"user");

        var user = username.removePrefix("0")
        txPhoneNumber.setText("+98 "+user)

        LoadData.loadNameAndPricure(this,clWifiState ,username,imgProfilePicture,etName,txEmail)

        imgEditProfile.setOnClickListener {
            startActivity(Intent(this, ProfileEdit::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }
        clAddress.setOnClickListener {
            startActivity(Intent(this, AddressHa::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

/*        clPayamHa.setOnClickListener {
            Toast.makeText(this,"بزودی فعال می شود",Toast.LENGTH_SHORT).show()
        }
        clKifPol.setOnClickListener {
            Toast.makeText(this,"بزودی فعال می شود",Toast.LENGTH_SHORT).show()
        }*/
        clKhoroj.setOnClickListener {
            SharedPrefClass.clearData(this)
            finish()
            Toast.makeText(this,"شما خارج شدید",Toast.LENGTH_SHORT).show()
        }

        imgHome.setOnClickListener{
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)*/
            finish()
        }
        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
            finish()

        }
        imgSearch.setOnClickListener{

            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
            finish()

        }

        imgListSefareshat.setOnClickListener{
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
            finish()

        }
    }
}