package com.preloode.panel.configuration.global;

import com.preloode.panel.configuration.api.ApiConfiguration;
import com.preloode.panel.configuration.data.CookieConfiguration;
import com.preloode.panel.configuration.data.DateTimeConfiguration;
import com.preloode.panel.configuration.data.EncryptionConfiguration;
import com.preloode.panel.configuration.data.MongodbConfiguration;
import com.preloode.panel.configuration.file.FileConfiguration;
import com.preloode.panel.configuration.layout.PaginationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.TimeZone;


@Configuration
public class GlobalConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(GlobalConfiguration.class);

    @Autowired
    private ApiConfiguration api;

    @Autowired
    private CookieConfiguration cookie;

    @Autowired
    private DateTimeConfiguration dateTime;

    @Autowired
    private EncryptionConfiguration encryption;

    @Autowired
    private FileConfiguration file;

    @Autowired
    private MongodbConfiguration mongodb;

    @Autowired
    private PaginationConfiguration pagination;

    @Autowired
    private SettingConfiguration setting;


    @PostConstruct
    private void initialize() {

        TimeZone timezone = TimeZone.getTimeZone(dateTime.getTimezone());
        timezone.setDefault(timezone);

    }


    public ApiConfiguration getApi() {

        return api;

    }


    public void setApi(ApiConfiguration api) {

        this.api = api;

    }


    public CookieConfiguration getCookie() {

        return cookie;

    }


    public void setCookie(CookieConfiguration cookie) {

        this.cookie = cookie;

    }


    public DateTimeConfiguration getDateTime() {

        return dateTime;

    }


    public void setDateTime(DateTimeConfiguration dateTime) {

        this.dateTime = dateTime;

    }


    public EncryptionConfiguration getEncryption() {

        return encryption;

    }


    public void setEncryption(EncryptionConfiguration encryption) {

        this.encryption = encryption;

    }


    public FileConfiguration getFile() {

        return file;

    }


    public void setFile(FileConfiguration file) {

        this.file = file;

    }


    public MongodbConfiguration getMongodb() {

        return mongodb;

    }


    public void setMongodb(MongodbConfiguration mongodb) {

        this.mongodb = mongodb;

    }


    public PaginationConfiguration getPagination() {

        return pagination;

    }


    public void setPagination(PaginationConfiguration pagination) {

        this.pagination = pagination;

    }


    public SettingConfiguration getSetting() {

        return setting;

    }


    public void setSetting(SettingConfiguration setting) {

        this.setting = setting;

    }


    public void initializeSetting(HttpServletRequest request, String name) {

        setting.setCss(new ArrayList<>() {
            {
                add(name.replaceAll(" ", "-").toLowerCase() + ".css");
            }
        });
        setting.setJavascript(new ArrayList<>() {
            {
                add(name.replaceAll(" ", "-").toLowerCase() + ".js");
            }
        });
        setting.getMeta().setTitle(name);

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {

            for(Cookie servletCookie : cookies) {

                if(servletCookie.getName().equals(cookie.getPrefix() + "mnmnu")) {

                    if(servletCookie.getValue().equals("opn")) {

                        setting.getLayout().setMenuTranslateX("0");
                        setting.getLayout().setIconTranslateX("0");

                    } else {

                        setting.getLayout().setMenuTranslateX("-100%");
                        setting.getLayout().setIconTranslateX("-250px");

                    }

                    break;

                }

            }

        }

        logger.info(name + " page setting configuration initialized");

    }


}
