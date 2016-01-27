package com.example.yongchehui.ghclocktime.Module;

import android.content.Context;

import com.example.yongchehui.ghclocktime.GHRealm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;


/**
 * Created by yongcheHui on 16/1/27.
 */
public class GHUserClock extends RealmObject{
    //闹钟id
    @PrimaryKey
    private long clockID = -2;

    public void setClockID(long clockID) {
        this.clockID = clockID;
    }

    public long getClockID() {
        return clockID;
    }

    //开启状态
    private boolean openState = true;

    public boolean isOpenState() {
        return openState;
    }

    public void setOpenState(boolean openState) {
        this.openState = openState;
    }

    //时间
    private Date time = null;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    //重复
    private int type = GHUserClockType.ONCE.value();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //重复天数
    private String repeatDay = null;
    public String getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(String repeatDay) {
        this.repeatDay = repeatDay;
    }

    public static void addClock(Context context, Date time, GHUserClockType type, ArrayList<Integer> repeatDay)
    {
        GHUserClock clock = new GHUserClock();
        clock.setClockID(System.currentTimeMillis());
        clock.setTime(time);
        clock.setType(type.ordinal());
        StringBuffer sb = new StringBuffer();
        if (repeatDay != null && repeatDay.size() > 0)
        {
            for (Integer i:repeatDay) {
                sb.append(i+",");
            }
            sb.deleteCharAt(sb.length()-1);
            clock.setRepeatDay(sb.toString());
        }
        GHRealm.save(context,clock);
    }

    public static List<GHUserClock> getClockList(Context context)
    {
        RealmResults<GHUserClock> results = Realm.getInstance(context).where(GHUserClock.class).findAll();
        return results;
    }
}
