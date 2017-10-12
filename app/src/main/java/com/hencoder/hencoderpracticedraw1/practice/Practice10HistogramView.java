package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice10HistogramView extends View {

    private Paint mHistoryPaint;
    private Paint mTextPaint;
    private Paint mCoordinatePaint;

    private int mCoordinateLeft = 50;
    private int mCoordinateTop = 50;
    private int mCoordinateWidth = 600;
    private int mCoordinateHeight = 500;



    private Path mPath;



    private List<String> mHistogramNames;

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
        mTextPaint = new Paint();

        mCoordinatePaint = new Paint();
        mCoordinatePaint.setStyle(Paint.Style.STROKE);
        mCoordinatePaint.setStrokeWidth(2);
        mCoordinatePaint.setColor(Color.WHITE);

        mPath = new Path();

        mHistogramNames = new ArrayList<>();
        mHistogramNames.addAll(Arrays.asList(new String[]{"Froyo", "GB", "ICS", "JB", "KiKat", "L", "M"}));
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

        canvas.drawLine(mCoordinateLeft,mCoordinateTop,mCoordinateLeft,mCoordinateTop+mCoordinateHeight,mCoordinatePaint);
        canvas.drawLine(mCoordinateLeft, mCoordinateTop + mCoordinateHeight, mCoordinateLeft + mCoordinateWidth, mCoordinateTop + mCoordinateHeight, mCoordinatePaint);

    }


    private void drawHistogram(Canvas canvas) {

    }


    private void drawTitle(Canvas canvas) {

    }
}
