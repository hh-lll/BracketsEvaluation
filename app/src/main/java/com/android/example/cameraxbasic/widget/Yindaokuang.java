package com.android.example.cameraxbasic.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.example.cameraxbasic.R;

/**
 * TODO: document your custom view class.
 */
public class Yindaokuang extends View {

    private Paint mPaint;
    private int width = 500;
    private int height = 500;
    private int move_x = 300;
    private int move_y = 450;


    //画笔的粗细，单位未知
    private static int mStrokeWidth = 10;

    public Yindaokuang(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int kcm_width = getMeasuredWidth();
        int kcm_height = getMeasuredHeight();
        Rect rect = new Rect(move_x, move_y, width + move_x, height + move_y);
        if (kcm_width - width > 0 && kcm_height - height > 0) {
            mPaint.setAntiAlias(true); // 设置画笔为无锯齿
            mPaint.setDither(true); // 设置画笔为防抖动
            mPaint.setColor(Color.BLACK); // 设置画笔的颜色
            mPaint.setStrokeWidth(mStrokeWidth); // 设置画笔的线宽
            mPaint.setStyle(Paint.Style.STROKE); // 设置画笔的类型。STROKE表示空心，FILL表示实心 stroke绘制出的矩形为空心！
            canvas.drawRect(rect, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + x);
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + y);
        System.out.println("----------------------------------------------------------" + (width + move_x));
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" + (height + move_y));

//        改变引导框大小
        if (Math.abs((int) x - width - move_x) < 50 && Math.abs((int) y - height - move_y) < 50) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    width = (int) x - move_x;
                    height = (int) y - move_y;
                    break;
            }
        }
//        改变框的位置
        else if(Math.abs((int) x - width/2 - move_x) < 50 && Math.abs((int) y - height/2 - move_y)<50){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    move_x = (int) x - width / 2;
                    move_y = (int) y - height / 2;
                    break;
            }
        }
        // 调用 draw()
        invalidate();

        return true;
    }
}