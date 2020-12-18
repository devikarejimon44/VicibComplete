package com.gipra.vicibcomplete.MembersArea.Reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder>  {
    private List<ListMyProducts> listMyProducts;
    private Context context;


    public  MyProductsAdapter(List<ListMyProducts>listMyProducts,Context context){
        this.listMyProducts=listMyProducts;
        this.context=context;
    }
    public MyProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myproduct_list,viewGroup,false);
        return new MyProductsAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(MyProductsAdapter.ViewHolder viewHolder,final int i){
        viewHolder.myproduct_product.setText(listMyProducts.get(i).getProductname());
        viewHolder.myproduct_quantity.setText(listMyProducts.get(i).getBquantity());
        viewHolder.myproduct_amount.setText(listMyProducts.get(i).getDp());
        viewHolder.myproduct_totalamount.setText(String.valueOf(listMyProducts.get(i).getTotalAmount()));
        viewHolder.myproduct_BV.setText(String.valueOf(listMyProducts.get(i).getTotalBv()));
        viewHolder.myproduct_totalBV.setText(String.valueOf(listMyProducts.get(i).getTotalBv()));
        viewHolder.myproducts_total_amount.setText(String.valueOf(listMyProducts.get(i).getTotalAmount()));


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
        return listMyProducts.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView myproduct_product,myproduct_quantity,myproduct_amount,myproduct_totalamount;
        TextView myproduct_BV,myproduct_totalBV,myproducts_total_amount;



        public ViewHolder(View view) {
            super(view);
            myproduct_product=view.findViewById(R.id.myproduct_product);
            myproduct_quantity=view.findViewById(R.id.myproduct_quantity);
            myproduct_amount=view.findViewById(R.id.myproduct_amount);
            myproduct_totalamount=view.findViewById(R.id.myproduct_totalamount);
            myproduct_BV=view.findViewById(R.id.myproduct_BV);
            myproduct_totalBV=view.findViewById(R.id.myproduct_totalBV);
            myproducts_total_amount=view.findViewById(R.id.myproducts_total_amount);



        }
    }
}
