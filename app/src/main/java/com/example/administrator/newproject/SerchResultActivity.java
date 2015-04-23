package com.example.administrator.newproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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

import Adapter.OldHouseAdapter;
import Bean.OldHouseData;
import progresdialog.CustormProgressDialog;


public class SerchResultActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private String path="http://m.jiwu.com/app!oldHouseList.action?v=1&appKey=&plateId=0&startId=1&houseType=0";
    private RequestParams requestParams;
    private String areaId;
    private String areaName;
    private String price;
    private String priceId;
    private String id;
    private String name;

    private ArrayList<OldHouseData> datas;
    private OldHouseAdapter adapter;
    private String TAG="name";
    private PullToRefreshListView pullRefresh;
    private ILoadingLayout loadingLayoutProxy;
    private ListView listView;
    private CustormProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_result);


        pullRefresh = (PullToRefreshListView) findViewById(R.id.listView2);

        loadingLayoutProxy = pullRefresh.getLoadingLayoutProxy();

        loadingLayoutProxy.setPullLabel("下拉刷新");
        loadingLayoutProxy.setReleaseLabel("放开刷新");
        loadingLayoutProxy.setRefreshingLabel("正在刷新...");

        Drawable drawable=getResources().getDrawable(R.drawable.loading_01);
        loadingLayoutProxy.setLoadingDrawable(drawable);

        listView=pullRefresh.getRefreshableView();

       /* ProgressBar bar= (ProgressBar) findViewById(R.id.progressBar3);

        listView.setEmptyView(bar);*/


        dialog = new CustormProgressDialog(SerchResultActivity.this,"正在加载...", R.anim.progressdialog_anim);

        dialog.show();

        getSupportActionBar().setTitle("搜索列表");

        Drawable d=getResources().getDrawable(R.drawable.btn_red_round);
        getSupportActionBar().setBackgroundDrawable(d);

        areaId = getIntent().getStringExtra("areaId");
        areaName = getIntent().getStringExtra("areaName");
        //Log.i(TAG,areaId+"=========");
        price = getIntent().getStringExtra("price");
        priceId = getIntent().getStringExtra("priceId");
        //Log.i(TAG,priceId+"===========");
        id = getIntent().getStringExtra("id");
       // Log.i(TAG,id+"==============");
        name = getIntent().getStringExtra("name");


        pullRefresh.setMode(PullToRefreshBase.Mode.BOTH);

        pullRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                //获得当前时间
                String time = DateUtils.formatDateTime(SerchResultActivity.this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_ABBREV_ALL);

                //设置最后刷新时间
                loadingLayoutProxy.setLastUpdatedLabel(time);

                datas.clear();


                requestParams.addBodyParameter("areaId",areaId);
                requestParams.addBodyParameter("cityId",id);
                requestParams.addBodyParameter("priceId",priceId);
                String pageSize=Integer.toString(10);

                requestParams.addBodyParameter("pageSize",pageSize);

                getListData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefresh.onRefreshComplete();
                    }
                }, 4000);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                //获得当前时间
                String time = DateUtils.formatDateTime(SerchResultActivity.this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_ABBREV_ALL);

                //设置最后刷新时间
                loadingLayoutProxy.setLastUpdatedLabel(time);

                requestParams.addBodyParameter("areaId",areaId);
                requestParams.addBodyParameter("cityId",id);
                requestParams.addBodyParameter("priceId",priceId);


                //String pageSize=Integer.toString(page);

                int startId=0;
                startId+=1;
                String page=Integer.toString(startId);

                requestParams.addBodyParameter("startId",page);
                getListData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefresh.onRefreshComplete();
                    }
                }, 4000);



            }
        });

        pullRefresh.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {

                Toast.makeText(SerchResultActivity.this,"加载更多",Toast.LENGTH_SHORT).show();

            }
        });

        datas=new ArrayList<OldHouseData>();

        requestParams = new RequestParams();

        adapter=new OldHouseAdapter(datas,SerchResultActivity.this);

        listView.setAdapter(adapter);
        requestParams.addBodyParameter("areaId",areaId);
        requestParams.addBodyParameter("cityId",id);
        requestParams.addBodyParameter("priceId",priceId);


        listView.setOnItemClickListener(this);

        getListData();


    }




    public void getListData(){

        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST,path,requestParams,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();
                try {
                    JSONObject object=new JSONObject(json);

                    JSONArray array=object.getJSONArray("houseArray");

                    if (array==null){

                        Toast.makeText(SerchResultActivity.this,"对不起,未发现相关信息!",Toast.LENGTH_SHORT).show();



                    }
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1=array.getJSONObject(i);


                        String id=object1.getString("id");
                        String title=object1.getString("title");
                        String buildName=object1.getString("buildName");
                        String price=object1.getString("price");
                        String areaName=object1.getString("areaName");
                        String buildId=object1.getString("buildId");
                        String name=object1.getString("name");
                        String path=object1.getString("path");

                        OldHouseData data=new OldHouseData(id,title,buildName,price,areaName,buildId,name,path);
                        datas.add(data);

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            dialog.dismiss();

            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(SerchResultActivity.this,"下载失败!",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }






    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent i=new Intent();

        i.putExtra("houseId",datas.get(position).getId());

        i.setClass(SerchResultActivity.this,HouseDetilActivity.class);
        startActivity(i);


    }
}
