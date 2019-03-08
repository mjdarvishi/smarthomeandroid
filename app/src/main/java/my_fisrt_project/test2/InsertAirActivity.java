package my_fisrt_project.test2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class InsertAirActivity extends AppCompatActivity {
    Context context = InsertAirActivity.this;
    ImageView clockstart;
    ImageView clockstop;
    ImageView calender;
    TextView start;
    TextView date;
    TextView stop;
    Button reg;
    private int mYear, mMonth, mDay, mHour, mMinute;
    JSONObject params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable() == 1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_air);


        clockstart = (ImageView) findViewById(R.id.clockairstart);
        clockstop = (ImageView) findViewById(R.id.clockairstop);
        start = (TextView) findViewById(R.id.startinsert);
        stop = (TextView) findViewById(R.id.stopinsert);
        reg = (Button) findViewById(R.id.reginsert);
        params = new JSONObject();
        clockstart.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                try {
                                    params.put("hourstart", hourOfDay);
                                    params.put("minstart", minute);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                start.setText(hourOfDay + ":" + minute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        clockstop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                try {
                                    params.put("hourstop", hourOfDay);
                                    params.put("minstop", minute);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                stop.setText(hourOfDay + ":" + minute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection.post("addair", params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("response") == 1) {
                                Toast.makeText(InsertAirActivity.this, "سناریوی شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, InsertAirActivity.this);
            }
        });



    }

}
