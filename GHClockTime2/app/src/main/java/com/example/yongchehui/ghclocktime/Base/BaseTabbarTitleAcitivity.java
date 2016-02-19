package com.example.yongchehui.ghclocktime.Base;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.yongchehui.ghclocktime.R;
import com.example.yongchehui.ghclocktime.Utils.GHScreenUtils;

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
        RelativeLayout.LayoutParams params = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, GHScreenUtils.dp2Px(this,45));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rootLayout.addView(tabbar,params);

        fl = (FrameLayout)findViewById(R.id.id_fragment_content);
        RelativeLayout.LayoutParams contentParams = (RelativeLayout.LayoutParams)fl.getLayoutParams();
        contentParams.addRule(RelativeLayout.ABOVE,1);
        fl.setLayoutParams(contentParams);
    }


}
