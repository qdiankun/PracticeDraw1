package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * 画圆弧
 * 画线
 * 画文字
 */
public class Practice11PieChartView extends View {

    private List<Paint> mPaints;

    private Paint mPathPaint;
    private Paint mTextPaint;

    private RectF mInnerPie;
    private RectF mOuterPie;

    private int mInnerPieRaidus;
    private int[] mMoveArc = new int[]{2, 7, 7, 30, 129, 120, 58};
    private String[] mStrings = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

    private static final String TAG = Practice11PieChartView.class.getSimpleName();

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mInnerPie = new RectF(100, 50, 500, 450);
        mOuterPie = new RectF(90, 40, 490, 440);
        mInnerPieRaidus = (int) ((mInnerPie.right - mInnerPie.left) / 2);

        mPaths = new ArrayList<>();
        mPoints = new ArrayList<>(3);


        initPaint();

        initAgreeLinePoints();
    }

    private float[] degrees;
    private List<Path> mPaths;
    private List<Point> mPoints;

    private void initAgreeLinePoints() {
        degrees = new float[7];
        int start = 0;


        /**
         * 计算角度 中心点点的规律
         * 0 -> 25/2 + 0
         * 1 -> 30/2 + 25
         * 2 -> 45/2 + 30 + 25
         * 3 -> 80/2 + 45 + 30 + 25
         */
        for (int i = 0; i < mMoveArc.length; i++) {
            //计算每个圆弧
            degrees[i] = start + mMoveArc[i] / 2;
            start += mMoveArc[i] + 1;
            Log.i(TAG, (i + 1) + "的角度=" + degrees[i]);
        }

        Path path;
        for (int i = 0; i < degrees.length; i++) {
            path = new Path();
            //计算角度所在的点
            Point point = getPoint(degrees[i]);
            //移动到角度的点
            path.moveTo(point.x, point.y);
            if (i == 0) {
                path.lineTo(point.x + 25, point.y);
                //标记文字点
                point.x = point.x + 25;
            }

            if (i == 1) {
                path.lineTo(point.x + 25, point.y);
                path.rLineTo(15, 15);
                path.rLineTo(15, 0);
                point.x = point.x + 55;
                point.y = point.y + 15;
            }

            if (i == 2) {
                path.lineTo(point.x + 25, point.y);
                path.rLineTo(15, 15);
                path.rLineTo(15, 0);
                point.x = point.x + 55;
                point.y = point.y + 15;
            }

            if (i == 3) {
                path.lineTo(point.x + 35, point.y + 35);
                path.rLineTo(40, 0);
                point.x = point.x + 75;
                point.y = point.y + 35;
            }

            if (i == 4) {
                path.lineTo(point.x - 35, point.y + 35);
                path.rLineTo(-40, 0);
                point.x = point.x - 75;
                point.y = point.y + 35;
            }

            if (i == 5) {
                //使用外圆
                path.moveTo(point.x - 10, point.y - 10);
                path.lineTo(point.x - 25, point.y - 25);
                path.rLineTo(-45, 0);
                point.x = point.x - 70;
                point.y = point.y - 25;
            }

            if (i == 6) {
                path.lineTo(point.x + 15, point.y - 15);
                path.rLineTo(35, 0);
                point.x = point.x + 50;
                point.y = point.y - 15;
            }

            mPoints.add(point);
            mPaths.add(path);
        }
    }

    /**
     * 圆上当前角度对应的点
     *
     * @param degrees
     * @return
     */
    private Point getPoint(float degrees) {
        Point point = new Point();
        point.x = (int) (mInnerPie.left + mInnerPieRaidus + mInnerPieRaidus * Math.cos(degrees * Math.PI / 180));
        point.y = (int) (mInnerPie.top + mInnerPieRaidus + mInnerPieRaidus * Math.sin(degrees * Math.PI / 180));
        return point;
    }

    private void initPaint() {
        mPaints = new ArrayList<>();

        Paint mPaint = new Paint(ANTI_ALIAS_FLAG);
        mPaints.add(mPaint);
        Paint mPaintPurple = new Paint(ANTI_ALIAS_FLAG);
        mPaintPurple.setColor(Color.argb(190, 115, 15, 189));
        mPaints.add(mPaintPurple);
        Paint mPaintGray = new Paint(ANTI_ALIAS_FLAG);
        mPaintGray.setColor(Color.argb(190, 144, 138, 143));
        mPaints.add(mPaintGray);
        Paint mPaintGreen = new Paint(ANTI_ALIAS_FLAG);
        mPaintGreen.setColor(Color.argb(190, 138, 216, 91));
        mPaints.add(mPaintGreen);
        Paint mPaintBlue = new Paint(ANTI_ALIAS_FLAG);
        mPaintBlue.setColor(Color.argb(190, 97, 176, 237));
        mPaints.add(mPaintBlue);
        Paint mPaintRed = new Paint(ANTI_ALIAS_FLAG);
        mPaintRed.setColor(Color.argb(190, 216, 44, 37));
        mPaints.add(mPaintRed);
        Paint mPaintYellow = new Paint(ANTI_ALIAS_FLAG);
        mPaintYellow.setColor(Color.argb(190, 228, 227, 17));
        mPaints.add(mPaintYellow);


        mPathPaint = new Paint(ANTI_ALIAS_FLAG);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(2);
        mPathPaint.setColor(Color.WHITE);

        mTextPaint = new Paint(ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(25);
        mTextPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        //画圆
        drawPie(canvas);
        //画线和文字
        drawLineTexts(canvas);
    }

    private void drawLineTexts(Canvas canvas) {

        Rect rect = new Rect();
        for (int i = 0; i < mPaths.size(); i++) {
            Path path = mPaths.get(i);
            canvas.drawPath(path, mPathPaint);

            //绘制文字
            Point point = mPoints.get(i);
            mTextPaint.getTextBounds(mStrings[i], 0, mStrings[i].length(), rect);
            //负方向
            if (i == 4 || i == 5) {
                canvas.drawText(mStrings[i], point.x - rect.width(), point.y + rect.height() / 2.0f, mTextPaint);
            } else {
                canvas.drawText(mStrings[i], point.x, point.y + rect.height() / 2.0f, mTextPaint);
            }
        }

    }

    private void drawPie(Canvas canvas) {
        int start = 0;
        for (int i = 0; i < mMoveArc.length; i++) {

            if (i == 5) {
                canvas.drawArc(mOuterPie, start, mMoveArc[i], true, mPaints.get(i));
            } else {
                canvas.drawArc(mInnerPie, start, mMoveArc[i], true, mPaints.get(i));
            }

            start = start + mMoveArc[i] + 1;
        }

//        canvas.drawArc(mInnerPie, start, start + mMoveArc[0], true, mPaints.get(0));
//        canvas.drawArc(mInnerPie, start + mMoveArc[0], mMoveArc[1], true, mPaints.get(1));
    }
}
