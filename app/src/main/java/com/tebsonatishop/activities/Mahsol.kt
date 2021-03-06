package com.tebsonatishop.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.EnglishNumberToPersian
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.customClasses.TimeKononi
import kotlinx.android.synthetic.main.mahsol.*
import kotlinx.android.synthetic.main.mahsol.txCountSabadKharid
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid.*
import kotlinx.android.synthetic.main.toolbar_top.*
import me.relex.circleindicator.CircleIndicator
import java.util.*

class Mahsol : AppCompatActivity() {
    private var rAdapter_place:RecyclerAdapter? = null
    private var rModels_place:ArrayList<RecyclerModel>? = null
   /* private var rAdapterPlaceComment:RecyclerAdapterPlaceComment? = null
    private var rModelsPlaceComment:ArrayList<RecyclerModelPlaceComment>? = null*/
   private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null

    var id:String = ""
    var onvan:String = ""
    var matn:String = ""
    var picture:String = ""
    var gheymat:String = ""
    var omdehOrJozi:String = ""

    private var viewPagerAdapterForSlider:ViewPagerAdapterForSlider? = null
    private var mPager:ViewPager? = null
    private val ImgArray = ArrayList<RecyclerModel>()


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mahsol)

        imgBack.setOnClickListener {
            finish()
        }

        //initSlider();

        val timeKononi = TimeKononi()
        var time = timeKononi.gregorianTime

        var family = SharedPrefClass.getUserId(this, "name")
        var userName = SharedPrefClass.getUserId(this, "user")

        id = intent.getStringExtra("id")
        onvan = intent.getStringExtra("onvan")
        matn = intent.getStringExtra("matn")
        picture = intent.getStringExtra("picture")
        gheymat = intent.getStringExtra("gheymat")

        txGheymat.setText(gheymat)
        txGheymatRoyeButton.setText(gheymat)

        txOnvanInActionBar.text = EnglishNumberToPersian().convert(onvan)

        tx_onvan.text = EnglishNumberToPersian().convert(onvan)
        tx_matn.text = EnglishNumberToPersian().convert(matn)

      /*  imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_main))
        imgIconToolbarTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_white))
        imgIconToolbarTop.setOnClickListener {
            finish()
        }*/

        LoadData.sendVisit(this, id)

/*        ratingBarinMahsolAct.setOnClickListener {
            val rating = ratingBarinMahsolAct.rating.toString()

            if (family.length <= 0) {
                Toast.makeText(this, "برای ارسال نظر باید وارد شوید", Toast.LENGTH_SHORT).show()
            } else {
                LoadData.sendRate(this, rAdapterPlaceComment, rModelsPlaceComment, rvMahsol,
                    clWifiState, userName, id, rating, ratingBarinMahsolAct, numberOfRate)
            }
        }

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        */


        //GET Information Mahsolat Mortabet:
        rModelsYouHaveKnow = ArrayList()
        rAdapterYouHaveKnow = RecyclerAdapter(
            rModelsYouHaveKnow,
            "add_mahsolat_mortabet",
            this,
            rAdapterYouHaveKnow,
            "",
            imgSabad,
            "",
            txCountSabadKharid)

        Recyclerview.define_recyclerviewYh(this, rv1, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, "")

        LoadData.loadMahsolatMortabet(this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rv1, clWifiState,onvan.substring(0,3))


/*
        rModelsPlaceComment = ArrayList()
*/
        rModels_place = ArrayList()
/*        rAdapterPlaceComment =
            RecyclerAdapterPlaceComment(rModelsPlaceComment, "mahsol", this, 0)
        rAdapter_place = RecyclerAdapter(rModels_place, "mahsol", this, 0,rvMahsol)*/

      /*  Recyclerview.defineRecyclerviewPC(this, rvMahsolComment, rAdapterPlaceComment,
            rModelsPlaceComment, progressBarMahsol, nestedScrollView, id)
        Recyclerview.define_recyclerviewYh(this, rvMahsol, rAdapter_place, rModels_place,
            progressBarMahsol, "")*/

       /* LoadData.firstLoadDataPlaceComment(this, rAdapterPlaceComment, rModelsPlaceComment,
            rvMahsol, clWifiState, id)*/


        mPager = findViewById<View>(R.id.pager) as ViewPager
        val indicator:CircleIndicator = findViewById<View>(R.id.indicator) as CircleIndicator

        //line zir baraye load tasavir safhe mahsole
        LoadData.firstLoadDataMahsolAct(this, rAdapter_place, rModels_place, null, clWifiState,
            id,mPager,indicator,viewPagerAdapterForSlider,ImgArray)


/*
        LoadData.firstLoadDataRate(this, clWifiState, id, ratingBarinMahsolAct, numberOfRate)
*/



       /* txSendComment.setOnClickListener {
            if (userName.length <= 0) {
                Toast.makeText(this, "برای ارسال کامنت باید وارد شوید", Toast.LENGTH_SHORT).show()
            } else {

                LoadData.sendComment(this, rAdapterPlaceComment, rModelsPlaceComment, rvMahsol, id,
                    etComment, userName, family, etComment.text.toString(), time, progressBarMahsol)
            }
        }*/

/*        etComment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence:CharSequence, i:Int, i1:Int, i2:Int) {}

            override fun onTextChanged(charSequence:CharSequence, i:Int, i1:Int, i2:Int) {
                if (etComment.text.toString().length > 0) {
                    txSendComment.setTextColor(Color.parseColor("#1287c2"))
                    txSendComment.isEnabled = true
                } else {
                    txSendComment.setTextColor(Color.parseColor("#b1c6e5"))
                    txSendComment.isEnabled = false
                }
            }

            override fun afterTextChanged(editable:Editable) {

            }
        })

        if (family.length <= 0) {
            etComment.hint = "برای ارسال کامنت باید وارد شوید"
        } else {
            etComment.hint = "کامنت با $family"
        }*/


        clAddToCard.setOnClickListener {
            val userName = SharedPrefClass.getUserId(this, "user")

            /*if (userName.length <= 0) {
                Toast.makeText(this, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show()
            } else {*/
                omdehOrJozi = "joze"
                imgButtonOmdeh.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.button_omdeh))
                imgButtonJoze.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.button_jozi_selected))
                imgRedManfi.visibility = View.VISIBLE
                txCountSefareshInRow.visibility = View.VISIBLE
                imgRedPlus.visibility = View.VISIBLE
                txCountSefareshInRow.text = "1"
                LoadData.addTempMahsol(this, null, id, userName,
                    txCountSefareshInRow.text.toString(), time)
                //numberofSefaresh += Integer.parseInt(holder.txCountSefaresh.getText().toString())
                //txCountSabadKharid.setText(numberofSefaresh.toString())

            /*}*/
        }

        imgButtonOmdeh.setOnClickListener {
            /*val userName = SharedPrefClass.getUserId(this, "user")

            if (userName.length <= 0) {
                Toast.makeText(this, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show()
            } else {*/
                omdehOrJozi = "omdeh"
                imgButtonJoze.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.button_jozi))
                imgButtonOmdeh.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.button_omdeh_selected))
                imgRedManfi.visibility = View.VISIBLE
                txCountSefareshInRow.visibility = View.VISIBLE
                imgRedPlus.visibility = View.VISIBLE
                txCountSefareshInRow.text = "10"
                LoadData.addTempMahsol(this, null, id, userName,
                    txCountSefareshInRow.text.toString(), time)
                //numberofSefaresh += Integer.parseInt(holder.txCountSefaresh.getText().toString())
                //txCountSabadKharid.setText(numberofSefaresh.toString())

            /*}*/
        }

        imgRedManfi.setOnClickListener {
            val userName = SharedPrefClass.getUserId(this, "user")

            if (txCountSefareshInRow.text.toString().matches("1".toRegex())) {
                imgButtonJoze.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.button_jozi))
                imgRedPlus.visibility = View.GONE
                imgRedManfi.visibility = View.GONE
                txCountSefareshInRow.visibility = View.GONE
                LoadData.removeTempMahsol(this, null, id, userName)

                /*numberofSefaresh--
                txCountSabadKharid.setText(numberofSefaresh.toString())*/

            } else {

                if (omdehOrJozi == "omdeh" && txCountSefareshInRow.text.toString().matches(
                        "10".toRegex())) {

                    Toast.makeText(this, "حداقل سفارش عمده ۱۰ قلم می باشد", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    var numberofSefareshYekMahsol =
                        Integer.parseInt(txCountSefareshInRow.text.toString())
                    numberofSefareshYekMahsol--
                    txCountSefareshInRow.text = numberofSefareshYekMahsol.toString()

                    /*numberofSefaresh--
                    txCountSabadKharid.setText(numberofSefaresh.toString())*/

                    LoadData.addTempMahsol(this, null, id, userName,
                        txCountSefareshInRow.text.toString(), time)


                }
            }
        }

        imgRedPlus.setOnClickListener {
            val userName = SharedPrefClass.getUserId(this, "user")


            var numberofSefareshYekMahsol =
                Integer.parseInt(txCountSefareshInRow.text.toString())
            numberofSefareshYekMahsol++
            txCountSefareshInRow.text = numberofSefareshYekMahsol.toString()

            /*numberofSefaresh++
            txCountSabadKharid.setText(numberofSefaresh.toString())*/

            LoadData.addTempMahsol(this, null, id, userName,
                txCountSefareshInRow.text.toString(), time)
        }

    }

/*    private fun initSlider() {
        for (i in 0 until 3) ImgArray.add(img[i])
        mPager = findViewById<View>(R.id.pager) as ViewPager
        mPager!!.setAdapter(ViewPagerAdapterForSlider(this, ImgArray))
        val indicator:CircleIndicator = findViewById<View>(R.id.indicator) as CircleIndicator
        indicator.setViewPager(mPager)
    }*/
}
