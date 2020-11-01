package com.tebsonatishop.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener


//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
class MainActivity2 : AppCompatActivity(), OnTabSelectedListener {

    private var tabLayout:TabLayout? = null
    private var viewPager:ViewPager? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.tebsonatishop.R.layout.activity_main2)

        tabLayout = findViewById<View>(com.tebsonatishop.R.id.tabLayout) as TabLayout

        tabLayout!!.addTab(tabLayout!!.newTab().setText("اساتید"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("محصولات"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        viewPager = findViewById<View>(com.tebsonatishop.R.id.pager) as ViewPager

        val adapter = Pager(supportFragmentManager, tabLayout!!.tabCount,"","load_cat")

        viewPager!!.adapter = adapter

        tabLayout!!.setOnTabSelectedListener(this)
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
    }

    override fun onTabSelected(tab:TabLayout.Tab) {
        viewPager!!.currentItem = tab.position
    }

    override fun onTabUnselected(tab:TabLayout.Tab) {}
    override fun onTabReselected(tab:TabLayout.Tab) {}
}