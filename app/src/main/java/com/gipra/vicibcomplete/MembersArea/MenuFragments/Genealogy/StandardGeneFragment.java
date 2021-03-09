package com.gipra.vicibcomplete.MembersArea.MenuFragments.Genealogy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
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
import com.gipra.vicibcomplete.MembersArea.Gene.ListPremiumPlanGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.ListStandardPlanGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.PremiumGeneBottomDailogue;
import com.gipra.vicibcomplete.MembersArea.Gene.RegVaccant;
import com.gipra.vicibcomplete.MembersArea.Gene.ResponsePremiumGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.ResponseStandardGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.StandardGeneBottomDailogue;
import com.gipra.vicibcomplete.MembersArea.Gene.StandardPlanGene;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Registration;
import com.gipra.vicibcomplete.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StandardGeneFragment extends Fragment {

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


    String side0,side1,side2,side3,side4,side5,side6;

    String sp_upline0,sp_upline1,sp_upline2,sp_upline3,sp_upline4,sp_upline5,sp_upline6;


    public StandardGeneFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_standard_gene, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Standard Genealogy");

        st_layout=view.findViewById(R.id.st_layout);

//        Toolbar toolbar=view.findViewById(R.id.standard_geneToolBar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        st_info = view.findViewById(R.id.st_info);
        st_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InfoBottomSheet();

//                LayoutInflater layoutInflater = (LayoutInflater) StandardPlanGene.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View premium_popup = layoutInflater.inflate(R.layout.standard_genepopup, null);
//
//                popupWindow = new PopupWindow(premium_popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.showAtLocation(st_layout, Gravity.CENTER, 0, 0);
//                popupWindow.setFocusable(true);
//                popupWindow.update();
            }
        });
        standard_refresh=view.findViewById(R.id.standard_refresh);
        standard_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                StandardGeneFragment standardGeneFragment = new StandardGeneFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, standardGeneFragment);
                fragmentTransaction.commit();
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
        st_imgone=view.findViewById(R.id.st_imgone);
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



        st_imgtwo=view.findViewById(R.id.st_imgtwo);
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



        st_imgthree=view.findViewById(R.id.st_imgthree);
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


        st_imgfour=view.findViewById(R.id.st_imgfour);
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

        st_imgfive=view.findViewById(R.id.st_imgfive);
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



        st_imgsix=view.findViewById(R.id.st_imgsix);
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

        st_imgseven=view.findViewById(R.id.st_imgseven);
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



        st_txtone=view.findViewById(R.id.st_txtone);
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
        st_txttwo=view.findViewById(R.id.st_txttwo);
        st_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid1);

//
            }
        });
        st_txtthree=view.findViewById(R.id.st_txtthree);
        st_txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid2);


            }
        });
        st_txtfour=view.findViewById(R.id.st_txtfour);
        st_txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid3);


            }
        });
        st_txtfive=view.findViewById(R.id.st_txtfive);
        st_txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid4);


            }
        });
        st_txtsix=view.findViewById(R.id.st_txtsix);

        st_txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid5);


            }
        });
        st_txtseven=view.findViewById(R.id.st_txtseven);
        st_txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gene(uid6);


            }
        });
        st_llimgone=view.findViewById(R.id.st_llimgone);
        st_username_one=view.findViewById(R.id.st_username_one);
        st_name_one=view.findViewById(R.id.st_name_one);
        st_sponsor_username_one=view.findViewById(R.id.st_sponsor_username_one);
        st_sponsor_name_one=view.findViewById(R.id.st_sponsor_name_one);
        st_leftBV_one=view.findViewById(R.id.st_leftBV_one);
        st_rightBV_one=view.findViewById(R.id.st_rightBV_one);
        st_leftcount_one=view.findViewById(R.id.st_leftcount_one);
        st_rightcount_one=view.findViewById(R.id.st_rightcount_one);

        st_llimgtwo=view.findViewById(R.id.st_llimgtwo);
        st_username_two=view.findViewById(R.id.st_username_two);
        st_name_two=view.findViewById(R.id.st_name_two);
        st_sponsor_username_two=view.findViewById(R.id.st_sponsor_username_two);
        st_sponsor_name_two=view.findViewById(R.id.st_sponsor_name_two);
        st_leftBV_two=view.findViewById(R.id.st_leftBV_two);
        st_rightBV_two=view.findViewById(R.id.st_rightBV_two);
        st_leftcount_two=view.findViewById(R.id.st_leftcount_two);
        st_rightcount_two=view.findViewById(R.id.st_rightcount_two);

        st_llimgthree=view.findViewById(R.id.st_llimgthree);
        st_username_three=view.findViewById(R.id.st_username_three);
        st_name_three=view.findViewById(R.id.st_name_three);
        st_sponsor_username_three=view.findViewById(R.id.st_sponsor_username_three);
        st_sponsor_name_three=view.findViewById(R.id.st_sponsor_name_three);
        st_leftBV_three=view.findViewById(R.id.st_leftBV_three);
        st_rightBV_three=view.findViewById(R.id.st_rightBV_three);
        st_leftcount_three=view.findViewById(R.id.st_leftcount_three);
        st_rightcount_three=view.findViewById(R.id.st_rightcount_three);

        st_llimgfour=view.findViewById(R.id.st_llimgfour);
        st_username_four=view.findViewById(R.id.st_username_four);
        st_name_four=view.findViewById(R.id.st_name_four);
        st_sponsor_username_four=view.findViewById(R.id.st_sponsor_username_four);
        st_sponsor_name_four=view.findViewById(R.id.st_sponsor_name_four);
        st_leftBV_four=view.findViewById(R.id.st_leftBV_four);
        st_rightBV_four=view.findViewById(R.id.st_rightBV_four);
        st_leftcount_four=view.findViewById(R.id.st_leftcount_four);
        st_rightcount_four=view.findViewById(R.id.st_rightcount_four);

        st_llimgfive=view.findViewById(R.id.st_llimgfive);
        st_username_five=view.findViewById(R.id.st_username_five);
        st_name_five=view.findViewById(R.id.st_name_five);
        st_sponsor_username_five=view.findViewById(R.id.st_sponsor_username_five);
        st_sponsor_name_five=view.findViewById(R.id.st_sponsor_name_five);
        st_leftBV_five=view.findViewById(R.id.st_leftBV_five);
        st_rightBV_five=view.findViewById(R.id.st_rightBV_five);
        st_leftcount_five=view.findViewById(R.id.st_leftcount_five);
        st_rightcount_five=view.findViewById(R.id.st_rightcount_five);


        st_llimgsix=view.findViewById(R.id.st_llimgsix);
        st_username_six=view.findViewById(R.id.st_username_six);
        st_name_six=view.findViewById(R.id.st_name_six);
        st_sponsor_username_six=view.findViewById(R.id.st_sponsor_username_six);
        sponsor_name_six=view.findViewById(R.id.sponsor_name_six);
        st_leftBV_six=view.findViewById(R.id.st_leftBV_six);
        st_rightBV_six=view.findViewById(R.id.st_rightBV_six);
        st_leftcount_six=view.findViewById(R.id.st_leftcount_six);
        st_rightcount_six=view.findViewById(R.id.st_rightcount_six);

        st_llimgseven=view.findViewById(R.id.st_llimgseven);
        st_username_seven=view.findViewById(R.id.st_username_seven);
        st_name_seven=view.findViewById(R.id.st_name_seven);
        st_sponsor_username_seven=view.findViewById(R.id.st_sponsor_username_seven);
        st_sponsor_name_seven=view.findViewById(R.id.st_sponsor_name_seven);
        st_leftBV_seven=view.findViewById(R.id.st_leftBV_seven);
        st_rightBV_seven=view.findViewById(R.id.st_rightBV_seven);
        st_leftcount_seven=view.findViewById(R.id.st_leftcount_seven);
        st_rightcount_seven=view.findViewById(R.id.st_rightcount_seven);



        SharedPreferences shpref;
        shpref = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        gene(u);
        st_txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid1.equals("0")) {


                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side1);
                    i.putExtra("UPLINE",sp_upline1);
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
        st_txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid2.equals("0")) {

                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side2);
                    i.putExtra("UPLINE",sp_upline2);
                    startActivity(i);
                } else {
                    gene(uid2);
                }


            }
        });
        st_txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid3.equals("0")) {

                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side3);
                    i.putExtra("UPLINE",sp_upline3);
                    startActivity(i);
                } else {
                    gene(uid3);
                }

            }
        });
        st_txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid4.equals("0")) {

                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side4);
                    i.putExtra("UPLINE",sp_upline4);
                    startActivity(i);
                } else {
                    gene(uid4);
                }

            }
        });
        st_txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid5.equals("")) {

                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side5);
                    i.putExtra("UPLINE",sp_upline5);
                    startActivity(i);
                } else {
                    gene(uid5);
                }

            }
        });
        st_txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid6.equals("0")) {

                    Intent i = new Intent(getContext(), RegVaccant.class);
                    i.putExtra("SIDE",side6);
                    i.putExtra("UPLINE",sp_upline6);
                    startActivity(i);

                } else {
                    gene(uid6);
                }

            }
        });



        return view;
    }
    private  void gene(final String id){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
       Call<ResponseStandardGenealogy>call=api.StandardGene(Integer.parseInt(id));
    //   Call<ResponseStandardGenealogy> call=api.StandardGene(1);

        call.enqueue(new Callback<ResponseStandardGenealogy>() {
            @Override
            public void onResponse(Call<ResponseStandardGenealogy> call, Response<ResponseStandardGenealogy> response) {
                if (response.body().getStatus().equals("1")){
                    final ResponseStandardGenealogy responseStandardGenealogy = response.body();
                    listStandardPlanGenealogy = (ArrayList<ListStandardPlanGenealogy>)responseStandardGenealogy.getData();

                    String uname=listStandardPlanGenealogy.get(0).getName();
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("USERNAME",uname);

//                   postion 0

                    uid0= listStandardPlanGenealogy.get(0).getUserid();
                    String mactive0= listStandardPlanGenealogy.get(0).getMemberActive();
                    String mbactive0= listStandardPlanGenealogy.get(0).getMemberbronzeActive();
                    String bactive0= listStandardPlanGenealogy.get(0).getBasicActive();
                    t0=listStandardPlanGenealogy.get(0).getName();
                    side0=listStandardPlanGenealogy.get(0).getSide();
                    sp_upline0=listStandardPlanGenealogy.get(0).getSponsorusername();
                    st_txtone.setText(t0);
                    String v0=listStandardPlanGenealogy.get(0).getVacant();
                    if (v0.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgone);
                    }





                   else if (uid0.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgone);
                    }
                    else if (mactive0.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgone);
                    }
                    else if (mbactive0.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgone);
                    }
                    else if (bactive0.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgone);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgone);
                    }



//                   postion 1

                    uid1= String.valueOf(listStandardPlanGenealogy.get(1).getUserid());
                    String mactive1= String.valueOf(listStandardPlanGenealogy.get(1).getMemberActive());
                    String mbactive1= String.valueOf(listStandardPlanGenealogy.get(1).getMemberbronzeActive());
                    String bactive1= String.valueOf(listStandardPlanGenealogy.get(1).getBasicActive());
                    t1=listStandardPlanGenealogy.get(1).getName();
                    side1=listStandardPlanGenealogy.get(1).getSide();
                    sp_upline1=listStandardPlanGenealogy.get(1).getSponsorusername();
                    st_txttwo.setText(t1);
                    String v1=listStandardPlanGenealogy.get(1).getVacant();
                    if (v1.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgtwo);
                    }


                    else if (uid1.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgtwo);
                    }
                    else if (mactive1.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgtwo);
                    }
                    else if (mbactive1.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgtwo);
                    }
                    else if (bactive1.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgtwo);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgtwo);
                    }


//                   postion 2

                    uid2= String.valueOf(listStandardPlanGenealogy.get(2).getUserid());
                    String mactive2= String.valueOf(listStandardPlanGenealogy.get(2).getMemberActive());
                    String mbactive2= String.valueOf(listStandardPlanGenealogy.get(2).getMemberbronzeActive());
                    String bactive2= String.valueOf(listStandardPlanGenealogy.get(2).getBasicActive());
                    t2=listStandardPlanGenealogy.get(2).getName();
                    side2=listStandardPlanGenealogy.get(2).getSide();
                    sp_upline2=listStandardPlanGenealogy.get(2).getSponsorusername();
                    st_txtthree.setText(t2);
                    String v2=listStandardPlanGenealogy.get(2).getVacant();
                    if (v2.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgthree);
                    }


                   else if (uid2.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgthree);
                    }
                    else if (mactive2.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgthree);
                    }
                    else if (mbactive2.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgthree);
                    }
                    else if (bactive2.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgthree);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgthree);
                    }


//                   postion 3

                    uid3= String.valueOf(listStandardPlanGenealogy.get(3).getUserid());
                    String mactive3= String.valueOf(listStandardPlanGenealogy.get(3).getMemberActive());
                    String mbactive3= String.valueOf(listStandardPlanGenealogy.get(3).getMemberbronzeActive());
                    String bactive3= String.valueOf(listStandardPlanGenealogy.get(3).getBasicActive());
                    t3=listStandardPlanGenealogy.get(3).getName();
                    st_txtfour.setText(t3);
                    side3=listStandardPlanGenealogy.get(3).getSide();
                    sp_upline3=listStandardPlanGenealogy.get(3).getSponsorusername();




                    String v3=listStandardPlanGenealogy.get(3).getVacant();
                    if (v3.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfour);
                    }


                    else if (uid3.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfour);
                    }

                    else if (mactive3.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgfour);
                    }
                    else if (mbactive3.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgfour);
                    }
                    else if (bactive3.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgfour);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgfour);
                    }

//                   postion 4

                    uid4= String.valueOf(listStandardPlanGenealogy.get(4).getUserid());
                    String mactive4= String.valueOf(listStandardPlanGenealogy.get(4).getMemberActive());
                    String mbactive4= String.valueOf(listStandardPlanGenealogy.get(4).getMemberbronzeActive());
                    String bactive4= String.valueOf(listStandardPlanGenealogy.get(4).getBasicActive());
                    t4=listStandardPlanGenealogy.get(4).getName();
                    st_txtfive.setText(t4);
                    side4=listStandardPlanGenealogy.get(4).getSide();
                    sp_upline4=listStandardPlanGenealogy.get(4).getSponsorusername();

                    String v4=listStandardPlanGenealogy.get(4).getVacant();
                    if (v4.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfive);
                    }

                   else if (uid4.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgfive);
                    }
                    else if (mactive4.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgfive);
                    }
                    else if (mbactive4.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgfive);
                    }
                    else if (bactive4.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgfive);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgfive);
                    }


//                   postion 5

                    uid5= String.valueOf(listStandardPlanGenealogy.get(5).getUserid());
                    String mactive5= String.valueOf(listStandardPlanGenealogy.get(5).getMemberActive());
                    String mbactive5= String.valueOf(listStandardPlanGenealogy.get(5).getMemberbronzeActive());
                    String bactive5= String.valueOf(listStandardPlanGenealogy.get(5).getBasicActive());
                    t5=listStandardPlanGenealogy.get(5).getName();
                    st_txtsix.setText(t5);
                    side5=listStandardPlanGenealogy.get(5).getSide();
                    sp_upline5=listStandardPlanGenealogy.get(5).getSponsorusername();

                    String v5=listStandardPlanGenealogy.get(5).getVacant();
                    if (v5.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgsix);
                    }



                 else if (uid5.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgsix);
                    }
                    else if (mactive5.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgsix);
                    }
                    else if (mbactive5.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgsix);
                    }
                    else if (bactive5.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgsix);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgsix);
                    }



//                   postion 6

                    uid6= String.valueOf(listStandardPlanGenealogy.get(6).getUserid());
                    String mactive6= String.valueOf(listStandardPlanGenealogy.get(6).getMemberActive());
                    String mbactive6= String.valueOf(listStandardPlanGenealogy.get(6).getMemberbronzeActive());
                    String bactive6= String.valueOf(listStandardPlanGenealogy.get(6).getBasicActive());
                    t6=listStandardPlanGenealogy.get(6).getName();
                    st_txtseven.setText(t6);
                    side6=listStandardPlanGenealogy.get(6).getSide();
                    sp_upline6=listStandardPlanGenealogy.get(6).getSponsorusername();

                    String v6=listStandardPlanGenealogy.get(6).getVacant();
                    if (v6.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgseven);
                    }

                   else if (uid6.equals("0")){
                        Glide.with(getContext())
                                .load(R.drawable.vacant)
                                .into(st_imgseven);
                    }
                    else if (mactive6.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.active)
                                .into(st_imgseven);
                    }
                    else if (mbactive6.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.bronzeactive)
                                .into(st_imgseven);
                    }
                    else if (bactive6.equals("Y")){
                        Glide.with(getContext())
                                .load(R.drawable.basicplan)
                                .into(st_imgseven);
                    }
                    else {
                        Glide.with(getContext())
                                .load(R.drawable.notactive)
                                .into(st_imgseven);
                    }

                    String p_username1 = String.valueOf(listStandardPlanGenealogy.get(0).getUserid());
                    Log.e("username",p_username1);
                    st_username_one.setText(p_username1);
                    String p_username2 = String.valueOf(listStandardPlanGenealogy.get(1).getUserid());
                    Log.e("u",p_username2);
                    st_username_two.setText(p_username2);
                    String p_username3 = String.valueOf(listStandardPlanGenealogy.get(2).getUserid());
                    Log.e("three",p_username3);
                    st_username_three.setText(p_username3);
                    String p_username4 = String.valueOf(listStandardPlanGenealogy.get(3).getUserid());
                    st_username_four.setText(p_username4);
                    String p_username5 = String.valueOf(listStandardPlanGenealogy.get(4).getUserid());
                    st_username_five.setText(p_username5);
                    String p_username6 = String.valueOf(listStandardPlanGenealogy.get(5).getUserid());
                    st_username_six.setText(p_username6);
                    String p_username7 = String.valueOf(listStandardPlanGenealogy.get(6).getUserid());
                    st_username_seven.setText(p_username7);


                    String p_name1=listStandardPlanGenealogy.get(0).getName();
                    st_name_one.setText(p_name1);
                    String p_name2=listStandardPlanGenealogy.get(1).getName();
                    st_name_two.setText(p_name2);
                    String p_name3=listStandardPlanGenealogy.get(2).getName();
                    st_name_three.setText(p_name3);
                    String p_name4=listStandardPlanGenealogy.get(3).getName();
                    st_name_four.setText(p_name4);
                    String p_name5=listStandardPlanGenealogy.get(4).getName();
                    st_name_five.setText(p_name5);
                    String p_name6=listStandardPlanGenealogy.get(5).getName();
                    st_name_six.setText(p_name6);
                    String p_name7=listStandardPlanGenealogy.get(6).getName();
                    st_name_seven.setText(p_name7);


                    String p_sponsor_username1= String.valueOf(listStandardPlanGenealogy.get(0).getSponsorId());
                    st_sponsor_username_one.setText(p_sponsor_username1);
                    String p_sponsor_username2= String.valueOf(listStandardPlanGenealogy.get(1).getSponsorId());
                    st_sponsor_username_two.setText(p_sponsor_username2);
                    String p_sponsor_username3= String.valueOf(listStandardPlanGenealogy.get(2).getSponsorId());
                    st_sponsor_username_three.setText(p_sponsor_username3);
                    String p_sponsor_username4= String.valueOf(listStandardPlanGenealogy.get(3).getSponsorId());
                    st_sponsor_username_four.setText(p_sponsor_username4);
                    String p_sponsor_username5= String.valueOf(listStandardPlanGenealogy.get(4).getSponsorId());
                    st_sponsor_username_five.setText(p_sponsor_username5);
                    String p_sponsor_username6= String.valueOf(listStandardPlanGenealogy.get(5).getSponsorId());
                    st_sponsor_username_six.setText(p_sponsor_username6);
                    String p_sponsor_username7= String.valueOf(listStandardPlanGenealogy.get(6).getSponsorId());
                    st_sponsor_username_seven.setText(p_sponsor_username7);


                    String p_sponsor_name1= String.valueOf(listStandardPlanGenealogy.get(0).getSponsorName());
                    st_sponsor_name_one.setText(p_sponsor_name1);
                    String p_sponsor_name2= String.valueOf(listStandardPlanGenealogy.get(1).getSponsorName());
                    st_sponsor_name_two.setText(p_sponsor_name2);
                    String p_sponsor_name3= String.valueOf(listStandardPlanGenealogy.get(2).getSponsorName());
                    st_sponsor_name_three.setText(p_sponsor_name3);
                    String p_sponsor_name4= String.valueOf(listStandardPlanGenealogy.get(3).getSponsorName());
                    st_name_four.setText(p_sponsor_name4);
                    String p_sponsor_name5= String.valueOf(listStandardPlanGenealogy.get(4).getSponsorName());
                    st_name_five.setText(p_sponsor_name5);
                    String p_sponsor_name6= String.valueOf(listStandardPlanGenealogy.get(5).getSponsorName());
                    st_name_six.setText(p_sponsor_name6);
                    String p_sponsor_name7= String.valueOf(listStandardPlanGenealogy.get(6).getSponsorName());
                    st_name_seven.setText(p_sponsor_name7);


                    String p_leftBV1= String.valueOf(listStandardPlanGenealogy.get(0).getLeftPv());
                    st_leftBV_one.setText(p_leftBV1);
                    String p_leftBV2= String.valueOf(listStandardPlanGenealogy.get(1).getLeftPv());
                    st_leftBV_two.setText(p_leftBV2);
                    String p_leftBV3= String.valueOf(listStandardPlanGenealogy.get(2).getLeftPv());
                    st_leftBV_three.setText(p_leftBV3);
                    String p_leftBV4= String.valueOf(listStandardPlanGenealogy.get(3).getLeftPv());
                    st_leftBV_four.setText(p_leftBV4);
                    String p_leftBV5= String.valueOf(listStandardPlanGenealogy.get(4).getLeftPv());
                    st_leftBV_five.setText(p_leftBV5);
                    String p_leftBV6= String.valueOf(listStandardPlanGenealogy.get(5).getLeftPv());
                    st_leftBV_six.setText(p_leftBV6);
                    String p_leftBV7= String.valueOf(listStandardPlanGenealogy.get(6).getLeftPv());
                    st_leftBV_seven.setText(p_leftBV7);


                    String p_rightBV1= String.valueOf(listStandardPlanGenealogy.get(0).getRightPv());
                    st_rightBV_one.setText(p_rightBV1);
                    String p_rightBV2= String.valueOf(listStandardPlanGenealogy.get(1).getRightPv());
                    st_rightBV_two.setText(p_rightBV2);
                    String p_rightBV3= String.valueOf(listStandardPlanGenealogy.get(2).getRightPv());
                    st_rightBV_three.setText(p_rightBV3);
                    String p_rightBV4= String.valueOf(listStandardPlanGenealogy.get(3).getRightPv());
                    st_rightBV_four.setText(p_rightBV4);
                    String p_rightBV5= String.valueOf(listStandardPlanGenealogy.get(4).getRightPv());
                    st_rightBV_five.setText(p_rightBV5);
                    String p_rightBV6= String.valueOf(listStandardPlanGenealogy.get(5).getRightPv());
                    st_rightBV_six.setText(p_rightBV6);
                    String p_rightBV7= String.valueOf(listStandardPlanGenealogy.get(6).getRightPv());
                    st_rightBV_seven.setText(p_rightBV7);





                    String p_leftcount1 = String.valueOf(listStandardPlanGenealogy.get(0).getLeftCount());
                    st_leftcount_one.setText(p_leftcount1);
                    String p_leftcount2= String.valueOf(listStandardPlanGenealogy.get(1).getLeftCount());
                    st_leftcount_two.setText(p_leftcount2);
                    String p_leftcount3= String.valueOf(listStandardPlanGenealogy.get(2).getLeftCount());
                    st_leftcount_three.setText(p_leftcount3);
                    String p_leftcount4= String.valueOf(listStandardPlanGenealogy.get(3).getLeftCount());
                    st_leftcount_four.setText(p_leftcount4);
                    String p_leftcount5= String.valueOf(listStandardPlanGenealogy.get(4).getLeftCount());
                    st_leftcount_five.setText(p_leftcount5);
                    String p_leftcount6= String.valueOf(listStandardPlanGenealogy.get(5).getLeftCount());
                    st_leftcount_six.setText(p_leftcount6);
                    String p_leftcount7= String.valueOf(listStandardPlanGenealogy.get(6).getLeftCount());
                    st_leftcount_seven.setText(p_leftcount7);


                    String p_rightcount1 = String.valueOf(listStandardPlanGenealogy.get(0).getRightCount());
                    st_rightcount_one.setText(p_rightcount1);
                    String p_rightcount2= String.valueOf(listStandardPlanGenealogy.get(1).getRightCount());
                    st_rightcount_two.setText(p_rightcount2);
                    String p_rightcount3= String.valueOf(listStandardPlanGenealogy.get(2).getRightCount());
                    st_rightcount_three.setText(p_rightcount3);
                    String p_rightcount4= String.valueOf(listStandardPlanGenealogy.get(3).getRightCount());
                    st_rightcount_four.setText(p_rightcount4);
                    String p_rightcount5= String.valueOf(listStandardPlanGenealogy.get(4).getRightCount());
                    st_rightcount_five.setText(p_rightcount5);
                    String p_rightcount6= String.valueOf(listStandardPlanGenealogy.get(5).getRightCount());
                    st_rightcount_six.setText(p_rightcount6);
                    String p_rightcount7= String.valueOf(listStandardPlanGenealogy.get(6).getRightCount());
                    st_rightcount_seven.setText(p_rightcount7);




                }
            }

            @Override
            public void onFailure(Call<ResponseStandardGenealogy> call, Throwable t) {

            }
        });

    }
    private void InfoBottomSheet(){

        PremiumGeneBottomDailogue bottomSheet = new PremiumGeneBottomDailogue();
        bottomSheet.show(getActivity().getSupportFragmentManager(),
                "ModalBotxtomSheet");


        //val moreOrderDetails = OrderDetailSheet(applicationContext, orderID,login_id)
        //        moreOrderDetails.show(getSupportFragmentManager(), moreOrderDetails.getTag())
    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}

