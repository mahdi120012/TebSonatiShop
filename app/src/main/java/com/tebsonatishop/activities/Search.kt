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
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.ArrayList


class Search : AppCompatActivity() {
    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)


        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_main))
        imgIconToolbarTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgIconToolbarTop.setOnClickListener {
            finish()
        }



        imgSearch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.search_red))

        val query:String = intent.getStringExtra("query")

        //var userName = SharedPrefClass.getUserId(this,"user")

        //GET Information Catigury Mahsolat:
        rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow =
            RecyclerAdapter(rModelsYouHaveKnow, "search", this, rAdapterYouHaveKnow, "", imgSabad,
                "", txCountSabadKharid)
        Recyclerview.define_recyclerviewYh(this, rvInSearch, rAdapterYouHaveKnow,
            rModelsYouHaveKnow, progressBar3,clWifiState, "search","","loadSearch")

        LoadData.loadSearch(this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvInSearch, clWifiState,
            query)

        searchViewInSearch.isIconified = false
        searchViewInSearch.setQuery(query,false)
        searchViewInSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                //Toast.makeText(SearchForSendMessageTeacher.this, query, Toast.LENGTH_SHORT).show();
                return false

            }

            override fun onQueryTextChange(newText:String):Boolean {
                rModelsYouHaveKnow = ArrayList()
                rAdapterYouHaveKnow =
                    RecyclerAdapter(rModelsYouHaveKnow, "search", this@Search, rAdapterYouHaveKnow,
                        "", imgSabad, "", txCountSabadKharid)

                Recyclerview.define_recyclerviewYh(this@Search, rvInSearch, rAdapterYouHaveKnow,
                    rModelsYouHaveKnow, progressBar3,clWifiState, "search",newText,"loadSearch")

                LoadData.loadSearch(this@Search, rAdapterYouHaveKnow, rModelsYouHaveKnow,
                    rvInSearch, clWifiState, newText)

                //Toast.makeText(this@Search,newText,Toast.LENGTH_SHORT).show()
                return false

            }
        })



        imgHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)
        }

        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this, "user")
            if (userName.length <= 0) {
                startActivity(Intent(this, Main_user_login_activity::class.java))
                overridePendingTransition(R.transition.enter_left_to_right,
                    R.transition.exit_left_to_right)
            } else {
                Toast.makeText(this, "شما وارد برنامه شده اید.", Toast.LENGTH_SHORT).show()

            }


        }

        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,
                R.transition.exit_left_to_right)

        }

        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,
                R.transition.exit_right_to_left)

        }
    }

    override fun onBackPressed() {
        //startActivity(Intent(this, MainActivity::class.java))
        //overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }
}
