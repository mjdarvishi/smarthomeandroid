package my_fisrt_project.test2;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class KichenAcivity extends AppCompatActivity {
    ImageView selectore;
    Context context = KichenAcivity.this;
    TextView text;
    JSONObject params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable() == 1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kichen_acivity);
        selectore = (ImageView) findViewById(R.id.coloerselector);
        text = (TextView) findViewById(R.id.kichentext);
        params = new JSONObject();
        Connection.get("kichenstate", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    text.setText(response.getString("response"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, KichenAcivity.this);
        selectore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder.with(context)
                        .setTitle("انتخاب رنگ")
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                int red = Color.red(selectedColor);
                                int green = Color.green(selectedColor);
                                int blue = Color.blue(selectedColor);
                                try {
                                    params.put("red",red);
                                    params.put("green",green);
                                    params.put("blue",blue);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context,red+"."+green+"."+blue, Toast.LENGTH_SHORT).show();
                                Connection.post("kichencolor", params, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                                    }
                                }, context);

                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();

            }
        });


    }


}
