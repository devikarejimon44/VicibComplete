package com.gipra.vicibcomplete.MembersArea.MenuFragments.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Reports.ListSponsorsList;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseSponsorsList;
import com.gipra.vicibcomplete.MembersArea.Reports.SponsorListAdapter;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SponsorsListFragment extends Fragment {

    RecyclerView recycler_sponsorlist;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    private List<ListSponsorsList> listSponsorsList;
    private SponsorListAdapter sponsorListAdapter;


    ShimmerFrameLayout m_shimmer_sponsorlist;
    ImageView nodata_sponsors_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sponsors_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Sponsors List");
        nodata_sponsors_list=view.findViewById(R.id.nodata_sponsors_list);
        recycler_sponsorlist=view.findViewById(R.id.recycler_sponsorlist);
        m_shimmer_sponsorlist=view.findViewById(R.id.m_shimmer_sponsorlist);
        SearchSponsorList();


       return view;
    }

    private void SearchSponsorList() {
        m_shimmer_sponsorlist.startShimmerAnimation();
        m_shimmer_sponsorlist.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        // Call<ResponseSponsorsList> usercall=api.searchsponsorlist(Integer.parseInt(id));
        Call<ResponseSponsorsList> usercall=api.searchsponsorlist(1);
        usercall.enqueue(new Callback<ResponseSponsorsList>() {
            @Override
            public void onResponse(Call<ResponseSponsorsList> call, Response<ResponseSponsorsList> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_sponsorlist.stopShimmerAnimation();
                    m_shimmer_sponsorlist.setVisibility(View.GONE);
                    recycler_sponsorlist.setVisibility(View.VISIBLE);

                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseSponsorsList responseSponsorsList=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_sponsorlist.setLayoutManager(layoutManager);
                    recycler_sponsorlist.setHasFixedSize(true);
                    listSponsorsList=responseSponsorsList.getData();
                    sponsorListAdapter=new SponsorListAdapter(listSponsorsList,getContext());
                    recycler_sponsorlist.setAdapter(sponsorListAdapter);
                }
                else {
                    m_shimmer_sponsorlist.stopShimmerAnimation();
                    m_shimmer_sponsorlist.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseSponsorsList> call, Throwable t) {
                m_shimmer_sponsorlist.stopShimmerAnimation();
                m_shimmer_sponsorlist.setVisibility(View.GONE);

                nodata_sponsors_list.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(R.drawable.nodatafound)
                        .into(nodata_sponsors_list);

            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }


}


