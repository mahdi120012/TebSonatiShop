package com.tebsonatishop;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tebsonatishop.placeComment.RecyclerAdapterPlaceComment;
import com.tebsonatishop.placeComment.RecyclerModelPlaceComment;

import java.util.ArrayList;

public class Recyclerview {

    public static void define_recyclerviewYh(final Context c, RecyclerView recyclerView,
                                             final RecyclerAdapter recyclerAdapter,
                                             final ArrayList<RecyclerModel> recyclerModels,
                                             final ProgressBar progressBar, String method) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        if (method.contains("search")) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));

        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, true));
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // for this tutorial, this is the ONLY method that we need, ignore the rest
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Recycle view scrolling downwards...
                    // this if statement detects when user reaches the end of recyclerView, this is only time we should load more
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        // remember "!" is the same as "== false"
                        // here we are now allowed to load more, but we need to be careful
                        // we must check if itShouldLoadMore variable is true [unlocked]
                        if (LoadData.itShouldLoadMore) {
                            //LoadData.loadMoreAllMahsolInCat(c, recyclerAdapter, recyclerModels,cat2Name);
                        }
                    }

                }
            }
        });
    }


    public static void define_recyclerviewYh(final Context c, RecyclerView recyclerView,
                                             final RecyclerAdapter recyclerAdapter,
                                             final ArrayList<RecyclerModel> recyclerModels,
                                             final ProgressBar progressBar, final ConstraintLayout clWifi, String method, final String cat2Name,
                                             final String mehodForLoadData) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        if (method.contains("search")) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));

        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, true));
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // for this tutorial, this is the ONLY method that we need, ignore the rest
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Recycle view scrolling downwards...
                    // this if statement detects when user reaches the end of recyclerView, this is only time we should load more
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        // remember "!" is the same as "== false"
                        // here we are now allowed to load more, but we need to be careful
                        // we must check if itShouldLoadMore variable is true [unlocked]
                        if (LoadData.itShouldLoadMore) {
                            if(mehodForLoadData == "loadSearch"){
                                LoadData.loadMoreSearch(c, recyclerAdapter, recyclerModels, progressBar, clWifi, cat2Name);

                            }else if (mehodForLoadData == "loadAllMahsolInCat"){
                                LoadData.loadMoreAllMahsolInCat(c, recyclerAdapter, recyclerModels, progressBar, clWifi, cat2Name);
                            }
                        }
                    }

                }
            }
        });
    }


    public static void defineRecyclerviewPC(final Context c, RecyclerView recyclerView,
                                            final RecyclerAdapterPlaceComment recyclerAdapter,
                                            final ArrayList<RecyclerModelPlaceComment> recyclerModels,
                                            final ProgressBar progressBar, NestedScrollView nestedScrollView,
                                            final String post_id) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerAdapter);

        //To Khat Zir Baraye in Az Nested Scroll View Estefade Kardim Ke Recyclerview ScrollViewosh Vaghti Toye
        //Nested ScrollView Bashe Kar Nmikone Baraye Hamin Baraye Scroll To Recyclerview Bayad Az
        //nestedScrollView.setOnScrollChangeListener Estefade konim:
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {

                    //Log.i(TAG, "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    //Log.i(TAG, "Scroll UP");
                }

                if (scrollY == 0) {
                    //Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (LoadData.itShouldLoadMore) {
                        LoadData.loadMorePlaceComment(c, recyclerAdapter, recyclerModels, progressBar, post_id);
                    }
                }
            }
        });

    }


    public static void define_RecyclerviewCat(final Context c, RecyclerView recyclerView,
                                              final RecyclerAdapter recyclerAdapter,
                                              final ArrayList<RecyclerModel> recyclerModels,
                                              final ProgressBar progressBar, NestedScrollView nestedScrollView,
                                              final ConstraintLayout clWifi, String method, final String cat2Name,
                                              final String mehodForLoadData) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        if (method.contains("search")) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));

        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, true));
        }

        //To Khat Zir Baraye in Az Nested Scroll View Estefade Kardim Ke Recyclerview ScrollViewosh Vaghti Toye
        //Nested ScrollView Bashe Kar Nmikone Baraye Hamin Baraye Scroll To Recyclerview Bayad Az
        //nestedScrollView.setOnScrollChangeListener Estefade konim:
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {

                    //Log.i(TAG, "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    //Log.i(TAG, "Scroll UP");
                }

                if (scrollY == 0) {
                    //Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (LoadData.itShouldLoadMore) {
                        if (mehodForLoadData == "loadMahsolatInCat1") {
                            LoadData.loadMoreMahsolInCat1(c, recyclerAdapter, recyclerModels, progressBar, clWifi, cat2Name);

                        } else if (mehodForLoadData == "loadMahsolatInCat2") {
                            LoadData.loadMoreMahsolInCat2(c, recyclerAdapter, recyclerModels, progressBar, clWifi, cat2Name);

                        }
                    }
                }
            }
        });

    }

}
