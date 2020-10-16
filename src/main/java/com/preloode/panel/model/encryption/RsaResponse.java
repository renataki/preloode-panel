package com.preloode.panel.model.encryption;

import com.preloode.panel.model.global.BaseResponse;


public class RsaResponse extends BaseResponse {


    private String privateKey;

    private String publicKey;


    public RsaResponse() {


    }


    public RsaResponse(String response, boolean result, String privateKey, String publicKey) {

        super(response, result);
        this.privateKey = privateKey;
        this.publicKey = publicKey;

    }


    public String getPrivateKey() {

        return privateKey;

    }


    public void setPrivateKey(String privateKey) {

        this.privateKey = privateKey;

    }


    public String getPublicKey() {

        return publicKey;

    }


    public void setPublicKey(String publicKey) {

        this.publicKey = publicKey;

    }


}
