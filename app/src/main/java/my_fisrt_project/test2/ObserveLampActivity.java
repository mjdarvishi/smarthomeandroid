package my_fisrt_project.test2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ObserveLampActivity extends AppCompatActivity {
    Context context = ObserveLampActivity.this;
    TextView start;
    TextView sssss;
    TextView del;
    LinearLayout layout;
    LinearLayout clockbox;
    LinearLayout layout1;
    ImageView livingroom;
    ImageView calender;
    ImageView kichen;
    ImageView clockstart;
    ImageView clockstop;
    int statelivingroom = 0;
    Button reg;
    int statekichen = 0;
    private int mYear, mMonth, mDay, mHour, mMinute;
    JSONObject params;
    JSONObject param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable() == 1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_lamp);
        layout = (LinearLayout) findViewById(R.id.boxinsert);
        layout1 = (LinearLayout) findViewById(R.id.linerdel);
        clockbox = (LinearLayout) findViewById(R.id.clockbox);
        livingroom = (ImageView) findViewById(R.id.inserlivingroomobserv);
        kichen = (ImageView) findViewById(R.id.insertkichenobserv);
        clockstart = (ImageView) findViewById(R.id.clockstartob);
        clockstop = (ImageView) findViewById(R.id.clockstopob);
        start = (TextView) findViewById(R.id.start);
        sssss = (TextView) findViewById(R.id.stop);
        del = (TextView) findViewById(R.id.del);
        reg = (Button) findViewById(R.id.regobserve);
        params = new JSONObject();
        param = new JSONObject();
        Intent intent = getIntent();
        String id = intent.getStringExtra("lamp");

        if (id.equals("1")) {
            Connection.get("getlamp", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        params.put("livingroom",response.getInt("livingroom"));
                        params.put("kichen",response.getInt("kichen"));
                        params.put("hourstart",response.getInt("hourstart"));
                        params.put("minstart",response.getInt("minstart"));
                        params.put("hourstop",response.getInt("hourstop"));
                        params.put("minstop",response.getInt("minstop"));
                        start.setText(String.valueOf(response.getInt("hourstart")) + ":" + String.valueOf(response.getInt("minstart")));
                        sssss.setText(String.valueOf(response.getInt("hourstop")) + ":" + String.valueOf(response.getInt("minstop")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (response.getInt("kichen") == 1) {
                            statekichen = 1;
                            kichen.setImageResource(R.drawable.kicheninserton);
                        }
                        if (response.getInt("livingroom") == 1) {
                            statelivingroom = 1;
                            livingroom.setImageResource(R.drawable.insertlivingroomon);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    layout.setVisibility(View.VISIBLE);
                    layout1.setVisibility(View.VISIBLE);
                    kichen.setVisibility(View.VISIBLE);
                    livingroom.setVisibility(View.VISIBLE);
                    del.setVisibility(View.VISIBLE);


                    livingroom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (statelivingroom == 0) {
                                statelivingroom = 1;
                                livingroom.setImageResource(R.drawable.insertlivingroomon);
                                try {
                                    params.remove("livingroom");
                                    params.put("livingroom", 1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                statelivingroom = 0;
                                livingroom.setImageResource(R.drawable.livingroominsert);
                                try {
                                    params.remove("livingroom");
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
                                    params.remove("kichen");
                                    params.put("kichen", 1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                statekichen = 0;
                                kichen.setImageResource(R.drawable.kicheninsert);
                                try {
                                    params.remove("kichen");
                                    params.put("kichen", 0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });


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
                                                params.remove("hourstart");
                                                params.remove("minstart");
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
                                                params.remove("hourstop");
                                                params.remove("minstop");
                                                params.put("hourstop", hourOfDay);
                                                params.put("minstop", minute);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            sssss.setText(hourOfDay + ":" + minute);

                                        }
                                    }, mHour, mMinute, false);
                            timePickerDialog.show();
                        }
                    });

                    reg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Connection.post("updatelamp", params, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if (response.getInt("response") == 1) {
                                            Toast.makeText(ObserveLampActivity.this, "سناریوی شما با موفقیت تغییر کرد", Toast.LENGTH_SHORT).show();
                                            finish();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, ObserveLampActivity.this);
                        }
                    });


                }
            }, ObserveLampActivity.this);
        } else {
            layout.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
            kichen.setVisibility(View.GONE);
            livingroom.setVisibility(View.GONE);
            del.setVisibility(View.GONE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(270, 500, 0, 0);
            clockbox.setLayoutParams(params);

            Connection.get("getair", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        param.put("hourstart",response.getInt("hourstart"));
                        param.put("minstart",response.getInt("minstart"));
                        param.put("hourstop",response.getInt("hourstop"));
                        param.put("minstop",response.getInt("minstop"));
                        start.setText(String.valueOf(response.getInt("hourstart")) + ":" + String.valueOf(response.getInt("minstart")));
                        sssss.setText(String.valueOf(response.getInt("hourstop")) + ":" + String.valueOf(response.getInt("minstop")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, ObserveLampActivity.this);


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
                                        param.put("hourstart", hourOfDay);
                                        param.put("minstart", minute);
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
                                        param.put("hourstop", hourOfDay);
                                        param.put("minstop", minute);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    sssss.setText(hourOfDay + ":" + minute);

                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            });

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Connection.post("updateair", param, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("response") == 1) {
                                    Toast.makeText(ObserveLampActivity.this, "سناریوی شما با موفقیت تغییر کرد", Toast.LENGTH_SHORT).show();
                                    finish();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ObserveLampActivity.this);
                }
            });




        }


    }


}
