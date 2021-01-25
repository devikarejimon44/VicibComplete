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

public class StandardTeamSalesBVMatching extends AppCompatActivity {
    RecyclerView Recycler_standard_teamsales_bv_matching;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_teamsales_bv_matching_fromdate,standard_teamsales_bv_matching_todate;
    Button standard_teamsales_bv_matching_search;
    private List<Standard_List_TeamSalesBvMatching> standard_list_teamSalesBvMatching;
    private StandardTeamSalesBVAdapter standardTeamSalesBVAdapter;
    AVLoadingIndicatorView st_bvmatchingloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_team_sales_b_v_matching);
        Toolbar toolbar=findViewById(R.id.stand_teamsalesbvmatching_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        st_bvmatchingloader=findViewById(R.id.st_bvmatchingloader);
        Recycler_standard_teamsales_bv_matching=findViewById(R.id.Recycler_standard_teamsales_bv_matching);
        standard_teamsales_bv_matching_fromdate=findViewById(R.id.standard_teamsales_bv_matching_fromdate);
        standard_teamsales_bv_matching_todate=findViewById(R.id.standard_teamsales_bv_matching_todate);

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
        from = new DatePickerDialog(StandardTeamSalesBVMatching.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_teamsales_bv_matching_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(StandardTeamSalesBVMatching.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_teamsales_bv_matching_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        standard_teamsales_bv_matching_search=findViewById(R.id.standard_teamsales_bv_matching_search);
        standard_teamsales_bv_matching_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        st_bvmatchingloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
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
                    st_bvmatchingloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseStandardTeamSalesBVMatching responseStandardTeamSalesBVMatching=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_standard_teamsales_bv_matching.setLayoutManager(layoutManager);
                    standard_list_teamSalesBvMatching=responseStandardTeamSalesBVMatching.getData();
                    standardTeamSalesBVAdapter=new StandardTeamSalesBVAdapter(standard_list_teamSalesBvMatching,getApplicationContext());
                    Recycler_standard_teamsales_bv_matching.setAdapter(standardTeamSalesBVAdapter);
                }
                else {
                    st_bvmatchingloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseStandardTeamSalesBVMatching> call, Throwable t) {
                st_bvmatchingloader.setVisibility(View.GONE);
            }
        });

    }
}