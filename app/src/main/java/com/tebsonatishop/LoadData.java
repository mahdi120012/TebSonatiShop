package com.tebsonatishop;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.tebsonatishop.customClasses.EnglishNumberToPersian;
import com.tebsonatishop.customClasses.ProgressDialogClass;
import com.tebsonatishop.customClasses.SharedPrefClass;
import com.tebsonatishop.customClasses.TimeKononi;
import com.tebsonatishop.customClasses.UrlEncoderClass;
import com.tebsonatishop.placeComment.RecyclerAdapterPlaceComment;
import com.tebsonatishop.placeComment.RecyclerModelPlaceComment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;

public class LoadData {

    public static final int LOAD_LIMIT = 15;
    public static String lastId = "0";
    public static boolean itShouldLoadMore = true;

    public static void loadCat2(final Context c, final RecyclerAdapter recyclerAdapter,
                                final ArrayList<RecyclerModel> recyclerModels,
                                final RecyclerView recyclerView, final ConstraintLayout clWifi,String cat) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat_2&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("cat");
                        String picture = jsonObject.getString("picture");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.loadCat(c, recyclerAdapter, recyclerModels,
                                recyclerView, clWifi);

                    }
                });
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadCat1(final Context c, final RecyclerAdapter recyclerAdapter,
                               final ArrayList<RecyclerModel> recyclerModels,
                               final RecyclerView recyclerView, final ConstraintLayout clWifi,String cat) {
        //Toye android haye paiin hatman baraye ersal matn farsi be server bayad as url encoder estefade konem
        //vagar na erorr mide dastresi be net mojod nist: to url zir baraye ersal cat be server estefade kardim.
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat_1&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("cat");
                        String picture = jsonObject.getString("picture");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.loadCat(c, recyclerAdapter, recyclerModels,
                                recyclerView, clWifi);

                    }
                });
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadAdress(final Context c, final EditText etAddress, final RecyclerAdapter recyclerAdapter,
                                  final ArrayList<RecyclerModel> recyclerModels,
                                  final RecyclerView recyclerView, final ConstraintLayout clWifi) {

        String username = SharedPrefClass.getUserId(c,"user");
        String usernameEncode = UrlEncoderClass.urlEncoder(username);

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_adress&username=" + usernameEncode;
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    return;
                }

                String address = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //lastId = jsonObject.getString("id");
                        //String onvan = jsonObject.getString("cat");
                        address = jsonObject.getString("address");
                        //recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"","",0,"",""));
                        //recyclerAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                etAddress.setText(address);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                //Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.loadCat(c, recyclerAdapter, recyclerModels,
                                recyclerView, clWifi);

                    }
                });
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadCat(final Context c, final RecyclerAdapter recyclerAdapter,
                               final ArrayList<RecyclerModel> recyclerModels,
                               final RecyclerView recyclerView, final ConstraintLayout clWifi) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat&limit=" + LOAD_LIMIT;
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("cat");
                        String picture = jsonObject.getString("picture");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                //Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.loadCat(c, recyclerAdapter, recyclerModels,
                                            recyclerView, clWifi);

                    }
                });
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadCatFood(final Context c, final RecyclerAdapter recyclerAdapter,
                               final ArrayList<RecyclerModel> recyclerModels,
                               final RecyclerView recyclerView, final ConstraintLayout clWifi) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat_food&limit=" + LOAD_LIMIT;
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("cat");
                        String picture = jsonObject.getString("picture");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                //Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.loadCat(c, recyclerAdapter, recyclerModels,
                                recyclerView, clWifi);

                    }
                });
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void firstLoadData(final Context c, final RecyclerViewDataAdapter recyclerAdapter,
                                     final ArrayList<RecyclerModel> recyclerModels,
                                     final RecyclerView recyclerView,
                                     final String cat, final ConstraintLayout clWifi) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_main&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat);
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    //Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        String gheymat_bad_takhfif = jsonObject.getString("gheymat_bad_takhfif");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,gheymat_bad_takhfif));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                //Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadAllMahsolInCat(final Context c, final RecyclerAdapter recyclerAdapter,
                                        final ArrayList<RecyclerModel> recyclerModels,
                                        final RecyclerView recyclerView,
                                        final ConstraintLayout clWifi, String cat2Name) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_main&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat2Name);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadMoreSearch(final Context c, final RecyclerAdapter recyclerAdapter,
                                              final ArrayList<RecyclerModel> recyclerModels, final ProgressBar progressBar,
                                              final ConstraintLayout clWifi,
                                              String query) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_more_search_result&limit=" + LOAD_LIMIT + "&query=" + UrlEncoderClass.urlEncoder(query) + "&last_id=" + lastId;
        itShouldLoadMore = false;
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

              /*  clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                        recyclerView, "جدید ترین", clWifi);
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMoreAllMahsolInCat(final Context c, final RecyclerAdapter recyclerAdapter,
                                              final ArrayList<RecyclerModel> recyclerModels, final ProgressBar progressBar,
                                              final ConstraintLayout clWifi,
                                              String cat2Name) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_more_main&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat2Name) + "&last_id=" + UrlEncoderClass.urlEncoder(lastId);
        itShouldLoadMore = false;
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

              /*  clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                        recyclerView, "جدید ترین", clWifi);
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMoreMahsolInCat1(final Context c, final RecyclerAdapter recyclerAdapter,
                                              final ArrayList<RecyclerModel> recyclerModels, final ProgressBar progressBar,
                                              final ConstraintLayout clWifi,
                                              String cat2Name) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_more_mahsol_in_cat_1&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat2Name) + "&last_id=" + lastId;
        itShouldLoadMore = false;
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

              /*  clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                        recyclerView, "جدید ترین", clWifi);
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }




    public static void loadMoreMahsolInCat2(final Context c, final RecyclerAdapter recyclerAdapter,
                                            final ArrayList<RecyclerModel> recyclerModels, final ProgressBar progressBar,
                                            final ConstraintLayout clWifi,
                                            String cat2Name) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_more_mahsol_in_cat_2&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder(cat2Name) + "&last_id=" + lastId;
        itShouldLoadMore = false;
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

              /*  clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                        recyclerView, "جدید ترین", clWifi);
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMahsolatCat2(final Context c, final RecyclerAdapter recyclerAdapter,
                                  final ArrayList<RecyclerModel> recyclerModels,
                                  final RecyclerView recyclerView,
                                  final ConstraintLayout clWifi, String cat2Name) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat2_mahsolat&limit=" + LOAD_LIMIT + "&cat2_name=" + UrlEncoderClass.urlEncoder(cat2Name);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadMahsolInGazayeFori(final Context c, final RecyclerAdapter recyclerAdapter,
                                           final ArrayList<RecyclerModel> recyclerModels,
                                           final RecyclerView recyclerView,
                                           final ConstraintLayout clWifi, String catFoodName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_ghazaye_fori_mahsolat&limit=" + LOAD_LIMIT + "&cat_food_name=" + UrlEncoderClass.urlEncoder(catFoodName);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMahsolatCatFood(final Context c, final RecyclerAdapter recyclerAdapter,
                                        final ArrayList<RecyclerModel> recyclerModels,
                                        final RecyclerView recyclerView,
                                        final ConstraintLayout clWifi, String catFoodName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_cat_food_mahsolat&limit=" + LOAD_LIMIT + "&cat_food_name=" + UrlEncoderClass.urlEncoder(catFoodName);
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }



    public static void loadMahsolatInCat2(final Context c, final RecyclerAdapter recyclerAdapter,
                                          final ArrayList<RecyclerModel> recyclerModels,
                                          final RecyclerView recyclerView,
                                          final ConstraintLayout clWifi, String cat, final TextView txShowAll) {
        String catEncode = UrlEncoderClass.urlEncoder(cat);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_mahsol_in_cat2&limit=" + LOAD_LIMIT + "&cat=" + catEncode;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                int count = 0;
                count = recyclerAdapter.getItemCount();
                if (count > 0){
                    //txShowAll.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMahsolatInCat1(final Context c, final RecyclerAdapter recyclerAdapter,
                                          final ArrayList<RecyclerModel> recyclerModels, final ProgressBar progressBar,
                                          final ConstraintLayout clWifi, String cat, final TextView txShowAll) {
        String catEncode = UrlEncoderClass.urlEncoder(cat);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_mahsol_in_cat1&limit=" + LOAD_LIMIT + "&cat=" + catEncode;
        itShouldLoadMore = false;

        progressBar.setVisibility(View.VISIBLE);

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);
                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                int count = 0;
                count = recyclerAdapter.getItemCount();
                if (count > 0){
                    txShowAll.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadCheckCodeKharid(final Context c, String codeKharid) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_code_kharid&limit=" + LOAD_LIMIT + "&query=" + codeKharid;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "کد خرید وارد شده اشتباه است", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(c, "اعمال شد", Toast.LENGTH_SHORT).show();
                }
                String code = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        code = jsonObject.getString("code");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
               /* if(code.isEmpty()){
                    Toast.makeText(c, "کد تخفیف وارد شده اشتباه است", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(c, "اعمال شد", Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText(c, "salame", Toast.LENGTH_SHORT).show();
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadSearch(final Context c, final RecyclerAdapter recyclerAdapter,
                                     final ArrayList<RecyclerModel> recyclerModels,
                                     final RecyclerView recyclerView,
                                     final ConstraintLayout clWifi, String query) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_search_result&limit=" + LOAD_LIMIT + "&query=" + query;
        itShouldLoadMore = false;
        //final ProgressDialogClass progressDialog = new ProgressDialogClass();
        //progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                //progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,rate,date,mojod));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                //progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadCommentsForManager(final Context c, final RecyclerAdapter recyclerAdapter,
                                           final ArrayList<RecyclerModel> recyclerModels,
                                           final RecyclerView recyclerView,
                                           final ConstraintLayout clWifi, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_comments_for_manager&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("username");
                        String picture = jsonObject.getString("comment");
                        String cat = jsonObject.getString("p1");
                        //String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("user_id");
                        String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,"",0,"",date));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadUsersForManager(final Context c, final RecyclerAdapter recyclerAdapter,
                                                final ArrayList<RecyclerModel> recyclerModels,
                                                final RecyclerView recyclerView,
                                                final ConstraintLayout clWifi, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_users_for_manager&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("family");
                        String matn = jsonObject.getString("username");
                        String tarikh_ozviyat = jsonObject.getString("tarikh_ozviyat");
                        //String cat = jsonObject.getString("noe_pardakht");
                        //String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("user_id");
                        //String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,"",tarikh_ozviyat,"",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadSefareshatForManager(final Context c, final RecyclerAdapter recyclerAdapter,
                                      final ArrayList<RecyclerModel> recyclerModels,
                                      final RecyclerView recyclerView,
                                      final ConstraintLayout clWifi, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_sefareshat_for_manager&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("vaziyat");
                        String picture = jsonObject.getString("p1");
                        String cat = jsonObject.getString("noe_pardakht");
                        String price = jsonObject.getString("price");
                        String mojod = jsonObject.getString("user_id");
                        String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,cat,price,0,mojod,date));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadGroupSefaresh(final Context c, final RecyclerAdapter recyclerAdapter,
                                      final ArrayList<RecyclerModel> recyclerModels,
                                      String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_sefareshat_in_next_row&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //lastId = jsonObject.getString("id");
                        //String onvan = jsonObject.getString("mahsol_id");
                        //String matn = jsonObject.getString("matn");
                        //String picture = jsonObject.getString("p1");
                        //String cat = jsonObject.getString("cat");
                        //String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("mojod");
                        //String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel("10", "salam","","","","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                //clWifi.setVisibility(View.VISIBLE);
            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadSefareshat(final Context c, final RecyclerAdapter recyclerAdapter,
                                  final ArrayList<RecyclerModel> recyclerModels,
                                  final RecyclerView recyclerView,
                                  final ConstraintLayout clWifi, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_sefareshat&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        //String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("p1");
                        //String cat = jsonObject.getString("cat");
                        String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("mojod");
                        String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,"",picture,"",price,0,"",date));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void loadSabadKharid(final Context c, final RecyclerAdapter recyclerAdapter,
                                      final ArrayList<RecyclerModel> recyclerModels,
                                      final RecyclerView recyclerView,
                                      final ConstraintLayout clWifi, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_sabad_kharid&limit=" + LOAD_LIMIT + "&username=" + userName;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String tedadSefaresh = jsonObject.getString("tedad_sefaresh");
                        String picture = jsonObject.getString("p1");
                        String mahsolId = jsonObject.getString("mahsol_id");
                        String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("mojod");
                        //String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, onvan,tedadSefaresh,picture,mahsolId,price,0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void removeAllTempMahsolYekUser(final Context c, final ConstraintLayout clWifi,
                                         String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=remove_all_temp_mahsol&username=" + userName;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String jsonError = null;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    jsonError = new String(networkResponse.data);
                    // Print Error!
                }

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "سبد خرید خالی شد.", Toast.LENGTH_SHORT).show();
              /*  clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*//*
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void removeTempMahsol(final Context c, final ConstraintLayout clWifi,
                                     String mahsolId, String userName) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=remove_temp_mahsol&username=" + userName + "&mahsol_id=" + mahsolId ;
        itShouldLoadMore = false;
        //final ProgressDialogClass progressDialog = new ProgressDialogClass();
        //progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                //progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String jsonError = null;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    jsonError = new String(networkResponse.data);
                    // Print Error!
                }

                itShouldLoadMore = true;
                //progressDialog.dismissProgress();
                Toast.makeText(c, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
              /*  clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*//*
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void payment(final Context c, String price) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=pay&price=" + price;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
              /*  clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*//*
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void addTempMahsol(final Context c, final ConstraintLayout clWifi,
                                     String mahsolId, String userName,String tedadSefaresh,String time) {

        String timeEncode = UrlEncoderClass.urlEncoder(time);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=add_temp_mahsol&username=" + userName + "&mahsol_id=" + mahsolId + "&tedad_sefaresh=" + tedadSefaresh + "&time=" + timeEncode ;
        itShouldLoadMore = false;
        //final ProgressDialogClass progressDialog = new ProgressDialogClass();
        //progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                //progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String jsonError = null;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    jsonError = new String(networkResponse.data);
                    // Print Error!
                }

                itShouldLoadMore = true;
                //progressDialog.dismissProgress();
                Toast.makeText(c, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
              /*  clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*//*
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void sendRate(final Context c, final RecyclerAdapterPlaceComment recyclerAdapter,
                                final ArrayList<RecyclerModelPlaceComment> recyclerModels,
                                final RecyclerView recyclerView, final ConstraintLayout clWifi,
                                String username, String mahsol_id, String rate,
                                final SimpleRatingBar ratingBar, final TextView numberOfRate) {
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=sendrate&limit=" + LOAD_LIMIT + "&username=" + username + "&mahsol_id=" + mahsol_id + "&rate=" + rate;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        float rate = Float.valueOf(jsonObject.getString("rate"));
                        String numberOfAllRate = (jsonObject.getString("all_rate_count"));
                        ratingBar.setRating(rate);

                        EnglishNumberToPersian englishNumberToPersian = new EnglishNumberToPersian();

                        numberOfRate.setText(englishNumberToPersian.convert(numberOfAllRate) + " نظر");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void firstLoadDataPlaceComment(final Context c, final RecyclerAdapterPlaceComment recyclerAdapter,
                                  final ArrayList<RecyclerModelPlaceComment> recyclerModels,
                                  final RecyclerView recyclerView,final ConstraintLayout clWifi,
                                  final String mahsol_id) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=comment&limit=" + LOAD_LIMIT + "&mahsol_id=" + mahsol_id;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    //Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String username = jsonObject.getString("username");
                        String name = jsonObject.getString("family");
                        String picture = jsonObject.getString("picture");
                        String comment = jsonObject.getString("comment");
                        recyclerModels.add(new RecyclerModelPlaceComment(username, name,picture,comment));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void firstLoadDataMahsolAct(final Context c, final RecyclerAdapter recyclerAdapter,
                                                 final ArrayList<RecyclerModel> recyclerModels,
                                                 final RecyclerView recyclerView,final ConstraintLayout clWifi,
                                                 final String mahsol_id) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_mahsol&limit=" + LOAD_LIMIT + "&mahsol_id=" + mahsol_id;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //lastId = jsonObject.getString("id");
                        //String onvan = jsonObject.getString("onvan");
                        //String matn = jsonObject.getString("matn");
                        String p1 = jsonObject.getString("p1");
                        //String p2 = jsonObject.getString("p2");
                        //String cat = jsonObject.getString("cat");
                        //String price = jsonObject.getString("price");
                        //String mojod = jsonObject.getString("mojod");
                        //String date = jsonObject.getString("date");
                        recyclerModels.add(new RecyclerModel(lastId, "","",p1,"","",0,"",""));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void resultAfterPayment(final Context c, final ConstraintLayout clWifi, final TextView txVaziyat,
                                          final TextView txDate, final TextView txSaAt, final TextView txVaziyat2,
                                          final TextView txDate2, final TextView txSaAt2, final TextView txCodeErja,
                                          final TextView txCodeTarakonesh, final TextView txMablagh, String userName) {
        String userNameEncode = UrlEncoderClass.urlEncoder(userName);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=after_pay&username=" + userNameEncode;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                TimeKononi timeKononi = new TimeKononi();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String mahsol_id = jsonObject.getString("mahsol_id");
                        String user_id = jsonObject.getString("user_id");
                        String vaziyat = jsonObject.getString("vaziyat");
                        String noe_pardakht = jsonObject.getString("noe_pardakht");
                        String date = jsonObject.getString("date");
                        String code_sefaresh = jsonObject.getString("code_sefaresh");

                        String persianDate = timeKononi.changeGregorianToPersianAndReturnYMD(date);
                        String persianDateOnlyClock = timeKononi.changeGregorianToPersianAndReturnHiS(date);

                        txVaziyat.setText(vaziyat);
                        txDate.setText(persianDate);
                        txSaAt.setText(persianDateOnlyClock);
                        txVaziyat2.setText(vaziyat);
                        txDate2.setText(persianDate);
                        txSaAt2.setText(persianDateOnlyClock);
                        txCodeErja.setText("-");
                        txCodeTarakonesh.setText(code_sefaresh);
                        txMablagh.setText("-");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void firstLoadDataRate(final Context c, final ConstraintLayout clWifi,final String mahsol_id,
                                         final SimpleRatingBar ratingBar, final TextView numberOfRate
                                         ) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=loadrate&limit=" + LOAD_LIMIT + "&mahsol_id=" + mahsol_id;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                float rate;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //line zir bara ine ke float null nmigire va age null begire error mide, pass avval baresi mikonim
                        //null ee ya na:
                        if (jsonObject.has("rate") && !jsonObject.isNull("rate")) {
                            rate = Float.valueOf(jsonObject.getString("rate"));
                            ratingBar.setRating(rate);
                        }

                        String numberOfAllRate = (jsonObject.getString("all_rate_count"));
                        EnglishNumberToPersian englishNumberToPersian = new EnglishNumberToPersian();

                        numberOfRate.setText(englishNumberToPersian.convert(numberOfAllRate) + " نظر");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,
                                recyclerView, "جدید ترین", clWifi);*/
                    }
                });

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }

    public static void sendVisit(final Context c, String id) {

        String idEncode = UrlEncoderClass.urlEncoder(id);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=send_visit&id=" + idEncode;

        StringRequest jsonArrayRequest = new StringRequest (Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        if (response.length() <= 0) {
                            Toast.makeText(c, "No data available", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void sendComment(final Context c, final RecyclerAdapterPlaceComment recyclerAdapter,
                                   final ArrayList<RecyclerModelPlaceComment> recyclerModels,
                                   final RecyclerView recyclerView,
                                   final String id, final EditText etComment, String username,
                                   String name, String comment,String time, final ProgressBar progressBar) {

        String idEncode = UrlEncoderClass.urlEncoder(id);
        String usernameEncode = UrlEncoderClass.urlEncoder(username);
        String nameEncode = UrlEncoderClass.urlEncoder(name);
        String commentEncode = UrlEncoderClass.urlEncoder(comment);
        String timeEncode = UrlEncoderClass.urlEncoder(time);
        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=sendcomment&limit=" + LOAD_LIMIT  + "&id=" + idEncode + "&username=" + usernameEncode + "&name=" + nameEncode + "&comment=" + commentEncode + "&time=" + timeEncode;
        itShouldLoadMore = false;
        final ProgressDialog progressDialog = new ProgressDialog(c);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest jsonArrayRequest = new StringRequest (Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                       /* webView.setVisibility(View.GONE);
                        img_refresh.setVisibility(View.GONE);
                        net_state.setText("");*/
                        progressDialog.dismiss();
                        itShouldLoadMore = true;

                        if (response.length() <= 0) {
                            Toast.makeText(c, "No data available", Toast.LENGTH_SHORT).show();

                            return;
                        }

                        etComment.setText("");
                        if (response.equals("send_shod")){
                            Toast.makeText(c, "ارسال شد", Toast.LENGTH_SHORT).show();
                            //Line Zir Baraye neshon dadan comment pas az ersal comment be server va namayesh to recyclerviewee.
                            LoadData.loadMorePlaceComment(c,recyclerAdapter,recyclerModels,progressBar,id);

                        }else {
                            Toast.makeText(c, "ارسال نشد", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itShouldLoadMore = true;
                progressDialog.dismiss();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();

                /*net_state.setText("دسترسی به اینترنت موجود نیست.");
                net_state.setTextSize(18);
                img_refresh.setVisibility(View.GONE);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/refresh_gif.gif");


                webView.postDelayed(new Runnable() {
                    public void run() {
                        webView.onPause();

                        //------>توضیح مهم، درصورت فعالسازی کد زیر وب ویو پرداخت همراه پی با مشکل مواجه میشود<-----
                        //webView.pauseTimers();
                    }
                }, 5000);

                //new JSONDownloader(c,jsonURL, rv,rv2,img_refresh).execute(net_state);



                webView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        //webView.onResume();
                        //webView.reload();
                        webView.onResume();
                        webView.resumeTimers();

                        webView.postDelayed(new Runnable() {
                            public void run() {
                                webView.onPause();
                                webView.pauseTimers();
                            }
                        }, 10000);

                        LoadData.firstLoadDataPlaceComment(c,recyclerAdapter,recyclerModels,img_refresh,webView,timer,net_state,recyclerView,city,id);



                        return false;
                    }
                });
                img_refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //new JSONDownloader(c,jsonURL, rv,rv2,img_refresh).execute(net_state);

                    }
                });*/


            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }


    public static void loadMorePlaceComment(final Context c, final RecyclerAdapterPlaceComment recyclerAdapter,
                                            final ArrayList<RecyclerModelPlaceComment> recyclerModels,
                                            final ProgressBar progressBar, final String mahsolId) {


        String url = "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=loadmore_comment&lastId=" + lastId + "&limit=" + LOAD_LIMIT + "&mahsol_id=" + mahsolId;

        itShouldLoadMore = false;
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);

                itShouldLoadMore = true;
                if (response.length() <= 0) {
                    // we need to check this, to make sure, our dataStructure JSonArray contains
                    // something
                    //Toast.makeText(c, "no data available", Toast.LENGTH_SHORT).show();
                    return; // return will end the program at this point
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String username = jsonObject.getString("username");
                        String name = jsonObject.getString("family");
                        String picture = jsonObject.getString("picture");
                        String comment = jsonObject.getString("comment");
                        recyclerModels.add(new RecyclerModelPlaceComment(username, name, picture, comment));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                itShouldLoadMore = true;
                Toast.makeText(c, "خطار در بارگزاری . دسترسی به اینترنت موجود نیست", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);

    }

}
