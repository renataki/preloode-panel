package com.preloode.panel.configuration.global;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ServerConfiguration {


    /*@Bean
    public WebServerFactoryCustomizer customizer() {

        return container -> {

            if(container instanceof TomcatServletWebServerFactory) {

                TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
                tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));

            }

        };

    }


    @Bean
    public RemoteIpFilter remoteIpFilter() {

        return new RemoteIpFilter();

    }*/


}
