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

public class DownlineRepurchaseDetails extends AppCompatActivity {
    RecyclerView recycler_downline_repurchase;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText downline_repurtchase_fromdate,downline_repurchase_todate;
    Button downline_repurchase_search;
    private List<ListDownlineRepurchaseDetails> listDownlineRepurchaseDetails;
    private DownlineRepurchaseDetailsAdapter downlineRepurchaseDetailsAdapter;

    ShimmerFrameLayout m_shimmer_downline_purchase_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downline_repurchase_details);
        Toolbar toolbar=findViewById(R.id.downline_repurchase_details_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recycler_downline_repurchase=findViewById(R.id.recycler_downline_repurchase);
        m_shimmer_downline_purchase_details=findViewById(R.id.m_shimmer_downline_purchase_details);

        downline_repurtchase_fromdate=findViewById(R.id.downline_repurtchase_fromdate);
        downline_repurchase_todate=findViewById(R.id.downline_repurchase_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        downline_repurtchase_fromdate.setInputType(InputType.TYPE_NULL);
        downline_repurtchase_fromdate.requestFocus();
        downline_repurtchase_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        downline_repurchase_todate.setInputType(InputType.TYPE_NULL);
        downline_repurchase_todate.requestFocus();
        downline_repurchase_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(DownlineRepurchaseDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                downline_repurtchase_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(DownlineRepurchaseDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                downline_repurchase_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        downline_repurchase_search=findViewById(R.id.downline_repurchase_search);
        downline_repurchase_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        m_shimmer_downline_purchase_details.startShimmerAnimation();
        recycler_downline_repurchase.setVisibility(View.VISIBLE);

        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=downline_repurtchase_fromdate.getText().toString();
        String tdate=downline_repurchase_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseDownlineRepurchaseDetails> usercall=api.SearchDownlineRepurchase(1,"2020-01-09","2020-09-24");
        usercall.enqueue(new Callback<ResponseDownlineRepurchaseDetails>() {
            @Override
            public void onResponse(Call<ResponseDownlineRepurchaseDetails> call, Response<ResponseDownlineRepurchaseDetails> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_downline_purchase_details.stopShimmerAnimation();
                    m_shimmer_downline_purchase_details.setVisibility(View.GONE);

                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseDownlineRepurchaseDetails responseDownlineRepurchaseDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_downline_repurchase.setLayoutManager(layoutManager);
                    recycler_downline_repurchase.setHasFixedSize(true);
                    listDownlineRepurchaseDetails=responseDownlineRepurchaseDetails.getData();
                    downlineRepurchaseDetailsAdapter=new DownlineRepurchaseDetailsAdapter(listDownlineRepurchaseDetails,getApplicationContext());
                    recycler_downline_repurchase.setAdapter(downlineRepurchaseDetailsAdapter);
                }
                else {
                    m_shimmer_downline_purchase_details.setVisibility(View.GONE);
                    m_shimmer_downline_purchase_details.stopShimmerAnimation();
                    ImageView nodata_downline_repurchase_details=findViewById(R.id.nodata_downline_repurchase_details);
                    Glide.with(getApplicationContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_downline_repurchase_details);
                }
            }
            @Override
            public void onFailure(Call<ResponseDownlineRepurchaseDetails> call, Throwable t) {
                m_shimmer_downline_purchase_details.setVisibility(View.GONE);
                m_shimmer_downline_purchase_details.stopShimmerAnimation();

            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}

