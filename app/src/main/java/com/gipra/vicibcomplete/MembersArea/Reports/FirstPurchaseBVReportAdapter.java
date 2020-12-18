package com.gipra.vicibcomplete.MembersArea.Reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class FirstPurchaseBVReportAdapter extends RecyclerView.Adapter<FirstPurchaseBVReportAdapter.ViewHolder>  {
    private List<ListFirstPurchaseBVReport> listFirstPurchaseBVReports;
    private Context context;


    public  FirstPurchaseBVReportAdapter(List<ListFirstPurchaseBVReport>listFirstPurchaseBVReports,Context context){
        this.listFirstPurchaseBVReports=listFirstPurchaseBVReports;
        this.context=context;
    }
    public FirstPurchaseBVReportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.first_purchasebv_report_list,viewGroup,false);
        return new FirstPurchaseBVReportAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(FirstPurchaseBVReportAdapter.ViewHolder viewHolder,final int i){

        viewHolder.first_purchase_count.setText(String.valueOf(listFirstPurchaseBVReports.get(i).getCount()));
        viewHolder.first_purchase_date.setText(listFirstPurchaseBVReports.get(i).getDActivation());
        viewHolder.first_purchase_productorder.setText(listFirstPurchaseBVReports.get(i).getOrderId());
        viewHolder.first_purchase_amount.setText(listFirstPurchaseBVReports.get(i).getNAmount());
        viewHolder.first_purchase_bv.setText(listFirstPurchaseBVReports.get(i).getNBV());



//
//        final String userimg = userStoryList.get(i).getUserImage();
//        Glide.with(context)
//                .load(userimg)
//                .into(viewHolder.userstory_userimg);
//        viewHolder.ll_storylist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=userStoryList.get(i).getTitle();
//                String des=userStoryList.get(i).getDescription();
//                String date=userStoryList.get(i).getAddedDate();
//
//                String username=userStoryList.get(i).getUserName();
//                String postago=userStoryList.get(i).getPostedAgo();
//                String st_id=userStoryList.get(i).getStoryId();
//
//                Intent i=new Intent(context,StoryMoreView.class);
//                i.putExtra("USERNAME",username);
//                i.putExtra("ST_NAME",name);
//                i.putExtra("ST_DESC",des);
//                i.putExtra("ST_DATE",date);
//
//                //  i.putExtra("ST_IMG",imgpath);
//                i.putExtra("POSTAGO",postago);
//                i.putExtra("USERIMG",userimg);
//                i.putExtra("STORYID",st_id);
//
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });



    }
    @Override
    public int getItemCount() {
        return listFirstPurchaseBVReports.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView first_purchase_count,first_purchase_date,first_purchase_productorder,first_purchase_amount,first_purchase_bv;


        public ViewHolder(View view) {
            super(view);
            first_purchase_count=view.findViewById(R.id.first_purchase_count);
            first_purchase_date=view.findViewById(R.id.first_purchase_date);
            first_purchase_productorder=view.findViewById(R.id.first_purchase_productorder);
            first_purchase_amount=view.findViewById(R.id.first_purchase_amount);
            first_purchase_bv=view.findViewById(R.id.first_purchase_bv);




        }
    }
}

