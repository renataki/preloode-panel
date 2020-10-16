package com.preloode.panel.configuration.layout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PaginationConfiguration {


    private PaginationAttributeConfiguration attribute;

    private PaginationCssConfiguration css;

    @Value("${pagination.next}")
    private int next;

    @Value("${pagination.previous}")
    private int previous;

    @Value("${pagination.size}")
    private int size;

    @Value("${pagination.url}")
    private String url;


    public PaginationAttributeConfiguration getAttribute() {

        return attribute;

    }


    public void setAttribute(PaginationAttributeConfiguration attribute) {

        this.attribute = attribute;

    }


    public PaginationCssConfiguration getCss() {

        return css;

    }


    public void setCss(PaginationCssConfiguration css) {

        this.css = css;

    }


    public int getNext() {

        return next;

    }


    public void setNext(int next) {

        this.next = next;

    }


    public int getPrevious() {

        return previous;

    }


    public void setPrevious(int previous) {

        this.previous = previous;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


}
