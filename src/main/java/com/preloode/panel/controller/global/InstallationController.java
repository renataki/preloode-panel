package com.preloode.panel.controller.global;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.global.InstallationService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/installation")
public class InstallationController {


    private static final Logger logger = LoggerFactory.getLogger(InstallationController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private InstallationService installationService;

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

        global.initializeSetting(request, "Installation");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        BaseResponse installation = installationService.checkInstallation();

        if(!installation.isResult()) {

            logger.info("Installation page initialized");

            return "global/installation";

        }

        global.getSetting().getMeta().setTitle("Page Not Found");

        logger.info("Error page not found initialized");

        return "global/page-not-found";

    }


    @RequestMapping(value = "/uninstallation", method = RequestMethod.GET)
    public String setting() {

        if(account.getUsername() != null) {

            if(account.getUsername().equals("renataki")) {

                logger.info("Uninstallation page initialized");

                return "global/uninstallation";

            }

        }

        global.getSetting().getMeta().setTitle("Page Not Found");

        logger.info("Error page not found initialized");

        return "global/page-not-found";

    }


    @RequestMapping(value = "/install")
    @ResponseBody
    public BaseResponse install() {

        BaseResponse result = new BaseResponse(
                "Failed to install application",
                false
        );

        BaseResponse installation = installationService.checkInstallation();

        if(!installation.isResult()) {

            result = installationService.install();

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/initialize-demo-data")
    @ResponseBody
    public BaseResponse initializeDemoData() {

        BaseResponse result = new BaseResponse(
                "Failed to initialize installation demo data",
                false
        );

        BaseResponse installation = installationService.checkInstallation();

        if(installation.isResult()) {

            result = installationService.initializeDemoData();

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/uninstall")
    @ResponseBody
    public BaseResponse uninstall(HttpServletRequest request, HttpServletResponse response) {

        BaseResponse result = new BaseResponse(
                "Failed to uninstall application",
                false
        );

        if(account.getUsername() != null) {

            if(account.getUsername().equals("renataki")) {

                result = installationService.uninstall(request, response);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


}
