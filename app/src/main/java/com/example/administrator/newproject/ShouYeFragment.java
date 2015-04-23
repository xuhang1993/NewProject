package com.example.administrator.newproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ShouYeFragment extends Fragment {


    private Button ershoufang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shou_ye, container, false);
        FragmentTransaction ftshouye = getActivity().getSupportFragmentManager().beginTransaction();
        ftshouye.add(R.id.top_container,new TopFragment()).commit();


        ershoufang = (Button) view.findViewById(R.id.ershoufang);

        ershoufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();
                i.setClass(getActivity(),SecrchActivity.class);


                startActivity(i);

            }

        });

        return view;
    }


}
