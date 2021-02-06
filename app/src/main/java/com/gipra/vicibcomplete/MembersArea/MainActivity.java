package com.gipra.vicibcomplete.MembersArea;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintStatus;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;

import com.gipra.vicibcomplete.MembersArea.Gene.PremiumPlanGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.StandardPlanGene;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageView;
import com.gipra.vicibcomplete.MembersArea.Payout.PayoutLedger;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumRightSideSales;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumTeamSalesBonusDetails;
import com.gipra.vicibcomplete.MembersArea.Reports.BasicActiveMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.FirstPurchaseBVReport;
import com.gipra.vicibcomplete.MembersArea.Reports.LeftSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.MyProducts;
import com.gipra.vicibcomplete.MembersArea.Reports.RightSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.SponsorsList;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.DownlineRepurchaseDetails;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseBVReports;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncome;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncomeDetails;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardRightSideSales;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBonusDetails;
import com.gipra.vicibcomplete.R;
import com.gipra.vicibshoppy.activity.ShoppyHome;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView txtusername,txtname;
    CircleImageView userimg;
    String imgpath;
    SharedPreferences sharedPreferences;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    Fragment f;
    ConstraintLayout mainlayout;

    PopupWindow popupWindow2;

    AVLoadingIndicatorView logout_loader;
    CoordinatorLayout layout_home;
     String imgurl ="https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/profile_image_view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        logout_loader=findViewById(R.id.logout_loader);
        layout_home=findViewById(R.id.layout_home);
     //   logout=findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                LogoutSheet logoutSheet = new LogoutSheet();
//                logoutSheet.show(getSupportFragmentManager(),
//                        "ModalBottomSheet");


//                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customview = layoutInflater.inflate(R.layout.story_delete_popup, null);
//                Button yes = customview.findViewById(R.id.yes);
//                yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        logout();
//
//                    }
//                });
//                Button no = customview.findViewById(R.id.no);
//                no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow2.dismiss();
//                    }
//                });
//                popupWindow2 = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow2.showAtLocation(layout_home, Gravity.CENTER, 0, 0);
//                popupWindow2.setFocusable(true);
//                popupWindow2.update();
//

//            }
//        });
        setSupportActionBar(toolbar);


        mainlayout=findViewById(R.id.mainlayout);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = sharedPreferences.getString("USERNAME", "");
        String n = sharedPreferences.getString("NAME", "");

        View headerview = navigationView.getHeaderView(0);
        userimg=headerview.findViewById(R.id.userimg);
        UserImage();
        txtusername=headerview.findViewById(R.id.txtusername);
        txtusername.setText(u);
        txtname=headerview.findViewById(R.id.txtname);
        txtname.setText(n);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        expandableListView = findViewById(R.id.expandableListView);
        expandableListView.setIndicatorBounds(width - GetPixelFromDips(10), width - GetPixelFromDips(10));


        prepareMenuData();
        populateExpandableList();





//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard,
                R.id.nav_regform, R.id.nav_productstore, R.id.nav_genealogy,
                R.id.nav_silver_plan_genealogy, R.id.nav_gold_plan_genealogy, R.id.nav_sponsortree, R.id.nav_sponsortree_tabularview,
                R.id.nav_sponsor_downline_list, R.id.nav_myproducts, R.id.nav_first_purchase_bv,
                R.id.nav_left_side_member, R.id.nav_right_side_member, R.id.nav_silver_plan_reports, R.id.nav_left_side_sales, R.id.nav_right_side_sales,
                R.id.nav_team_salesbv, R.id.nav_team_sales_bonus, R.id.nav_gold_left_side_sales, R.id.nav_gold_right_side_sales,
                R.id.nav_gold_team_salesbv, R.id.nav_gold_team_sales_bonus, R.id.nav_repurchase_plan_report, R.id.nav_repurchase_bv_report, R.id.nav_downline_repurchase_details, R.id.nav_repurchase_income,
                R.id.nav_repurchase_income_details)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    private void UserImage() {
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String u=shpref.getString("ID","");

        RequestQueue requestQueue=new Volley().newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, imgurl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        try {
                           JSONObject cjsonObject = new JSONObject(response);

                            if (cjsonObject.getString("status").equals("1")) {

                                String img=cjsonObject.getString("path");
                                Log.e("pathh",img);
                                Glide.with(getApplicationContext())
                                        .load(img)
                                        .into(userimg);


                            } else {
                                Toast.makeText(getApplicationContext(), "Some error occured..Please try again later", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error+"");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user_id",u );
                return map;
            }
        };
        requestQueue.add(stringRequest);


    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private void prepareMenuData() {
//        MenuModel menuModel = new MenuModel("Dashboard", true,true,R.drawable.ic_home); //Menu of Android Tutorial. No sub menus
//        headerList.add(menuModel);
//        if (!menuModel.hasChildren) {
//            childList.put(menuModel, null);
//        }
//
//        menuModel=new MenuModel("Product Store",true,true,R.drawable.ic_shopping_cart);
//        headerList.add(menuModel);
//        if (!menuModel.hasChildren) {
//            childList.put(menuModel, null);
//        }

        MenuModel menuModel = new MenuModel("Genealogy", true, true,R.drawable.ic_genealogy); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(" Premium Plan Genealogy ", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

        childModel=new MenuModel("Standard Plan Genealogy",false,false,R.drawable.ic_arrow);
        childModelsList.add(childModel);



//        childModel = new MenuModel(" PET Genealogy", false, false,R.drawable.ic_arrow);
//        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Registration Form", true,true,R.drawable.ic_form); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Reports", true, true,R.drawable.ic_reports); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" My Product", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" First Purchase BV Report", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel("Sponsors List", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Left Side Members", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Right Side Members", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Basic Active Members", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Standard Plan Reports", true, true,R.drawable.ic_reports); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" Left Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Right Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales BV Matching", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales Bonus Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Premium Plan Reports", true, true,R.drawable.ic_reports); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" Left Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Right Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales BV Matching", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales Bonus Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Repurchase Plan Reports", true, true,R.drawable.ic_reports); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" Repurchase BV Report", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Downline Repurchase Details ", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Repurchase Income", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Repurchase Income Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new MenuModel("Payout Ledger", true,true,R.drawable.ic_payout); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
//        menuModel = new MenuModel("ID Card", true,true,R.drawable.ic_idcard); //Menu of Android Tutorial. No sub menus
//        headerList.add(menuModel);
//
//        if (!menuModel.hasChildren) {
//            childList.put(menuModel, null);
//        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Complaints", true, true,R.drawable.ic_complaints); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" Complaints Registration", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Complaints Status ", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }


    }

    private void populateExpandableList() {

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                if(groupPosition==0) {
//                    Intent Intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(Intent);
//
//
//
//                }

//                if(groupPosition==1) {
//
//                    Intent Intent = new Intent(getApplicationContext(), ShoppyHome.class);
//                       startActivity(Intent);
//                       // DashBoardFragment fragment = new DashBoardFragment();
//                    //                    getSupportFragmentManager().beginTransaction()
//                    //                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
//                    //                    //toolbar.setTitle("Dashboard");
//                    //                    onBackPressed();
//
//
//                }
                if(groupPosition==1) {
                    startActivity(new Intent(getApplicationContext(), Registration.class));


                }
                if (groupPosition==6){
                    startActivity(new Intent(getApplicationContext(), PayoutLedger.class));


                }
//                if (groupPosition==9){
//                    startActivity(new Intent(getApplicationContext(), IDCard.class));
//
//                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if(groupPosition==0) {

                    if (childPosition == 0) {

                        Intent Intent = new Intent(getApplicationContext(), PremiumPlanGenealogy.class);
                        startActivity(Intent);


                    }
                    if (childPosition == 1) {

                        Intent Intent = new Intent(getApplicationContext(), StandardPlanGene.class);
                        startActivity(Intent);


                    }

                }


                if (groupPosition==2){
                        if(childPosition == 0){
                            Intent Intent = new Intent(getApplicationContext(), MyProducts.class);
                            startActivity(Intent);


                        }
                        if(childPosition == 1){
                            Intent Intent = new Intent(getApplicationContext(), FirstPurchaseBVReport.class);
                            startActivity(Intent);


                        }

                        if(childPosition == 2){
                            Intent Intent = new Intent(getApplicationContext(), SponsorsList.class);
                            startActivity(Intent);


                        }
                        if(childPosition == 3){
                            Intent Intent = new Intent(getApplicationContext(), LeftSideMembers.class);
                            startActivity(Intent);


                        }

                        if(childPosition == 4){
                            Intent Intent = new Intent(getApplicationContext(), RightSideMembers.class);
                            startActivity(Intent);

                        }
                        if (childPosition==5){
                            Intent Intent = new Intent(getApplicationContext(), BasicActiveMembers.class);
                            startActivity(Intent);
                        }

                    }
                    if (groupPosition==3) {

                        if(childPosition == 0){
                            Intent Intent = new Intent(getApplicationContext(), StandardLeftSideSales.class);
                            startActivity(Intent);



                        }
                        if(childPosition == 1){

                            Intent Intent = new Intent(getApplicationContext(), StandardRightSideSales.class);
                            startActivity(Intent);


                        }
                        if(childPosition == 2){
                            Intent Intent = new Intent(getApplicationContext(), StandardTeamSalesBVMatching.class);
                            startActivity(Intent);


                        }


                        if(childPosition == 3){
                            Intent Intent = new Intent(getApplicationContext(), StandardTeamSalesBonusDetails.class);
                            startActivity(Intent);


                        }


                    }
                    if (groupPosition==4) {
                        if(childPosition == 0){
                            Intent Intent = new Intent(getApplicationContext(), PremiumLeftSideSales.class);
                            startActivity(Intent);


                        }
                        if(childPosition == 1){
                            Intent Intent = new Intent(getApplicationContext(), PremiumRightSideSales.class);
                            startActivity(Intent);



                        }
                        if(childPosition == 2){
                            Intent Intent = new Intent(getApplicationContext(), PremiumTeamSalesBVMatching.class);
                            startActivity(Intent);


                        }
                        if(childPosition == 3){
                            Intent Intent = new Intent(getApplicationContext(), PremiumTeamSalesBonusDetails.class);
                            startActivity(Intent);


                        }

                    }

                   if (groupPosition==5) {
                       if(childPosition == 0){
                           Intent Intent = new Intent(getApplicationContext(), RepurchaseBVReports.class);
                           startActivity(Intent);



                       }
                       if(childPosition == 1){
                           Intent Intent = new Intent(getApplicationContext(), DownlineRepurchaseDetails.class);
                           startActivity(Intent);



                       }
                       if(childPosition == 2){
                           Intent Intent = new Intent(getApplicationContext(), RepurchaseIncome.class);
                           startActivity(Intent);



                       }
                       if(childPosition == 3){
                           Intent Intent = new Intent(getApplicationContext(), RepurchaseIncomeDetails.class);
                           startActivity(Intent);

                       }

                   }
                   if (groupPosition==7){
                       if (childPosition==0){
                           Intent Intent = new Intent(getApplicationContext(), ComplaintsRegistration.class);
                           startActivity(Intent);

                       }
                       if (childPosition==1){
                           Intent Intent = new Intent(getApplicationContext(), ComplaintStatus.class);
                           startActivity(Intent);

                       }
                   }

                   return false;
            }});

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customview = layoutInflater.inflate(R.layout.logoutpopup, null);
            Button yes = customview.findViewById(R.id.yes);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                    popupWindow2.dismiss();
                }
            });
            Button no = customview.findViewById(R.id.no);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow2.dismiss();
                }
            });
            popupWindow2 = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow2.showAtLocation(mainlayout, Gravity.CENTER, 0, 0);
            popupWindow2.setFocusable(true);
            popupWindow2.update();



            logout();

            return true;
        }
//        else  if (id==R.id.changepsd){
//            startActivity(new Intent(getApplicationContext(),ChangePassword.class));
//        }
//        else if(id==R.id.logout) {
//
//        }

//            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View customview = layoutInflater.inflate(R.layout.logoutpopup, null);
//            Button yes = customview.findViewById(R.id.yes);
//            yes.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    logout();
//                    popupWindow2.dismiss();
//                }
//            });
//            Button no = customview.findViewById(R.id.no);
//            no.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow2.dismiss();
//                }
//            });
//            popupWindow2 = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            popupWindow2.showAtLocation(mainlayout, Gravity.CENTER, 0, 0);
//            popupWindow2.setFocusable(true);
//            popupWindow2.update();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//    private void dp(){
//        String u=sharedPreferences.getString("ID","");
//        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
//        assert u != null;
//        Call<ResponseUserImageView> usercall=api.userImageView(Integer.parseInt(u));
//        usercall.enqueue(new Callback<ResponseUserImageView>() {
//            @Override
//            public void onResponse(Call<ResponseUserImageView> call, Response<ResponseUserImageView> response) {
//           Log.i("onResponse", new Gson().toJson(response.body()));
//                if (response.body().getStatus().equals("1"))
//                    imgpath = response.body().getPath();
//                Glide.with(getApplicationContext())
//                        .load(imgpath)
//                        .into(userdp);  }
//            @Override
//            public void onFailure(Call<ResponseUserImageView> call, Throwable t) {
//
//            }
//        });
//    }

    private void logout(){
        logout_loader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String username=shpref.getString("USERNAME","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogout> usercall=api.Logout(username);
        usercall.enqueue(new Callback<ResponseLogout>() {
            @Override
            public void onResponse(Call<ResponseLogout> call, Response<ResponseLogout> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){
                    logout_loader.setVisibility(View.GONE);

                    //startActivity(new Intent(getApplicationContext(),Login.class));
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                else {
                    logout_loader.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Please exit the Application and Login again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogout> call, Throwable t) {
                logout_loader.setVisibility(View.GONE);

            }
        });
    }

    public void onBackPressed() {
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//        finish();

        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        }
        else{
            FragmentManager fm = getFragmentManager();
            if (fm.getBackStackEntryCount() > 0 && fm.getBackStackEntryCount() != 1) {
                fm.popBackStackImmediate();
            } else if (fm.getBackStackEntryCount() == 1) {

                toolbar.setTitle("DASHBOARD");



                fm.popBackStackImmediate();
            } else {
                toolbar.setTitle("DASHBOARD");
                super.onBackPressed();

            }

           //MainActivity.super.onBackPressed();

        }



    }


}
