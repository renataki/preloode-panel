package com.preloode.panel.configuration.layout;

import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingLayoutConfiguration {


    private String iconTranslateX;

    private String menuTranslateX;


    public String getIconTranslateX() {

        return iconTranslateX;

    }


    public void setIconTranslateX(String iconTranslateX) {

        this.iconTranslateX = iconTranslateX;

    }


    public String getMenuTranslateX() {

        return menuTranslateX;

    }


    public void setMenuTranslateX(String menuTranslateX) {

        this.menuTranslateX = menuTranslateX;

    }


}
