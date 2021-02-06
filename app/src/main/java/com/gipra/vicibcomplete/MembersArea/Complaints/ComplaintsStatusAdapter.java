package com.gipra.vicibcomplete.MembersArea.Complaints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.vicibcomplete.R;

import java.util.List;

public class ComplaintsStatusAdapter extends RecyclerView.Adapter<ComplaintsStatusAdapter.ViewHolder>  {
   private List<ListComplaintList> listComplaintList;
   private Context context;


        public  ComplaintsStatusAdapter(List<ListComplaintList>listComplaintList,Context context){
                        this.listComplaintList=listComplaintList;
                        this.context=context;
                        }
                public ComplaintsStatusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint_status_list,viewGroup,false);
                        return new ComplaintsStatusAdapter.ViewHolder(view);
                        }
                public  void onBindViewHolder(ComplaintsStatusAdapter.ViewHolder viewHolder,final int i){

                        viewHolder.comp_status_count.setText(String.valueOf(listComplaintList.get(i).getCount()));
                        viewHolder.comp_status_comp_date.setText(listComplaintList.get(i).getDDate());

                        viewHolder.comp_status_priority.setText(listComplaintList.get(i).getCPriority());
                        viewHolder.comp_status_subject.setText(listComplaintList.get(i).getCSubject());
                    viewHolder.comp_status_description.setText(listComplaintList.get(i).getCDescription());






                }
                @Override
                public int getItemCount() {
                        return listComplaintList.size();
                        }
                public  class ViewHolder extends RecyclerView.ViewHolder{

                    TextView comp_status_count,comp_status_comp_date,comp_status_priority,comp_status_subject;
                    TextView comp_status_description;


                    public ViewHolder(View view) {
                        super(view);
                        comp_status_count=view.findViewById(R.id.comp_status_count);
                        comp_status_comp_date=view.findViewById(R.id.comp_status_comp_date);

                        comp_status_priority=view.findViewById(R.id.comp_status_priority);
                        comp_status_subject=view.findViewById(R.id.comp_status_subject);
                        comp_status_description=view.findViewById(R.id.comp_status_description);



                    }
                }
                }


