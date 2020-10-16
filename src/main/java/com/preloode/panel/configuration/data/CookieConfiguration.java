package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CookieConfiguration {


    @Value("${cookie.path}")
    private String path;

    @Value("${cookie.prefix}")
    private String prefix;


    public String getPath() {

        return path;

    }


    public void setPath(String path) {

        this.path = path;

    }


    public String getPrefix() {

        return prefix;

    }


    public void setPrefix(String prefix) {

        this.prefix = prefix;

    }


}
