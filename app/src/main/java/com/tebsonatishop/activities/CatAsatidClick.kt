package com.tebsonatishop.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import kotlinx.android.synthetic.main.cat1.*
import kotlinx.android.synthetic.main.cat1.toolbarTop
import kotlinx.android.synthetic.main.mahsolat_ostad.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import kotlinx.android.synthetic.main.toolbar_top.imgNavigationTop
import kotlinx.android.synthetic.main.toolbar_top_for_main_page.*


class CatAsatidClick : AppCompatActivity() {
    private var rAdapterYouHaveKnow: RecyclerAdapter? = null
    private var rModelsYouHaveKnow: ArrayList<RecyclerModel>? = null

    var name_ostad:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mahsolat_ostad)

        name_ostad = intent.getStringExtra("name_ostad")
        txOnvanForoshgah.text = name_ostad

        toolbarTop.setOnClickListener{
            txOnvanForoshgah.text = ""
            txSearchIn.text = ""

            val i = Intent(baseContext, Search::class.java)
            i.putExtra("query", "")
            startActivity(i)
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
        }

        imgBack.setOnClickListener { finish() }
        txOstadName.setText(name_ostad.toString())



/*        sv1.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                return false
            }

            override fun onQueryTextChange(newText:String):Boolean {

                if(newText.isEmpty()){
                    txSearchIn.text = "جستجو در"
                    txOnvanForoshgah.text = onvan
                }else{
                    txOnvanForoshgah.text = ""
                    txSearchIn.text = ""

                    val i = Intent(baseContext, Search::class.java)
                    i.putExtra("query", newText)
                    startActivity(i)
                    overridePendingTransition(
                        R.transition.enter_right_to_left,
                        R.transition.exit_right_to_left
                    )
                }

                return false

            }
        })*/


        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgNavigationTop.setOnClickListener {
            finish()
        }

       rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow, "mahsolat_asatid", this, rAdapterYouHaveKnow, "",null,"" , null)
        Recyclerview.defineRecyclerview2ItemDarRow(this, rv1, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"")

        LoadData.loadMahsolatOstad(this,rAdapterYouHaveKnow,rModelsYouHaveKnow,rv1,clWifiState,name_ostad)

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
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)
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
        //startActivity(Intent(this, MainActivity::class.java))
        //overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        finish()

    }
}

