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
    private static String ESP_FORMAT = "dd-MM-yyyy";

    public static String getDateFormatted(String sDate, int days) {
        return new SimpleDateFormat(ESP_FORMAT, Locale.ENGLISH).format(getDateFromString(sDate, days));
    }

    public static boolean willBeToday(String sDate, int days) {
        if (sDate == null) {
            return false;
        }
        return getDateFromString(sDate, days) == TODAY;
    }

    private static long getMillisecondsFromDate(String sDate) throws ParseException {
        return new SimpleDateFormat(ESP_FORMAT, Locale.ENGLISH).parse(sDate).getTime();
    }

    private static long getMillisecondsFromDays(int days) {
        return days * ONE_DAY_MILLISECONDS;
    }

    private static Date getDateFromString(String sDate, int days) {
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
