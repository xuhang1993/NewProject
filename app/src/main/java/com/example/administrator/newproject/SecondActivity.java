package com.example.administrator.newproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends ActionBarActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private TextView textView3;
    private String name;
    private String id;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //toolbar

        toolbar.setOnMenuItemClickListener(this);

        Intent i=getIntent();

        name = i.getStringExtra("name");
        id = i.getStringExtra("id");

        getSupportActionBar().hide();

        textView3 = (TextView) findViewById(R.id.tv3);
        textView3.setText(name);
        textView3.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.iv);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();

                i.setClass(SecondActivity.this,SecrchActivity.class);

                startActivity(i);

            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {



        return false;
    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent();

        i.setClass(SecondActivity.this,MainActivity.class);

        startActivity(i);

        finish();


    }
}

