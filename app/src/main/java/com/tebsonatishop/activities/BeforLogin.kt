package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.tebsonatishop.R
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.befor_login.*
import kotlinx.android.synthetic.main.toolbar_button.*

class BeforLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.befor_login)

        imgProfile.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.profile_red
            )
        )
        imgProfileActive.visibility = View.VISIBLE



        btnLogin.setOnClickListener {
            startActivity(Intent(this, Main_user_login_activity::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
            finish()
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



    }
}