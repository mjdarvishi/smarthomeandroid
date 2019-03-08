package my_fisrt_project.test2.fregment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import my_fisrt_project.test2.BedroomActivity;
import my_fisrt_project.test2.KichenAcivity;
import my_fisrt_project.test2.LivingRoomActivity;
import my_fisrt_project.test2.R;

/**
 * Created by m.j on 10/09/2018.
 */

public class first extends Fragment implements View.OnClickListener{

    public first(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.firstpage,container,false);
        ImageView imageView=(ImageView)view.findViewById(R.id.bedroom);
        ImageView imageViewlivingroom=(ImageView)view.findViewById(R.id.livingroom);
        ImageView imageViewKichen=(ImageView)view.findViewById(R.id.kichen);
        imageViewlivingroom.setOnClickListener(this);
        imageViewKichen.setOnClickListener(this);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bedroom)
        startActivity(new Intent(getActivity(), BedroomActivity.class));
        if (v.getId()==R.id.livingroom)
            startActivity(new Intent(getActivity(), LivingRoomActivity.class));
        if (v.getId()==R.id.kichen)
            startActivity(new Intent(getActivity(), KichenAcivity.class));
    }
}
