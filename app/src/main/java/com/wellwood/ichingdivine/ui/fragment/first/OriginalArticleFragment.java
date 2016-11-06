package com.wellwood.ichingdivine.ui.fragment.first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nispok.snackbar.Snackbar;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.OriginArticleAdapter;
import com.wellwood.ichingdivine.model.SimpleArticleItem;
import com.wellwood.ichingdivine.ui.activity.first.DetailActivity;
import com.wellwood.ichingdivine.util.ApiUrl;
import com.wellwood.ichingdivine.util.ColumnType;
import com.wellwood.ichingdivine.util.Constant;
import com.wellwood.ichingdivine.util.OnItemClickLitener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 普通 展示新闻列表
 * <p/>
 * 实现上拉加载更多
 * Created by tomchen on 1/10/16.
 */
public class OriginalArticleFragment extends Fragment {


    public static final String ARTICLE_ID = "id";
    public static final String ARTICLE_TITLE = "title";
    public static final String ARTICLE_DATE = "date";
    public static final String ARTICLE_READ = "read_times";
    public static final String COLUMN_TYPE = "type";
    private static final String POSITION = "column";

    private static final String SINA_ERROR_INFO = "您所访问的网站发生故障";
    @InjectView(R.id.rcv_article_origin)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    //存储的参数,是栏目的 aid 不连续
    private int mColumn;
    //获取 fragment 依赖的 Activity，方便使用 Context
    private Activity mActivity;
    private Handler mHandler;
    private OriginArticleAdapter mAdapter;
    private List<SimpleArticleItem> mArticleList;
    private boolean loading = false;

    public static OriginalArticleFragment newInstance(int param) {
        OriginalArticleFragment fragment = new OriginalArticleFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int mPosition = getArguments().getInt(POSITION);
        mColumn = ColumnType.getType(mPosition);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_first_other, container, false);
        ButterKnife.inject(this, view);
        mActivity = getActivity();
        mHandler = new Handler();
        mArticleList = new ArrayList<SimpleArticleItem>();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //让 RecyclerView 每一项的高度相同
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new OriginArticleAdapter(mActivity, mArticleList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * Callback method to be invoked when the RecyclerView has been scrolled. This will be
             * called after the scroll has completed.
             * <p/>
             * This callback will also be called if visible item range changes after a layout
             * calculation. In that case, dx and dy will be 0.
             *
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);


                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!loading && totalItemCount < (lastVisibleItem + Constant.VISIBLE_THRESHOLD)) {
                    new ArticleTask(mActivity).execute(mAdapter.getBottomArticleId());
                    loading = true;
                }
            }
        });

        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                SimpleArticleItem articleItem = mArticleList.get(position);
                Intent intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra(COLUMN_TYPE, mColumn);
                intent.putExtra(ARTICLE_ID, articleItem.getId());
                intent.putExtra(ARTICLE_TITLE, articleItem.getTitle());
                intent.putExtra(ARTICLE_DATE, articleItem.getPublishDate());
                intent.putExtra(ARTICLE_READ, articleItem.getReadTimes());

                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new MoreArticleTask().execute(mAdapter.getTopArticleId());
            }
        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                new MoreArticleTask().execute(mAdapter.getTopArticleId());
            }
        });

//        new ArticleTask(mActivity).execute(-1);

//        mAdapter.setOnLoadMoreListener(new OriginArticleAdapter.OnLoadMoreListener() {
//            /**
//             * 加载更多
//             */
//            @Override
//            public void onLoadMore() {
//
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //   remove progress item
//                        mArticleList.remove(mArticleList.size() - 1);
//                        //notifyItemRemoved(int position)
//                        // 第position个被删除的时候刷新，同样会有动画。
//                        mAdapter.notifyItemRemoved(mArticleList.size());
//                        List<SimpleArticleItem> moreArticles = getArticleList(mColumn,
//                                mArticleList.get(mArticleList.size() - 1).getId());
//
//                        for (int i = 0; i < moreArticles.size(); i++) {
//                            mArticleList.add(moreArticles.get(i));
//                            mAdapter.notifyItemInserted(mArticleList.size());
//                        }
//                        mAdapter.setLoaded();
//                    }
//                }, 1000);
//            }
//        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * @param type     第几个栏目
     * @param moreThan 大于该id的新闻数组
     * @return
     */
    public List<SimpleArticleItem> getMoreById(int type, int moreThan) {
        String api = ApiUrl.moreThanUrl();
        String url = String.format(api, type, moreThan);

        Logger.d("得到更多新闻 url " + url);

        OkHttpClient client = new OkHttpClient();

        Request request
                = new Request.Builder()
                .url(url)
                .build();

        try {
            Response responses = client.newCall(request).execute();
            String jsonData = responses.body().string();

            Logger.d("json数据" + jsonData);

            // 新浪云网站故障，资源耗尽
            if (jsonData.contains(Constant.SINA_ERROR_INFO)) {
                return null;
            } else {
                Gson gson = new GsonBuilder().create();

                Type listType = new TypeToken<List<SimpleArticleItem>>() {
                }.getType();
                List<SimpleArticleItem> articles = gson.fromJson(jsonData, listType);
                return articles;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.d("服务器url没获得数据");
        return new ArrayList<SimpleArticleItem>();
    }


    /**
     * @param type   第几个栏目
     * @param offset 偏移 aid
     * @return
     */
    public List<SimpleArticleItem> getArticleList(int type, int offset) {
        String api = ApiUrl.columnUrl();
        String url = String.format(api, type, offset);

        OkHttpClient client = new OkHttpClient();

        Request request
                = new Request.Builder()
                .url(url)
                .build();

        try {
            Response responses = client.newCall(request).execute();
            String jsonData = responses.body().string();

            // 新浪云网站故障，资源耗尽
            if (jsonData.contains(SINA_ERROR_INFO)) {
                return null;
            } else {
                Gson gson = new GsonBuilder().create();

                Type listType = new TypeToken<List<SimpleArticleItem>>() {
                }.getType();
                List<SimpleArticleItem> articles = gson.fromJson(jsonData, listType);
                return articles;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<SimpleArticleItem>();

    }

    //Integer 是输入参数
// 得到比某个id大的新闻数组
    class MoreArticleTask extends AsyncTask<Integer, Void, List<SimpleArticleItem>> {
        @Override
        protected List<SimpleArticleItem> doInBackground(Integer... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return getMoreById(mColumn, params[0]);
        }

        @Override
        protected void onPostExecute(List<SimpleArticleItem> simpleArticleItems) {
            super.onPostExecute(simpleArticleItems);

            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            //没有新的数据，提示消息
            if (simpleArticleItems == null || simpleArticleItems.size() == 0) {
                Snackbar.with(mActivity.getApplicationContext()) // context
                        .text(mActivity.getResources().getString(R.string.list_more_data)) // text to display
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT) // make it shorter
                        .show(mActivity); // activity where it is displayed
            } else {
                mArticleList.addAll(simpleArticleItems);
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    class ArticleTask extends AsyncTask<Integer, Void, List<SimpleArticleItem>> {

        private Context mContext;

        public ArticleTask(Context context) {
            mContext = context;
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mArticleList != null && mArticleList.size() > 0) {
                mArticleList.add(null);
                // notifyItemInserted(int position)，这个方法是在第position位置
                // 被插入了一条数据的时候可以使用这个方法刷新，
                // 注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。
                Logger.d("in mArticleList.add(null)");

                mAdapter.notifyItemInserted(mArticleList.size() - 1);
            }
        }

        /**
         * @param params 偏移量 aid
         * @return
         */
        @Override
        protected List<SimpleArticleItem> doInBackground(Integer... params) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getArticleList(mColumn, params[0]);
        }

        @Override
        protected void onPostExecute(final List<SimpleArticleItem> moreArticles) {
            // 新增新闻数据
            super.onPostExecute(moreArticles);


            if (mArticleList.size() == 0) {
                mArticleList.addAll(moreArticles);
                mAdapter.notifyDataSetChanged();
            } else {


                //删除 footer
                mArticleList.remove(mArticleList.size() - 1);
                mArticleList.addAll(moreArticles);
                mAdapter.notifyDataSetChanged();
//                for (int i = 0; i < moreArticles.size(); i++) {
//                    mArticleList.add(moreArticles.get(i));
//                    mAdapter.notifyItemInserted(mArticleList.size());
//                }
                loading = false;
//            mArticleList.addAll(moreArticles);
            }


        }

    }

}
