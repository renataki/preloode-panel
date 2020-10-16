package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceInterval;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceRateLimitType;
import org.springframework.data.mongodb.core.index.Indexed;


public class CryptocurrencyBinanceRateLimiterReference {


    @Indexed
    private BinanceRateLimitType rateLimitType;

    @Indexed
    private BinanceInterval interval;

    @Indexed
    private int intervalNum;

    @Indexed
    private int limit;


    public CryptocurrencyBinanceRateLimiterReference() {


    }


    public CryptocurrencyBinanceRateLimiterReference(BinanceRateLimitType rateLimitType, BinanceInterval interval, int intervalNum, int limit) {

        this.rateLimitType = rateLimitType;
        this.interval = interval;
        this.intervalNum = intervalNum;
        this.limit = limit;

    }


    public BinanceRateLimitType getRateLimitType() {

        return rateLimitType;

    }


    public void setRateLimitType(BinanceRateLimitType rateLimitType) {

        this.rateLimitType = rateLimitType;

    }


    public BinanceInterval getInterval() {

        return interval;

    }


    public void setInterval(BinanceInterval interval) {

        this.interval = interval;

    }


    public int getIntervalNum() {

        return intervalNum;

    }


    public void setIntervalNum(int intervalNum) {

        this.intervalNum = intervalNum;

    }


    public int getLimit() {

        return limit;

    }


    public void setLimit(int limit) {

        this.limit = limit;

    }


}
