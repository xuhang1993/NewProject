package com.example.administrator.newproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import progresdialog.CustormProgressDialog;


public class SecrchActivity extends ActionBarActivity {

    private TextView quyu;
    private RequestQueue requestQueue;
    private String cityId;
    private String name;
    private String areaName;
    private String price1;
    private Button sousuo;
    private String areaId;
    private String priceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secrch);


        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar3);

        cityId = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        getSupportActionBar().hide();


        quyu = (TextView) findViewById(R.id.tv4);
        TextView price= (TextView) findViewById(R.id.price);


        areaName = getIntent().getStringExtra("areaName");
        areaId = getIntent().getStringExtra("areaId");
        if (areaName !=null){

            quyu.setText(areaName);
        }

        price1 = getIntent().getStringExtra("price");
        priceId = getIntent().getStringExtra("priceId");
        if (price1 !=null){

            price.setText(price1);

        }


        sousuo = (Button) findViewById(R.id.sousuo);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                if (areaName==null||price1==null){

                    Toast.makeText(SecrchActivity.this,"请选择区域或价格!",Toast.LENGTH_SHORT).show();

                }else{
                    i.putExtra("areaName", areaName);
                    i.putExtra("areaId",areaId);
                    i.putExtra("price",price1);
                    i.putExtra("priceId",priceId);
                    i.putExtra("id",cityId);
                    i.putExtra("name",name);
                    i.setClass(SecrchActivity.this, SerchResultActivity.class);
                    startActivity(i);
                }




            }
        });

        quyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();

                /*SharedPreferences preferences1=getSharedPreferences("idAndname",Context.MODE_PRIVATE);
                String cityid=preferences1.getString("id",null);
                String cityName=preferences1.getString("name",null);*/
                i.putExtra("id", cityId);
                if (price1!=null){

                    i.putExtra("price1",price1);

                }
                i.putExtra("name",name);
                i.setClass(SecrchActivity.this,ThridActivity.class);

                SecrchActivity.this.finish();
                startActivity(i);
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent();
                if (areaName!=null){

                    i.putExtra("areaName",areaName);
                    i.putExtra("areaId",areaId);


                }
                i.putExtra("id",cityId);
                i.putExtra("name",name);
                i.setClass(SecrchActivity.this,PriceActivity.class);
                SecrchActivity.this.finish();
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
        this.finish();
        startActivity(i);
    }
}
