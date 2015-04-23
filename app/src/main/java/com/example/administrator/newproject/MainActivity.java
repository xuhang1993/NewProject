package com.example.administrator.newproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import Adapter.CityAdapter;
import Bean.CityName;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private ArrayList<CityName> datas;
    private CityAdapter adapter;

    private String path="http://m.jiwu.com/app!cityList.action?v=1&appKey=&deviceType=Android";
    private String TAG="name";
    private Drawable d;
    private Toolbar toolbar;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        bar = (ProgressBar) findViewById(R.id.bar);

        listView.setEmptyView(bar);

        getSupportActionBar().hide();

        listView.setOnItemClickListener(this);

        datas=new ArrayList<CityName>();
        adapter=new CityAdapter(this,datas);
        listView.setAdapter(adapter);

        getData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        return super.onCreateOptionsMenu(menu);
    }

    public void getData(){

        requestQueue = Volley.newRequestQueue(this);


        jsonObjectRequest = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {




                    JSONArray array=response.getJSONArray("cityArray");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object2=array.getJSONObject(i);


                        String name=object2.getString("cityName");

                        String id=object2.getString("cityId");

                       // Log.i(TAG, name + "====");
                        //Log.i(TAG,id+"====");
                        CityName cityName=new CityName(name,id);

                        datas.add(cityName);

                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "下载失败!", Toast.LENGTH_SHORT).show();

            }
        });


        requestQueue.add(jsonObjectRequest);

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




        Intent i=new Intent();

        i.putExtra("id", datas.get(position).getiCodeID());
        i.putExtra("name",datas.get(position).getsName());

        i.setClass(MainActivity.this,SecondActivity.class);

        this.finish();
        startActivity(i);

    }
}
