package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.sefareshat.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.ArrayList

class CommentsForManager : AppCompatActivity() {

    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sefareshat_for_manager)

        textView.text = "نظرها"
        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgNavigationTop.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)
            finish()
        }

        /* imgListSefareshat.setImageDrawable(ContextCompat.getDrawable(this,
             R.drawable.order_red
         ));*/
        rModelsYouHaveKnow = ArrayList()

        rAdapterYouHaveKnow =
            RecyclerAdapter(rModelsYouHaveKnow, "comments_for_manager", this, rAdapterYouHaveKnow,
                "", imgSabad, "", txCountSabadKharid)
        Recyclerview.define_recyclerviewYh(this, rvSefareshat, rAdapterYouHaveKnow,
            rModelsYouHaveKnow, null, "search")


        var userName = SharedPrefClass.getUserId(this, "user")

        LoadData.loadCommentsForManager(this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvSefareshat,
            clWifiState, userName)

        imgHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)
        }

        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this,"user")
            if (userName.length <= 0) {

                startActivity(Intent(this, BeforLogin::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )


            } else {
                //Toast.makeText(this, "شما وارد برنامه شده اید.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Profile::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
            }
        }

        imgSearch.setOnClickListener {
            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)

        }
        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)

        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }
}
