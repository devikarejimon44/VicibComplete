package com.gipra.vicibcomplete.MembersArea;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gipra.vicibcomplete.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePdf extends AppCompatActivity implements View.OnClickListener{

    Button btn_generate;
    TextView tv_link;
    ImageView iv_image;
    LinearLayout ll_pdflayout;
  //  public static int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savepdf);
        init();
        fn_permission();
        listener();
    }

    private void init(){
        btn_generate = (Button)findViewById(R.id.btn_generate);
        tv_link = (TextView)findViewById(R.id.tv_link);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        ll_pdflayout = (LinearLayout) findViewById(R.id.ll_pdflayout);

    }

    private void listener(){
        btn_generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_generate:

                if (boolean_save) {
                    Intent intent = new Intent(getApplicationContext(), PDFViewActivity.class);
                    startActivity(intent);

                } else {
                    if (boolean_permission) {
                        progressDialog = new ProgressDialog(SavePdf.this);
                        progressDialog.setMessage("Please wait");
                         bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                        createPdf();
//                        saveBitmap(bitmap);
                    } else {

                    }

                    createPdf();
                    break;
                }
        }
    }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.imagedemo);


//        public class BitmapScalingHelper
//{
//    public static Bitmap decodeResource(Resources res, int resId, int dstWidth, int dstHeight)
//    {
//        Options options = new Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//        options.inJustDecodeBounds = false;
//
//        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth,
//                dstHeight);
//
//        options = new Options();
//        //May use null here as well. The funciton may interpret the pre-used options variable in ways hard to tell.
//        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);
//
//        if(unscaledBitmap == null)
//        {
//            Log.e("ERR","Failed to decode resource - " + resId + " " + res.toString());
//            return null;
//        }
//
//        return unscaledBitmap;


//    }
//}



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

//        String targetPdf = Environment.getExternalStorageDirectory().getAbsolutePath();

       // String targetPdf = "/sdcard/vicibtest.pdf";
//        File filePath = new File(targetPdf);


        java.io.File xmlFile = new java.io.File((getApplicationContext()
                .getApplicationContext().getFileStreamPath("FileName.xml")
                .getPath()));

        try {
            document.writeTo(new FileOutputStream(xmlFile));
            btn_generate.setText("Check PDF");
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

    private void fn_permission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
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
                        openAppSettings(SavePdf.this);
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
