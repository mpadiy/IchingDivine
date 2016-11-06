package com.wellwood.ichingdivine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wellwood.ichingdivine.BaseActivity;
import com.wellwood.ichingdivine.R;

public class WebviewActivity extends BaseActivity {
    public Bundle bundle;
    public Intent intent;
    protected Toolbar toolbar;
    protected TextView toolbar_title;
    private ActionBarDrawerToggle toggle;//代替监听器


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_translate);

        initToolbar();

//        requestWindowFeature(1);

        WebView myBrowser = (WebView) findViewById(R.id.mybrowser);

        myBrowser.setBackgroundColor(0);//先设置背景色为transparent
//        WebView.setBackgroundResource(R.drawable.sb__bg);//然后设置背景图片
        myBrowser.setBackgroundColor(getResources().getColor(R.color.md_light_yellow));




        this.intent = getIntent();
        this.bundle = this.intent.getExtras();
        String webLink = this.bundle.getString("gdContentLink");
        WebSettings websettings = myBrowser.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setJavaScriptEnabled(true);
        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.loadUrl(webLink);
    }



    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        //setTile 要在下面这句话上面，不然会失效
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        //监听DrawerLayout
        //将抽屉事件和 toolbar联系起来，这是 material design 的设计
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
    }









    public void onBackPressed() {
        finish();
    }
}
