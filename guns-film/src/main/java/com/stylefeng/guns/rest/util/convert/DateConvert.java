package com.stylefeng.guns.rest.util.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 11:42
 */
public class DateConvert implements Converter<Date,String> {

    @Override
    public String convert(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
