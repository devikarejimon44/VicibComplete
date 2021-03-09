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
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardTeamSalesBonusDetails;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBonusAdapter;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardTeamSalesBonusDetails;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.Standard_ListTeamSalesBonusDetails;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class St_TeamSalesBonusDetailsFragment extends Fragment {

    RecyclerView Recycler_standard_team_sales_bonus;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_team_sales_bonus_fromdate,standard_team_sales_bonus_todate;
    Button standard_team_sales_bonus_search;
    private List<Standard_ListTeamSalesBonusDetails> standard_list_teamSalesBvMatching;
    private StandardTeamSalesBonusAdapter standardTeamSalesBVAdapter;
    ShimmerFrameLayout m_shimmer_st_team_sales_bonus_details;

    ImageView nodata_st_team_sales_bonus;

    public St_TeamSalesBonusDetailsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_st__team_sales_bonus_details, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Team Sales Bonus Details");

        m_shimmer_st_team_sales_bonus_details=view.findViewById(R.id.m_shimmer_st_team_sales_bonus_details);
        Recycler_standard_team_sales_bonus=view.findViewById(R.id.Recycler_standard_team_sales_bonus);
        standard_team_sales_bonus_fromdate=view.findViewById(R.id.standard_team_sales_bonus_fromdate);
        standard_team_sales_bonus_todate=view.findViewById(R.id.standard_team_sales_bonus_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        standard_team_sales_bonus_fromdate.setInputType(InputType.TYPE_NULL);
        standard_team_sales_bonus_fromdate.requestFocus();
        standard_team_sales_bonus_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        standard_team_sales_bonus_todate.setInputType(InputType.TYPE_NULL);
        standard_team_sales_bonus_todate.requestFocus();
        standard_team_sales_bonus_todate.setOnClickListener(new View.OnClickListener() {
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
                standard_team_sales_bonus_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_team_sales_bonus_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        standard_team_sales_bonus_search=view.findViewById(R.id.standard_team_sales_bonus_search);
        standard_team_sales_bonus_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
        nodata_st_team_sales_bonus=view.findViewById(R.id.nodata_st_team_sales_bonus);

        return view;
    }


    private void searchFirstPurchaseReport() {
        m_shimmer_st_team_sales_bonus_details.setVisibility(View.VISIBLE);
        m_shimmer_st_team_sales_bonus_details.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=standard_team_sales_bonus_fromdate.getText().toString();
        String tdate=standard_team_sales_bonus_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseStandardTeamSalesBonusDetails> usercall=api.SearchStandardTeamSalesBonus(1,"20/08/2018","19/11/2020");
        usercall.enqueue(new Callback<ResponseStandardTeamSalesBonusDetails>() {
            @Override
            public void onResponse(Call<ResponseStandardTeamSalesBonusDetails> call, Response<ResponseStandardTeamSalesBonusDetails> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_st_team_sales_bonus_details.setVisibility(View.GONE);
                    m_shimmer_st_team_sales_bonus_details.stopShimmerAnimation();
                    Recycler_standard_team_sales_bonus.setVisibility(View.VISIBLE);
                    nodata_st_team_sales_bonus.setVisibility(View.GONE);

                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseStandardTeamSalesBonusDetails responseStandardTeamSalesBonusDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_standard_team_sales_bonus.setLayoutManager(layoutManager);
                    standard_list_teamSalesBvMatching=responseStandardTeamSalesBonusDetails.getData();
                    standardTeamSalesBVAdapter=new StandardTeamSalesBonusAdapter(standard_list_teamSalesBvMatching,getContext());
                    Recycler_standard_team_sales_bonus.setAdapter(standardTeamSalesBVAdapter);
                }
                else {
                    m_shimmer_st_team_sales_bonus_details.setVisibility(View.GONE);
                    m_shimmer_st_team_sales_bonus_details.stopShimmerAnimation();

                    nodata_st_team_sales_bonus.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_st_team_sales_bonus);
                }
            }
            @Override
            public void onFailure(Call<ResponseStandardTeamSalesBonusDetails> call, Throwable t) {
                m_shimmer_st_team_sales_bonus_details.setVisibility(View.GONE);
                m_shimmer_st_team_sales_bonus_details.stopShimmerAnimation();
                Toast.makeText(getContext(), "No Response", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}
