package com.preloode.panel.controller.cryptocurrency.binance;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.WebSocketResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.cryptocurrency.binance.CryptocurrencyBinanceService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/cryptocurrency/binance")
public class CryptocurrencyBinanceController {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyBinanceController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CryptocurrencyBinanceService cryptocurrencyBinanceService;

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

        global.initializeSetting(request, "Cryptocurrency Binance");

        return global;

    }


    @MessageMapping("/cryptocurrency/binance/stream")
    @SendTo("/output-stream/cryptocurrency/binance/stream")
    public WebSocketResponse send(WebSocketResponse webSocketResponse) {

        return webSocketResponse;

    }


}
