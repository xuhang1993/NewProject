package com.example.administrator.newproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.TopDataAdapter;
import Bean.TopData;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class TopFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private RadioGroup radioGoup;
    private RequestQueue requestQueue;
    private String path="http://api.pinganfang.com/common/misc/kv.html?time=1422261384&apiKey=7bae14bab42629ee01e323db934d6a060b60b634&signFuncID=100&signature=ce8ff3d283138e599ee4930b448b649f&key=advertise";
    private TopDataAdapter adapter;
    private ArrayList<TopData> list;
    private String TAG="name";
    private JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_top, container, false);

        ViewPager pager= (ViewPager) view.findViewById(R.id.fa_toppager);


        radioGoup = (RadioGroup) view.findViewById(R.id.fragment_top_radio);

        list=new ArrayList<TopData>();
        adapter=new TopDataAdapter(getActivity(),list);

        pager.setAdapter(adapter);
        getTopData();

        pager.setOnPageChangeListener(this);

        //设置默认选中

        ((RadioButton)radioGoup.getChildAt(0)).setChecked(true);


        return view;
    }




    public void getTopData(){

        requestQueue = Volley.newRequestQueue(getActivity());

        jsonObjectRequest = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String data = response.getString("data");

                    //Log.i(TAG, data + "==========");
                    JSONObject object = new JSONObject(data);

                    JSONArray array = object.getJSONArray("ad_home_page_info");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        String link = object1.getString("sLink");
                        String image = object1.getString("sImageUrl");
                        Log.i(TAG,image+"===========");

                        TopData data1 = new TopData(image, link);

                        list.add(data1);
                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "下载失败!", Toast.LENGTH_SHORT).show();


            }
        });


        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        RadioButton button= (RadioButton) radioGoup.getChildAt(position);

        button.setChecked(true);



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
