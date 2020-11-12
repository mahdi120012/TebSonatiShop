package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tebsonatishop.R
import com.tebsonatishop.RecyclerAdapter
import com.tebsonatishop.RecyclerModel
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.*

class Sefareshat : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private var tabLayout:TabLayout? = null
    private var viewPager:ViewPager? = null

    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sefareshat_va_sefareshat_ghabli)
        Locale.setDefault(Locale("en", "US"))

        txOnvanInActionBar.setText("سفارش ها")


        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_main))
        imgIconToolbarTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgIconToolbarTop.setOnClickListener {
            finish()
        }


        imgListSefareshat.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.order_red
            )
        )
        imgListSefareshatActive.visibility = View.VISIBLE


        imgBack.setOnClickListener { finish() }


        tabLayout = findViewById<View>(com.tebsonatishop.R.id.tabLayout) as TabLayout

        tabLayout!!.addTab(tabLayout!!.newTab().setText("سفارش فعلی"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("سفارشات قبلی"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        viewPager = findViewById<View>(com.tebsonatishop.R.id.pager) as ViewPager

        val adapter = Pager(supportFragmentManager, tabLayout!!.tabCount,"","load_sefareshat")

        viewPager!!.adapter = adapter

        tabLayout!!.setOnTabSelectedListener(this)
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

       /* rModelsYouHaveKnow = ArrayList()

        rAdapterYouHaveKnow = RecyclerAdapter(
            rModelsYouHaveKnow,
            "sefareshat",
            this,
            rAdapterYouHaveKnow,
            "",
            imgSabad,
            "",
            txCountSabadKharid
        )
        Recyclerview.define_recyclerviewYh(
            this, rv1, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, "search"
        )


        var userName = SharedPrefClass.getUserId(this, "user")

        LoadData.loadSefareshat(
            this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rv1, clWifiState, userName
        )*/

        imgHome.setOnClickListener {
            /*startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(
                R.transition.enter_left_to_right,
                R.transition.exit_left_to_right
            )*/
            finish()
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
            overridePendingTransition(
                R.transition.enter_left_to_right,
                R.transition.exit_left_to_right
            )
        }
        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(
                R.transition.enter_left_to_right,
                R.transition.exit_left_to_right
            )
        }

    }

    override fun onBackPressed() {
        //startActivity(Intent(this, MainActivity::class.java))
        //overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }

    override fun onTabSelected(tab:TabLayout.Tab) {
        viewPager!!.currentItem = tab.position
    }

    override fun onTabUnselected(tab:TabLayout.Tab) {}
    override fun onTabReselected(tab:TabLayout.Tab) {}
}
