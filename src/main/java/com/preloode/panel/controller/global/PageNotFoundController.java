package com.preloode.panel.controller.global;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/page-not-found")
public class PageNotFoundController {


    private static final Logger logger = LoggerFactory.getLogger(PageNotFoundController.class);

    @Autowired
    private GlobalConfiguration global;


    @ModelAttribute("global")
    public GlobalConfiguration global(HttpServletRequest request) {

        global.initializeSetting(request, "Page Not Found");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        logger.info("Error page not found initialized");

        return "global/page-not-found";

    }


}
