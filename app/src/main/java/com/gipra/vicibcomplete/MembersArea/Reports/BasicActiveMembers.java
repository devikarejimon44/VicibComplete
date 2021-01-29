package com.gipra.vicibcomplete.MembersArea.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BasicActiveMembers extends AppCompatActivity {
    EditText basic_active__fromdate,basic_active_todate;
    Button basic_active_search;
    RecyclerView recyler_basic_active;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    private List<ListBasicActiveMembers> listBasicActiveMembers;
    private BasicActiveAdapter basicActiveAdapter;

    ShimmerFrameLayout m_shimmer_basic_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_active_members);
        Toolbar toolbar=findViewById(R.id.basic_acive_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        basic_active__fromdate=findViewById(R.id.basic_active__fromdate);
        basic_active_todate=findViewById(R.id.basic_active_todate);
        recyler_basic_active=findViewById(R.id.recyler_basic_active);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        basic_active__fromdate.setInputType(InputType.TYPE_NULL);
        basic_active__fromdate.requestFocus();
        basic_active__fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        basic_active_todate.setInputType(InputType.TYPE_NULL);
        basic_active_todate.requestFocus();
        basic_active_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(BasicActiveMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                basic_active__fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(BasicActiveMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                basic_active_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        basic_active_search=findViewById(R.id.basic_active_search);
        basic_active_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchBasicActive();

            }
        });
        m_shimmer_basic_active=findViewById(R.id.m_shimmer_basic_active);
    }

    private void SearchBasicActive() {
        m_shimmer_basic_active.setVisibility(View.VISIBLE);
        m_shimmer_basic_active.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=basic_active__fromdate.getText().toString();
        String tdate=basic_active_todate.getText().toString();

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
    //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
    Call<ResponseBasicActiveMemebers> usercall=api.SearchBasicActive(34898,"2021/01/28","2021/01/28");
        usercall.enqueue(new Callback<ResponseBasicActiveMemebers>() {
        @Override
        public void onResponse(Call<ResponseBasicActiveMemebers> call, Response<ResponseBasicActiveMemebers> response) {
            Log.i("onResponse", new Gson().toJson(response.body()));
            if (response.body().getStatus().equals("1")){
                m_shimmer_basic_active.stopShimmerAnimation();
                m_shimmer_basic_active.setVisibility(View.GONE);
                recyler_basic_active.setVisibility(View.VISIBLE);
                Log.i("onResponse", new Gson().toJson(response.body()));
                ResponseBasicActiveMemebers responseBasicActiveMemebers=response.body();
                final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyler_basic_active.setLayoutManager(layoutManager);
                recyler_basic_active.setHasFixedSize(true);
                listBasicActiveMembers=responseBasicActiveMemebers.getData();
                basicActiveAdapter=new BasicActiveAdapter(listBasicActiveMembers,getApplicationContext());
                recyler_basic_active.setAdapter(basicActiveAdapter);
            }
            else {
                m_shimmer_basic_active.stopShimmerAnimation();
                m_shimmer_basic_active.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<ResponseBasicActiveMemebers> call, Throwable t) {
            m_shimmer_basic_active.stopShimmerAnimation();
            m_shimmer_basic_active.setVisibility(View.GONE);
        }
    });

}


}