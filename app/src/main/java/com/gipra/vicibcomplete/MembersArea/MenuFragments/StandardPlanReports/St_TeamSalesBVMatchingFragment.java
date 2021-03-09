package com.gipra.vicibcomplete.MembersArea.MenuFragments.StandardPlanReports;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBVAdapter;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.Standard_List_TeamSalesBvMatching;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class St_TeamSalesBVMatchingFragment extends Fragment {
    RecyclerView Recycler_standard_teamsales_bv_matching;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_teamsales_bv_matching_fromdate,standard_teamsales_bv_matching_todate;
    Button standard_teamsales_bv_matching_search;
    private List<Standard_List_TeamSalesBvMatching> standard_list_teamSalesBvMatching;
    private StandardTeamSalesBVAdapter standardTeamSalesBVAdapter;
    ShimmerFrameLayout m_shimmer_st_team_sales_bvmatching;
    ImageView nodata_st_team_salesbv_matching;

    public St_TeamSalesBVMatchingFragment() {
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
      View view= inflater.inflate(R.layout.fragment_st__team_sales_b_v_matching, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Team Sales BV Matching");

        m_shimmer_st_team_sales_bvmatching=view.findViewById(R.id.m_shimmer_st_team_sales_bvmatching);
        Recycler_standard_teamsales_bv_matching=view.findViewById(R.id.Recycler_standard_teamsales_bv_matching);
        standard_teamsales_bv_matching_fromdate=view.findViewById(R.id.standard_teamsales_bv_matching_fromdate);
        standard_teamsales_bv_matching_todate=view.findViewById(R.id.standard_teamsales_bv_matching_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        standard_teamsales_bv_matching_fromdate.setInputType(InputType.TYPE_NULL);
        standard_teamsales_bv_matching_fromdate.requestFocus();
        standard_teamsales_bv_matching_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        standard_teamsales_bv_matching_todate.setInputType(InputType.TYPE_NULL);
        standard_teamsales_bv_matching_todate.requestFocus();
        standard_teamsales_bv_matching_todate.setOnClickListener(new View.OnClickListener() {
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
                standard_teamsales_bv_matching_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_teamsales_bv_matching_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        standard_teamsales_bv_matching_search=view.findViewById(R.id.standard_teamsales_bv_matching_search);
        standard_teamsales_bv_matching_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });


        nodata_st_team_salesbv_matching=view.findViewById(R.id.nodata_st_team_salesbv_matching);

        return view;
    }
    private void searchFirstPurchaseReport() {
        m_shimmer_st_team_sales_bvmatching.setVisibility(View.VISIBLE);
        m_shimmer_st_team_sales_bvmatching.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=standard_teamsales_bv_matching_fromdate.getText().toString();
        String tdate=standard_teamsales_bv_matching_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseStandardTeamSalesBVMatching> usercall=api.SearchStandardTeamSalesBV(1,"09/01/2018","09/09/2020");
        usercall.enqueue(new Callback<ResponseStandardTeamSalesBVMatching>() {
            @Override
            public void onResponse(Call<ResponseStandardTeamSalesBVMatching> call, Response<ResponseStandardTeamSalesBVMatching> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_st_team_sales_bvmatching.setVisibility(View.GONE);
                    m_shimmer_st_team_sales_bvmatching.stopShimmerAnimation();
                    Recycler_standard_teamsales_bv_matching.setVisibility(View.VISIBLE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    nodata_st_team_salesbv_matching.setVisibility(View.GONE);

                    ResponseStandardTeamSalesBVMatching responseStandardTeamSalesBVMatching=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_standard_teamsales_bv_matching.setLayoutManager(layoutManager);
                    standard_list_teamSalesBvMatching=responseStandardTeamSalesBVMatching.getData();
                    standardTeamSalesBVAdapter=new StandardTeamSalesBVAdapter(standard_list_teamSalesBvMatching,getContext());
                    Recycler_standard_teamsales_bv_matching.setAdapter(standardTeamSalesBVAdapter);

                }
                else {
                    m_shimmer_st_team_sales_bvmatching.setVisibility(View.GONE);
                    m_shimmer_st_team_sales_bvmatching.stopShimmerAnimation();
                    nodata_st_team_salesbv_matching.setVisibility(View.VISIBLE);

                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_st_team_salesbv_matching);
                }
            }
            @Override
            public void onFailure(Call<ResponseStandardTeamSalesBVMatching> call, Throwable t) {
                m_shimmer_st_team_sales_bvmatching.setVisibility(View.GONE);
                m_shimmer_st_team_sales_bvmatching.stopShimmerAnimation();
                Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}