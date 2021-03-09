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
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ListRepurchaseIncomeDetails;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncomeDetails;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.RepurchaseIncomeDetailsAdapter;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseRepurchaseIncomeDetails;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepurchaseIncomeDetailsFragment extends Fragment {

    RecyclerView Recycler_repurchase_income_details;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText repurchase_income_details_fromdate,repurchase_income_details_todate;
    Button repurchase_income_details_search;
    private List<ListRepurchaseIncomeDetails> listRepurchaseIncomeDetails;
    private RepurchaseIncomeDetailsAdapter repurchaseIncomeDetailsAdapter;
    ShimmerFrameLayout m_shimmer_repurchase_income_details;

    ImageView nodata_repurchase_income_details;


    public RepurchaseIncomeDetailsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_repurchase_income_details, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Repurchase Income Details");
        m_shimmer_repurchase_income_details=view.findViewById(R.id.m_shimmer_repurchase_income_details);
        Recycler_repurchase_income_details=view.findViewById(R.id.Recycler_repurchase_income_details);
        repurchase_income_details_fromdate=view.findViewById(R.id.repurchase_income_details_fromdate);
        repurchase_income_details_todate=view.findViewById(R.id.repurchase_income_details_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        repurchase_income_details_fromdate.setInputType(InputType.TYPE_NULL);
        repurchase_income_details_fromdate.requestFocus();
        repurchase_income_details_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        repurchase_income_details_todate.setInputType(InputType.TYPE_NULL);
        repurchase_income_details_todate.requestFocus();
        repurchase_income_details_todate.setOnClickListener(new View.OnClickListener() {
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
                repurchase_income_details_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                repurchase_income_details_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        repurchase_income_details_search=view.findViewById(R.id.repurchase_income_details_search);
        repurchase_income_details_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
       nodata_repurchase_income_details=view.findViewById(R.id.nodata_repurchase_income_details);


        return view;
    }
    private void searchFirstPurchaseReport() {
        m_shimmer_repurchase_income_details.setVisibility(View.VISIBLE);
        m_shimmer_repurchase_income_details.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=repurchase_income_details_fromdate.getText().toString();
        String tdate=repurchase_income_details_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseRepurchaseIncomeDetails> usercall=api.SearchRepurchaseIncomeDetails(1,"10/10/2020","10/11/2020");
        usercall.enqueue(new Callback<ResponseRepurchaseIncomeDetails>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncomeDetails> call, Response<ResponseRepurchaseIncomeDetails> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_repurchase_income_details.setVisibility(View.GONE);
                    m_shimmer_repurchase_income_details.stopShimmerAnimation();
                    Recycler_repurchase_income_details.setVisibility(View.VISIBLE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseRepurchaseIncomeDetails responseRepurchaseIncomeDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_repurchase_income_details.setLayoutManager(layoutManager);
                    listRepurchaseIncomeDetails=responseRepurchaseIncomeDetails.getData();
                    repurchaseIncomeDetailsAdapter=new RepurchaseIncomeDetailsAdapter(listRepurchaseIncomeDetails,getContext());
                    Recycler_repurchase_income_details.setAdapter(repurchaseIncomeDetailsAdapter);
                }
                else {
                    m_shimmer_repurchase_income_details.setVisibility(View.GONE);
                    m_shimmer_repurchase_income_details.stopShimmerAnimation();

                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_repurchase_income_details);
                }
            }
            @Override
            public void onFailure(Call<ResponseRepurchaseIncomeDetails> call, Throwable t) {
                m_shimmer_repurchase_income_details.setVisibility(View.GONE);
                m_shimmer_repurchase_income_details.stopShimmerAnimation();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

}