package com.gipra.vicibcomplete.MembersArea.MyProfile;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.IDCard;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.ProductStore;
import com.gipra.vicibcomplete.MembersArea.Reports.LeftSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.MyProducts;
import com.gipra.vicibcomplete.R;
import com.gipra.vicibshoppy.activity.OrderHistoryActivity;
import com.gipra.vicibshoppy.activity.ShoppyHome;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProfile extends AppCompatActivity {

 CardView cardview_personalinfo,cardview_bankinfo;
 LinearLayout layout_personalinfo,layout_bankinfo;
 TextView myprofile_name,prof_userid,prof_sponsorid,prof_name,prof_dob,prof_mobile,prof_activateddate;
 LinearLayout prof_dash,prof_productstore,prof_compliants,prof_idcard;
 EditText edit_name,edit_dob,edit_mobile,edit_email,edit_address,edit_panchayath,edit_zipcode,edit_pannumber,edit_nomineename,edit_nomineerelation;
 EditText edit_bankname,edit_branch,edit_accountnum,edit_ifsc;
 Spinner edit_country,edit_state,edit_district;
 Button edit_update;
 Spinner edit_gender;
    String g;

    LinearLayout ll_edit_pancard;
    CheckBox pan_check;

 ImageView order_history_myprof;
 ImageView bank_upload,pan_upload;
 MaterialButton btn_bank_upload,btn_pan_upload;

 CircleImageView acc_account_profile_pic;
 ImageView edit_next;


    DatePickerDialog from;
    SimpleDateFormat dateFormatter;

    JSONObject jsonObject,cjsonObject;
    String st_id,d_id;


    ImageView picphoto;
    CircleImageView changephoto;
    AVLoadingIndicatorView photo_loader;
    private static final int SELECT_PIC = 100;
    private static final int SELECT_BANK = 200;
    private static final int SELECT_PAN = 300;
    private static final String TAG = "MyProfile";
    String imgurl ="https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/profile_image_view";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
       Toolbar toolbar=findViewById(R.id.myprofileToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        order_history_myprof=toolbar.findViewById(R.id.order_history_myprof);
        order_history_myprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrderHistoryActivity.class));
            }
        });

        photo_loader=findViewById(R.id.photo_loader);
        bank_upload=findViewById(R.id.bank_upload);
        btn_bank_upload=findViewById(R.id.btn_bank_upload);
        btn_bank_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePermission();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 200);
            }
        });
        pan_upload=findViewById(R.id.pan_upload);
        btn_pan_upload=findViewById(R.id.btn_pan_upload);
        btn_pan_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePermission();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 300);
            }
        });
        myprofile_name=findViewById(R.id.myprofile_name);
        prof_userid=findViewById(R.id.prof_userid);
        prof_sponsorid=findViewById(R.id.prof_sponsorid);
        prof_name=findViewById(R.id.prof_name);
        prof_dob=findViewById(R.id.prof_dob);
        prof_mobile=findViewById(R.id.prof_mobile);
        prof_activateddate=findViewById(R.id.prof_activateddate);

        edit_name=findViewById(R.id.edit_name);
        edit_mobile=findViewById(R.id.edit_mobile);
        edit_dob=findViewById(R.id.edit_dob);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        edit_dob.setInputType(InputType.TYPE_NULL);
        edit_dob.requestFocus();
        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        from = new DatePickerDialog(MyProfile.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edit_dob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        edit_email=findViewById(R.id.edit_email);
        edit_address=findViewById(R.id.edit_address);
        edit_country=findViewById(R.id.edit_country);
        edit_state=findViewById(R.id.edit_state);
        edit_district=findViewById(R.id.edit_district);
        edit_panchayath=findViewById(R.id.edit_panchayath);
        edit_zipcode=findViewById(R.id.edit_zipcode);
        edit_pannumber=findViewById(R.id.edit_pannumber);
        edit_nomineename=findViewById(R.id.edit_nomineename);
        edit_nomineerelation=findViewById(R.id.edit_nomineerelation);

        edit_bankname=findViewById(R.id.edit_bankname);
        edit_branch=findViewById(R.id.edit_branch);
        edit_accountnum=findViewById(R.id.edit_accountnum);
        edit_ifsc=findViewById(R.id.edit_ifsc);
        edit_gender=findViewById(R.id.edit_gender);


        ll_edit_pancard=findViewById(R.id.ll_edit_pancard);
        pan_check=findViewById(R.id.pan_check);






        List<String> gen=new ArrayList<>();
        gen.add(0,"Select Position");
        gen.add("Female");
        gen.add("Male");





        ArrayAdapter padapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gen);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_gender.setAdapter(padapter);
        edit_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Position")){
                    //Toast.makeText(getBaseContext(), list.get(arg2).toString(),
                    //				Toast.LENGTH_SHORT).show();
                    g=adapterView.getSelectedItem().toString();



                }else {
                    final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        List<String> co=new ArrayList<>();
        co.add(0,"Select Country");
        co.add("India");


        ArrayAdapter cadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,co);
        cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_country.setAdapter(cadapter);
        edit_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        acc_account_profile_pic=findViewById(R.id.acc_account_profile_pic);



        edit_update=findViewById(R.id.edit_update);
        edit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upadte();
            }
        });
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        String uname=shpref.getString("USERNAME","");
        String name=shpref.getString("NAME","");
        Log.i(TAG, "name : " + name);
        String mob=shpref.getString("MOBILE","");
        String email=shpref.getString("EMAIL","");


        myprofile_name.setText(name);
        prof_userid.setText(id);
        prof_sponsorid.setText(uname);
        prof_mobile.setText(mob);

        edit_next=findViewById(R.id.edit_next);
        edit_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_bankinfo.setVisibility(View.VISIBLE);
                layout_personalinfo.setVisibility(View.GONE);
                cardview_bankinfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                cardview_personalinfo.setCardBackgroundColor(Color.parseColor("#AFABAB"));
            }
        });

        cardview_personalinfo=findViewById(R.id.cardview_personalinfo);
        layout_bankinfo=findViewById(R.id.layout_bankinfo);
        layout_personalinfo=findViewById(R.id.layout_personalinfo);
        cardview_personalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_personalinfo.setVisibility(View.VISIBLE);
                layout_bankinfo.setVisibility(View.GONE);
                cardview_bankinfo.setBackgroundColor(Color.parseColor("#AFABAB"));
                cardview_personalinfo.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        cardview_bankinfo=findViewById(R.id.cardview_bankinfo);
        cardview_bankinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_bankinfo.setVisibility(View.VISIBLE);
                layout_personalinfo.setVisibility(View.GONE);
                cardview_bankinfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                cardview_personalinfo.setCardBackgroundColor(Color.parseColor("#AFABAB"));


            }
        });

     prof_dash=findViewById(R.id.prof_dash);
     prof_dash.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), MainActivity.class));
         }
     });

     prof_productstore=findViewById(R.id.prof_productstore);
     prof_productstore.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), ShoppyHome.class));
         }
     });

     prof_idcard=findViewById(R.id.prof_idcard);
     prof_idcard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), IDCard.class));
         }
     });
     loadState();
     ViewPhoto();
     changephoto=findViewById(R.id.changephoto);
     picphoto=findViewById(R.id.picphoto);
     picphoto.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

//             handlePermission();
//             Intent i=new Intent();
//             i.setType("image/*");
//
//             i.setAction(Intent.ACTION_PICK);
//             startActivityForResult(Intent.createChooser(i, "Select Image"), 100);

             handlePermission();
             Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(i, 100);

         }
     });
    }
    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if (!checkBox.isChecked()) {
            ll_edit_pancard.setVisibility(View.VISIBLE);
        } else {
            ll_edit_pancard.setVisibility(View.GONE);

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
                                edit_state.setAdapter(arrayAdapter);
                                edit_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                edit_district.setAdapter(arrayAdapter);

                                edit_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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




    private void Upadte() {

        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String id=shpref.getString("ID","");
        String name=edit_name.getText().toString();

        String mobile=edit_mobile.getText().toString();
        String email=edit_email.getText().toString();
        String dob=edit_dob.getText().toString();
        String address=edit_address.getText().toString();
        String panchayth=edit_panchayath.getText().toString();
        String zipcode=edit_zipcode.getText().toString();
        String pannumber=edit_pannumber.getText().toString();
        String nominenname=edit_nomineename.getText().toString();
        String nomineerelation=edit_nomineerelation.getText().toString();
        String bankname=edit_bankname.getText().toString();
        String branch=edit_branch.getText().toString();
        String accountnum=edit_accountnum.getText().toString();
        String ifsc=edit_ifsc.getText().toString();

        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseEditProfile>call=api.UpdateProf(Integer.parseInt(id),name,g,dob,mobile,email,address,"99",st_id,d_id,panchayth,zipcode,pannumber,
                bankname,branch,accountnum,ifsc,nominenname,nomineerelation);
//        Call<ResponseEditProfile>call=api.UpdateProf(1,"keerthana","female","10/10/2000","9695695656",
//                "dsacasc","dasdc","99","KL","Kottayam","636363","636565","DVFgvhhgf",
//                "Fedral","Kottayam","2516436251","FDG52555","ghjhhd","ghgsdfgs");
        call.enqueue(new Callback<ResponseEditProfile>() {
            @Override
            public void onResponse(Call<ResponseEditProfile> call, Response<ResponseEditProfile> response) {
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(MyProfile.this,""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MyProfile.this,""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseEditProfile> call, Throwable t) {
                Toast.makeText(MyProfile.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private  void ViewPhoto(){


      SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String u=shpref.getString("ID","");

        RequestQueue requestQueue=new Volley().newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, imgurl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        try {
                            JSONObject cjsonObject = new JSONObject(response);

                            if (cjsonObject.getString("status").equals("1")) {

                                String img=cjsonObject.getString("path");
                                Log.e("pathh",img);

                                Glide.with(getApplicationContext())
                                        .load(img)
                                        .into(changephoto);

                                Glide.with(getApplicationContext())
                                        .load(img)
                                        .into(acc_account_profile_pic);




                            } else {
                                Toast.makeText(getApplicationContext(), "Some error occured..Please try again later", Toast.LENGTH_SHORT).show();
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
                map.put("user_id",u );
                return map;
            }
        };
        requestQueue.add(stringRequest);


 }
    private void handlePermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_PIC);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_BANK);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case SELECT_PIC:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
            case SELECT_BANK:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
            case SELECT_PAN:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }




        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void showSettingsAlert() {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        openAppSettings(MyProfile.this);
                    }
                });
        alertDialog.show();
    }
    public static void openAppSettings(final Activity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI

            final Uri selectedImage = data.getData();
            if (null !=selectedImage){
                String path=getRealPathFromURI(selectedImage);


                Log.i(TAG, "Image Path : " + path);
                picphoto.post(new Runnable() {
                    @Override
                    public void run() {
                        changephoto.setImageURI(selectedImage);
                        uploadFile(selectedImage);
                    }
                });
            }
        }
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            //the image URI

            final Uri selectedImage = data.getData();
            if (null !=selectedImage){
                String path=getRealPathFromURI(selectedImage);


                Log.i(TAG, "Image Path : " + path);
                btn_bank_upload.post(new Runnable() {
                    @Override
                    public void run() {
                        bank_upload.setImageURI(selectedImage);
                        uploadbankfile(selectedImage);
                    }
                });
            }
        }
        if (requestCode == 300 && resultCode == RESULT_OK && data != null) {
            //the image URI

            final Uri selectedImage = data.getData();
            if (null !=selectedImage){
                String path=getRealPathFromURI(selectedImage);


                Log.i(TAG, "Image Path : " + path);
                btn_pan_upload.post(new Runnable() {
                    @Override
                    public void run() {
                        pan_upload.setImageURI(selectedImage);
                        uploadbankfile(selectedImage);
                    }
                });
            }
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();

        return result;
    }
    private void uploadFile(Uri fileUri) {

        photo_loader.setVisibility(View.VISIBLE);
        SharedPreferences shpref;
        shpref = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        //creating a file
        final File file = new File(getRealPathFromURI(fileUri));

//        int compressionRatio = 2;
//        try {
//            Bitmap bitmap = BitmapFactory.decodeFile (file.getPath ());
//            bitmap.compress (Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
//        }
//        catch (Throwable t) {
//            Log.e("ERROR", "Error compressing file." + t.toString ());
//            t.printStackTrace ();
//        }


        //creating request body for file
        final RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);


        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseImageUpload> call = api.ImageUpload(requestFile, Integer.parseInt(u));
        call.enqueue(new Callback<ResponseImageUpload>() {
            @Override
            public void onResponse(Call<ResponseImageUpload> call, Response<ResponseImageUpload> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")) {
                    Log.d("onResponse", "" + response.body().getMessage());
                    photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Profile picture updated successfully", Toast.LENGTH_LONG).show();

                } else {
                    photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Some error occurred..Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseImageUpload> call, Throwable t) {
                photo_loader.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Some error occurred..", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void uploadbankfile(Uri fileUri) {


        SharedPreferences shpref;
        shpref = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        //creating a file
        final File file = new File(getRealPathFromURI(fileUri));

//        int compressionRatio = 2;
//        try {
//            Bitmap bitmap = BitmapFactory.decodeFile (file.getPath ());
//            bitmap.compress (Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
//        }
//        catch (Throwable t) {
//            Log.e("ERROR", "Error compressing file." + t.toString ());
//            t.printStackTrace ();
//        }


        //creating request body for file
        final RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);


        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBankProofUpload> call = api.BankProof(requestFile, Integer.parseInt(u));
        call.enqueue(new Callback<ResponseBankProofUpload>() {
            @Override
            public void onResponse(Call<ResponseBankProofUpload> call, Response<ResponseBankProofUpload> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")) {
                    Log.d("onResponse", "" + response.body().getMessage());
                    photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG).show();

                } else {
                    photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Some error occurred..Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBankProofUpload> call, Throwable t) {
                photo_loader.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Some error occurred..", Toast.LENGTH_LONG).show();
            }
        });


    }


    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
