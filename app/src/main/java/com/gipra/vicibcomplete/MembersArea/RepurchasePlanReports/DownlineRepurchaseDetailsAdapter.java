package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class DownlineRepurchaseDetailsAdapter extends RecyclerView.Adapter<DownlineRepurchaseDetailsAdapter.ViewHolder>  {
private List<ListDownlineRepurchaseDetails> listDownlineRepurchaseDetails;
private Context context;


public  DownlineRepurchaseDetailsAdapter(List<ListDownlineRepurchaseDetails>listDownlineRepurchaseDetails,Context context){
        this.listDownlineRepurchaseDetails=listDownlineRepurchaseDetails;
        this.context=context;
        }
public DownlineRepurchaseDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.downline_repurchase_details_list,viewGroup,false);
        return new DownlineRepurchaseDetailsAdapter.ViewHolder(view);
        }
public  void onBindViewHolder(DownlineRepurchaseDetailsAdapter.ViewHolder viewHolder,final int i){

        viewHolder.downline_repurchase_level.setText(String.valueOf(listDownlineRepurchaseDetails.get(i).getLevel()));
        viewHolder.downline_repurchase_no_members.setText(String.valueOf(listDownlineRepurchaseDetails.get(i).getCount()));
        viewHolder.downline_repurchase_dateofpurchase.setText(listDownlineRepurchaseDetails.get(i).getDActivation());
        viewHolder.downline_repurchase_username.setText(listDownlineRepurchaseDetails.get(i).getCUsername());
        viewHolder.downline_repurchase_purchase_amount.setText(listDownlineRepurchaseDetails.get(i).getNAmount());
        viewHolder.downline_repurchase_productbv.setText(listDownlineRepurchaseDetails.get(i).getNPV());
        viewHolder.downline_repurchase__repurchase_income.setText(String.valueOf(listDownlineRepurchaseDetails.get(i).getLevelbv()));



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
        return listDownlineRepurchaseDetails.size();
        }
public  class ViewHolder extends RecyclerView.ViewHolder{

    TextView downline_repurchase_level,downline_repurchase_no_members,downline_repurchase_dateofpurchase,downline_repurchase_username;
    TextView downline_repurchase_purchase_amount,downline_repurchase_productbv,downline_repurchase__repurchase_income;

    public ViewHolder(View view) {
        super(view);
        downline_repurchase_level=view.findViewById(R.id.downline_repurchase_level);
        downline_repurchase_no_members=view.findViewById(R.id.downline_repurchase_no_members);
        downline_repurchase_dateofpurchase=view.findViewById(R.id.downline_repurchase_dateofpurchase);
        downline_repurchase_username=view.findViewById(R.id.downline_repurchase_username);
        downline_repurchase_purchase_amount=view.findViewById(R.id.downline_repurchase_purchase_amount);
        downline_repurchase_productbv=view.findViewById(R.id.downline_repurchase_productbv);
        downline_repurchase__repurchase_income=view.findViewById(R.id.downline_repurchase__repurchase_income);




    }
}
}

