package com.example.administrator.newproject;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


public class WebViewActivity extends ActionBarActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView= (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.bar2);
        //响应JS控件
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl("http://www.baidu.com");

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


                Toast.makeText(WebViewActivity.this,"加载失败!",Toast.LENGTH_SHORT);

                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
