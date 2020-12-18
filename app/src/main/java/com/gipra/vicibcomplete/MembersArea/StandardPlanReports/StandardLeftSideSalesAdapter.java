package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class StandardLeftSideSalesAdapter extends RecyclerView.Adapter<StandardLeftSideSalesAdapter.ViewHolder>  {
    private List<Standard_ListLeftSideSales> Standard_ListLeftSideSales;
    private Context context;


    public  StandardLeftSideSalesAdapter(List<Standard_ListLeftSideSales>Standard_ListLeftSideSales,Context context){
        this.Standard_ListLeftSideSales=Standard_ListLeftSideSales;
        this.context=context;
    }
    public StandardLeftSideSalesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.standard_leftsidesales,viewGroup,false);
        return new StandardLeftSideSalesAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(StandardLeftSideSalesAdapter.ViewHolder viewHolder,final int i){

        viewHolder.standard_left_side_sales_count.setText(String.valueOf(Standard_ListLeftSideSales.get(i).getCount()));
        viewHolder.standard_left_side_sales_name.setText(Standard_ListLeftSideSales.get(i).getName());
        viewHolder.standard_left_side_sales_username.setText(Standard_ListLeftSideSales.get(i).getChildusername());
        viewHolder.standard_left_side_sales_dateofpurchase.setText(Standard_ListLeftSideSales.get(i).getDactivated());
        viewHolder.standard_left_side_sales_bv.setText(Standard_ListLeftSideSales.get(i).getNPV());
        viewHolder.standard_left_side_sales_status.setText(Standard_ListLeftSideSales.get(i).getCStatus());





    }
    @Override
    public int getItemCount() {
        return Standard_ListLeftSideSales.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView standard_left_side_sales_count,standard_left_side_sales_name,standard_left_side_sales_username,standard_left_side_sales_dateofpurchase;
        TextView standard_left_side_sales_bv,standard_left_side_sales_status;


        public ViewHolder(View view) {
            super(view);
            standard_left_side_sales_count=view.findViewById(R.id.standard_left_side_sales_count);
            standard_left_side_sales_name=view.findViewById(R.id.standard_left_side_sales_name);
            standard_left_side_sales_username=view.findViewById(R.id.standard_left_side_sales_username);
            standard_left_side_sales_dateofpurchase=view.findViewById(R.id.standard_left_side_sales_dateofpurchase);
            standard_left_side_sales_bv=view.findViewById(R.id.standard_left_side_sales_bv);
            standard_left_side_sales_status=view.findViewById(R.id.standard_left_side_sales_status);




        }
    }
}


