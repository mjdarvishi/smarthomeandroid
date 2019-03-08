package my_fisrt_project.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class LivingRoomActivity extends AppCompatActivity {
    ImageView air;
    ImageView lamp;
    ImageView close;
    ImageView open;
    int state = 0;
    int state1 = 0;
    int satateclose=0;
    int stateopen=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        lamp = (ImageView) findViewById(R.id.livingroomlamp);
        air = (ImageView) findViewById(R.id.livingroomair);
        open = (ImageView) findViewById(R.id.curopen);
        close = (ImageView) findViewById(R.id.curclose);
        Connection.get("livinglampstate", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("response").equals("1\r\n")){
                        lamp.setBackgroundResource(R.drawable.imageselected);
                        lamp.setImageResource(R.drawable.lampon);
                        state=1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },LivingRoomActivity.this);
        Connection.get("livingair", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("response").equals("1\r\n")){
                        air.setBackgroundResource(R.drawable.imageselected);
                        air.setImageResource(R.drawable.livingroomairon);
                        state1=1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },LivingRoomActivity.this);
        Connection.get("livingcur", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("response").equals("0\r\n")){
                        satateclose = 1;
                        stateopen=0;
                        open.setImageResource(R.drawable.curopen);
                        close.setImageResource(R.drawable.curcloseon);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },LivingRoomActivity.this);

        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0) {
                    Connection.get("livingturnon", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 1;
                            lamp.setBackgroundResource(R.drawable.imageselected);
                            lamp.setImageResource(R.drawable.lampon);
                        }
                    },LivingRoomActivity.this);


                } else {
                    Connection.get("livingturnoff", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 0;
                            lamp.setBackgroundResource(R.drawable.image);
                            lamp.setImageResource(R.drawable.lampoff);
                        }
                    },LivingRoomActivity.this);

                }

            }
        });
        air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state1 == 0) {
                    Connection.get("livingairturnon", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state1 = 1;
                            air.setBackgroundResource(R.drawable.imageselected);
                            air.setImageResource(R.drawable.livingroomairon);
                        }
                    },LivingRoomActivity.this);


                } else {
                    Connection.get("livingairturnoff", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state1 = 0;
                            air.setBackgroundResource(R.drawable.image);
                            air.setImageResource(R.drawable.livingroomair);
                        }
                    },LivingRoomActivity.this);
                }

            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stateopen == 0) {
                    Connection.get("livingcurturnon", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            stateopen = 1;
                            satateclose = 0;
                            open.setImageResource(R.drawable.curopenon);
                            close.setImageResource(R.drawable.curclose);

                        }
                    },LivingRoomActivity.this);

                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (satateclose == 0) {
                    Connection.get("livingcurturnoff", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            satateclose = 1;
                            stateopen=0;
                            open.setImageResource(R.drawable.curopen);
                            close.setImageResource(R.drawable.curcloseon);

                        }
                    },LivingRoomActivity.this);



                }


            }
        });


    }
}
