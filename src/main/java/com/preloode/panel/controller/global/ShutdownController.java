package com.preloode.panel.controller.global;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.global.ShutdownService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/shutdown")
public class ShutdownController {


    private static final Logger logger = LoggerFactory.getLogger(ShutdownController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private ShutdownService shutdownService;

    @Autowired
    private UserService userService;

    private User account;


    @ModelAttribute("account")
    public User account(HttpServletRequest request) {

        account = userService.initializeAccount(request);

        return account;

    }


    @ModelAttribute("global")
    public GlobalConfiguration global(HttpServletRequest request) {

        global.initializeSetting(request, "Shutdown");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        if(account.getUsername() != null) {

            if(account.getUsername().equals("renataki")) {

                logger.info("Shutdown page initialized");

                return "global/shutdown";

            }

        }

        global.getSetting().getMeta().setTitle("Page Not Found");

        logger.info("Error page not found initialized");

        return "global/page-not-found";

    }


}
