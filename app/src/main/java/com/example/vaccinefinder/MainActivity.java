package com.example.vaccinefinder;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "Logging Example";
    Context context;

    String centerName=null;
    String centerAddress=null;
    String centerFromTiming=null;
    String centerToTiming=null;
    String feeType=null;
   String ageLimit=null;
    String vaccineName=null;
  String availableCapacity=null;
    String date=null;
    String pinCode=null;
    ImageView calendar;
    TextInputEditText pin;
    Button button;

    RecyclerView mrecycle;
    Adapter madapter;
    Information dataInfo;
    public static  ArrayList<Information> newsArrayList  = new ArrayList<>();
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecycle= findViewById(R.id.center_list_recycler_view);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));


        pin = (TextInputEditText)findViewById(R.id.pin_code);

        Log.d("show","pincode="+pinCode);












        final Calendar cal = Calendar.getInstance();




        calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        String myFormat = "dd-MM-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        date = sdf.format(cal.getTime());
                        Log.d("show","date="+date);
                    }
                }
                        , year, month, day);
                datePickerDialog.show();

            }
        });

       button=findViewById(R.id.btn_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    fetchData();
                }


    });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void fetchData() {


        pinCode=pin.getText().toString();
        if(pinCode.length()!=6){
            Toast.makeText(this,"Please enter a valid pin-code",Toast.LENGTH_SHORT).show();
        }else{
            newsArrayList.clear();
            String url ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pinCode+"&date="+date;
            Log.d("Logging",url);
            Toast.makeText(this,"enter the case",Toast.LENGTH_SHORT).show();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                try {
                        Log.d("Logging",response);

                    JSONObject js = new JSONObject(response);
                    String stringy = js.getString("sessions");


                    JSONArray jsonArray = new JSONArray(stringy);
                        if(jsonArray.length()==0){
                            Toast.makeText(MainActivity.this,"No centers available", Toast.LENGTH_SHORT).show();
                        }

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        centerName = jsonObject.getString("name");
                        centerAddress = jsonObject.getString("address");
                        centerFromTiming = jsonObject.getString("from");
                        centerToTiming = jsonObject.getString("to");
                        feeType = jsonObject.getString("fee_type");

                        ageLimit = jsonObject.getString("min_age_limit");
                        vaccineName = jsonObject.getString("vaccine");
                        availableCapacity = jsonObject.getString("available_capacity");


                        dataInfo = new Information(centerName,
                                centerAddress,
                                centerFromTiming,
                                centerToTiming,
                                feeType,
                                ageLimit,
                                vaccineName,
                                availableCapacity);


                        newsArrayList.add(dataInfo);


                    }
                    madapter = new Adapter(newsArrayList);
                    mrecycle.setAdapter(madapter);
                    mrecycle.setHasFixedSize(true);
                    madapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },new ErrorListener()  {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("on fail","fail"+error);

            }



        });


        requestQueue.add(request);
    }
    }}




