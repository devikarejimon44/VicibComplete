package com.gipra.vicibcomplete.MembersArea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText currentpsd,newpsd,confirmpsd;
    Button submitpsd,cancelpsd;
    ImageView back_changepsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back_changepsd=findViewById(R.id.back_changepsd);
        back_changepsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        currentpsd=findViewById(R.id.currentpsd);
        newpsd=findViewById(R.id.newpsd);
        confirmpsd=findViewById(R.id.confirmpsd);
        submitpsd=findViewById(R.id.sumbitpsd);
        submitpsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changepsd();
                if(validate()) {
                    changepsd();
                }

            }
        });
//        cancelpsd=findViewById(R.id.cancelpsd);
//        cancelpsd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentpsd.setText("");
//                newpsd.setText("");
//                confirmpsd.setText("");
//            }
//        });
    }
    public  Boolean validate() {
        boolean valid = true;
        String cur = currentpsd.getText().toString();
        String n = newpsd.getText().toString();
        String con = confirmpsd.getText().toString();


        if (cur.isEmpty()) {
            valid=false;
            currentpsd.setError("Please enter password");
        } else if (n.isEmpty()) {
            valid=false;
            newpsd.setError("Please enter new password");
        } else if (!(n.length() >= 6)) {
            valid=false;
            confirmpsd.setError("Please enter confirm password");
        } else if (con.isEmpty()) {
            valid=false;
            confirmpsd.setError("Please enter confirm password");

        } else if (!con.equals(n)) {
            valid=false;
            confirmpsd.setError("Password not matching");

        }else{
                valid = true;
                changepsd();
            }

            return valid;
        }

    private  void changepsd(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String cpasd=currentpsd.getText().toString();
        String npsd=newpsd.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseChangePassword>usercall=api.Changepsd(id,cpasd,npsd);
        usercall.enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ChangePassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {

            }
        });

    }

}
