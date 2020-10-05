package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.rbddevs.splashy.Splashy
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.AppVersionName
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.imageview_with_auto_height_left.*
import kotlinx.android.synthetic.main.imageview_with_auto_height_right.*
import kotlinx.android.synthetic.main.navigation_items.*
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.row_mahsol.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.imgNavigationTop
import kotlinx.android.synthetic.main.toolbar_top_for_main_page.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null
    private var rAdapterYouHaveKnow2:RecyclerAdapter? = null
    private var rModelsYouHaveKnow2:ArrayList<RecyclerModel>? = null
    private var allSampleData:ArrayList<SectionDataModelRecyclerModel>? = null
    private var rAdapterMain:RecyclerViewDataAdapter? = null
    private var sliderAdapterExample:SliderAdapterExample? = null
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_main_activity)

        //setSplashy()
        sliderAdapterExample = SliderAdapterExample(this)
        imageSlider.sliderAdapter = sliderAdapterExample

        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_LEFT
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.scrollTimeInSec = 7 //set scroll delay in seconds :
        imageSlider.startAutoCycle()


        imgSearch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.search_black))
        nav_footer_txVesionCode.text = "نسخه " + AppVersionName.getVersionName(this)

        var userType = SharedPrefClass.getUserId(this, "type")
        if (userType == "admin") {
            clManagerInNavigation.visibility = View.VISIBLE
        }

        var userName = SharedPrefClass.getUserId(this, "user")
        if (userName == "") {

            txExit.setTextColor(Color.parseColor("#236faf"))
            txExit.text = "ورود به حساب کاربری"
            imageView51.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.login_icon))
        }

        imgGhazayeFori.setOnClickListener{
            startActivity(Intent(this, Ghazaye_Fori::class.java))
            overridePendingTransition(R.transition.enter_left_to_right,R.transition.exit_left_to_right)
        }

        //line zir baraye update automatic ee.
        val appUpdater = AppUpdater(this).setUpdateFrom(UpdateFrom.JSON)
            .setUpdateJSON("http://robika.ir/ultitled/practice/tavasi_teb_sonati_update_checker.json")
            .setTitleOnUpdateAvailable("بروزرسانی جدید موجوده!").setButtonUpdate("بروزرسانی")
            .setButtonDismiss("فعلا نه").setButtonDoNotShowAgain("")
        appUpdater.start()

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
                    txOnvanForoshgah.text = "فروشگاه جامع طب اسلامی ایرانی"
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
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        var link = "http://robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/ax_ghazaye_fori.png"
        var linkLeft = "http://robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/ax_ghazaye_fori.png"
        var linkRight = "http://robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/ax_ghazaye_fori.png"

        if(link.isEmpty()){
            Picasso.get()
                .load(R.drawable.no_image)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPic)
        }else{
            Picasso.get()
                .load(link)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPic)
        }

        if(linkLeft.isEmpty()){
            Picasso.get()
                .load(R.drawable.no_image)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPicLeft)
        }else{
            Picasso.get()
                .load(linkLeft)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPicLeft)
        }


        if(linkRight.isEmpty()){
            Picasso.get()
                .load(R.drawable.no_image)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPicRight)
        }else{
            Picasso.get()
                .load(linkRight)
                .resize(width/2,0)
                .error(R.drawable.no_image)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgMahsolPicRight)
        }


        //GET Information Catigury Mahsolat:
        rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(
            rModelsYouHaveKnow,
            "add_catigoury",
            this@MainActivity,
            rAdapterYouHaveKnow,
            "",
            imgSabad,
            "",
            txCountSabadKharid
        )
        Recyclerview.define_recyclerviewYh(
            this, rvCatigoury, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, ""
        )

        LoadData.loadCat(
            this@MainActivity, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvCatigoury, clWifiState
        )

        //GET Information CatiguryFood:
        rModelsYouHaveKnow2 = ArrayList()
        rAdapterYouHaveKnow2 = RecyclerAdapter(
            rModelsYouHaveKnow2,
            "add_food_cat",
            this@MainActivity,
            rAdapterYouHaveKnow2,
            "",
            imgSabad,
            "",
            txCountSabadKharid
        )
        Recyclerview.define_recyclerviewYh(
            this, rvCatigouryFood, rAdapterYouHaveKnow2, rModelsYouHaveKnow2, null, ""
        )

        LoadData.loadCatFood(
            this@MainActivity, rAdapterYouHaveKnow2, rModelsYouHaveKnow2, rvCatigouryFood, clWifiState
        )

        //GET Information Main:
        allSampleData = ArrayList<SectionDataModelRecyclerModel>()
        rvMain.setHasFixedSize(true)
        rvMain.isNestedScrollingEnabled = false
        rAdapterMain = RecyclerViewDataAdapter(this, allSampleData, imgSabad, txCountSabadKharid)
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.adapter = rAdapterMain
        createDummyData()

        txMore.setOnClickListener {
            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
        }


        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this, "user")
            if (userName.length <= 0) {
                startActivity(Intent(this, Main_user_login_activity::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
            } else {
                Toast.makeText(this, "شما وارد برنامه شده اید.", Toast.LENGTH_SHORT).show()

            }


        }

        imgSabadKharid.setOnClickListener {
            startActivity(Intent(this, SabadKharidAct::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )

        }
        imgSearch.setOnClickListener {

            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)

            //startActivity(Intent(this, MoreCat::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
        }
        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )

        }

        txExit.setOnClickListener {

            if (txExit.text.contains("خروج")) {
                SharedPrefClass.clearData(this)
                drawer_layout.closeDrawer(Gravity.RIGHT)

                txExit.setTextColor(Color.parseColor("#236faf"))
                txExit.text = "ورود به حساب کاربری"
                imageView51.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.login_icon
                    )
                )
                clManagerInNavigation.visibility = View.GONE

                Toast.makeText(this, "شما خارج شدید", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, Main_user_login_activity::class.java))
                overridePendingTransition(
                    R.transition.enter_right_to_left,
                    R.transition.exit_right_to_left
                )
            }

        }

        txOrdersInNavigation.setOnClickListener {
            startActivity(Intent(this, SefareshatForManager::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
        }

        txUsersInNavigation.setOnClickListener {
            startActivity(Intent(this, UsersForManager::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
        }

        txCommentsInNavigation.setOnClickListener {
            startActivity(Intent(this, CommentsForManager::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left,
                R.transition.exit_right_to_left
            )
        }

        imgNavigationTop.setOnClickListener {
            if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                drawer_layout.closeDrawer(Gravity.RIGHT)
            } else {
                drawer_layout.openDrawer(Gravity.RIGHT)
            }

        }
    }

    fun createDummyData() {
        for (i in 1..6) {
            val dm = SectionDataModelRecyclerModel()

            var cat:String? = null
            if(i == 1) {
                cat = "پک سلامت"
                dm.headerTitle = "$cat"
            } else if(i == 2) {
                cat = "جدید ترین"
                dm.headerTitle = "$cat محصولات"
            } else if (i == 3) {
                cat = "پر فروش ترین"
                dm.headerTitle = "$cat محصولات"
            } else if (i == 4) {
                cat = "محبوب ترین"
                dm.headerTitle = "$cat محصولات"
            } else if (i == 5) {
                cat = "پر بازدید ترین"
                dm.headerTitle = "$cat محصولات"
            } else if (i == 6) {
                cat = "تخفیفات ویژه"
                dm.headerTitle = "$cat"
            }
            val singleItem = ArrayList<RecyclerModel>()

            LoadData.firstLoadData(
                this, rAdapterMain, singleItem, rvMain, cat, clWifiState
            )

            dm.allItemsInSection = singleItem
            allSampleData?.add(dm)
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            drawer_layout.closeDrawer(Gravity.RIGHT)
        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun setSplashy() {
        Splashy(this)         // For JAVA : new Splashy(this)
            .setLogo(R.drawable.logo_h)
            .setTitleColor(R.color.colorBlack)
            .setTitleSize(22F)
            .setBackgroundColor(R.color.colorKeremi)
            .setTitle("فروشگاه جامع طب اسلامی ایرانی")
            .showProgress(true)
            .setProgressColor(R.color.colorAccent).setFullScreen(true).setTime(3000).show()
    }


    /*  override fun onStop() {
          super.onStop()
          try {
              val foreground = ForegroundCheckTask().execute(applicationContext).get()
              if (!foreground) {
                  var userName = SharedPrefClass.getUserId(this,"user")
                  LoadData.removeAllTempMahsolYekUser(this, null, userName)

              }
          } catch (e: InterruptedException) {
              e.printStackTrace()
          } catch (e: ExecutionException) {
              e.printStackTrace()
          }
      }*/

}
