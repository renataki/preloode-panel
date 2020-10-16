package com.preloode.panel.controller.cryptocurrency;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyFilter;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.cryptocurrency.CryptocurrencyService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/cryptocurrency")
public class CryptocurrencyController {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

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

        global.initializeSetting(request, "Cryptocurrency");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrency());

            if(checkPrivilege.isResult()) {

                logger.info("Cryptocurrency page initialized");

                return "cryptocurrency/cryptocurrency";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-data")
    @ResponseBody
    public BaseResponse initializeData() {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to initialize cryptocurrency data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrency());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyService.initializeData();

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/initialize-information")
    @ResponseBody
    public BaseResponse initializeInformation() {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to initialize cryptocurrency information");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrency());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyService.initializeInformation();

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/load-list")
    @ResponseBody
    public CryptocurrencyResponse loadList(@RequestBody CryptocurrencyFilter filter) {

        CryptocurrencyResponse result = new CryptocurrencyResponse() {
            {
                setResponse("Failed to load cryptocurrency list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrency());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyService.loadList(filter);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


}
