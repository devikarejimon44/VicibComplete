package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class StandardTeamSalesBonusAdapter extends RecyclerView.Adapter<StandardTeamSalesBonusAdapter.ViewHolder>  {
private List<Standard_ListTeamSalesBonusDetails> standard_list_teamSalesBvMatching;
private Context context;


public  StandardTeamSalesBonusAdapter(List<Standard_ListTeamSalesBonusDetails>standard_list_teamSalesBvMatching,Context context){
        this.standard_list_teamSalesBvMatching=standard_list_teamSalesBvMatching;
        this.context=context;
        }
public StandardTeamSalesBonusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.standard_team_sales_bonus_details,viewGroup,false);
        return new StandardTeamSalesBonusAdapter.ViewHolder(view);
        }
public  void onBindViewHolder(StandardTeamSalesBonusAdapter.ViewHolder viewHolder,final int i){
     viewHolder.standard_team_sales_bonus_count.setText(String.valueOf(standard_list_teamSalesBvMatching.get(i).getCount()));
     viewHolder.standard_team_sales_bonus_date.setText(standard_list_teamSalesBvMatching.get(i).getTodate());
        viewHolder.standard_team_sales_bonus_teambonus.setText(standard_list_teamSalesBvMatching.get(i).getNBinaryAmt());
        viewHolder.standard_team_sales_bonus_grossamount.setText(standard_list_teamSalesBvMatching.get(i).getGrossAmount());
        viewHolder.standard_team_sales_bonus_deduction.setText(standard_list_teamSalesBvMatching.get(i).getDeductionAmount());
        viewHolder.standard_team_sales_bonus_netamount.setText(standard_list_teamSalesBvMatching.get(i).getNetAmount());


}
@Override
public int getItemCount() {
        return standard_list_teamSalesBvMatching.size();
        }
public  class ViewHolder extends RecyclerView.ViewHolder{

    TextView standard_team_sales_bonus_count,standard_team_sales_bonus_date,standard_team_sales_bonus_teambonus,standard_team_sales_bonus_grossamount;
    TextView standard_team_sales_bonus_deduction,standard_team_sales_bonus_netamount;


    public ViewHolder(View view) {
        super(view);
        standard_team_sales_bonus_count=view.findViewById(R.id.standard_team_sales_bonus_count);
        standard_team_sales_bonus_date=view.findViewById(R.id.standard_team_sales_bonus_date);
        standard_team_sales_bonus_teambonus=view.findViewById(R.id.standard_team_sales_bonus_teambonus);
        standard_team_sales_bonus_grossamount=view.findViewById(R.id.standard_team_sales_bonus_grossamount);
        standard_team_sales_bonus_deduction=view.findViewById(R.id.standard_team_sales_bonus_deduction);
        standard_team_sales_bonus_netamount=view.findViewById(R.id.standard_team_sales_bonus_netamount);


    }
}
}

