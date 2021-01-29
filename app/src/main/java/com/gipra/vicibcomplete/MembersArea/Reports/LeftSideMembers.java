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

public class LeftSideMembers extends AppCompatActivity {
    EditText left_side_member_fromdate,left_side_member_todate;
    Button left_side_member_search;
    RecyclerView recycler_leftside_member;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    ShimmerFrameLayout m_shimmer_left_side_member;

    private List<ListLeftSideMembers> listLeftSideMembers;
    private LeftSideMemberAdapter leftSideMemberAdapter;
    AVLoadingIndicatorView report_leftloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_side_members);
        Toolbar toolbar=findViewById(R.id.leftsidemembers_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        report_leftloader=findViewById(R.id.report_leftloader);
        left_side_member_fromdate=findViewById(R.id.left_side_member_fromdate);
        left_side_member_todate=findViewById(R.id.left_side_member_todate);
        left_side_member_search=findViewById(R.id.left_side_member_search);
        recycler_leftside_member=findViewById(R.id.recycler_leftside_member);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        left_side_member_fromdate.setInputType(InputType.TYPE_NULL);
        left_side_member_fromdate.requestFocus();
        left_side_member_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        left_side_member_todate.setInputType(InputType.TYPE_NULL);
        left_side_member_todate.requestFocus();
        left_side_member_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(LeftSideMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                left_side_member_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(LeftSideMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                left_side_member_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        left_side_member_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchLeftSideMember();

            }
        });

        m_shimmer_left_side_member=findViewById(R.id.m_shimmer_left_side_member);
    }

    private void SearchLeftSideMember() {
        m_shimmer_left_side_member.setVisibility(View.VISIBLE);
        m_shimmer_left_side_member.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=left_side_member_fromdate.getText().toString();
        String tdate=left_side_member_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseLeftSideMembers> usercall=api.SearchLeftSidemembers(32971,fdate,tdate,"left");
        usercall.enqueue(new Callback<ResponseLeftSideMembers>() {
            @Override
            public void onResponse(Call<ResponseLeftSideMembers> call, Response<ResponseLeftSideMembers> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_left_side_member.stopShimmerAnimation();
                    m_shimmer_left_side_member.setVisibility(View.GONE);
                    recycler_leftside_member.setVisibility(View.VISIBLE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseLeftSideMembers responseFirstPurchaseBVReport=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_leftside_member.setLayoutManager(layoutManager);
                    recycler_leftside_member.setHasFixedSize(true);
                    listLeftSideMembers=responseFirstPurchaseBVReport.getData();
                    leftSideMemberAdapter=new LeftSideMemberAdapter(listLeftSideMembers,getApplicationContext());
                    recycler_leftside_member.setAdapter(leftSideMemberAdapter);
                }
                else {
                    m_shimmer_left_side_member.stopShimmerAnimation();
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseLeftSideMembers> call, Throwable t) {
                m_shimmer_left_side_member.stopShimmerAnimation();
            }
        });

    }
}