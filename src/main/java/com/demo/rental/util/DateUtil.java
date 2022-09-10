package com.demo.rental.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DEFAULT_STYLE = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(String dateString) throws ParseException {

        if (dateString == null) {
            return null;
        } else {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_STYLE);
            return simpleDateFormat.parse(dateString);
        }
    }

    public static String formatDate(Date date) {

        if (date == null) {
            return null;
        } else {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_STYLE);
            return simpleDateFormat.format(date);
        }

    }

}
