package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class PremiumTeamSalesBvAdapter  extends RecyclerView.Adapter<PremiumTeamSalesBvAdapter.ViewHolder>  {
    private List<PremiumListTeamSalesBVMatching> premiumListTeamSalesBVMatching;
    private Context context;


    public  PremiumTeamSalesBvAdapter(List<PremiumListTeamSalesBVMatching>premiumListTeamSalesBVMatching,Context context){
        this.premiumListTeamSalesBVMatching=premiumListTeamSalesBVMatching;
        this.context=context;
    }
    public PremiumTeamSalesBvAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.premium_team_sales_bvmatching,viewGroup,false);
        return new PremiumTeamSalesBvAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(PremiumTeamSalesBvAdapter.ViewHolder viewHolder,final int i){

        viewHolder.premium_team_salesbv_matching_count.setText(String.valueOf(premiumListTeamSalesBVMatching.get(i).getCount()));
        viewHolder.premium_team_salesbv_matching_date.setText(premiumListTeamSalesBVMatching.get(i).getDToDate());
        viewHolder.premium_team_salesbv_matching_leftsidesalesbv.setText(premiumListTeamSalesBVMatching.get(i).getNLeftPv());
        viewHolder.premium_team_salesbv_matching_rightsidesalesbv.setText(premiumListTeamSalesBVMatching.get(i).getNRightPv());
        viewHolder.premium_team_salesbv_matchingbv.setText(premiumListTeamSalesBVMatching.get(i).getNPairPv());
        viewHolder.premium_team_salesbv_matching_leftsalecarrybv.setText(premiumListTeamSalesBVMatching.get(i).getNLeftCarryPv());
        viewHolder.premium_team_salesbv_matching_rightsalecarrybv.setText(premiumListTeamSalesBVMatching.get(i).getNRightCarryPv());
        viewHolder.premium_team_salesbv_matching_amount.setText(String.valueOf(premiumListTeamSalesBVMatching.get(i).getAmount()));


    }
    @Override
    public int getItemCount() {
        return premiumListTeamSalesBVMatching.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView premium_team_salesbv_matching_count,premium_team_salesbv_matching_date,premium_team_salesbv_matching_leftsidesalesbv,premium_team_salesbv_matching_rightsidesalesbv;
        TextView premium_team_salesbv_matchingbv,premium_team_salesbv_matching_leftsalecarrybv,premium_team_salesbv_matching_rightsalecarrybv;
        TextView premium_team_salesbv_matching_amount;


        public ViewHolder(View view) {
            super(view);
            premium_team_salesbv_matching_count=view.findViewById(R.id.premium_team_salesbv_matching_count);
            premium_team_salesbv_matching_date=view.findViewById(R.id.premium_team_salesbv_matching_date);
            premium_team_salesbv_matching_leftsidesalesbv=view.findViewById(R.id.premium_team_salesbv_matching_leftsidesalesbv);
            premium_team_salesbv_matching_rightsidesalesbv=view.findViewById(R.id.premium_team_salesbv_matching_rightsidesalesbv);
            premium_team_salesbv_matchingbv=view.findViewById(R.id.premium_team_salesbv_matchingbv);
            premium_team_salesbv_matching_leftsalecarrybv=view.findViewById(R.id.premium_team_salesbv_matching_leftsalecarrybv);
            premium_team_salesbv_matching_rightsalecarrybv=view.findViewById(R.id.premium_team_salesbv_matching_rightsalecarrybv);
            premium_team_salesbv_matching_amount=view.findViewById(R.id.premium_team_salesbv_matching_amount);


        }
    }
}


