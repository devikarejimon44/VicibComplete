package com.gipra.vicibcomplete.MembersArea.Gene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
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
    TextView st_username_two,st_name_two,st_sponsor_username_two,st_sponsor_name_two,st_leftBV_two,st_rightBV_two,st_leftcount_two,st_rightcount_two;
    TextView st_username_three,st_name_three,st_sponsor_username_three,st_sponsor_name_three,st_leftBV_three,st_rightBV_three,st_leftcount_three,st_rightcount_three;
    TextView st_username_four,st_name_four,st_sponsor_username_four,st_sponsor_name_four,st_leftBV_four,st_rightBV_four,st_leftcount_four,st_rightcount_four;
    TextView st_username_five,st_name_five,st_sponsor_username_five,st_sponsor_name_five,st_leftBV_five,st_rightBV_five,st_leftcount_five,st_rightcount_five ;
    TextView st_username_six,st_name_six,st_sponsor_username_six,sponsor_name_six,st_leftBV_six,st_rightBV_six,st_leftcount_six,st_rightcount_six;
    TextView st_username_seven,st_name_seven,st_sponsor_username_seven,st_sponsor_name_seven,st_leftBV_seven,st_rightBV_seven,st_leftcount_seven,st_rightcount_seven;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    String t0,t1,t2,t3,t4,t5,t6;
    SwipeRefreshLayout standard_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_plan_gene);
        st_layout=findViewById(R.id.st_layout);

        Toolbar toolbar=findViewById(R.id.standard_geneToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

              st_info = findViewById(R.id.st_info);
          st_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheet();

//                LayoutInflater layoutInflater = (LayoutInflater) StandardPlanGene.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View premium_popup = layoutInflater.inflate(R.layout.standard_genepopup, null);
//
//                popupWindow = new PopupWindow(premium_popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.showAtLocation(st_layout, Gravity.CENTER, 0, 0);
//                popupWindow.setFocusable(true);
//                popupWindow.update();
            }
        });
        standard_refresh=findViewById(R.id.standard_refresh);
        standard_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(new Intent(getApplicationContext(),StandardPlanGene.class));
                standard_refresh.setRefreshing(false);
            }
        });


        st_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);
            }
        });
        st_imgone=findViewById(R.id.st_imgone);
        st_imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.VISIBLE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);
            }
        });



        st_imgtwo=findViewById(R.id.st_imgtwo);
        st_imgtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.VISIBLE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);

            }
        });



        st_imgthree=findViewById(R.id.st_imgthree);
        st_imgthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.VISIBLE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);

            }
        });


        st_imgfour=findViewById(R.id.st_imgfour);
        st_imgfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.VISIBLE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);
            }
        });

        st_imgfive=findViewById(R.id.st_imgfive);
        st_imgfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.VISIBLE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.GONE);

            }
        });



        st_imgsix=findViewById(R.id.st_imgsix);
        st_imgsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.VISIBLE);
                st_llimgseven.setVisibility(View.GONE);
            }
        });

        st_imgseven=findViewById(R.id.st_imgseven);
        st_imgseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_llimgone.setVisibility(View.GONE);
                st_llimgtwo.setVisibility(View.GONE);
                st_llimgthree.setVisibility(View.GONE);
                st_llimgfour.setVisibility(View.GONE);
                st_llimgfive.setVisibility(View.GONE);
                st_llimgsix.setVisibility(View.GONE);
                st_llimgseven.setVisibility(View.VISIBLE);
            }
        });



        st_txtone=findViewById(R.id.st_txtone);
        st_txtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid0);

//
//                GeneBottomDailogue bottomSheet = new GeneBottomDailogue();
//                bottomSheet.show(getSupportFragmentManager(),
//                        "ModalBottomSheet");
            }
        });
        st_txttwo=findViewById(R.id.st_txttwo);
        st_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid1);

//
            }
        });
        st_txtthree=findViewById(R.id.st_txtthree);
        st_txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid2);


            }
        });
        st_txtfour=findViewById(R.id.st_txtfour);
        st_txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid3);


            }
        });
        st_txtfive=findViewById(R.id.st_txtfive);
        st_txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid4);


            }
        });
        st_txtsix=findViewById(R.id.st_txtsix);

        st_txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid5);


            }
        });
        st_txtseven=findViewById(R.id.st_txtseven);
        st_txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid6);


            }
        });
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
        st_rightBV_two=findViewById(R.id.st_rightBV_two);
        st_leftcount_two=findViewById(R.id.st_leftcount_two);
        st_rightcount_two=findViewById(R.id.st_rightcount_two);

        st_llimgthree=findViewById(R.id.st_llimgthree);
        st_username_three=findViewById(R.id.st_username_three);
        st_name_three=findViewById(R.id.st_name_three);
        st_sponsor_username_three=findViewById(R.id.st_sponsor_username_three);
        st_sponsor_name_three=findViewById(R.id.st_sponsor_name_three);
        st_leftBV_three=findViewById(R.id.st_leftBV_three);
        st_rightBV_three=findViewById(R.id.st_rightBV_three);
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
        st_sponsor_username_seven=findViewById(R.id.st_sponsor_username_seven);
        st_sponsor_name_seven=findViewById(R.id.st_sponsor_name_seven);
        st_leftBV_seven=findViewById(R.id.st_leftBV_seven);
        st_rightBV_seven=findViewById(R.id.st_rightBV_seven);
        st_leftcount_seven=findViewById(R.id.st_leftcount_seven);
        st_rightcount_seven=findViewById(R.id.st_rightcount_seven);



        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
         final  String u=shpref.getString("ID","");
        gene(u);
        st_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid1.equals("0")){


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
    private  void gene(final String id){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
    //    Call<ResponseStandardGenealogy>call=api.StandardGene(Integer.parseInt(id));
  //  Call<ResponseStandardGenealogy>call=api.StandardGene(10739);
        Call<ResponseStandardGenealogy>call=api.StandardGene(1);

        call.enqueue(new Callback<ResponseStandardGenealogy>() {
            @Override
            public void onResponse(Call<ResponseStandardGenealogy> call, Response<ResponseStandardGenealogy> response) {
                if (response.body().getStatus().equals("1")){
                    final ResponseStandardGenealogy responseStandardGenealogy = response.body();
                    listStandardPlanGenealogy = (ArrayList<ListStandardPlanGenealogy>)responseStandardGenealogy.getData();
                    String userid=listStandardPlanGenealogy.get(0).getUserid();
                    String uname=listStandardPlanGenealogy.get(0).getName();
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("USERNAME",uname);
                    editor.putString("UID",userid);

//                   postion 0

                    uid0=listStandardPlanGenealogy.get(0).getUserid();
                    String mactive0=listStandardPlanGenealogy.get(0).getMemberActive();
                    String mbactive0=listStandardPlanGenealogy.get(0).getMemberbronzeActive();
                    String bactive0=listStandardPlanGenealogy.get(0).getBasicActive();
                    t0=listStandardPlanGenealogy.get(0).getName();

                    String spid1=listStandardPlanGenealogy.get(0).getSponsorId();
                    if (spid1==null){
                        st_txtone.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgone);
                    }


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


                    String spid2=listStandardPlanGenealogy.get(1).getSponsorId();
                    if (spid2==null){
                        st_txttwo.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgtwo);
                    }




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


                    String spid3=listStandardPlanGenealogy.get(2).getSponsorId();
                    if (spid3==null){
                        st_txtthree.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgthree);
                    }


                    st_txtthree.setText(t2);
                    if (uid2.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgthree);
                    }
                    else if (mactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgthree);
                    }
                    else if (mbactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgthree);
                    }
                    else if (bactive2.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgthree);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgthree);
                    }


//                   postion 3

                    uid3=listStandardPlanGenealogy.get(3).getUserid();
                    String mactive3=listStandardPlanGenealogy.get(3).getMemberActive();
                    String mbactive3=listStandardPlanGenealogy.get(3).getMemberbronzeActive();
                    String bactive3=listStandardPlanGenealogy.get(3).getBasicActive();
                    t3=listStandardPlanGenealogy.get(3).getName();
                    st_txtfour.setText(t3);


                    String spid4=listStandardPlanGenealogy.get(3).getSponsorId();
                    if (spid4==null){
                        st_txtfour.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfour);
                    }

                    if (uid3.equals("0")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfour);
                    }
                    else if (mactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.active)
                                .into(st_imgfour);
                    }
                    else if (mbactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgfour);
                    }
                    else if (bactive3.equals("Y")){
                        Glide.with(getApplicationContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgfour);
                    }
                    else {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.notactive)
                                .into(st_imgfour);
                    }

//                   postion 4

                    uid4=listStandardPlanGenealogy.get(4).getUserid();
                    String mactive4=listStandardPlanGenealogy.get(4).getMemberActive();
                    String mbactive4=listStandardPlanGenealogy.get(4).getMemberbronzeActive();
                    String bactive4=listStandardPlanGenealogy.get(4).getBasicActive();
                    t4=listStandardPlanGenealogy.get(4).getName();
                    st_txtfive.setText(t4);

                    String spid5=listStandardPlanGenealogy.get(4).getSponsorId();
                    if (spid5==null){
                        st_txtfive.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfive);
                    }


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


                    String spid6=listStandardPlanGenealogy.get(5).getSponsorId();
                    if (spid6==null){
                        st_txtsix.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgsix);
                    }



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


                    String spid7=listStandardPlanGenealogy.get(6).getSponsorId();
                    if (spid7==null){
                        st_txtseven.setText("Vaccant");
                        Glide.with(getApplicationContext())
                                .load(R.drawable.vacant)
                                .into(st_imgseven);
                    }



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

                    String st_username1 = listStandardPlanGenealogy.get(0).getUserid();
                    Log.e("username",st_username1);
                    st_username_one.setText(st_username1);
                    String st_username2 = listStandardPlanGenealogy.get(1).getUserid();
                    Log.e("u",st_username1);
                    st_username_two.setText(st_username2);
                    String st_username3 = listStandardPlanGenealogy.get(2).getUserid();
                    Log.e("three",st_username3);
                    st_username_three.setText(st_username3);
                    String st_username4 = listStandardPlanGenealogy.get(3).getUserid();
                    st_username_four.setText(st_username4);
                    String st_username5 = listStandardPlanGenealogy.get(4).getUserid();
                    st_username_five.setText(st_username5);
                    String st_username6 = listStandardPlanGenealogy.get(5).getUserid();
                    st_username_six.setText(st_username6);
                    String st_username7 = listStandardPlanGenealogy.get(6).getUserid();
                    st_username_seven.setText(st_username7);


                    String st_name1=listStandardPlanGenealogy.get(0).getName();
                    st_name_one.setText(st_name1);
                    String st_name2=listStandardPlanGenealogy.get(1).getName();
                    st_name_two.setText(st_name2);
                    String st_name3=listStandardPlanGenealogy.get(2).getName();
                    st_name_three.setText(st_name3);
                    String st_name4=listStandardPlanGenealogy.get(3).getName();
                    st_name_four.setText(st_name4);
                    String st_name5=listStandardPlanGenealogy.get(4).getName();
                    st_name_five.setText(st_name5);
                    String st_name6=listStandardPlanGenealogy.get(5).getName();
                    st_name_six.setText(st_name6);
                    String st_name7=listStandardPlanGenealogy.get(6).getName();
                    st_name_seven.setText(st_name7);


                    String st_sponsor_username1=listStandardPlanGenealogy.get(0).getSponsorId();
                    st_sponsor_username_one.setText(st_sponsor_username1);
                    String st_sponsor_username2=listStandardPlanGenealogy.get(1).getSponsorId();
                    st_sponsor_username_two.setText(st_sponsor_username2);
                    String st_sponsor_username3=listStandardPlanGenealogy.get(2).getSponsorId();
                    st_sponsor_username_three.setText(st_sponsor_username3);
                    String st_sponsor_username4=listStandardPlanGenealogy.get(3).getSponsorId();
                    st_sponsor_username_four.setText(st_sponsor_username4);
                    String st_sponsor_username5=listStandardPlanGenealogy.get(4).getSponsorId();
                    st_sponsor_username_five.setText(st_sponsor_username5);
                    String st_sponsor_username6=listStandardPlanGenealogy.get(5).getSponsorId();
                    st_sponsor_username_six.setText(st_sponsor_username6);
                    String st_sponsor_username7=listStandardPlanGenealogy.get(6).getSponsorId();
                    st_sponsor_username_seven.setText(st_sponsor_username7);


                    String st_sponsor_name1=listStandardPlanGenealogy.get(0).getSponsorName();
                    st_sponsor_name_one.setText(st_sponsor_name1);
                    String st_sponsor_name2=listStandardPlanGenealogy.get(1).getSponsorName();
                    st_sponsor_name_two.setText(st_sponsor_name2);
                    String st_sponsor_name3=listStandardPlanGenealogy.get(2).getSponsorName();
                    st_sponsor_name_three.setText(st_sponsor_name3);
                    String st_sponsor_name4=listStandardPlanGenealogy.get(3).getSponsorName();
                    st_name_four.setText(st_sponsor_name4);
                    String st_sponsor_name5=listStandardPlanGenealogy.get(4).getSponsorName();
                    st_name_five.setText(st_sponsor_name5);
                    String st_sponsor_name6=listStandardPlanGenealogy.get(5).getSponsorName();
                    st_name_six.setText(st_sponsor_name6);
                    String st_sponsor_name7=listStandardPlanGenealogy.get(6).getSponsorName();
                    st_name_seven.setText(st_sponsor_name7);


                    String st_leftBV1=listStandardPlanGenealogy.get(0).getLeftPv();
                    st_leftBV_one.setText(st_leftBV1);
                    String st_leftBV2=listStandardPlanGenealogy.get(1).getLeftPv();
                    st_leftBV_two.setText(st_leftBV2);
                    String st_leftBV3=listStandardPlanGenealogy.get(2).getLeftPv();
                    st_leftBV_three.setText(st_leftBV3);
                    String st_leftBV4=listStandardPlanGenealogy.get(3).getLeftPv();
                    st_leftBV_four.setText(st_leftBV4);
                    String st_leftBV5=listStandardPlanGenealogy.get(4).getLeftPv();
                    st_leftBV_five.setText(st_leftBV5);
                    String st_leftBV6=listStandardPlanGenealogy.get(5).getLeftPv();
                    st_leftBV_six.setText(st_leftBV6);
                    String st_leftBV7=listStandardPlanGenealogy.get(6).getLeftPv();
                    st_leftBV_seven.setText(st_leftBV7);


                    String st_rightBV1=listStandardPlanGenealogy.get(0).getRightPv();
                    st_rightBV_one.setText(st_rightBV1);
                    String st_rightBV2=listStandardPlanGenealogy.get(1).getRightPv();
                    st_rightBV_two.setText(st_rightBV2);
                    String st_rightBV3=listStandardPlanGenealogy.get(2).getRightPv();
                    st_rightBV_three.setText(st_rightBV3);
                    String st_rightBV4=listStandardPlanGenealogy.get(3).getRightPv();
                    st_rightBV_four.setText(st_rightBV4);
                    String st_rightBV5=listStandardPlanGenealogy.get(4).getRightPv();
                    st_rightBV_five.setText(st_rightBV5);
                    String st_rightBV6=listStandardPlanGenealogy.get(5).getRightPv();
                    st_rightBV_six.setText(st_rightBV6);
                    String st_rightBV7=listStandardPlanGenealogy.get(6).getRightPv();
                    st_rightBV_seven.setText(st_rightBV7);





                    String st_leftcount1 =listStandardPlanGenealogy.get(0).getLeftCount();
                    st_leftcount_one.setText(st_leftcount1);
                    String st_leftcount2=listStandardPlanGenealogy.get(1).getLeftCount();
                    st_leftcount_two.setText(st_leftcount2);
                    String st_leftcount3=listStandardPlanGenealogy.get(2).getLeftCount();
                    st_leftcount_three.setText(st_leftcount3);
                    String st_leftcount4=listStandardPlanGenealogy.get(3).getLeftCount();
                    st_leftcount_four.setText(st_leftcount4);
                    String st_leftcount5=listStandardPlanGenealogy.get(4).getLeftCount();
                    st_leftcount_five.setText(st_leftcount5);
                    String st_leftcount6=listStandardPlanGenealogy.get(5).getLeftCount();
                    st_leftcount_six.setText(st_leftcount6);
                    String st_leftcount7=listStandardPlanGenealogy.get(6).getLeftCount();
                    st_leftcount_seven.setText(st_leftcount7);


                    String st_rightcount1 =listStandardPlanGenealogy.get(0).getRightCount();
                    st_rightcount_one.setText(st_rightcount1);
                    String st_rightcount2=listStandardPlanGenealogy.get(1).getRightCount();
                    st_rightcount_two.setText(st_rightcount2);
                    String st_rightcount3=listStandardPlanGenealogy.get(2).getRightCount();
                    st_rightcount_three.setText(st_rightcount3);
                    String st_rightcount4=listStandardPlanGenealogy.get(3).getRightCount();
                    st_rightcount_four.setText(st_rightcount4);
                    String st_rightcount5=listStandardPlanGenealogy.get(4).getRightCount();
                    st_rightcount_five.setText(st_rightcount5);
                    String st_rightcount6=listStandardPlanGenealogy.get(5).getRightCount();
                    st_rightcount_six.setText(st_rightcount6);
                    String st_rightcount7=listStandardPlanGenealogy.get(6).getRightCount();
                    st_rightcount_seven.setText(st_rightcount7);

                }
            }

            @Override
            public void onFailure(Call<ResponseStandardGenealogy> call, Throwable t) {

            }
        });

    }
    private void BottomSheet(){

        StandardGeneBottomDailogue bottomSheet = new StandardGeneBottomDailogue();
        bottomSheet.show(getSupportFragmentManager(),
                "ModalBottomSheet");

        //val moreOrderDetails = OrderDetailSheet(applicationContext, orderID,login_id)
        //        moreOrderDetails.show(getSupportFragmentManager(), moreOrderDetails.getTag())
    }

    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


}