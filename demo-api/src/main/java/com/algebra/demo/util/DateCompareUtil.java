package com.algebra.demo.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author al
 * @date 2020/5/18 16:32
 * @description
 */
public class DateCompareUtil {

    /**
     * 计算两个日期的天数差值
     * @param date1 日期-1
     * @param date2 日期-2
     * @return 天数
     */
    public static int compareDays(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if(year1 > year2) {
            int tempYear = year1;
            int tempDay = day1;
            day1 = day2;
            day2 = tempDay;
            year1 = year2;
            year2 = tempYear;
        }
        if (year1 == year2) {
            return (day2 - day1);
        } else {
            int dayCount = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    dayCount += 366;
                }else {
                    dayCount += 365;
                }
            }
            return dayCount+(day2-day1);
        }
    }

    /**
     * 比较日期：方法-2
     * @param date1 日期1
     * @param date2 日期2
     * @return 天数
     */
    public static int differentDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new RuntimeException("date is not allow empty!");
        }
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return (int) localDate1.until(localDate2, ChronoUnit.DAYS);
    }

    private static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }


    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int i = compareDays(sdf.parse("2020-1-1"), sdf.parse("2019-12-25"));

        int i1 = differentDays(sdf.parse("2020-1-1"), sdf.parse("2019-12-25"));

        System.out.println(i);

        System.out.println(i1);

    }

}
