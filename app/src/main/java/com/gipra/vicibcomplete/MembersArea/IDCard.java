package com.gipra.vicibcomplete.MembersArea;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gipra.vicibcomplete.MembersArea.Complaints.ComplaintsRegistration;
import com.gipra.vicibcomplete.MembersArea.MyProfile.MyProfile;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageView;
import com.gipra.vicibcomplete.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IDCard extends AppCompatActivity {
    LinearLayout id_dash,id_productstore,id_compliants,id_profile;
    CircleImageView id_image,id_account_profile_pic;

    private static final int SELECT_PIC = 100;

    boolean boolean_permission;
    boolean boolean_save;

    LinearLayout ll_idpdf;
    ImageView btn_save_idcard;
    File folder;
    File filePath;
    String path1,path,imagesUri,signature_pdf_="VicibIdcard",signature_img_;
    String filename;
    Bitmap b;
    ScrollView scroll;
    Uri uri;
    private static final String TAG = "IDCard";
    String imgurl ="https://www.vicibhomelyindia.com/api_demo/api_demo/Webservices/Membersarea/profile_image_view";

    public final static int QRcodeWidth = 500 ;
    Bitmap qrbitmap ;
    String qrlink="https://www.vicibhomelyindia.com/";
    ImageView qr_image,bar_image;





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

        id_dash=findViewById(R.id.id_dash);
        id_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        ll_idpdf=findViewById(R.id.ll_idpdf);


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
        id_account_profile_pic=findViewById(R.id.id_account_profile_pic);

        ViewPhoto();

        btn_save_idcard=findViewById(R.id.btn_save_idcard);
        btn_save_idcard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                takeScreenShot();

            }
        });

        try {
            qrbitmap = LinkToQrcode(qrlink);
            qr_image=findViewById(R.id.qr_image);
            qr_image.setImageBitmap(qrbitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        bar_image=findViewById(R.id.bar_image);
            ViewBarCode();



    }
    Bitmap LinkToQrcode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null




            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.colorBlackShoppy):getResources().getColor(R.color.colorWhiteShoppy);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
    private void ViewBarCode(){
        try {
           // String productId = "https://www.vicibhomelyindia.com/";
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer codeWriter;
            codeWriter = new Code128Writer();
            BitMatrix byteMatrix = codeWriter.encode(qrlink, BarcodeFormat.CODE_128,400, 200, hintMap);
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
            bar_image.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
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
                                        .into(id_image);

                                Glide.with(getApplicationContext())
                                        .load(img)
                                        .into(id_account_profile_pic);




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

    private void takeScreenShot() {

        //File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/EmailClient/");
        //
        //folder.mkdirs();
        //File file = new File(folder,filename);
        //file.createNewFile();
folder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/Vicib");
     //   folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Vicib/");

        if (!folder.exists()) {
            boolean success = folder.mkdir();
        }

        path = folder.getAbsolutePath();

        filename=signature_pdf_ + System.currentTimeMillis() + ".pdf";
        path = path + "/" +filename;// path where pdf will be stored

        View vv = findViewById(R.id.scroll);
        NestedScrollView z = (NestedScrollView) findViewById(R.id.scroll); // parent view
        int totalHeight = z.getChildAt(0).getHeight();// parent view height
        int totalWidth = z.getChildAt(0).getWidth();// parent view width

        //Save bitmap to  below path
        String extr = Environment.getExternalStorageDirectory() + "/Vicibjpg/";
        File file = new File(extr);
        if (!file.exists())
            file.mkdir();
        String fileName = signature_img_ + ".jpg";
        File myPath = new File(extr, fileName);
        imagesUri = myPath.getPath();
        FileOutputStream fos = null;
        b = getBitmapFromView(vv, totalHeight, totalWidth);

        try {

            //  if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            fos = new FileOutputStream(imagesUri);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);

            fos.flush();
            fos.close();
            //  } else {
            // Request permission from the user
            //     ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            // }
        } catch (Exception e) {

            e.printStackTrace();
        }
       // create pdf after creating bitmap and saving
        createPdf();
    }

    private void createPdf() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            PdfDocument document = null;
            document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(b.getWidth(), b.getHeight(), 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPaint(paint);
            Bitmap bitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);
            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            document.finishPage(page);
            filePath = new File(path);
            Log.e(TAG,"file path.."+filePath);

            try {

                if (ContextCompat.checkSelfPermission(IDCard.this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) {
                    // Do the file write
                    document.writeTo(new FileOutputStream(filePath));
                    sendNotification();
                    openPdf();
                    Toast.makeText(IDCard.this, "downloaded: " + path, Toast.LENGTH_LONG).show();
                    document.close();
                } else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions((Activity) IDCard.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }

            } catch (IOException e) {
                Log.e(TAG,"createpdf error.. "+e.toString());
                e.printStackTrace();
                Toast.makeText(IDCard.this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }
            // close the document
            openPdf();  // You can open pdf after complete
        }
    }

    private void sendNotification() {
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        NotificationManager notificationManager = (NotificationManager) IDCard.this.getSystemService(Context.NOTIFICATION_SERVICE);


        try{


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                // Configure the notification channel.
                notificationChannel.setDescription("Channel description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(IDCard.this,NOTIFICATION_CHANNEL_ID);
            builder.setSmallIcon(android.R.drawable.stat_sys_download_done);
            //Uri URI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", folder);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setDataAndType(Uri.parse(folder+filename),"application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri,"application/pdf");
            PendingIntent pendingIntent = PendingIntent.getActivity(IDCard.this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
            builder.setLargeIcon(BitmapFactory.decodeResource(IDCard.this.getResources(), R.mipmap.ic_launcher));
            builder.setContentTitle(filename);
            builder.setContentText("ID Card Downloaded");
            builder.setSubText("Tap to view the pdf or go to downloads.");
            // NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            // Will display the notification in the notification bar

            notificationManager.notify(1, builder.build());

        } catch (Exception e) {
            Log.e(TAG,"notification error.. "+e.toString());
            e.printStackTrace();
            Toast.makeText(IDCard.this, "Something wrong notification " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void openPdf() {

        File file=new File(Environment.getExternalStorageDirectory() + "/Vicib/"+filename);

        Log.e(TAG,"path "+path);

        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        }
        else {
            uri = Uri.parse(path); //  doesn't work in Android 10.
        }

        if (filePath.exists()) //Checking for the file is exist or not
        {
            Log.e(TAG,"exist pdf ");
            Log.e(TAG,"filepath "+filePath);
            Log.e(TAG,"uri "+uri.toString());
            //Uri uri = Uri.fromFile(folder);
            try {
                if (ContextCompat.checkSelfPermission(IDCard.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent objIntent = new Intent(Intent.ACTION_VIEW);
                    objIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    objIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP);
                    objIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    objIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    objIntent.setDataAndType(uri,"application/pdf");
                    PackageManager pm = IDCard.this.getPackageManager();
                    List<ResolveInfo> activities = pm.queryIntentActivities(objIntent, 0);
                    if (activities.size() > 0) {
                        IDCard.this.startActivity(objIntent);
                    }
                    //objIntent.setDataAndType(Uri.parse(folder+filename),"application/pdf");
                    // context.startActivity(objIntent);//Staring the pdf viewer
                    // context.startActivity(objIntent);
                }else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions((Activity) IDCard.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(IDCard.this,"You don't have a pdf viewer!!! please install a pdf viewer",Toast.LENGTH_SHORT).show();// Instruct the user to install a PDF reader here, or something
            }
        }

        else
        {
            Log.e(TAG,"file not exist");
        }
    }

    public Bitmap getBitmapFromView(View vv, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = vv.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        vv.draw(canvas);
        return returnedBitmap;
    }

}




