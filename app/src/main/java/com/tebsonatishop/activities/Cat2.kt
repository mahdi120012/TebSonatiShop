package com.tebsonatishop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.cat1.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.search.rvInSearch
import kotlinx.android.synthetic.main.search.toolbarTop
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.imgNavigationTop
import kotlinx.android.synthetic.main.toolbar_top.txOnvanInActionBar
import kotlinx.android.synthetic.main.toolbar_top_for_main_page.*
import java.util.ArrayList

class  Cat2 : AppCompatActivity() {
    private var rAdapterYouHaveKnow: RecyclerAdapter? = null
    private var rModelsYouHaveKnow: ArrayList<RecyclerModel>? = null
    var onvan:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat1)
        onvan = intent.getStringExtra("onvan")
        txOnvanForoshgah.text = onvan

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
        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow, "cat2", this, rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid)
        Recyclerview.define_recyclerviewYh(this, rvInSearch, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"search")

        LoadData.loadCat2(
            this,
            rAdapterYouHaveKnow,
            rModelsYouHaveKnow,
            rvInSearch,
            clWifiState,onvan
        )


        //GET Information Catigury Mahsolat:
        rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow, "search", this, rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid)
        Recyclerview.define_RecyclerviewCat(this, rvInSearch1, rAdapterYouHaveKnow, rModelsYouHaveKnow,
                                               progressBar2,nestedScrollView2,clWifiState,"search",
                                               onvan,"loadMahsolatInCat2")

        LoadData.loadMahsolatInCat2(this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvInSearch1, clWifiState,onvan,txShowAll)

        rvInSearch1.visibility = View.VISIBLE
        txShowAll.setOnClickListener{

            if(rvInSearch1.visibility == View.VISIBLE){
                txShowAll.text = "مشاهده همه محصولات..."
                rvInSearch1.visibility = View.GONE
                txShowAll.setPadding(0,0,0,300)
            }else{
                txShowAll.text = "پنهان کردن همه محصولات"
                rvInSearch1.visibility = View.VISIBLE
                txShowAll.setPadding(0,0,0,0)
            }
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

