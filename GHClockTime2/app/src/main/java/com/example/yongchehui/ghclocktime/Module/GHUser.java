package com.example.yongchehui.ghclocktime.Module;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.yongchehui.ghclocktime.Base.UpdateEventHandler;
import com.example.yongchehui.ghclocktime.GHRealm;
import com.example.yongchehui.ghclocktime.Utils.GHStringUtils;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yongcheHui on 16/1/21.
 */
public class GHUser extends RealmObject {
    //用户名
    private String userName = null;
    //密码
    private String passWord;
    //最后一次登录时间
    private long finalLogintime = -2;

    //用户id
    @PrimaryKey
    private long objectID = -2;

    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }

    public long getObjectID() {
        return objectID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getFinalLogintime() {
        return finalLogintime;
    }

    public void setFinalLogintime(long finalLogintime) {
        this.finalLogintime = finalLogintime;
    }

    public String getPassWord() {
        return passWord;
    }

    public GHUser(){

    }

    public GHUser(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public static GHUser findUser(Context context, String userName)
    {
        Realm rm = Realm.getInstance(context);
        RealmResults<GHUser> results= rm.where(GHUser.class).equalTo("userName",userName).findAll();
        if (results.size() == 0) return null;
        else return results.first();
    }

    public static GHUser findUser(Context context, String userName,String passWord)
    {
        Realm rm = Realm.getInstance(context);
        RealmResults<GHUser> results= rm.where(GHUser.class).equalTo("userName",userName).equalTo("passWord",passWord).findAll();
        if (results.size() == 0) return null;
        else return results.first();
    }

    public static void register(Context context, String userName, String passWord)
    {
        if(findUser(context,userName) != null){
            new AlertDialog.Builder(context).setTitle("警告").setMessage("该用户名已经被注册").setPositiveButton("确定",null).show();
        }
        else
        {
            GHUser user = new GHUser(userName, GHStringUtils.MD5(passWord));
            if (user.getObjectID() == -2) user.setObjectID(System.currentTimeMillis());
            GHRealm.save(context,user);
        }
    }

    public static boolean login(Context context,String userName,String passWord)
    {
        final GHUser  user = findUser(context,userName,GHStringUtils.MD5(passWord));
        if(user == null){
            new AlertDialog.Builder(context).setTitle("警告").setMessage("用户名,或者密码不对").setPositiveButton("确定",null).show();
            return false;
        }
        else
        {
            SharedPreferences settings = context.getSharedPreferences("loginState", 0);
            SharedPreferences.Editor edt = settings.edit();
            edt.putBoolean("isLogin",true);
            edt.commit();

            GHRealm.update(context,new UpdateEventHandler() {
                @Override
                public void execute() {
                    user.setFinalLogintime(System.currentTimeMillis());
                }
            });
            return true;
        }
    }
}
