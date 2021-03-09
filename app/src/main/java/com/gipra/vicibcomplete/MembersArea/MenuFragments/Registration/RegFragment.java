package com.gipra.vicibcomplete.MembersArea.MenuFragments.Registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class RegFragment extends Fragment {


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

    TextView error_position,error_country;
    TextView sp_name;

    DatePickerDialog from;
    SimpleDateFormat dateFormatter;

    JSONObject jsonObject,cjsonObject;
    String st_id,d_id;
    private static final String TAG = "RegFragment";
    String p;



    public RegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_reg, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Registration");
//        Toolbar toolbar=view.findViewById(R.id.registration_ToolBar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        reg_loader=view.findViewById(R.id.reg_loader);
        reg_name=view.findViewById(R.id.reg_name);
        reg_regdob=view.findViewById(R.id.reg_regdob);
        reg_emailid=view.findViewById(R.id.reg_emailid);
        reg_mobilenum=view.findViewById(R.id.reg_mobilenum);

        reg_address=view.findViewById(R.id.reg_address);
        reg_pincode=view.findViewById(R.id.reg_pincode);
        reg_country=view.findViewById(R.id.reg_country);
        reg_state=view.findViewById(R.id.reg_state);
        reg_district=view.findViewById(R.id.reg_district);
        reg_panchayath=view.findViewById(R.id.reg_panchayath);
        ll_sponsor_check=view.findViewById(R.id.ll_sponsor_check);
        check_msg=view.findViewById(R.id.check_msg);
        sp_name=view.findViewById(R.id.sp_name);

        check_upline=view.findViewById(R.id.check_upline);
        reg_sponsorusername=view.findViewById(R.id.reg_sponsorusername);
        reg_sponsorusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check_msg.setText("");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                sponsorname_check();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                check_msg.setText("");
            }
        });

        reg_uplineusername=view.findViewById(R.id.reg_uplineusername);
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
        reg_pancardnumber=view.findViewById(R.id.reg_pancardnumber);
        reg_bankname=view.findViewById(R.id.reg_bankname);
        reg_branchname=view.findViewById(R.id.reg_branchname);
        reg_accnum=view.findViewById(R.id.reg_accnum);
        reg_ifsc=view.findViewById(R.id.reg_ifsc);

        reg_nomname=view.findViewById(R.id.reg_nomname);
        reg_nomrelation=view.findViewById(R.id.reg_nomrelation);
        reg_psd=view.findViewById(R.id.reg_psd);
        reg_confirmpsd=view.findViewById(R.id.reg_confirmpsd);

        reg_register=view.findViewById(R.id.reg_register);
        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    Register();
                }

            }
        });
        reg_reset=view.findViewById(R.id.reg_reset);
        reg_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();

            }
        });

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
        from = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

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

        ArrayAdapter cadapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,co);
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
        reg_position=view.findViewById(R.id.reg_position);
        List<String> position=new ArrayList<>();
        position.add(0,"Select Position");
        position.add("L");
        position.add("R");


        ArrayAdapter padapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,position);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_position.setAdapter(padapter);
        reg_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Position")){
                    //Toast.makeText(getBaseContext(), list.get(arg2).toString(),
                    //				Toast.LENGTH_SHORT).show();
                    p=adapterView.getSelectedItem().toString();


                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       return view;
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
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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

    private void sponsorname_check() {
        final String u = reg_sponsorusername.getText().toString();
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
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    private void parseData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            String sp=jsonObject.getString("sponsor_name");
            String sp_msg=jsonObject.getString("message");

            if (jsonObject.getString("status").equals("1")) {
                sp_name.setText(sp);

            }
            else
            {

                check_msg.setText(sp_msg);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void loadState(){
        RequestQueue requestQueue=new Volley().newRequestQueue(getContext());
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
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.country_list, R.id.country_name, statearray);
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
        RequestQueue requestQueue=new Volley().newRequestQueue(getContext());
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
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.country_list, R.id.country_name, distarray);
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
        String name = reg_name.getText().toString();
        String dob = reg_regdob.getText().toString();
        String email = reg_emailid.getText().toString();
        String emailpattern="^[A-Za-z0-9+_.-]+@(.+)$";
        String mob = reg_mobilenum.getText().toString();
        String add = reg_address.getText().toString();
        String pin = reg_pincode.getText().toString();

        String panchayath = reg_panchayath.getText().toString();
        String spname = reg_sponsorusername.getText().toString();
        String upline = reg_uplineusername.getText().toString();
        String pan = reg_pancardnumber.getText().toString();
        String panpattern="[A-Z]{5}[0-9]{4}[A-Z]{1}";
        String bank = reg_bankname.getText().toString();
        String branch = reg_branchname.getText().toString();
        String acc = reg_accnum.getText().toString();
        String ifsc = reg_ifsc.getText().toString();
        String ifscpattern = "^[A-Z]{4}0[A-Z0-9]{6}$";
        String n_name = reg_nomname.getText().toString();
        String n_rela = reg_nomrelation.getText().toString();
        String psd = reg_psd.getText().toString();
        String cpsd = reg_confirmpsd.getText().toString();
        String con=reg_country.getSelectedItem().toString();
        String pos=reg_position.getSelectedItem().toString();



        if (name.isEmpty()) {
            valid=false;
            reg_name.setError("Enter name");
            reg_name.requestFocus();
        } else if (dob.isEmpty()) {
            valid=false;
            reg_regdob.setError("Enter DOB");
            reg_regdob.requestFocus();
        }
        else if (email.isEmpty() || !email.matches(emailpattern)) {
            valid=false;
            reg_emailid.setError("Enter email ID");
            reg_emailid.requestFocus();
        }
        else if (mob.isEmpty() || mob.length()!=10) {
            valid=false;
            reg_mobilenum.setError("Enter mobile number");
            reg_mobilenum.requestFocus();
        }
        else if (add.isEmpty()) {
            valid=false;
            reg_address.setError("Enter Address");
            reg_address.requestFocus();
        }


        else if (pin.isEmpty() || pin.length()!=6) {
            valid=false;
            reg_pincode.setError("Enter new password");
            reg_pincode.requestFocus();
        }else if (panchayath.isEmpty()) {
            valid=false;
            reg_panchayath.setError("Enter panchayath");
            reg_panchayath.requestFocus();
        }else if (spname.isEmpty()) {
            valid=false;
            reg_sponsorusername.setError("Enter sponsorname");
            reg_sponsorusername.requestFocus();
        }else if (upline.isEmpty()) {
            valid=false;
            reg_uplineusername.setError("Enter upline username");
            reg_uplineusername.requestFocus();
        }

        else if (pan.isEmpty() || !pan.matches(panpattern)) {
            valid=false;
            reg_pancardnumber.setError("Enter Pancard Number");
            reg_pancardnumber.requestFocus();
        }
        else if (bank.isEmpty()) {
            valid=false;
            reg_bankname.setError("Enter bank name");
            reg_bankname.requestFocus();
        }

        else if (branch.isEmpty()) {
            valid=false;
            reg_branchname.setError("Enter branch name");
            reg_branchname.requestFocus();
        }
        else if (acc.isEmpty() || acc.length() <= 11 ||acc.length()>=18) {
            valid=false;
            reg_accnum.setError("Enter valid account number");
            reg_accnum.requestFocus();
        }
        else if (ifsc.isEmpty() || !ifsc.matches(ifscpattern)) {
            reg_ifsc.setError("Enter valid IFSC ");
            reg_ifsc.requestFocus();
            return false;


        }

        else if (n_name.isEmpty()) {
            valid=false;
            reg_nomname.setError("Enter nominee name");
            reg_nomname.requestFocus();
        }
        else if (n_rela.isEmpty()) {
            valid=false;
            reg_nomrelation.setError("Enter nominee relation");
            reg_nomrelation.requestFocus();
        }
        else if (psd.isEmpty()) {
            valid=false;
            reg_psd.setError("Enter password");
            reg_psd.requestFocus();
        }
        else if(cpsd.isEmpty() || !cpsd.equals(psd)){
            valid=false;
            reg_confirmpsd.setError("Password mismatch");
            reg_confirmpsd.requestFocus();
        }
        else if (con.equals("Select Country")){
            valid=false;
            error_country= (TextView)reg_country.getSelectedView();
            error_country.setError("");
            error_country.setText("Select Country");
            reg_country.requestFocus();
        }else if(pos.equals("Select Position")){
            valid=false;
            error_position = (TextView)reg_position.getSelectedView();
            error_position.setError("");
            error_position.setText("Select Position");
            reg_country.requestFocus();
        }


        else{
            valid = true;

        }

        return valid;
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
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseRegistration> call, Throwable t) {
                reg_loader.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went error... Please try again ", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void Reset(){
        reg_name.setText("");
        reg_regdob.setText("");
        reg_emailid.setText("");
        reg_mobilenum.setText("");
        reg_address.setText("");
        reg_pincode.setText("");
        reg_country.setSelection(0);
        reg_state.setSelection(0);
        reg_district.setSelection(0);
        sp_name.setText("");
        reg_panchayath.setText("");
        reg_sponsorusername.setText("");
        reg_uplineusername.setText("");
        check_upline.setText("");
        check_msg.setText("");
        reg_position.setSelection(0);
        reg_nomname.setText("");
        reg_nomrelation.setText("");


        reg_psd.setText("");
        reg_confirmpsd.setText("");
        reg_pancardnumber.setText("");
        reg_bankname.setText("");
        reg_branchname.setText("");
        reg_accnum.setText("");
        reg_ifsc.setText("");
        reg_accnum.setText("");
        reg_ifsc.setText("");

//                error_country.setText("");
//                error_position.setText("");


    }
    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }





}