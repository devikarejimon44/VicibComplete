package com.gipra.vicibcomplete.MembersArea.Payout;

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

public class PayoutLedger extends AppCompatActivity {
    RecyclerView Recycler_payoutledger;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText payoutledger_fromdate,payoutledger_todate;
    Button payoutledger_search;
    private List<ListPayoutLedger> listPayoutLedger;
    private PayoutLedgerAdapter payoutLedgerAdapter;
    ShimmerFrameLayout m_shimmer_payout_ledger;
    ImageView nodata_payoutledger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout_ledger);
        Toolbar toolbar=findViewById(R.id.payoutledger_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        m_shimmer_payout_ledger=findViewById(R.id.m_shimmer_payout_ledger);
        Recycler_payoutledger=findViewById(R.id.Recycler_payoutledger);
        payoutledger_fromdate=findViewById(R.id.payoutledger_fromdate);
        payoutledger_todate=findViewById(R.id.payoutledger_todate);
        nodata_payoutledger=findViewById(R.id.nodata_payoutledger);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        payoutledger_fromdate.setInputType(InputType.TYPE_NULL);
        payoutledger_fromdate.requestFocus();
        payoutledger_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        payoutledger_todate.setInputType(InputType.TYPE_NULL);
        payoutledger_todate.requestFocus();
        payoutledger_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(PayoutLedger.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                payoutledger_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(PayoutLedger.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                payoutledger_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        payoutledger_search=findViewById(R.id.payoutledger_search);
        payoutledger_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });

    }
    private void searchFirstPurchaseReport() {
        m_shimmer_payout_ledger.setVisibility(View.VISIBLE);
        m_shimmer_payout_ledger.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=payoutledger_fromdate.getText().toString();
        String tdate=payoutledger_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponsePayoutLedger> usercall=api.SearchPayoutLedger(1,"01/01/2018","01/11/2020");
        usercall.enqueue(new Callback<ResponsePayoutLedger>() {
            @Override
            public void onResponse(Call<ResponsePayoutLedger> call, Response<ResponsePayoutLedger> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_payout_ledger.setVisibility(View.GONE);
                    m_shimmer_payout_ledger.stopShimmerAnimation();
                    Recycler_payoutledger.setVisibility(View.VISIBLE);
                    nodata_payoutledger.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponsePayoutLedger responsePayoutLedger=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_payoutledger.setLayoutManager(layoutManager);
                    listPayoutLedger=responsePayoutLedger.getData();
                    payoutLedgerAdapter=new PayoutLedgerAdapter(listPayoutLedger,getApplicationContext());
                    Recycler_payoutledger.setAdapter(payoutLedgerAdapter);
                }
                else {
                    m_shimmer_payout_ledger.setVisibility(View.GONE);
                    m_shimmer_payout_ledger.stopShimmerAnimation();
                    nodata_payoutledger.setVisibility(View.VISIBLE);
                    Glide.with(getApplicationContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_payoutledger);
                }
            }
            @Override
            public void onFailure(Call<ResponsePayoutLedger> call, Throwable t) {
                m_shimmer_payout_ledger.setVisibility(View.GONE);
                m_shimmer_payout_ledger.stopShimmerAnimation();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}