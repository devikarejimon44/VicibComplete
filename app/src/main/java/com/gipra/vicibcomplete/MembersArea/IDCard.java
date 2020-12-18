package com.gipra.vicibcomplete.MembersArea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.MyProfile.MyProfile;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageView;
import com.gipra.vicibcomplete.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IDCard extends AppCompatActivity {
    LinearLayout id_dash,id_productstore,id_compliants,id_profile;
    CircleImageView id_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_card);
        ImageView back_idcard=findViewById(R.id.back_idcard);
        back_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        id_dash=findViewById(R.id.id_dash);
        id_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        id_productstore=findViewById(R.id.id_productstore);
        id_productstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        id_compliants=findViewById(R.id.id_compliants);
        id_compliants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ComplaintsRegistration.class));
            }
        });
        id_profile=findViewById(R.id.id_profile);
        id_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyProfile.class));
            }
        });
        id_image=findViewById(R.id.id_image);
        ViewPhoto();

    }
    private  void ViewPhoto(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseImageView> call = api.ViewPhoto(Integer.parseInt(u));
        call.enqueue(new Callback<ResponseImageView>() {
            @Override
            public void onResponse(Call<ResponseImageView> call, Response<ResponseImageView> response) {
                String img=response.body().getPath();
                if (response.body().getStatus().equals("1")) {
                    Log.e("pathh",img);
                    Glide.with(getApplicationContext())
                            .load(img)
                            .into(id_image);
                }
            }
            @Override
            public void onFailure(Call<ResponseImageView> call, Throwable t) {

            }
        });
    }
}