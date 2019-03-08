package my_fisrt_project.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataActivity extends AppCompatActivity {

    TextView temp;
    TextView insidetemp;
    TextView maxtemp;
    TextView tempbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable() == 1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        temp = (TextView) findViewById(R.id.exittemp);
        insidetemp = (TextView) findViewById(R.id.insidetemp);
        maxtemp = (TextView) findViewById(R.id.highexittemp);
        tempbody = (TextView) findViewById(R.id.bodytemp);


        Connection.get("insidetemp", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    temp.setText(response.get("response").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, DataActivity.this);
        Thread thread=new Thread();
        try {
            thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Connection.get("sensor", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    insidetemp.setText(response.get("response").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, DataActivity.this);
        try {
            thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection.get("data", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tempbody.setText(response.get("response").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, DataActivity.this);
        Connection.get("foren", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    maxtemp.setText(response.get("response").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, DataActivity.this);

    }
}
