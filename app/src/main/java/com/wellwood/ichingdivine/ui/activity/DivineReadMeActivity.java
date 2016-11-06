package com.wellwood.ichingdivine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.BaseViewHolder;
import com.wellwood.ichingdivine.ui.activity.baseactivity.BaseListActivity;

import java.util.ArrayList;

public class DivineReadMeActivity extends BaseListActivity<String> {
    protected Toolbar toolbar;
    protected TextView toolbar_title;
    private ActionBarDrawerToggle toggle;//代替监听器

//    protected void onCreate(Bundle savedInstanceState) {
//
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_divine_readme);
//        initToolbar();
////        WebView myBrowser = (WebView) findViewById(R.id.mybrowser);
////
////        myBrowser.setBackgroundColor(0);//先设置背景色为transparent
//////        WebView.setBackgroundResource(R.drawable.sb__bg);//然后设置背景图片
////        myBrowser.setBackgroundColor(getResources().getColor(R.color.md_light_yellow));
////
////
////
////        WebSettings websettings = myBrowser.getSettings();
////        websettings.setSupportZoom(true);
////        websettings.setBuiltInZoomControls(true);
////        websettings.setJavaScriptEnabled(true);
////        myBrowser.setWebViewClient(new WebViewClient());
////
////        myBrowser.loadUrl("file:///android_asset/gdx01.html");
//
//
//    }



    @Override
    protected void setUpContentView() {
        super.setUpContentView();
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        initToolbar();
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        initReadMeContents();

    }





    private void initToolbar() {
        FrameLayout fragmentContainer = (FrameLayout)findViewById(R.id.fragment_container);
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



    private void initReadMeContents() {
        if (mDataList == null) {
            mDataList = new ArrayList<String>();
        }
        mDataList.add("关于易经");
        mDataList.add("易经占卦的奥妙");
        mDataList.add("学习易经的心理准备");
        mDataList.add("易经占卦不是迷信");
        mDataList.add("古人如何看待《易经》");
        mDataList.add("乐天知命谈易经");
        mDataList.add("断卦方法");
        mDataList.add("高岛吞象易经筮法揭秘");


    }



    //item 显示

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_book_contents, parent, false);
        return new SampleViewHolder(view);
    }





    //因为RecyclerView帮我们封装了ViewHolder，所以我们自己写的ViewHolder就需要继承RecyclerView.ViewHolder，
    // 只有这样，RecyclerView才能帮你去管理这个ViewHolder类。
    class SampleViewHolder extends BaseViewHolder {
        TextView item_lv_index_name;




        public SampleViewHolder(View itemView) {
            super(itemView);
            item_lv_index_name = (TextView) itemView.findViewById(R.id.item_lv_index_name);
        }




        @Override
        public void onBindViewHolder(int position) {
            int curPos = position ;
            item_lv_index_name.setText(mDataList.get(curPos));


        }



        @Override
        public void onItemClick(View view, int position) {
            String gdContentLink = getString(getApplicationContext().getResources().getIdentifier("readme" + position , "string",getApplicationContext().getPackageName()));
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("gdContentLink", gdContentLink);
            intent.putExtras(bundle);
            intent.setClass(getApplicationContext(), WebviewActivity.class);
            startActivity(intent);
        }

    }





    public void onBackPressed() {
        finish();
    }
}
