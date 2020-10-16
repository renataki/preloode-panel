package com.preloode.panel.controller.user;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserLoginResponse;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/login")
public class LoginController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private UserService userService;

    private User account;


    @ModelAttribute("account")
    public User account(HttpServletRequest request, HttpServletResponse response) {

        userService.checkAccess(response);

        account = userService.initializeAccount(request);

        return account;

    }


    @ModelAttribute("global")
    public GlobalConfiguration global(HttpServletRequest request) {

        global.initializeSetting(request, "Login");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        String checkLogin = userService.checkLogin(request);

        if(checkLogin.isEmpty()) {

            logger.info("Login page initialized");

            return "user/login";

        }

        logger.info("Redirected to dashboard page");

        return "redirect:/";

    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String indexRedirect() {

        logger.info("Redirected to dashboard page");

        return "redirect:/";

    }


    @RequestMapping(value = "/check-username")
    @ResponseBody
    public UserLoginResponse checkUsername(@RequestBody User data) {

        return userService.checkUsername(data);

    }


    @RequestMapping(value = "/check-password")
    @ResponseBody
    public BaseResponse checkPassword(HttpServletRequest request, HttpServletResponse response, @RequestBody User data) {

        return userService.checkPassword(request, response, data);

    }


    @RequestMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {

        return userService.logout(request, response);

    }


}
