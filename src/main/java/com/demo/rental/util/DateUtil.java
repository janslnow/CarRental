package com.demo.rental.util;

import com.demo.rental.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DEFAULT_TIME_STYLE = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_STYLE = "yyyy-MM-dd";

    public static Date parseDate(String dateString, String style) {

        if (dateString == null) {
            return null;
        } else {

            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new BusinessException("time style error");
            }
        }
    }

    public static String formatDate(Date date, String style) {

        if (date == null) {
            return null;
        } else {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
            return simpleDateFormat.format(date);
        }

    }

}
