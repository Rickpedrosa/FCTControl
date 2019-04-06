package com.example.fctcontrol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeCustomUtils {

    @SuppressWarnings("WeakerAccess")
    public static Date TODAY = new Date();
    @SuppressWarnings("FieldCanBeLocal")
    private static long ONE_DAY_MILLISECONDS = 86400000;
    @SuppressWarnings("FieldCanBeLocal")
    private static long ONE_HOUR_MILLISECONDS = 3600000;
    private static String ESP_FORMAT = "dd-MM-yyyy";

    public static String getStringDateFormatted(String sDate, int days) {
        return new SimpleDateFormat(ESP_FORMAT, Locale.ENGLISH).format(getDateFromStringPlusDays(sDate, days));
    }

    public static String obtainStringFromDate(Date date) {
        return new SimpleDateFormat(ESP_FORMAT, Locale.ENGLISH).format(date);
    }

    public static String getTime(Date date, int plusHours) {
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(
                getDateFromMilliseconds(date.getTime() + getMilliSecondsFromHours(plusHours)));
    }

    public static boolean willBeToday(String sDate, int days) {
        if (sDate == null) {
            return false;
        }
        return getDateFromStringPlusDays(sDate, days) == TODAY;
    }

    public static int[] getDayMonthYear(String sDate) {
        int[] values = new int[3];
        values[0] = Integer.parseInt(sDate.substring(0, sDate.indexOf("-"))); //day
        //month -1 because calendars month starts at index 0
        values[1] = Integer.parseInt(sDate.substring(sDate.indexOf("-") + 1, sDate.lastIndexOf("-"))) - 1; //month
        values[2] = Integer.parseInt(sDate.substring(sDate.lastIndexOf("-") + 1)); //year

        return values;
    }

    public static int[] getHoursMinutes(String time) {
        int[] values = new int[2];
        values[0] = Integer.parseInt(time.split(":")[0]);
        values[1] = Integer.parseInt(time.split(":")[1]);
        return values;
    }

    private static long getMillisecondsFromDate(String sDate) throws ParseException {
        return new SimpleDateFormat(ESP_FORMAT, Locale.ENGLISH).parse(sDate).getTime();
    }

    private static Date getDateFromMilliseconds(long milliseconds) {
        return new Date(milliseconds);
    }

    private static long getMillisecondsFromDays(int days) {
        return days * ONE_DAY_MILLISECONDS;
    }

    private static long getMilliSecondsFromHours(int hours) {
        return hours * ONE_HOUR_MILLISECONDS;
    }

    private static Date getDateFromStringPlusDays(String sDate, int days) {
        try {
            return new Date(getMillisecondsFromDate(sDate) + getMillisecondsFromDays(days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TimeCustomUtils() {
    }
}
