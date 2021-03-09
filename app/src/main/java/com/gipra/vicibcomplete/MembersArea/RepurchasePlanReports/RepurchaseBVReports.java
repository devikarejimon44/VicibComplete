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


import com.bumptech.glide.Glide;
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

public class RepurchaseBVReports extends AppCompatActivity {

    RecyclerView Recycler_repurchase_bv_reports;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText repurchase_bv_reports_fromdate,repurchase_bv_reports_todate;
    Button repurchase_bv_reports_search;
    private List<ListRepurchaseBVReport> listRepurchaseBVReport;
    private RepurchaseBVReportAdapter repurchaseBVReportAdapter;
    ShimmerFrameLayout m_shimmer_repurchase_bvreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repurchase_b_v_reports);
        Toolbar toolbar=findViewById(R.id.repurchasebvreport_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        m_shimmer_repurchase_bvreport=findViewById(R.id.m_shimmer_repurchase_bvreport);
        Recycler_repurchase_bv_reports=findViewById(R.id.Recycler_repurchase_bv_reports);
        repurchase_bv_reports_fromdate=findViewById(R.id.repurchase_bv_reports_fromdate);
        repurchase_bv_reports_todate=findViewById(R.id.repurchase_bv_reports_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        repurchase_bv_reports_fromdate.setInputType(InputType.TYPE_NULL);
        repurchase_bv_reports_fromdate.requestFocus();
        repurchase_bv_reports_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        repurchase_bv_reports_todate.setInputType(InputType.TYPE_NULL);
        repurchase_bv_reports_todate.requestFocus();
        repurchase_bv_reports_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(RepurchaseBVReports.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_bv_reports_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(RepurchaseBVReports.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_bv_reports_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        repurchase_bv_reports_search=findViewById(R.id.repurchase_bv_reports_search);
        repurchase_bv_reports_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        m_shimmer_repurchase_bvreport.setVisibility(View.VISIBLE);
        m_shimmer_repurchase_bvreport.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=repurchase_bv_reports_fromdate.getText().toString();
        String tdate=repurchase_bv_reports_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseRepurchaseBvReport> usercall=api.SearchRepurchaseBVReport(16,"20/05/2020","09/11/2020");
        usercall.enqueue(new Callback<ResponseRepurchaseBvReport>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseBvReport> call, Response<ResponseRepurchaseBvReport> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_repurchase_bvreport.setVisibility(View.GONE);
                    m_shimmer_repurchase_bvreport.stopShimmerAnimation();
                    Recycler_repurchase_bv_reports.setVisibility(View.VISIBLE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseRepurchaseBvReport responseStandardLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_repurchase_bv_reports.setLayoutManager(layoutManager);
                    listRepurchaseBVReport=responseStandardLeftSideSales.getData();
                    repurchaseBVReportAdapter=new RepurchaseBVReportAdapter(listRepurchaseBVReport,getApplicationContext());
                    Recycler_repurchase_bv_reports.setAdapter(repurchaseBVReportAdapter);
                }
                else {
                    m_shimmer_repurchase_bvreport.setVisibility(View.GONE);
                    m_shimmer_repurchase_bvreport.stopShimmerAnimation();
                    ImageView nodata_repurchase_bv_reports=findViewById(R.id.nodata_repurchase_bv_reports);
                    Glide.with(getApplicationContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_repurchase_bv_reports);


                }
            }
            @Override
            public void onFailure(Call<ResponseRepurchaseBvReport> call, Throwable t) {
                m_shimmer_repurchase_bvreport.setVisibility(View.GONE);
                m_shimmer_repurchase_bvreport.stopShimmerAnimation();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}