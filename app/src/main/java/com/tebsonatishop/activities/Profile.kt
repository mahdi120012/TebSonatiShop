package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tebsonatishop.R
import kotlinx.android.synthetic.main.toolbar_button.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)



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