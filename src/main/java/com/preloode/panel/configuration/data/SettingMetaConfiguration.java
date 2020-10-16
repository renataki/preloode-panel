package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingMetaConfiguration {


    @Value("${setting.meta.categories}")
    private String categories;

    @Value("${setting.meta.description}")
    private String description;

    @Autowired
    private SettingMetaGeoConfiguration geo;

    @Value("${setting.meta.keyword}")
    private String keyword;

    @Value("${setting.meta.language}")
    private String language;

    @Autowired
    private SettingMetaOgConfiguration og;

    @Value("${setting.meta.robots}")
    private String robots;

    @Value("${setting.meta.theme-color}")
    private String themeColor;

    @Value("${setting.meta.title}")
    private String title;


    public String getCategories() {

        return categories;

    }


    public void setCategories(String categories) {

        this.categories = categories;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public SettingMetaGeoConfiguration getGeo() {

        return geo;

    }


    public void setGeo(SettingMetaGeoConfiguration geo) {

        this.geo = geo;

    }


    public String getKeyword() {

        return keyword;

    }


    public void setKeyword(String keyword) {

        this.keyword = keyword;

    }


    public String getLanguage() {

        return language;

    }


    public void setLanguage(String language) {

        this.language = language;

    }


    public SettingMetaOgConfiguration getOg() {

        return og;

    }


    public void setOg(SettingMetaOgConfiguration og) {

        this.og = og;

    }


    public String getRobots() {

        return robots;

    }


    public void setRobots(String robots) {

        this.robots = robots;

    }


    public String getThemeColor() {

        return themeColor;

    }


    public void setThemeColor(String themeColor) {

        this.themeColor = themeColor;

    }


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }


}
