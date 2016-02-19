package com.example.yongchehui.ghclocktime.Module;

import com.example.yongchehui.ghclocktime.Utils.GHJsonHelper;

/**
 * Created by yongcheHui on 16/2/19.
 */
public class GHWeatherInfo {

    private String wendu;
    private String max;
    private String min;
    private String air;
    private String fengli;
    private String city;
    private long date;

    public String getWendu() {
        return wendu;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public String getAir() {
        return air;
    }

    public String getFengli() {
        return fengli;
    }


    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public void setMax(String max) {
        max = max.replace("高温 ","");
        this.max = max;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public void setMin(String min) {
        min = min.replace("低温 ","");
        this.min = min;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public String jsonString()
    {
        return GHJsonHelper.toJSON(this);
    }

    public static GHWeatherInfo createInfoByJson(String json)
    {
        GHWeatherInfo info = null;
        try {
            info = GHJsonHelper.parseObject(json,GHWeatherInfo.class);
        }
        catch (Exception e){}
        return info;
    }
}
