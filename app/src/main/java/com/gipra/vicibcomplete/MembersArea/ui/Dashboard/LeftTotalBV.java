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
//public class LeftTotalBV extends AppCompatActivity {
//    Button fromdate_leftactive,todate_leftactive,search_leftactive;
//    RecyclerView recycler_leftactive;
//    private List<CpSales> cpSales;
//    private CpSalesAdapter cpSalesAdapter;
//    private static final String TAG = "LeftTotalBV";
//    TextView txt_leftactive;
//    DatePickerDialog fromdp,todp;
//    SimpleDateFormat dateFormatter;
//    ImageView lactiveback;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_left_active_members);
//        txt_leftactive=findViewById(R.id.txtnodata_leftactive);
//        recycler_leftactive=findViewById(R.id.recycler_leftactive);
//        fromdate_leftactive=findViewById(R.id.fromdate_leftactive);
//        todate_leftactive=findViewById(R.id.todate_leftactive);
//        search_leftactive=findViewById(R.id.search_leftactive);
//        search_leftactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
//                LeftActive();
//            }
//        });
//        lactiveback=findViewById(R.id.lactiveback);
//        lactiveback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
//            }
//        });
//        LeftActive();
//        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        long date = System.currentTimeMillis();
//
//        fromdate_leftactive.setInputType(InputType.TYPE_NULL);
//        fromdate_leftactive.requestFocus();
//        String dateString = dateFormatter.format(date);
//        fromdate_leftactive.setText(dateString);
//
//
//        todate_leftactive.setInputType(InputType.TYPE_NULL);
//        todate_leftactive.setText(dateString);
//
//        fromdate_leftactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fromdp.show();
//            }
//        });
//        todate_leftactive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                todp.show();
//            }
//        });
//
//        Calendar newCalendar = Calendar.getInstance();
//        fromdp = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                fromdate_leftactive.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        todp = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                todate_leftactive.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//
//    }
//    private void LeftActive(){
//        String fdate=fromdate_leftactive.getText().toString();
//        String tdate=todate_leftactive.getText().toString();
//        SharedPreferences shpref;
//        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseCpSales> usercall=api.CpSalesView(Integer.parseInt(u),fdate,tdate,"Left");
//        usercall.enqueue(new Callback<ResponseCpSales>() {
//            @Override
//            public void onResponse(Call<ResponseCpSales> call, Response<ResponseCpSales> response) {
//                if (response.body().getStatus().equals("1")){
//                    ResponseCpSales responseCpSales=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_leftactive.setLayoutManager(layoutManager);
//                    recycler_leftactive.setHasFixedSize(true);
//                    cpSales=responseCpSales.getData();
//                    cpSalesAdapter=new CpSalesAdapter(cpSales,getApplicationContext());
//                    recycler_leftactive.setAdapter(cpSalesAdapter);
//                    txt_leftactive.setVisibility(View.GONE);
//                }
//                else{
//                    txt_leftactive.setText("No Data Found");
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
//
//}
//
