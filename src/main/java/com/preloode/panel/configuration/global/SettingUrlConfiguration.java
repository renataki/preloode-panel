package com.preloode.panel.configuration.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingUrlConfiguration {


    @Value("${setting.url.audio}")
    private String audio;

    @Value("${setting.url.base}")
    private String base;

    @Value("${setting.url.css}")
    private String css;

    @Value("${setting.url.font}")
    private String font;

    @Value("${setting.url.image}")
    private String image;

    @Value("${setting.url.javascript}")
    private String javascript;

    @Value("${setting.url.panel}")
    private String panel;

    @Value("${setting.url.plugin}")
    private String plugin;

    @Value("${setting.url.website}")
    private String website;

    @Value("${setting.url.websocket}")
    private String websocket;

    @Value("${setting.url.video}")
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


    public String getPanel() {

        return panel;

    }


    public void setPanel(String panel) {

        this.panel = panel;

    }


    public String getPlugin() {

        return plugin;

    }


    public void setPlugin(String plugin) {

        this.plugin = plugin;

    }


    public String getWebsite() {

        return website;

    }


    public void setWebsite(String website) {

        this.website = website;

    }


    public String getWebsocket() {

        return websocket;

    }


    public void setWebsocket(String websocket) {

        this.websocket = websocket;

    }


    public String getVideo() {

        return video;

    }


    public void setVideo(String video) {

        this.video = video;

    }


}
