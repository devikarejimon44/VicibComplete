package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class StandardTeamSalesBVAdapter extends RecyclerView.Adapter<StandardTeamSalesBVAdapter.ViewHolder>  {
    private List<Standard_List_TeamSalesBvMatching> standard_list_teamSalesBvMatchings;
    private Context context;


    public  StandardTeamSalesBVAdapter(List<Standard_List_TeamSalesBvMatching>standard_list_teamSalesBvMatchings,Context context){
        this.standard_list_teamSalesBvMatchings=standard_list_teamSalesBvMatchings;
        this.context=context;
    }
    public StandardTeamSalesBVAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.standard_team_sales_bvmatching,viewGroup,false);
        return new StandardTeamSalesBVAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(StandardTeamSalesBVAdapter.ViewHolder viewHolder,final int i){

        viewHolder.standard_team_salesbv_matching_count.setText(String.valueOf(standard_list_teamSalesBvMatchings.get(i).getCount()));
        viewHolder.standard_team_salesbv_matching_leftBV.setText(standard_list_teamSalesBvMatchings.get(i).getNLeftPv());
        viewHolder.standard_team_salesbv_matching_rightBV.setText(standard_list_teamSalesBvMatchings.get(i).getNLeftPv());
        viewHolder.standard_team_salesbv_matching_BV.setText(standard_list_teamSalesBvMatchings.get(i).getDFromDate());
        viewHolder.standard_team_salesbv_matching_leftcarryBV.setText(standard_list_teamSalesBvMatchings.get(i).getNLeftCarryPv());
        viewHolder.standard_team_salesbv_matching_rightcarryBV.setText(standard_list_teamSalesBvMatchings.get(i).getNRightCarryPv());
        viewHolder.standard_team_salesbv_matching_amount.setText(String.valueOf(standard_list_teamSalesBvMatchings.get(i).getAmount()));


    }
    @Override
    public int getItemCount() {
        return standard_list_teamSalesBvMatchings.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView standard_team_salesbv_matching_count,standard_team_salesbv_matching_leftBV,standard_team_salesbv_matching_rightBV,standard_team_salesbv_matching_BV;
        TextView standard_team_salesbv_matching_leftcarryBV,standard_team_salesbv_matching_rightcarryBV,standard_team_salesbv_matching_amount;


        public ViewHolder(View view) {
            super(view);
            standard_team_salesbv_matching_count=view.findViewById(R.id.standard_team_salesbv_matching_count);
            standard_team_salesbv_matching_leftBV=view.findViewById(R.id.standard_team_salesbv_matching_leftBV);
            standard_team_salesbv_matching_rightBV=view.findViewById(R.id.standard_team_salesbv_matching_rightBV);
            standard_team_salesbv_matching_BV=view.findViewById(R.id.standard_team_salesbv_matching_BV);
            standard_team_salesbv_matching_leftcarryBV=view.findViewById(R.id.standard_team_salesbv_matching_leftcarryBV);
            standard_team_salesbv_matching_rightcarryBV=view.findViewById(R.id.standard_team_salesbv_matching_rightcarryBV);
            standard_team_salesbv_matching_amount=view.findViewById(R.id.standard_team_salesbv_matching_amount);




        }
    }
}
