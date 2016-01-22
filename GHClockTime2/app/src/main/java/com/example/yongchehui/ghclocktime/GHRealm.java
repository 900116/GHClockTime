package com.example.yongchehui.ghclocktime;

import android.content.Context;

import com.example.yongchehui.ghclocktime.Base.UpdateEventHandler;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by yongcheHui on 16/1/21.
 */
public class GHRealm {
    public static void save(Context context,RealmObject obj)
    {
        Realm rm = Realm.getInstance(context);
        rm.beginTransaction();
        rm.copyToRealm(obj);
        rm.commitTransaction();
    }

    public static void update(Context context,UpdateEventHandler handler)
    {
        Realm rm = Realm.getInstance(context);
        rm.beginTransaction();
        handler.execute();
        rm.commitTransaction();
    }
}
