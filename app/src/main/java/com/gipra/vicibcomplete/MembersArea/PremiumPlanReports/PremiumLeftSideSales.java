package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

public class PremiumLeftSideSales extends AppCompatActivity {
    RecyclerView Recycler_premium_left_side_sales;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText premium_left_side_sales_fromdate,premium_left_side_sales_todate;
    Button premium_left_side_sales_search;
    private List<PremiumListLeftSideSales> premiumListLeftSideSales;
    private PremiumLeftSideSalesAdapter premiumLeftSideSalesAdapter;
    AVLoadingIndicatorView premium_leftloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_left_side_sales);
        ImageView back_gold_leftsidesales=findViewById(R.id.back_gold_leftsidesales);
        back_gold_leftsidesales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        premium_leftloader=findViewById(R.id.premium_leftloader);

        Recycler_premium_left_side_sales=findViewById(R.id.Recycler_premium_left_side_sales);
        premium_left_side_sales_fromdate=findViewById(R.id.premium_left_side_sales_fromdate);
        premium_left_side_sales_todate=findViewById(R.id.premium_left_side_sales_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        premium_left_side_sales_fromdate.setInputType(InputType.TYPE_NULL);
        premium_left_side_sales_fromdate.requestFocus();
        premium_left_side_sales_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        premium_left_side_sales_todate.setInputType(InputType.TYPE_NULL);
        premium_left_side_sales_todate.requestFocus();
        premium_left_side_sales_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(PremiumLeftSideSales.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_left_side_sales_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(PremiumLeftSideSales.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_left_side_sales_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        premium_left_side_sales_search=findViewById(R.id.premium_left_side_sales_search);
        premium_left_side_sales_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        premium_leftloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=premium_left_side_sales_fromdate.getText().toString();
        String tdate=premium_left_side_sales_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponsePremiumListLeftSideSales> usercall=api.SearchPremiumLeftSales(1,"01/01/2020","01/09/2020","Left");
        usercall.enqueue(new Callback<ResponsePremiumListLeftSideSales>() {
            @Override
            public void onResponse(Call<ResponsePremiumListLeftSideSales> call, Response<ResponsePremiumListLeftSideSales> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    premium_leftloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponsePremiumListLeftSideSales responsePremiumListLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_premium_left_side_sales.setLayoutManager(layoutManager);
                    premiumListLeftSideSales=responsePremiumListLeftSideSales.getData();
                    premiumLeftSideSalesAdapter=new PremiumLeftSideSalesAdapter(premiumListLeftSideSales,getApplicationContext());
                    Recycler_premium_left_side_sales.setAdapter(premiumLeftSideSalesAdapter);
                }
                else {
                    premium_leftloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePremiumListLeftSideSales> call, Throwable t) {
                premium_leftloader.setVisibility(View.GONE);
            }
        });

    }
}