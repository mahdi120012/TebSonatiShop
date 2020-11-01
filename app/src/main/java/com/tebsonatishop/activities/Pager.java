package com.tebsonatishop.activities;


import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tebsonatishop.R;
import com.tebsonatishop.RecyclerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    String onvan,method;


    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount,String onvan,String method) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.onvan = onvan;
        this.method = method;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {


        if (method.contains("load_cat")) {

            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1(onvan);
                    return tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                default:
                    return null;
            }

        }else if (method.contains("load_sefareshat")) {
            switch (position) {
            case 0:
                TabSefareshatFeli tabSefareshatFeli = new TabSefareshatFeli(onvan);
                return tabSefareshatFeli;
            case 1:

                TabSefareshatGhabli tabSefareshatGhabli = new TabSefareshatGhabli(onvan);
                return tabSefareshatGhabli;
            default:
                return null;
        }}

        return null;
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}