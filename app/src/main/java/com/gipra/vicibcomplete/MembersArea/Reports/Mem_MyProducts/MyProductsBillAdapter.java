package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.vicibcomplete.R;

import java.util.List;

public class MyProductsBillAdapter extends RecyclerView.Adapter<MyProductsBillAdapter.ViewHolder>{
    private List<ListMyproductsbill> listMyproductsbill;
    private Context context;
    private static final String TAG = "MyProductsDateAdapter";
    public  MyProductsBillAdapter(List<ListMyproductsbill>listMyproductsbill,Context context){
        this.listMyproductsbill=listMyproductsbill;
        this.context=context;
    }
    public MyProductsBillAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myproducts_bill_list,viewGroup,false);
        return new MyProductsBillAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(MyProductsBillAdapter.ViewHolder viewHolder,final int i){
        try {
            viewHolder.bill_item_name.setText(listMyproductsbill.get(i).getProductname());
            viewHolder.bill_dp.setText(listMyproductsbill.get(i).getDp());
            viewHolder.bill_bv.setText(String.valueOf(listMyproductsbill.get(i).getTotalBv()));
           // viewHolder.bill_qty.setText(String.valueOf(listMyproductsbill.get(i).getTotalAmount()));
           viewHolder.bill_qty.setText(listMyproductsbill.get(i).getBquantity());
            viewHolder.bill_amt.setText(String.valueOf(listMyproductsbill.get(i).getTotalAmount()));
        }catch (Exception e){
            Log.e(TAG, "error" + e);
        }





    }
    @Override
    public int getItemCount() {
        return listMyproductsbill.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView bill_item_name,bill_dp,bill_qty,bill_bv;
        TextView bill_amt;


        public ViewHolder(View view) {
            super(view);
            bill_item_name=view.findViewById(R.id.bill_item_name);
            bill_dp=view.findViewById(R.id.bill_dp);
            bill_qty=view.findViewById(R.id.bill_qty);
            bill_bv=view.findViewById(R.id.bill_bv);
            bill_amt=view.findViewById(R.id.bill_amt);


        }
    }
}