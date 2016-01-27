package com.example.yongchehui.ghclocktime.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.yongchehui.ghclocktime.R;
import com.example.yongchehui.ghclocktime.Utils.GHScreenUtils;

/**
 * Created by yongcheHui on 16/1/19.
 */
public class TitleFragment extends Fragment {

    private ViewGroup view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.title_frag, container, false);
        return view;
    }

    private final int leftButtonID = 1;
    private final int rightButtonID = 2;

    private RelativeLayout.LayoutParams paramWithLeft(boolean left)
    {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(GHScreenUtils.dp2Px(this.getActivity(),80), GHScreenUtils.dp2Px(this.getActivity(),40));
        lp.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        lp.addRule(left?RelativeLayout.ALIGN_PARENT_LEFT:RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        lp.setMargins(left?10:0,0,!left?10:0,0);
        return lp;
    }

    public void removeLeftItem()
    {
        //删除之前的view
        Button button = (Button) view.findViewById(leftButtonID);
        if (button != null) view.removeView(button);
    }

    public void removeRightItem()
    {
        //删除之前的view
        Button button = (Button) view.findViewById(rightButtonID);
        if (button != null) view.removeView(button);
    }

    public void addLeftItem(String title, Button.OnClickListener listener)
    {
        removeLeftItem();
        Button button = new Button(this.getActivity());
        button.setText(title);
        button.setOnClickListener(listener);

        RelativeLayout.LayoutParams lp = paramWithLeft(true);
        button.setId(leftButtonID);
        view.addView(button,lp);
    }

    public void addLeftItem(Integer bgImageID, Button.OnClickListener listener)
    {
        removeLeftItem();
        Button button = new Button(this.getActivity());
        button.setBackgroundResource(bgImageID);
        button.setOnClickListener(listener);

        RelativeLayout.LayoutParams lp = paramWithLeft(true);
        button.setId(leftButtonID);
        view.addView(button,lp);
    }

    public void addRightItem(String title, Button.OnClickListener listener)
    {
        removeRightItem();

        Button button = new Button(this.getActivity());
        button.setText(title);
        button.setOnClickListener(listener);

        RelativeLayout.LayoutParams lp = paramWithLeft(false);
        button.setId(rightButtonID);
        view.addView(button,lp);
    }

    public void addRightItem(Integer bgImageID, Button.OnClickListener listener)
    {
        removeLeftItem();

        Button button = new Button(this.getActivity());
        button.setBackgroundResource(bgImageID);
        button.setOnClickListener(listener);

        RelativeLayout.LayoutParams lp = paramWithLeft(false);
        button.setId(rightButtonID);
        view.addView(button,lp);
    }
}