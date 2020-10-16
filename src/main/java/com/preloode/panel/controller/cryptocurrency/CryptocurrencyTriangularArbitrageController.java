package com.preloode.panel.controller.cryptocurrency;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.cryptocurrency.Exchanger;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyTriangularArbitrage;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyTriangularArbitrageFilter;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyTriangularArbitrageResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.cryptocurrency.CryptocurrencyTriangularArbitrageService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/cryptocurrency/triangular-arbitrage")
public class CryptocurrencyTriangularArbitrageController {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyTriangularArbitrageController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CryptocurrencyTriangularArbitrageService cryptocurrencyTriangularArbitrageService;

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

        global.initializeSetting(request, "Cryptocurrency Triangular Arbitrage");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                logger.info("Cryptocurrency triangular arbitrage page initialized");

                return "cryptocurrency/triangular-arbitrage";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/load-coin-information")
    @ResponseBody
    public CryptocurrencyTriangularArbitrageResponse loadCoinInformation(Exchanger exchanger) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency triangular arbitrage coin information");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.loadCoinInformation(exchanger);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/initialize-data")
    @ResponseBody
    public CryptocurrencyTriangularArbitrageResponse initializeData() {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to initialize cryptocurrency triangular arbitrage data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.initializeData();

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/load-data-stream")
    @ResponseBody
    public BaseResponse loadDataStream(@RequestBody CryptocurrencyTriangularArbitrageFilter filter) {

        BaseResponse result = new BaseResponse(
                "Failed to load cryptocurrency triangular arbitrage data stream",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.loadDataStream(filter);

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
    public CryptocurrencyTriangularArbitrageResponse loadList(@RequestBody CryptocurrencyTriangularArbitrageFilter filter) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency triangular arbitrage list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.loadList(filter);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/trade-binance")
    @ResponseBody
    public BaseResponse tradeBinance(@RequestBody CryptocurrencyTriangularArbitrage triangularArbitrage) {

        BaseResponse result = new BaseResponse(
                "Failed to trade cryptocurrency triangular arbitrage binance",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.tradeBinance(triangularArbitrage);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/unsubscribe-stream")
    @ResponseBody
    public BaseResponse unsubscribeStream(@RequestBody CryptocurrencyTriangularArbitrageFilter filter) {

        BaseResponse result = new BaseResponse(
                "Failed to unsubscribe cryptocurrency triangular arbitrage stream",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCryptocurrencyTriangularArbitrage());

            if(checkPrivilege.isResult()) {

                result = cryptocurrencyTriangularArbitrageService.unsubscribeStream(filter);

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
