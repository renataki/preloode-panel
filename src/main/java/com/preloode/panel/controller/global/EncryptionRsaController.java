package com.preloode.panel.controller.global;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.encryption.RsaResponse;
import com.preloode.panel.service.global.EncryptionRsaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/encryption/rsa")
public class EncryptionRsaController {


    private static final Logger logger = LoggerFactory.getLogger(EncryptionRsaController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private EncryptionRsaService encryptionRsaService;


    @ModelAttribute("global")
    public GlobalConfiguration global(HttpServletRequest request) {

        global.initializeSetting(request, "Encryption RSA");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        return "global/encryption-rsa";

    }


    @RequestMapping(value = "/generate-key-pair", method = RequestMethod.GET)
    public String generateKeyPair(Model model) {

        RsaResponse keyPair = encryptionRsaService.generateKeyPair();

        if(keyPair.isResult()) {

            model.addAttribute("publicKey", keyPair.getPublicKey());
            model.addAttribute("privateKey", keyPair.getPrivateKey());

        }

        logger.info("Encryption RSA page initialized");

        return "global/encryption-rsa-generate-key-pair";

    }


}
