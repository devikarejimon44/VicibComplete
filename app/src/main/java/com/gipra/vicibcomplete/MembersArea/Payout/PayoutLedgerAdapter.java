package com.gipra.vicibcomplete.MembersArea.Payout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.gipra.vicibcomplete.R;

import java.util.List;

public class PayoutLedgerAdapter  extends RecyclerView.Adapter<PayoutLedgerAdapter.ViewHolder>  {
    private List<ListPayoutLedger> listPayoutLedger;
    private Context context;


    public  PayoutLedgerAdapter(List<ListPayoutLedger>listPayoutLedger,Context context){
        this.listPayoutLedger=listPayoutLedger;
        this.context=context;
    }
    public PayoutLedgerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payoutledger_list,viewGroup,false);
        return new PayoutLedgerAdapter.ViewHolder(view);
    }
    public  void onBindViewHolder(PayoutLedgerAdapter.ViewHolder viewHolder,final int i){

        viewHolder.payoutledger_count.setText(String.valueOf(listPayoutLedger.get(i).getCount()));
        viewHolder.payoutledger_date.setText(listPayoutLedger.get(i).getDTranscation());
//        viewHolder.payoutledger_description.setText(listPayoutLedger.get(i).gte());gte
        viewHolder.payoutledger_amount.setText(listPayoutLedger.get(i).getNTransAmount());
        viewHolder.payoutledger_balance.setText(listPayoutLedger.get(i).getNAccbalanceAfter());





    }
    @Override
    public int getItemCount() {
        return listPayoutLedger.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView payoutledger_count,payoutledger_date,payoutledger_description,payoutledger_amount;
        TextView payoutledger_balance;


        public ViewHolder(View view) {
            super(view);
            payoutledger_count=view.findViewById(R.id.payoutledger_count);
            payoutledger_date=view.findViewById(R.id.payoutledger_date);
            payoutledger_description=view.findViewById(R.id.payoutledger_description);
            payoutledger_amount=view.findViewById(R.id.payoutledger_amount);
            payoutledger_balance=view.findViewById(R.id.payoutledger_balance);



        }
    }
}



