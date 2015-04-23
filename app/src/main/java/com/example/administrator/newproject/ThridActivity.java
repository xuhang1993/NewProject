package com.example.administrator.newproject;

import android.content.Intent;
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
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AreaAdapter;
import Bean.AreaNmae;
import progresdialog.CustormProgressDialog;


public class ThridActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private String cityid;
    private String name;
    private Toolbar toolbar;
    private ListView listView;
    private RequestQueue requestQueue;
    private ArrayList<AreaNmae> list;

    private JsonObjectRequest jsonObjectRequest;
    private AreaAdapter adapter;
    private String TAG="name";
    private RequestParams requestParams;
    private String price1;
    private CustormProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);

        cityid = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        price1 = getIntent().getStringExtra("price1");
        toolbar = (Toolbar) findViewById(R.id.toolbar4);

        getSupportActionBar().hide();
        toolbar.setTitle(name);

        listView = (ListView) findViewById(R.id.cityList);

        list=new ArrayList<AreaNmae>();

        adapter=new AreaAdapter(ThridActivity.this,list);

        dialog = new CustormProgressDialog(ThridActivity.this,"正在加载...", R.anim.progressdialog_anim);

        dialog.show();

        listView.setAdapter(adapter);
       /* ProgressBar bar= (ProgressBar) findViewById(R.id.progressBar);
        listView.setEmptyView(bar);*/
        listView.setOnItemClickListener(this);
        getList();

    }

    public void getList(){


        requestParams = new RequestParams();

        requestParams.addBodyParameter("cityId",cityid);
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!cityDetail.action?v=1&appKey=",requestParams,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();

                //Log.i(TAG,json+"===");

                try {
                    JSONObject object=new JSONObject(json);
                    JSONArray array = object.getJSONArray("areaArray");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        String areaName = object1.getString("areaName");
                        //Log.i(TAG,areaName+"=================");
                        String cityId = object1.getString("cityId");
                        String areaId = object1.getString("areaId");

                        AreaNmae areaNmae = new AreaNmae(areaName, areaId, cityId);

                        list.add(areaNmae);
                    }

                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(ThridActivity.this,"下载失败!",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });



    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        Intent i=new Intent();
        i.putExtra("id",cityid);
        i.putExtra("name",name);
        i.putExtra("price",price1);
        i.putExtra("areaName",list.get(position).getAreaName());
        i.putExtra("areaId",list.get(position).getAreaId());
        i.setClass(ThridActivity.this,SecrchActivity.class);

        this.finish();
        startActivity(i);

    }
}
