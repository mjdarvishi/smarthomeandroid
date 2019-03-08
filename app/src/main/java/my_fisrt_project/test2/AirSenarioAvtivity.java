package my_fisrt_project.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AirSenarioAvtivity extends AppCompatActivity {
    ImageView insert;
    ImageView observe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_senario_avtivity);
        insert=(ImageView)findViewById(R.id.insertair);
        observe=(ImageView)findViewById(R.id.observeair);

        observe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AirSenarioAvtivity.this,ObserveLampActivity.class);
                intent.putExtra("lamp","0");
                startActivity(intent);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AirSenarioAvtivity.this,InsertAirActivity.class));
            }
        });
    }
}
