package com.wellwood.ichingdivine.ui.fragment.first;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.util.ColumnName;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * mainactivity 三个tab 对应三个fragment
 *
 * firstfragment 里包含ViewPager 和 tablayout , 里面再加载fragment
 *
 * 易经fragment主容器 实例(包含ViewPager和tablayout)
 *
 */
public class IChingFragmentContainer extends Fragment {
    private static final String PARAM = "param";

    private static final String LOG = "PAGER_LOG";

    @InjectView(R.id.tablayout_iching)
    TabLayout tablayout;//Tablayout
    @InjectView(R.id.viewpager_iching)
    ViewPager vp;//ViewPager
    private String mParam;
    private Activity mAct;//托管的Activity
    private String[] mTitles = new String[]{ColumnName.DIVINATION, ColumnName.DEDUCTION,
            ColumnName.ARCHIVES, ColumnName.BOOKS,
            ColumnName.ACADEMIC, ColumnName.ABOUT};//

    public static IChingFragmentContainer newInstance(String param) {
        IChingFragmentContainer fragment = new IChingFragmentContainer();
        Bundle args = new Bundle();
        args.putString(PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(PARAM);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_first_container, null);
        mAct = getActivity();
        //注入
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Tablayout
//        setTablayout();
        //初始化ViewPager
        setViewPager();
    }

//    /**
//     * 设置Tablayout
//     */
//    private void setTablayout() {
//        tablayout.addTab(tablayout.newTab().setText(DIVINATION), true);
//        tablayout.addTab(tablayout.newTab().setText(BOOKS));
//    }

    /**
     * 设置ViewPager  嵌套时需要 getChildFragmentManager
     */
    private void setViewPager() {
        //设置适配器
        IChingFragmentAdapter adapter = new IChingFragmentAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        //绑定tab
        tablayout.setupWithViewPager(vp);
        tablayout.setTabsFromPagerAdapter(adapter);
    }

    /**
     * Fragment切换时隐藏控件
     *
     * @param menuVisible
     */
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 处理类库ButterKnife
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 适配器
     */
    class IChingFragmentAdapter extends FragmentStatePagerAdapter {

        public IChingFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //getFragmentInstance
            switch (position){
                case 0: /* 占卜 */
                    Logger.i(LOG, "in getItem " + position);
                    return DivineFragment.newInstance("");
                case 1: /* 演卦 */
                    Logger.i(LOG, "in getItem " + position);
                    return Gua64Fragment.newInstance("");
                case 2: /* 档案 */
                    Logger.i(LOG, "in getItem " + position);
                    return ArchiveFragment.newInstance("");
                case 3: /* 易学库 */
                    Logger.i(LOG, "in getItem " + position);
                    return BookFragment.newInstance("");
                case 4: /* 学术交流 */
                    Logger.i(LOG, "in getItem " + position);
                    return OriginalArticleFragment.newInstance(position);
                case 5: /* 关于 */
                    Logger.i(LOG, "in getItem " + position);
                    return OriginalArticleFragment.newInstance(position);
                default:
                    return OriginalArticleFragment.newInstance(position);
            }

        }

        @Override
        public int getCount() {
            return 6;
        }

        /**
         * 标签卡上方的标题
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
