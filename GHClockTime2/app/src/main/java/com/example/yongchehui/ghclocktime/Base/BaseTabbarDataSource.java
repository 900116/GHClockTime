package com.example.yongchehui.ghclocktime.Base;


import android.app.Fragment;

/**
 * Created by yongcheHui on 16/1/22.
 */
public interface BaseTabbarDataSource {
    int tarbar_count(Tabbar tabbar);
    String tabbar_title(Tabbar tabbar,int index);
    Integer tabbar_images(Tabbar tabbar,int index);
    void tabbarTouchEvent(Tabbar tabbar, int index, Fragment frag);
    Class tabbar_frag(Tabbar tabbar,int index);
    Integer contentLayoutID(Tabbar tabbar);
}
