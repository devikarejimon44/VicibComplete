package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder>  {
    private List<ListMyProductsDateOnly> listMyProducts;
    private List<ListMyProductsDate> listMyProductsDate;
    private List<ListMyproductsbill>listMyproductsbill;
    private MyProductsDateAdapter myProductsDateAdapter;
    private  MyProductsBillAdapter myProductsBillAdapter;
    private Context context;
    private Dialog dialog;
    private static final String TAG = "MyProductsAdapter";
    PopupWindow popupWindow;

    public  MyProductsAdapter(List<ListMyProductsDateOnly>listMyProducts,Context context){
        this.listMyProducts=listMyProducts;
        this.context=context;
    }
    public MyProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myproduct_list,viewGroup,false);
        return new MyProductsAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(final MyProductsAdapter.ViewHolder viewHolder, final int i){
//        viewHolder.myproduct_product.setText(listMyProducts.get(i).getProductname());
//        viewHolder.myproduct_quantity.setText(listMyProducts.get(i).getBquantity());
//        viewHolder.myproduct_amount.setText(listMyProducts.get(i).getDp());
//        viewHolder.myproduct_totalamount.setText(String.valueOf(listMyProducts.get(i).getTotalAmount()));
//        viewHolder.myproduct_BV.setText(String.valueOf(listMyProducts.get(i).getTotalBv()));
//        viewHolder.myproduct_totalBV.setText(String.valueOf(listMyProducts.get(i).getTotalBv()));
        try{

            SharedPreferences shpref;
            shpref=context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
            final String o_id=shpref.getString("ORDER_ID","");
            Log.e("order_id",o_id);
        // String oid=listMyProducts.get(i).getOrderid();
         viewHolder.btn_purchase_bill.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customview = layoutInflater.inflate(R.layout.myproduct_bill, null);
                final RecyclerView recycler_bill=customview.findViewById(R.id.recycler_bill);
                final TextView bill_invoice=customview.findViewById(R.id.bill_invoice);
                final  TextView bill_date=customview.findViewById(R.id.bill_date);
                final  TextView bill_name=customview.findViewById(R.id.bill_name);
                 final  TextView bill_address=customview.findViewById(R.id.bill_address);
                 final  TextView bill_mobile=customview.findViewById(R.id.bill_mobile);




                 ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                 Call<ResponseMyProductsBill> usercall = api.MyproductsBill(17426, 8655);
                 usercall.enqueue(new Callback<ResponseMyProductsBill>() {
                     @Override
                     public void onResponse(Call<ResponseMyProductsBill> call, Response<ResponseMyProductsBill> response) {
                         if (response.body().getStatus().equals("1")){

                             Log.i("onResponse", new Gson().toJson(response.body()));
                             ResponseMyProductsBill responseMyProductsBill = response.body();

                             String invoice=responseMyProductsBill.getInvoice();
                             bill_invoice.setText(invoice);
                             String date=responseMyProductsBill.getDate();
                             bill_date.setText(date);
                             String add=responseMyProductsBill.getAddress();
                             bill_address.setText(add);
                             String name=responseMyProductsBill.getName();
                             bill_name.setText(name);
                             String mob=responseMyProductsBill.getMobile();
                             bill_mobile.setText(mob);
                           //  String pin=responseMyProductsBill.getPincode();


                             final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                             layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                             recycler_bill.setLayoutManager(layoutManager);
                             recycler_bill.setHasFixedSize(true);
                             listMyproductsbill = responseMyProductsBill.getData();
                             myProductsBillAdapter = new MyProductsBillAdapter(listMyproductsbill, context);
                             recycler_bill.setAdapter(myProductsBillAdapter);


                         }
                     }

                     @Override
                     public void onFailure(Call<ResponseMyProductsBill> call, Throwable t) {

                     }
                 });




//                Button yes = customview.findViewById(R.id.yes);
//                yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        popupWindow.dismiss();
//                    }
//                });
                popupWindow = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(viewHolder.cardview_oderdate, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
             }
         });
        viewHolder.oderddate.setText(String.valueOf(listMyProducts.get(i).getBdatetime()));


        String date=listMyProducts.get(i).getBdatetime();

    ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
    Call<ResponseMyProductsDate> usercall = api.searchmyproductsdate(1, date);
    //  Call<ResponseMyProducts> usercall=api.searchmyproducts(1,fdate,tdate);
    usercall.enqueue(new Callback<ResponseMyProductsDate>() {
        @Override
        public void onResponse(Call<ResponseMyProductsDate> call, Response<ResponseMyProductsDate> response) {
            Log.i("onResponse", new Gson().toJson(response.body()));
            if (response.body().getStatus().equals("1")) {

                Log.i("onResponse", new Gson().toJson(response.body()));
                ResponseMyProductsDate responseMyProductsDate = response.body();


                final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                viewHolder.recycler_myproduct_more.setLayoutManager(layoutManager);
                viewHolder.recycler_myproduct_more.setHasFixedSize(true);
                listMyProductsDate = responseMyProductsDate.getData();
                myProductsDateAdapter = new MyProductsDateAdapter(listMyProductsDate, context);
                viewHolder.recycler_myproduct_more.setAdapter(myProductsDateAdapter);



            } else {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseMyProductsDate> call, Throwable t) {
            Toast.makeText(context, "fdsfsf", Toast.LENGTH_SHORT).show();

        }
    });











//
//        final String userimg = userStoryList.get(i).getUserImage();
//        Glide.with(context)
//                .load(userimg)
//                .into(viewHolder.userstory_userimg);
//        viewHolder.ll_storylist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=userStoryList.get(i).getTitle();
//                String des=userStoryList.get(i).getDescription();
//                String date=userStoryList.get(i).getAddedDate();
//
//                String username=userStoryList.get(i).getUserName();
//                String postago=userStoryList.get(i).getPostedAgo();
//                String st_id=userStoryList.get(i).getStoryId();
//
//                Intent i=new Intent(context,StoryMoreView.class);
//                i.putExtra("USERNAME",username);
//                i.putExtra("ST_NAME",name);
//                i.putExtra("ST_DESC",des);
//                i.putExtra("ST_DATE",date);
//
//                //  i.putExtra("ST_IMG",imgpath);
//                i.putExtra("POSTAGO",postago);
//                i.putExtra("USERIMG",userimg);
//                i.putExtra("STORYID",st_id);
//
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });

        }catch (Exception e) {
            Log.e(TAG, "error" + e);
        }
    }
    @Override
    public int getItemCount() {
        return listMyProducts.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{


        TextView oderddate;
        LinearLayout myproducts_moreview,ll_viewdetails;
        Button btn_purchase_bill;
        RecyclerView recycler_myproduct_more;
        CardView cardview_oderdate;



        public ViewHolder(View view) {
            super(view);

            oderddate=view.findViewById(R.id.oderddate);

          recycler_myproduct_more = view.findViewById(R.id.recycler_myproduct_more);
            btn_purchase_bill=view.findViewById(R.id.btn_purchase_bill);
            cardview_oderdate=view.findViewById(R.id.cardview_oderdate);



        }
    }
}
