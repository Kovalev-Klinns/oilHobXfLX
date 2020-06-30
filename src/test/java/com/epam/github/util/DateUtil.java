package com.epam.github.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
