package com.ylsoft.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    // 日期转换方法
//    private static SimpleDateFormat sdf = new SimpleDateFormat();

    //日期转字符串
    public static String DateToString(Date date,String DateDome) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateDome);
        return sdf.format(date);

    }

    //字符串转日期
    public static Date StringToDate(String date,String DateDome) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateDome);
        return sdf.parse(date);
    }


}
