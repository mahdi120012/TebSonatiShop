package com.tebsonatishop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tebsonatishop.activities.MoreCatMain;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<SectionDataModelRecyclerModel> dataList;
    private Context mContext;
    ImageView imgSabad;
    TextView txCountSabadKharid;
    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModelRecyclerModel> dataList,
                                   ImageView imgSabad,TextView txCountSabadKharid) {
        this.dataList = dataList;
        this.mContext = context;
        this.imgSabad = imgSabad;
        this.txCountSabadKharid = txCountSabadKharid;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_row_nested_list, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);

        RecyclerAdapter itemListDataAdapter = new RecyclerAdapter(singleSectionItems,"add_main",mContext,null,null,imgSabad,"",txCountSabadKharid);



        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setItemViewCacheSize(20);
        itemRowHolder.recycler_view_list.setDrawingCacheEnabled(true);
        itemRowHolder.recycler_view_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);
        itemRowHolder.recycler_view_list.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));



       /*  itemRowHolder.recycler_view_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/

        itemRowHolder.txMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sectionName.contains("محصولات")){
                    String a =sectionName.replace(" محصولات","");
                    //Toast.makeText(v.getContext(), "click event on more, "+a , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MoreCatMain.class);
                    intent.putExtra("onvan", a);
                    mContext.startActivity(intent);

                }else {
                    Intent intent = new Intent(mContext, MoreCatMain.class);
                    intent.putExtra("onvan", sectionName);
                    mContext.startActivity(intent);

                }




            }
        });







       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected TextView txMore;



        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle =  view.findViewById(R.id.itemTitle);
            this.recycler_view_list =view.findViewById(R.id.recycler_view_list);
            this.txMore=  view.findViewById(R.id.txMore);


        }

    }

}