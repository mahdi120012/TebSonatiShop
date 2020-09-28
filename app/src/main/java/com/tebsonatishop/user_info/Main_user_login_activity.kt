package com.tebsonatishop.user_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.activities.*
import com.tebsonatishop.customClasses.AppVersionName
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.customClasses.UrlEncoderClass
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.navigation_items.*
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*

class Main_user_login_activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_userlogin)

        nav_footer_txVesionCode.text = "نسخه " + AppVersionName.getVersionName(this)
        imgProfile.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.user_red))

        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgNavigationTop.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)
            finish()
        }

        var userName = SharedPrefClass.getUserId(this, "user")
        if (userName.length <= 0) {

            //tx_user_state.setText("ابتدا در برنامه وارد شوید");

        }else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }

        imgLogin.setOnClickListener{
            if (etUsername.text.length <= 0 || etUsername.text == null || etPassword.text.length <= 0 || etPassword.text == null) {
                Toast.makeText(this, "لطفا همه فیلد ها را تکمیل نمایید", Toast.LENGTH_SHORT).show()

            } else {

                var userNameEncode = UrlEncoderClass.urlEncoder(etUsername.text.toString())
                var passwordEncode = UrlEncoderClass.urlEncoder(etPassword.text.toString())
                val url = "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=login&phone_number=$userNameEncode&pass1=$passwordEncode"
                Login_helper(this, url, etUsername, etPassword).execute()
            }
        }

        txRegisterInLoginPage.setOnClickListener{
            val intent = Intent(this, Main_user_register_activity::class.java)
            startActivity(intent)
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



            //val intent = Intent(this, Search::class.java)
            //startActivity(intent)
            //overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }
        imgListSefareshat.setOnClickListener{
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
            finish()

        }
        /*imgNavigationTop.setOnClickListener{
                if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                    drawer_layout.closeDrawer(Gravity.RIGHT)
                } else {
                    drawer_layout.openDrawer(Gravity.RIGHT)
                }

        }*/

        fun userReg(view: View) {
            val intent = Intent(this, Main_user_register_activity::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        /*if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            drawer_layout.closeDrawer(Gravity.RIGHT)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)*/
            finish()
        /*}*/
    }
}
