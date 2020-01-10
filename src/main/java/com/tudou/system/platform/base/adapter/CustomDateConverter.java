package com.tudou.system.platform.base.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换，json接收字符串转换为Date
 *
 * @author weihua
 * @create 2017-05-13 15:42
 */
public class CustomDateConverter implements Converter<String, Date> {
    private static final Logger logger = LoggerFactory.getLogger(CustomDateConverter.class);
    private String datePattern;

    @Override
    public Date convert(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("日期转换失败");
            return null;
        }
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
