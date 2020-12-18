package com.gipra.vicibcomplete.MembersArea.Gene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.Registration;
import com.gipra.vicibcomplete.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandardPlanGene extends AppCompatActivity {
    PopupWindow popupWindow;
    LinearLayout st_layout,st_llimgone,st_llimgtwo,st_llimgthree,st_llimgfour,st_llimgfive,st_llimgsix,st_llimgseven;
    ImageView st_info;
    private ArrayList<ListStandardPlanGenealogy> listStandardPlanGenealogy;
    ImageView st_imgone,st_imgtwo,st_imgthree,st_imgfour,st_imgfive,st_imgsix,st_imgseven;
    TextView st_txtone,st_txttwo,st_txtthree,st_txtfour,st_txtfive,st_txtsix,st_txtseven;
    TextView st_username_one,st_name_one,st_sponsor_username_one,st_sponsor_name_one,st_leftBV_one,st_rightBV_one,st_leftcount_one,st_rightcount_one;
    TextView st_username_two,st_name_two,st_sponsor_username_two,st_sponsor_name_two,st_leftBV_two,st_rightGBV_two,st_leftcount_two,st_rightcount_two;
    TextView st_username_three,st_name_three,st_sponsor_username_three,st_sponsor_name_three,st_leftGBV_three,st_rightGBV_three,st_leftcount_three,st_rightcount_three;
    TextView st_username_four,st_name_four,st_sponsor_username_four,st_sponsor_name_four,st_leftBV_four,st_rightBV_four,st_leftcount_four,st_rightcount_four;
    TextView st_username_five,st_name_five,st_sponsor_username_five,st_sponsor_name_five,st_leftBV_five,st_rightBV_five,st_leftcount_five,st_rightcount_five ;
    TextView st_username_six,st_name_six,st_sponsor_username_six,sponsor_name_six,st_leftBV_six,st_rightBV_six,st_leftcount_six,st_rightcount_six;
    TextView st_username_seven,st_name_seven,sponsor_username_seven,st_sponsor_name_seven,st_leftBV_seven,st_rightBV_seven,st_leftcount_seven,st_rightcount_seven;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    String t0,t1,t2,t3,t4,t5,t6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_plan_gene);
        st_layout=findViewById(R.id.st_layout);

              st_info = findViewById(R.id.st_info);
          st_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) StandardPlanGene.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View premium_popup = layoutInflater.inflate(R.layout.standard_genepopup, null);

                popupWindow = new PopupWindow(premium_popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(st_layout, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
            }
        });
        st_imgone=findViewById(R.id.st_imgone);
        st_imgtwo=findViewById(R.id.st_imgtwo);
        st_imgthree=findViewById(R.id.st_imgthree);
        st_imgfour=findViewById(R.id.st_imgfour);
        st_imgfive=findViewById(R.id.st_imgfive);
        st_imgsix=findViewById(R.id.st_imgsix);
        st_imgseven=findViewById(R.id.st_imgseven);

        st_txtone=findViewById(R.id.st_txtone);
        st_txttwo=findViewById(R.id.st_txttwo);
        st_txtthree=findViewById(R.id.st_txtthree);
        st_txtfour=findViewById(R.id.st_txtfour);
        st_txtfive=findViewById(R.id.st_txtfive);
        st_txtsix=findViewById(R.id.st_txtsix);
        st_txtseven=findViewById(R.id.st_txtseven);

        st_llimgone=findViewById(R.id.st_llimgone);
        st_username_one=findViewById(R.id.st_username_one);
        st_name_one=findViewById(R.id.st_name_one);
        st_sponsor_username_one=findViewById(R.id.st_sponsor_username_one);
        st_sponsor_name_one=findViewById(R.id.st_sponsor_name_one);
        st_leftBV_one=findViewById(R.id.st_leftBV_one);
        st_rightBV_one=findViewById(R.id.st_rightBV_one);
        st_leftcount_one=findViewById(R.id.st_leftcount_one);
        st_rightcount_one=findViewById(R.id.st_rightcount_one);

        st_llimgtwo=findViewById(R.id.st_llimgtwo);
        st_username_two=findViewById(R.id.st_username_two);
        st_name_two=findViewById(R.id.st_name_two);
        st_sponsor_username_two=findViewById(R.id.st_sponsor_username_two);
        st_sponsor_name_two=findViewById(R.id.st_sponsor_name_two);
        st_leftBV_two=findViewById(R.id.st_leftBV_two);
        st_rightGBV_two=findViewById(R.id.st_rightGBV_two);
        st_leftcount_two=findViewById(R.id.st_leftcount_two);
        st_rightcount_two=findViewById(R.id.st_rightcount_two);

        st_llimgthree=findViewById(R.id.st_llimgthree);
        st_username_three=findViewById(R.id.st_username_three);
        st_name_three=findViewById(R.id.st_name_three);
        st_sponsor_username_three=findViewById(R.id.st_sponsor_username_three);
        st_sponsor_name_three=findViewById(R.id.st_sponsor_name_three);
        st_leftGBV_three=findViewById(R.id.st_leftGBV_three);
        st_rightGBV_three=findViewById(R.id.st_rightGBV_three);
        st_leftcount_three=findViewById(R.id.st_leftcount_three);
        st_rightcount_three=findViewById(R.id.st_rightcount_three);

        st_llimgfour=findViewById(R.id.st_llimgfour);
        st_username_four=findViewById(R.id.st_username_four);
        st_name_four=findViewById(R.id.st_name_four);
        st_sponsor_username_four=findViewById(R.id.st_sponsor_username_four);
        st_sponsor_name_four=findViewById(R.id.st_sponsor_name_four);
        st_leftBV_four=findViewById(R.id.st_leftBV_four);
        st_rightBV_four=findViewById(R.id.st_rightBV_four);
        st_leftcount_four=findViewById(R.id.st_leftcount_four);
        st_rightcount_four=findViewById(R.id.st_rightcount_four);

        st_llimgfive=findViewById(R.id.st_llimgfive);
        st_username_five=findViewById(R.id.st_username_five);
        st_name_five=findViewById(R.id.st_name_five);
        st_sponsor_username_five=findViewById(R.id.st_sponsor_username_five);
        st_sponsor_name_five=findViewById(R.id.st_sponsor_name_five);
        st_leftBV_five=findViewById(R.id.st_leftBV_five);
        st_rightBV_five=findViewById(R.id.st_rightBV_five);
        st_leftcount_five=findViewById(R.id.st_leftcount_five);
        st_rightcount_five=findViewById(R.id.st_rightcount_five);


        st_llimgsix=findViewById(R.id.st_llimgsix);
        st_username_six=findViewById(R.id.st_username_six);
        st_name_six=findViewById(R.id.st_name_six);
        st_sponsor_username_six=findViewById(R.id.st_sponsor_username_six);
        sponsor_name_six=findViewById(R.id.sponsor_name_six);
        st_leftBV_six=findViewById(R.id.st_leftBV_six);
        st_rightBV_six=findViewById(R.id.st_rightBV_six);
        st_leftcount_six=findViewById(R.id.st_leftcount_six);
        st_rightcount_six=findViewById(R.id.st_rightcount_six);

        st_llimgseven=findViewById(R.id.st_llimgseven);
        st_username_seven=findViewById(R.id.st_username_seven);
        st_name_seven=findViewById(R.id.st_name_seven);
        sponsor_username_seven=findViewById(R.id.sponsor_username_seven);
        st_sponsor_name_seven=findViewById(R.id.st_sponsor_name_seven);
        st_leftBV_seven=findViewById(R.id.st_leftBV_seven);
        st_rightBV_seven=findViewById(R.id.st_rightBV_seven);
        st_leftcount_seven=findViewById(R.id.st_leftcount_seven);
        st_rightcount_seven=findViewById(R.id.st_rightcount_seven);

        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        gene(u);
        st_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid1.equals("0")){

                    // Toast.makeText(getActivity(), "nulllllllllllll", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);

//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();



                }
                else{
                    gene(uid1);
                }


            }
        });
        st_txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid2.equals("0")){

                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }else {
                    gene(uid2);
                }


            }
        });
        st_txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid3.equals("0")){

                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }
                else {
                    gene(uid3);
                }

            }
        });
        st_txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid4.equals("0")){

                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }else
                {
                    gene(uid4);
                }

            }
        });
        st_txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid5.equals("0")){

                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }else {
                    gene(uid5);
                }

            }
        });
        st_txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid6.equals("0")){

                    Intent i=new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }else {
                    gene(uid6);
                }

            }
        });

    }
    private  void gene(String id){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStandardGenealogy>call=api.StandardGene(Integer.parseInt(id));
        call.enqueue(new Callback<ResponseStandardGenealogy>() {
            @Override
            public void onResponse(Call<ResponseStandardGenealogy> call, Response<ResponseStandardGenealogy> response) {
                if (response.body().getStatus().equals("1")){
                    final ResponseStandardGenealogy responseStandardGenealogy = response.body();
                    listStandardPlanGenealogy = (ArrayList<ListStandardPlanGenealogy>)responseStandardGenealogy.getData();

//                   postion 0

                    uid0=listStandardPlanGenealogy.get(0).getUserid();
                    String mactive0=listStandardPlanGenealogy.get(0).getMemberActive();
                    String mbactive0=listStandardPlanGenealogy.get(0).getMemberbronzeActive();
                    String bactive0=listStandardPlanGenealogy.get(0).getBasicActive();
                    t0=listStandardPlanGenealogy.get(0).getName();



                    st_txtone.setText(t0);
                    if (uid0.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgone);
                    }
                    else if (mactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgone);
                    }
                    else if (mbactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgone);
                    }
                    else if (bactive0.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgone);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgone);
                    }



//                   postion 1

                    uid1=listStandardPlanGenealogy.get(1).getUserid();
                    String mactive1=listStandardPlanGenealogy.get(1).getMemberActive();
                    String mbactive1=listStandardPlanGenealogy.get(1).getMemberbronzeActive();
                    String bactive1=listStandardPlanGenealogy.get(1).getBasicActive();
                    t1=listStandardPlanGenealogy.get(1).getName();


                    st_txttwo.setText(t1);
                    if (uid1.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgtwo);
                    }
                    else if (mactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgtwo);
                    }
                    else if (mbactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgtwo);
                    }
                    else if (bactive1.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgtwo);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgtwo);
                    }


//                   postion 2

                    uid2=listStandardPlanGenealogy.get(2).getUserid();
                    String mactive2=listStandardPlanGenealogy.get(2).getMemberActive();
                    String mbactive2=listStandardPlanGenealogy.get(2).getMemberbronzeActive();
                    String bactive2=listStandardPlanGenealogy.get(2).getBasicActive();
                    t2=listStandardPlanGenealogy.get(2).getName();

                    st_txtthree.setText(t2);
                    if (uid2.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgtwo);
                    }
                    else if (mactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgtwo);
                    }
                    else if (mbactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgtwo);
                    }
                    else if (bactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgtwo);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgtwo);
                    }


//                   postion 3

                    uid3=listStandardPlanGenealogy.get(3).getUserid();
                    String mactive3=listStandardPlanGenealogy.get(3).getMemberActive();
                    String mbactive3=listStandardPlanGenealogy.get(3).getMemberbronzeActive();
                    String bactive3=listStandardPlanGenealogy.get(3).getBasicActive();
                    t3=listStandardPlanGenealogy.get(3).getName();
                    st_txtfour.setText(t3);

                    if (uid3.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgthree);
                    }
                    else if (mactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgthree);
                    }
                    else if (mbactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgthree);
                    }
                    else if (bactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgthree);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgthree);
                    }

//                   postion 4

                    uid4=listStandardPlanGenealogy.get(4).getUserid();
                    String mactive4=listStandardPlanGenealogy.get(4).getMemberActive();
                    String mbactive4=listStandardPlanGenealogy.get(4).getMemberbronzeActive();
                    String bactive4=listStandardPlanGenealogy.get(4).getBasicActive();
                    t4=listStandardPlanGenealogy.get(4).getName();
                    st_txtfive.setText(t4);



                    if (uid4.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfive);
                    }
                    else if (mactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgfive);
                    }
                    else if (mbactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgfive);
                    }
                    else if (bactive4.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgfive);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgfive);
                    }


//                   postion 5

                    uid5=listStandardPlanGenealogy.get(5).getUserid();
                    String mactive5=listStandardPlanGenealogy.get(5).getMemberActive();
                    String mbactive5=listStandardPlanGenealogy.get(5).getMemberbronzeActive();
                    String bactive5=listStandardPlanGenealogy.get(5).getBasicActive();
                    t5=listStandardPlanGenealogy.get(5).getName();
                    st_txtsix.setText(t5);



                    if (uid5.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgsix);
                    }
                    else if (mactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgsix);
                    }
                    else if (mbactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgsix);
                    }
                    else if (bactive5.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgsix);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgsix);
                    }



//                   postion 6

                    uid6=listStandardPlanGenealogy.get(6).getUserid();
                    String mactive6=listStandardPlanGenealogy.get(6).getMemberActive();
                    String mbactive6=listStandardPlanGenealogy.get(6).getMemberbronzeActive();
                    String bactive6=listStandardPlanGenealogy.get(6).getBasicActive();
                    t6=listStandardPlanGenealogy.get(6).getName();
                    st_txtseven.setText(t6);


                    if (uid6.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgseven);
                    }
                    else if (mactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgseven);
                    }
                    else if (mbactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgseven);
                    }
                    else if (bactive6.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgseven);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgseven);
                    }










                }
            }

            @Override
            public void onFailure(Call<ResponseStandardGenealogy> call, Throwable t) {

            }
        });

    }

}