package my_fisrt_project.test2.fregment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import my_fisrt_project.test2.AirSenarioAvtivity;
import my_fisrt_project.test2.BedroomActivity;
import my_fisrt_project.test2.LampSenarioAvtivity;
import my_fisrt_project.test2.R;


public class second extends Fragment implements View.OnClickListener {
    ImageView lamp;
    public second(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.secondpage,container,false);
        ImageView lamp=(ImageView)view.findViewById(R.id.lampsenario);
        ImageView air=(ImageView)view.findViewById(R.id.airserial);
        air.setOnClickListener(this);
        lamp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.lampsenario)
            startActivity(new Intent(getActivity(), LampSenarioAvtivity.class));
        if (v.getId()==R.id.airserial)
            startActivity(new Intent(getActivity(), AirSenarioAvtivity.class));

    }
}
