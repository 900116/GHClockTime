package com.example.yongchehui.ghclocktime;

import android.app.Fragment;

import com.example.yongchehui.ghclocktime.Base.BaseTabbarDataSource;
import com.example.yongchehui.ghclocktime.Base.BaseTabbarTitleAcitivity;
import com.example.yongchehui.ghclocktime.Base.ContentFragment;
import com.example.yongchehui.ghclocktime.Base.Tabbar;
import com.example.yongchehui.ghclocktime.View.ClockFragment;
import com.example.yongchehui.ghclocktime.View.TimeClockFragment;

/**
 * Created by yongcheHui on 16/1/20.
 */
public class HomeActivity extends BaseTabbarTitleAcitivity implements BaseTabbarDataSource{

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

    public int tarbar_count(Tabbar tabbar)
    {
        return 3;
    }

    public String tabbar_title(Tabbar tabbar,int index){
        String titles[] = {"时间","闹钟","秒表"};
        return titles[index];
    }

    @Override
    public Integer contentLayoutID(Tabbar tabbar) {
        return getContentViewLayout();
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

    private ClockFragment clock = null;
    private TimeClockFragment timerClock = null;

    public void tabbarTouchEvent(Tabbar tabbar, int index, Fragment fragment){
        if (index == lastTouch) return;
        else lastTouch = index;
        if (index == 0) contentView = (ContentFragment) fragment;
        else if(index == 1) clock = (ClockFragment) fragment;
        else timerClock = (TimeClockFragment)fragment;

    }
}
