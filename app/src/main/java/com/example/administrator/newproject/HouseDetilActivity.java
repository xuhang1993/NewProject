package com.example.administrator.newproject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.util.List;

import Adapter.JingjirenAdapter;
import Adapter.OldHouseAdapter;

import Adapter.OldHouseDetilsAdapter;
import Bean.BuildImages;
import Bean.Jingjiren;
import Bean.OldHouseData;
import Bean.OldHouseDetils;


public class HouseDetilActivity extends ActionBarActivity {

    private ListView listView;
    private ListView listView4;
    private String houseId;
    private RequestParams params;
    private ArrayList<BuildImages> imageses;
    private ArrayList<OldHouseData> oldHouseDatas;
    private OldHouseDetilsAdapter adapter;
    private OldHouseAdapter adapter1;
    private ArrayList<OldHouseDetils>datas;
    private String TAG="name";
    private ArrayList<Jingjiren> jingjirens;
    private JingjirenAdapter adapter2;
    private ProgressBar progressBar;
    private WebView webView;
    private String shareUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView= (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.bar2);
        //响应JS控件
        webView.getSettings().setJavaScriptEnabled(true);

       /* Drawable d=getResources().getDrawable(R.drawable.btn_red_round);
        getSupportActionBar().setBackgroundDrawable(d);

        getSupportActionBar().setTitle("房子详情");*/

        getSupportActionBar().hide();

        houseId = getIntent().getStringExtra("houseId");
        HttpUtils utils=new HttpUtils();

        params = new RequestParams();
        params.addBodyParameter("id",houseId);

        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!houseDetail.action?v=1&appKey=&type=1",params,new RequestCallBack<Object>() {



            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();

                //Log.i(TAG,json+"=========");
                try {
                    JSONObject object=new JSONObject(json);

                    String build=object.getString("build");

                    JSONObject object1=new JSONObject(build);

                    String locPath=object1.getString("locPath");
                    Log.i(TAG,locPath+"=========");
                    String houseInfo=object1.getString("houseInfo");
                    Log.i(TAG,houseInfo+"=========");
                    String averPrice=object1.getString("averPrice");
                    Log.i(TAG,averPrice+"=========");
                    String id=object1.getString("id");
                    Log.i(TAG,id+"=========");
                    String title=object1.getString("title");
                    Log.i(TAG,title+"=========");
                    String area=object1.getString("area");
                    Log.i(TAG,area+"=========");
                    String totalPrice=object1.getString("totalPrice");
                    Log.i(TAG,totalPrice+"=========");
                    String orientation=object1.getString("orientation");
                    Log.i(TAG,orientation+"=========");
                    String buildId=object1.getString("buildId");
                    Log.i(TAG,buildId+"=========");
                    String fitment=object1.getString("fitment");
                    Log.i(TAG,fitment+"=========");
                    String floor=object1.getString("floor");
                    Log.i(TAG,floor+"=========");
                    String address=object1.getString("address");
                    Log.i(TAG,address+"=========");
                    String houseType=object1.getString("houseType");
                    Log.i(TAG,houseType+"=========");
                    shareUrl = object1.getString("shareUrl");
                    Log.i(TAG,shareUrl+"=========");

                    webView.loadUrl(shareUrl);
                    /*OldHouseDetils oldHouseDetils=
                            new OldHouseDetils(locPath,houseInfo,averPrice,id,title,area,totalPrice,orientation,buildId,fitment,floor,address,houseType,shareUrl);

                    datas.add(oldHouseDetils);*/

                    webView.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if (event.getAction()==KeyEvent.ACTION_DOWN){

                                if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){

                                    webView.goBack();//后退

                                    return true;
                                }


                            }


                            return false;
                        }
                    });

                    webView.setWebViewClient(new WebViewClient(){

                        //页面加载时显示进度条
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);

                            progressBar.setVisibility(View.VISIBLE);

                        }

                        //页面加载后隐藏进度条
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);

                            progressBar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                            super.onReceivedError(view, errorCode, description, failingUrl);


                            Toast.makeText(HouseDetilActivity.this,"加载失败!",Toast.LENGTH_SHORT);

                            progressBar.setVisibility(View.GONE);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(HouseDetilActivity.this,"下载失败!",Toast.LENGTH_SHORT);

            }
        });






       /* Drawable d=getResources().getDrawable(R.drawable.btn_red_round);
        getSupportActionBar().setBackgroundDrawable(d);

        getSupportActionBar().setTitle("房子详情");
        houseId = getIntent().getStringExtra("houseId");




        //View view2=getLayoutInflater().inflate(R.layout.oldlist,null);


        View view=getLayoutInflater().inflate(R.layout.oldlist2,null);
        View view2=getLayoutInflater().inflate(R.layout.jingjiren_list,null);

        listView = (ListView)findViewById(R.id.listView3);

        listView4= (ListView) view.findViewById(R.id.listView4);
        ProgressBar bar= (ProgressBar) view.findViewById(R.id.progressBar4);

        listView4.setEmptyView(bar);
        datas=new ArrayList<OldHouseDetils>();
        adapter=new OldHouseDetilsAdapter(datas,HouseDetilActivity.this);

        listView4.setAdapter(adapter);

        getTopData();

        listView.addHeaderView(view);
        //listView.setEmptyView(bar);
        oldHouseDatas=new ArrayList<OldHouseData>();
        adapter1=new OldHouseAdapter(oldHouseDatas,HouseDetilActivity.this);

        listView.setAdapter(adapter1);
        getListData();



        jingjirens=new ArrayList<Jingjiren>();
        adapter2=new JingjirenAdapter(jingjirens,HouseDetilActivity.this);
        ListView listView1= (ListView) view2.findViewById(R.id.listView5);

        listView1.setAdapter(adapter2);

        getMenager();
*/


    }

    /*public void getTopData(){

        HttpUtils utils=new HttpUtils();

        params = new RequestParams();
        params.addBodyParameter("id",houseId);

        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!houseDetail.action?v=1&appKey=&type=1",params,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();

                //Log.i(TAG,json+"=========");
                try {
                    JSONObject object=new JSONObject(json);

                    String build=object.getString("build");

                    JSONObject object1=new JSONObject(build);

                    String locPath=object1.getString("locPath");
                    Log.i(TAG,locPath+"=========");
                    String houseInfo=object1.getString("houseInfo");
                    Log.i(TAG,houseInfo+"=========");
                    String averPrice=object1.getString("averPrice");
                    Log.i(TAG,averPrice+"=========");
                    String id=object1.getString("id");
                    Log.i(TAG,id+"=========");
                    String title=object1.getString("title");
                    Log.i(TAG,title+"=========");
                    String area=object1.getString("area");
                    Log.i(TAG,area+"=========");
                    String totalPrice=object1.getString("totalPrice");
                    Log.i(TAG,totalPrice+"=========");
                    String orientation=object1.getString("orientation");
                    Log.i(TAG,orientation+"=========");
                    String buildId=object1.getString("buildId");
                    Log.i(TAG,buildId+"=========");
                    String fitment=object1.getString("fitment");
                    Log.i(TAG,fitment+"=========");
                    String floor=object1.getString("floor");
                    Log.i(TAG,floor+"=========");
                    String address=object1.getString("address");
                    Log.i(TAG,address+"=========");
                    String houseType=object1.getString("houseType");
                    Log.i(TAG,houseType+"=========");
                    String shareUrl=object1.getString("shareUrl");

                    OldHouseDetils oldHouseDetils=
                            new OldHouseDetils(locPath,houseInfo,averPrice,id,title,area,totalPrice,orientation,buildId,fitment,floor,address,houseType,shareUrl);

                    datas.add(oldHouseDetils);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(HouseDetilActivity.this,"下载失败!",Toast.LENGTH_SHORT);

            }
        });




    }

*/
   /* public void getListData(){

        HttpUtils utils=new HttpUtils();

        params = new RequestParams();
        params.addBodyParameter("id",houseId);

        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!houseDetail.action?v=1&appKey=&type=1",params,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                String json=objectResponseInfo.result.toString();

                try {
                    JSONObject jsonObject=new JSONObject(json);

                    String build=jsonObject.getString("build");
                    JSONObject object=new JSONObject(build);

                    JSONArray array=object.getJSONArray("house");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1=array.getJSONObject(i);

                        String id=object1.getString("id");
                        Log.i(TAG,id+"------------");
                        String title=object1.getString("title");
                        Log.i(TAG,title+"------------");
                        String buildName=object1.getString("buildName");
                        Log.i(TAG,buildName+"------------");
                        String price=object1.getString("price");
                        Log.i(TAG,price+"------------");
                        String areaName=object1.getString("areaName");
                        Log.i(TAG,areaName+"------------");
                        String name=object1.getString("name");
                        Log.i(TAG,name+"------------");
                        String path=object1.getString("path");
                        Log.i(TAG,path+"------------");

                        OldHouseData data=new OldHouseData();
                        data.setTitle(title);
                        data.setId(id);
                        data.setAreaName(areaName);
                        data.setBuildName(buildName);
                        data.setPrice(price);
                        data.setPath(path);
                        data.setName(name);

                        oldHouseDatas.add(data);

                    }

                    adapter1.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(HttpException e, String s) {

                Toast.makeText(HouseDetilActivity.this,"下载失败!",Toast.LENGTH_SHORT);

            }
        });


    }

    public void getMenager(){

        HttpUtils utils=new HttpUtils();

        params = new RequestParams();
        params.addBodyParameter("id",houseId);

        utils.send(HttpRequest.HttpMethod.POST,"http://m.jiwu.com/app!houseDetail.action?v=1&appKey=&type=1",params,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {
                String json=objectResponseInfo.result.toString();


                try {
                    JSONObject jsonObject=new JSONObject(json);
                    String build = jsonObject.getString("build");
                    JSONObject object=new JSONObject(build);

                    String goldAgent=object.getString("goldAgent");

                    JSONObject object1=new JSONObject(goldAgent);

                    String agentName=object1.getString("agentName");
                    Log.i(TAG,agentName+"////////////////////////");
                    String mobile=object1.getString("mobile");
                    String agencyName=object1.getString("agencyName");
                    String avatar=object1.getString("avatar");

                    Jingjiren jingjiren=new Jingjiren(agentName,mobile,agencyName,avatar);

                    jingjirens.add(jingjiren);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onFailure(HttpException e, String s) {


            }
        });



    }*/

}
