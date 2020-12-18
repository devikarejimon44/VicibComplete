package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

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

public class RepurchaseIncome extends AppCompatActivity {
    RecyclerView Recycler_repurchase_income;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText repurchase_income_fromdate,repurchase_income_todate;
    Button repurchase_income_search;
    private List<ListRepurchaseIncome> listRepurchaseIncome;
    private RepurchaseIncomeAdapter repurchaseIncomeAdapter;
    AVLoadingIndicatorView re_incomeloader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repurchase_income);
        ImageView back_repurchaseincome=findViewById(R.id.back_repurchaseincome);
        back_repurchaseincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        re_incomeloader=findViewById(R.id.re_incomeloader);
        Recycler_repurchase_income=findViewById(R.id.Recycler_repurchase_income);
        repurchase_income_fromdate=findViewById(R.id.repurchase_income_fromdate);
        repurchase_income_todate=findViewById(R.id.repurchase_income_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        repurchase_income_fromdate.setInputType(InputType.TYPE_NULL);
        repurchase_income_fromdate.requestFocus();
        repurchase_income_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        repurchase_income_todate.setInputType(InputType.TYPE_NULL);
        repurchase_income_todate.requestFocus();
        repurchase_income_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(RepurchaseIncome.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(RepurchaseIncome.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        repurchase_income_search=findViewById(R.id.repurchase_income_search);
        repurchase_income_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        re_incomeloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=repurchase_income_fromdate.getText().toString();
        String tdate=repurchase_income_fromdate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseRepurchaseIncome> usercall=api.SearchRepurchaseIncome(1,"01/01/2018","01/11/2020");
        usercall.enqueue(new Callback<ResponseRepurchaseIncome>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncome> call, Response<ResponseRepurchaseIncome> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    re_incomeloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseRepurchaseIncome responseStandardLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_repurchase_income.setLayoutManager(layoutManager);
                    listRepurchaseIncome=responseStandardLeftSideSales.getData();
                    repurchaseIncomeAdapter=new RepurchaseIncomeAdapter(listRepurchaseIncome,getApplicationContext());
                    Recycler_repurchase_income.setAdapter(repurchaseIncomeAdapter);
                }
                else {
                    re_incomeloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseRepurchaseIncome> call, Throwable t) {
                re_incomeloader.setVisibility(View.GONE);
            }
        });

    }

}