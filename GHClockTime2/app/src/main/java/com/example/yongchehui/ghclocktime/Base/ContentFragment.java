package com.example.yongchehui.ghclocktime.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by yongcheHui on 16/1/20.
 */
public class ContentFragment extends Fragment{
    protected Integer viewLayout = 0;
    protected RelativeLayout rootView = null;

    public ContentFragment(Integer viewLayout) {
        this.viewLayout = viewLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (RelativeLayout) inflater.inflate(this.viewLayout, container, false);
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        BaseTitleActivity act = (BaseTitleActivity) this.getActivity();
        act.initControlView();
    }
}
