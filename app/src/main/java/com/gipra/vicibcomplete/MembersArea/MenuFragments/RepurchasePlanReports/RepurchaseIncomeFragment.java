package com.gipra.vicibcomplete.MembersArea.MenuFragments.RepurchasePlanReports;

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

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ListRepurchaseIncome;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncome;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncomeAdapter;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseRepurchaseIncome;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepurchaseIncomeFragment extends Fragment {

    RecyclerView Recycler_repurchase_income;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText repurchase_income_fromdate,repurchase_income_todate;
    Button repurchase_income_search;
    private List<ListRepurchaseIncome> listRepurchaseIncome;
    private RepurchaseIncomeAdapter repurchaseIncomeAdapter;
    ShimmerFrameLayout m_shimmer_repurchase_income;
    ImageView nodata_repurchase_income;
    public RepurchaseIncomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repurchase_income, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Repurchase Income");

        m_shimmer_repurchase_income = view.findViewById(R.id.m_shimmer_repurchase_income);
        Recycler_repurchase_income = view.findViewById(R.id.Recycler_repurchase_income);
        repurchase_income_fromdate = view.findViewById(R.id.repurchase_income_fromdate);
        repurchase_income_todate = view.findViewById(R.id.repurchase_income_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        repurchase_income_fromdate.setInputType(InputType.TYPE_NULL);
        repurchase_income_fromdate.requestFocus();
        repurchase_income_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        repurchase_income_todate.setInputType(InputType.TYPE_NULL);
        repurchase_income_todate.requestFocus();
        repurchase_income_todate.setOnClickListener(new View.OnClickListener() {
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
                repurchase_income_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        repurchase_income_search = view.findViewById(R.id.repurchase_income_search);
        repurchase_income_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
       nodata_repurchase_income=view.findViewById(R.id.nodata_repurchase_income);


        return view;
    }
        private void searchFirstPurchaseReport() {
            m_shimmer_repurchase_income.setVisibility(View.VISIBLE);
            m_shimmer_repurchase_income.startShimmerAnimation();
            SharedPreferences shpref;
            shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
            String id=shpref.getString("ID","");
            Log.e("id",id);
            String fdate=repurchase_income_fromdate.getText().toString();
            String tdate=repurchase_income_fromdate.getText().toString();
            ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
            //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
            Call<ResponseRepurchaseIncome> usercall=api.SearchRepurchaseIncome(1,"01/01/2018","01/11/2020");
            usercall.enqueue(new Callback<ResponseRepurchaseIncome>() {
                @Override
                public void onResponse(Call<ResponseRepurchaseIncome> call, Response<ResponseRepurchaseIncome> response) {
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    if (response.body().getStatus().equals("1")){
                        Recycler_repurchase_income.setVisibility(View.VISIBLE);
                        m_shimmer_repurchase_income.setVisibility(View.GONE);
                        m_shimmer_repurchase_income.stopShimmerAnimation();
                        Log.i("onResponse", new Gson().toJson(response.body()));
                        ResponseRepurchaseIncome responseStandardLeftSideSales=response.body();
                        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        Recycler_repurchase_income.setLayoutManager(layoutManager);
                        listRepurchaseIncome=responseStandardLeftSideSales.getData();
                        repurchaseIncomeAdapter=new RepurchaseIncomeAdapter(listRepurchaseIncome,getContext());
                        Recycler_repurchase_income.setAdapter(repurchaseIncomeAdapter);
                    }
                    else {
                        m_shimmer_repurchase_income.setVisibility(View.GONE);
                        m_shimmer_repurchase_income.stopShimmerAnimation();
                        Glide.with(getContext())
                                .load(R.drawable.nodatafound)
                                .into(nodata_repurchase_income);
                    }
                }
                @Override
                public void onFailure(Call<ResponseRepurchaseIncome> call, Throwable t) {
                    m_shimmer_repurchase_income.setVisibility(View.GONE);
                    m_shimmer_repurchase_income.stopShimmerAnimation();
                }
            });

        }
        public void onBackPressed(){
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }



    }