package com.preloode.panel.configuration.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingPathConfiguration {


    @Value("${setting.path.audio}")
    private String audio;

    @Value("${setting.path.base}")
    private String base;

    @Value("${setting.path.css}")
    private String css;

    @Value("${setting.path.font}")
    private String font;

    @Value("${setting.path.image}")
    private String image;

    @Value("${setting.path.javascript}")
    private String javascript;

    @Value("${setting.path.plugin}")
    private String plugin;

    @Value("${setting.path.video}")
    private String video;


    public String getAudio() {

        return audio;

    }


    public void setAudio(String audio) {

        this.audio = audio;

    }


    public String getBase() {

        return base;

    }


    public void setBase(String base) {

        this.base = base;

    }


    public String getCss() {

        return css;

    }


    public void setCss(String css) {

        this.css = css;

    }


    public String getFont() {

        return font;

    }


    public void setFont(String font) {

        this.font = font;

    }


    public String getImage() {

        return image;

    }


    public void setImage(String image) {

        this.image = image;

    }


    public String getJavascript() {

        return javascript;

    }


    public void setJavascript(String javascript) {

        this.javascript = javascript;

    }


    public String getPlugin() {

        return plugin;

    }


    public void setPlugin(String plugin) {

        this.plugin = plugin;

    }


    public String getVideo() {

        return video;

    }


    public void setVideo(String video) {

        this.video = video;

    }


}
