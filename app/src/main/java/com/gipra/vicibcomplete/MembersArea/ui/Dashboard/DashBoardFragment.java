package com.gipra.vicibcomplete.MembersArea.ui.Dashboard;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.IDCard;
import com.gipra.vicibcomplete.MembersArea.MyProfile.MyProfile;
import com.gipra.vicibcomplete.MembersArea.ProductStore;
import com.gipra.vicibcomplete.MembersArea.SavePdf;
import com.gipra.vicibcomplete.R;
import com.gipra.vicibshoppy.activity.ShoppyHome;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {
    LinearLayout d_standard_layout,d_premium_layout,d_prof_layout;
    Button d_btnprofile,d_btnpremium,d_btnstandard;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawer;
    private static final String TAG = "DashBoardFragment";
    LinearLayout dash_dash,dash_productstore,dash_compliants,dash_idcard,dash_profile;
    TextView d_totalearnings,d_premiumearnings,d_standardearnings,d_repurchaseearnings,d_lastmonthearnings;
    TextView d_prof_name,d_prof_userid,d_prof_sponsorid,d_prof_dateofjoin,d_prof_dayscompleted,d_prof_premiumdirectreferals;
    TextView d_prof_standarddirectreferals,d_prof_leftteam,d_prof_rightteam,d_prof_currentstdbv,d_prof_currentpremiumbv;
    TextView d_pre_activateddate,d_pre_totalleftbv,d_pre_totalrightbv,d_pre_totalbv,d_pre_totalpairbv,d_pre_currentleftbv;
    TextView d_pre_currentrightbv,d_pre_todaysbusinessleftbv,d_pre_bussinessrightbv;
    TextView d_std_activateddate,d_std_totalleftbv,d_std_totalrighttbv,d_std_totaltbv,d_std_totalpairbv;
    TextView d_std_currentleftbv,d_std_currenrightbv,d_std_todaysbusinessleftbv,d_std_todaysbusinessrightbv;



//    MainActivity activity = (MainActivity) getActivity();
    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("DashBoard");

        d_totalearnings=view.findViewById(R.id.d_totalearnings);
        d_premiumearnings=view.findViewById(R.id.d_premiumearnings);
        d_standardearnings=view.findViewById(R.id.d_standardearnings);
        d_repurchaseearnings=view.findViewById(R.id.d_repurchaseearnings);
        d_lastmonthearnings=view.findViewById(R.id.d_lastmonthearnings);

        d_prof_name=view.findViewById(R.id.d_prof_name);
        d_prof_userid=view.findViewById(R.id.d_prof_userid);
        d_prof_sponsorid=view.findViewById(R.id.d_prof_sponsorid);
        d_prof_dateofjoin=view.findViewById(R.id.d_prof_dateofjoin);
        d_prof_dayscompleted=view.findViewById(R.id.d_prof_dayscompleted);
        d_prof_premiumdirectreferals=view.findViewById(R.id.d_prof_premiumdirectreferals);
        d_prof_standarddirectreferals=view.findViewById(R.id.d_prof_standarddirectreferals);
        d_prof_leftteam=view.findViewById(R.id.d_prof_leftteam);
        d_prof_rightteam=view.findViewById(R.id.d_prof_rightteam);
        d_prof_currentstdbv=view.findViewById(R.id.d_prof_currentstdbv);
        d_prof_currentpremiumbv=view.findViewById(R.id.d_prof_currentpremiumbv);

        d_pre_activateddate=view.findViewById(R.id.d_pre_activateddate);
        d_pre_totalleftbv=view.findViewById(R.id.d_pre_totalleftbv);
        d_pre_totalrightbv=view.findViewById(R.id.d_pre_totalrightbv);
        d_pre_totalbv=view.findViewById(R.id.d_pre_totalbv);
        d_pre_totalpairbv=view.findViewById(R.id.d_pre_totalpairbv);
        d_pre_currentleftbv=view.findViewById(R.id.d_pre_currentleftbv);
        d_pre_currentrightbv=view.findViewById(R.id.d_pre_currentrightbv);
        d_pre_todaysbusinessleftbv=view.findViewById(R.id.d_pre_todaysbusinessleftbv);
        d_pre_bussinessrightbv=view.findViewById(R.id.d_pre_bussinessrightbv);

        d_std_activateddate=view.findViewById(R.id.d_std_activateddate);
        d_std_totalleftbv=view.findViewById(R.id.d_std_totalleftbv);
        d_std_totalrighttbv=view.findViewById(R.id.d_std_totalrighttbv);
        d_std_totaltbv=view.findViewById(R.id.d_std_totaltbv);
        d_std_totalpairbv=view.findViewById(R.id.d_std_totalpairbv);
        d_std_currentleftbv=view.findViewById(R.id.d_std_currentleftbv);
        d_std_currenrightbv=view.findViewById(R.id.d_std_currenrightbv);
        d_std_todaysbusinessleftbv=view.findViewById(R.id.d_std_todaysbusinessleftbv);
        d_std_todaysbusinessrightbv=view.findViewById(R.id.d_std_todaysbusinessrightbv);



        ///layouts
        drawer = view.findViewById(R.id.drawer_layout);
        viewDashboard();
        d_standard_layout=view.findViewById(R.id.d_standard_layout);
        d_premium_layout=view.findViewById(R.id.d_premium_layout);
        d_prof_layout=view.findViewById(R.id.d_prof_layout);
        d_btnprofile=view.findViewById(R.id.d_btnprofile);
        d_btnpremium=view.findViewById(R.id.d_btnpremium);
        d_btnstandard=view.findViewById(R.id.d_btnstandard);
        d_btnprofile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                d_prof_layout.setVisibility(View.VISIBLE);
                d_premium_layout.setVisibility(View.GONE);
                d_standard_layout.setVisibility(View.GONE);
                d_btnprofile.setBackgroundResource(R.drawable.yellowbg);
                d_btnprofile.setTextColor(Color.WHITE);
                d_btnpremium.setBackgroundResource(R.drawable.white_bg);
                d_btnpremium.setTextColor(Color.BLACK);
                d_btnstandard.setBackgroundResource(R.drawable.white_bg);
                d_btnstandard.setTextColor(Color.BLACK);
//  yourView.setBackgroundColor(Color.parseColor("#ffffff"));

                // btn_my_lit.setBackgroundColor(Color.WHITE);
                //                btn_my_lit.setTextColor(R.color.colorPrimaryDark);
                //                btn_my_story.setTextColor(Color.WHITE);
                //                btn_my_story.setBackgroundResource(R.color.colorPrimaryDark);
                //                btn_my_lit.setFocusable(true);

            }
        });
        d_btnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_premium_layout.setVisibility(View.VISIBLE);
                d_prof_layout.setVisibility(View.GONE);
                d_standard_layout.setVisibility(View.GONE);

                d_btnpremium.setBackgroundResource(R.drawable.yellowbg);
                d_btnpremium.setTextColor(Color.WHITE);
                d_btnprofile.setBackgroundResource(R.drawable.white_bg);
                d_btnprofile.setTextColor(Color.BLACK);
                d_btnstandard.setBackgroundResource(R.drawable.white_bg);
                d_btnstandard.setTextColor(Color.BLACK);
            }
        });
        d_btnstandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_standard_layout.setVisibility(View.VISIBLE);
                d_premium_layout.setVisibility(View.GONE);
                d_prof_layout.setVisibility(View.GONE);

                d_btnstandard.setBackgroundResource(R.drawable.yellowbg);
                d_btnstandard.setTextColor(Color.WHITE);
                d_btnprofile.setBackgroundResource(R.drawable.white_bg);
                d_btnprofile.setTextColor(Color.BLACK);
                d_btnpremium.setBackgroundResource(R.drawable.white_bg);
                d_btnpremium.setTextColor(Color.BLACK);
            }
        });
        dash_dash=view.findViewById(R.id.dash_dash);
        dash_productstore=view.findViewById(R.id.dash_productstore);
        dash_productstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppyHome.class);
                startActivity(intent);
            }
        });
        dash_compliants=view.findViewById(R.id.dash_compliants);
        dash_compliants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SavePdf.class);
                startActivity(intent);
            }
        });
        dash_idcard=view.findViewById(R.id.dash_idcard);
        dash_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IDCard.class);
                startActivity(intent);
            }
        });
        dash_profile=view.findViewById(R.id.dash_profile);
        dash_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyProfile.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void viewDashboard() {
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String id=shpref.getString("ID","");
        Log.e("id",id);
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<Responsedashboard> usercall=api.SearchDashboard(1);
        usercall.enqueue(new Callback<Responsedashboard>() {
            @Override
            public void onResponse(Call<Responsedashboard> call, Response<Responsedashboard> response) {
                if (response.body().getStatus().equals("1")){
                    Responsedashboard responsedashboard=response.body();

                    String totalearnings= String.valueOf(responsedashboard.getTotalEarnings());
                    d_totalearnings.setText(totalearnings);

                    String premiumearnings= String.valueOf(responsedashboard.getGoldEarnings());
                    d_premiumearnings.setText(premiumearnings);

                    String standardearnings= String.valueOf(responsedashboard.getSilverEarnings());
                    d_standardearnings.setText(standardearnings);

                    String repurchaseearnings= String.valueOf(responsedashboard.getRepurchaseEarnings());
                    d_repurchaseearnings.setText(repurchaseearnings);

                    String lastmonthearnings= String.valueOf(responsedashboard.getThismonthRepurchaseEarnings());
                    d_lastmonthearnings.setText(lastmonthearnings);

                    String prof_name=responsedashboard.getCFname();
                    d_prof_name.setText(prof_name);

                    String prof_userid=responsedashboard.getUserid();
                    d_prof_userid.setText(prof_userid);

                    String prof_sponsorid=responsedashboard.getSponsorId();
                    d_prof_sponsorid.setText(prof_sponsorid);

                    String prof_dateofjoin=responsedashboard.getDJoin();
                    d_prof_dateofjoin.setText(prof_dateofjoin);

                    String prof_dayscompleted= String.valueOf(responsedashboard.getDayscompleted());
                    d_prof_dayscompleted.setText(prof_dayscompleted);

                    String prof_premiumdirectreferals= String.valueOf(responsedashboard.getPremiumDirectReferals());
                    d_prof_premiumdirectreferals.setText(prof_premiumdirectreferals);

                    String prof_standarddirectreferals= String.valueOf(responsedashboard.getStdDirectReferals());
                    d_prof_standarddirectreferals.setText(prof_standarddirectreferals);

                    String prof_leftteam=responsedashboard.getLeftTeam();
                    d_prof_leftteam.setText(prof_leftteam);

                    String prof_rightteam=responsedashboard.getRightTeam();
                    d_prof_rightteam.setText(prof_rightteam);

                    String prof_currentstdbv= String.valueOf(responsedashboard.getCurrentStdBv());
                    d_prof_currentstdbv.setText(prof_currentstdbv);

                    String prof_currentpremiumbv= String.valueOf(responsedashboard.getCurrentPremiumBv());
                    d_prof_currentpremiumbv.setText(prof_currentpremiumbv);

                    String pre_activateddate=responsedashboard.getActivatedDate();
                    d_pre_activateddate.setText(pre_activateddate);

                    String pre_totalleftbv=responsedashboard.getTotalLeftBv();
                    d_pre_totalleftbv.setText(pre_totalleftbv);

                    String pre_totalrightbv=responsedashboard.getTotalRightBv();
                    d_pre_totalrightbv.setText(pre_totalrightbv);

                    String pre_totalbv= String.valueOf(responsedashboard.getTotalBv());
                    d_pre_totalbv.setText(pre_totalbv);

                    String pre_totalpairbv=responsedashboard.getTotalPairBv();
                    d_pre_totalpairbv.setText(pre_totalpairbv);

                    String pre_currentleftbv= String.valueOf(responsedashboard.getCurrentLeftBv());
                    d_pre_currentleftbv.setText(pre_currentleftbv);

                    String pre_currentrightbv= String.valueOf(responsedashboard.getCurrentRightBv());
                    d_pre_currentrightbv.setText(pre_currentrightbv);

                    String pre_todaysbusinessleftbv=responsedashboard.getTotalBusinessLeft();
                    d_pre_todaysbusinessleftbv.setText(pre_todaysbusinessleftbv);

                    String pre_bussinessrightbv=responsedashboard.getTotalBusinessRight();
                    d_pre_bussinessrightbv.setText(pre_bussinessrightbv);

                    String std_activateddate=responsedashboard.getSilverActivatedOn();
                    d_std_activateddate.setText(std_activateddate);

                    String std_totalleftbv= String.valueOf(responsedashboard.getTotalBv1());
                    d_std_totalleftbv.setText(std_totalleftbv);

                    String std_totalrighttbv=responsedashboard.getTotalRightBv1();
                    d_std_totalrighttbv.setText(std_totalrighttbv);

                    String std_totaltbv= String.valueOf(responsedashboard.getTotalBv1());
                    d_std_totaltbv.setText(std_totaltbv);

                    String std_totalpairbv=responsedashboard.getTotalPairBv1();
                    d_std_totalpairbv.setText(std_totalpairbv);

                    String std_currentleftbv= String.valueOf(responsedashboard.getCurrentLeftBv1());
                    d_std_currentleftbv.setText(std_currentleftbv);

                    String std_currenrightbv= String.valueOf(responsedashboard.getCurrentRightBv1());
                    d_std_currenrightbv.setText(std_currenrightbv);

                    String std_todaysbusinessleftbv=responsedashboard.getTodaysBusinessLeftBv1();
                    d_std_todaysbusinessleftbv.setText(std_todaysbusinessleftbv);

                    String std_todaysbusinessrightbv=responsedashboard.getTodaysBusinessRightBv1();
                    d_std_todaysbusinessrightbv.setText(std_todaysbusinessrightbv);

                }
                else {

                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Responsedashboard> call, Throwable t) {

            }
        });


    }


}

