package com.example.administrator.newproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONObject;


public class SecrchActivity extends ActionBarActivity {

    private TextView quyu;
    private RequestQueue requestQueue;
    private String cityId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secrch);


        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar3);

        cityId = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");



        //数据持久化

       /* SharedPreferences preferences=getSharedPreferences("idAndname", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("id",cityId);
        editor.putString("name",name);

        editor.commit();*/

        getSupportActionBar().hide();


        quyu = (TextView) findViewById(R.id.tv4);
        TextView price= (TextView) findViewById(R.id.price);


        String areaName=getIntent().getStringExtra("areaName");
        if (areaName!=null){

            quyu.setText(areaName);
        }

        quyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();

                /*SharedPreferences preferences1=getSharedPreferences("idAndname",Context.MODE_PRIVATE);
                String cityid=preferences1.getString("id",null);
                String cityName=preferences1.getString("name",null);*/
                i.putExtra("id", cityId);
                i.putExtra("name",name);
                i.setClass(SecrchActivity.this,ThridActivity.class);

                startActivity(i);
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent();

                i.putExtra("id",cityId);
                i.putExtra("name",name);
                i.setClass(SecrchActivity.this,PriceActivity.class);

                startActivity(i);

            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent();
        i.putExtra("name",name);
        i.putExtra("id",cityId);

        i.setClass(SecrchActivity.this,SecondActivity.class);
        startActivity(i);
    }
}
