package com.wellwood.ichingdivine.ui.activity.bookContents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.BaseViewHolder;
import com.wellwood.ichingdivine.ui.activity.WebviewActivity;
import com.wellwood.ichingdivine.ui.activity.baseactivity.BaseListActivity;

import java.util.ArrayList;

/**
 *ListActivity 使用的是默认的setContentView(com.android.internal.R.layout.list_content);
 * 你也可以使用自定义的布局文件，只要list的id设置为android:id/list就行了
 *
 *ListActivity extends Activity
 *
 *  using toolbar you should extends AppCompatActivity
 * and then import android.support.v7.widget.Toolbar
 * setSupportActionBar
 *
 *
 *
 */




public class KZContentsActivity extends BaseListActivity<String> {
    private ActionBarDrawerToggle toggle;//代替监听器




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
        initKZContents();

    }


    protected void initToolbar() {

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
            String gdContentLink = getString(getApplicationContext().getResources().getIdentifier("gua" + position , "string",getApplicationContext().getPackageName()));
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("gdContentLink", gdContentLink);
            intent.putExtras(bundle);
            Logger.d("gdContentLink ="+ gdContentLink);
            Logger.d("position ="+ position);
            Toast.makeText(KZContentsActivity.this, "tiaozhuan...",
                    Toast.LENGTH_SHORT).show();
            intent.setClass(getApplicationContext(), WebviewActivity.class);
            startActivity(intent);
        }

    }




    

    private void initKZContents(){
        if (mDataList == null) {
            mDataList = new ArrayList<String>();
        }
        mDataList.add("系辞上");   //pos = 0
        mDataList.add("系辞下");   //pos = 1
        mDataList.add("说卦");  //pos = 2
        mDataList.add("序卦");  //pos = 3
        mDataList.add("杂卦");  //pos = 4
    }






}
