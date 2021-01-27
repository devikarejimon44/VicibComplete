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
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
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

public class RepurchaseIncomeDetails extends AppCompatActivity {
    RecyclerView Recycler_repurchase_income_details;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText repurchase_income_details_fromdate,repurchase_income_details_todate;
    Button repurchase_income_details_search;
    private List<ListRepurchaseIncomeDetails> listRepurchaseIncomeDetails;
    private RepurchaseIncomeDetailsAdapter repurchaseIncomeDetailsAdapter;
    AVLoadingIndicatorView re_incomedetailsloader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repurchase_income_details);
        Toolbar toolbar=findViewById(R.id.repurchaseincomedetails_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        re_incomedetailsloader=findViewById(R.id.re_incomedetailsloader);
        Recycler_repurchase_income_details=findViewById(R.id.Recycler_repurchase_income_details);
        repurchase_income_details_fromdate=findViewById(R.id.repurchase_income_details_fromdate);
        repurchase_income_details_todate=findViewById(R.id.repurchase_income_details_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        repurchase_income_details_fromdate.setInputType(InputType.TYPE_NULL);
        repurchase_income_details_fromdate.requestFocus();
        repurchase_income_details_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        repurchase_income_details_todate.setInputType(InputType.TYPE_NULL);
        repurchase_income_details_todate.requestFocus();
        repurchase_income_details_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(RepurchaseIncomeDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_details_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(RepurchaseIncomeDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_details_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        repurchase_income_details_search=findViewById(R.id.repurchase_income_details_search);
        repurchase_income_details_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        re_incomedetailsloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=repurchase_income_details_fromdate.getText().toString();
        String tdate=repurchase_income_details_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseRepurchaseIncomeDetails> usercall=api.SearchRepurchaseIncomeDetails(1,"10/10/2020","10/11/2020");
        usercall.enqueue(new Callback<ResponseRepurchaseIncomeDetails>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncomeDetails> call, Response<ResponseRepurchaseIncomeDetails> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    re_incomedetailsloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseRepurchaseIncomeDetails responseRepurchaseIncomeDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_repurchase_income_details.setLayoutManager(layoutManager);
                    listRepurchaseIncomeDetails=responseRepurchaseIncomeDetails.getData();
                    repurchaseIncomeDetailsAdapter=new RepurchaseIncomeDetailsAdapter(listRepurchaseIncomeDetails,getApplicationContext());
                    Recycler_repurchase_income_details.setAdapter(repurchaseIncomeDetailsAdapter);
                }
                else {
                    re_incomedetailsloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseRepurchaseIncomeDetails> call, Throwable t) {
                re_incomedetailsloader.setVisibility(View.GONE);
            }
        });

    }

}