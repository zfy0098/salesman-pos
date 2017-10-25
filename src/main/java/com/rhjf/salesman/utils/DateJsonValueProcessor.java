package com.rhjf.salesman.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String datePattern = "yyyy-MM-dd";

    public DateJsonValueProcessor() {
        super();
    }

    public DateJsonValueProcessor(String format) {
        super();
        this.datePattern = format;
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        try {
            if(value instanceof Date){
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);
                return sdf.format((Date) value);
            }
            return "";
        } catch (Exception e) {
            return "";
        }

    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

}
