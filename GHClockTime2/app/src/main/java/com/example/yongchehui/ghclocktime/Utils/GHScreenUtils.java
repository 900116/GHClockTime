package com.example.yongchehui.ghclocktime.Utils;

import android.content.Context;

/**
 * Created by yongcheHui on 16/1/27.
 */
public class GHScreenUtils {

    public static int px2Dp(Context context, float px)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale + 0.5f);
    }

    public static int dp2Px(Context context,float dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5f);
    }
}
