package com.gipra.vicibcomplete.MembersArea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputEditText username,password;
    Button signin;
    AVLoadingIndicatorView login_loader;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_loader=findViewById(R.id.login_loader);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    private void SignIn() {
        login_loader.setVisibility(View.VISIBLE);
        String name=username.getText().toString();
        String psd=password.getText().toString();

        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin>usercall=api.Login(name,psd);
        usercall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().getStatus().equals("1")){
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    login_loader.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    Toast.makeText(Login.this, "You are succesfully logged in", Toast.LENGTH_SHORT).show();
                    String id=response.body().getUserId();
                    String uname=response.body().getUsername();
                    String name=response.body().getName();
                    Log.i(TAG, "getname : " + name);
                    String mob=response.body().getMobile();
                    String email=response.body().getEmail();
                    String add=response.body().getAddress();
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("USERNAME",uname);
                    editor.putString("ID",id);
                    editor.putString("NAME",name);
                    editor.putString("MOBILE",mob);
                    editor.putString("EMAIL",email);
                    editor.putString("ADD",add);
                    editor.commit();
                }
                else {
                    login_loader.setVisibility(View.GONE);
                    Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(Login.this, "Something went error,Please try again later", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}