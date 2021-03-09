package com.gipra.vicibcomplete.MembersArea.MenuFragments.Complaints;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintStatus;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsStatusAdapter;
import com.gipra.vicibcomplete.MembersArea.Complaints.ListComplaintList;
import com.gipra.vicibcomplete.MembersArea.Complaints.ResponseComplaintsList;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompliantStatusFragment extends Fragment {

    RecyclerView Recycler_complaints_status;
    EditText comp_fromdate,comp_todate;
    Button comp_search;
    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    private List<ListComplaintList> listComplaintList;
    private ComplaintsStatusAdapter complaintsStatusAdapter;
    ShimmerFrameLayout m_shimmer_complaints_status;
    ImageView nodata_complaint_status;


    public CompliantStatusFragment() {
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
        View view=inflater.inflate(R.layout.fragment_compliant_status, container, false);

        m_shimmer_complaints_status=view.findViewById(R.id.m_shimmer_complaints_status);
        comp_fromdate=view.findViewById(R.id.comp_fromdate);
        comp_todate=view.findViewById(R.id.comp_todate);
        nodata_complaint_status=view.findViewById(R.id.nodata_complaint_status);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        //dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        comp_fromdate.setInputType(InputType.TYPE_NULL);
        comp_fromdate.requestFocus();
        comp_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        comp_todate.setInputType(InputType.TYPE_NULL);
        comp_todate.requestFocus();
        comp_todate.setOnClickListener(new View.OnClickListener() {
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
                comp_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                comp_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        Recycler_complaints_status=view.findViewById(R.id.Recycler_complaints_status);
        comp_search=view.findViewById(R.id.comp_search);
        comp_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComplaintsList();
            }
        });
        return view;
    }
    private void ComplaintsList() {
        m_shimmer_complaints_status.setVisibility(View.VISIBLE);
        m_shimmer_complaints_status.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=comp_fromdate.getText().toString();
        String tdate=comp_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseComplaintsList> usercall=api.ComplaintsList(Integer.parseInt(id),fdate,tdate);
        //   Call<ResponseComplaintsList> usercall=api.ComplaintsList(1,"2021-01-21","2021-01-22");
        usercall.enqueue(new Callback<ResponseComplaintsList>() {
            @Override
            public void onResponse(Call<ResponseComplaintsList> call, Response<ResponseComplaintsList> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_complaints_status.setVisibility(View.GONE);
                    m_shimmer_complaints_status.stopShimmerAnimation();
                    Recycler_complaints_status.setVisibility(View.VISIBLE);
                    nodata_complaint_status.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponseComplaintsList responseComplaintsList=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_complaints_status.setLayoutManager(layoutManager);
                    listComplaintList=responseComplaintsList.getData();
                    complaintsStatusAdapter=new ComplaintsStatusAdapter(listComplaintList,getContext());
                    Recycler_complaints_status.setAdapter(complaintsStatusAdapter);
                }
                else {
                    m_shimmer_complaints_status.setVisibility(View.GONE);
                    m_shimmer_complaints_status.stopShimmerAnimation();

                    nodata_complaint_status.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_complaint_status);
                }
            }
            @Override
            public void onFailure(Call<ResponseComplaintsList> call, Throwable t) {
                m_shimmer_complaints_status.setVisibility(View.GONE);
                m_shimmer_complaints_status.stopShimmerAnimation();
            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}