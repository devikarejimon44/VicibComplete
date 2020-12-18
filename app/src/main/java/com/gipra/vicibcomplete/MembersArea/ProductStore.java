package com.gipra.vicibcomplete.MembersArea;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.R;

public class ProductStore extends AppCompatActivity {
    private WebView sheroweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_shero);
        sheroweb=findViewById(R.id.sheroweb);
        WebSettings webSettings=sheroweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        sheroweb.loadUrl("https://www.vicibhomelyindia.com/home");
        sheroweb.setWebViewClient(new WebViewClient());

    }
    public void onBackPressed() {
        if (sheroweb.canGoBack()){
            sheroweb.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
