package my_fisrt_project.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BedroomActivity extends AppCompatActivity {
    ImageView lamp;
    int state = 0;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom);
        lamp = (ImageView) findViewById(R.id.lampoff);
        text = (TextView) findViewById(R.id.mosbedroom);
        Connection.get("mosbedroom", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    text.setText(response.getString("response")+"%");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, BedroomActivity.this);
        Connection.get("bedstate", new Response.Listener<JSONObject>() {
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
        }, BedroomActivity.this);

        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0) {

                    Connection.get("bedroomturnon", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 1;
                            lamp.setBackgroundResource(R.drawable.imageselected);
                            lamp.setImageResource(R.drawable.lampon);
                            Toast.makeText(BedroomActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }, BedroomActivity.this);


                } else {

                    Connection.get("bedroomturnoff", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            state = 0;
                            lamp.setBackgroundResource(R.drawable.image);
                            lamp.setImageResource(R.drawable.lampoff);
                            Toast.makeText(BedroomActivity.this,response.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }, BedroomActivity.this);


                }

            }
        });


    }
}
