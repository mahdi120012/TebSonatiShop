package com.tebsonatishop.activities;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.tebsonatishop.R;
import com.tebsonatishop.RecyclerModel;

import java.util.ArrayList;

public class ViewPagerAdapterForSlider extends PagerAdapter {

    private ArrayList<RecyclerModel> images;
    private LayoutInflater inflater;
    private Context context;
    String method;

    public ViewPagerAdapterForSlider(Context context, ArrayList<RecyclerModel> images,String method){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
        this.method = method;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position){
        View myImageLayout = null;
        if (method.matches("slider_ba_hashiye")){
             myImageLayout  = inflater.inflate(R.layout.slider_ba_hashiye, view, false);
        }else if (method.matches("slider")){
             myImageLayout  = inflater.inflate(R.layout.slider, view, false);
        }

        final ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);

        if(images.get(position).getPicture().isEmpty()){
            Picasso.get()
                    .load(R.drawable.no_image)
                    .fit()
                    .error(R.drawable.no_image)
                    .into(myImage);
        }else {

            Picasso.get()
                    .load(images.get(position).getPicture())
                    .error(R.drawable.no_image)
                    .into(myImage);
        }
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(images.get(position).getCity().isEmpty()){

                }else {
                    String data = images.get(position).getCity();
                    Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
                    defaultBrowser.setData(Uri.parse(data));
                    context.startActivity(defaultBrowser);
                }

            }
        });

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}