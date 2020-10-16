package com.preloode.panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@ServletComponentScan
public class PanelApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {

        SpringApplication.run(PanelApplication.class, args);

    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {

        return springApplicationBuilder.sources(PanelApplication.class);

    }


}
