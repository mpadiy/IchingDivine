package com.wellwood.ichingdivine.ui.customizedview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class myNote
extends EditText {
    Context context;

    public myNote(Context context) {
        super(context);
        this.context = context;
    }

    public myNote(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public myNote(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.context = context;
    }

//    protected void onDraw(Canvas canvas) {
//        int n2 = getWidth();
//        Paint paint = new Paint();
//        //(paint.setStyle(Paint.Style.FILL))   实心
//        // (paint.setStyle(Paint.Style.STROKE);空心
//        //消除锯齿：paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        //paint.setColor();
//        paint.setStrokeWidth(1.5f);
//        int n3 = this.getPaddingTop();
//        int n4 = this.getPaddingBottom();
//        int n5 = this.getScrollY();
//        int n6 = n2 + this.getScrollX();
//        int n7 = n5 + this.getHeight() - n4;
//        int n8 = this.getLineHeight();
//        int n9 = n5 + (n8 - (n5 - n3) % n8);
//        do {
//            if (n9 >= n7) {
//                super.onDraw(canvas);
//                return;
//            }
//            canvas.drawLine((float)4, (float)n9, (float)(n6 - 4), (float)n9, paint);
//            n9 += n8;
//        } while (true);
//    }




    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.5f);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int scrollY = getScrollY();
        int scrollX = getScrollX() + width;
        int height = (getHeight() + scrollY) - paddingBottom;
        int lineHeight = getLineHeight();
        for (int i = (lineHeight - ((scrollY - paddingTop) % lineHeight)) + scrollY; i < height; i += lineHeight) {
            canvas.drawLine((float) 4, (float) i, (float) (scrollX - 4), (float) i, paint);
        }
        super.onDraw(canvas);
    }



}

