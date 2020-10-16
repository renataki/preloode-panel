package com.preloode.panel.enumeration.global;

public enum CountryCode {


    Malaysia("+60"),
    Indonesia("+62"),
    Philippines("+63"),
    Thailand("+66"),
    Cambodia("+855"),
    Japan("+81"),
    China("+86"),
    Taiwan("+886"),
    Other("Other");


    private String value;


    private CountryCode(String value) {

        this.value = value;

    }


    public String toString() {

        return this.value;

    }


}
