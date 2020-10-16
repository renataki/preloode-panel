package com.preloode.panel.configuration.layout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PaginationAttributeConfiguration {


    @Value("${pagination.attribute.button}")
    private String button;

    @Value("${pagination.attribute.form}")
    private String form;

    @Value("${pagination.attribute.input}")
    private String input;


    public String getButton() {

        return button;

    }


    public void setButton(String button) {

        this.button = button;

    }


    public String getForm() {

        return form;

    }


    public void setForm(String form) {

        this.form = form;

    }


    public String getInput() {

        return input;

    }


    public void setInput(String input) {

        this.input = input;

    }


}
