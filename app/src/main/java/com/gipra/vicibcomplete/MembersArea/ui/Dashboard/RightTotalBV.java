package com.gipra.vicibcomplete.MembersArea.ui.Dashboard;//package com.gipra.shero.ui.Dashboard;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.text.InputType;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.gipra.shero.ApiClient;
//import com.gipra.shero.ApiInterface;
//import com.gipra.shero.MainActivity;
//import com.gipra.shero.R;
//import com.gipra.shero.ui.Report.CpSales.CpSales;
//import com.gipra.shero.ui.Report.CpSales.CpSalesAdapter;
//import com.gipra.shero.ui.Report.CpSales.ResponseCpSales;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class RightTotalBV extends AppCompatActivity {
//    Button fromdate_rightactive,todate_rightactive,search_rightactive;
//    RecyclerView recycler_rightactive;
//    private List<CpSales> cpSales;
//    private CpSalesAdapter cpSalesAdapter;
//    private static final String TAG = "ChannelPartnerTwoSales";
//    TextView txt_rightactive;
//    DatePickerDialog fromdp,todp;
//    SimpleDateFormat dateFormatter;
//    ImageView rtotal;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_right_active_members);
//        txt_rightactive=findViewById(R.id.txtnodata_rightactive);
//        recycler_rightactive=findViewById(R.id.recycler_rightactive);
//        fromdate_rightactive=findViewById(R.id.fromdate_rightactive);
//        todate_rightactive=findViewById(R.id.todate_rightactive);
//        search_rightactive=findViewById(R.id.search_rightactive);
//        search_rightactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
//                ViewRightCount();
//            }
//        });
//        rtotal=findViewById(R.id.rightactiveback);
//        rtotal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);
//            }
//        });
//        ViewRightCount();
//        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        long date = System.currentTimeMillis();
//
//        fromdate_rightactive.setInputType(InputType.TYPE_NULL);
//        fromdate_rightactive.requestFocus();
//        String dateString = dateFormatter.format(date);
//        fromdate_rightactive.setText(dateString);
//
//
//        todate_rightactive.setInputType(InputType.TYPE_NULL);
//        todate_rightactive.setText(dateString);
//
//        fromdate_rightactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fromdp.show();
//            }
//        });
//        todate_rightactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                todp.show();
//            }
//        });
//
//        Calendar newCalendar = Calendar.getInstance();
//        fromdp = new DatePickerDialog(RightTotalBV.this, new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                fromdate_rightactive.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//
//        todp = new DatePickerDialog(RightTotalBV.this, new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                todate_rightactive.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//    }
//    private void ViewRightCount(){
//        String fdate=fromdate_rightactive.getText().toString();
//        String tdate=todate_rightactive.getText().toString();
//        SharedPreferences shpref;
//        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseCpSales> usercall=api.CpSalesView(Integer.parseInt(u),fdate,tdate,"right");
//        usercall.enqueue(new Callback<ResponseCpSales>() {
//            @Override
//            public void onResponse(Call<ResponseCpSales> call, Response<ResponseCpSales> response) {
//                if (response.body().getStatus().equals("1")){
//                    ResponseCpSales responseCpSales=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_rightactive.setLayoutManager(layoutManager);
//                    recycler_rightactive.setHasFixedSize(true);
//                    cpSales=responseCpSales.getData();
//                    cpSalesAdapter=new CpSalesAdapter(cpSales,getApplicationContext());
//                    recycler_rightactive.setAdapter(cpSalesAdapter);
//                    txt_rightactive.setVisibility(View.GONE);
//                }
//                else{
//                    txt_rightactive.setText("No Data Found");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseCpSales> call, Throwable t) {
//
//            }
//        });
//    }
//}
