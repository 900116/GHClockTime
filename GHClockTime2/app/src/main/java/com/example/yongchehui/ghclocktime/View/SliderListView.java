package com.example.yongchehui.ghclocktime.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by yongcheHui on 16/2/2.
 */
public class SliderListView extends ListView{
    public SliderListView(Context context)
    {
        super(context);
    }

    public SliderListView(Context context, AttributeSet set)
    {
        super(context,set);
    }

    private SliderView mFocusedItemView;

    @Override
    public   boolean  onTouchEvent(MotionEvent event) {
        switch  (event.getAction()) {
            case  MotionEvent.ACTION_DOWN: {
                int  x = ( int ) event.getX();
                int  y = ( int ) event.getY();
//我们想知道当前点击了哪一行
                int  position = pointToPosition(x, y);
                Log.e("listview",  "postion="  + position);
                if  (position != INVALID_POSITION) {
//得到当前点击行的数据从而取出当前行的item。
//可能有人怀疑，为什么要这么干？为什么不用getChildAt(position)？
//因为ListView会进行缓存，如果你不这么干，有些行的view你是得不到的。
                    mFocusedItemView = (SliderView) getChildAt(position);
//                    MessageItem data = (MessageItem) getItemAtPosition(position);
//                    mFocusedItemView = data.slideView;
//                    Log.e(TAG,  "FocusedItemView="  + mFocusedItemView);
                }
            }
            default :
                break ;
        }
//向当前点击的view发送滑动事件请求，其实就是向SlideView发请求
        if  (mFocusedItemView !=  null ) {
            mFocusedItemView.onRequireTouchEvent(event);
        }
        return   super .onTouchEvent(event);
    }
}
