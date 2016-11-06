package com.wellwood.ichingdivine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.model.BookInfo;

import java.util.List;

/**
 * Created by waterwoodwell on 2016/6/12.
 */
public class BookFragmentAdapter extends BaseAdapter{
    LayoutInflater inflater;
    private List list;
    private Context context;
    private static class ViewHolder {
        ImageView img;
        TextView txt_name;
        TextView txt_title;
        private ViewHolder() {
        }
    }

    public BookFragmentAdapter(Context context, List list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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

        BookInfo info = (BookInfo) this.list.get(arg0);
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_lv_book, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.item_lv_book_img);
            holder.txt_name = (TextView) view.findViewById(R.id.item_lv_index_name);
            holder.txt_title = (TextView) view.findViewById(R.id.item_lv_book_subname);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt_name.setText(info.getName().toString());
        holder.txt_title.setText(info.getAuthor().toString());

        return view;
    }
}
