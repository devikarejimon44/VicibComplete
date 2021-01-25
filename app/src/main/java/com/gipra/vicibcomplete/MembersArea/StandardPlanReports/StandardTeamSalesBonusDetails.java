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

public class StandardTeamSalesBonusDetails extends AppCompatActivity {
    RecyclerView Recycler_standard_team_sales_bonus;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_team_sales_bonus_fromdate,standard_team_sales_bonus_todate;
    Button standard_team_sales_bonus_search;
    private List<Standard_ListTeamSalesBonusDetails> standard_list_teamSalesBvMatching;
    private StandardTeamSalesBonusAdapter standardTeamSalesBVAdapter;
    AVLoadingIndicatorView st_salesloader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_team_sales_bonus_details);
        Toolbar toolbar=findViewById(R.id.stand_teamsalesbonusdetails_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        st_salesloader=findViewById(R.id.st_salesloader);
        Recycler_standard_team_sales_bonus=findViewById(R.id.Recycler_standard_team_sales_bonus);
        standard_team_sales_bonus_fromdate=findViewById(R.id.standard_team_sales_bonus_fromdate);
        standard_team_sales_bonus_todate=findViewById(R.id.standard_team_sales_bonus_todate);

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
        from = new DatePickerDialog(StandardTeamSalesBonusDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_team_sales_bonus_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(StandardTeamSalesBonusDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_team_sales_bonus_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        standard_team_sales_bonus_search=findViewById(R.id.standard_team_sales_bonus_search);
        standard_team_sales_bonus_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        st_salesloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
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
                    st_salesloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseStandardTeamSalesBonusDetails responseStandardTeamSalesBonusDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_standard_team_sales_bonus.setLayoutManager(layoutManager);
                    standard_list_teamSalesBvMatching=responseStandardTeamSalesBonusDetails.getData();
                    standardTeamSalesBVAdapter=new StandardTeamSalesBonusAdapter(standard_list_teamSalesBvMatching,getApplicationContext());
                    Recycler_standard_team_sales_bonus.setAdapter(standardTeamSalesBVAdapter);
                }
                else {
                    st_salesloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseStandardTeamSalesBonusDetails> call, Throwable t) {
                st_salesloader.setVisibility(View.GONE);
            }
        });

    }
}
