package com.example.yongchehui.ghclocktime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.yongchehui.ghclocktime.Base.BaseTabbarDataSource;
import com.example.yongchehui.ghclocktime.Base.BaseTabbarTitleAcitivity;
import com.example.yongchehui.ghclocktime.Base.ContentFragment;
import com.example.yongchehui.ghclocktime.Base.Tabbar;
import com.example.yongchehui.ghclocktime.Module.GHUserClock;
import com.example.yongchehui.ghclocktime.Module.GHUserClockType;
import com.example.yongchehui.ghclocktime.View.ClockFragment;
import com.example.yongchehui.ghclocktime.View.TimeClockFragment;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by yongcheHui on 16/1/20.
 */
public class HomeActivity extends BaseTabbarTitleAcitivity implements BaseTabbarDataSource{

    /**
     * tabbar
     * */
    public int tarbar_count(Tabbar tabbar)
    {
        return 3;
    }

    public String tabbar_title(Tabbar tabbar,int index){
        String titles[] = {"时间","闹钟","日历"};
        return titles[index];
    }
    public Integer tabbar_images(Tabbar tabbar, int index)
    {
        return -1;
    }

    public Class tabbar_frag(Tabbar tabbar, int index)
    {
        if (index == 0) return ContentFragment.class;
        else if (index == 1) return ClockFragment.class;
        else return TimeClockFragment.class;
    }


    private int lastTouch = 0;

    @Override
    protected Integer getContentViewLayout()
    {
        return R.layout.home;
    }

    @Override
    protected void  initControlView(){
        setTitle("主页");
        if (tab.dataSource == null) tab.setDataSource(this);
    }

    @Override
    public Integer contentLayoutID(Tabbar tabbar) {
        return getContentViewLayout();
    }

    private ClockFragment clockFragment = null;

    public void tabbarTouchEvent(Tabbar tabbar, int index, Fragment fragment){
        if (index == lastTouch) return;
        else lastTouch = index;
        //设置标题
        String title = tabbar_title(tabbar,index);
        setTitle(title);
        //设置变量
        if (index == 0){
            titleFragment.removeRightItem();
        }
        else if(index == 1){ //闹钟
            titleFragment.addRightItem("添加",new ClockRightClickListener(this));
            clockFragment = (ClockFragment) fragment;
        }
        else {
            titleFragment.removeRightItem();
        }
    }

    /**
     * 闹钟添加点击事件
     * */
    class ClockRightClickListener implements Button.OnClickListener{
        public Context contex = null;
        public ClockRightClickListener(Context context)
        {
            this.contex = context;
        }

        @Override
        //点击添加
        public void onClick(View v) {
            new TimePickerDialog(contex, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Log.i("---","--------");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(0,0,0,hourOfDay,minute,0);
                    Date date = calendar.getTime();
                    ClockRightClickListener.this.setTime(date);
                }
            }, 8, 0, false).show();
        }

        private int tWhich = 0;
        /**
         * 设置完毕时间
         * */
        public void setTime(final Date date)
        {
            final String[] arrayTypes = new String[] { "仅一次", "每天", "工作日", "周末","自定义"};
            final Dialog alertDialog = new AlertDialog.Builder(contex).
                    setTitle("请选择闹钟的类型")
                    .setSingleChoiceItems(arrayTypes, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                           tWhich = which;
                        }
                    }).
                            setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GHUserClockType type = GHUserClockType.valueOf(tWhich);
                            ClockRightClickListener.this.setType(date,type);
                        }
                    }) .create();
            alertDialog.show();
        }

        /**
         * 设置类型
         * */
        public void setType(Date date,GHUserClockType type)
        {
            if (type == GHUserClockType.TYPEDEF)
            {

            }
            else
            {
                GHUserClock.addClock(contex,date,type,null);
                clockFragment.reloadData();
            }
        }
    }
}
