package com.gipra.vicibcomplete.MembersArea.Reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.vicibcomplete.R;

import java.util.List;

public class BasicActiveAdapter extends RecyclerView.Adapter<BasicActiveAdapter.ViewHolder>  {
private List<ListBasicActiveMembers> listBasicActiveMembers;
private Context context;


public  BasicActiveAdapter(List<ListBasicActiveMembers>listBasicActiveMembers,Context context){
        this.listBasicActiveMembers=listBasicActiveMembers;
        this.context=context;
        }
public BasicActiveAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basic_active_list,viewGroup,false);
        return new BasicActiveAdapter.ViewHolder(view);
        }
public  void onBindViewHolder(BasicActiveAdapter.ViewHolder viewHolder,final int i){

        viewHolder.basic_active_count.setText(String.valueOf(listBasicActiveMembers.get(i).getCount()));
        viewHolder.basic_active_name.setText(listBasicActiveMembers.get(i).getName());
        viewHolder.basice_active_username.setText(listBasicActiveMembers.get(i).getUsername());
        viewHolder.basic_active_joining_date.setText(listBasicActiveMembers.get(i).getJoiningdatedate());
        viewHolder.basic_active_activated_date.setText(listBasicActiveMembers.get(i).getActvatedddate());





        }
@Override
public int getItemCount() {
        return listBasicActiveMembers.size();
        }
public  class ViewHolder extends RecyclerView.ViewHolder{

    TextView basic_active_count,basic_active_name,basice_active_username,basic_active_joining_date,basic_active_activated_date;


    public ViewHolder(View view) {
        super(view);
        basic_active_count=view.findViewById(R.id.basic_active_count);
        basic_active_name=view.findViewById(R.id.basic_active_name);
        basice_active_username=view.findViewById(R.id.basice_active_username);
        basic_active_joining_date=view.findViewById(R.id.basic_active_joining_date);
        basic_active_activated_date=view.findViewById(R.id.basic_active_activated_date);




    }
}
}
