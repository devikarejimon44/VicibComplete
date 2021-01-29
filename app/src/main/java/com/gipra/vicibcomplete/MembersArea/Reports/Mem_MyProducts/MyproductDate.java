package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyproductDate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyproductDate extends Fragment {
    RecyclerView recycler_myproduct_more;
    private List<ListMyProductsDateOnly> listMyProducts;
    private MyProductsAdapter myProductsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyproductDate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyproductDate.
     */
    // TODO: Rename and change types and number of parameters
    public static MyproductDate newInstance(String param1, String param2) {
        MyproductDate fragment = new MyproductDate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_myproduct_date, container, false);
        recycler_myproduct_more=view.findViewById(R.id.recycler_myproduct_more);
       // showproducts();
        return  view;
    }
//    private void showproducts() {
//        SharedPreferences shpref;
//        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String id=shpref.getString("ID","");
//        Log.e("id",id);
//
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseMyProducts> usercall=api.searchmyproducts(1,"06-01-2020","08-09-2020");
//        //  Call<ResponseMyProducts> usercall=api.searchmyproducts(1,fdate,tdate);
//        usercall.enqueue(new Callback<ResponseMyProducts>() {
//            @Override
//            public void onResponse(Call<ResponseMyProducts> call, Response<ResponseMyProducts> response) {
//                Log.i("onResponse", new Gson().toJson(response.body()));
//                if (response.body().getStatus().equals("1")){
//
//                    Log.i("onResponse", new Gson().toJson(response.body()));
//                    ResponseMyProducts responseMyProducts=response.body();
//
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_myproduct_more.setLayoutManager(layoutManager);
//                    recycler_myproduct_more.setHasFixedSize(true);
//                    listMyProducts=responseMyProducts.getData();
//                    myProductsAdapter=new MyProductsAdapter(listMyProducts,getActivity());
//                    recycler_myproduct_more.setAdapter(myProductsAdapter);
//
////                    for (int i = 0; i < listMyProducts.size(); i++) {
////                        String orderid = listMyProducts.get(i).getOrderid();
////                        SharedPreferences sharedPreferences;
////                        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
////                        SharedPreferences.Editor editor = sharedPreferences.edit();
////                        editor.putString("ORDERID", orderid);
////                        editor.commit();
////                    }
//                }
//                else {
//                    Toast.makeText(getContext(), "fswfsfs", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseMyProducts> call, Throwable t) {
//
//            }
//        });
//
//    }
}