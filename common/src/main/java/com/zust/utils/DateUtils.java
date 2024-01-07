package com.zust.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Andy
 * @date 2023-8-11 011 13:15
 * 日期工具类
 */
@Slf4j
public class DateUtils {
    private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter YMDHMS_FORMATTER = DateTimeFormatter.ofPattern(YMDHMS);

    private static final String YMD = "yyyy-MM-dd";

    private static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern(YMD);


    /**
     * 将日期格式化为默认格式
     *
     * @param date 日期
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String DateToString(Date date) {
        try {
            SimpleDateFormat f = new SimpleDateFormat(YMDHMS);
            return f.format(date);
        } catch (Exception e) {
            log.info("日期转换失败");
        }
        return null;
    }

    /**
     * 将String转换为Date，String格式为yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd
     */
    public static Date StringToDate(String dateString) {
        try {
            SimpleDateFormat f = new SimpleDateFormat(YMDHMS);
            return f.parse(dateString);
        } catch (Exception e) {
            try {
                SimpleDateFormat f = new SimpleDateFormat(YMD);
                return f.parse(dateString);
            } catch (ParseException ex) {
                log.info("日期转换失败");
            }
        }
        return null;

    }


    /**
     * 清除时间信息
     */
    private static void clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


}
