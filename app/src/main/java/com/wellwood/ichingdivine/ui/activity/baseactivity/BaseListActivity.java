package com.wellwood.ichingdivine.ui.activity.baseactivity;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.BaseListAdapter;
import com.wellwood.ichingdivine.adapter.BaseViewHolder;
import com.wellwood.ichingdivine.adapter.DividerItemDecoration;
import com.wellwood.ichingdivine.adapter.layoutmanager.ILayoutManager;
import com.wellwood.ichingdivine.adapter.layoutmanager.MyLinearLayoutManager;

import java.util.ArrayList;

/**
 * Created by waterwoodwell on 2016/10/12.
 *
 *
 *
 *
 */
public abstract class BaseListActivity<T> extends Stay4itBaseActivity{
    protected BaseListAdapter adapter;
    protected ArrayList<T> mDataList;
    private RecyclerView mRecyclerView;
    private ILayoutManager mLayoutManager;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }


    @Override
    protected void setUpView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }




    @Override
    protected void setUpData() {
        setUpAdapter();


        mRecyclerView.setLayoutManager(getLayoutManager().getLayoutManager());
        mRecyclerView.addItemDecoration(getItemDecoration());
        mRecyclerView.setAdapter(adapter);
        mLayoutManager = getLayoutManager();
        mLayoutManager.setUpAdapter(adapter);


    }

    protected void setUpAdapter() {
        adapter = new ListAdapter();
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }






    //
    public class ListAdapter extends BaseListAdapter {

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        public boolean isSectionHeader(int position) {
            return BaseListActivity.this.isSectionHeader(position);
        }
    }

    protected boolean isSectionHeader(int position) {
        return false;
    }


    protected int getItemType(int position) {
        return 0;
    }



    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);




}
