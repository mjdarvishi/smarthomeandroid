package my_fisrt_project.test2;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Connection {
    public static String path = "http://192.168.1.4:8080/smarthome/api/";

    public static void get(String url, Response.Listener<JSONObject> response, final Context context) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path + url, null, response, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferencesRead = PreferenceManager.getDefaultSharedPreferences(context);
                headers.put("Authorization", sharedPreferencesRead.getString("token", null));
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

    public static void post(String url, JSONObject params, Response.Listener<JSONObject> response, final Context context) {

       JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, path + url, params, response, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               HashMap<String, String> headers = new HashMap<String, String>();
               SharedPreferences sharedPreferencesRead = PreferenceManager.getDefaultSharedPreferences(context);
               headers.put("Authorization", sharedPreferencesRead.getString("token", null));
               return headers;
           }
       };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public static void login(String url, final Map<String, String> params, Response.Listener<String> response, Context context) {

        StringRequest request = new StringRequest(Request.Method.POST, path + url, response, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            Map<String, String> map = new HashMap<String, String>();

            @Override
            protected Map<String, String> getParams() {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }

                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

}
