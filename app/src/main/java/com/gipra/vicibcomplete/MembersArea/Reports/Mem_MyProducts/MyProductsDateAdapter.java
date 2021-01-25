package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gipra.vicibcomplete.R;

import java.util.List;

public class MyProductsDateAdapter extends RecyclerView.Adapter<MyProductsDateAdapter.ViewHolder>{
    private List<ListMyProductsDate> listMyProductsDate;
    private Context context;
    private static final String TAG = "MyProductsDateAdapter";
    public  MyProductsDateAdapter(List<ListMyProductsDate>listMyProductsDate,Context context){
        this.listMyProductsDate=listMyProductsDate;
        this.context=context;
    }
    public MyProductsDateAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_products_viewmore,viewGroup,false);
        return new MyProductsDateAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(MyProductsDateAdapter.ViewHolder viewHolder,final int i){
        try {
            viewHolder.myproduct_product.setText(listMyProductsDate.get(i).getProductname());
            viewHolder.myproduct_quantity.setText(listMyProductsDate.get(i).getBquantity());
            viewHolder.myproduct_amount.setText(listMyProductsDate.get(i).getDp());
            viewHolder.myproduct_totalamount.setText(String.valueOf(listMyProductsDate.get(i).getTotalAmount()));
            viewHolder.myproduct_BV.setText(String.valueOf(listMyProductsDate.get(i).getTotalBv()));
            viewHolder.myproduct_totalBV.setText(String.valueOf(listMyProductsDate.get(i).getTotalBv()));
        }catch (Exception e){
            Log.e(TAG, "error" + e);
        }
      




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
        return listMyProductsDate.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView myproduct_product,myproduct_quantity,myproduct_amount,myproduct_totalamount;
        TextView myproduct_BV,myproduct_totalBV;


        public ViewHolder(View view) {
            super(view);
            myproduct_product=view.findViewById(R.id.myproduct_product);
            myproduct_quantity=view.findViewById(R.id.myproduct_quantity);
            myproduct_amount=view.findViewById(R.id.myproduct_amount);
            myproduct_totalamount=view.findViewById(R.id.myproduct_totalamount);
            myproduct_BV=view.findViewById(R.id.myproduct_BV);
            myproduct_totalBV=view.findViewById(R.id.myproduct_totalBV);

        }
    }
}
