package com.gipra.vicibcomplete.MembersArea.MenuFragments.Complaints;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gipra.vicibcomplete.MembersArea.ApiClient;
import com.gipra.vicibcomplete.MembersArea.ApiInterface;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.Complaints.ResponseComplaintsImageUpload;
import com.gipra.vicibcomplete.MembersArea.MainActivity;
import com.gipra.vicibcomplete.R;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class CompliantRegFragment extends Fragment {


    LinearLayout complaints_dash,complaints_productstore,complaints_compliants,complaints_idcard,compliants_profile;
    Spinner comp_complaints_in,comp_priority;
    String comp_category,c_priority;
    EditText comp_subject,comp_description;
    MaterialButton comp_image_upload,comp_submit,comp_reset;
    ImageView c_image;
    TextView error_compin,error_prio;

    private static final int SELECT_PIC = 100;

    private static final String TAG = "CompliantRegFragment";



    public CompliantRegFragment() {
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
        View view= inflater.inflate(R.layout.fragment_compliant_reg, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Complaint Registration");
//
//        Toolbar toolbar=view.findViewById(R.id.compliant_reg_ToolBar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });


        comp_complaints_in=view.findViewById(R.id.comp_complaints_in);
        List<String> comp=new ArrayList<>();
        comp.add(0,"Select Issue");
        comp.add("Issue in Upgradation");
        comp.add("Issue in Activation");
        comp.add("Issue in Login");
        comp.add("Issue in Email");
        comp.add("Issue in Forgot Password");
        comp.add("Issue in Registration");


        ArrayAdapter padapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,comp);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comp_complaints_in.setAdapter(padapter);
        comp_complaints_in.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Issue")){
                    //Toast.makeText(getBaseContext(), list.get(arg2).toString(),
                    //				Toast.LENGTH_SHORT).show();
//                    comp_category=adapterView.getSelectedItem().toString();
                    //    Toast.makeText(getBaseContext(), "Select Issue", Toast.LENGTH_SHORT).show();

                }else {
                    comp_category=adapterView.getSelectedItem().toString();
                    //  final String item=adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        comp_priority=view.findViewById(R.id.comp_priority);
        List<String> priority=new ArrayList<>();
        priority.add(0,"Select Priority");
        priority.add("High");
        priority.add("Medium");
        priority.add("Low");



        ArrayAdapter prio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,priority);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comp_priority.setAdapter(prio);
        comp_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Priority")){
//                    Toast.makeText(getBaseContext(), "Select Priority",
//                    				Toast.LENGTH_SHORT).show();
                    //   c_priority=adapterView.getSelectedItem().toString();
//

                }else {
                    c_priority=adapterView.getSelectedItem().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        comp_subject=view.findViewById(R.id.comp_subject);
        comp_description=view.findViewById(R.id.comp_description);
        c_image=view.findViewById(R.id.c_image);
        comp_image_upload=view.findViewById(R.id.comp_image_upload);
        comp_image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePermission();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        comp_submit=view.findViewById(R.id.comp_submit);
        comp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    Sumbit();
                }

            }
        });
        comp_reset=view.findViewById(R.id.comp_reset);
        comp_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ComplaintsRegistration.class));
            }
        });







        return view;
    }

    private boolean validate() {
        boolean valid = true;


        String comp_in=comp_complaints_in.getSelectedItem().toString();
        String comp_prio=comp_priority.getSelectedItem().toString();
        String comp_sub=comp_subject.getText().toString();
        String comp_desc=comp_description.getText().toString();
        if(comp_in.equals("Select Issue")){
            valid=false;
            error_compin = (TextView)comp_complaints_in.getSelectedView();
            error_compin.setError("");
            error_compin.setText("Select Position");
            comp_complaints_in.requestFocus();
        }
        else if(comp_prio.equals("Select Priority")){
            valid=false;
            error_prio = (TextView)comp_priority.getSelectedView();
            error_prio.setError("");
            error_prio.setText("Select Position");
            comp_priority.requestFocus();
        }else if(comp_sub.isEmpty()){
            comp_subject.setError("Enter name");
            comp_subject.requestFocus();
        }else if (comp_desc.isEmpty()){
            comp_description.setError("Enter name");
            comp_description.requestFocus();
        }else {
            valid=true;

        }
        return valid;

    }
    private void Sumbit() {
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String id=shpref.getString("ID","");
        final String subject = comp_subject.getText().toString();
        final String descr = comp_description.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/Complaints_registration",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        parseData(resp);

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", id);
                params.put("c_complaint_category", comp_category);
                params.put("c_priority", c_priority);
                params.put("c_subject", subject);
                params.put("c_description", descr);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void parseData(String resp) {
        try {
            JSONObject jsonObject = new JSONObject(resp);
            String msg=jsonObject.getString("message");

            if (jsonObject.getString("status").equals("1")) {
                Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handlePermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_PIC);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case SELECT_PIC:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission);
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
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext()).create();
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
                        openAppSettings(getActivity());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI

            final Uri selectedImage = data.getData();
            if (null !=selectedImage){
                String path=getRealPathFromURI(selectedImage);


                Log.i(TAG, "Image Path : " + path);
                comp_image_upload.post(new Runnable() {
                    @Override
                    public void run() {
                        c_image.setImageURI(selectedImage);
                        uploadFile(selectedImage);
                    }
                });
            }
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();


        return result;
    }
    private void uploadFile(Uri fileUri) {


        SharedPreferences shpref;
        shpref =getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
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
        final RequestBody requestFile = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(fileUri)),file);


        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseComplaintsImageUpload> call = api.CompImageUpload(requestFile, Integer.parseInt(u));
        call.enqueue(new Callback<ResponseComplaintsImageUpload>() {
            @Override
            public void onResponse(Call<ResponseComplaintsImageUpload> call, Response<ResponseComplaintsImageUpload> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")) {
                    Log.d("onResponse", "" + response.body().getMessage());
//                        photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Profile picture updated successfully", Toast.LENGTH_LONG).show();

                } else {
//                        photo_loader.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Some error occurred..Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseComplaintsImageUpload> call, Throwable t) {
//                    photo_loader.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Some error occurred..", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void onBackPressed(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }



}