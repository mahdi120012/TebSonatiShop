package com.tebsonatishop.activities;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tebsonatishop.LoadData;
import com.tebsonatishop.R;
import com.tebsonatishop.RecyclerAdapter;
import com.tebsonatishop.RecyclerModel;
import com.tebsonatishop.Recyclerview;

import java.util.ArrayList;


public class Tab1 extends Fragment {
    private RecyclerAdapter rAdapterYouHaveKnow;
    private ArrayList<RecyclerModel> rModelsYouHaveKnow;
    View inflatedview;
    String onvan;

    public Tab1(String onvan) {
        this.onvan = onvan;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedview = inflater.inflate(R.layout.tab1, container, false);

        ImageView imgSabad = (ImageView) inflatedview.findViewById(R.id.imgSabad);
        TextView txCountSabadKharid = (TextView) inflatedview.findViewById(R.id.txCountSabadKharid);
        RecyclerView rvInSearch = (RecyclerView) inflatedview.findViewById(R.id.rvInSearch);

        ConstraintLayout clWifiState = (ConstraintLayout) inflatedview.findViewById(R.id.clWifiState);

        //TextView onvan = (TextView) inflatedview.findViewById(R.id.tx_onvan);

        //RecyclerView rvInSearch = (RecyclerView) inflatedview.findViewById(R.id.rvInSearch);
        //RecyclerView rvInSearch = (RecyclerView) inflatedview.findViewById(R.id.rvInSearch);


        rModelsYouHaveKnow = new ArrayList();
        rAdapterYouHaveKnow = new RecyclerAdapter(rModelsYouHaveKnow, "add_cat_more", getActivity(), rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid);
        Recyclerview.define_recyclerviewYh(getActivity(), rvInSearch, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"search");


        LoadData.loadCat( getActivity(),rAdapterYouHaveKnow,rModelsYouHaveKnow,rvInSearch,clWifiState);

        return inflatedview;
    }
}