package com.gipra.vicibcomplete.MembersArea.Gene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.Registration;
import com.gipra.vicibcomplete.MembersArea.ResponseRegistration;
import com.gipra.vicibcomplete.R;
import com.google.android.material.textfield.TextInputEditText;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegVaccant extends AppCompatActivity {

    TextView reg_vac_position,reg_vac_uplineusername;
    TextInputEditText reg_vac_name,reg_vac_dob,reg_vac_emailid,reg_vac_mobilenum,reg_vac_address;
    TextInputEditText reg_vac_pincode,reg_vac_panchayath;
    TextInputEditText reg_vac_sponsorusername;
    TextView reg_vac_check_msg,reg_vac_check_upline;
    TextView reg_vac_sp_name;
    TextInputEditText reg_vac_pancardnumber,reg_vac_bankname,reg_vac_branchname,reg_vac_accnum,reg_vac_ifsc;
    TextInputEditText reg_vac_nomname,reg_vac_nomrelation,reg_vac_psd,reg_vac_confirmpsd;
    Button reg_vac_register,reg_vac_reset;
    Spinner reg_vac_country,reg_vac_state,reg_vac_district;
    AVLoadingIndicatorView reg_vac__loader;
    LinearLayout ll_sponsor_check;

    TextView error_position,error_country;

    DatePickerDialog from;
    SimpleDateFormat dateFormatter;

    JSONObject jsonObject,cjsonObject;
    String st_id,d_id;
    private static final String TAG = "Registration";
    String s,up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vaccant);


        Toolbar toolbar=findViewById(R.id.reg_vac_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        reg_vac__loader=findViewById(R.id.reg_vac__loader);
        reg_vac_name=findViewById(R.id.reg_vac_name);
        reg_vac_dob=findViewById(R.id.reg_vac_dob);
        reg_vac_emailid=findViewById(R.id.reg_vac_emailid);
        reg_vac_mobilenum=findViewById(R.id.reg_vac_mobilenum);

        reg_vac_address=findViewById(R.id.reg_vac_address);
        reg_vac_pincode=findViewById(R.id.reg_vac_pincode);
        reg_vac_country=findViewById(R.id.reg_vac_country);
        reg_vac_state=findViewById(R.id.reg_vac_state);
        reg_vac_district=findViewById(R.id.reg_vac_district);
        reg_vac_panchayath=findViewById(R.id.reg_vac_panchayath);
        ll_sponsor_check=findViewById(R.id.ll_sponsor_check);
        reg_vac_check_msg=findViewById(R.id.reg_vac_check_msg);
        reg_vac_sp_name=findViewById(R.id.reg_vac_sp_name);

        reg_vac_sponsorusername=findViewById(R.id.reg_vac_sponsorusername);
        reg_vac_sponsorusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                reg_vac_check_msg.setText("");
//                reg_vac_sp_name.setText("");
               if(reg_vac_sponsorusername.getText().toString().equals(1)) {
                   reg_vac_sponsorusername.setError("sdsdsd");
               }




            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                spcheck();


            }

            @Override
            public void afterTextChanged(Editable editable) {
//                reg_vac_check_msg.setText("");
//                reg_vac_sp_name.setText("");

            }
        });

        reg_vac_uplineusername=findViewById(R.id.reg_vac_uplineusername);

        reg_vac_pancardnumber=findViewById(R.id.reg_vac_pancardnumber);
        reg_vac_bankname=findViewById(R.id.reg_vac_bankname);
        reg_vac_branchname=findViewById(R.id.reg_vac_branchname);
        reg_vac_accnum=findViewById(R.id.reg_vac_accnum);
        reg_vac_ifsc=findViewById(R.id.reg_vac_ifsc);

        reg_vac_nomname=findViewById(R.id.reg_vac_nomname);
        reg_vac_nomrelation=findViewById(R.id.reg_vac_nomrelation);
        reg_vac_psd=findViewById(R.id.reg_vac_psd);
        reg_vac_confirmpsd=findViewById(R.id.reg_vac_confirmpsd);

        reg_vac_register=findViewById(R.id.reg_vac_register);
        reg_vac_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    Register();
                }

            }
        });
        reg_vac_reset=findViewById(R.id.reg_vac_reset);
        reg_vac_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Reset();

            }
        });
        reg_vac_position=findViewById(R.id.reg_vac_position);
        Intent i=getIntent();
        s=i.getStringExtra("SIDE");
        reg_vac_position.setText(s);
        up=i.getStringExtra("UPLINE");
        reg_vac_uplineusername.setText(up);


        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        reg_vac_dob.setInputType(InputType.TYPE_NULL);
//        reg_regdob.requestFocus();
        reg_vac_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(RegVaccant.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                reg_vac_dob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        loadState();

        List<String> co=new ArrayList<>();
        co.add(0,"Select Country");
        co.add("India");

        ArrayAdapter cadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,co);
        cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_vac_country.setAdapter(cadapter);
        reg_vac_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Country")){

                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        reg_honorific=findViewById(R.id.reg_honorific);
//        List<String> honorific=new ArrayList<>();
//        honorific.add(0,"Select Honorific");
//        honorific.add("Mr");
//        honorific.add("Ms");
//        honorific.add("Mrs");
//
//
//        ArrayAdapter hadpater = new ArrayAdapter(this,android.R.layout.simple_spinner_item,honorific);
//        hadpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        reg_honorific.setAdapter(hadpater);
//        reg_honorific.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(adapterView.getItemAtPosition(i).equals("Select Honorific")){
//
//                }else {
//                    final String item=adapterView.getItemAtPosition(i).toString();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        List<String> position=new ArrayList<>();
//        position.add(0,"Select Position");
//        position.add("L");
//        position.add("R");

//
//        ArrayAdapter padapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,position);
//        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        reg_vac_position.setAdapter(padapter);
//        reg_vac_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(adapterView.getItemAtPosition(i).equals("Select Position")){
//                    //Toast.makeText(getBaseContext(), list.get(arg2).toString(),
//                    //				Toast.LENGTH_SHORT).show();
//                    p=adapterView.getSelectedItem().toString();
//
//
//                }else {
//                    final String item=adapterView.getItemAtPosition(i).toString();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    private void spcheck() {
        final String spname = reg_vac_sponsorusername.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/sponsor_check",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseData(response);

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("c_sponsor_name", spname);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void parseData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sp=jsonObject.getString("sponsor_name");
            String msg=jsonObject.getString("message");

            if (jsonObject.getString("status").equals("1")) {
                reg_vac_sp_name.setText(sp);

            }else {
                reg_vac_check_msg.setText(msg);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void loadState(){
        RequestQueue requestQueue=new Volley().newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/State_list",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        try {
                            cjsonObject = new JSONObject(response);
                            if (cjsonObject.getString("status").equals("1")) {
                                final JSONArray jsonArray = cjsonObject.getJSONArray("data");
                                Log.e(TAG, cjsonObject.length() + "");
                                List<String> statearray = new ArrayList<String>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String state_name = jsonArray.getJSONObject(i).getString("state_name");

                                    // con_id=jsonArray.getJSONObject(i).getString("country_id");
                                    Log.e(TAG, state_name + "");
                                    Log.e(TAG, st_id + "");
                                    statearray.add(state_name);

                                }
                                Log.e(TAG, statearray.toString());
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.country_list, R.id.country_name, statearray);
                                reg_vac_state.setAdapter(arrayAdapter);

                                reg_vac_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        try {
                                            st_id=jsonArray.getJSONObject(i).getString("state_code");
                                            loadDistrict();
                                            Log.e(TAG,st_id);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            } else {
                                //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error+"");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void loadDistrict(){
        RequestQueue requestQueue=new Volley().newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/District_list",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        try {
                            cjsonObject = new JSONObject(response);
                            if (cjsonObject.getString("status").equals("1")) {
                                final JSONArray jsonArray = cjsonObject.getJSONArray("data");
                                Log.e(TAG, cjsonObject.length() + "");
                                List<String> distarray = new ArrayList<String>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String dist_name = jsonArray.getJSONObject(i).getString("district_name");

                                    // con_id=jsonArray.getJSONObject(i).getString("country_id");
                                    Log.e(TAG, dist_name + "");
                                    Log.e(TAG, d_id + "");
                                    distarray.add(dist_name);
                                }
                                Log.e(TAG, distarray.toString());
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.country_list, R.id.country_name, distarray);
                                reg_vac_district.setAdapter(arrayAdapter);

                                reg_vac_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        try {
                                            d_id=jsonArray.getJSONObject(i).getString("district_id");
                                            Log.e(TAG,d_id);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            } else {
                                //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error+"");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("state_code",st_id );
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public  Boolean validate() {
        boolean valid = true;
        String name = reg_vac_name.getText().toString();
        String dob = reg_vac_dob.getText().toString();
        String email = reg_vac_emailid.getText().toString();
        String emailpattern="^[A-Za-z0-9+_.-]+@(.+)$";
        String mob = reg_vac_mobilenum.getText().toString();
        String add = reg_vac_address.getText().toString();
        String pin = reg_vac_pincode.getText().toString();

        String panchayath = reg_vac_panchayath.getText().toString();
        String spname = reg_vac_sponsorusername.getText().toString();
        String pan = reg_vac_pancardnumber.getText().toString();
        String panpattern="[A-Z]{5}[0-9]{4}[A-Z]{1}";
        String bank = reg_vac_bankname.getText().toString();
        String branch = reg_vac_branchname.getText().toString();
        String acc = reg_vac_accnum.getText().toString();
        String ifsc = reg_vac_ifsc.getText().toString();
        String ifscpattern = "^[A-Z]{4}0[A-Z0-9]{6}$";
        String n_name = reg_vac_nomname.getText().toString();
        String n_rela = reg_vac_nomrelation.getText().toString();
        String psd = reg_vac_psd.getText().toString();
        String cpsd = reg_vac_confirmpsd.getText().toString();
        String con=reg_vac_country.getSelectedItem().toString();
        String pos=reg_vac_position.getText().toString();



        if (name.isEmpty()) {
            valid=false;
            reg_vac_name.setError("Enter name");
            reg_vac_name.requestFocus();
        } else if (dob.isEmpty()) {
            valid=false;
            reg_vac_dob.setError("Enter DOB");
            reg_vac_dob.requestFocus();
        }
        else if (email.isEmpty() || !email.matches(emailpattern)) {
            valid=false;
            reg_vac_emailid.setError("Enter email ID");
            reg_vac_emailid.requestFocus();
        }
        else if (mob.isEmpty() || mob.length()!=10) {
            valid=false;
            reg_vac_mobilenum.setError("Enter mobile number");
            reg_vac_mobilenum.requestFocus();
        }
        else if (add.isEmpty()) {
            valid=false;
            reg_vac_emailid.setError("Enter Address");
            reg_vac_emailid.requestFocus();
        }


        else if (pin.isEmpty() || pin.length()!=6) {
            valid=false;
            reg_vac_pincode.setError("Enter new password");
            reg_vac_pincode.requestFocus();
        }else if (panchayath.isEmpty()) {
            valid=false;
            reg_vac_panchayath.setError("Enter panchayath");
            reg_vac_panchayath.requestFocus();
        }else if (spname.isEmpty()) {
            valid=false;
            reg_vac_sponsorusername.setError("Enter sponsorname");
            reg_vac_panchayath.requestFocus();
        }

        else if (pan.isEmpty() || !pan.matches(panpattern)) {
            valid=false;
            reg_vac_pancardnumber.setError("Enter Pancard Number");
            reg_vac_pancardnumber.requestFocus();
        }
        else if (bank.isEmpty()) {
            valid=false;
            reg_vac_bankname.setError("Enter bank name");
            reg_vac_bankname.requestFocus();
        }

        else if (branch.isEmpty()) {
            valid=false;
            reg_vac_branchname.setError("Enter branch name");
            reg_vac_branchname.requestFocus();
        }
        else if (acc.isEmpty() || acc.length() <= 11 ||acc.length()>=18) {
            valid=false;
            reg_vac_accnum.setError("Enter valid account number");
            reg_vac_accnum.requestFocus();
        }
        else if (ifsc.isEmpty() || !ifsc.matches(ifscpattern)) {
            reg_vac_ifsc.setError("Enter valid IFSC ");
            reg_vac_ifsc.requestFocus();
            return false;


        }

        else if (n_name.isEmpty()) {
            valid=false;
            reg_vac_nomname.setError("Enter nominee name");
            reg_vac_nomname.requestFocus();
        }
        else if (n_rela.isEmpty()) {
            valid=false;
            reg_vac_nomrelation.setError("Enter nominee relation");
            reg_vac_nomrelation.requestFocus();
        }
        else if (psd.isEmpty()) {
            valid=false;
            reg_vac_psd.setError("Enter password");
            reg_vac_psd.requestFocus();
        }
        else if(cpsd.isEmpty() || !cpsd.equals(psd)){
            valid=false;
            reg_vac_confirmpsd.setError("Password mismatch");
            reg_vac_confirmpsd.requestFocus();
        }
        else if (con.equals("Select Country")){
            valid=false;
            error_country= (TextView)reg_vac_country.getSelectedView();
            error_country.setError("");
            error_country.setText("Select Country");
            reg_vac_country.requestFocus();
        }


        else{
            valid = true;

        }

        return valid;
    }

    private void Register(){
        reg_vac__loader.setVisibility(View.VISIBLE);

        String name=reg_vac_name.getText().toString();
        String dob=reg_vac_dob.getText().toString();
        String email=reg_vac_emailid.getText().toString();
        String mob=reg_vac_mobilenum.getText().toString();

        String add=reg_vac_address.getText().toString();
        String pin=reg_vac_pincode.getText().toString();
        String panchayath=reg_vac_panchayath.getText().toString();

        String pos=reg_vac_position.getText().toString();
        String no_name=reg_vac_nomname.getText().toString();
        String no_rela=reg_vac_nomrelation.getText().toString();

        String psd=reg_vac_psd.getText().toString();
        // Toast.makeText(this, ""+psd, Toast.LENGTH_SHORT).show();
        String cpsd=reg_vac_confirmpsd.getText().toString();
        String pan=reg_vac_pancardnumber.getText().toString();
        String bank=reg_vac_bankname.getText().toString();
        String branch=reg_vac_branchname.getText().toString();
        String accnum=reg_vac_accnum.getText().toString();
        String ifsc=reg_vac_ifsc.getText().toString();


        // String pin=reg_pincode.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRegistration> usercall=api.Register(name,name,dob,mob,add,pin,"99",st_id,d_id,
                pan,no_name,no_rela,"VB0123758",up,
                s, psd,cpsd,email,"VB000111",
                bank,branch,accnum,pan,ifsc);
        usercall.enqueue(new Callback<ResponseRegistration>() {
            @Override
            public void onResponse(Call<ResponseRegistration> call, Response<ResponseRegistration> response) {
                reg_vac__loader.setVisibility(View.GONE);
                if (response.body().getStatus().equals("1")) {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseRegistration> call, Throwable t) {
                reg_vac__loader.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Something went error... Please try again ", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void Reset(){
        reg_vac_name.setText("");
        reg_vac_dob.setText("");
        reg_vac_emailid.setText("");
        reg_vac_mobilenum.setText("");
        reg_vac_address.setText("");
        reg_vac_pincode.setText("");
        reg_vac_panchayath.setText("");
        reg_vac_country.setSelection(0);
        reg_vac_state.setSelection(0);
        reg_vac_district.setSelection(0);
        reg_vac_sponsorusername.setText("");


        reg_vac_nomname.setText("");
        reg_vac_nomrelation.setText("");

        reg_vac_psd.setText("");
        reg_vac_confirmpsd.setText("");
        reg_vac_pancardnumber.setText("");
        reg_vac_bankname.setText("");
        reg_vac_branchname.setText("");
        reg_vac_accnum.setText("");
        reg_vac_ifsc.setText("");
        reg_vac_accnum.setText("");
        reg_vac_ifsc.setText("");

//                error_country.setText("");
//                error_position.setText("");


    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


}

