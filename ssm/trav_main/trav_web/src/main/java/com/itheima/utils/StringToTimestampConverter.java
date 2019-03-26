package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义的类型转换器
 */
public class StringToTimestampConverter implements Converter<String,Timestamp> {
    @Override
    public Timestamp convert(String source) {
        try {
            if (StringUtils.isEmpty(source)){
                return null;
            }
            //String--->Date   字符串转日期
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(source);
            //Date------>Timestamp
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}













