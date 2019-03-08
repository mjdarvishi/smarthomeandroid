package my_fisrt_project.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LampSenarioAvtivity extends AppCompatActivity {
    ImageView create;
    ImageView oberve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_senario_avtivity);
        create = (ImageView) findViewById(R.id.insert);
        oberve = (ImageView) findViewById(R.id.observe);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LampSenarioAvtivity.this, InsertSenarioActivity.class));
            }
        });
        oberve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LampSenarioAvtivity.this, ObserveLampActivity.class);
                intent.putExtra("lamp","1");
                startActivity(intent);
            }
        });
    }
}
