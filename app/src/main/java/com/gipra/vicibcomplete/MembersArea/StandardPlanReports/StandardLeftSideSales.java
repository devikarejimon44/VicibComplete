package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandardLeftSideSales extends AppCompatActivity {
    RecyclerView recycler_standard_left_side_sales;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_left_side_sales_fromdate,standard_left_side_sales_todate;
    Button search_standard_left_side_sales;
    private List<Standard_ListLeftSideSales> standard_listLeftSideSales;
    private StandardLeftSideSalesAdapter standardLeftSideSalesAdapter;
    ShimmerFrameLayout m_shimmer_st_leftside_sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_left_side_sales);
        Toolbar toolbar=findViewById(R.id.stand_lefttsidesales_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        m_shimmer_st_leftside_sales=findViewById(R.id.m_shimmer_st_leftside_sales);
        recycler_standard_left_side_sales=findViewById(R.id.recycler_standard_left_side_sales);
        standard_left_side_sales_fromdate=findViewById(R.id.standard_left_side_sales_fromdate);
        standard_left_side_sales_todate=findViewById(R.id.standard_left_side_sales_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        standard_left_side_sales_fromdate.setInputType(InputType.TYPE_NULL);
        standard_left_side_sales_fromdate.requestFocus();
        standard_left_side_sales_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        standard_left_side_sales_todate.setInputType(InputType.TYPE_NULL);
        standard_left_side_sales_todate.requestFocus();
        standard_left_side_sales_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(StandardLeftSideSales.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_left_side_sales_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(StandardLeftSideSales.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_left_side_sales_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        search_standard_left_side_sales=findViewById(R.id.search_standard_left_side_sales);
        search_standard_left_side_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {


        m_shimmer_st_leftside_sales.setVisibility(View.VISIBLE);
        m_shimmer_st_leftside_sales.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=standard_left_side_sales_fromdate.getText().toString();
        String tdate=standard_left_side_sales_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseStandardLeftSideSales> usercall=api.SearchStandardLeftSales(1,"01/01/2018","01/11/2020","Right");
        usercall.enqueue(new Callback<ResponseStandardLeftSideSales>() {
            @Override
            public void onResponse(Call<ResponseStandardLeftSideSales> call, Response<ResponseStandardLeftSideSales> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    recycler_standard_left_side_sales.setVisibility(View.VISIBLE);
                    m_shimmer_st_leftside_sales.setVisibility(View.GONE);
                    m_shimmer_st_leftside_sales.stopShimmerAnimation();
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseStandardLeftSideSales responseStandardLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_standard_left_side_sales.setLayoutManager(layoutManager);
                    standard_listLeftSideSales=responseStandardLeftSideSales.getData();
                    standardLeftSideSalesAdapter=new StandardLeftSideSalesAdapter(standard_listLeftSideSales,getApplicationContext());
                    recycler_standard_left_side_sales.setAdapter(standardLeftSideSalesAdapter);
                }
                else {
                    m_shimmer_st_leftside_sales.setVisibility(View.GONE);
                    m_shimmer_st_leftside_sales.stopShimmerAnimation();
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseStandardLeftSideSales> call, Throwable t) {
                m_shimmer_st_leftside_sales.setVisibility(View.GONE);
                m_shimmer_st_leftside_sales.stopShimmerAnimation();
            }
        });


    }
}