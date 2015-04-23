package com.example.administrator.newproject;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;



import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class BaiduMapActivity extends ActionBarActivity {

    private MapView mapView;
    private String TAG="name";
    private LatLng latLng;
    private MapStatus status;
    private PoiSearch poi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_baidu_map);

        getSupportActionBar().setTitle("百度地图");

        Drawable drawable=getResources().getDrawable(R.drawable.btn_red_round);

        getSupportActionBar().setBackgroundDrawable(drawable);



        mapView = (MapView) findViewById(R.id.bmapView);


        final BaiduMap map=mapView.getMap();

        map.setMyLocationEnabled(true);

        MyLocationConfiguration configuration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,null);


        map.setMyLocationConfigeration(configuration);

        final LocationClient client=new LocationClient(getApplicationContext());


        client.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation==null){

                    return;
                }
                Toast.makeText(BaiduMapActivity.this,"当前位置:"+bdLocation.getAddrStr(),Toast.LENGTH_SHORT).show();

                MyLocationData data=new MyLocationData.Builder()
                        .longitude(bdLocation.getLongitude())
                        .latitude(bdLocation.getLatitude())
                        .accuracy(bdLocation.getRadius())
                        .satellitesNum(bdLocation.getSatelliteNumber())
                        .speed(bdLocation.getSpeed())
                        .build();


                double latitude=bdLocation.getLatitude();
                double longitude=bdLocation.getLongitude();
                latLng = new LatLng(latitude, longitude);

                if (latLng!=null) {

                    status = new MapStatus.Builder()
                            .target(latLng)
                            .zoom(19)   //缩放级别
                            .rotate(0)  //俯仰角度
                            .overlook(45)//旋转角度
                            .build();

                    client.stop();
                    MapStatusUpdate update=MapStatusUpdateFactory.newMapStatus(status);

                    map.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图

                    map.setMapStatus(update);


                    MarkerOptions marker =new MarkerOptions();
                    BitmapDescriptor descriptor=new BitmapDescriptorFactory()
                            .fromResource(R.drawable.marker);
                    marker.icon(descriptor);
                    marker.position(latLng);
                    map.addOverlay(marker);

                    map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            Button btn= new Button(BaiduMapActivity.this);

                            btn.setText("查找房子");
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    //搜索附近的房子

                                    poi = PoiSearch.newInstance();
                                    poi.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
                                        @Override
                                        public void onGetPoiResult(PoiResult poiResult) {

                                            map.hideInfoWindow();
                                            map.clear();

                                            PoiOverlay overlay=new MyPoinOverlay(map);
                                            map.setOnMarkerClickListener(overlay);
                                            overlay.setData(poiResult);
                                            overlay.addToMap();
                                            overlay.zoomToSpan();
                                        }

                                        @Override
                                        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

                                            Toast.makeText(BaiduMapActivity.this,poiDetailResult.getAddress(),Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                    PoiNearbySearchOption option=new PoiNearbySearchOption();


                                    option.keyword("房子").location(latLng).radius(1000);

                                    poi.searchNearby(option);
                                }
                            });

                            InfoWindow infoWindow=new InfoWindow(btn,latLng,100);

                            map.showInfoWindow(infoWindow);

                            return false;
                        }
                    });
                }
                map.setMyLocationData(data);
                /*Log.i(TAG,bdLocation.getLatitude()+"==============");
                Log.i(TAG,bdLocation.getLongitude()+"==============");*/


            }
        });



        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(1000);//5000毫秒

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//高精度模式
        option.setIsNeedAddress(true);//返回结果包括地址信息
        option.setNeedDeviceDirect(false);//手机头方向
        option.setOpenGps(true);//打开GPS
        option.setCoorType("bd09ll");//坐标系

        client.setLocOption(option);

        client.start();
        //发起请求
        client.requestLocation();






    }

    class MyPoinOverlay extends PoiOverlay {


        public MyPoinOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {

            PoiInfo info = getPoiResult().getAllPoi().get(i);

            if (info.hasCaterDetails) {
                PoiDetailSearchOption option = new PoiDetailSearchOption().poiUid(info.uid);


                poi.searchPoiDetail(option);
            }


            return super.onPoiClick(i);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();
    }
}
