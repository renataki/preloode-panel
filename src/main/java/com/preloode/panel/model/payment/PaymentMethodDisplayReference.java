package com.preloode.panel.model.payment;

import org.springframework.data.mongodb.core.index.Indexed;


public class PaymentMethodDisplayReference {


    @Indexed
    private boolean display;

    @Indexed
    private String logo;

    @Indexed
    private String script;

    @Indexed
    private String url;


    public PaymentMethodDisplayReference() {


    }


    public PaymentMethodDisplayReference(boolean display, String logo, String script, String url) {

        this.display = display;
        this.logo = logo;
        this.script = script;
        this.url = url;

    }


    public boolean isDisplay() {

        return display;

    }


    public void setDisplay(boolean display) {

        this.display = display;

    }


    public String getLogo() {

        return logo;

    }


    public void setLogo(String logo) {

        this.logo = logo;

    }


    public String getScript() {

        return script;

    }


    public void setScript(String script) {

        this.script = script;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


}
