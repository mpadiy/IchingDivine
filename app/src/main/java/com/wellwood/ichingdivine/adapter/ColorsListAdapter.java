package com.wellwood.ichingdivine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wellwood.ichingdivine.R;

import java.util.List;

/**
 * Created by lgp on 2015/6/7.
 */
public class ColorsListAdapter extends BaseAdapter {


    private int checkItem;
    public List<Integer> list;
    public Context mContext;
    public LayoutInflater mInflater;


    public ColorsListAdapter(Context context, List<Integer> list) {
        this.mContext = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);


    }


    public View bindView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.colors_image_layout, null);
            holder = new Holder();
            holder.imageView1 = (ImageView)convertView.findViewById(R.id.img_1);
            holder.imageView2 = (ImageView)convertView.findViewById(R.id.img_2);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.imageView1.setImageResource(list.get(position));
        if (checkItem == position){
            holder.imageView2.setImageResource(R.drawable.ic_done_white);
        }
        return convertView;
    }




    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = bindView(position, convertView, parent);
//        addInternalClickListener(convertView, position, list.get(position));
        return convertView;
    }



    @Override
    public Integer getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getCount() {
        return list.size();
    }





    public int getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(int checkItem) {
        this.checkItem = checkItem;
    }

    static class Holder {
        ImageView imageView1;
        ImageView imageView2;
    }







}
