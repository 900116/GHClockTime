package com.example.yongchehui.ghclocktime.Module;

/**
 * Created by yongcheHui on 16/1/27.
 */
public enum GHUserClockType {
    ONCE(0),
    EVERYDAY(1),
    WORKDAY(2),
    WEEKEND(3),
    TYPEDEF(4);

    private int value = 0;
    private GHUserClockType(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static GHUserClockType valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return ONCE;
            case 1:
                return EVERYDAY;
            case 2:
                return WORKDAY;
            case 3:
                return WEEKEND;
            case 4:
                return TYPEDEF;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (value) {
            case 0:
                return "仅一次";
            case 1:
                return "每天";
            case 2:
                return "工作日";
            case 3:
                return "周末";
            case 4:
                return "自定义";
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
