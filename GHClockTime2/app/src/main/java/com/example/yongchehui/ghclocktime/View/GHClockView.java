package com.example.yongchehui.ghclocktime.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by yongcheHui on 16/1/21.
 */
public class GHClockView extends View {

    public GHClockView(Context context)
    {
        super(context);
    }

    public GHClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GHClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        Paint paint = new Paint();
        int r = half_w()-12;
        int dr = r - getWidth()/15;

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);

        //外层圆
        paint.setShadowLayer(4,2,2,Color.BLACK);
        setLayerType( LAYER_TYPE_SOFTWARE , null);
        canvas.drawCircle(half_w(),half_w(),r,paint);

        //内层圆
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.DKGRAY);
        paint.setShadowLayer(0,0,0,Color.DKGRAY);
        canvas.drawCircle(half_w(),half_w(),dr,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        int bigDegreeLenth = 20;
        int littleDegreeLenth = 10;
        //刻度
        for (int i = 0; i < 60;++i)
        {
            int length = (i % 5 == 0)?bigDegreeLenth:littleDegreeLenth;
            drawMinuteLine(dr,i,length,canvas,paint);
            if (i % 15 == 0) drawHourText(dr-40,i/5,canvas,paint);
        }

        Calendar cal = Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR);//小时
        int minute=cal.get(Calendar.MINUTE);//分
        int second=cal.get(Calendar.SECOND);//秒

        //时针
        int MLength = (int)(getWidth()/3.5);
        int HLength = (int)(MLength * 0.8);
        int HWidth = 20;
        paint.setStrokeWidth(HWidth);
        paint.setColor(Color.WHITE);

        drawHourLine(HLength,hour,HLength,canvas,paint);
        drawMinuteLine(MLength,minute,MLength,canvas,paint);

        int SWidth = 5;
        int SLength = (int)(MLength*1.1);
        paint.setStrokeWidth(SWidth);
        drawMinuteLine(SLength,second,SLength,canvas,paint);
    }

    private int half_w()
    {
        return getWidth()/2;
    }

    private double degreeForHour(int hour)
    {
        return Math.PI*hour/6.0;
    }
    private double degreeForMinute(int minute)
    {
        return Math.PI*minute/30.0;
    }

    private int xWithDegree(int r,double degree)
    {
        return half_w()+(int)(r*Math.sin(degree));
    }

    private int yWithDegree(int r,double degree)
    {
        return (int)(half_w()-r*Math.cos(degree));
    }

    private int getHourX(int r,int hour)
    {
        double degree = degreeForHour(hour);
        return xWithDegree(r,degree);
    }

    private int getMinuteX(int r,int minute)
    {
        double degree = degreeForMinute(minute);
        return xWithDegree(r,degree);
    }

    private int getHourY(int r,int hour)
    {
        double degree = degreeForHour(hour);
        return yWithDegree(r,degree);
    }

    private int getMinuteY(int r,int minute)
    {
        double degree = degreeForMinute(minute);
        return yWithDegree(r,degree);
    }

    private void drawMinuteLine(int r,int minute,int length,Canvas canvas,Paint paint)
    {
        int startX = getMinuteX(r,minute);
        int startY = getMinuteY(r,minute);
        int nR = r - length;
        int endX = getMinuteX(nR,minute);
        int endY = getMinuteY(nR,minute);
        canvas.drawLine(startX,startY,endX,endY,paint);
    }

    private void drawHourText(int r,int hour,Canvas canvas,Paint paint)
    {
        int startX = getHourX(r,hour)-10;
        int startY = getHourY(r,hour)+10;
        if (hour == 0) hour = 12;
        paint.setTextSize(30);
        canvas.drawText(hour+"",startX,startY,paint);
    }

    private void drawHourLine(int r,int hour,int length,Canvas canvas,Paint paint)
    {
        int startX = getHourX(r,hour);
        int startY = getHourY(r,hour);
        int nR = r - length;
        int endX = getHourX(nR,hour);
        int endY = getHourY(nR,hour);
        canvas.drawLine(startX,startY,endX,endY,paint);
    }
}
