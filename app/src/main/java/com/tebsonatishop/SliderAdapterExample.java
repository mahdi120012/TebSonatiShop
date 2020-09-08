package com.tebsonatishop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_img_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        //viewHolder.textViewDescription.setText("This is slider item " + position);


     /*   viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });*/

        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load("http://www.robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/slide1.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load("http://www.robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/slide2.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load("http://www.robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/slide3.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load("http://www.robika.ir/ultitled/practice/update_for_tavasi_teb_sonati/slide4.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgCatInRowCatigoury);
            textViewDescription = itemView.findViewById(R.id.textView22);
            this.itemView = itemView;
        }
    }
}
