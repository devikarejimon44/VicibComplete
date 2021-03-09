package com.gipra.vicibcomplete.MembersArea.MenuFragments.PayoutLedger;

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

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Payout.ListPayoutLedger;
import com.gipra.vicibcomplete.MembersArea.Payout.PayoutLedger;
import com.gipra.vicibcomplete.MembersArea.Payout.PayoutLedgerAdapter;
import com.gipra.vicibcomplete.MembersArea.Payout.ResponsePayoutLedger;
import com.gipra.vicibcomplete.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PayoutLedgerFragment extends Fragment {

    RecyclerView Recycler_payoutledger;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;
    EditText payoutledger_fromdate,payoutledger_todate;
    Button payoutledger_search;
    private List<ListPayoutLedger> listPayoutLedger;
    private PayoutLedgerAdapter payoutLedgerAdapter;
    ShimmerFrameLayout m_shimmer_payout_ledger;
    ImageView nodata_payoutledger;



    public PayoutLedgerFragment() {
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
        View view= inflater.inflate(R.layout.fragment_payout_ledger, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Payout Ledger");
        m_shimmer_payout_ledger=view.findViewById(R.id.m_shimmer_payout_ledger);
        Recycler_payoutledger=view.findViewById(R.id.Recycler_payoutledger);
        payoutledger_fromdate=view.findViewById(R.id.payoutledger_fromdate);
        payoutledger_todate=view.findViewById(R.id.payoutledger_todate);
        nodata_payoutledger=view.findViewById(R.id.nodata_payoutledger);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        payoutledger_fromdate.setInputType(InputType.TYPE_NULL);
        payoutledger_fromdate.requestFocus();
        payoutledger_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        payoutledger_todate.setInputType(InputType.TYPE_NULL);
        payoutledger_todate.requestFocus();
        payoutledger_todate.setOnClickListener(new View.OnClickListener() {
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
                payoutledger_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                payoutledger_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        payoutledger_search=view.findViewById(R.id.payoutledger_search);
        payoutledger_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFirstPurchaseReport();
            }
        });


        return view;
    }
    private void searchFirstPurchaseReport() {
        m_shimmer_payout_ledger.setVisibility(View.VISIBLE);
        m_shimmer_payout_ledger.startShimmerAnimation();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=payoutledger_fromdate.getText().toString();
        String tdate=payoutledger_todate.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        //   Call<ResponseFirstPurchaseBVReport> usercall=api.SearchFirstPurchase(Integer.parseInt(id),fdate,tdate);
        Call<ResponsePayoutLedger> usercall=api.SearchPayoutLedger(1,"01/01/2018","01/11/2020");
        usercall.enqueue(new Callback<ResponsePayoutLedger>() {
            @Override
            public void onResponse(Call<ResponsePayoutLedger> call, Response<ResponsePayoutLedger> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    m_shimmer_payout_ledger.setVisibility(View.GONE);
                    m_shimmer_payout_ledger.stopShimmerAnimation();
                    Recycler_payoutledger.setVisibility(View.VISIBLE);
                    nodata_payoutledger.setVisibility(View.GONE);
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    ResponsePayoutLedger responsePayoutLedger=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Recycler_payoutledger.setLayoutManager(layoutManager);
                    listPayoutLedger=responsePayoutLedger.getData();
                    payoutLedgerAdapter=new PayoutLedgerAdapter(listPayoutLedger,getContext());
                    Recycler_payoutledger.setAdapter(payoutLedgerAdapter);
                }
                else {
                    m_shimmer_payout_ledger.setVisibility(View.GONE);
                    m_shimmer_payout_ledger.stopShimmerAnimation();
                    nodata_payoutledger.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(R.drawable.nodatafound)
                            .into(nodata_payoutledger);
                }
            }
            @Override
            public void onFailure(Call<ResponsePayoutLedger> call, Throwable t) {
                m_shimmer_payout_ledger.setVisibility(View.GONE);
                m_shimmer_payout_ledger.stopShimmerAnimation();
            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

}