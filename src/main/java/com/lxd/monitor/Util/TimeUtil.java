package com.lxd.monitor.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getFormatDate(long time){
        return dateFormat.format(new Date(time));
    }

    public static void main(String[] args){
        System.out.println(getFormatDate(System.currentTimeMillis()));
    }
}

