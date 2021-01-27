package com.gipra.vicibcomplete.MembersArea.Complaints;

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
import com.gipra.vicibcomplete.MembersArea.Payout.ListPayoutLedger;
import com.gipra.vicibcomplete.MembersArea.Payout.PayoutLedger;
import com.gipra.vicibcomplete.MembersArea.Payout.PayoutLedgerAdapter;
import com.gipra.vicibcomplete.MembersArea.Payout.ResponsePayoutLedger;
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


public class ComplaintStatus extends AppCompatActivity {
    RecyclerView Recycler_complaints_status;
    EditText comp_fromdate,comp_todate;
    Button comp_search;
    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    private List<ListComplaintList> listComplaintList;
    private ComplaintsStatusAdapter complaintsStatusAdapter;
    AVLoadingIndicatorView comp_status_loader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
        Toolbar toolbar=findViewById(R.id.compliant_status_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        comp_status_loader=findViewById(R.id.comp_status_loader);
        comp_fromdate=findViewById(R.id.comp_fromdate);
        comp_todate=findViewById(R.id.comp_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        comp_fromdate.setInputType(InputType.TYPE_NULL);
        comp_fromdate.requestFocus();
        comp_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        comp_todate.setInputType(InputType.TYPE_NULL);
        comp_todate.requestFocus();
        comp_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(ComplaintStatus.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                comp_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(ComplaintStatus.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                comp_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        Recycler_complaints_status=findViewById(R.id.Recycler_complaints_status);
        comp_search=findViewById(R.id.comp_search);
        comp_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComplaintsList();
            }
        });
    }
    private void ComplaintsList() {
        comp_status_loader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=comp_fromdate.getText().toString();
        String tdate=comp_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseComplaintsList> usercall=api.ComplaintsList(1,"2021-01-21","2021-01-22");
        usercall.enqueue(new Callback<ResponseComplaintsList>() {
            @Override
            public void onResponse(Call<ResponseComplaintsList> call, Response<ResponseComplaintsList> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    comp_status_loader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseComplaintsList responseComplaintsList=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_complaints_status.setLayoutManager(layoutManager);
                    listComplaintList=responseComplaintsList.getData();
                    complaintsStatusAdapter=new ComplaintsStatusAdapter(listComplaintList,getApplicationContext());
                    Recycler_complaints_status.setAdapter(complaintsStatusAdapter);
                }
                else {
                    comp_status_loader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseComplaintsList> call, Throwable t) {
                comp_status_loader.setVisibility(View.GONE);
            }
        });
    }
}