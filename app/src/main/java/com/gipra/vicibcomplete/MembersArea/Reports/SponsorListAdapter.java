package com.gipra.vicibcomplete.MembersArea.Reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class SponsorListAdapter extends RecyclerView.Adapter<SponsorListAdapter.ViewHolder>  {
    private List<ListSponsorsList> listSponsorsList;
    private Context context;


    public  SponsorListAdapter(List<ListSponsorsList>listSponsorsList,Context context){
        this.listSponsorsList=listSponsorsList;
        this.context=context;
    }
    public SponsorListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sponsor_list_list,viewGroup,false);
        return new SponsorListAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(SponsorListAdapter.ViewHolder viewHolder,final int i){
        viewHolder.sponsorlist_count.setText(String.valueOf(listSponsorsList.get(i).getCount()));
        viewHolder.sponsorlist_name.setText(listSponsorsList.get(i).getFname());
        viewHolder.sponsorlist_username.setText(listSponsorsList.get(i).getChildusername());
        viewHolder.sponsorlist_joining_date.setText(listSponsorsList.get(i).getDateofjoin());
        viewHolder.sponsorlist_totalbv.setText(String.valueOf(listSponsorsList.get(i).getNCouponPv()));


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
        return listSponsorsList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView sponsorlist_count,sponsorlist_name,sponsorlist_username,sponsorlist_joining_date;
        TextView sponsorlist_activated_date,sponsorlist_totalbv;



        public ViewHolder(View view) {
            super(view);
            sponsorlist_count=view.findViewById(R.id.sponsorlist_count);
            sponsorlist_name=view.findViewById(R.id.sponsorlist_name);
            sponsorlist_username=view.findViewById(R.id.sponsorlist_username);
            sponsorlist_joining_date=view.findViewById(R.id.sponsorlist_joining_date);
            sponsorlist_activated_date=view.findViewById(R.id.sponsorlist_activated_date);
            sponsorlist_totalbv=view.findViewById(R.id.sponsorlist_totalbv);

        }
    }
}
