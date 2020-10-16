package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DateTimeConfiguration {


    @Value("${datetime.format}")
    private String format;

    @Value("${datetime.offset}")
    private String offset;

    @Value("${datetime.timezone}")
    private String timezone;


    public String getFormat() {

        return format;

    }


    public void setFormat(String format) {

        this.format = format;

    }


    public String getOffset() {

        return offset;

    }


    public void setOffset(String offset) {

        this.offset = offset;

    }


    public String getTimezone() {

        return timezone;

    }


    public void setTimezone(String timezone) {

        this.timezone = timezone;

    }


}
