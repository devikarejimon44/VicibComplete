package com.gipra.vicibcomplete.MembersArea.District;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.gipra.vicibcomplete.R;

import java.util.ArrayList;

public class DistrictAdapter extends BaseAdapter {
    private static final String TAG ="dist" ;
    private Context context;
    private ArrayList<DistrictList>dist;
    private LayoutInflater inflater;
    public DistrictAdapter(Context context,ArrayList<DistrictList>dist){
        this.context=context;
        this.dist=dist;
    }
    @Override
    public int getCount() {
        return dist.size();
    }
    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

//    public void setError(View view, String text) {
//        DistrictAdapter.Holder holder;
//        holder=new Holder();
//        if (null != holder.txtdist || holder.txtdist.getText().toString().equals("Select District")) {
//            holder.txtdist.setError("null");
//        } else {
//            Log.d(TAG, "textview is null");
//        }
//
//
//    }

    class Holder{
        private TextView txtdist;
    }
    @Override    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View myView = null;
        try {
            Holder holder;
            myView = convertView;


            if (myView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.activity_district_list_item, null);
                holder=new Holder();
                holder.txtdist=(TextView)myView.findViewById(R.id.txtdist);
                myView.setTag(holder);


            } else {
                holder = (Holder) myView.getTag();
            }

            holder.txtdist.setText(dist.get(i).getDistrictName());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return myView;
    }

}

