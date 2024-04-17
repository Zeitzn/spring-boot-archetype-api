package com.archetype.api.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {
    private DateUtil() {}
    public static Date format(String date, String format) {
        if(StringUtil.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String format(Date date, String format) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
