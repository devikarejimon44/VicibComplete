package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.vicibcomplete.R;

import java.util.List;

public class MyProductsgstAdapter extends RecyclerView.Adapter<MyProductsgstAdapter.ViewHolder>{
    private List<ListMyproductsbill> listMyproductsbill;
    private Context context;
    private static final String TAG = "MyProductsgstAdapter";
    public  MyProductsgstAdapter(List<ListMyproductsbill>listMyproductsbill,Context context){
        this.listMyproductsbill=listMyproductsbill;
        this.context=context;
    }
    public MyProductsgstAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myproduts_bill_gst_list,viewGroup,false);
        return new MyProductsgstAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(MyProductsgstAdapter.ViewHolder viewHolder,final int i){
        try {
            viewHolder.cgst_rate.setText(listMyproductsbill.get(i).getCgstrate());
            viewHolder.cgst_amount.setText(listMyproductsbill.get(i).getCgstamount());
            viewHolder.sgst_rate.setText(listMyproductsbill.get(i).getSgstrate());
            // viewHolder.bill_qty.setText(String.valueOf(listMyproductsbill.get(i).getTotalAmount()));
            viewHolder.sgst_amount.setText(listMyproductsbill.get(i).getSgstamount());

        }catch (Exception e){
            Log.e(TAG, "error" + e);
        }


    }
    @Override
    public int getItemCount() {
        return listMyproductsbill.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView cgst_rate,cgst_amount,sgst_rate,sgst_amount;


        public ViewHolder(View view) {
            super(view);
            cgst_rate=view.findViewById(R.id.cgst_rate);
            cgst_amount=view.findViewById(R.id.cgst_amount);
            sgst_rate=view.findViewById(R.id.sgst_rate);
            sgst_amount=view.findViewById(R.id.sgst_amount);


        }
    }
}
