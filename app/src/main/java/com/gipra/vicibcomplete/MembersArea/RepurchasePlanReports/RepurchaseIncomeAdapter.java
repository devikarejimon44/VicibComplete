package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class RepurchaseIncomeAdapter extends RecyclerView.Adapter<RepurchaseIncomeAdapter.ViewHolder>  {
    private List<ListRepurchaseIncome> listRepurchaseBVReport;
    private Context context;


    public  RepurchaseIncomeAdapter(List<ListRepurchaseIncome>listRepurchaseBVReport,Context context){
        this.listRepurchaseBVReport=listRepurchaseBVReport;
        this.context=context;
    }
    public RepurchaseIncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repurchase_income_list,viewGroup,false);
        return new RepurchaseIncomeAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(RepurchaseIncomeAdapter.ViewHolder viewHolder,final int i){

        viewHolder.repurchase_income_count.setText(String.valueOf(listRepurchaseBVReport.get(i).getCount()));
        viewHolder.repurchase_income_datefrom.setText(listRepurchaseBVReport.get(i).getDFROM());
        viewHolder.repurchase_income_dateto.setText(listRepurchaseBVReport.get(i).getDTo());
        viewHolder.repurchase_income_totalbv.setText(listRepurchaseBVReport.get(i).getNBv());
        viewHolder.repurchase_income_level.setText(listRepurchaseBVReport.get(i).getNLevel());
        viewHolder.repurchase_income_percentage.setText(listRepurchaseBVReport.get(i).getNPrecentage());
        viewHolder.repurchase_income_grossamount.setText(String.valueOf(listRepurchaseBVReport.get(i).getTotalamount()));


    }
    @Override
    public int getItemCount() {
        return listRepurchaseBVReport.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView repurchase_income_count,repurchase_income_datefrom,repurchase_income_dateto,repurchase_income_totalbv;
        TextView repurchase_income_level,repurchase_income_percentage,repurchase_income_grossamount;


        public ViewHolder(View view) {
            super(view);
            repurchase_income_count=view.findViewById(R.id.repurchase_income_count);
            repurchase_income_datefrom=view.findViewById(R.id.repurchase_income_datefrom);
            repurchase_income_dateto=view.findViewById(R.id.repurchase_income_dateto);
            repurchase_income_totalbv=view.findViewById(R.id.repurchase_income_totalbv);
            repurchase_income_level=view.findViewById(R.id.repurchase_income_level);
            repurchase_income_percentage=view.findViewById(R.id.repurchase_income_percentage);
            repurchase_income_grossamount=view.findViewById(R.id.repurchase_income_grossamount);





        }
    }
}

