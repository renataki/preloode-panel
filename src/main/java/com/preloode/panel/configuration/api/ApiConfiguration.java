package com.preloode.panel.configuration.api;

import com.preloode.panel.configuration.api.binance.ApiBinanceConfiguration;
import com.preloode.panel.configuration.api.bitfinex.ApiBitfinexConfiguration;
import com.preloode.panel.configuration.api.bittrex.ApiBittrexConfiguration;
import com.preloode.panel.configuration.api.kraken.ApiKrakenConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiConfiguration {


    @Autowired
    private ApiBinanceConfiguration binance;

    @Autowired
    private ApiBitfinexConfiguration bitfinex;

    @Autowired
    private ApiBittrexConfiguration bittrex;

    @Autowired
    private ApiKrakenConfiguration kraken;


    public ApiBinanceConfiguration getBinance() {

        return binance;

    }


    public void setBinance(ApiBinanceConfiguration binance) {

        this.binance = binance;

    }


    public ApiBitfinexConfiguration getBitfinex() {

        return bitfinex;

    }


    public void setBitfinex(ApiBitfinexConfiguration bitfinex) {

        this.bitfinex = bitfinex;

    }


    public ApiBittrexConfiguration getBittrex() {

        return bittrex;

    }


    public void setBittrex(ApiBittrexConfiguration bittrex) {

        this.bittrex = bittrex;

    }


    public ApiKrakenConfiguration getKraken() {

        return kraken;

    }


    public void setKraken(ApiKrakenConfiguration kraken) {

        this.kraken = kraken;

    }


}
