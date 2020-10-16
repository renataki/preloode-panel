package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EncryptionConfiguration {


    @Value("${encryption.rsa.key.private.client}")
    private String clientRsaPrivateKey;

    @Value("${encryption.rsa.key.public.client}")
    private String clientRsaPublicKey;

    @Value("${encryption.rsa.key.private.server}")
    private String serverRsaPrivateKey;

    @Value("${encryption.rsa.key.public.server}")
    private String serverRsaPublicKey;


    public String getClientRsaPrivateKey() {

        return clientRsaPrivateKey;

    }


    public void setClientRsaPrivateKey(String clientRsaPrivateKey) {

        this.clientRsaPrivateKey = clientRsaPrivateKey;

    }


    public String getClientRsaPublicKey() {

        return clientRsaPublicKey;

    }


    public void setClientRsaPublicKey(String clientRsaPublicKey) {

        this.clientRsaPublicKey = clientRsaPublicKey;

    }


    public String getServerRsaPrivateKey() {

        return serverRsaPrivateKey;

    }


    public void setServerRsaPrivateKey(String serverRsaPrivateKey) {

        this.serverRsaPrivateKey = serverRsaPrivateKey;

    }


    public String getServerRsaPublicKey() {

        return serverRsaPublicKey;

    }


    public void setServerRsaPublicKey(String serverRsaPublicKey) {

        this.serverRsaPublicKey = serverRsaPublicKey;

    }


}
