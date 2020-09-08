package com.tebsonatishop.activities

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.AppVersionName
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cat1.*
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.search.rvInSearch
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.ArrayList




class Cat2_click : AppCompatActivity() {
    private var rAdapterYouHaveKnow: RecyclerAdapter? = null
    private var rModelsYouHaveKnow: ArrayList<RecyclerModel>? = null
    var onvan:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_2_click)
        onvan = intent.getStringExtra("onvan")
        txOnvanInActionBar.text = onvan

        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgNavigationTop.setOnClickListener {
            finish()
        }

        //GET Information Catigury Mahsolat:
        rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow, "search", this, rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid)
        Recyclerview.define_recyclerviewYh(this, rvInSearch, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"search")

        LoadData.loadMahsolatCat2(this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvInSearch, clWifiState,onvan)

        imgHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)
        }

        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this,"user")
            if (userName.length <= 0){
                startActivity(Intent(this, Main_user_login_activity::class.java))
                overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)
            }else{
                Toast.makeText(this,"شما وارد برنامه شده اید.",Toast.LENGTH_SHORT).show()

            }


        }

        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)

        }

        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)

        }

        imgSearch.setOnClickListener {
            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

    }

    override fun onBackPressed() {
        finish()
        }
}
