package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingMetaOgConfiguration {


    @Value("${setting.meta.og.description}")
    private String description;

    @Value("${setting.meta.og.locale}")
    private String locale;

    @Value("${setting.meta.og.site-name}")
    private String siteName;

    @Value("${setting.meta.og.title}")
    private String title;

    @Value("${setting.meta.og.type}")
    private String type;

    @Value("${setting.meta.og.url}")
    private String url;


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public String getLocale() {

        return locale;

    }


    public void setLocale(String locale) {

        this.locale = locale;

    }


    public String getSiteName() {

        return siteName;

    }


    public void setSiteName(String siteName) {

        this.siteName = siteName;

    }


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }


    public String getType() {

        return type;

    }


    public void setType(String type) {

        this.type = type;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


}
