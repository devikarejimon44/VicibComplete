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

public class RightSideMembers extends AppCompatActivity {
    EditText right_side_members_fromdate,right_side_members_todate;
    Button right_side_members_search;
    RecyclerView recycler_right_side_members;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    private List<ListLeftSideMembers> listLeftSideMembers;
    private LeftSideMemberAdapter leftSideMemberAdapter;
    AVLoadingIndicatorView report_rightloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_side_members);
        ImageView back_rightsidemembers=findViewById(R.id.back_rightsidemembers);
        back_rightsidemembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        report_rightloader=findViewById(R.id.report_rightloader);
        right_side_members_fromdate=findViewById(R.id.right_side_members_fromdate);
        right_side_members_todate=findViewById(R.id.right_side_members_todate);
        right_side_members_search=findViewById(R.id.right_side_members_search);
        recycler_right_side_members=findViewById(R.id.recycler_right_side_members);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        right_side_members_fromdate.setInputType(InputType.TYPE_NULL);
        right_side_members_fromdate.requestFocus();
        right_side_members_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        right_side_members_todate.setInputType(InputType.TYPE_NULL);
        right_side_members_todate.requestFocus();
        right_side_members_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(RightSideMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                right_side_members_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(RightSideMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                right_side_members_todate.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        right_side_members_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchLeftSideMember();

            }
        });
    }

    private void SearchLeftSideMember() {
        report_rightloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=right_side_members_fromdate.getText().toString();
        String tdate=right_side_members_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseLeftSideMembers> usercall=api.SearchLeftSidemembers(32971,fdate,tdate,"right");
        usercall.enqueue(new Callback<ResponseLeftSideMembers>() {
            @Override
            public void onResponse(Call<ResponseLeftSideMembers> call, Response<ResponseLeftSideMembers> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    report_rightloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseLeftSideMembers responseFirstPurchaseBVReport=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_right_side_members.setLayoutManager(layoutManager);
                    recycler_right_side_members.setHasFixedSize(true);
                    listLeftSideMembers=responseFirstPurchaseBVReport.getData();
                    leftSideMemberAdapter=new LeftSideMemberAdapter(listLeftSideMembers,getApplicationContext());
                    recycler_right_side_members.setAdapter(leftSideMemberAdapter);
                }
                else {
                    report_rightloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseLeftSideMembers> call, Throwable t) {
                report_rightloader.setVisibility(View.GONE);
            }
        });

    }
}