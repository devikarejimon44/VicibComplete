package com.gipra.vicibcomplete.MembersArea.Reports;

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

import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstPurchaseBVReport extends AppCompatActivity {
    RecyclerView recyler_first_purchase_bv_report;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText first_purchase_fromdate,first_purchase_todate;
    Button first_purchase_search;
    private List<ListFirstPurchaseBVReport> listFirstPurchaseBVReport;
    private FirstPurchaseBVReportAdapter firstPurchaseBVReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_purchase_b_v_report);
        Toolbar toolbar=findViewById(R.id.firstpurchase_bvreport_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyler_first_purchase_bv_report=findViewById(R.id.recyler_first_purchase_bv_report);
        first_purchase_fromdate=findViewById(R.id.first_purchase_fromdate);
        first_purchase_todate=findViewById(R.id.first_purchase_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        first_purchase_fromdate.setInputType(InputType.TYPE_NULL);
        first_purchase_fromdate.requestFocus();
        first_purchase_fromdate.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        from.show();
        }
        });
        first_purchase_todate.setInputType(InputType.TYPE_NULL);
        first_purchase_todate.requestFocus();
        first_purchase_todate.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        to.show();
        }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(FirstPurchaseBVReport.this, new DatePickerDialog.OnDateSetListener() {

public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        first_purchase_fromdate.setText(dateFormatter.format(newDate.getTime()));
        }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(FirstPurchaseBVReport.this, new DatePickerDialog.OnDateSetListener() {

public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        first_purchase_todate.setText(dateFormatter.format(newDate.getTime()));
        }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        first_purchase_search=findViewById(R.id.first_purchase_search);
        first_purchase_search.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        searchFirstPurchaseReport();
        }
        });

        }
private void searchFirstPurchaseReport() {
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=first_purchase_fromdate.getText().toString();
        String tdate=first_purchase_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(17426,fdate,tdate);
        usercall.enqueue(new Callback<ResponseFirstPurchaseBVReport>() {
@Override
public void onResponse(Call<ResponseFirstPurchaseBVReport> call, Response<ResponseFirstPurchaseBVReport> response) {
        Log.i("onResponse", new Gson().toJson(response.body()));
        if (response.body().getStatus().equals("1")){
        Log.i("onResponse", new Gson().toJson(response.body()));
        ResponseFirstPurchaseBVReport responseFirstPurchaseBVReport=response.body();
final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyler_first_purchase_bv_report.setLayoutManager(layoutManager);
        recyler_first_purchase_bv_report.setHasFixedSize(true);
        listFirstPurchaseBVReport=responseFirstPurchaseBVReport.getData();
        firstPurchaseBVReportAdapter=new FirstPurchaseBVReportAdapter(listFirstPurchaseBVReport,getApplicationContext());
        recyler_first_purchase_bv_report.setAdapter(firstPurchaseBVReportAdapter);
        }
        else {
        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
        }
@Override
public void onFailure(Call<ResponseFirstPurchaseBVReport> call, Throwable t) {

        }
        });

        }
        }