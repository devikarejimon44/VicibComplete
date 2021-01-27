package com.gipra.vicibcomplete.MembersArea.Reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gipra.vicibcomplete.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class BasicActiveMembers extends AppCompatActivity {
    EditText basic_active__fromdate,basic_active_todate;
    Button basic_active_search;

    DatePickerDialog from,to;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_active_members);
        Toolbar toolbar=findViewById(R.id.basic_acive_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        basic_active__fromdate=findViewById(R.id.basic_active__fromdate);
        basic_active_todate=findViewById(R.id.basic_active_todate);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        basic_active__fromdate.setInputType(InputType.TYPE_NULL);
        basic_active__fromdate.requestFocus();
        basic_active__fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });
        basic_active_todate.setInputType(InputType.TYPE_NULL);
        basic_active_todate.requestFocus();
        basic_active_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(BasicActiveMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                basic_active__fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        to = new DatePickerDialog(BasicActiveMembers.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                basic_active_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        basic_active_search=findViewById(R.id.basic_active_search);
        basic_active_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchBasicActive();

            }
        });
    }

    private void SearchBasicActive() {
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        Log.e("id",id);
        String fdate=basic_active__fromdate.getText().toString();
        String tdate=basic_active_todate.getText().toString();
        Toast.makeText(this, "No Date Found", Toast.LENGTH_SHORT).show();

    }


}