package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class PremiumTeamSalesBonusAdapter extends RecyclerView.Adapter<PremiumTeamSalesBonusAdapter.ViewHolder>  {
    private List<PremiumListTeamSalesBonusDetails> premiumListTeamSalesBonusDetails;
    private Context context;


    public  PremiumTeamSalesBonusAdapter(List<PremiumListTeamSalesBonusDetails>premiumListTeamSalesBonusDetails,Context context){
        this.premiumListTeamSalesBonusDetails=premiumListTeamSalesBonusDetails;
        this.context=context;
    }
    public PremiumTeamSalesBonusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.premium_team_sales_bonusdetails,viewGroup,false);
        return new PremiumTeamSalesBonusAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(PremiumTeamSalesBonusAdapter.ViewHolder viewHolder,final int i){

        viewHolder.premium_team_sales_bonus_count.setText(String.valueOf(premiumListTeamSalesBonusDetails.get(i).getCount()));
        viewHolder.premium_team_sales_bonus_date.setText(premiumListTeamSalesBonusDetails.get(i).getTodate());
        viewHolder.premium_team_sales_bonus_teambonus.setText(premiumListTeamSalesBonusDetails.get(i).getNBinaryAmt());
        viewHolder.premium_team_sales_bonus_deduction.setText(premiumListTeamSalesBonusDetails.get(i).getDeductionAmount());
        viewHolder.premium_team_sales_bonus_netamount.setText(premiumListTeamSalesBonusDetails.get(i).getNetAmount());
        viewHolder.premium_team_sales_bonus_grossamount.setText(premiumListTeamSalesBonusDetails.get(i).getGrossAmount());

    }
    @Override
    public int getItemCount() {
        return premiumListTeamSalesBonusDetails.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView premium_team_sales_bonus_count,premium_team_sales_bonus_date,premium_team_sales_bonus_teambonus,premium_team_sales_bonus_grossamount;
        TextView premium_team_sales_bonus_deduction,premium_team_sales_bonus_netamount;

        public ViewHolder(View view) {
            super(view);
            premium_team_sales_bonus_count=view.findViewById(R.id.premium_team_sales_bonus_count);
            premium_team_sales_bonus_date=view.findViewById(R.id.premium_team_sales_bonus_date);
            premium_team_sales_bonus_teambonus=view.findViewById(R.id.premium_team_sales_bonus_teambonus);
            premium_team_sales_bonus_grossamount=view.findViewById(R.id.premium_team_sales_bonus_grossamount);
            premium_team_sales_bonus_deduction=view.findViewById(R.id.premium_team_sales_bonus_deduction);
            premium_team_sales_bonus_netamount=view.findViewById(R.id.premium_team_sales_bonus_netamount);


        }
    }
}


