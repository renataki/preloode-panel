package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


public class CryptocurrencyBinanceAveragePriceTicker extends Base {


    private String symbol;

    private BigDecimal priceChange;

    private BigDecimal priceChangePercent;

    private BigDecimal weightedAvgPrice;

    private BigDecimal prevClosePrice;

    private BigDecimal lastPrice;

    private BigDecimal lastQty;

    private BigDecimal bidPrice;

    private BigDecimal bidQty;

    private BigDecimal askPrice;

    private BigDecimal askQty;

    private BigDecimal openPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private BigDecimal volume;

    private BigDecimal quoteVolume;

    private Date openTime;

    private Date closeTime;

    private BigInteger firstId;

    private BigInteger lastId;

    private BigInteger count;


    public CryptocurrencyBinanceAveragePriceTicker() {


    }


    public CryptocurrencyBinanceAveragePriceTicker(String id, TimestampReference created, TimestampReference modified, String symbol, BigDecimal priceChange, BigDecimal priceChangePercent, BigDecimal weightedAvgPrice, BigDecimal prevClosePrice, BigDecimal lastPrice, BigDecimal lastQty, BigDecimal bidPrice, BigDecimal bidQty, BigDecimal askPrice, BigDecimal askQty, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal volume, BigDecimal quoteVolume, Date openTime, Date closeTime, BigInteger firstId, BigInteger lastId, BigInteger count) {

        super(id, created, modified);
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPrice;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.lastQty = lastQty;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.firstId = firstId;
        this.lastId = lastId;
        this.count = count;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BigDecimal getPriceChange() {

        return priceChange;

    }


    public void setPriceChange(BigDecimal priceChange) {

        this.priceChange = priceChange;

    }


    public BigDecimal getPriceChangePercent() {

        return priceChangePercent;

    }


    public void setPriceChangePercent(BigDecimal priceChangePercent) {

        this.priceChangePercent = priceChangePercent;

    }


    public BigDecimal getWeightedAvgPrice() {

        return weightedAvgPrice;

    }


    public void setWeightedAvgPrice(BigDecimal weightedAvgPrice) {

        this.weightedAvgPrice = weightedAvgPrice;

    }


    public BigDecimal getPrevClosePrice() {

        return prevClosePrice;

    }


    public void setPrevClosePrice(BigDecimal prevClosePrice) {

        this.prevClosePrice = prevClosePrice;

    }


    public BigDecimal getLastPrice() {

        return lastPrice;

    }


    public void setLastPrice(BigDecimal lastPrice) {

        this.lastPrice = lastPrice;

    }


    public BigDecimal getLastQty() {

        return lastQty;

    }


    public void setLastQty(BigDecimal lastQty) {

        this.lastQty = lastQty;

    }


    public BigDecimal getBidPrice() {

        return bidPrice;

    }


    public void setBidPrice(BigDecimal bidPrice) {

        this.bidPrice = bidPrice;

    }


    public BigDecimal getBidQty() {

        return bidQty;

    }


    public void setBidQty(BigDecimal bidQty) {

        this.bidQty = bidQty;

    }


    public BigDecimal getAskPrice() {

        return askPrice;

    }


    public void setAskPrice(BigDecimal askPrice) {

        this.askPrice = askPrice;

    }


    public BigDecimal getAskQty() {

        return askQty;

    }


    public void setAskQty(BigDecimal askQty) {

        this.askQty = askQty;

    }


    public BigDecimal getOpenPrice() {

        return openPrice;

    }


    public void setOpenPrice(BigDecimal openPrice) {

        this.openPrice = openPrice;

    }


    public BigDecimal getHighPrice() {

        return highPrice;

    }


    public void setHighPrice(BigDecimal highPrice) {

        this.highPrice = highPrice;

    }


    public BigDecimal getLowPrice() {

        return lowPrice;

    }


    public void setLowPrice(BigDecimal lowPrice) {

        this.lowPrice = lowPrice;

    }


    public BigDecimal getVolume() {

        return volume;

    }


    public void setVolume(BigDecimal volume) {

        this.volume = volume;

    }


    public BigDecimal getQuoteVolume() {

        return quoteVolume;

    }


    public void setQuoteVolume(BigDecimal quoteVolume) {

        this.quoteVolume = quoteVolume;

    }


    public Date getOpenTime() {

        return openTime;

    }


    public void setOpenTime(Date openTime) {

        this.openTime = openTime;

    }


    public Date getCloseTime() {

        return closeTime;

    }


    public void setCloseTime(Date closeTime) {

        this.closeTime = closeTime;

    }


    public BigInteger getFirstId() {

        return firstId;

    }


    public void setFirstId(BigInteger firstId) {

        this.firstId = firstId;

    }


    public BigInteger getLastId() {

        return lastId;

    }


    public void setLastId(BigInteger lastId) {

        this.lastId = lastId;

    }


    public BigInteger getCount() {

        return count;

    }


    public void setCount(BigInteger count) {

        this.count = count;

    }


}
