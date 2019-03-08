package my_fisrt_project.test2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.volley.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class InsertSenarioActivity extends AppCompatActivity {
    ImageView livingroom;
    Context context = InsertSenarioActivity.this;
    ImageView kichen;
    ImageView clockstart;
    ImageView clockstop;
    int statelivingroom = 0;
    Button reg;
    int statekichen = 0;
    private int mHour, mMinute;
    JSONObject params;
    TextView start;
    TextView ssssss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable() == 1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_senario);
        livingroom = (ImageView) findViewById(R.id.inserlivingroom);
        kichen = (ImageView) findViewById(R.id.insertkichen);
        clockstart = (ImageView) findViewById(R.id.clockstart);
        reg = (Button) findViewById(R.id.reglamp);
        clockstop = (ImageView) findViewById(R.id.clockstop);
        start = (TextView) findViewById(R.id.starttext);
        ssssss = (TextView) findViewById(R.id.stoptext);
        params = new JSONObject();


        clockstart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
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
                final Calendar c = Calendar.getInstance();
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
                                ssssss.setText(hourOfDay + ":" + minute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (statelivingroom == 0) {
                    statelivingroom = 1;
                    livingroom.setImageResource(R.drawable.insertlivingroomon);
                    try {
                        params.put("livingroom", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    statelivingroom = 0;
                    livingroom.setImageResource(R.drawable.livingroominsert);
                    try {
                        params.put("livingroom", 0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        kichen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statekichen == 0) {
                    statekichen = 1;
                    kichen.setImageResource(R.drawable.kicheninserton);
                    try {
                        params.put("kichen", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    statekichen = 0;
                    kichen.setImageResource(R.drawable.kicheninsert);
                    try {
                        params.put("kichen", 0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection.post("addlamp", params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("response") == 1) {
                                Toast.makeText(InsertSenarioActivity.this, "سناریوی شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, InsertSenarioActivity.this);
            }
        });


    }
}
