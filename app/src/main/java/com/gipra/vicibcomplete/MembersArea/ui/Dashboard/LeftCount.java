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
//
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
//public class LeftCount extends AppCompatActivity {
//    Button fromdate_leftcount,todate_leftcount,search_leftcount;
//    RecyclerView recycler_leftcount;
//    private List<CpList> cpList;
//    private CpListAdapter cpListAdapter;
//    private static final String TAG = "LeftCount";
//    TextView txt_nodateleftcount;
//    DatePickerDialog fromdp,todp;
//    SimpleDateFormat dateFormatter;
//    ImageView lcountback;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_left_count);
//        txt_nodateleftcount=findViewById(R.id.txt_noleftcount);
//        recycler_leftcount=findViewById(R.id.recycler_leftcount);
//        fromdate_leftcount=findViewById(R.id.fromdate_leftcount);
//        todate_leftcount=findViewById(R.id.todate_leftcount);
//        search_leftcount=findViewById(R.id.searchlist_leftcount);
//        search_leftcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
//                viewleftcount();
//            }
//        });
//        lcountback=findViewById(R.id.lcountback);
//        lcountback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
//            }
//        });
//        viewleftcount();
//        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        long date = System.currentTimeMillis();
//        fromdate_leftcount=findViewById(R.id.fromdate_leftcount);
//        fromdate_leftcount.setInputType(InputType.TYPE_NULL);
//        fromdate_leftcount.requestFocus();
//        String dateString = dateFormatter.format(date);
//        fromdate_leftcount.setText(dateString);
//
//
//        todate_leftcount.setInputType(InputType.TYPE_NULL);
//        todate_leftcount.setText(dateString);
//
//        fromdate_leftcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fromdp.show();
//            }
//        });
//        todate_leftcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                todp.show();
//            }
//        });
//
//        Calendar newCalendar = Calendar.getInstance();
//        fromdp = new DatePickerDialog(LeftCount.this,new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                fromdate_leftcount.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        todp = new DatePickerDialog(LeftCount.this, new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                todate_leftcount.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//    }
//    private void viewleftcount(){
//        String fdate=fromdate_leftcount.getText().toString();
//        String tdate=todate_leftcount.getText().toString();
//        SharedPreferences shpref;
//        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseCpOneList> usercall=api.CpList(Integer.parseInt(u),fdate,tdate,"Left");
//        usercall.enqueue(new Callback<ResponseCpOneList>() {
//            @Override
//            public void onResponse(Call<ResponseCpOneList> call, Response<ResponseCpOneList> response) {
//                if (response.body().getStatus().equals("1")){
//                    ResponseCpOneList responseCpOneList=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_leftcount.setLayoutManager(layoutManager);
//                    recycler_leftcount.setHasFixedSize(true);
//                    cpList=responseCpOneList.getData();
//                    cpListAdapter=new CpListAdapter(cpList,getApplicationContext());
//                    recycler_leftcount.setAdapter(cpListAdapter);
//                    txt_nodateleftcount.setVisibility(View.GONE);
//                }
//                else{
//                    txt_nodateleftcount.setText("No Data Found");
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseCpOneList> call, Throwable t) {
//
//            }
//        });
//    }
//}
//
