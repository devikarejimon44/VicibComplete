package com.gipra.vicibcomplete.MembersArea;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gipra.vicibcomplete.MembersArea.ui.Dashboard.Responsedashboard;
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

public class Registration extends AppCompatActivity {
 private Spinner reg_honorific,reg_position;
 EditText reg_name,reg_regdob,reg_emailid,reg_mobilenum,reg_address;
 TextInputEditText reg_pincode,reg_panchayath;
 TextInputEditText reg_sponsorusername,reg_uplineusername;
 TextView check_msg,check_upline;
 TextInputEditText reg_pancardnumber,reg_bankname,reg_branchname,reg_accnum,reg_ifsc;
 TextInputEditText reg_nomname,reg_nomrelation,reg_psd,reg_confirmpsd;
 Button reg_register,reg_reset;
 Spinner reg_country,reg_state,reg_district;
 AVLoadingIndicatorView reg_loader;
 LinearLayout ll_sponsor_check;

    DatePickerDialog from;
    SimpleDateFormat dateFormatter;

    JSONObject jsonObject,cjsonObject;
    String st_id,d_id;
    private static final String TAG = "Registration";
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar=findViewById(R.id.registration_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        reg_loader=findViewById(R.id.reg_loader);
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
        ll_sponsor_check=findViewById(R.id.ll_sponsor_check);
        check_msg=findViewById(R.id.check_msg);
        check_upline=findViewById(R.id.check_upline);
        reg_sponsorusername=findViewById(R.id.reg_sponsorusername);
        reg_sponsorusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check_msg.setText("");
                ll_sponsor_check.setVisibility(View.GONE);

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                sponsornamecheck();

            }
            @Override
            public void afterTextChanged(Editable editable) {
                check_msg.setText("");
                ll_sponsor_check.setVisibility(View.GONE);
            }
        });

        reg_uplineusername=findViewById(R.id.reg_uplineusername);
        reg_uplineusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check_upline.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                uplinecheck();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                check_upline.setText("");
            }
        });
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
//        reg_regdob.requestFocus();
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


        loadState();


        List<String> co=new ArrayList<>();
        co.add(0,"Select Country");
        co.add("India");

        ArrayAdapter cadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,co);
        cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_country.setAdapter(cadapter);
        reg_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        reg_position=findViewById(R.id.reg_position);
        List<String> position=new ArrayList<>();
        position.add(0,"Select Position");
        position.add("L");
        position.add("R");


        ArrayAdapter padapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,position);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_position.setAdapter(padapter);
        reg_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Position")){
                    //Toast.makeText(getBaseContext(), list.get(arg2).toString(),
                    //				Toast.LENGTH_SHORT).show();
                    p=adapterView.getSelectedItem().toString();
                    Toast.makeText(getBaseContext(), ""+p, Toast.LENGTH_SHORT).show();

                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void uplinecheck() {
        final String u = reg_uplineusername.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/Upline_check",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseDataUpline(response);

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
                params.put("c_upline", u);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void parseDataUpline(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String msg=jsonObject.getString("message");

            if (jsonObject.getString("status").equals("1")) {

            }
            else
            {

                check_upline.setText(msg);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void sponsornamecheck() {
        final String u = reg_sponsorusername.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/sponsor_check",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        parseData(resp);

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
                params.put("c_sponsor_name", u);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void parseData(String resp) {
        try {
            JSONObject jsonObject = new JSONObject(resp);
            String msg=jsonObject.getString("message");
            String sponsor_name=jsonObject.getString("sponsor_name");
            if (jsonObject.getString("status").equals("0")) {

                check_msg.setText(msg);
            }
            else
            {

                ll_sponsor_check.setVisibility(View.VISIBLE);
                TextView sp_name=findViewById(R.id.sp_name);
                sp_name.setText(sponsor_name);
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
                                reg_state.setAdapter(arrayAdapter);

                                reg_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
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
                                reg_district.setAdapter(arrayAdapter);

                                reg_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
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
    private void Register(){
        reg_loader.setVisibility(View.VISIBLE);

        String name=reg_name.getText().toString();
        String dob=reg_regdob.getText().toString();
        String email=reg_emailid.getText().toString();
        String mob=reg_mobilenum.getText().toString();

        String add=reg_address.getText().toString();
        String pin=reg_pincode.getText().toString();
        String panchayath=reg_panchayath.getText().toString();
        String upline=reg_uplineusername.getText().toString();
        String no_name=reg_nomname.getText().toString();
        String no_rela=reg_nomrelation.getText().toString();

        String psd=reg_psd.getText().toString();
       // Toast.makeText(this, ""+psd, Toast.LENGTH_SHORT).show();
        String cpsd=reg_confirmpsd.getText().toString();
        String pan=reg_pancardnumber.getText().toString();
        String bank=reg_bankname.getText().toString();
        String branch=reg_branchname.getText().toString();
        String accnum=reg_accnum.getText().toString();
        String ifsc=reg_ifsc.getText().toString();


       // String pin=reg_pincode.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRegistration> usercall=api.Register(name,name,dob,mob,add,pin,"99",st_id,d_id,
                pan,no_name,no_rela,"VB0123758","VB0123758",
                p, psd,cpsd,email,"VB000111",
                bank,branch,accnum,pan,ifsc);
        usercall.enqueue(new Callback<ResponseRegistration>() {
            @Override
            public void onResponse(Call<ResponseRegistration> call, Response<ResponseRegistration> response) {
                reg_loader.setVisibility(View.GONE);
                if (response.body().getStatus().equals("1")) {
                    Toast.makeText(Registration.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Registration.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseRegistration> call, Throwable t) {
                reg_loader.setVisibility(View.GONE);
                Toast.makeText(Registration.this, "Something went error... Please try again ", Toast.LENGTH_SHORT).show();
            }
        });

    }


}