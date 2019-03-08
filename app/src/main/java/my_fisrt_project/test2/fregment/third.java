package my_fisrt_project.test2.fregment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import my_fisrt_project.test2.BedroomActivity;
import my_fisrt_project.test2.DataActivity;
import my_fisrt_project.test2.LampSenarioAvtivity;
import my_fisrt_project.test2.R;
import my_fisrt_project.test2.SettingActivity;

/**
 * Created by m.j on 10/09/2018.
 */

public class third extends Fragment implements View.OnClickListener {
    ImageView setting;
    ImageView statistic;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thirdpage, container, false);
        statistic = (ImageView) view.findViewById(R.id.statistic);
        setting = (ImageView) view.findViewById(R.id.setting);

        statistic.setOnClickListener(this);
        setting.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.statistic) {
            startActivity(new Intent(getActivity(), DataActivity.class));

        }
        if (v.getId() == R.id.setting) {
            startActivity(new Intent(getActivity(), SettingActivity.class));

        }


    }
}
