package com.gipra.vicibcomplete.MembersArea.MenuFragments.StandardPlanReports;

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
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardLeftSideSalesAdapter;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.StandardRightSideSales;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.Standard_ListLeftSideSales;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class St_RightSideSalesFragment extends Fragment {

    RecyclerView recycler_standard_right_side_sales;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText standard_right_side_sales_fromdate,standard_right_side_sales_todate;
    Button search_standard_right_side_sales;
    private List<Standard_ListLeftSideSales> standard_listLeftSideSales;
    private StandardLeftSideSalesAdapter standardLeftSideSalesAdapter;
    ShimmerFrameLayout m_shimmer_st_rightside_sales;

    ImageView nodata_st_rightside_sales;

    public St_RightSideSalesFragment() {
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
       View view= inflater.inflate(R.layout.fragment_st__right_side_sales, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Right Side Sales");

        m_shimmer_st_rightside_sales=view.findViewById(R.id.m_shimmer_st_rightside_sales);

        recycler_standard_right_side_sales=view.findViewById(R.id.recycler_standard_right_side_sales);
        standard_right_side_sales_fromdate=view.findViewById(R.id.standard_right_side_sales_fromdate);
        standard_right_side_sales_todate=view.findViewById(R.id.standard_right_side_sales_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        standard_right_side_sales_fromdate.setInputType(InputType.TYPE_NULL);
        standard_right_side_sales_fromdate.requestFocus();
        standard_right_side_sales_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        standard_right_side_sales_todate.setInputType(InputType.TYPE_NULL);
        standard_right_side_sales_todate.requestFocus();
        standard_right_side_sales_todate.setOnClickListener(new View.OnClickListener() {
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
                standard_right_side_sales_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                standard_right_side_sales_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        search_standard_right_side_sales=view.findViewById(R.id.search_standard_right_side_sales);
        search_standard_right_side_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });
      nodata_st_rightside_sales=view.findViewById(R.id.nodata_st_rightside_sales);
        return view;
    }

 private void searchFirstPurchaseReport() {
    m_shimmer_st_rightside_sales.setVisibility(View.VISIBLE);
    m_shimmer_st_rightside_sales.startShimmerAnimation();
    SharedPreferences shpref;
    shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
    String id=shpref.getString("ID","");
    Log.e("id",id);
    String fdate=standard_right_side_sales_todate.getText().toString();
    String tdate=standard_right_side_sales_todate.getText().toString();
    ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
    //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
    Call<ResponseStandardLeftSideSales> usercall=api.SearchStandardLeftSales(1,"01/01/2018","01/11/2020","Right");
    usercall.enqueue(new Callback<ResponseStandardLeftSideSales>() {
        @Override
        public void onResponse(Call<ResponseStandardLeftSideSales> call, Response<ResponseStandardLeftSideSales> response) {
            Log.i("onResponse", new Gson().toJson(response.body()));
            if (response.body().getStatus().equals("1")){
                recycler_standard_right_side_sales.setVisibility(View.VISIBLE);
                m_shimmer_st_rightside_sales.setVisibility(View.GONE);
                m_shimmer_st_rightside_sales.stopShimmerAnimation();
                nodata_st_rightside_sales.setVisibility(View.GONE);
                Log.i("onResponse", new Gson().toJson(response.body()));
                ResponseStandardLeftSideSales responseStandardLeftSideSales=response.body();
                final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycler_standard_right_side_sales.setLayoutManager(layoutManager);
                standard_listLeftSideSales=responseStandardLeftSideSales.getData();
                standardLeftSideSalesAdapter=new StandardLeftSideSalesAdapter(standard_listLeftSideSales,getContext());
                recycler_standard_right_side_sales.setAdapter(standardLeftSideSalesAdapter);
            }
            else {
                m_shimmer_st_rightside_sales.setVisibility(View.GONE);
                m_shimmer_st_rightside_sales.stopShimmerAnimation();
                nodata_st_rightside_sales.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(R.drawable.nodatafound)
                        .into(nodata_st_rightside_sales);
            }
        }
        @Override
        public void onFailure(Call<ResponseStandardLeftSideSales> call, Throwable t) {
            m_shimmer_st_rightside_sales.setVisibility(View.GONE);
            m_shimmer_st_rightside_sales.stopShimmerAnimation();
        }
    });

}
public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}