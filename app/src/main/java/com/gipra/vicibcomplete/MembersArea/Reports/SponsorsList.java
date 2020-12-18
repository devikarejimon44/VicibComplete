package com.gipra.vicibcomplete.MembersArea.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorsList extends AppCompatActivity {
    RecyclerView recycler_sponsorlist;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    private List<ListSponsorsList> listSponsorsList;
    private SponsorListAdapter sponsorListAdapter;
    AVLoadingIndicatorView report_sponsorloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors_list);
        ImageView back_sponsorslist=findViewById(R.id.back_sponsorslist);
        back_sponsorslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        report_sponsorloader=findViewById(R.id.report_sponsorloader);
        recycler_sponsorlist=findViewById(R.id.recycler_sponsorlist);
        SearchSponsorList();



    }
    private void SearchSponsorList() {
        report_sponsorloader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
       // Call<ResponseSponsorsList> usercall=api.searchsponsorlist(Integer.parseInt(id));
        Call<ResponseSponsorsList> usercall=api.searchsponsorlist(1);
        usercall.enqueue(new Callback<ResponseSponsorsList>() {
            @Override
            public void onResponse(Call<ResponseSponsorsList> call, Response<ResponseSponsorsList> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    report_sponsorloader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseSponsorsList responseSponsorsList=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_sponsorlist.setLayoutManager(layoutManager);
                    recycler_sponsorlist.setHasFixedSize(true);
                    listSponsorsList=responseSponsorsList.getData();
                    sponsorListAdapter=new SponsorListAdapter(listSponsorsList,getApplicationContext());
                    recycler_sponsorlist.setAdapter(sponsorListAdapter);
                }
                else {
                    report_sponsorloader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseSponsorsList> call, Throwable t) {
                report_sponsorloader.setVisibility(View.GONE);
            }
        });

    }


    }
