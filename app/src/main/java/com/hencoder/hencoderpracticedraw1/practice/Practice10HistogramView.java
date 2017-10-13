package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Practice10HistogramView extends View {

    private static final String TAG = Practice10HistogramView.class.getSimpleName();

    private Paint mHistoryPaint;
    private Paint mTextPaint;
    private Paint mCoordinatePaint;

    private int mCoordinateLeft = 50;
    private int mCoordinateTop = 50;
    private int mCoordinateWidth = 700;
    private int mCoordinateHeight = 500;

    private int columnSpace = 20;
    private int columnWidth = 60;

    private Path mPath;

    private List<String> mHistogramNames;


    private int mLeft;
    private int mRight;
    private String title = "直方图";

    private static final int COLUMN_MIN_HEIGHT = 80;


    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mHistoryPaint = new Paint();
        mHistoryPaint.setColor(Color.parseColor("#72B916"));
        mHistoryPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(16);

        mCoordinatePaint = new Paint();
        mCoordinatePaint.setStyle(Paint.Style.STROKE);
        mCoordinatePaint.setStrokeWidth(1);
        mCoordinatePaint.setColor(Color.WHITE);

        mPath = new Path();

        mHistogramNames = new ArrayList<>();
        mHistogramNames.addAll(Arrays.asList(new String[]{"Froyo", "GB", "ICS", "JB", "KiKat", "L", "M"}));

        mLeft = mCoordinateLeft + columnSpace;
        mRight = mLeft + columnWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        drawCoordinate(canvas);

        drawHistogram(canvas);

        drawTitle(canvas);
    }

    private void drawCoordinate(Canvas canvas) {

        mPath.moveTo(mCoordinateLeft, mCoordinateTop);
        mPath.lineTo(mCoordinateLeft, mCoordinateTop + mCoordinateHeight);
        mPath.lineTo(mCoordinateLeft + mCoordinateWidth, mCoordinateTop + mCoordinateHeight);
        canvas.drawPath(mPath, mCoordinatePaint);

//        canvas.drawLine(mCoordinateLeft,mCoordinateTop,mCoordinateLeft,mCoordinateTop+mCoordinateHeight,mCoordinatePaint);
//        canvas.drawLine(mCoordinateLeft, mCoordinateTop + mCoordinateHeight, mCoordinateLeft + mCoordinateWidth, mCoordinateTop + mCoordinateHeight, mCoordinatePaint);
    }

    private void drawHistogram(Canvas canvas) {
        int HistoryGramBottom = mCoordinateTop + mCoordinateHeight;
        for (int i = 0; i < mHistogramNames.size(); i++) {
            //绘制柱状矩形
            int randomTop = new Random().nextInt(HistoryGramBottom - COLUMN_MIN_HEIGHT) + COLUMN_MIN_HEIGHT;
            Log.i(TAG, "randomTop=" + randomTop);
            canvas.drawRect(mLeft, randomTop, mRight, mCoordinateTop + mCoordinateHeight, mHistoryPaint);
            //绘制矩形下面文字
            String name = mHistogramNames.get(i);
            Rect bound = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), bound);
            canvas.drawText(name, mLeft + (columnWidth - bound.width()) / 2.0f,
                    mCoordinateTop + mCoordinateHeight + 20 + (bound.height()) / 2.0f, mTextPaint);
            //绘制
            mLeft = mRight + columnSpace;
            mRight = mLeft + columnWidth;
        }
    }


    private void drawTitle(Canvas canvas) {
        mTextPaint.setTextSize(20);
        canvas.drawText(title, mCoordinateLeft + mCoordinateWidth / 2,
                mCoordinateTop + mCoordinateHeight + 80, mTextPaint);
    }
}
