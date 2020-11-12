package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tebsonatishop.R
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.activity_main.clSearch
import kotlinx.android.synthetic.main.more_cat.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.imgNavigationTop
import kotlinx.android.synthetic.main.toolbar_top.txOnvanInActionBar


class MoreCat : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    /*private var rAdapterYouHaveKnow: RecyclerAdapter? = null
    private var rModelsYouHaveKnow: ArrayList<RecyclerModel>? = null*/
    var onvan:String = ""

    private var tabLayout:TabLayout? = null
    private var viewPager:ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_cat)

        onvan = intent.getStringExtra("onvan")
        txOnvanInActionBar.text = onvan


        imgSearch.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.category_red
            )
        )
        imgSearchActive.visibility = View.VISIBLE


        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgNavigationTop.setOnClickListener {
            finish()
        }

        imgBack.setOnClickListener { finish() }


        tabLayout = findViewById<View>(com.tebsonatishop.R.id.tabLayout) as TabLayout

        tabLayout!!.addTab(tabLayout!!.newTab().setText("محصولات"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("اساتید"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        viewPager = findViewById<View>(com.tebsonatishop.R.id.pager) as ViewPager

        val adapter = Pager(supportFragmentManager, tabLayout!!.tabCount,"","load_cat")

        viewPager!!.adapter = adapter

        tabLayout!!.setOnTabSelectedListener(this)
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        /*rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow, "add_cat_more", this, rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid)
        Recyclerview.define_recyclerviewYh(this, rvInSearch, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"search")


        LoadData.loadCat(
            this,
            rAdapterYouHaveKnow,
            rModelsYouHaveKnow,
            rvCatigoury,
            clWifiState
        )*/


            clSearch.setOnClickListener {
                val i = Intent(baseContext, Search::class.java)
                i.putExtra("query", "")
                startActivity(i)
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
            }

        imgHome.setOnClickListener {
            finish()
           /* startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)*/
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

        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)

        }

        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)

        }

      /*  imgSearch.setOnClickListener {

                val intent = Intent(this, MoreCat::class.java)
                intent.putExtra("onvan", "دسته بندی")
                this.startActivity(intent)
                overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }*/
    }

    override fun onBackPressed() {
        //startActivity(Intent(this, MainActivity::class.java))
        //overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        finish()

    }

    override fun onTabSelected(tab:TabLayout.Tab) {
        viewPager!!.currentItem = tab.position
    }

    override fun onTabUnselected(tab:TabLayout.Tab) {}
    override fun onTabReselected(tab:TabLayout.Tab) {}
}

