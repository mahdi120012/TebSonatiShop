package com.tebsonatishop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;
import com.tebsonatishop.activities.Address;
import com.tebsonatishop.activities.Cat1;
import com.tebsonatishop.activities.Cat2;
import com.tebsonatishop.activities.Cat2_click;
import com.tebsonatishop.activities.CatAsatidClick;
import com.tebsonatishop.activities.Cat_Food;
import com.tebsonatishop.activities.Mahsol;
import com.tebsonatishop.activities.Payment;
import com.tebsonatishop.activities.SabadKharidAct;
import com.tebsonatishop.customClasses.EnglishNumberToPersian;
import com.tebsonatishop.customClasses.SharedPrefClass;
import com.tebsonatishop.customClasses.TimeKononi;

import java.util.ArrayList;
import java.util.Timer;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private RecyclerAdapter rAdapterYouHaveKnow;
    private ArrayList<RecyclerModel> rModelsYouHaveKnow;

    private ArrayList<RecyclerModel> recyclerModels; // this data structure carries our title and description
    Context c;
    String rowLayoutType,className;
    RecyclerAdapter RecyclerAdapter;
    ImageView img_refresh,imgSabad;
    ConstraintLayout imgSabtSefaresh;
    WebView webView;
    Timer timer;
    TextView tx_state,txGeymatKol,txGeymatKolBadTakhfif,txCountSabadKharid,txEmalCodeKharid;
    RecyclerView rvAddJalase,rvMahsol;
    String jalaseId;
    int width;
    EditText etCity,etPhoneNumber,etAddress,etCodeKharid;
    public static int numberofSefaresh;
    public static int sumPrice;
    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels, String rowLayoutType, Context c,
                           RecyclerAdapter recyclerAdapterYouHaveKnow, ConstraintLayout imgSabtSefaresh, TextView txGeymatKol,
                           TextView txGeymatKolBadTakhfif,
                           EditText etCity,EditText etPhoneNumber, EditText etAddress, EditText etCodeKharid,TextView txEmalCodeKharid) {
        this.recyclerModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.RecyclerAdapter = recyclerAdapterYouHaveKnow;
        this.imgSabtSefaresh = imgSabtSefaresh;
        this.txGeymatKol = txGeymatKol;
        this.txGeymatKolBadTakhfif = txGeymatKolBadTakhfif;
        this.etCity = etCity;
        this.etPhoneNumber = etPhoneNumber;
        this.etAddress = etAddress;
        this.etCodeKharid = etCodeKharid;
        this.txEmalCodeKharid = txEmalCodeKharid;
    }

    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels, String rowLayoutType, Context c,
                           RecyclerAdapter recyclerAdapterYouHaveKnow, String className,
                           ImageView imgSabad,String a,TextView txCountSabadKharid) {
        this.recyclerModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.RecyclerAdapter = recyclerAdapterYouHaveKnow;
        this.className = className;
        this.imgSabad = imgSabad;
        this.txCountSabadKharid = txCountSabadKharid;
    }

    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels,String rowLayoutType, Context c,int width,RecyclerView rvMahsol) {
        this.recyclerModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.width = width;
        this.rvMahsol = rvMahsol;
    }

    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels, String rowLayoutType, Context c,
                           RecyclerAdapter recyclerAdapterYouHaveKnow,
                           ImageView img_refresh, WebView webView, Timer timer, TextView tx_state,
                           RecyclerView rvAddJalase, String className, String jalaseId) {
        this.recyclerModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.RecyclerAdapter = recyclerAdapterYouHaveKnow;
        this.className = className;
        this.img_refresh = img_refresh;
        this.webView = webView;
        this.timer = timer;
        this.tx_state = tx_state;
        this.rvAddJalase = rvAddJalase;
        this.jalaseId = jalaseId;

    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate your custom row layout here
        if (rowLayoutType.contains("add_catigoury")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_catigoury, parent, false));

        }else if (rowLayoutType.contains("add_food_cat")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food_cat, parent, false));

        }else if (rowLayoutType.contains("add_main")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false));

        }else if (rowLayoutType.contains("search")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search, parent, false));

        }else if (rowLayoutType.contains("sefareshat")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sefareshat, parent, false));

        }else if (rowLayoutType.contains("sefaresh_for_manager")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sefareshat_for_manager, parent, false));

        }else if (rowLayoutType.contains("users_for_manager")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users_for_manager, parent, false));

        }else if (rowLayoutType.contains("comments_for_manager")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comments_for_manager, parent, false));

        }else if (rowLayoutType.contains("mahsol")){
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mahsol, parent, false));

        }else if (rowLayoutType.contains("sabad_kharid")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sabad_kharid, parent, false));

        }else if (rowLayoutType.contains("cat1")){
        return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat1_va_cat2, parent, false));

        }else if (rowLayoutType.contains("cat2")){
        return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat1_va_cat2, parent, false));

        }else if (rowLayoutType.contains("add_cat_more")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat1_va_cat2, parent, false));

        }else if (rowLayoutType.contains("cat_asatid")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat1_va_cat2, parent, false));

        }else if (rowLayoutType.contains("address_ha")){
        return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_addressha, parent, false));

    }


        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

            TimeKononi timeKononi = new TimeKononi();
            final String time = timeKononi.getGregorianTime();
          if (rowLayoutType.contains("add_catigoury")){

              holder.txCatName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){

                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);

              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .centerCrop()
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgCatPicture);
              }

              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String onvan = recyclerModels.get(position).getOnvan();
                      Intent intent = new Intent(c, Cat1.class);
                      intent.putExtra("onvan", onvan);
                      c.startActivity(intent);

                  }
              });


          }else if (rowLayoutType.contains("add_food_cat")){

              holder.txCatName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){

                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);

              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .centerCrop()
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgCatPicture);
              }

              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String onvan = recyclerModels.get(position).getOnvan();
                      Intent intent = new Intent(c, Cat_Food.class);
                      intent.putExtra("onvan", onvan);
                      c.startActivity(intent);

                  }
              });



          }else if (rowLayoutType.contains("add_main")){

              //numberofSefaresh = 0;
              txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

              String persianNumberInOnvan = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianNumberInOnvan);

              //line zir baraye joda kardate 3 ragham 3 ragham gheymat bekar mireh,
              //baraye inke hatman to hameye adad dorost jodasazi kone bayad lyout direction and text direction
              //toye textview == ltr bashe.

              String jodaSazi3Ragham3RaghamGheymat;
              if (recyclerModels.get(position).getPosition().isEmpty() || recyclerModels.get(position).getPosition().matches("[0-9]+") == false){
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt("0"));
              }else {
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt(recyclerModels.get(position).getPosition()));
              }
              String persianPrice = new EnglishNumberToPersian().convert(jodaSazi3Ragham3RaghamGheymat);
              holder.txPrice.setText(persianPrice);


              if(recyclerModels.get(position).getIdTimeTakhir().length() >= 1){
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                      holder.txPrice.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                      holder.txPriceBadTakhfif.setVisibility(View.VISIBLE);
                      holder.txPriceBadTakhfif.setText(recyclerModels.get(position).getIdTimeTakhir());
                  }
              }

              holder.ratingBar.setRating(recyclerModels.get(position).getRate());

              if(recyclerModels.get(position).getPicture().isEmpty()){

                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);

              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }


              imgSabad.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(c, SabadKharidAct.class);
                      c.startActivity(intent);
                  }
              });

          /*    if (txCountSabadKharid.getText().toString().matches("0")){

                  imgSabad.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Toast.makeText(c, "سبد خرید خالی است.", Toast.LENGTH_SHORT).show();
                      }
                  });

              }else {

              }*/


              holder.imgMahsolPicture.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String id = recyclerModels.get(position).getId();
                      String onvan = recyclerModels.get(position).getOnvan();
                      String matn = recyclerModels.get(position).getMatn();
                      String picture = recyclerModels.get(position).getPicture();
                      float rate = recyclerModels.get(position).getRate();
                      Intent intent = new Intent(c, Mahsol.class);
                      intent.putExtra("id", id);
                      intent.putExtra("onvan", onvan);
                      intent.putExtra("matn", matn);
                      intent.putExtra("picture", picture);
                      intent.putExtra("rate", rate);
                      c.startActivity(intent);

                  }
              });


                  holder.imgAddToSabadKharid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                     /* if (userName.length() <= 0 ) {
                          Toast.makeText(c, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show();
                      }else {*/
                          holder.imgAddToSabadKharid.setVisibility(View.GONE);
                          holder.imgRedManfi.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setVisibility(View.VISIBLE);
                          holder.imgRedPlus.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setText("1");

                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);
                          numberofSefaresh += Integer.parseInt(holder.txCountSefaresh.getText().toString());
                          txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                     /* }*/
                  }
              });

              holder.imgRedManfi.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                          if (holder.txCountSefaresh.getText().toString().matches("1")){
                              holder.imgAddToSabadKharid.setVisibility(View.VISIBLE);
                              holder.imgRedPlus.setVisibility(View.GONE);
                              holder.imgRedManfi.setVisibility(View.GONE);
                              holder.txCountSefaresh.setVisibility(View.GONE);
                              LoadData.removeTempMahsol(c,null, recyclerModels.get(position).getId(), userName);

                              numberofSefaresh --;
                              txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                          }else {
                              int numberofSefareshYekMahsol = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                              numberofSefareshYekMahsol--;
                              holder.txCountSefaresh.setText(String.valueOf(numberofSefareshYekMahsol));

                              numberofSefaresh --;
                              txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                              LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);



                          }
                  }
              });

              holder.imgRedPlus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");


                           int numberofSefareshYekMahsol = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                           numberofSefareshYekMahsol++;
                           holder.txCountSefaresh.setText(String.valueOf(numberofSefareshYekMahsol));

                           numberofSefaresh ++;
                           txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                           LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);

                  }
              });


          }else if (rowLayoutType.contains("search")){

              //txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

              String persianTextNumber = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianTextNumber);

              String jodaSazi3Ragham3RaghamGheymat;
              if (recyclerModels.get(position).getPosition().isEmpty() || recyclerModels.get(position).getPosition().matches("[0-9]+") == false){
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt("0"));
              }else {
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt(recyclerModels.get(position).getPosition()));
              }
              String persianPrice = new EnglishNumberToPersian().convert(jodaSazi3Ragham3RaghamGheymat);
              holder.txPrice.setText(persianPrice);

              holder.ratingBar.setRating(recyclerModels.get(position).getRate());

              if(recyclerModels.get(position).getPicture().isEmpty()){

                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);

              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }


              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String id = recyclerModels.get(position).getId();
                      String onvan = recyclerModels.get(position).getOnvan();
                      String matn = recyclerModels.get(position).getMatn();
                      String picture = recyclerModels.get(position).getPicture();
                      float rate = recyclerModels.get(position).getRate();
                      Intent intent = new Intent(c, Mahsol.class);
                      intent.putExtra("id", id);
                      intent.putExtra("onvan", onvan);
                      intent.putExtra("matn", matn);
                      intent.putExtra("picture", picture);
                      intent.putExtra("rate", rate);
                      c.startActivity(intent);

                  }
              });


/*              imgSabad.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(c, SabadKharidAct.class);
                      c.startActivity(intent);
                  }
              });*/

             /* if (txCountSabadKharid.getText().toString().matches("0")){

                  imgSabad.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Toast.makeText(c, "سبد خرید خالی است.", Toast.LENGTH_SHORT).show();
                      }
                  });

              }else {

              }*/

              holder.imgAddToSabadKharid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                      if (userName.length() <= 0 ) {
                          Toast.makeText(c, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show();
                      }else {
                          holder.imgAddToSabadKharid.setVisibility(View.GONE);
                          holder.imgRedManfi.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setVisibility(View.VISIBLE);
                          holder.imgRedPlus.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setText("1");
                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);
                          numberofSefaresh += Integer.parseInt(holder.txCountSefaresh.getText().toString());
                          txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                      }
                  }
              });

              holder.imgRedManfi.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                      if (holder.txCountSefaresh.getText().toString().matches("1")){
                          holder.imgAddToSabadKharid.setVisibility(View.VISIBLE);
                          holder.imgRedPlus.setVisibility(View.GONE);
                          holder.imgRedManfi.setVisibility(View.GONE);
                          holder.txCountSefaresh.setVisibility(View.GONE);
                          LoadData.removeTempMahsol(c,null, recyclerModels.get(position).getId(), userName);
                          numberofSefaresh --;
                          txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                      }else {
                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);

                          int numberofSefareshYekMahsol = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                          numberofSefareshYekMahsol--;
                          holder.txCountSefaresh.setText(String.valueOf(numberofSefareshYekMahsol));

                          numberofSefaresh --;
                          txCountSabadKharid.setText(String.valueOf(numberofSefaresh));
                      }
                  }
              });

              holder.imgRedPlus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");
                      LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString(),time);

                      int numberofSefareshYekMahsol = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                      numberofSefareshYekMahsol++;
                      holder.txCountSefaresh.setText(String.valueOf(numberofSefareshYekMahsol));

                      numberofSefaresh ++;
                      txCountSabadKharid.setText(String.valueOf(numberofSefaresh));

                  }
              });

          /*    holder.imgAddToSabadKharid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");
                      if (userName.length() <= 0 ) {
                          Toast.makeText(c, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show();
                      }else {
                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getId(), userName,holder.txCountSefaresh.getText().toString());
                      }
                  }
              });*/

          }else if (rowLayoutType.contains("sefareshat")){

              String persianTextNumber = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianTextNumber);

              String jodaSazi3Ragham3RaghamGheymat;
              if (recyclerModels.get(position).getPosition().isEmpty() || recyclerModels.get(position).getPosition().matches("[0-9]+") == false){
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt("0"));
              }else {
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt(recyclerModels.get(position).getPosition()));
              }

              String persianPrice = new EnglishNumberToPersian().convert(jodaSazi3Ragham3RaghamGheymat);
              holder.txPrice.setText(persianPrice);

              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }

              String persianDate = timeKononi.changeGregorianToPersian(recyclerModels.get(position).getIdTimeTakhir());
              holder.txDateOrder.setText(persianDate);

          }else if (rowLayoutType.contains("sefaresh_for_manager")){

              String persianNumberTest = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianNumberTest);

              String jodaSazi3Ragham3RaghamGheymat;
              if (recyclerModels.get(position).getPosition().isEmpty() || recyclerModels.get(position).getPosition().matches("[0-9]+") == false){
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt("0"));
              }else {
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt(recyclerModels.get(position).getPosition()));
              }

              String persianPrice = new EnglishNumberToPersian().convert(jodaSazi3Ragham3RaghamGheymat);
              holder.txPrice.setText(persianPrice);

              holder.txVaziyatPardakht.setText(recyclerModels.get(position).getMatn());
              holder.txNoePardakht.setText(recyclerModels.get(position).getCity());
              holder.txUserPardakhtKonande.setText(recyclerModels.get(position).getCountRateAndComment());

              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }

              String persianDate = timeKononi.changeGregorianToPersian(recyclerModels.get(position).getIdTimeTakhir());
              holder.txDateOrder.setText(persianDate);


          }else if (rowLayoutType.contains("users_for_manager")){

              holder.txMahsolName.setText(recyclerModels.get(position).getOnvan());
              holder.txUserPardakhtKonande.setText(recyclerModels.get(position).getMatn());

              String persianDate = timeKononi.changeGregorianToPersian(recyclerModels.get(position).getCity());
              holder.txDateOrder.setText(persianDate);

          }else if (rowLayoutType.contains("comments_for_manager")){

              String persianTextNumber = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianTextNumber);

              holder.txUserPardakhtKonande.setText(recyclerModels.get(position).getMatn());
              holder.txVaziyatPardakht.setText(recyclerModels.get(position).getPicture());

              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getCity())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }


              String persianDate = timeKononi.changeGregorianToPersian(recyclerModels.get(position).getIdTimeTakhir());
              holder.txDateOrder.setText(persianDate);


          }else if (rowLayoutType.contains("sabad_kharid")){


              holder.cardSabadKharid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      //Toast.makeText(c, recyclerModels.get(position).getMatn(), Toast.LENGTH_SHORT).show();
                      String id = recyclerModels.get(position).getId();
                      String mahsolId = recyclerModels.get(position).getCity();
                      String onvan = recyclerModels.get(position).getOnvan();
                      String matn = recyclerModels.get(position).getCountRateAndComment();
                      String picture = recyclerModels.get(position).getPicture();
                      float rate = recyclerModels.get(position).getRate();
                      Intent intent = new Intent(c, Mahsol.class);
                      intent.putExtra("id", mahsolId);
                      intent.putExtra("onvan", onvan);
                      intent.putExtra("matn", matn);
                      intent.putExtra("picture", picture);
                      intent.putExtra("rate", rate);
                      c.startActivity(intent);
                  }
              });







              String persianTextNumber = new EnglishNumberToPersian().convert(recyclerModels.get(position).getOnvan());
              holder.txMahsolName.setText(persianTextNumber);


              String jodaSazi3Ragham3RaghamGheymat;
              if (recyclerModels.get(position).getPosition().isEmpty() || recyclerModels.get(position).getPosition().matches("[0-9]+") == false){
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt("0"));
              }else {
                  jodaSazi3Ragham3RaghamGheymat = String.format("%,d", Integer.parseInt(recyclerModels.get(position).getPosition()));
              }

              String persianPrice = new EnglishNumberToPersian().convert(jodaSazi3Ragham3RaghamGheymat);
              holder.txPrice.setText(persianPrice);

              holder.txCountSefaresh.setText(recyclerModels.get(position).getMatn());

              sumPrice = 0;
              for (int i = 0; i <= position; i++) {
                  //to line zir migim ke age tadad yek mahsol bishtar az 10 bod
                  //tedad mahsol ro zarb dar gheymetesh kone ke gheymat kol dorost dar biyad:
                  if (Integer.parseInt(recyclerModels.get(i).getMatn())>1){
                      sumPrice += Integer.parseInt(recyclerModels.get(i).getPosition())*
                                  Integer.parseInt(recyclerModels.get(i).getMatn()) ;
                  }else {
                      if (recyclerModels.get(i).getPosition() == null || recyclerModels.get(i).getPosition().isEmpty()){

                      }else {
                          sumPrice += Integer.parseInt(recyclerModels.get(i).getPosition());
                      }
                  }

              }

              if (numberofSefaresh>=10){

                  int takhfif_10Darsad = (int)(sumPrice*(10.0f/100.0f));

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                      txGeymatKol.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                      txGeymatKolBadTakhfif.setVisibility(View.VISIBLE);
                      txGeymatKolBadTakhfif.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                      txGeymatKol.setText(sumPrice +" هزار تومان");
                  }else {

                      txGeymatKol.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                  }


              }else {

                  txGeymatKol.setText(sumPrice +" هزار تومان");
              }


              txEmalCodeKharid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      LoadData.loadCheckCodeKharid(c, etCodeKharid.getText().toString());
                  }
              });

              imgSabtSefaresh.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String city = etCity.getText().toString();
                        String phoneNumber = etPhoneNumber.getText().toString();
                        String address = etAddress.getText().toString();
                        String codeKharid = etCodeKharid.getText().toString();

                      if(city.length() <= 0 || city == null || phoneNumber.length() <= 0 || phoneNumber == null || address.length() <= 0 || address == null){
                          Toast.makeText(c, "لطفا همه فیلدها را وارد کنید", Toast.LENGTH_SHORT).show();
                      }else {

                          Intent intent = new Intent(c, Payment.class);
                          intent.putExtra("price", String.valueOf(sumPrice));
                          intent.putExtra("address", city + "/" + address);
                          intent.putExtra("phone_number", phoneNumber);
                          intent.putExtra("code_kharid", codeKharid);
                          c.startActivity(intent);

                         //String url = "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=pay&price=" + price;
                          //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                          //c.startActivity(browserIntent);



                      }
                  }
              });

              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);

              }

              holder.imgAddToSabadKharid.setOnClickListener(new View.OnClickListener() {
                  @RequiresApi(api = Build.VERSION_CODES.M)
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                      /*if (userName.length() <= 0 ) {
                          Toast.makeText(c, "ابتدا در برنامه وارد شوید.", Toast.LENGTH_SHORT).show();
                      }else {*/
                          holder.imgAddToSabadKharid.setVisibility(View.GONE);
                          holder.imgRedManfi.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setVisibility(View.VISIBLE);
                          holder.imgRedPlus.setVisibility(View.VISIBLE);
                          holder.txCountSefaresh.setText("1");

                          numberofSefaresh ++;

                          sumPrice += Integer.parseInt(recyclerModels.get(position).getPosition());

                          if (numberofSefaresh>=10){

                              int takhfif_10Darsad = (int)(sumPrice*(10.0f/100.0f));

                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                  txGeymatKol.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                                  txGeymatKolBadTakhfif.setVisibility(View.VISIBLE);

                                  txGeymatKolBadTakhfif.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                                  txGeymatKol.setText(sumPrice +" هزار تومان");
                              }else {
                                  txGeymatKol.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                              }


                          }else {
                              txGeymatKolBadTakhfif.setText("0");
                              txGeymatKolBadTakhfif.setVisibility(View.GONE);
                              txGeymatKol.setForeground(ContextCompat.getDrawable(c, android.R.color.transparent));
                              txGeymatKol.setText(sumPrice +" هزار تومان");
                          }

                          //txGeymatKol.setText(String.valueOf(sumPrice)+" هزار تومان");

                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getCity(), userName,holder.txCountSefaresh.getText().toString(),time);

                      /*}*/
                  }
              });

              holder.imgRedManfi.setOnClickListener(new View.OnClickListener() {
                  @RequiresApi(api = Build.VERSION_CODES.M)
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                      if (holder.txCountSefaresh.getText().toString().matches("1")){
                          holder.imgAddToSabadKharid.setVisibility(View.VISIBLE);
                          holder.imgRedPlus.setVisibility(View.GONE);
                          holder.imgRedManfi.setVisibility(View.GONE);
                          holder.txCountSefaresh.setVisibility(View.GONE);

                          numberofSefaresh --;

                          sumPrice -= Integer.parseInt(recyclerModels.get(position).getPosition());

                          if (numberofSefaresh>=10){

                              int takhfif_10Darsad = (int)(sumPrice*(10.0f/100.0f));

                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                  txGeymatKol.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                                  txGeymatKolBadTakhfif.setVisibility(View.VISIBLE);
                                  txGeymatKolBadTakhfif.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                                  txGeymatKol.setText(sumPrice +" هزار تومان");
                              }else {
                                  txGeymatKol.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                              }


                          }else {
                              txGeymatKolBadTakhfif.setText("0");
                              txGeymatKolBadTakhfif.setVisibility(View.GONE);
                              txGeymatKol.setForeground(ContextCompat.getDrawable(c, android.R.color.transparent));
                              txGeymatKol.setText(sumPrice +" هزار تومان");
                          }

                          LoadData.removeTempMahsol(c,null, recyclerModels.get(position).getCity(), userName);

                      }else {
                          int numberofSefaresha = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                          numberofSefaresha--;
                          holder.txCountSefaresh.setText(String.valueOf(numberofSefaresha));

                          numberofSefaresh --;

                          sumPrice -= Integer.parseInt(recyclerModels.get(position).getPosition());

                          if (numberofSefaresh>=10){

                              int takhfif_10Darsad = (int)(sumPrice*(10.0f/100.0f));

                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                  txGeymatKol.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                                  txGeymatKolBadTakhfif.setVisibility(View.VISIBLE);
                                  txGeymatKolBadTakhfif.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                                  txGeymatKol.setText(sumPrice +" هزار تومان");
                              }else {
                                  txGeymatKol.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                              }


                          }else {
                              txGeymatKolBadTakhfif.setText("0");
                              txGeymatKolBadTakhfif.setVisibility(View.GONE);
                              txGeymatKol.setForeground(ContextCompat.getDrawable(c, android.R.color.transparent));
                              txGeymatKol.setText(sumPrice +" هزار تومان");
                          }


                          LoadData.addTempMahsol(c,null, recyclerModels.get(position).getCity(), userName,holder.txCountSefaresh.getText().toString(),time);

                      }
                  }
              });

              holder.imgRedPlus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String userName = SharedPrefClass.getUserId(c,"user");

                      int numberofSefaresha = Integer.parseInt(holder.txCountSefaresh.getText().toString());
                      numberofSefaresha++;
                      holder.txCountSefaresh.setText(String.valueOf(numberofSefaresha));

                      numberofSefaresh ++;
                      sumPrice += Integer.parseInt(recyclerModels.get(position).getPosition());



                      if (numberofSefaresh>=10){

                          int takhfif_10Darsad = (int)(sumPrice*(10.0f/100.0f));

                          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                              txGeymatKol.setForeground(ContextCompat.getDrawable(c, R.drawable.red_line));
                              txGeymatKolBadTakhfif.setVisibility(View.VISIBLE);
                              txGeymatKolBadTakhfif.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                              txGeymatKol.setText(sumPrice +" هزار تومان");
                          }else {
                              txGeymatKol.setText((sumPrice - takhfif_10Darsad) +" هزار تومان");
                          }


                      }else {
                          txGeymatKol.setText(sumPrice +" هزار تومان");
                      }

                      LoadData.addTempMahsol(c,null, recyclerModels.get(position).getCity(), userName,holder.txCountSefaresh.getText().toString(),time);

                  }
              });

          }else if (rowLayoutType.contains("mahsol")){

              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPic);
              }else {

                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPic);
              }

             /* holder.imgNext.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                          rvMahsol.smoothScrollToPosition(position+1);
                  }
              });

              holder.imgPrev.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      rvMahsol.smoothScrollToPosition(position-1);
                  }
              });

              //(getItemCount()-1) baraye fahmidan ine ke be akharin view residim ya na
              if (position==(getItemCount()-1)){
                  holder.imgNext.setVisibility(View.GONE);
              }

              if (position== 0 ){
                  holder.imgPrev.setVisibility(View.GONE);
              }*/


          }else if (rowLayoutType.contains("cat1")){
              holder.txMahsolName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }



              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String onvan = recyclerModels.get(position).getOnvan();
                      Intent intent = new Intent(c, Cat2.class);
                      intent.putExtra("onvan", onvan);
                      c.startActivity(intent);

                  }
              });



          }else if (rowLayoutType.contains("address_ha")){
              holder.txOnvan.setText(recyclerModels.get(position).getMatn());
              holder.txAddress.setText(recyclerModels.get(position).getPicture());
              String telephone = recyclerModels.get(position).getCity();

              if (telephone.isEmpty()) {
                  String userName = SharedPrefClass.getUserId(c, "user");
                  String user = userName.substring(1);
                  holder.txMobile.setText("+98 " + user);
              }else {
                  holder.txMobile.setText(telephone);
              }


              holder.clMain.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      Intent intent = new Intent(c, Address.class);
                      intent.putExtra("id", recyclerModels.get(position).getId());
                      intent.putExtra("onvan", recyclerModels.get(position).getMatn());
                      intent.putExtra("address", recyclerModels.get(position).getPicture());
                      intent.putExtra("telephone", recyclerModels.get(position).getCity());
                      c.startActivity(intent);

                     Activity activity = (Activity)c;
                     activity.finish();


                  }
              });

          }else if (rowLayoutType.contains("cat_asatid")){
              holder.txMahsolName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }



              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String name_ostad = recyclerModels.get(position).getMatn();
                      Intent intent = new Intent(c, CatAsatidClick.class);
                      intent.putExtra("name_ostad", name_ostad);
                      c.startActivity(intent);

                  }
              });


          }else if (rowLayoutType.contains("add_cat_more")){
              holder.txMahsolName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }



              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String onvan = recyclerModels.get(position).getOnvan();
                      Intent intent = new Intent(c, Cat1.class);
                      intent.putExtra("onvan", onvan);
                      c.startActivity(intent);

                  }
              });


         }else if (rowLayoutType.contains("cat2")){
              holder.txMahsolName.setText(recyclerModels.get(position).getOnvan());


              if(recyclerModels.get(position).getPicture().isEmpty()){
                  Picasso.get()
                          .load(R.drawable.no_image)
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }else {
                  Picasso.get()
                          .load(recyclerModels.get(position).getPicture())
                          .fit()
                          .error(R.drawable.no_image)
                          .into(holder.imgMahsolPicture);
              }


              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String onvan = recyclerModels.get(position).getOnvan();
                      Intent intent = new Intent(c, Cat2_click.class);
                      intent.putExtra("onvan", onvan);
                      c.startActivity(intent);

                  }
              });
          }

    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txCatName,txMahsolName,txPrice,txCountSefaresh,txPriceBadTakhfif,txDateOrder,
        txVaziyatPardakht,txNoePardakht,txUserPardakhtKonande,txOnvan,txAddress,txMobile;
        ImageView imgCatPicture,imgMahsolPicture,imgAddToSabadKharid,imgRedManfi,imgRedPlus,imgMahsolPic,
                  imgNext,imgPrev;
        SimpleRatingBar ratingBar;
        CardView cardSabadKharid;
        ConstraintLayout clMain;
        EditText etCity,etPhoneNumber,etAddress;
        MyViewHolder(View view) {
            super(view);
            txMobile = itemView.findViewById(R.id.txMobile);

            clMain = itemView.findViewById(R.id.clMain);
            txOnvan = itemView.findViewById(R.id.txOnvan);
            txAddress = itemView.findViewById(R.id.txAddress);

            cardSabadKharid = itemView.findViewById(R.id.cardSabadKharid);
            imgNext = itemView.findViewById(R.id.imgNext);
            imgPrev = itemView.findViewById(R.id.imgPrev);

            txVaziyatPardakht = itemView.findViewById(R.id.txVaziyatPardakhtMahsolInRowMain);
            txNoePardakht = itemView.findViewById(R.id.txNoePardakhtMahsolInRowMain);
            txUserPardakhtKonande = itemView.findViewById(R.id.txUserPardakhtKonandehMahsolInRowMain);

            txDateOrder = itemView.findViewById(R.id.txDateOrderInRowMain);
            imgMahsolPic = itemView.findViewById(R.id.imgMahsolPic);
            ratingBar = itemView.findViewById(R.id.ratingBarMain);
            txMahsolName = itemView.findViewById(R.id.txNameMahsolInRowMain);
            imgMahsolPicture = itemView.findViewById(R.id.imgMahsolPictureInRowMain);

            txCountSefaresh = itemView.findViewById(R.id.txCountSefareshInRow);
            imgRedManfi = itemView.findViewById(R.id.imgRedManfi);
            imgRedPlus = itemView.findViewById(R.id.imgRedPlus);
            imgAddToSabadKharid = itemView.findViewById(R.id.imgAddToSabadKharid);

            txPrice = itemView.findViewById(R.id.txPriceMahsolInRowMain);

            txPriceBadTakhfif = itemView.findViewById(R.id.txNameMahsolInRowMain4);

            txCatName = itemView.findViewById(R.id.txCatNameInRowCatigoury);
            imgCatPicture = itemView.findViewById(R.id.imgCatInRowCatigoury);

        }
    }
}