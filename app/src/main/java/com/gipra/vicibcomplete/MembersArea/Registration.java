package com.gipra.vicibcomplete.MembersArea;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Registration extends AppCompatActivity {
 private Spinner reg_honorific,reg_position;
 EditText reg_name,reg_regdob,reg_emailid,reg_mobilenum,reg_address;
 TextInputEditText reg_pincode,reg_country,reg_state,reg_district,reg_panchayath;
 TextInputEditText reg_sponsoruserid,reg_uplineusername;
 TextView reg_sponsorusername;
 TextInputEditText reg_pancardnumber,reg_bankname,reg_branchname,reg_accnum,reg_ifsc;
 TextInputEditText reg_nomname,reg_nomrelation,reg_psd,reg_confirmpsd;
 Button reg_register,reg_reset;

    DatePickerDialog from;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView back_register=findViewById(R.id.back_register);
        back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        reg_name=findViewById(R.id.reg_name);
        reg_regdob=findViewById(R.id.reg_regdob);
        reg_emailid=findViewById(R.id.reg_emailid);
        reg_mobilenum=findViewById(R.id.reg_mobilenum);

        reg_address=findViewById(R.id.reg_address);
        reg_pincode=findViewById(R.id.reg_pincode);
        reg_country=findViewById(R.id.reg_country);
        reg_state=findViewById(R.id.reg_state);
        reg_district=findViewById(R.id.reg_district);
        reg_panchayath=findViewById(R.id.reg_panchayath);

        reg_sponsoruserid=findViewById(R.id.reg_sponsoruserid);
        reg_sponsorusername=findViewById(R.id.reg_sponsorusername);
        reg_uplineusername=findViewById(R.id.reg_uplineusername);

        reg_pancardnumber=findViewById(R.id.reg_pancardnumber);
        reg_bankname=findViewById(R.id.reg_bankname);
        reg_branchname=findViewById(R.id.reg_branchname);
        reg_accnum=findViewById(R.id.reg_accnum);
        reg_ifsc=findViewById(R.id.reg_ifsc);

        reg_nomname=findViewById(R.id.reg_nomname);
        reg_nomrelation=findViewById(R.id.reg_nomrelation);
        reg_psd=findViewById(R.id.reg_psd);
        reg_confirmpsd=findViewById(R.id.reg_confirmpsd);

        reg_register=findViewById(R.id.reg_register);
        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        reg_reset=findViewById(R.id.reg_reset);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        reg_regdob.setInputType(InputType.TYPE_NULL);
        reg_regdob.requestFocus();
        reg_regdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                reg_regdob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));




        reg_honorific=findViewById(R.id.reg_honorific);
        List<String> honorific=new ArrayList<>();
        honorific.add(0,"Select Honorific");
        honorific.add("Mr");
        honorific.add("Ms");
        honorific.add("Mrs");


        ArrayAdapter hadpater = new ArrayAdapter(this,android.R.layout.simple_spinner_item,honorific);
        hadpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_honorific.setAdapter(hadpater);
        reg_honorific.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Honorific")){

                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        reg_position=findViewById(R.id.reg_position);
        List<String> position=new ArrayList<>();
        position.add(0,"Select Position");
        position.add("Left");
        position.add("Right");


        ArrayAdapter padapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,position);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_position.setAdapter(padapter);
        reg_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Position")){

                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Register(){
//        String hon=reg_honorific.getSelectedItem().toString();
//        String name=reg_name.getText().toString();
//        String dob=reg_regdob.getText().toString();
//        String email=reg_emailid.getText().toString();
//        String mob=reg_mobilenum.getText().toString();
//
//        String add=reg_address.getText().toString();
//        String pin=reg_pincode.getText().toString();
//        String panchayath=reg_panchayath.getText().toString();
//        String upline=reg_uplineusername.getText().toString();
//        String panchayath=reg_pincode.getText().toString();

    }


}