package com.tebsonatishop.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tebsonatishop.*
import kotlinx.android.synthetic.main.address_ha.*
import kotlinx.android.synthetic.main.net_connection.*
import java.util.*

class AddressHa : AppCompatActivity() {

    private var rAdapterYouHaveKnow:RecyclerAdapter? = null
    private var rModelsYouHaveKnow:ArrayList<RecyclerModel>? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_ha)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window:Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#c82c15"))
        }

        clAddAddress.setOnClickListener {
            val i = Intent(this, Address::class.java)
            i.putExtra("add_address", "add_address")
            startActivity(i)
            overridePendingTransition(R.transition.enter_right_to_left,R.transition.exit_right_to_left)
        }

        imgExit.setOnClickListener { finish() }

        rModelsYouHaveKnow = ArrayList()

        rAdapterYouHaveKnow = RecyclerAdapter(rModelsYouHaveKnow,"address_ha",this,
            rAdapterYouHaveKnow,null,null,null,null,null,
            null,null,null)

        Recyclerview.define_recyclerviewYh(
            this, rv1, rAdapterYouHaveKnow, rModelsYouHaveKnow, null, "search")


        LoadData.loadAdress(this,rAdapterYouHaveKnow,rModelsYouHaveKnow,rv1,clWifiState)

    }
}