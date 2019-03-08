package my_fisrt_project.test2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText user;
    EditText pass;
    AppCompatCheckBox checkBox;
    ProgressDialog progressdialog;
    public static String data;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesRead;
    Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.send);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        checkBox = (AppCompatCheckBox) findViewById(R.id.check);
        sharedPreferencesRead = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferencesRead.getString("email", null);
        String password = sharedPreferencesRead.getString("password", null);
        final String token = sharedPreferencesRead.getString("token", null);
        if (username != null) {
            startActivity(new Intent(MainActivity.this, mainFragment.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork == null) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("اینترنت خود را فعال کنید")
                            .setNegativeButton("ok", null).show();
                } else {
                    progressdialog = new ProgressDialog(MainActivity.this);
                    progressdialog.setMessage(" لطفا منتظر بمانید....");
                    progressdialog.setCancelable(false);
                    progressdialog.show();
                    map.put("email", user.getText().toString());
                    map.put("password", pass.getText().toString());

                    if (token != null) {
                        progressdialog.dismiss();
                    startActivity(new Intent(MainActivity.this,mainFragment.class));
                    finish();
                    } else {
                    Connection.login("login", map, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("0")) {

                                progressdialog.dismiss();


                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("نام کاربری یا پسورد شما اشتباه است")
                                        .setNegativeButton("ok", null).show();

                            } else {
                                progressdialog.dismiss();
                                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if (checkBox.isChecked()) {


                                    editor.putString("email", user.getText().toString());
                                    editor.putString("password", pass.getText().toString());
                                }
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    String tok = obj.getString("token");
                                    editor.putString("token","Bearer " +tok);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                editor.commit();
                                startActivity(new Intent(MainActivity.this, mainFragment.class));
                                finish();

                            }
                        }
                    }, MainActivity.this);
                }

            }
             }
        });
    }

}



