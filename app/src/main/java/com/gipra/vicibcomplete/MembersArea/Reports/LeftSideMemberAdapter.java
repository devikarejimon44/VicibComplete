package com.gipra.vicibcomplete.MembersArea.Reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class LeftSideMemberAdapter extends RecyclerView.Adapter<LeftSideMemberAdapter.ViewHolder>  {
    private List<ListLeftSideMembers> listLeftSideMembers;
    private Context context;


    public  LeftSideMemberAdapter(List<ListLeftSideMembers>listLeftSideMembers,Context context){
        this.listLeftSideMembers=listLeftSideMembers;
        this.context=context;
    }
    public LeftSideMemberAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_side_members_list,viewGroup,false);
        return new LeftSideMemberAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(LeftSideMemberAdapter.ViewHolder viewHolder,final int i){
        viewHolder.left_side_member_count.setText(String.valueOf(listLeftSideMembers.get(i).getCount1()));
        viewHolder.left_side_member_name.setText(listLeftSideMembers.get(i).getFname());
        viewHolder.left_side_member_username.setText(listLeftSideMembers.get(i).getChildusername());
        viewHolder.left_side_member_activated_date.setText(listLeftSideMembers.get(i).getDateofjoin());
      //   viewHolder.left_side_member_activated_date.setText(listLeftSideMembers.get(i).getActvatedddate());
        viewHolder.left_side_member_total_repurchase_bv.setText(String.valueOf(listLeftSideMembers.get(i).getRepurchasePv()));


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
        return listLeftSideMembers.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView left_side_member_count,left_side_member_name,left_side_member_username,left_side_member_joining_date,left_side_member_activated_date;
        TextView left_side_member_total_activation_bv,left_side_member_total_repurchase_bv;


        public ViewHolder(View view) {
            super(view);
            left_side_member_count=view.findViewById(R.id.left_side_member_count);
            left_side_member_name=view.findViewById(R.id.left_side_member_name);
            left_side_member_username=view.findViewById(R.id.left_side_member_username);
            left_side_member_joining_date=view.findViewById(R.id.left_side_member_joining_date);
            left_side_member_activated_date=view.findViewById(R.id.left_side_member_activated_date);
            left_side_member_total_activation_bv=view.findViewById(R.id.left_side_member_total_activation_bv);
            left_side_member_total_repurchase_bv=view.findViewById(R.id.left_side_member_total_repurchase_bv);



        }
    }
}
