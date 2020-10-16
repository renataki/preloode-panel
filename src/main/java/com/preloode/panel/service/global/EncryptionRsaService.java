package com.preloode.panel.service.global;

import com.preloode.panel.component.EncryptionComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.encryption.RsaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EncryptionRsaService {


    private static final Logger logger = LoggerFactory.getLogger(EncryptionRsaService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private EncryptionComponent encryption;


    public RsaResponse generateKeyPair() {

        return encryption.generateRsaKeyPair(4096);

    }


}
