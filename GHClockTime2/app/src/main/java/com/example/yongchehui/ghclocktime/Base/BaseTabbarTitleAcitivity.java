package com.example.yongchehui.ghclocktime.Base;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.yongchehui.ghclocktime.R;

/**
 * Created by yongcheHui on 16/1/22.
 */
public abstract class BaseTabbarTitleAcitivity  extends BaseTitleActivity{

    private FrameLayout fl = null;
    protected Tabbar tab = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tabbar tabbar = new Tabbar(this);
        tabbar.setId(1);
        tab = tabbar;
        RelativeLayout.LayoutParams params = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dp2Px(this,45));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rootLayout.addView(tabbar,params);

        fl = (FrameLayout)findViewById(R.id.id_fragment_content);
        RelativeLayout.LayoutParams contentParams = (RelativeLayout.LayoutParams)fl.getLayoutParams();
        fl.setLayoutParams(contentParams);
    }

    public int px2Dp(Context context,float px)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale + 0.5f);
    }

    public int dp2Px(Context context,float dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5f);
    }
}
