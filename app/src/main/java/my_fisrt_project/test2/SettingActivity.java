package my_fisrt_project.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingActivity extends AppCompatActivity {
    ImageView off;
    ImageView minus;
    ImageView pluse;
    Button send;
    TextView text;
    int state = 0;
    int amount;
    JSONObject params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        off = (ImageView)findViewById(R.id.off);
        minus = (ImageView)findViewById(R.id.minus);
        pluse = (ImageView)findViewById(R.id.pluse);
        text = (TextView)findViewById(R.id.showbox);
        send = (Button) findViewById(R.id.sendtemp);
        params=new JSONObject();

        Connection.get("gettemp", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    text.setText(response.getString("response"));
                    amount=response.getInt("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SettingActivity.this);

        Connection.get("settingstate", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("response").equals("1\r\n")){
                        state = 1;
                        off.setImageResource(R.drawable.on);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SettingActivity.this);

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0) {
                    Connection.get("settingturnon", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 1;
                            off.setImageResource(R.drawable.on);
                            Toast.makeText(SettingActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }, SettingActivity.this);

                } else {
                    Connection.get("settingturnoff", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 0;
                            off.setImageResource(R.drawable.off);
                            Toast.makeText(SettingActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }, SettingActivity.this);

                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount > 16) {
                    pluse.setImageResource(R.drawable.add);

                    minus.setImageResource(R.drawable.minus);
                    amount--;
                    text.setText(Integer.toString(amount));

                } else
                    minus.setImageResource(R.drawable.minusdis);

            }
        });
        pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount < 32) {
                    minus.setImageResource(R.drawable.minus);
                    pluse.setImageResource(R.drawable.add);
                    amount++;
                    text.setText(Integer.toString(amount));

                } else
                    pluse.setImageResource(R.drawable.plusedis);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    params.put("temp",text.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Connection.post("settemp", params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SettingActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    }
                },SettingActivity.this);
            }
        });
    }
}
