package com.wellwood.ichingdivine.ui.customizedview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class VerticalTextView extends TextView {
    boolean topDown;

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int gravity = getGravity();
        if (Gravity.isVertical(gravity) && (gravity & 112) == 80) {
            setGravity((gravity & 7) | 48);
            this.topDown = false;
            return;
        }
        this.topDown = true;
    }

    public VerticalTextView(Context context) {
        super(context);
        int gravity = getGravity();
        if (Gravity.isVertical(gravity) && (gravity & 112) == 80) {
            setGravity((gravity & 7) | 48);
            this.topDown = false;
            return;
        }
        this.topDown = true;
    }

    public void setGravity(int gravity) {
        super.setGravity(gravity);
        int gravity1 = getGravity();
        if (Gravity.isVertical(gravity1) && (gravity1 & 112) == 80) {
            setGravity((gravity1 & 7) | 48);
            this.topDown = false;
            return;
        }
        this.topDown = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected boolean setFrame(int l, int t, int r, int b) {
        return super.setFrame(l, t, (b - t) + l, (r - l) + t);
    }

    public void draw(Canvas canvas) {
        if (this.topDown) {
            canvas.translate((float) getHeight(), 0.0f);
            canvas.rotate(90.0f);
        } else {
            canvas.translate(0.0f, (float) getWidth());
            canvas.rotate(-90.0f);
        }
        canvas.clipRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), Op.REPLACE);
        super.draw(canvas);
    }
}
