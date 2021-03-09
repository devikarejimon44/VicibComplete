package com.gipra.vicibcomplete.MembersArea.MenuFragments.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Reports.FirstPurchaseBVReportAdapter;
import com.gipra.vicibcomplete.MembersArea.Reports.ListFirstPurchaseBVReport;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseFirstPurchaseBVReport;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstPurchaseBVReportFragment extends Fragment {

    RecyclerView recyler_first_purchase_bv_report;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText first_purchase_fromdate,first_purchase_todate;
    Button first_purchase_search;
    private List<ListFirstPurchaseBVReport> listFirstPurchaseBVReport;
    private FirstPurchaseBVReportAdapter firstPurchaseBVReportAdapter;

    ShimmerFrameLayout m_shimmer_first_purchase_bvreport;
    ImageView nodata_first_purchase_bvreport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_first_purchase_b_v_report, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("First Purchase BV Report");

        recyler_first_purchase_bv_report=view.findViewById(R.id.recyler_first_purchase_bv_report);
        first_purchase_fromdate=view.findViewById(R.id.first_purchase_fromdate);
        first_purchase_todate=view.findViewById(R.id.first_purchase_todate);

        m_shimmer_first_purchase_bvreport=view.findViewById(R.id.m_shimmer_first_purchase_bvreport);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        first_purchase_fromdate.setInputType(InputType.TYPE_NULL);
        first_purchase_fromdate.requestFocus();
        first_purchase_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        first_purchase_todate.setInputType(InputType.TYPE_NULL);
        first_purchase_todate.requestFocus();
        first_purchase_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                first_purchase_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                first_purchase_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        first_purchase_search=view.findViewById(R.id.first_purchase_search);
        first_purchase_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
        nodata_first_purchase_bvreport=view.findViewById(R.id.nodata_first_purchase_bvreport);

        return view;
    }
    private void searchFirstPurchaseReport() {
        m_shimmer_first_purchase_bvreport.setVisibility(View.VISIBLE);
        m_shimmer_first_purchase_bvreport.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=first_purchase_fromdate.getText().toString();
        String tdate=first_purchase_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(17426,fdate,tdate);
        usercall.enqueue(new Callback<ResponseFirstPurchaseBVReport>() {
            @Override
            public void onResponse(Call<ResponseFirstPurchaseBVReport> call, Response<ResponseFirstPurchaseBVReport> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    nodata_first_purchase_bvreport.setVisibility(View.GONE);
                    recyler_first_purchase_bv_report.setVisibility(View.VISIBLE);
                    m_shimmer_first_purchase_bvreport.setVisibility(View.GONE);
                    m_shimmer_first_purchase_bvreport.stopShimmerAnimation();
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseFirstPurchaseBVReport responseFirstPurchaseBVReport=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyler_first_purchase_bv_report.setLayoutManager(layoutManager);
                    recyler_first_purchase_bv_report.setHasFixedSize(true);
                    listFirstPurchaseBVReport=responseFirstPurchaseBVReport.getData();
                    firstPurchaseBVReportAdapter=new FirstPurchaseBVReportAdapter(listFirstPurchaseBVReport,getContext());
                    recyler_first_purchase_bv_report.setAdapter(firstPurchaseBVReportAdapter);
                }
                else {

                    m_shimmer_first_purchase_bvreport.setVisibility(View.GONE);
                    m_shimmer_first_purchase_bvreport.stopShimmerAnimation();

                    nodata_first_purchase_bvreport.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_first_purchase_bvreport);
                }
            }
            @Override
            public void onFailure(Call<ResponseFirstPurchaseBVReport> call, Throwable t) {
                m_shimmer_first_purchase_bvreport.setVisibility(View.GONE);
                m_shimmer_first_purchase_bvreport.stopShimmerAnimation();


            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}

