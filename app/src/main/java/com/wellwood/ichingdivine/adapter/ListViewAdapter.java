package com.wellwood.ichingdivine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.model.GuaInfo;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private List<GuaInfo> list;

    private static class ViewHolder {
        ImageView img;
        TextView txt_name;
        TextView txt_title;

        private ViewHolder() {
        }
    }

    public ListViewAdapter(Context context, List<GuaInfo> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int arg0) {
        return this.list.get(arg0);
    }

    public long getItemId(int arg0) {
        return (long) arg0;
    }

    public View getView(int arg0, View view, ViewGroup arg2) {
        ViewHolder holder;
        GuaInfo info = (GuaInfo) this.list.get(arg0);
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_lv_gua, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.item_lv_gua_img);
            holder.txt_name = (TextView) view.findViewById(R.id.item_lv_gua_name);
            holder.txt_title = (TextView) view.findViewById(R.id.item_lv_gua_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
       // holder.img.setImageBitmap(info.getIcon());
        holder.txt_name.setText(info.getName());
        //holder.txt_subnam.setText(info.getMsg());
        return view;
    }
}
