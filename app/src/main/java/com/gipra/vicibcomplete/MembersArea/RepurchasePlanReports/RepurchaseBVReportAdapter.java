package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class RepurchaseBVReportAdapter extends RecyclerView.Adapter<RepurchaseBVReportAdapter.ViewHolder>  {
    private List<ListRepurchaseBVReport> listRepurchaseBVReport;
    private Context context;


    public  RepurchaseBVReportAdapter(List<ListRepurchaseBVReport>listRepurchaseBVReport,Context context){
        this.listRepurchaseBVReport=listRepurchaseBVReport;
        this.context=context;
    }
    public RepurchaseBVReportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repurchase_bvreport_list,viewGroup,false);
        return new RepurchaseBVReportAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(RepurchaseBVReportAdapter.ViewHolder viewHolder,final int i){

        viewHolder.repurchase_bv_reports_count.setText(String.valueOf(listRepurchaseBVReport.get(i).getCount()));
        viewHolder.repurchase_bv_reports_date.setText(listRepurchaseBVReport.get(i).getDActivation());
        viewHolder.repurchase_bv_reports_product_orderid.setText(listRepurchaseBVReport.get(i).getOrderId());
        viewHolder.repurchase_bv_reports_amount.setText(listRepurchaseBVReport.get(i).getNAmount());
        viewHolder.repurchase_bv_reports_pv.setText(listRepurchaseBVReport.get(i).getNPV());


    }
    @Override
    public int getItemCount() {
        return listRepurchaseBVReport.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView repurchase_bv_reports_count,repurchase_bv_reports_date,repurchase_bv_reports_product_orderid,repurchase_bv_reports_amount;
        TextView repurchase_bv_reports_pv;


        public ViewHolder(View view) {
            super(view);
            repurchase_bv_reports_count=view.findViewById(R.id.repurchase_bv_reports_count);
            repurchase_bv_reports_date=view.findViewById(R.id.repurchase_bv_reports_date);
            repurchase_bv_reports_product_orderid=view.findViewById(R.id.repurchase_bv_reports_product_orderid);
            repurchase_bv_reports_amount=view.findViewById(R.id.repurchase_bv_reports_amount);
            repurchase_bv_reports_pv=view.findViewById(R.id.repurchase_bv_reports_pv);





        }
    }
}


