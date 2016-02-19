package com.example.yongchehui.ghclocktime.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yongcheHui on 16/2/19.
 */
public class System {
    //locSDK 的ak
    public static final String A_K = "gN4TXBFVEesxMIXmOVgunWlt";

    public static final String Weather_API = "http://wthrcdn.etouch.cn/WeatherApi";

    public static SharedPreferences loginSetting(Context context)
    {
        return context.getSharedPreferences("loginState",  Activity.MODE_PRIVATE);
    }

    public static SharedPreferences weatherSetting(Context context)
    {
        return context.getSharedPreferences("weather",  Activity.MODE_PRIVATE);
    }
}
