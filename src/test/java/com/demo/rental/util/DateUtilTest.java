package com.demo.rental.util;

import com.demo.rental.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void parseNullString() {

        String dateString = null;

        Date actual = DateUtil.parseDate(dateString, DateUtil.DEFAULT_DATE_STYLE);
        Assertions.assertNull(actual);

    }

    @Test
    void parseNormalDate() {

        String dateString = "2020-01-01";

        Date date = DateUtil.parseDate(dateString, DateUtil.DEFAULT_DATE_STYLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Assertions.assertEquals(2020, calendar.get(Calendar.YEAR));
        Assertions.assertEquals(1, calendar.get(Calendar.MONTH) + 1);
        Assertions.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void parseNormalTime() {

        String dateString = "2020-01-01 12:00:00";

        Date date = DateUtil.parseDate(dateString, DateUtil.DEFAULT_TIME_STYLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Assertions.assertEquals(2020, calendar.get(Calendar.YEAR));
        Assertions.assertEquals(1, calendar.get(Calendar.MONTH) + 1);
        Assertions.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(12, calendar.get(Calendar.HOUR_OF_DAY));
        Assertions.assertEquals(0, calendar.get(Calendar.MINUTE));
        Assertions.assertEquals(0, calendar.get(Calendar.SECOND));
    }

    @Test
    void parseException() {

        String dateString = "20200101";
        Assertions.assertThrows(BusinessException.class, () -> DateUtil.parseDate(dateString, DateUtil.DEFAULT_DATE_STYLE));
    }

    @Test
    void formatNullDate() {

        Date date = null;

        Assertions.assertNull(DateUtil.formatDate(date, DateUtil.DEFAULT_DATE_STYLE));
    }

    @Test
    void formatDate() {

        String dateString = "2020-01-01";
        Date date = DateUtil.parseDate(dateString, DateUtil.DEFAULT_DATE_STYLE);

        String actual = DateUtil.formatDate(date, DateUtil.DEFAULT_DATE_STYLE);

        Assertions.assertEquals(dateString, actual);
    }

    @Test
    void formatTime() {

        String dateString = "2020-01-01 12:00:00";
        Date date = DateUtil.parseDate(dateString, DateUtil.DEFAULT_TIME_STYLE);

        String actual = DateUtil.formatDate(date, DateUtil.DEFAULT_TIME_STYLE);

        Assertions.assertEquals(dateString, actual);
    }
}