package com.gipra.vicibcomplete.MembersArea.Gene;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.Registration;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiumPlanGenealogy extends AppCompatActivity {
    PopupWindow popupWindow;
    LinearLayout layout_premium;


    private static final String TAG = "BinaryGene";
    ImageView p_imgone,p_imgtwo,p_imgthree,p_imgfour,p_imgfive,p_imgsix,p_imgseven;
    LinearLayout p_llimgone,lltwo,llthree,llfour,llfive,llsix,llseven;
    LinearLayout p_llmain;
    TextView p_txtone,p_txttwo,p_txtthree,p_txtfour,p_txtfive,p_txtsix,p_txtseven;
    private ArrayList<ListPremiumPlanGenealogy> listPremiumPlanGenealogy;
    TextView p_username_one,sponsor_userid_one,sponsor_username_one,type_one,leftGBV_one,rightGBV_one,leftcount_one,rightcount_one,leftactive_one,rightactive_one;
    TextView username_two,sponsor_userid_two,sponsor_username_two,type_two,leftGBV_two,rightGBV_two,leftcount_two,rightcount_two,leftactive_two,rightactive_two;
    TextView username_three,sponsor_userid_three,sponsor_username_three,type_three,leftGBV_three,rightGBV_three,leftcount_three,rightcount_three,leftactive_three,rightactive_three;
    TextView username_four,sponsor_userid_four,sponsor_username_four,type_four,leftGBV_four,rightGBV_four,leftcount_four,rightcount_four,leftactive_four,rightactive_four;
    TextView username_five,sponsor_userid_five,sponsor_username_five,type_five,leftGBV_five,rightGBV_five,leftcount_five,rightcount_five,leftactive_five,rightactive_five;
    TextView username_six,sponsor_userid_six,sponsor_username_six,type_six,leftGBV_six,rightGBV_six,leftcount_six,rightcount_six,leftactive_six,rightactive_six;
    TextView username_seven,sponsor_userid_seven,sponsor_username_seven,type_seven,leftGBV_seven,rightGBV_seven,leftcount_seven,rightcount_seven,leftactive_seven,rightactive_seven;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    String t0,t1,t2,t3,t4,t5,t6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_plan_genealogy);

        Toolbar toolbar=findViewById(R.id.premium_geneToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ImageView premium_info = findViewById(R.id.premium_info);
        layout_premium = findViewById(R.id.layout_premium);
        premium_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) PremiumPlanGenealogy.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View premium_popup = layoutInflater.inflate(R.layout.premium_genepopup, null);

                popupWindow = new PopupWindow(premium_popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(layout_premium, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
            }
        });
        p_imgone = findViewById(R.id.p_imgone);
        p_txtone = findViewById(R.id.p_txtone);
        p_imgtwo = findViewById(R.id.p_imgtwo);
        p_imgthree = findViewById(R.id.p_imgthree);
        p_txttwo = findViewById(R.id.p_txttwo);
        p_txtthree = findViewById(R.id.p_txtthree);
        p_imgfour = findViewById(R.id.p_imgfour);
        p_imgfive = findViewById(R.id.p_imgfive);
        p_imgsix = findViewById(R.id.p_imgsix);
        p_imgseven = findViewById(R.id.p_imgseven);
        p_txtfour = findViewById(R.id.p_txtfour);
        p_txtfive = findViewById(R.id.p_txtfive);
        p_txtsix = findViewById(R.id.p_txtsix);
        p_txtseven = findViewById(R.id.p_txtseven);


        SharedPreferences shpref;
        shpref = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        gene(u);
        p_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid1.equals("0")) {


                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);

//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();


                } else {
                    gene(uid1);
                }


            }
        });
        p_txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid2.equals("0")) {

                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                } else {
                    gene(uid2);
                }


            }
        });
        p_txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid3.equals("0")) {

                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                } else {
                    gene(uid3);
                }

            }
        });
        p_txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid4.equals("0")) {

                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                } else {
                    gene(uid4);
                }

            }
        });
        p_txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid5.equals("0")) {

                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                } else {
                    gene(uid5);
                }

            }
        });
        p_txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid6.equals("0")) {

                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                } else {
                    gene(uid6);
                }

            }
        });
    }
    private  void gene(final String id){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePremiumGenealogy>call=api.PremiumGene(Integer.parseInt(id));
        call.enqueue(new Callback<ResponsePremiumGenealogy>() {
            @Override
            public void onResponse(Call<ResponsePremiumGenealogy> call, Response<ResponsePremiumGenealogy> response) {
                if (response.body().getStatus().equals("1")){
                    final ResponsePremiumGenealogy responsePremiumGenealogy = response.body();
                    listPremiumPlanGenealogy = (ArrayList<ListPremiumPlanGenealogy>)responsePremiumGenealogy.getData();

                    String uname=listPremiumPlanGenealogy.get(0).getName();
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("USERNAME",uname);

//                   postion 0

                    uid0=listPremiumPlanGenealogy.get(0).getUserid();
                    String mactive0=listPremiumPlanGenealogy.get(0).getMemberActive();
                    String mbactive0=listPremiumPlanGenealogy.get(0).getMemberbronzeActive();
                    String bactive0=listPremiumPlanGenealogy.get(0).getBasicActive();
                    t0=listPremiumPlanGenealogy.get(0).getName();



                    p_txtone.setText(t0);
                    if (uid0.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgone);
                    }
                    else if (mactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgone);
                    }
                    else if (mbactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgone);
                    }
                    else if (bactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgone);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgone);
                    }



//                   postion 1

                    uid1=listPremiumPlanGenealogy.get(1).getUserid();
                    String mactive1=listPremiumPlanGenealogy.get(1).getMemberActive();
                    String mbactive1=listPremiumPlanGenealogy.get(1).getMemberbronzeActive();
                    String bactive1=listPremiumPlanGenealogy.get(1).getBasicActive();
                    t1=listPremiumPlanGenealogy.get(1).getName();


                    p_txttwo.setText(t1);
                    if (uid1.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgtwo);
                    }
                    else if (mactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgtwo);
                    }
                    else if (mbactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgtwo);
                    }
                    else if (bactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgtwo);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgtwo);
                    }


//                   postion 2

                    uid2=listPremiumPlanGenealogy.get(2).getUserid();
                    String mactive2=listPremiumPlanGenealogy.get(2).getMemberActive();
                    String mbactive2=listPremiumPlanGenealogy.get(2).getMemberbronzeActive();
                    String bactive2=listPremiumPlanGenealogy.get(2).getBasicActive();
                    t2=listPremiumPlanGenealogy.get(2).getName();

                    p_txtthree.setText(t2);
                    if (uid2.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgtwo);
                    }
                    else if (mactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgtwo);
                    }
                    else if (mbactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgtwo);
                    }
                    else if (bactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgtwo);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgtwo);
                    }


//                   postion 3

                    uid3=listPremiumPlanGenealogy.get(3).getUserid();
                    String mactive3=listPremiumPlanGenealogy.get(3).getMemberActive();
                    String mbactive3=listPremiumPlanGenealogy.get(3).getMemberbronzeActive();
                    String bactive3=listPremiumPlanGenealogy.get(3).getBasicActive();
                    t3=listPremiumPlanGenealogy.get(3).getName();
                    p_txtfour.setText(t3);

                    if (uid3.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgthree);
                    }
                    else if (mactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgthree);
                    }
                    else if (mbactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgthree);
                    }
                    else if (bactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgthree);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgthree);
                    }

//                   postion 4

                    uid4=listPremiumPlanGenealogy.get(4).getUserid();
                    String mactive4=listPremiumPlanGenealogy.get(4).getMemberActive();
                    String mbactive4=listPremiumPlanGenealogy.get(4).getMemberbronzeActive();
                    String bactive4=listPremiumPlanGenealogy.get(4).getBasicActive();
                    t4=listPremiumPlanGenealogy.get(4).getName();
                    p_txtfive.setText(t4);



                    if (uid4.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgfive);
                    }
                    else if (mactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgfive);
                    }
                    else if (mbactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgfive);
                    }
                    else if (bactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgfive);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgfive);
                    }


//                   postion 5

                    uid5=listPremiumPlanGenealogy.get(5).getUserid();
                    String mactive5=listPremiumPlanGenealogy.get(5).getMemberActive();
                    String mbactive5=listPremiumPlanGenealogy.get(5).getMemberbronzeActive();
                    String bactive5=listPremiumPlanGenealogy.get(5).getBasicActive();
                    t5=listPremiumPlanGenealogy.get(5).getName();
                    p_txtsix.setText(t5);



                    if (uid5.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgsix);
                    }
                    else if (mactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgsix);
                    }
                    else if (mbactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgsix);
                    }
                    else if (bactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgsix);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgsix);
                    }



//                   postion 6

                    uid6=listPremiumPlanGenealogy.get(6).getUserid();
                    String mactive6=listPremiumPlanGenealogy.get(6).getMemberActive();
                    String mbactive6=listPremiumPlanGenealogy.get(6).getMemberbronzeActive();
                    String bactive6=listPremiumPlanGenealogy.get(6).getBasicActive();
                    t6=listPremiumPlanGenealogy.get(6).getName();
                    p_txtseven.setText(t6);


                    if (uid6.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(p_imgseven);
                    }
                    else if (mactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgseven);
                    }
                    else if (mbactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgseven);
                    }
                    else if (bactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgseven);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgseven);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponsePremiumGenealogy> call, Throwable t) {

            }
        });

    }
}