package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class RepurchaseIncomeDetailsAdapter extends RecyclerView.Adapter<RepurchaseIncomeDetailsAdapter.ViewHolder>  {
    private List<ListRepurchaseIncomeDetails> listRepurchaseIncomeDetails;
    private Context context;


    public  RepurchaseIncomeDetailsAdapter(List<ListRepurchaseIncomeDetails>listRepurchaseIncomeDetails,Context context){
        this.listRepurchaseIncomeDetails=listRepurchaseIncomeDetails;
        this.context=context;
    }
    public RepurchaseIncomeDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repurchase_income_details_list,viewGroup,false);
        return new RepurchaseIncomeDetailsAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(RepurchaseIncomeDetailsAdapter.ViewHolder viewHolder,final int i){

        viewHolder.repurchase_incomedetails_count.setText(String.valueOf(listRepurchaseIncomeDetails.get(i).getCount()));
        viewHolder.repurchase_incomedetails_datefrom.setText(listRepurchaseIncomeDetails.get(i).getDFROM());
        viewHolder.repurchase_incomedetails_dateto.setText(listRepurchaseIncomeDetails.get(i).getDTo());
        viewHolder.repurchase_incomedetails_grossincome.setText(listRepurchaseIncomeDetails.get(i).getNBv());
        viewHolder.repurchase_incomedetails_deduction.setText(listRepurchaseIncomeDetails.get(i).getNLevel());
        viewHolder.repurchase_incomedetails_netamount.setText(listRepurchaseIncomeDetails.get(i).getNPrecentage());



    }
    @Override
    public int getItemCount() {
        return listRepurchaseIncomeDetails.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView repurchase_incomedetails_count,repurchase_incomedetails_datefrom,repurchase_incomedetails_dateto,repurchase_incomedetails_grossincome;
        TextView repurchase_incomedetails_deduction,repurchase_incomedetails_netamount;


        public ViewHolder(View view) {
            super(view);
            repurchase_incomedetails_count=view.findViewById(R.id.repurchase_incomedetails_count);
            repurchase_incomedetails_datefrom=view.findViewById(R.id.repurchase_incomedetails_datefrom);
            repurchase_incomedetails_dateto=view.findViewById(R.id.repurchase_incomedetails_dateto);
            repurchase_incomedetails_grossincome=view.findViewById(R.id.repurchase_incomedetails_grossincome);
            repurchase_incomedetails_deduction=view.findViewById(R.id.repurchase_incomedetails_deduction);
            repurchase_incomedetails_netamount=view.findViewById(R.id.repurchase_incomedetails_netamount);


        }
    }
}


