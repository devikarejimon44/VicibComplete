package com.gipra.vicibcomplete.MembersArea.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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


public class MyProducts extends AppCompatActivity {
    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText fromdate,todate;
    Button myproduct_search;
    RecyclerView recycler_myproducts;
    private List<ListMyProducts> listMyProducts;
    private MyProductsAdapter myProductsAdapter;
    AVLoadingIndicatorView mypdoducts_loader;
    ImageView bill;
    PopupWindow popupWindow;
    RelativeLayout rlmyproducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);
        fromdate=findViewById(R.id.fromdate);
        todate=findViewById(R.id.todate);
        recycler_myproducts=findViewById(R.id.recycler_myproducts);

        mypdoducts_loader=findViewById(R.id.mypdoducts_loader);
        rlmyproducts=findViewById(R.id.rlmyproducts);


        ImageView back_myproducts=findViewById(R.id.back_myproducts);
        back_myproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
        from = new DatePickerDialog(MyProducts.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(MyProducts.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        myproduct_search=findViewById(R.id.myproduct_search);
        myproduct_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchmyproducts();
            }
        });
        bill=findViewById(R.id.bill);
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) MyProducts.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customview = layoutInflater.inflate(R.layout.myproduct_bill, null);
//                Button yes = customview.findViewById(R.id.yes);
//                yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Logout();
//                        popupWindow.dismiss();
//                    }
//                });

                popupWindow = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(rlmyproducts, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
            }
        });

    }
    private void searchmyproducts() {
        mypdoducts_loader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=fromdate.getText().toString();
        String tdate=todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMyProducts> usercall=api.searchmyproducts(17426,"06/01/2020","08/09/2020");
        usercall.enqueue(new Callback<ResponseMyProducts>() {
            @Override
            public void onResponse(Call<ResponseMyProducts> call, Response<ResponseMyProducts> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    mypdoducts_loader.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseMyProducts responseMyProducts=response.body();

                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_myproducts.setLayoutManager(layoutManager);
                    recycler_myproducts.setHasFixedSize(true);
                    listMyProducts=responseMyProducts.getData();
                    myProductsAdapter=new MyProductsAdapter(listMyProducts,getApplicationContext());
                    recycler_myproducts.setAdapter(myProductsAdapter);

                    for (int i = 0; i < listMyProducts.size(); i++) {
                        String orderid = listMyProducts.get(i).getOrderid();
                        SharedPreferences sharedPreferences;
                        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ORDERID", orderid);
                        editor.commit();
                    }
                }
                else {
                    mypdoducts_loader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseMyProducts> call, Throwable t) {
                mypdoducts_loader.setVisibility(View.GONE);

            }
        });

    }
}