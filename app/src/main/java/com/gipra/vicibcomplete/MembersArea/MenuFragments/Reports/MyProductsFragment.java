package com.gipra.vicibcomplete.MembersArea.MenuFragments.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ListMyProductsDateOnly;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.MyProducts;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.MyProductsAdapter;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ResponseMyproductsDateOnly;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProductsFragment extends Fragment {
    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText fromdate,todate;
    Button myproduct_search;
    RecyclerView recycler_myproducts;
    private List<ListMyProductsDateOnly> listMyProducts;
    private MyProductsAdapter myProductsAdapter;

    RelativeLayout rlmyproducts;
    ShimmerFrameLayout m_shimmer_myproducts;
    ImageView nodata_myproducts;



    public MyProductsFragment() {
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
        View view=inflater.inflate(R.layout.fragment_my_products, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Products");

        fromdate=view.findViewById(R.id.fromdate);
        todate=view.findViewById(R.id.todate);
        recycler_myproducts=view.findViewById(R.id.recycler_myproducts);




        rlmyproducts=view.findViewById(R.id.rlmyproducts);
        m_shimmer_myproducts=view.findViewById(R.id.m_shimmer_myproducts);


//        Toolbar toolbar=view.findViewById(R.id.myproducts_ToolBar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        fromdate.setInputType(InputType.TYPE_NULL);
        fromdate.requestFocus();
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        todate.setInputType(InputType.TYPE_NULL);
        todate.requestFocus();
        todate.setOnClickListener(new View.OnClickListener() {
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
                fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        myproduct_search=view.findViewById(R.id.myproduct_search);
        myproduct_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchmyproducts();
            }
        });
        nodata_myproducts=view.findViewById(R.id.nodata_myproducts);


        return view;
    }


    private void searchmyproducts() {
//        mypdoducts_loader.setVisibility(View.VISIBLE);
        m_shimmer_myproducts.startShimmerAnimation();
        m_shimmer_myproducts.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=fromdate.getText().toString();
        String tdate=todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        // Call<ResponseMyproductsDateOnly> usercall=api.SearchMyproductsDateonly(1,"06-01-2020","08-09-2020");
        // Call<ResponseMyproductsDateOnly> usercall=api.SearchMyproductsDateonly(1,"17/2/2021","17/2/2021");
        Call<ResponseMyproductsDateOnly> usercall=api.SearchMyproductsDateonly(1);
        usercall.enqueue(new Callback<ResponseMyproductsDateOnly>() {
            @Override
            public void onResponse(Call<ResponseMyproductsDateOnly> call, Response<ResponseMyproductsDateOnly> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    // mypdoducts_loader.setVisibility(View.GONE);
                    m_shimmer_myproducts.stopShimmerAnimation();
                    m_shimmer_myproducts.setVisibility(View.GONE);
                    recycler_myproducts.setVisibility(View.VISIBLE);

                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseMyproductsDateOnly responseMyProducts=response.body();

                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_myproducts.setLayoutManager(layoutManager);
                    recycler_myproducts.setHasFixedSize(true);
                    listMyProducts=responseMyProducts.getData();
                    myProductsAdapter=new MyProductsAdapter(listMyProducts,getContext());
                    recycler_myproducts.setAdapter(myProductsAdapter);

//                    for (int i = 0; i < listMyProducts.size(); i++) {
//                      //  String orderid = listMyProducts.get(i).getOrderid();
//                        SharedPreferences sharedPreferences;
//                        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("ORDERID", orderid);
//                        editor.commit();
//                    }
                }
                else {
                    m_shimmer_myproducts.setVisibility(View.GONE);
                    m_shimmer_myproducts.stopShimmerAnimation();
//                  Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();

                    nodata_myproducts.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_myproducts);

                }
            }
            @Override
            public void onFailure(Call<ResponseMyproductsDateOnly> call, Throwable t) {
                m_shimmer_myproducts.setVisibility(View.GONE);
                m_shimmer_myproducts.stopShimmerAnimation();
                Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}