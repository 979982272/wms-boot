package com.tudou.system.platform.base.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转换
 *
 * @author weihua
 * @create 2017-05-13 16:05
 */
public class CustomDateFormatter implements Formatter<Date> {
    private static final Logger logger = LoggerFactory.getLogger(CustomDateFormatter.class);
    private String datePattern="yyyy-MM-dd";
    private SimpleDateFormat dateFormat;

    public CustomDateFormatter(String datePattern) {
        this.datePattern = datePattern;
        this.dateFormat = new SimpleDateFormat(datePattern);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        try {
            return dateFormat.parse(text);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("日期转换失败");
            return null;
        }
    }

    @Override
    public String print(Date object, Locale locale) {
        return dateFormat.format(object);
    }
}
