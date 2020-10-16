package com.preloode.panel.controller.cryptocurrency;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyArbitrageResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.cryptocurrency.CryptocurrencyArbitrageService;
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
@RequestMapping(value = "/cryptocurrency/arbitrage")
public class CryptocurrencyArbitrageController {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyArbitrageController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CryptocurrencyArbitrageService cryptocurrencyArbitrageService;

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

        global.initializeSetting(request, "Cryptocurrency Arbitrage");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyArbitrage());

            if(checkPrivilege.isResult()) {

                logger.info("Cryptocurrency arbitrage page initialized");

                return "cryptocurrency/arbitrage";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-data")
    @ResponseBody
    public CryptocurrencyArbitrageResponse initializeData() {

        CryptocurrencyArbitrageResponse result = new CryptocurrencyArbitrageResponse() {
            {
                setResponse("Failed to initialize cryptocurrency arbitrage data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyArbitrageService.initializeData();

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/load-coin-information")
    @ResponseBody
    public CryptocurrencyArbitrageResponse loadCoinInformation() {

        CryptocurrencyArbitrageResponse result = new CryptocurrencyArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency arbitrage coin information");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyArbitrageService.loadCoinInformation();

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
