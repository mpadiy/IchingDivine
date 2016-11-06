package com.wellwood.ichingdivine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.model.Recordinfo;

import java.util.List;

public class RecordSearchAdapter extends BaseAdapter {
    private List<Recordinfo> list;
    private Context context;

    public RecordSearchAdapter(Context context, List<Recordinfo> list) {
        // this.actArchive = mArchive;
        this.context = context;
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int i) {
        return this.list.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_lv_record, null);
        Recordinfo info = (Recordinfo) this.list.get(i);
        // String str = (String) ((ContentValues) this.list.get(i)).get("origuabin");
        // String dtime = (String) ((HashMap) this.list.get(i)).get("dtime");
        TextView tvItemName = (TextView) inflate.findViewById(R.id.tvItemName);
        TextView tvItemNote = (TextView) inflate.findViewById(R.id.tvItemNote);
        TextView tvItemTime = (TextView) inflate.findViewById(R.id.tvItemTime);
        tvItemName.setText(info.getName());
        tvItemNote.setText(info.getReason().trim().replace("<br>","\n"));
        tvItemTime.setText(info.getdtime().trim().replace("<br>","\n"));
        return inflate;
    }
}

