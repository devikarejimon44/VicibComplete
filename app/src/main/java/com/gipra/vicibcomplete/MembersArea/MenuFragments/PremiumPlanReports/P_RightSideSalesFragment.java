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
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumLeftSideSalesAdapter;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumListLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.PremiumRightSideSales;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.ResponsePremiumListLeftSideSales;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class P_RightSideSalesFragment extends Fragment {

    RecyclerView Recycler_premium_right_side_sales;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText premium_right_side_sales_fromdate,premium_right_side_sales_todate;
    Button premium_right_side_sales_search;
    private List<PremiumListLeftSideSales> premiumListLeftSideSales;
    private PremiumLeftSideSalesAdapter premiumLeftSideSalesAdapter;
    ShimmerFrameLayout m_shimmer_pr_rightside_sales;
    ImageView nodata_pr_rightside_sales;

    public P_RightSideSalesFragment() {
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
        View view= inflater.inflate(R.layout.fragment_p__right_side_sales, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Right Side Sales");


        nodata_pr_rightside_sales=view.findViewById(R.id.nodata_pr_rightside_sales);


        m_shimmer_pr_rightside_sales=view.findViewById(R.id.m_shimmer_pr_rightside_sales);
        Recycler_premium_right_side_sales=view.findViewById(R.id.Recycler_premium_right_side_sales);
        premium_right_side_sales_fromdate=view.findViewById(R.id.premium_right_side_sales_fromdate);
        premium_right_side_sales_todate=view.findViewById(R.id.premium_right_side_sales_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        premium_right_side_sales_fromdate.setInputType(InputType.TYPE_NULL);
        premium_right_side_sales_fromdate.requestFocus();
        premium_right_side_sales_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        premium_right_side_sales_todate.setInputType(InputType.TYPE_NULL);
        premium_right_side_sales_todate.requestFocus();
        premium_right_side_sales_todate.setOnClickListener(new View.OnClickListener() {
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
                premium_right_side_sales_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_right_side_sales_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        premium_right_side_sales_search=view.findViewById(R.id.premium_right_side_sales_search);
        premium_right_side_sales_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });


        return view;
    }
    private void searchFirstPurchaseReport() {
        m_shimmer_pr_rightside_sales.setVisibility(View.VISIBLE);
        m_shimmer_pr_rightside_sales.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=premium_right_side_sales_fromdate.getText().toString();
        String tdate=premium_right_side_sales_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponsePremiumListLeftSideSales> usercall=api.SearchPremiumLeftSales(1,"01/01/2020","01/09/2020","Right");
        usercall.enqueue(new Callback<ResponsePremiumListLeftSideSales>() {
            @Override
            public void onResponse(Call<ResponsePremiumListLeftSideSales> call, Response<ResponsePremiumListLeftSideSales> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_pr_rightside_sales.setVisibility(View.GONE);
                    m_shimmer_pr_rightside_sales.stopShimmerAnimation();
                    Recycler_premium_right_side_sales.setVisibility(View.VISIBLE);
                    nodata_pr_rightside_sales.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponsePremiumListLeftSideSales responsePremiumListLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_premium_right_side_sales.setLayoutManager(layoutManager);
                    premiumListLeftSideSales=responsePremiumListLeftSideSales.getData();
                    premiumLeftSideSalesAdapter=new PremiumLeftSideSalesAdapter(premiumListLeftSideSales,getContext());
                    Recycler_premium_right_side_sales.setAdapter(premiumLeftSideSalesAdapter);
                }
                else {
                    m_shimmer_pr_rightside_sales.setVisibility(View.GONE);
                    m_shimmer_pr_rightside_sales.stopShimmerAnimation();
                    nodata_pr_rightside_sales.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_pr_rightside_sales);
                }
            }
            @Override
            public void onFailure(Call<ResponsePremiumListLeftSideSales> call, Throwable t) {
                m_shimmer_pr_rightside_sales.setVisibility(View.GONE);
                m_shimmer_pr_rightside_sales.stopShimmerAnimation();

            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}
