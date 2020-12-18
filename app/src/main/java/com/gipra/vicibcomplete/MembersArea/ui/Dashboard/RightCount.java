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
//import com.gipra.shero.ui.Report.Cp_One_List.CpList;
//import com.gipra.shero.ui.Report.Cp_One_List.CpListAdapter;
//import com.gipra.shero.ui.Report.Cp_One_List.ResponseCpOneList;
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
//public class RightCount extends AppCompatActivity {
//    Button fromdate_rightcount,todate_rightcount,searchrigntcount;
//    RecyclerView recycler_rightcount;
//    private List<CpList> cpList;
//    private CpListAdapter cpListAdapter;
//    private static final String TAG = "RightCount";
//    TextView txt_nodatarightcount;
//    DatePickerDialog fromdp,todp;
//    SimpleDateFormat dateFormatter;
//    ImageView ractiveback;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_right_count);
//        txt_nodatarightcount=findViewById(R.id.txt_nodatarightcount);
//        recycler_rightcount=findViewById(R.id.recycler_rightcount);
//        fromdate_rightcount=findViewById(R.id.fromdate_rightcount);
//        todate_rightcount=findViewById(R.id.todate_rightcount);
//        searchrigntcount=findViewById(R.id.searchlist_rightcount);
//        searchrigntcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
//               ViewRightcount();
//            }
//        });
//        ractiveback=findViewById(R.id.ractiveback);
//        ractiveback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
//            }
//        });
//        ViewRightcount();
//        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        long date = System.currentTimeMillis();
//
//        fromdate_rightcount.setInputType(InputType.TYPE_NULL);
//        fromdate_rightcount.requestFocus();
//        String dateString = dateFormatter.format(date);
//        fromdate_rightcount.setText(dateString);
//
//
//        todate_rightcount.setInputType(InputType.TYPE_NULL);
//        todate_rightcount.setText(dateString);
//
//        fromdate_rightcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fromdp.show();
//            }
//        });
//        todate_rightcount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                todp.show();
//            }
//        });
//
//        Calendar newCalendar = Calendar.getInstance();
//        fromdp = new DatePickerDialog(RightCount.this,new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                fromdate_rightcount.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        todp = new DatePickerDialog(RightCount.this, new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                todate_rightcount.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//    }
//    private void ViewRightcount(){
//        String fdate=fromdate_rightcount.getText().toString();
//        String tdate=todate_rightcount.getText().toString();
//        SharedPreferences shpref;
//        shpref=getApplication().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseCpOneList> usercall=api.CpList(Integer.parseInt(u),fdate,tdate,"Right");
//        usercall.enqueue(new Callback<ResponseCpOneList>() {
//            @Override
//            public void onResponse(Call<ResponseCpOneList> call, Response<ResponseCpOneList> response) {
//                if (response.body().getStatus().equals("1")){
//                    ResponseCpOneList responseCpOneList=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_rightcount.setLayoutManager(layoutManager);
//                    recycler_rightcount.setHasFixedSize(true);
//                    cpList=responseCpOneList.getData();
//                    cpListAdapter=new CpListAdapter(cpList,getApplicationContext());
//                    recycler_rightcount.setAdapter(cpListAdapter);
//                    txt_nodatarightcount.setVisibility(View.GONE);
//                }
//                else{
//                    txt_nodatarightcount.setText("No Data Found");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseCpOneList> call, Throwable t) {
//
//            }
//        });
//    }
//    }
//
