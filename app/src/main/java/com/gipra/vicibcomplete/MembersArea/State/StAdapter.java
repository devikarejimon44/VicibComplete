package com.gipra.vicibcomplete.MembersArea.State;//package com.Vicib.vicibapp.State;
//
//        import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.BaseAdapter;
//        import android.widget.TextView;
//
//        import com.Vicib.vicibapp.R;
//
//        import java.util.ArrayList;
//
//public class StAdapter extends BaseAdapter {
//    private ArrayList<StateList> stat;
//    private Context context;
//    private LayoutInflater inflater;
//
//    public StAdapter(Context context, ArrayList<StateList> stat) {
//        this.context = context;
//        this.stat = stat;
//    }
//
//    @Override
//    public int getCount() {
//        return stat.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return i;
//    }
//
//
//    @Override    public long getItemId(int i) {
//        return i;
//    }
//    class Holder{
//        private TextView txtstate;
//    }
//    @Override    public View getView(int i, View convertView, ViewGroup viewGroup) {
//        View myView = null;
//        try {
//            Holder holder;
//            myView = convertView;
//
//
//            if (myView == null) {
//                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                myView = inflater.inflate(R.layout.activity_st_list_item, null);
////                holder=new Holder();
//                holder=new Holder();
//                holder.txtstate=(TextView)myView.findViewById(R.id.txtstate);
//                myView.setTag(holder);
//
//
//            } else {
//                holder = (Holder) myView.getTag();
//            }
//
//            holder.txtstate.setText(stat.get(i).getStateName());
//
////
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return myView;
//    }
//}
