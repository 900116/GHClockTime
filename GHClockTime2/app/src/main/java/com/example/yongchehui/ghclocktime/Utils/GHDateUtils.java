package com.example.yongchehui.ghclocktime.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yongcheHui on 16/2/19.
 */
public class GHDateUtils {
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }
}
