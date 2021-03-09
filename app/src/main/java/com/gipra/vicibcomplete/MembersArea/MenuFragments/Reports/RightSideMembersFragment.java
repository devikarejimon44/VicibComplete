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

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Reports.LeftSideMemberAdapter;
import com.gipra.vicibcomplete.MembersArea.Reports.ListLeftSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseLeftSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.RightSideMembers;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RightSideMembersFragment extends Fragment {


    EditText right_side_members_fromdate,right_side_members_todate;
    Button right_side_members_search;
    RecyclerView recycler_right_side_members;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    private List<ListLeftSideMembers> listLeftSideMembers;
    private LeftSideMemberAdapter leftSideMemberAdapter;


    ShimmerFrameLayout m_shimmer_right_side_member;
    ImageView nodata_right_side_member;


    public RightSideMembersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_right_side_members, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Right Side Members");

        right_side_members_fromdate=view.findViewById(R.id.right_side_members_fromdate);
        right_side_members_todate=view.findViewById(R.id.right_side_members_todate);
        right_side_members_search=view.findViewById(R.id.right_side_members_search);
        recycler_right_side_members=view.findViewById(R.id.recycler_right_side_members);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        right_side_members_fromdate.setInputType(InputType.TYPE_NULL);
        right_side_members_fromdate.requestFocus();
        right_side_members_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        right_side_members_todate.setInputType(InputType.TYPE_NULL);
        right_side_members_todate.requestFocus();
        right_side_members_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                right_side_members_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                right_side_members_todate.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        right_side_members_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchLeftSideMember();

            }
        });
        m_shimmer_right_side_member=view.findViewById(R.id.m_shimmer_right_side_member);
        nodata_right_side_member=view.findViewById(R.id.nodata_right_side_member);
       return view;
    }
    private void SearchLeftSideMember() {
        m_shimmer_right_side_member.setVisibility(View.VISIBLE);
        m_shimmer_right_side_member.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=right_side_members_fromdate.getText().toString();
        String tdate=right_side_members_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseLeftSideMembers> usercall=api.SearchLeftSidemembers(32971,fdate,tdate,"right");
        usercall.enqueue(new Callback<ResponseLeftSideMembers>() {
            @Override
            public void onResponse(Call<ResponseLeftSideMembers> call, Response<ResponseLeftSideMembers> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_right_side_member.stopShimmerAnimation();
                    m_shimmer_right_side_member.setVisibility(View.GONE);
                    recycler_right_side_members.setVisibility(View.VISIBLE);
                    nodata_right_side_member.setVisibility(View.GONE);

                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseLeftSideMembers responseFirstPurchaseBVReport=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_right_side_members.setLayoutManager(layoutManager);
                    recycler_right_side_members.setHasFixedSize(true);
                    listLeftSideMembers=responseFirstPurchaseBVReport.getData();
                    leftSideMemberAdapter=new LeftSideMemberAdapter(listLeftSideMembers,getContext());
                    recycler_right_side_members.setAdapter(leftSideMemberAdapter);
                }
                else {
                    m_shimmer_right_side_member.stopShimmerAnimation();
                    m_shimmer_right_side_member.setVisibility(View.GONE);
                    nodata_right_side_member.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_right_side_member);
                }
            }
            @Override
            public void onFailure(Call<ResponseLeftSideMembers> call, Throwable t) {
                m_shimmer_right_side_member.stopShimmerAnimation();
                m_shimmer_right_side_member.setVisibility(View.GONE);


            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
       getActivity().finish();
    }
}