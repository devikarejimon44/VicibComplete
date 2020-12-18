package com.gipra.vicibcomplete.MembersArea.Complaints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.MembersArea.IDCard;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.MyProfile.MyProfile;
import com.gipra.vicibcomplete.MembersArea.ProductStore;
import com.gipra.vicibcomplete.R;
import com.gipra.vicibshoppy.activity.ShoppyHome;


public class ComplaintsRegistration extends AppCompatActivity {
    LinearLayout complaints_dash,complaints_productstore,complaints_compliants,complaints_idcard,compliants_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_registration);
        ImageView back_complaintregistration=findViewById(R.id.back_complaintregistration);
        back_complaintregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        complaints_dash=findViewById(R.id.complaints_dash);
        complaints_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        complaints_productstore=findViewById(R.id.complaints_productstore);
        complaints_productstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShoppyHome.class));
            }
        });
        complaints_compliants=findViewById(R.id.complaints_compliants);
        complaints_idcard=findViewById(R.id.complaints_idcard);
        complaints_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IDCard.class));
            }
        });
        compliants_profile=findViewById(R.id.compliants_profile);
        compliants_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyProfile.class));
            }
        });
    }
}