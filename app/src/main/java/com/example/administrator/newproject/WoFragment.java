package com.example.administrator.newproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import Adapter.WoFragListAdapter;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class WoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wo, container, false);
        ListView woListView= (ListView) view.findViewById(R.id.wofrag_listview);
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map1.put("name","注册/登录"); map1.put("image",R.drawable.default_avtar);
        map2.put("name","我的钱包"); map2.put("image",R.drawable.qianjia);
        map3.put("name","我的收藏"); map3.put("image",R.drawable.rate_star_big_off_holo_dark);
        map4.put("name","我的好房宝"); map4.put("image",R.drawable.ic_menu_view);
        map5.put("name","我的好房贷"); map5.put("image",R.drawable.ic_menu_sort_by_size);
        map6.put("name","我的租金贷"); map6.put("image",R.drawable.ic_menu_notifications);
        map7.put("name","房事记录"); map7.put("image",R.drawable.ic_menu_paste_holo_light);
        map8.put("name","消息"); map8.put("image",R.drawable.ic_menu_start_conversation);
        map9.put("name","联系电话 4008681111-0"); map9.put("image",R.drawable.ic_menu_call);




        arrayList.add(map1);
        arrayList.add(map2);
        arrayList.add(map3);
        arrayList.add(map4);
        arrayList.add(map5);
        arrayList.add(map6);
        arrayList.add(map7);
        arrayList.add(map8);
        arrayList.add(map9);

        WoFragListAdapter listAdapter = new WoFragListAdapter(arrayList, getActivity());
        woListView.setAdapter(listAdapter);


        return view;
    }


}
