package com.example.yongchehui.ghclocktime.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Xml;

import com.example.yongchehui.ghclocktime.Module.GHWeatherInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yongcheHui on 16/2/19.
 */
public class GHWeatherManager {

    public static void registerWeatherCallBack(final Activity context, final GHWeatherCallBack callBack)
    {
        SharedPreferences settings = System.weatherSetting(context);
        String strValue = settings.getString("weatherInfo",null);
        GHWeatherInfo info = null;
        if (strValue != null) info = GHWeatherInfo.createInfoByJson(strValue);
        if (info != null)
        {
            long now = java.lang.System.currentTimeMillis();
            long dTime = now - info.getDate();
            if (dTime > 60 * 30 * 1000)
            {
                info = null;
            }
            else
            {
                callBack.receiveWeatherInfo(info);
                return;
            }
        }
        new GPSManager(context).getLocation(new GPSManagerListener() {
            @Override
            public void getCity(final String city) {
                if (city != null)
                {
                    RequestParams params = new RequestParams();
                    params.add("city",city.substring(0,city.length()-1));
                    new AsyncHttpClient().get(System.Weather_API, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                GHWeatherInfo info = GHWeatherManager.parserWeatherInfo(context,responseBody,city);
                                callBack.receiveWeatherInfo(info);
                            }
                            catch (Exception e)
                            {
                                Log.e("error",e.toString());
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        }
                    });
                }
            }
        });
    }

    public static GHWeatherInfo parserWeatherInfo(Context context, byte[] response,String city) throws Exception {
        GHWeatherInfo info = null;
        InputStream is = new ByteArrayInputStream(response);
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(is, "UTF-8");
        int eventType = xpp.getEventType();
        boolean forecastBegin = false;
        boolean firstForecastWeatherBegin = false;
        String text = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType) {
                // 判断当前事件是否为文档开始事件
                case XmlPullParser.START_DOCUMENT:
                    info = new GHWeatherInfo();
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("forecast")) {
                        forecastBegin = true;
                    }else if (xpp.getName().equals("weather")) {
                        if (forecastBegin) firstForecastWeatherBegin = true;
                        forecastBegin = false;
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = xpp.getText();
                    break;
                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("wendu")) {
                        info.setWendu(text);
                    } else if (xpp.getName().equals("fengli")) {
                        if (info.getFengli() == null)
                        {
                            info.setFengli(text);
                        }
                    }else if (xpp.getName().equals("high")) {
                        if (firstForecastWeatherBegin)
                        {
                            info.setMax(text);
                        }
                    } else if (xpp.getName().equals("low")) {
                        if (firstForecastWeatherBegin)
                        {
                            info.setMin(text);
                        }
                    }else if (xpp.getName().equals("weather"))
                    {
                        firstForecastWeatherBegin = false;
                    }
                    break;
            }
            // 进入下一个元素并触发相应事件
            eventType = xpp.next();
        }
        info.setCity(city);
        info.setDate(java.lang.System.currentTimeMillis());
        if (info != null)
        {
            SharedPreferences settings = System.weatherSetting(context);
            SharedPreferences.Editor dt = settings.edit();
            dt.putString("weatherInfo",info.jsonString());
            dt.commit();
        }
        return info;
    }
}
