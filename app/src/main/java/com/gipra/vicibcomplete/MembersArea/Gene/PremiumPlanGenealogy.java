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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_plan_genealogy);
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


        p_imgone=findViewById(R.id.p_imgone);
        p_txtone=findViewById(R.id.p_txtone);
        p_imgtwo=findViewById(R.id.p_imgtwo);
        p_imgthree=findViewById(R.id.p_imgthree);
        p_txttwo=findViewById(R.id.p_txttwo);
        p_txtthree=findViewById(R.id.p_txtthree);
        p_imgfour=findViewById(R.id.p_imgfour);
        p_imgfive=findViewById(R.id.p_imgfive);
        p_imgsix=findViewById(R.id.p_imgsix);
        p_imgseven=findViewById(R.id.p_imgseven);
        p_txtfour=findViewById(R.id.p_txtfour);
        p_txtfive=findViewById(R.id.p_txtfive);
        p_txtsix=findViewById(R.id.p_txtsix);
        p_txtseven=findViewById(R.id.p_txtseven);


        p_llimgone=findViewById(R.id.p_llimgone);
        p_username_one=findViewById(R.id.p_username_one);
//        leftcount_two=findViewById(R.id.leftcount_two);
//        rightcount_two=findViewById(R.id.rightcount_two);
//        leftactive_two=findViewById(R.id.leftactive_members_two);
//        rightactive_two=findViewById(R.id.rightactive_members_two);
//
//        username_three=findViewById(R.id.username_three);
//        sponsor_userid_three=findViewById(R.id.sponsor_userid_three);
////        sponsor_username_three=view.findViewById(R.id.sponsor_username_three);
////        type_three=view.findViewById(R.id.type_three);
//        leftGBV_three=findViewById(R.id.leftGBV_three);
//        rightGBV_three=findViewById(R.id.rightGBV_three);
//        leftcount_three=findViewById(R.id.leftcount_three);
//        rightcount_three=findViewById(R.id.rightcount_three);
//        leftactive_three=findViewById(R.id.leftactive_members_three);
//        rightactive_three=findViewById(R.id.rightactive_members_three);
//
//        username_four=findViewById(R.id.username_four);
//        sponsor_userid_four=findViewById(R.id.sponsor_userid_four);
////        sponsor_username_four=view.findViewById(R.id.sponsor_username_four);
////        type_four=view.findViewById(R.id.type_four);
//        leftGBV_four=findViewById(R.id.leftGBV_four);
//        rightGBV_four=findViewById(R.id.rightGBV_four);
//        leftcount_four=findViewById(R.id.leftcount_four);
//        rightcount_four=findViewById(R.id.rightcount_four);
//        leftactive_four=findViewById(R.id.leftactive_members_four);
//        rightactive_four=findViewById(R.id.rightactive_members_four);
//
//        username_five=findViewById(R.id.username_five);
//        sponsor_userid_five=findViewById(R.id.sponsor_userid_five);
////        sponsor_username_five=view.findViewById(R.id.sponsor_username_five);
////        type_five=view.findViewById(R.id.type_five);
//        leftGBV_five=findViewById(R.id.leftGBV_five);
//        rightGBV_five=findViewById(R.id.rightGBV_five);
//        leftcount_five=findViewById(R.id.leftcount_five);
//        rightcount_five=findViewById(R.id.rightcount_five);
//        leftactive_five=findViewById(R.id.leftactive_members_five);
//        rightactive_five=findViewById(R.id.rightactive_members_five);
//
//        username_six=findViewById(R.id.username_six);
//        sponsor_userid_six=findViewById(R.id.sponsor_userid_six);
////        sponsor_username_six=view.findViewById(R.id.sponsor_username_six);
////        type_six=view.findViewById(R.id.txttypesix);
//        leftGBV_six=findViewById(R.id.leftGBV_six);
//        rightGBV_six=findViewById(R.id.rightGBV_six);
//        leftcount_six=findViewById(R.id.leftcount_six);
//        rightcount_six=findViewById(R.id.rightcount_six);
//        leftactive_six=findViewById(R.id.leftactive_members_six);
//        rightactive_six=findViewById(R.id.rightactive_members_six);
//
//        username_seven=findViewById(R.id.username_seven);
//        sponsor_userid_seven=findViewById(R.id.sponsor_userid_seven);
////        sponsor_username_seven=view.findViewById(R.id.sponsor_username_seven);
////        type_seven=view.findViewById(R.id.type_seven);
//        leftGBV_seven=findViewById(R.id.leftGBV_seven);
//        rightGBV_seven=findViewById(R.id.rightGBV_seven);
//        leftcount_seven=findViewById(R.id.leftcount_seven);
//        rightcount_seven=findViewById(R.id.rightcount_seven);
//        leftactive_seven=findViewById(R.id.leftactive_members_seven);
//        rightactive_seven=findViewById(R.id.rightactive_members_seven);
//
//
//        p_llmain=findViewById(R.id.p_llmain);
//        txtone=findViewById(R.id.txtone);
//        txttwo=findViewById(R.id.txttwo);
//        txtthree=findViewById(R.id.txtthree);
//        txtfour=findViewById(R.id.txtfour);
//        txtfive=findViewById(R.id.txtfive);
//        txtsix=findViewById(R.id.txtsix);
//        txtseven=findViewById(R.id.txtseven);
//
//        imgone=findViewById(R.id.imgone);
//        imgtwo=findViewById(R.id.imgtwo);
//        imgthree=findViewById(R.id.imgthree);
//        imgfour=findViewById(R.id.imgfour);
//        imgfive=findViewById(R.id.imgfive);
//        imgsix=findViewById(R.id.imgsix);
//        imgseven=findViewById(R.id.imgseven);
//        llone=findViewById(R.id.llimgone);
//        lltwo=findViewById(R.id.llimgtwo);
//        llthree=findViewById(R.id.llimgthree);
//        llfour=findViewById(R.id.llimgfour);
//        llfive=findViewById(R.id.llimgfive);
//        llsix=findViewById(R.id.llimgsix);
//        llseven=findViewById(R.id.llimgseven);
//        imgone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llone.setVisibility(View.VISIBLE );
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgtwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lltwo.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgthree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llthree.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgfour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llfour.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgfive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llfive.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgsix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llsix.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgseven.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llseven.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//
//            }
//        });
//        p_llmain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        SharedPreferences shpref;
//        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        gene(u);
//        txttwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid1==null){
//
//                    // Toast.makeText(getActivity(), "nulllllllllllll", Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//
////                    getActivity().getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
////                            .addToBackStack("")
////                            .commit();
//
//
//
//                }
//                else{
//                    gene(uid1);
//                }
//
//
//            }
//        });
//        txtthree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid2==null){
//
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//                }else {
//                    gene(uid2);
//                }
//
//
//            }
//        });
//        txtfour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid3==null){
//
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//                }
//                else {
//                    gene(uid3);
//                }
//
//            }
//        });
//        txtfive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid4==null){
//
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//                }else
//                {
//                    gene(uid4);
//                }
//
//            }
//        });
//        txtsix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid5==null){
//
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//                }else {
//                    gene(uid5);
//                }
//
//            }
//        });
//        txtseven.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (uid6==null){
//
//                    Intent i=new Intent(getApplicationContext(), Registration.class);
//                    startActivity(i);
//                }else {
//                    gene(uid6);
//                }
//
//            }
//        });
//
//    }
//    private  void gene(String id){
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        final Call<ResponsePremiumGenealogy>usercall=api.PremiumGene(Integer.parseInt(id));
//        usercall.enqueue(new Callback<ResponsePremiumGenealogy>() {
//            @Override
//            public void onResponse(Call<ResponsePremiumGenealogy> call, final Response<ResponsePremiumGenealogy> response) {
//                Log.i("onResponse", new Gson().toJson(response.body()));
//
//                final ResponsePremiumGenealogy responsePremiumGenealogy = response.body();
//                listPremiumPlanGenealogy = (ArrayList<ListPremiumPlanGenealogy>)responsePremiumGenealogy.getData();
//                uid0=listPremiumPlanGenealogy.get(0).getUserid();
//                String a0=listPremiumPlanGenealogy.get(0).getMemberActive();
//
//                if (uid0==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgone);
//
//                }else if(a0.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgone);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgone);
//                }
//
//                uid1=listPremiumPlanGenealogy.get(1).getUserid();
//                String a1=listPremiumPlanGenealogy.get(1).getMemberActive();
//                Log.i(TAG, "uid1"+uid1);
//                if (uid1==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgtwo);
//
//                }else if(a1.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgtwo);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgtwo);
//                }
//
//                uid2=listPremiumPlanGenealogy.get(2).getUserid();
//                String a2=listPremiumPlanGenealogy.get(2).getMemberActive();
//                Log.i(TAG, "uid2"+uid2);
//                if (uid2==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgthree);
//
//                }else if(a2.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgthree);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgthree);
//                }
//                uid3=listPremiumPlanGenealogy.get(3).getUserid();
//                String a3=listPremiumPlanGenealogy.get(3).getMemberActive();
//                Log.i(TAG, "uid3"+uid3);
//                if (uid3==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgfour);
//
//                }
//                else if (a3.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgfour);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgfour);
//                }
//                uid4=listPremiumPlanGenealogy.get(4).getUserid();
//                String a4=listPremiumPlanGenealogy.get(4).getMemberActive();
//                Log.i(TAG, "uid4"+uid4);
//                if (uid4==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgfive);
//                }else if (a4.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgfive);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgfive);
//                }
//                uid5=listPremiumPlanGenealogy.get(5).getUserid();
//                String a5=listPremiumPlanGenealogy.get(5).getMemberActive();
//                Log.i(TAG, "uid5"+uid5);
//                if (uid5==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgsix);
//
//                }else if (a5.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgsix);
//                }else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgsix);
//                }
//                uid6=listPremiumPlanGenealogy.get(6).getUserid();
//                String a6=listPremiumPlanGenealogy.get(6).getMemberActive();
//                Log.i(TAG, "uid6"+uid6);
//                if (uid6==null){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.vacant)
//                            .into(imgseven);
//
//                }else if (a6.equals("Y")){
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.active)
//                            .into(imgseven);
//                }
//                else {
//                    Glide.with(getApplicationContext())
//                            .load(R.drawable.notactive)
//                            .into(imgseven);
//                }
//                String username = listPremiumPlanGenealogy.get(0).getName();
//                txtone.setText(username);
//                String u = listPremiumPlanGenealogy.get(1).getName();
//                txttwo.setText(u);
//                String three = listPremiumPlanGenealogy.get(2).getName();
//                txtthree.setText(three);
//                String four = listPremiumPlanGenealogy.get(3).getName();
//                txtfour.setText(four);
//                String five = listPremiumPlanGenealogy.get(4).getName();
//                txtfive.setText(five);
//                String six = listPremiumPlanGenealogy.get(5).getName();
//                txtsix.setText(six);
//                String seven = listPremiumPlanGenealogy.get(6).getName();
//                txtseven.setText(seven);
//
//                String lcount = listPremiumPlanGenealogy.get(0).getLeftCount();
//                leftcount_one.setText(lcount);
//                String rcount = listPremiumPlanGenealogy.get(0).getRightCount();
//                rightcount_one.setText(rcount);
//                String lactive = listPremiumPlanGenealogy.get(0).getLeftActive();
//                leftactive_one.setText(lactive);
//                String ractive = listPremiumPlanGenealogy.get(0).getRightActive();
//                rightactive_one.setText(ractive);
//                String lpv = listPremiumPlanGenealogy.get(0).getLeftPv();
//                leftGBV_one.setText(lpv);
//                String rpv = listPremiumPlanGenealogy.get(0).getRightPv();
//                rightGBV_one.setText(rpv);
//                String name = listPremiumPlanGenealogy.get(0).getName();
//                username_one.setText(name);
//                String spuserid_one = listPremiumPlanGenealogy.get(0).getSponsorId();
//                sponsor_userid_one.setText(spuserid_one);
//
//
//
//
//                String lcount_two = listPremiumPlanGenealogy.get(1).getLeftCount();
//                leftcount_two.setText(lcount_two);
//                String rcount_two = listPremiumPlanGenealogy.get(1).getRightCount();
//                rightcount_two.setText(rcount_two);
//                String lactive_two = listPremiumPlanGenealogy.get(1).getLeftActive();
//                leftactive_two.setText(lactive_two);
//                String ractive_two = listPremiumPlanGenealogy.get(1).getRightActive();
//                rightactive_two.setText(ractive_two);
//                String lpv_two = listPremiumPlanGenealogy.get(1).getLeftPv();
//                leftGBV_two.setText(lpv_two);
//                String rpv_two = listPremiumPlanGenealogy.get(1).getRightPv();
//                rightGBV_two.setText(rpv_two);
//                String name_two = listPremiumPlanGenealogy.get(1).getName();
//                username_two.setText(name_two);
//                String spuserid_two = listPremiumPlanGenealogy.get(1).getSponsorId();
//                sponsor_userid_two.setText(spuserid_two);
//
//
//                String lcount_three = listPremiumPlanGenealogy.get(2).getLeftCount();
//                leftcount_three.setText(lcount_three);
//                String rcount_three = listPremiumPlanGenealogy.get(2).getRightCount();
//                rightcount_three.setText(rcount_three);
//                String lactive_three = listPremiumPlanGenealogy.get(2).getLeftActive();
//                leftactive_three.setText(lactive_three);
//                String ractive_three = listPremiumPlanGenealogy.get(2).getRightActive();
//                rightactive_three.setText(ractive_three);
//                String lpv_three = listPremiumPlanGenealogy.get(2).getLeftPv();
//                leftGBV_three.setText(lpv_three);
//                String rpv_three = listPremiumPlanGenealogy.get(2).getRightPv();
//                rightGBV_three.setText(rpv_three);
//                String name_three = listPremiumPlanGenealogy.get(2).getName();
//                username_three.setText(name_three);
//                String spuserid_three = listPremiumPlanGenealogy.get(2).getSponsorId();
//                sponsor_userid_three.setText(spuserid_three);
//
//
//                String lcount_four = listPremiumPlanGenealogy.get(3).getLeftCount();
//                leftcount_four.setText(lcount_four);
//                String rcount_four = listPremiumPlanGenealogy.get(3).getRightCount();
//                rightcount_four.setText(rcount_four);
//                String lactive_four = listPremiumPlanGenealogy.get(3).getLeftActive();
//                leftactive_four.setText(lactive_four);
//                String ractive_four = listPremiumPlanGenealogy.get(3).getRightActive();
//                rightactive_four.setText(ractive_four);
//                String lpv_four = listPremiumPlanGenealogy.get(3).getLeftPv();
//                leftGBV_four.setText(lpv_four);
//                String rpv_four = listPremiumPlanGenealogy.get(3).getRightPv();
//                rightGBV_four.setText(rpv_four);
//                String name_four = listPremiumPlanGenealogy.get(3).getName();
//                username_four.setText(name_four);
//                String spuserid_four = listPremiumPlanGenealogy.get(3).getSponsorId();
//                sponsor_userid_four.setText(spuserid_four);
//
//
//                String lcount_five = listPremiumPlanGenealogy.get(4).getLeftCount();
//                leftcount_five.setText(lcount_five);
//                String rcount_five = listPremiumPlanGenealogy.get(4).getRightCount();
//                rightcount_five.setText(rcount_five);
//                String lactive_five = listPremiumPlanGenealogy.get(4).getLeftActive();
//                leftactive_five.setText(lactive_five);
//                String ractive_five = listPremiumPlanGenealogy.get(4).getRightActive();
//                rightactive_one.setText(ractive_five);
//                String lpv_five = listPremiumPlanGenealogy.get(4).getLeftPv();
//                leftGBV_five.setText(lpv_five);
//                String rpv_five = listPremiumPlanGenealogy.get(4).getRightPv();
//                rightGBV_five.setText(rpv_five);
//                String name_five = listPremiumPlanGenealogy.get(4).getName();
//                username_five.setText(name_five);
//                String spuserid_five = listPremiumPlanGenealogy.get(4).getSponsorId();
//                sponsor_userid_five.setText(spuserid_five);
//
//                String lcount_six = listPremiumPlanGenealogy.get(5).getLeftCount();
//                leftcount_six.setText(lcount_six);
//                String rcount_six = listPremiumPlanGenealogy.get(5).getRightCount();
//                rightcount_six.setText(rcount_six);
//                String lactive_six = listPremiumPlanGenealogy.get(5).getLeftActive();
//                leftactive_six.setText(lactive_six);
//                String ractive_six = listPremiumPlanGenealogy.get(5).getRightActive();
//                rightactive_six.setText(ractive_six);
//                String lpv_six = listPremiumPlanGenealogy.get(5).getLeftPv();
//                leftGBV_six.setText(lpv_six);
//                String rpv_six = listPremiumPlanGenealogy.get(5).getRightPv();
//                rightGBV_six.setText(rpv_six);
//                String name_six = listPremiumPlanGenealogy.get(5).getName();
//                username_six.setText(name_six);
//                String spuserid_six = listPremiumPlanGenealogy.get(5).getSponsorId();
//                sponsor_userid_six.setText(spuserid_six);
//
//                String lcount_seven = listPremiumPlanGenealogy.get(6).getLeftCount();
//                leftcount_seven.setText(lcount_seven);
//                String rcount_seven = listPremiumPlanGenealogy.get(6).getRightCount();
//                rightcount_seven.setText(rcount_seven);
//                String lactive_seven = listPremiumPlanGenealogy.get(6).getLeftActive();
//                leftactive_seven.setText(lactive_seven);
//                String ractive_seven = listPremiumPlanGenealogy.get(6).getRightActive();
//                rightactive_seven.setText(ractive_seven);
//                String lpv_seven = listPremiumPlanGenealogy.get(6).getLeftPv();
//                leftGBV_seven.setText(lpv_seven);
//                String rpv_seven = listPremiumPlanGenealogy.get(6).getRightPv();
//                rightGBV_one.setText(rpv_seven);
//                String name_seven = listPremiumPlanGenealogy.get(6).getName();
//                username_seven.setText(name_seven);
//                String spuserid_seven = listPremiumPlanGenealogy.get(6).getSponsorId();
//                sponsor_userid_seven.setText(spuserid_seven);
//
//            }
//            @Override
//            public void onFailure(Call<ResponsePremiumGenealogy> call, Throwable t) {
//
//            }
//        });
    }
}