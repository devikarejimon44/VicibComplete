package com.gipra.vicibcomplete.MembersArea.Gene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gipra.vicibcomplete.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class StandardGeneBottomDailogue extends BottomSheetDialogFragment {
//    TextView p_username_one,p_name_one,p_sponsor_userid_one,p_sponsor_username_one,p_leftBV_one,p_rightBV_one,p_leftcount_one,p_rightcount_one;
//    private ArrayList<ListStandardPlanGenealogy> stgene;
//    String uid;
//    Context context;
//    String id;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.standard_genepopup,
                container, false);
//        SharedPreferences shpref;
//        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//
//
//        p_username_one=view.findViewById(R.id.p_username_one);
////        p_username_one.setText(u);
//
//        p_name_one=view.findViewById(R.id.p_name_one);
//        p_sponsor_userid_one=view.findViewById(R.id.p_sponsor_userid_one);
//        p_sponsor_username_one=view.findViewById(R.id.p_sponsor_username_one);
//        p_leftBV_one=view.findViewById(R.id.p_leftBV_one);
//        p_rightBV_one=view.findViewById(R.id.p_rightBV_one);
//        p_leftcount_one=view.findViewById(R.id.p_leftcount_one);
//        p_rightcount_one=view.findViewById(R.id.p_rightcount_one);
//
//        final ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseStandardGenealogy>call=api.StandardGene(Integer.parseInt(id));
//        call.enqueue(new Callback<ResponseStandardGenealogy>() {
//            @Override
//            public void onResponse(Call<ResponseStandardGenealogy> call, Response<ResponseStandardGenealogy> response) {
//                if (response.body().getStatus().equals("1")){
//                    final ResponseStandardGenealogy responseStandardGenealogy = response.body();
//                    stgene = (ArrayList<ListStandardPlanGenealogy>)responseStandardGenealogy.getData();
//                for(int i = 0; i == stgene.size(); i++){
//                    String  u_name=stgene.get(i).getUserid();
//                    p_username_one.setText(u_name);
//                    String  name=stgene.get(i).getName();
//                    p_name_one.setText(name);
//                    String  sp_uid=stgene.get(i).getSponsorId();
//                    p_sponsor_userid_one.setText(sp_uid);
//                    String  sp_uname=stgene.get(i).getSponsorName();
//                    p_sponsor_username_one.setText(sp_uname);
//                    String  l_one=stgene.get(i).getLeftActive();
//                    p_leftBV_one.setText(l_one);
//                    String  r_one=stgene.get(i).getRightActive();
//                    p_rightBV_one.setText(r_one);
//                    String  l_count=stgene.get(i).getLeftCount();
//                    p_leftcount_one.setText(l_count);
//                    String  r_count=stgene.get(i).getRightCount();
//                    p_rightcount_one.setText(r_count);
//                }
//
//
//                }
//                }
//
//                @Override
//            public void onFailure(Call<ResponseStandardGenealogy> call, Throwable t) {
//
//            }
//        });
        return view;
    }


}

