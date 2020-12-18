package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class PremiumLeftSideSalesAdapter  extends RecyclerView.Adapter<PremiumLeftSideSalesAdapter.ViewHolder>  {
    private List<PremiumListLeftSideSales> premiumListLeftSideSales;
    private Context context;


    public  PremiumLeftSideSalesAdapter(List<PremiumListLeftSideSales>premiumListLeftSideSales,Context context){
        this.premiumListLeftSideSales=premiumListLeftSideSales;
        this.context=context;
    }
    public PremiumLeftSideSalesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.premium_leftsidesales,viewGroup,false);
        return new PremiumLeftSideSalesAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(PremiumLeftSideSalesAdapter.ViewHolder viewHolder,final int i){

        viewHolder.premium_left_side_sales_count.setText(String.valueOf(premiumListLeftSideSales.get(i).getCount()));
        viewHolder.premium_left_side_sales_name.setText(premiumListLeftSideSales.get(i).getName());
        viewHolder.premium_left_side_sales_username.setText(premiumListLeftSideSales.get(i).getChildusername());
        viewHolder.premium_left_side_sales_date.setText(premiumListLeftSideSales.get(i).getDactivated());
        viewHolder.premium_left_side_sales_bv.setText(premiumListLeftSideSales.get(i).getNPV());






    }
    @Override
    public int getItemCount() {
        return premiumListLeftSideSales.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView premium_left_side_sales_count,premium_left_side_sales_name,premium_left_side_sales_username,premium_left_side_sales_date;
        TextView premium_left_side_sales_bv;


        public ViewHolder(View view) {
            super(view);
            premium_left_side_sales_count=view.findViewById(R.id.premium_left_side_sales_count);
            premium_left_side_sales_name=view.findViewById(R.id.premium_left_side_sales_name);
            premium_left_side_sales_username=view.findViewById(R.id.premium_left_side_sales_username);
            premium_left_side_sales_date=view.findViewById(R.id.premium_left_side_sales_date);
            premium_left_side_sales_bv=view.findViewById(R.id.premium_left_side_sales_bv);





        }
    }
}



