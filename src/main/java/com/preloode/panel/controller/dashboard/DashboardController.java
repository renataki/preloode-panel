package com.preloode.panel.controller.dashboard;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.transaction.TransactionService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "")
public class DashboardController {


    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private TransactionService transactionService;

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

        global.initializeSetting(request, "Index");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            model.addAttribute("summary", transactionService.initializeSummary().getTransactionSummary());

            logger.info("Dashboard page initialized");

            return "dashboard/dashboard";

        }

        return "redirect:/login/";

    }


}
