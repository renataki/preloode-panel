package com.preloode.panel.model.cryptocurrency.binance;

import java.util.Date;
import java.util.List;


public class CryptocurrencyBinanceExchangeInformationResponse {


    private String timezone;

    private Date serverTime;

    private List<CryptocurrencyBinanceRateLimiterReference> rateLimits;

    private List<CryptocurrencyBinanceFilterReference> exchangeFilters;

    private List<CryptocurrencyBinanceExchangeInformation> symbols;


    public CryptocurrencyBinanceExchangeInformationResponse() {


    }


    public CryptocurrencyBinanceExchangeInformationResponse(String timezone, Date serverTime, List<CryptocurrencyBinanceRateLimiterReference> rateLimits, List<CryptocurrencyBinanceFilterReference> exchangeFilters, List<CryptocurrencyBinanceExchangeInformation> symbols) {

        this.timezone = timezone;
        this.serverTime = serverTime;
        this.rateLimits = rateLimits;
        this.exchangeFilters = exchangeFilters;
        this.symbols = symbols;

    }


    public String getTimezone() {

        return timezone;

    }


    public void setTimezone(String timezone) {

        this.timezone = timezone;

    }


    public Date getServerTime() {

        return serverTime;

    }


    public void setServerTime(Date serverTime) {

        this.serverTime = serverTime;

    }


    public List<CryptocurrencyBinanceRateLimiterReference> getRateLimits() {

        return rateLimits;

    }


    public void setRateLimits(List<CryptocurrencyBinanceRateLimiterReference> rateLimits) {

        this.rateLimits = rateLimits;

    }


    public List<CryptocurrencyBinanceFilterReference> getExchangeFilters() {

        return exchangeFilters;

    }


    public void setExchangeFilters(List<CryptocurrencyBinanceFilterReference> exchangeFilters) {

        this.exchangeFilters = exchangeFilters;

    }


    public List<CryptocurrencyBinanceExchangeInformation> getSymbols() {

        return symbols;

    }


    public void setSymbols(List<CryptocurrencyBinanceExchangeInformation> symbols) {

        this.symbols = symbols;

    }


}
