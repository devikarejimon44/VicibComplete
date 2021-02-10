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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiumPlanGenealogy extends AppCompatActivity {
    PopupWindow popupWindow;
    LinearLayout p_layout;


    private static final String TAG = "BinaryGene";
    ImageView p_imgone,p_imgtwo,p_imgthree,p_imgfour,p_imgfive,p_imgsix,p_imgseven;
    LinearLayout p_llimgone,p_llimgtwo,p_llimgthree,p_llimgfour,p_llimgfive,p_llimgsix,p_llimgseven;
    LinearLayout p_llmain;
    TextView p_txtone,p_txttwo,p_txtthree,p_txtfour,p_txtfive,p_txtsix,p_txtseven;
    private ArrayList<ListPremiumPlanGenealogy> listPremiumPlanGenealogy;
    TextView p_username_one,p_username_two,p_username_three,p_username_four,p_username_five,p_username_six,p_username_seven;
    TextView p_name_one,p_name_two,p_name_three,p_name_four,p_name_five,p_name_six,p_name_seven;
    TextView p_sponsor_username_one,p_sponsor_username_two,p_sponsor_username_three,p_sponsor_username_four,p_sponsor_username_five,p_sponsor_username_six,p_sponsor_username_seven;
    TextView p_sponsor_name_one,p_sponsor_name_two,p_sponsor_name_three,p_sponsor_name_four,p_sponsor_name_five,p_sponsor_name_six,p_sponsor_name_seven;
    TextView p_leftBV_one,p_leftBV_two,p_leftBV_three,p_leftBV_four,p_leftBV_five,p_leftBV_six,p_leftBV_seven;
    TextView p_rightBV_one,p_rightBV_two,p_rightBV_three,p_rightBV_four,p_rightBV_five,p_rightBV_six,p_rightBV_seven;
    TextView p_leftcount_one,p_leftcount_two,p_leftcount_three,p_leftcount_four,p_leftcount_five,p_leftcount_six,p_leftcount_seven;
    TextView p_rightcount_one,p_rightcount_two,p_rightcount_three,p_rightcount_four,p_rightcount_five,p_rightcount_six,p_rightcount_seven;
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
        p_layout = findViewById(R.id.p_layout);
        premium_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater layoutInflater = (LayoutInflater) PremiumPlanGenealogy.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View premium_popup = layoutInflater.inflate(R.layout.standard_genepopup, null);
//
//                popupWindow = new PopupWindow(premium_popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.showAtLocation(layout_premium, Gravity.CENTER, 0, 0);
//                popupWindow.setFocusable(true);
//                popupWindow.update();
                InfoBottomSheet();

            }
        });







        p_imgone = findViewById(R.id.p_imgone);
        p_txtone = findViewById(R.id.p_txtone);

        p_imgtwo = findViewById(R.id.p_imgtwo);
        p_txttwo = findViewById(R.id.p_txttwo);

        p_imgthree = findViewById(R.id.p_imgthree);
        p_txtthree = findViewById(R.id.p_txtthree);

        p_imgfour = findViewById(R.id.p_imgfour);
        p_txtfour = findViewById(R.id.p_txtfour);

        p_imgfive = findViewById(R.id.p_imgfive);
        p_txtfive = findViewById(R.id.p_txtfive);

        p_imgsix = findViewById(R.id.p_imgsix);
        p_txtsix = findViewById(R.id.p_txtsix);

        p_imgseven = findViewById(R.id.p_imgseven);
        p_txtseven = findViewById(R.id.p_txtseven);




        p_llimgone=findViewById(R.id.p_llimgone);
        p_llimgtwo=findViewById(R.id.p_llimgtwo);
        p_llimgthree=findViewById(R.id.p_llimgthree);
        p_llimgfour=findViewById(R.id.p_llimgfour);
        p_llimgfive=findViewById(R.id.p_llimgfive);
        p_llimgsix=findViewById(R.id.p_llimgsix);
        p_llimgseven=findViewById(R.id.p_llimgseven);

        p_username_one=findViewById(R.id.p_username_one);
        p_username_two=findViewById(R.id.p_username_two);
        p_username_three=findViewById(R.id.p_username_three);
        p_username_four=findViewById(R.id.p_username_four);
        p_username_five=findViewById(R.id.p_username_five);
        p_username_six=findViewById(R.id.p_username_six);
        p_username_seven=findViewById(R.id.p_username_seven);

        p_name_one=findViewById(R.id.p_name_one);
        p_name_two=findViewById(R.id.p_name_two);
        p_name_three=findViewById(R.id.p_name_three);
        p_name_four=findViewById(R.id.p_name_four);
        p_name_five=findViewById(R.id.p_name_five);
        p_name_six=findViewById(R.id.p_name_six);
        p_name_seven=findViewById(R.id.p_name_seven);


        p_sponsor_username_one=findViewById(R.id.p_sponsor_username_one);
        p_sponsor_username_two=findViewById(R.id.p_sponsor_username_two);
        p_sponsor_username_three=findViewById(R.id.p_sponsor_username_three);
        p_sponsor_username_four=findViewById(R.id.p_sponsor_username_four);
        p_sponsor_username_five=findViewById(R.id.p_sponsor_username_five);
        p_sponsor_username_six=findViewById(R.id.p_sponsor_username_six);
        p_sponsor_username_seven=findViewById(R.id.p_sponsor_username_seven);


        p_sponsor_name_one=findViewById(R.id.p_sponsor_name_one);
        p_sponsor_name_two=findViewById(R.id.p_sponsor_name_two);
        p_sponsor_name_three=findViewById(R.id.p_sponsor_name_three);
        p_sponsor_name_four=findViewById(R.id.p_sponsor_name_four);
        p_sponsor_name_five=findViewById(R.id.p_sponsor_name_five);
        p_sponsor_name_six=findViewById(R.id.p_sponsor_name_six);
        p_sponsor_name_seven=findViewById(R.id.p_sponsor_name_seven);


        p_leftBV_one=findViewById(R.id.p_leftBV_one);
        p_leftBV_two=findViewById(R.id.p_leftBV_two);
        p_leftBV_three=findViewById(R.id.p_leftBV_three);
        p_leftBV_four=findViewById(R.id.p_leftBV_four);
        p_leftBV_five=findViewById(R.id.p_leftBV_five);
        p_leftBV_six=findViewById(R.id.p_leftBV_six);
        p_leftBV_seven=findViewById(R.id.p_leftBV_seven);

        p_rightBV_one=findViewById(R.id.p_rightBV_one);
        p_rightBV_two=findViewById(R.id.p_rightBV_two);
        p_rightBV_three=findViewById(R.id.p_rightBV_three);
        p_rightBV_four=findViewById(R.id.p_rightBV_four);
        p_rightBV_five=findViewById(R.id.p_rightBV_five);
        p_rightBV_six=findViewById(R.id.p_rightBV_six);
        p_rightBV_seven=findViewById(R.id.p_rightBV_seven);


        p_leftcount_one=findViewById(R.id.p_leftcount_one);
        p_leftcount_two=findViewById(R.id.p_leftcount_two);
        p_leftcount_three=findViewById(R.id.p_leftcount_three);
        p_leftcount_four=findViewById(R.id.p_leftcount_four);
        p_leftcount_five=findViewById(R.id.p_leftcount_five);
        p_leftcount_six=findViewById(R.id.p_leftcount_six);
        p_leftcount_seven=findViewById(R.id.p_leftcount_seven);


        p_rightcount_one=findViewById(R.id.p_rightcount_one);
        p_rightcount_two=findViewById(R.id.p_rightcount_two);
        p_rightcount_three=findViewById(R.id.p_rightcount_three);
        p_rightcount_four=findViewById(R.id.p_rightcount_four);
        p_rightcount_five=findViewById(R.id.p_rightcount_five);
        p_rightcount_six=findViewById(R.id.p_rightcount_six);
        p_rightcount_seven=findViewById(R.id.p_rightcount_seven);



        p_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);
            }
        });

        p_imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.VISIBLE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);
            }
        });




        p_imgtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.VISIBLE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);

            }
        });


        p_imgthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.VISIBLE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);

            }
        });

        p_imgfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.VISIBLE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);
            }
        });

        p_imgfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.VISIBLE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.GONE);

            }
        });

        p_imgsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.VISIBLE);
                p_llimgseven.setVisibility(View.GONE);
            }
        });


        p_imgseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_llimgone.setVisibility(View.GONE);
                p_llimgtwo.setVisibility(View.GONE);
                p_llimgthree.setVisibility(View.GONE);
                p_llimgfour.setVisibility(View.GONE);
                p_llimgfive.setVisibility(View.GONE);
                p_llimgsix.setVisibility(View.GONE);
                p_llimgseven.setVisibility(View.VISIBLE);
            }
        });




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
                                .into(p_imgthree);
                    }
                    else if (mactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgthree);
                    }
                    else if (mbactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgthree);
                    }
                    else if (bactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgthree);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgthree);
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
                                .into(p_imgfour);
                    }
                    else if (mactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(p_imgfour);
                    }
                    else if (mbactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(p_imgfour);
                    }
                    else if (bactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(p_imgfour);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(p_imgfour);
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

                    String p_username1 = listPremiumPlanGenealogy.get(0).getUserid();
                    Log.e("username",p_username1);
                    p_username_one.setText(p_username1);
                    String p_username2 = listPremiumPlanGenealogy.get(1).getUserid();
                    Log.e("u",p_username2);
                    p_username_two.setText(p_username2);
                    String p_username3 = listPremiumPlanGenealogy.get(2).getUserid();
                    Log.e("three",p_username3);
                    p_username_three.setText(p_username3);
                    String p_username4 = listPremiumPlanGenealogy.get(3).getUserid();
                    p_username_four.setText(p_username4);
                    String p_username5 = listPremiumPlanGenealogy.get(4).getUserid();
                    p_username_five.setText(p_username5);
                    String p_username6 = listPremiumPlanGenealogy.get(5).getUserid();
                    p_username_six.setText(p_username6);
                    String p_username7 = listPremiumPlanGenealogy.get(6).getUserid();
                    p_username_seven.setText(p_username7);


                    String p_name1=listPremiumPlanGenealogy.get(0).getName();
                    p_name_one.setText(p_name1);
                    String p_name2=listPremiumPlanGenealogy.get(1).getName();
                    p_name_two.setText(p_name2);
                    String p_name3=listPremiumPlanGenealogy.get(2).getName();
                    p_name_three.setText(p_name3);
                    String p_name4=listPremiumPlanGenealogy.get(3).getName();
                    p_name_four.setText(p_name4);
                    String p_name5=listPremiumPlanGenealogy.get(4).getName();
                    p_name_five.setText(p_name5);
                    String p_name6=listPremiumPlanGenealogy.get(5).getName();
                    p_name_six.setText(p_name6);
                    String p_name7=listPremiumPlanGenealogy.get(6).getName();
                    p_name_seven.setText(p_name7);


                    String p_sponsor_username1=listPremiumPlanGenealogy.get(0).getSponsorId();
                    p_sponsor_username_one.setText(p_sponsor_username1);
                    String p_sponsor_username2=listPremiumPlanGenealogy.get(1).getSponsorId();
                    p_sponsor_username_two.setText(p_sponsor_username2);
                    String p_sponsor_username3=listPremiumPlanGenealogy.get(2).getSponsorId();
                    p_sponsor_username_three.setText(p_sponsor_username3);
                    String p_sponsor_username4=listPremiumPlanGenealogy.get(3).getSponsorId();
                    p_sponsor_username_four.setText(p_sponsor_username4);
                    String p_sponsor_username5=listPremiumPlanGenealogy.get(4).getSponsorId();
                    p_sponsor_username_five.setText(p_sponsor_username5);
                    String p_sponsor_username6=listPremiumPlanGenealogy.get(5).getSponsorId();
                    p_sponsor_username_six.setText(p_sponsor_username6);
                    String p_sponsor_username7=listPremiumPlanGenealogy.get(6).getSponsorId();
                    p_sponsor_username_seven.setText(p_sponsor_username7);


                    String p_sponsor_name1=listPremiumPlanGenealogy.get(0).getSponsorName();
                    p_sponsor_name_one.setText(p_sponsor_name1);
                    String p_sponsor_name2=listPremiumPlanGenealogy.get(1).getSponsorName();
                    p_sponsor_name_two.setText(p_sponsor_name2);
                    String p_sponsor_name3=listPremiumPlanGenealogy.get(2).getSponsorName();
                    p_sponsor_name_three.setText(p_sponsor_name3);
                    String p_sponsor_name4=listPremiumPlanGenealogy.get(3).getSponsorName();
                    p_name_four.setText(p_sponsor_name4);
                    String p_sponsor_name5=listPremiumPlanGenealogy.get(4).getSponsorName();
                    p_name_five.setText(p_sponsor_name5);
                    String p_sponsor_name6=listPremiumPlanGenealogy.get(5).getSponsorName();
                    p_name_six.setText(p_sponsor_name6);
                    String p_sponsor_name7=listPremiumPlanGenealogy.get(6).getSponsorName();
                    p_name_seven.setText(p_sponsor_name7);


                    String p_leftBV1=listPremiumPlanGenealogy.get(0).getLeftPv();
                    p_leftBV_one.setText(p_leftBV1);
                    String p_leftBV2=listPremiumPlanGenealogy.get(1).getLeftPv();
                    p_leftBV_two.setText(p_leftBV2);
                    String p_leftBV3=listPremiumPlanGenealogy.get(2).getLeftPv();
                    p_leftBV_three.setText(p_leftBV3);
                    String p_leftBV4=listPremiumPlanGenealogy.get(3).getLeftPv();
                    p_leftBV_four.setText(p_leftBV4);
                    String p_leftBV5=listPremiumPlanGenealogy.get(4).getLeftPv();
                    p_leftBV_five.setText(p_leftBV5);
                    String p_leftBV6=listPremiumPlanGenealogy.get(5).getLeftPv();
                    p_leftBV_six.setText(p_leftBV6);
                    String p_leftBV7=listPremiumPlanGenealogy.get(6).getLeftPv();
                    p_leftBV_seven.setText(p_leftBV7);


                    String p_rightBV1=listPremiumPlanGenealogy.get(0).getRightPv();
                    p_rightBV_one.setText(p_rightBV1);
                    String p_rightBV2=listPremiumPlanGenealogy.get(1).getRightPv();
                    p_rightBV_two.setText(p_rightBV2);
                    String p_rightBV3=listPremiumPlanGenealogy.get(2).getRightPv();
                    p_rightBV_three.setText(p_rightBV3);
                    String p_rightBV4=listPremiumPlanGenealogy.get(3).getRightPv();
                    p_rightBV_four.setText(p_rightBV4);
                    String p_rightBV5=listPremiumPlanGenealogy.get(4).getRightPv();
                    p_rightBV_five.setText(p_rightBV5);
                    String p_rightBV6=listPremiumPlanGenealogy.get(5).getRightPv();
                    p_rightBV_six.setText(p_rightBV6);
                    String p_rightBV7=listPremiumPlanGenealogy.get(6).getRightPv();
                    p_rightBV_seven.setText(p_rightBV7);





                    String p_leftcount1 =listPremiumPlanGenealogy.get(0).getLeftCount();
                    p_leftcount_one.setText(p_leftcount1);
                    String p_leftcount2=listPremiumPlanGenealogy.get(1).getLeftCount();
                    p_leftcount_two.setText(p_leftcount2);
                    String p_leftcount3=listPremiumPlanGenealogy.get(2).getLeftCount();
                    p_leftcount_three.setText(p_leftcount3);
                    String p_leftcount4=listPremiumPlanGenealogy.get(3).getLeftCount();
                    p_leftcount_four.setText(p_leftcount4);
                    String p_leftcount5=listPremiumPlanGenealogy.get(4).getLeftCount();
                    p_leftcount_five.setText(p_leftcount5);
                    String p_leftcount6=listPremiumPlanGenealogy.get(5).getLeftCount();
                    p_leftcount_six.setText(p_leftcount6);
                    String p_leftcount7=listPremiumPlanGenealogy.get(6).getLeftCount();
                    p_leftcount_seven.setText(p_leftcount7);


                    String p_rightcount1 =listPremiumPlanGenealogy.get(0).getRightCount();
                    p_rightcount_one.setText(p_rightcount1);
                    String p_rightcount2=listPremiumPlanGenealogy.get(1).getRightCount();
                    p_rightcount_two.setText(p_rightcount2);
                    String p_rightcount3=listPremiumPlanGenealogy.get(2).getRightCount();
                    p_rightcount_three.setText(p_rightcount3);
                    String p_rightcount4=listPremiumPlanGenealogy.get(3).getRightCount();
                    p_rightcount_four.setText(p_rightcount4);
                    String p_rightcount5=listPremiumPlanGenealogy.get(4).getRightCount();
                    p_rightcount_five.setText(p_rightcount5);
                    String p_rightcount6=listPremiumPlanGenealogy.get(5).getRightCount();
                    p_rightcount_six.setText(p_rightcount6);
                    String p_rightcount7=listPremiumPlanGenealogy.get(6).getRightCount();
                    p_rightcount_seven.setText(p_rightcount7);




                }
            }

            @Override
            public void onFailure(Call<ResponsePremiumGenealogy> call, Throwable t) {

            }
        });

    }
    private void InfoBottomSheet(){

        PremiumGeneBottomDailogue bottomSheet = new PremiumGeneBottomDailogue();
        bottomSheet.show(getSupportFragmentManager(),
                "ModalBottomSheet");


        //val moreOrderDetails = OrderDetailSheet(applicationContext, orderID,login_id)
        //        moreOrderDetails.show(getSupportFragmentManager(), moreOrderDetails.getTag())
    }
}