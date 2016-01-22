package com.example.yongchehui.ghclocktime.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yongchehui.ghclocktime.R;

/**
 * Created by yongcheHui on 16/1/22.
 */
public class TimeClockFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeclock_frag, container, false);
        return view;
    }
}
