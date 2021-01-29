package com.gipra.vicibcomplete.MembersArea;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.MyProfile.MyProfile;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageView;
import com.gipra.vicibcomplete.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IDCard extends AppCompatActivity {
    LinearLayout id_dash,id_productstore,id_compliants,id_profile;
    CircleImageView id_image;
    Button btn_save_idcard;
    private static final int SELECT_PIC = 100;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    LinearLayout ll_idpdf;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_card);
        Toolbar toolbar=findViewById(R.id.idcard_ToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fn_permission();
        id_dash=findViewById(R.id.id_dash);
        id_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        ll_idpdf=findViewById(R.id.ll_idpdf);
        btn_save_idcard=findViewById(R.id.btn_save_idcard);
        btn_save_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolean_save) {
                    Intent intent = new Intent(getApplicationContext(), IDCardPdf.class);
                    startActivity(intent);

                } else {
                    if (boolean_permission) {
                        progressDialog = new ProgressDialog(IDCard.this);
                        progressDialog.setMessage("Please wait");
                        bitmap = loadBitmapFromView(ll_idpdf, ll_idpdf.getWidth(), ll_idpdf.getHeight());
                        createPdf();
//                        saveBitmap(bitmap);
                    } else {

                    }
                    createPdf();


                }
            }

        });

        id_productstore=findViewById(R.id.id_productstore);
        id_productstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        id_profile=findViewById(R.id.id_profile);
        id_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyProfile.class));
            }
        });
        id_image=findViewById(R.id.id_image);
        ViewPhoto();

    }
    private  void ViewPhoto(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseImageView> call = api.ViewPhoto(Integer.parseInt(u));
        call.enqueue(new Callback<ResponseImageView>() {
            @Override
            public void onResponse(Call<ResponseImageView> call, Response<ResponseImageView> response) {
                String img=response.body().getPath();
                if (response.body().getStatus().equals("1")) {
                    Log.e("pathh",img);
                    Glide.with(getApplicationContext())
                            .load(img)
                            .into(id_image);
                }
            }
            @Override
            public void onFailure(Call<ResponseImageView> call, Throwable t) {

            }
        });
    }



    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

        Resources mResources = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.imagedemo);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        canvas.drawPaint(paint);


        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);


        // write the document content


       // Do not hardcode "/sdcard/"; use Environment.getExternalStorageDirectory().getPath() instead
        //        String targetPdf= Environment.getExternalStorageDirectory().getPath();


//        java.io.File xmlFile = new java.io.File((getApplicationContext()
//                .getApplicationContext().getFileStreamPath("FileName.xml")
//                .getPath()));


        java.io.File xmlFile = new java.io.File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + "/Filename.xml");


//       String targetPdf = "/sdcard/Test.pdf";
//       File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(xmlFile));
            btn_save_idcard.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

//    private void fn_permission() {
//        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
//                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(IDCard.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
//            } else {
//                ActivityCompat.requestPermissions(IDCard.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSIONS);
//
//            }
//
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(IDCard.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
//            } else {
//                ActivityCompat.requestPermissions(IDCard.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSIONS);
//
//            }
//        } else {
//            boolean_permission = true;
//
//
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSIONS) {
//
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                boolean_permission = true;
//
//
//            } else {
//                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
//
//            }
//        }
//    }

private void fn_permission(){
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        return;
    }

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        //ask for permission
        ActivityCompat.requestPermissions(this,
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
                        openAppSettings(IDCard.this);
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






}




