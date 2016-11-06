package com.wellwood.ichingdivine.ui.customizedview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wellwood.ichingdivine.R;


public class FastBar extends LinearLayout {
    private static String TAG = "FastBar";
    Bitmap bitmap;
    private Context context;
    private GridView gridView;
    private int height;
    ListView listView;
    OnFastBarChangleListener listener;
    private int max;
    private int now;
    private int nowY;
    private int portion;
    private int split;

    public interface OnFastBarChangleListener {
        void onFastBarChangeled(int i);
    }

    public FastBar(Context context, int max, int split, int portion) {
        super(context);
        setBackgroundColor(getResources().getColor(R.color.md_gray1));
        setOrientation(VERTICAL);
        this.context = context;
        this.max = max;
        this.split = split;
        this.portion = portion;
        init(true);
    }

    private void init(boolean b) {
        TextView textView;
        LayoutParams params;
        removeAllViews();

//      设置背景
        setBackgroundResource(R.color.highlighted_text_material_dark);

        for (int i = 0; i < this.split; i++) {
            textView = new TextView(this.context);
            textView.setText(new StringBuilder(String.valueOf((this.portion * i) + 1)).toString());

//            设置数字颜色
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);

            textView.setTextSize(12.0f);
            params = new LayoutParams(-1, -2);
            params.weight = 1.0f;
            textView.setGravity(1);
            textView.setLayoutParams(params);
            addView(textView);
            if (b) {
                VerticalTextView textView2 = new VerticalTextView(this.context);
                textView2.setText("...");
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setTextSize(12.0f);
                params = new LayoutParams(-1, -2);
                params.weight = 1.0f;
                textView2.setGravity(1);
                textView2.setLayoutParams(params);
                addView(textView2);
            }
        }
        textView = new TextView(this.context);
        textView.setText(new StringBuilder(String.valueOf(this.max)).toString());
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(12.0f);
        params = new LayoutParams(-1, -2);
        textView.setGravity(17);
        textView.setLayoutParams(params);
        addView(textView);
    }

    public void setGridView(GridView gridView) {
        this.gridView = gridView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.height = getMeasuredHeight();
                Log.e(TAG, "MotionEvent.ACTION_DOWN");
                this.nowY = (int) ev.getY();
                if (this.nowY < 0) {
                    this.nowY = 0;
                }
                if (this.nowY > this.height) {
                    this.nowY = this.height;
                }
                this.now = (this.max * this.nowY) / this.height;
                update();
                invalidate();
                break;
            case 1:
                Log.e(TAG, "MotionEvent.ACTION_UP");
                break;
            case 2:
                this.nowY = (int) ev.getY();
                if (this.nowY < 0) {
                    this.nowY = 0;
                }
                if (this.nowY > this.height) {
                    this.nowY = this.height;
                }
                this.now = (this.max * this.nowY) / this.height;
                update();
                invalidate();
                break;
        }
        return true;
    }

    public void update() {
        if (this.listener != null) {
            this.listener.onFastBarChangeled(this.now);
        }
        if (this.gridView != null && this.gridView.getVisibility() == 0) {
            this.gridView.setSelection(this.now);
        }
        if (this.listView != null && this.listView.getVisibility() == 0) {
            this.listView.setSelection(this.now);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#fff7de"));
        canvas.drawRect(0.0f, (float) this.nowY, (float) getWidth(), (float) (this.nowY + 20), paint);
    }

    public void setSilder(int index) {
        this.height = getMeasuredHeight();
        this.now = index;
        this.nowY = (this.height * index) / this.max;
        invalidate();
    }

    public void setOnFastBarChangleListener(OnFastBarChangleListener listener) {
        this.listener = listener;
    }

    public void showInput(boolean b) {
        init(b);
        invalidate();
    }
}
