package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tebsonatishop.*
import com.tebsonatishop.customClasses.SharedPrefClass
import com.tebsonatishop.user_info.Main_user_login_activity
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabad_kharid_act.*
import kotlinx.android.synthetic.main.sefareshat.rvSefareshat
import kotlinx.android.synthetic.main.toolbar_button.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.util.*


class SabadKharidAct : AppCompatActivity() {

    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sabad_kharid_act)

        imgIconToolbarTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.trash))
        toolbarTopaa.setBackgroundColor(Color.parseColor("#efefef"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#bfbfbf")
        }

        txOnvanInActionBar.setTextColor(Color.parseColor("#4a4a4a"))
        txOnvanInActionBar.text = "سبد خرید"
        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back1))

        imgNavigationTop.setOnClickListener {
            finish()
        }

        etCodeKharid.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s:Editable) {

            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

            }

            override fun onTextChanged(
                s:CharSequence, start:Int, before:Int, count:Int
            ) {

                if (etCodeKharid.text.isEmpty()){
                    txEmalCodeKharid.visibility = View.GONE
                }else{
                    txEmalCodeKharid.visibility = View.VISIBLE
                }
            }
        })

        imgIconToolbarTop.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this, "user")
            LoadData.removeAllTempMahsolYekUser(this, null, userName)


            //line zir faghat baraye khali kardane recyclerview eee:
            rModelsYouHaveKnow = ArrayList()

            rAdapterYouHaveKnow = RecyclerAdapter(
                rModelsYouHaveKnow,
                "sabad_kharid",
                this,
                rAdapterYouHaveKnow,
                imgSabtSefaresh,
                txGheymatKol,
                txGheymatKolBadTakhfif,
                etCityName,
                etPhoneNumber,
                etAddress,etCodeKharid,txEmalCodeKharid
            )
            Recyclerview.define_recyclerviewYh(
                this, rvSefareshat, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, "search"
            )

            txGheymatKol.text = "0 تومان"
            RecyclerAdapter.numberofSefaresh = 0
        }


        var list = ArrayList<String>()
        list.add("")
        list.add("قم")

        var spinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.spinnter_dropdown_custom, list
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnter_dropdown_custom)
        spinner.adapter = spinnerArrayAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView:AdapterView<*>, view:View, i:Int, l:Long
            ) {

                var selectedItem = adapterView.getItemAtPosition(i).toString()
                if (selectedItem.length <= 0) {

                } else {

                    etCityName.hint = "نام شهر"
                    etCityName.setText(selectedItem)
                    spinner.setSelection(0)
                }

            }

            override fun onNothingSelected(adapterView:AdapterView<*>?) {}
        }


        imgSabadKharid.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.sabadkharid_red
            )
        )
        rModelsYouHaveKnow = ArrayList()

        rAdapterYouHaveKnow = RecyclerAdapter(
            rModelsYouHaveKnow,
            "sabad_kharid",
            this,
            rAdapterYouHaveKnow,
            imgSabtSefaresh,
            txGheymatKol,
            txGheymatKolBadTakhfif,
            etCityName,
            etPhoneNumber,
            etAddress,etCodeKharid,txEmalCodeKharid
        )
        Recyclerview.define_recyclerviewYh(
            this, rvSefareshat, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, "search"
        )


        var userName = SharedPrefClass.getUserId(this, "user")

        LoadData.loadSabadKharid(
            this, rAdapterYouHaveKnow, rModelsYouHaveKnow, rvSefareshat, clWifiState, userName
        )

        imgHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(
                R.transition.enter_left_to_right, R.transition.exit_left_to_right
            )
        }

        imgProfile.setOnClickListener {
            var userName = SharedPrefClass.getUserId(this, "user")
            if (userName.length <= 0) {
                startActivity(Intent(this, Main_user_login_activity::class.java))
                overridePendingTransition(
                    R.transition.enter_left_to_right, R.transition.exit_left_to_right
                )
            } else {
                Toast.makeText(this, "شما وارد برنامه شده اید.", Toast.LENGTH_SHORT).show()
            }
        }

        imgSearch.setOnClickListener {
            val intent = Intent(this, MoreCat::class.java)
            intent.putExtra("onvan", "دسته بندی")
            this.startActivity(intent)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)

        }
        imgListSefareshat.setOnClickListener {
            startActivity(Intent(this, Sefareshat::class.java))
            overridePendingTransition(
                R.transition.enter_right_to_left, R.transition.exit_right_to_left
            )

        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }
}
