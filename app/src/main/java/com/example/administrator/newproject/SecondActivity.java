package com.example.administrator.newproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


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

                i.putExtra("id",id);
                i.putExtra("name",name);
                i.setClass(SecondActivity.this,SecrchActivity.class);

                startActivity(i);

            }
        });




//-------lzl------
        SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setMenu(R.layout.menu);
        slidingMenu.setBehindWidth(400);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setShadowWidth(50);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setFadeDegree(0.40f);
        slidingMenu.setBehindScrollScale(0.40f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);

        SlidingMenuFragment slidingMenuFragment = new SlidingMenuFragment();
        FragmentTransaction menuFt = getSupportFragmentManager().beginTransaction();
        menuFt.add(R.id.slidmenu_container,slidingMenuFragment).commit();



        View view = getLayoutInflater().inflate(R.layout.fragment_sliding_menu, null);
        slidingMenu.addView(view);


        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.cont,new ShouYeFragment()).commit();


        RadioGroup group= (RadioGroup) findViewById(R.id.tabgroup);
        ((RadioButton)group.getChildAt(0)).setChecked(true);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment f=null;

                switch (checkedId) {
                    case R.id.tab1:
                        f = new ShouYeFragment();
                        break;
                    case R.id.tab2:
                        f = new WoFragment();
                        break;
                }
                ft.replace(R.id.cont, f).commit();

            }
        });











    }


    long currnttime=0;

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis()-currnttime<3000){

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            System.exit(0);//退出程序

            this.finish();
            //android.os.Process.killProcess(android.os.Process.myPid());

        }else{

            currnttime=System.currentTimeMillis();
            Toast.makeText(SecondActivity.this,"再按一次退出!",Toast.LENGTH_SHORT).show();

        }

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




    }
}

