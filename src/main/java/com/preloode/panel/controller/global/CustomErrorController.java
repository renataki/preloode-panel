package com.preloode.panel.controller.global;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController {


    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @Autowired
    private GlobalConfiguration global;


    @Override
    public String getErrorPath() {

        return "/error";

    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        String result = null;

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {

            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {

                global.initializeSetting(request, "Page Not Found");

                result = "global/page-not-found";

            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                global.initializeSetting(request, "Internal Server Error");

                result = "global/internal-server-error";

            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {

                result = "global/forbidden";

            }

        }

        model.addAttribute("global", global);

        logger.info(global.getSetting().getMeta().getTitle());

        return result;

    }


}
