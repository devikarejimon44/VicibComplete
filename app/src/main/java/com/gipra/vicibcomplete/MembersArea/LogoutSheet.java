package com.gipra.vicibcomplete.MembersArea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gipra.vicibcomplete.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutSheet  extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logoutsheet,
                container, false);
        Button yes=view.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        Button no=view.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;

    }

    private void logout() {

        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String username=shpref.getString("USERNAME","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogout> usercall=api.Logout(username);
        usercall.enqueue(new Callback<ResponseLogout>() {
            @Override
            public void onResponse(Call<ResponseLogout> call, Response<ResponseLogout> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){


                    //startActivity(new Intent(getApplicationContext(),Login.class));
                    Intent intent = new Intent(getContext(), Login.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(getContext(), "Please exit the Application and Login again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogout> call, Throwable t) {


            }


});
}
    }
