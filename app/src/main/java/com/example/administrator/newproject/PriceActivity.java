package com.example.administrator.newproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import Adapter.PriceAdapter;
import Bean.AreaNmae;
import Bean.Price;
import progresdialog.CustormProgressDialog;


public class PriceActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolBar;
    private RequestParams requestParams;
    private String cityid;
    private ArrayList<Price> list;
    private PriceAdapter adapter;
    private String name;
    private String areaName;
    private String areaId;
    private CustormProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        toolBar = (Toolbar) findViewById(R.id.toolbar5);

        toolBar.setTitle("价格");
        getSupportActionBar().hide();
        Intent intent=getIntent();

        cityid = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        areaName = intent.getStringExtra("areaName");
        areaId = intent.getStringExtra("areaId");

        //ProgressBar bar= (ProgressBar) findViewById(R.id.progressBar2);

        ListView listView= (ListView) findViewById(R.id.priceList);
        //listView.setEmptyView(bar);
        list=new ArrayList<Price>();
        adapter=new PriceAdapter(PriceActivity.this,list);

        dialog = new CustormProgressDialog(PriceActivity.this,"正在加载...", R.anim.progressdialog_anim);

        dialog.show();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        getList();

    }
    public void getList(){


        requestParams = new RequestParams();

        requestParams.addBodyParameter("cityId",cityid);
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!priceList.action?v=1&appKey=",requestParams,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();

                //Log.i(TAG,json+"===");

                try {
                    JSONObject object=new JSONObject(json);
                    JSONArray array = object.getJSONArray("priceArray");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        String priceName = object1.getString("priceName");
                        //Log.i(TAG,areaName+"=================");
                        String priceId = object1.getString("priceId");


                        Price price = new Price(priceName,priceId);

                        list.add(price);
                    }

                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(PriceActivity.this, "下载失败!", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i=new Intent();

        i.putExtra("price",list.get(position).getPriceName());
        i.putExtra("id",cityid);
        i.putExtra("name",name);
        i.putExtra("areaName",areaName);
        i.putExtra("areaId",areaId);
        i.putExtra("priceId",list.get(position).getPriceId());
        i.setClass(PriceActivity.this,SecrchActivity.class);

        this.finish();
        startActivity(i);

    }
}
