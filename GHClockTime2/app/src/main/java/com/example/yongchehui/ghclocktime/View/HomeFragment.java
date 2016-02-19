package com.example.yongchehui.ghclocktime.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yongchehui.ghclocktime.Base.ContentFragment;
import com.example.yongchehui.ghclocktime.Module.GHWeatherInfo;
import com.example.yongchehui.ghclocktime.R;
import com.example.yongchehui.ghclocktime.Utils.GHDateUtils;
import com.example.yongchehui.ghclocktime.Utils.GHWeatherCallBack;
import com.example.yongchehui.ghclocktime.Utils.GHWeatherManager;
import com.example.yongchehui.ghclocktime.Utils.System;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yongcheHui on 16/1/28.
 */
public class HomeFragment extends ContentFragment {
    public HomeFragment(Integer viewLayout) {
        super(viewLayout);
    }

    private TextView weatherTV = null;
    private TextView airTV = null;
    private TextView dateTV = null;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        weatherTV = (TextView) rootView.findViewById(R.id.home_weather);
        airTV = (TextView) rootView.findViewById(R.id.home_air);
        dateTV = (TextView)rootView.findViewById(R.id.home_date);

        weatherTV.setText("");
        airTV.setText("");

        Date date = new Date(java.lang.System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("M月d日");
        dateTV.setText(format.format(date)+" "+ GHDateUtils.getWeekOfDate(date));

        GHWeatherManager.registerWeatherCallBack(this.getActivity(), new GHWeatherCallBack() {
            @Override
            public void receiveWeatherInfo(GHWeatherInfo info) {
                String html="<font color='#CA2642'><big>"+info.getWendu()+"℃"+"</big></font>&nbsp;";
                html+="<font color='#0E9BB7'> <small>"+info.getMin()+"~"+info.getMax()+"&nbsp;风力:"+info.getFengli()+"</small> <font>";
                CharSequence charSequence= Html.fromHtml(html);
                weatherTV.setText(charSequence);
            }
        });
        return rootView;
    }


}
