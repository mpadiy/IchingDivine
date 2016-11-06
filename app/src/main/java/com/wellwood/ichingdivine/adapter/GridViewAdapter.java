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

//GridViewAdapter  GridAdapter
public class GridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<GuaInfo> list;

    private static class ViewHolder {
        ImageView img_pic;
        TextView txt_title;
        private ViewHolder() {
        }
    }

    public GridViewAdapter(Context context, List<GuaInfo> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }
// error list.size  0
    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int arg0) {
        return this.list.get(arg0);
    }

    public long getItemId(int arg0) {
        return (long) arg0;
    }

    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder holder;
        GuaInfo info = (GuaInfo) this.list.get(arg0);
        if (arg1 == null) {
            arg1 = this.inflater.inflate(R.layout.item_gridview_gua, null);
            holder = new ViewHolder();
            holder.img_pic = (ImageView) arg1.findViewById(R.id.item_gridview_img);
            holder.txt_title = (TextView) arg1.findViewById(R.id.item_gridview_title);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

      //  holder.img_pic.setImageBitmap(info.getIcon());
        holder.txt_title.setText(info.getName());
        return arg1;
    }
}
