package com.gipra.vicibcomplete.MembersArea.State;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.gipra.vicibcomplete.R;

import java.util.ArrayList;

public class StateAdapter extends BaseAdapter {
    private ArrayList<StateList> stat;
    private Context context;
    private LayoutInflater inflater;
    private static final String TAG = "StateAdapter";
    public StateAdapter(Context context, ArrayList<StateList> stat){
        //  context=form;
        this.context=context;
        this.stat=stat;
    }

    @Override
    public int getCount() {
        return stat.size();
    }

    @Override
    public Object getItem(int position) {
        return stat.get(position);
    }

    @Override    public long getItemId(int i) {
        return i;
    }

//    public void setError(View view, String text) {
//        StateAdapter.Holder holder;
//        holder=new Holder();
//        if (null != holder.statee || holder.statee.getText().toString().equals("Select State")) {
//            holder.statee.setError("null");
//        } else {
//            Log.d(TAG, "textview is null");
//        }
//    }


   public class Holder{
         TextView statee;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View myView = null;
        try {
            Holder holder;
            myView = convertView;


            if (myView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.activity_state_list_item, null);
                holder=new Holder();
                holder.statee=(TextView)myView.findViewById(R.id.txtliststate);
                myView.setTag(holder);
                String s = (null != stat.get(i).getStateName()) ? stat.get(i).getStateName().toString() : "Select State";
                holder.statee.setText(s);

                if ("Select State".equals(s) && null == holder.statee) {

                }

            } else {
                holder = (Holder) myView.getTag();

            }

            holder.statee.setText(stat.get(i).getStateName());
            Log.e(TAG,stat.get(i).getStateName());

        } catch (Exception e) {
            Log.e(TAG,"Error"+e);
        }
//        Holder in = (Holder) getItem(i);

        return myView;
    }

}