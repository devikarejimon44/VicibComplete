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

public class PremiumTeamSalesBonusDetails extends AppCompatActivity {
    RecyclerView Recycler_premium_team_sales_bonus_details;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText premium_team_sales_bonus_details_fromdate,premium_team_sales_bonus_details_todate;
    Button premium_team_sales_bonus_details_search;
    private List<PremiumListTeamSalesBonusDetails> premiumListTeamSalesBonusDetails;
    private PremiumTeamSalesBonusAdapter premiumTeamSalesBonusAdapter;
    AVLoadingIndicatorView premium_teambonusloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_team_sales_bonus_details);
        ImageView back_gold_teamsalesbonusdetails=findViewById(R.id.back_gold_teamsalesbonusdetails);
        back_gold_teamsalesbonusdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        premium_teambonusloader=findViewById(R.id.premium_teambonusloader);
        Recycler_premium_team_sales_bonus_details=findViewById(R.id.Recycler_premium_team_sales_bonus_details);
        premium_team_sales_bonus_details_fromdate=findViewById(R.id.premium_team_sales_bonus_details_fromdate);
        premium_team_sales_bonus_details_todate=findViewById(R.id.premium_team_sales_bonus_details_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        premium_team_sales_bonus_details_fromdate.setInputType(InputType.TYPE_NULL);
        premium_team_sales_bonus_details_fromdate.requestFocus();
        premium_team_sales_bonus_details_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        premium_team_sales_bonus_details_todate.setInputType(InputType.TYPE_NULL);
        premium_team_sales_bonus_details_todate.requestFocus();
        premium_team_sales_bonus_details_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(PremiumTeamSalesBonusDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_team_sales_bonus_details_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(PremiumTeamSalesBonusDetails.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                premium_team_sales_bonus_details_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        premium_team_sales_bonus_details_search=findViewById(R.id.premium_team_sales_bonus_details_search);
        premium_team_sales_bonus_details_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        premium_teambonusloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=premium_team_sales_bonus_details_fromdate.getText().toString();
        String tdate=premium_team_sales_bonus_details_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponsePremiumTeamSalesBonusDetails> usercall=api.SearchPremiumTeamSalesBonus(1,"20/08/2018","09/11/2020");
        usercall.enqueue(new Callback<ResponsePremiumTeamSalesBonusDetails>() {
            @Override
            public void onResponse(Call<ResponsePremiumTeamSalesBonusDetails> call, Response<ResponsePremiumTeamSalesBonusDetails> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    premium_teambonusloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponsePremiumTeamSalesBonusDetails responsePremiumListLeftSideSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_premium_team_sales_bonus_details.setLayoutManager(layoutManager);
                    premiumListTeamSalesBonusDetails=responsePremiumListLeftSideSales.getData();
                    premiumTeamSalesBonusAdapter=new PremiumTeamSalesBonusAdapter(premiumListTeamSalesBonusDetails,getApplicationContext());
                    Recycler_premium_team_sales_bonus_details.setAdapter(premiumTeamSalesBonusAdapter);
                }
                else {
                    premium_teambonusloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePremiumTeamSalesBonusDetails> call, Throwable t) {
                premium_teambonusloader.setVisibility(View.GONE);
            }
        });

    }
}