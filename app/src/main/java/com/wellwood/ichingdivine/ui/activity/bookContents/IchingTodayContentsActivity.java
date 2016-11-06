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




public class IchingTodayContentsActivity extends BaseListActivity<String> {
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
        initIchingTodayContents();

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
            item_lv_index_name.setText(mDataList.get(position));


        }



        @Override
        public void onItemClick(View view, int position) {

            int curPos = position + 1 ;
            String gdContentLink = getString(getApplicationContext().getResources().getIdentifier("gua" + curPos , "string",getApplicationContext().getPackageName()));
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("gdContentLink", gdContentLink);
            intent.putExtras(bundle);
            Logger.d("gdContentLink ="+ gdContentLink);
            Logger.d("position ="+ position);
            Toast.makeText(IchingTodayContentsActivity.this, "tiaozhuan...",
                    Toast.LENGTH_SHORT).show();
            intent.setClass(getApplicationContext(), WebviewActivity.class);
            startActivity(intent);
        }

    }




    

    private void initIchingTodayContents(){
        if (mDataList == null) {
            mDataList = new ArrayList<String>();
        }
        mDataList.add("01 乾为天");
        mDataList.add("02 坤为地");
        mDataList.add("03 水雷屯");
        mDataList.add("04 山水蒙");
        mDataList.add("05 水天需");
        mDataList.add("06 天水讼");
        mDataList.add("07 地水师");
        mDataList.add("08 水地比");
        mDataList.add("09 风天小畜");
        mDataList.add("10 天泽履");
        mDataList.add("11 地天泰");
        mDataList.add("12 天地否");
        mDataList.add("13 天火同人");
        mDataList.add("14 火天大有");
        mDataList.add("15 地山谦");
        mDataList.add("16 雷地豫");
        mDataList.add("17 泽雷随");
        mDataList.add("18 山风蛊");
        mDataList.add("19 地泽临");
        mDataList.add("20 风地观");
        mDataList.add("21 火雷噬嗑");
        mDataList.add("22 山火贲");
        mDataList.add("23 山地剥");
        mDataList.add("24 地雷复");
        mDataList.add("25 天雷无妄");
        mDataList.add("26 山天大畜");
        mDataList.add("27 山雷颐");
        mDataList.add("28 泽风大过");
        mDataList.add("29 坎为水");
        mDataList.add("30 离为火");
        mDataList.add("31 泽山咸");
        mDataList.add("32 雷风恒");
        mDataList.add("33 天山遁");
        mDataList.add("34 雷天大壮");
        mDataList.add("35 火地晋");
        mDataList.add("36 地火明夷");
        mDataList.add("37 风火家人");
        mDataList.add("38 火泽睽");
        mDataList.add("39 水山蹇");
        mDataList.add("40 雷水解");
        mDataList.add("41 山泽损");
        mDataList.add("42 风雷益");
        mDataList.add("43 泽天夬");
        mDataList.add("44 天风姤");
        mDataList.add("45 泽地萃");
        mDataList.add("46 地风升");
        mDataList.add("47 泽水困");
        mDataList.add("48 水风井");
        mDataList.add("49 泽火革");
        mDataList.add("50 火风鼎");
        mDataList.add("51 震为雷");
        mDataList.add("52 艮为山");
        mDataList.add("53 风山渐");
        mDataList.add("54 雷泽归妹");
        mDataList.add("55 雷火丰");
        mDataList.add("56 火山旅");
        mDataList.add("57 巽为风");
        mDataList.add("58 兑为泽");
        mDataList.add("59 风水涣");
        mDataList.add("60 水泽节");
        mDataList.add("61 风泽中孚");
        mDataList.add("62 雷山小过");
        mDataList.add("63 水火既济");
        mDataList.add("64 火水未济");
    }






}
