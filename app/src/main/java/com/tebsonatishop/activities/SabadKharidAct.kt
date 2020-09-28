package com.tebsonatishop.activities

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
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

            clickDialogItems(this)

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
        //startActivity(Intent(this, MainActivity::class.java))
        //overridePendingTransition(R.transition.enter_left_to_right, R.transition.exit_left_to_right)
        finish()

    }

    fun clickDialogItems(
        context:Context
    ) {
        val dialog = Dialog(context, R.style.customDialog2)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_dialog, null, false)
        val txCancel = view.findViewById<TextView>(R.id.txCancel)
        val txRemove = view.findViewById<TextView>(R.id.txRemove)
        val txHazfPayam = view.findViewById<TextView>(R.id.txHazfPayam)
        val txMatnHazfPayam = view.findViewById<TextView>(R.id.txMatnHazfPayam)
        val txRad = view.findViewById<TextView>(R.id.txRad)

        txCancel.setOnClickListener {
            dialog.dismiss()
        }



        txRemove.setOnClickListener {

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


                //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

        }
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialog.setContentView(view)
            val window = dialog.window
            window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            window.setGravity(Gravity.CENTER)
            //line zir baraye transparent kardan hashiye haye cardview ee:
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }


}
