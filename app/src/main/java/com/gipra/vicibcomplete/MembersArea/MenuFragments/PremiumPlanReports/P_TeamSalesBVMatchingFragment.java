package com.gipra.vicibcomplete.MembersArea.MenuFragments.PremiumPlanReports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumListTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumTeamSalesBvAdapter;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.ResponsePremiumTeamSalesBVMatching;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class P_TeamSalesBVMatchingFragment extends Fragment {
    RecyclerView Recycler_premium_team_salesbv_matching;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText premium_team_salesbv_matching_fromdate,premium_team_salesbv_matching_todate;
    Button premium_team_salesbv_matching_search;
    private List<PremiumListTeamSalesBVMatching> premiumListLeftSideSales;
    private PremiumTeamSalesBvAdapter premiumTeamSalesBvAdapter;
    ShimmerFrameLayout m_shimmer_pr_team_sales_bvmatching;
    ImageView nodata_pr_team_salesbv_matching;

    public P_TeamSalesBVMatchingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_p__team_sales_b_v_matching, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Team Sales BV Matching");

        m_shimmer_pr_team_sales_bvmatching=view.findViewById(R.id.m_shimmer_pr_team_sales_bvmatching);
        Recycler_premium_team_salesbv_matching=view.findViewById(R.id.Recycler_premium_team_salesbv_matching);
        premium_team_salesbv_matching_fromdate=view.findViewById(R.id.premium_team_salesbv_matching_fromdate);
        premium_team_salesbv_matching_todate=view.findViewById(R.id.premium_team_salesbv_matching_todate);

        nodata_pr_team_salesbv_matching=view.findViewById(R.id.nodata_pr_team_salesbv_matching);


        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        premium_team_salesbv_matching_fromdate.setInputType(InputType.TYPE_NULL);
        premium_team_salesbv_matching_fromdate.requestFocus();
        premium_team_salesbv_matching_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        premium_team_salesbv_matching_todate.setInputType(InputType.TYPE_NULL);
        premium_team_salesbv_matching_todate.requestFocus();
        premium_team_salesbv_matching_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_team_salesbv_matching_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_team_salesbv_matching_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        premium_team_salesbv_matching_search=view.findViewById(R.id.premium_team_salesbv_matching_search);
        premium_team_salesbv_matching_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
       return view;
    }
 private void searchFirstPurchaseReport() {
    m_shimmer_pr_team_sales_bvmatching.setVisibility(View.VISIBLE);
    m_shimmer_pr_team_sales_bvmatching.startShimmerAnimation();
    SharedPreferences shpref;
    shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
    String id=shpref.getString("ID","");
    Log.e("id",id);
    String fdate=premium_team_salesbv_matching_fromdate.getText().toString();
    String tdate=premium_team_salesbv_matching_todate.getText().toString();
    ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
    //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
    Call<ResponsePremiumTeamSalesBVMatching> usercall=api.SearchPremiumTeamSalesBV(1,"09/01/2018","09/09/2020");
    usercall.enqueue(new Callback<ResponsePremiumTeamSalesBVMatching>() {
        @Override
        public void onResponse(Call<ResponsePremiumTeamSalesBVMatching> call, Response<ResponsePremiumTeamSalesBVMatching> response) {
            Log.i("onResponse", new Gson().toJson(response.body()));
            if (response.body().getStatus().equals("1")){
                m_shimmer_pr_team_sales_bvmatching.setVisibility(View.GONE);
                m_shimmer_pr_team_sales_bvmatching.stopShimmerAnimation();
                Recycler_premium_team_salesbv_matching.setVisibility(View.VISIBLE);
                nodata_pr_team_salesbv_matching.setVisibility(View.GONE);

                Log.i("onResponse", new Gson().toJson(response.body()));
                ResponsePremiumTeamSalesBVMatching responsePremiumTeamSalesBVMatching=response.body();
                final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                Recycler_premium_team_salesbv_matching.setLayoutManager(layoutManager);
                premiumListLeftSideSales=responsePremiumTeamSalesBVMatching.getData();
                premiumTeamSalesBvAdapter=new PremiumTeamSalesBvAdapter(premiumListLeftSideSales,getContext());
                Recycler_premium_team_salesbv_matching.setAdapter(premiumTeamSalesBvAdapter);
            }
            else {
                m_shimmer_pr_team_sales_bvmatching.setVisibility(View.GONE);
                m_shimmer_pr_team_sales_bvmatching.stopShimmerAnimation();
                nodata_pr_team_salesbv_matching.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(R.drawable.nodatafound)
                        .into(nodata_pr_team_salesbv_matching);
            }
        }
        @Override
        public void onFailure(Call<ResponsePremiumTeamSalesBVMatching> call, Throwable t) {
            m_shimmer_pr_team_sales_bvmatching.setVisibility(View.GONE);
            m_shimmer_pr_team_sales_bvmatching.stopShimmerAnimation();
        }
    });

}
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}