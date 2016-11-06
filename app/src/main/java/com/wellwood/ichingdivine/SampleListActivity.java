package com.wellwood.ichingdivine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wellwood.ichingdivine.API.Api;
import com.wellwood.ichingdivine.adapter.BaseViewHolder;
import com.wellwood.ichingdivine.model.BaseModel;
import com.wellwood.ichingdivine.model.Benefit;
import com.wellwood.ichingdivine.ui.activity.baseactivity.BaseListActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stay on 25/2/16.
 * Powered by www.stay4it.com
 */
public class SampleListActivity extends BaseListActivity<Benefit> {

    private int random;
    private int page = 1;

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.title_activity_sample);
    }

    @Override
    protected void setUpData() {
        super.setUpData();

        onLoad();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }




    public void onLoad() {

        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

//        （1）创建Retrofit实例
//        通过实现Converter.Factoty接口来创建一个自定义的converter。
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Api api = retrofit.create(Api.class);



        Call<BaseModel<ArrayList<Benefit>>> call = api.defaultBenefits(20, page++);


//        Call是Retrofit中重要的一个概念，代表被封装成单个请求/响应的交互行为
//        通过调用Retrofit2的execute（同步）或者enqueue（异步）方法，
//        发送请求到网络服务器，并返回一个响应（Response）。

        call.enqueue(new Callback<BaseModel<ArrayList<Benefit>>>() {
                         @Override
                         public void onResponse(Call<BaseModel<ArrayList<Benefit>>> call, Response<BaseModel<ArrayList<Benefit>>> response) {

                                 mDataList.addAll(response.body().results);
                                 adapter.notifyDataSetChanged();


                         }

                         @Override
                         public void onFailure(Call<BaseModel<ArrayList<Benefit>>> call, Throwable t) {

                         }
                     }
        );
    }

    class SampleViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.mSampleListItemImg);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setVisibility(View.GONE);
            Glide.with(mSampleListItemImg.getContext())
                    .load(mDataList.get(position).url)
                    .centerCrop()
                    .placeholder(R.color.md_green_blue)
                    .crossFade()
                    .into(mSampleListItemImg);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }
}
