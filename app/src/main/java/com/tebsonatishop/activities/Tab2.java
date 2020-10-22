package com.tebsonatishop.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tebsonatishop.LoadData;
import com.tebsonatishop.R;
import com.tebsonatishop.RecyclerAdapter;
import com.tebsonatishop.RecyclerModel;
import com.tebsonatishop.Recyclerview;

import java.util.ArrayList;


public class Tab2 extends Fragment {
    private RecyclerAdapter rAdapterYouHaveKnow;
    private ArrayList<RecyclerModel> rModelsYouHaveKnow;
    View inflatedview;

    public Tab2()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedview = inflater.inflate(R.layout.tab2, container, false);

        ImageView imgSabad = (ImageView) inflatedview.findViewById(R.id.imgSabad);
        TextView txCountSabadKharid = (TextView) inflatedview.findViewById(R.id.txCountSabadKharid);
        RecyclerView rv1 = (RecyclerView) inflatedview.findViewById(R.id.rv1);

        ConstraintLayout clWifiState = (ConstraintLayout) inflatedview.findViewById(R.id.clWifiState);


        rModelsYouHaveKnow = new ArrayList();
        rAdapterYouHaveKnow = new RecyclerAdapter(rModelsYouHaveKnow, "cat_asatid", getActivity(), rAdapterYouHaveKnow, "",imgSabad,"" , txCountSabadKharid);
        Recyclerview.define_recyclerviewYh(getActivity(), rv1, rAdapterYouHaveKnow, rModelsYouHaveKnow, null,"search");

        LoadData.loadAsatid(getActivity(),rAdapterYouHaveKnow,rModelsYouHaveKnow,rv1,clWifiState);

        return inflatedview;
    }
}