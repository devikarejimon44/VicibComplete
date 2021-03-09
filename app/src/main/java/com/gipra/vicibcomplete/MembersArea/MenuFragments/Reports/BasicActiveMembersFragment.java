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
import com.gipra.vicibcomplete.MembersArea.Reports.BasicActiveAdapter;
import com.gipra.vicibcomplete.MembersArea.Reports.BasicActiveMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.ListBasicActiveMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseBasicActiveMemebers;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BasicActiveMembersFragment extends Fragment {

    EditText basic_active__fromdate,basic_active_todate;
    Button basic_active_search;
    RecyclerView recyler_basic_active;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    private List<ListBasicActiveMembers> listBasicActiveMembers;
    private BasicActiveAdapter basicActiveAdapter;

    ShimmerFrameLayout m_shimmer_basic_active;
    ImageView nodata_basic_active_member;


    public BasicActiveMembersFragment() {
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
        View view= inflater.inflate(R.layout.fragment_basic_active_members, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Basic Active Members");
        nodata_basic_active_member=view.findViewById(R.id.nodata_basic_active_member);


        basic_active__fromdate=view.findViewById(R.id.basic_active__fromdate);
        basic_active_todate=view.findViewById(R.id.basic_active_todate);
        recyler_basic_active=view.findViewById(R.id.recyler_basic_active);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        basic_active__fromdate.setInputType(InputType.TYPE_NULL);
        basic_active__fromdate.requestFocus();
        basic_active__fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        basic_active_todate.setInputType(InputType.TYPE_NULL);
        basic_active_todate.requestFocus();
        basic_active_todate.setOnClickListener(new View.OnClickListener() {
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
                basic_active__fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                basic_active_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        basic_active_search=view.findViewById(R.id.basic_active_search);
        basic_active_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchBasicActive();

            }
        });
        m_shimmer_basic_active=view.findViewById(R.id.m_shimmer_basic_active);

        return view;
    }


    private void SearchBasicActive() {
        m_shimmer_basic_active.setVisibility(View.VISIBLE);
        m_shimmer_basic_active.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=basic_active__fromdate.getText().toString();
        String tdate=basic_active_todate.getText().toString();

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponseBasicActiveMemebers> usercall=api.SearchBasicActive(34898,"2021/01/28","2021/01/28");
        usercall.enqueue(new Callback<ResponseBasicActiveMemebers>() {
            @Override
            public void onResponse(Call<ResponseBasicActiveMemebers> call, Response<ResponseBasicActiveMemebers> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_basic_active.stopShimmerAnimation();
                    m_shimmer_basic_active.setVisibility(View.GONE);
                    recyler_basic_active.setVisibility(View.VISIBLE);
                    nodata_basic_active_member.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseBasicActiveMemebers responseBasicActiveMemebers=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyler_basic_active.setLayoutManager(layoutManager);
                    recyler_basic_active.setHasFixedSize(true);
                    listBasicActiveMembers=responseBasicActiveMemebers.getData();
                    basicActiveAdapter=new BasicActiveAdapter(listBasicActiveMembers,getContext());
                    recyler_basic_active.setAdapter(basicActiveAdapter);
                }
                else {
                    m_shimmer_basic_active.stopShimmerAnimation();
                    m_shimmer_basic_active.setVisibility(View.GONE);
                    nodata_basic_active_member.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_basic_active_member);
                }
            }
            @Override
            public void onFailure(Call<ResponseBasicActiveMemebers> call, Throwable t) {
                m_shimmer_basic_active.stopShimmerAnimation();
                m_shimmer_basic_active.setVisibility(View.GONE);


            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }


}