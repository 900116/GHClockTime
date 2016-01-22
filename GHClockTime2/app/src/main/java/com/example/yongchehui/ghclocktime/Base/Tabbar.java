package com.example.yongchehui.ghclocktime.Base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yongchehui.ghclocktime.R;

import junit.framework.Assert;

import java.lang.reflect.Constructor;

/**
 * Created by yongcheHui on 16/1/22.
 */
public class Tabbar extends LinearLayout {

    public Tabbar(Context context) {
        super(context);
    }

    public Tabbar(Context context,BaseTabbarDataSource dataSource) {
        super(context);
        this.setDataSource(dataSource);
    }

    public BaseTabbarDataSource dataSource = null;

    public void setDataSource(BaseTabbarDataSource dataSource) {
        if (dataSource == this.dataSource) return;
        this.removeAllViews();
        setBackgroundResource(R.drawable.tabshape);
        for (int i = 0;i < dataSource.tarbar_count(this);++i)
        {
            final int touchI = i;
            Button btn = new Button(this.getContext());
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment obj = null;
                    Class fragClass = Tabbar.this.dataSource.tabbar_frag(Tabbar.this,touchI);
                    try {
                        FragmentManager fm = ((Activity)Tabbar.this.getContext()).getFragmentManager();
                        // 开启Fragment事务
                        FragmentTransaction transaction = fm.beginTransaction();
                        if (fragClass == ContentFragment.class)
                        {
                            Integer layoutID = Tabbar.this.dataSource.contentLayoutID(Tabbar.this);
                            Constructor<ContentFragment> con = fragClass.getConstructor(Integer.class);
                            obj = con.newInstance(layoutID);
                        }
                        else obj = (Fragment) fragClass.newInstance();
                        transaction.replace(R.id.id_fragment_content,obj);
                        transaction.commit();
                    }
                    catch (Exception e)
                    {
                    }
                    finally {

                    }
                    Tabbar.this.dataSource.tabbarTouchEvent(Tabbar.this,touchI,obj);
                }
            });

            String title = dataSource.tabbar_title(this,i);
            if (title == null) title = "";
            btn.setText(title);
            btn.setTextColor(Color.WHITE);

            //图片
            Integer imageID = dataSource.tabbar_images(this,i);
            if (imageID > 0 ) btn.setBackgroundResource(imageID);

            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            btn.setLayoutParams(params);
            btn.setBackgroundColor(Color.TRANSPARENT);

            this.addView(btn);
        }
        this.dataSource = dataSource;
    }

    @Override
    public int getOrientation() {
        return LinearLayout.HORIZONTAL;
    }

    @Override
    public void setOrientation(int orientation) {
        Assert.assertTrue("不能设置方向",true);
    }
}
