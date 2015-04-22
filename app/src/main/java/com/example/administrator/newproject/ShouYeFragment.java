package com.example.administrator.newproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ShouYeFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shou_ye, container, false);
        FragmentTransaction ftshouye = getActivity().getSupportFragmentManager().beginTransaction();
        ftshouye.add(R.id.top_container,new TopFragment()).commit();
        return view;
    }


}
