package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.model.global.BaseResponse;

import java.util.List;


public class CryptocurrencyBinanceResponse extends BaseResponse {


    private List<CryptocurrencyBinanceAveragePriceTicker> averagePriceTickerList;

    private List<CryptocurrencyBinanceCoinInformation> coinInformationList;

    private List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList;

    private CryptocurrencyBinanceNewOrder newOrder;

    private CryptocurrencyBinanceOrderBookTicker orderBookTicker;

    private List<CryptocurrencyBinanceOrderBookTicker> orderBookTickerList;

    private CryptocurrencyBinancePriceTicker priceTicker;

    private List<CryptocurrencyBinancePriceTicker> priceTickerList;

    private Object testConnection;

    private CryptocurrencyBinanceTradeOrder tradeOrder;

    private List<CryptocurrencyBinanceTradeOrder> tradeOrderList;


    public CryptocurrencyBinanceResponse() {


    }


    public CryptocurrencyBinanceResponse(String response, boolean result, List<CryptocurrencyBinanceAveragePriceTicker> averagePriceTickerList, List<CryptocurrencyBinanceCoinInformation> coinInformationList, List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList, CryptocurrencyBinanceNewOrder newOrder, CryptocurrencyBinanceOrderBookTicker orderBookTicker, List<CryptocurrencyBinanceOrderBookTicker> orderBookTickerList, CryptocurrencyBinancePriceTicker priceTicker, List<CryptocurrencyBinancePriceTicker> priceTickerList, Object testConnection, CryptocurrencyBinanceTradeOrder tradeOrder, List<CryptocurrencyBinanceTradeOrder> tradeOrderList) {

        super(response, result);
        this.averagePriceTickerList = averagePriceTickerList;
        this.coinInformationList = coinInformationList;
        this.exchangeInformationList = exchangeInformationList;
        this.newOrder = newOrder;
        this.orderBookTicker = orderBookTicker;
        this.orderBookTickerList = orderBookTickerList;
        this.priceTicker = priceTicker;
        this.priceTickerList = priceTickerList;
        this.testConnection = testConnection;
        this.tradeOrder = tradeOrder;
        this.tradeOrderList = tradeOrderList;

    }


    public List<CryptocurrencyBinanceAveragePriceTicker> getAveragePriceTickerList() {

        return averagePriceTickerList;

    }


    public void setAveragePriceTickerList(List<CryptocurrencyBinanceAveragePriceTicker> averagePriceTickerList) {

        this.averagePriceTickerList = averagePriceTickerList;

    }


    public List<CryptocurrencyBinanceCoinInformation> getCoinInformationList() {

        return coinInformationList;

    }


    public void setCoinInformationList(List<CryptocurrencyBinanceCoinInformation> coinInformationList) {

        this.coinInformationList = coinInformationList;

    }


    public List<CryptocurrencyBinanceExchangeInformation> getExchangeInformationList() {

        return exchangeInformationList;

    }


    public void setExchangeInformationList(List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList) {

        this.exchangeInformationList = exchangeInformationList;

    }


    public CryptocurrencyBinanceNewOrder getNewOrder() {

        return newOrder;

    }


    public void setNewOrder(CryptocurrencyBinanceNewOrder newOrder) {

        this.newOrder = newOrder;

    }


    public CryptocurrencyBinanceOrderBookTicker getOrderBookTicker() {

        return orderBookTicker;

    }


    public void setOrderBookTicker(CryptocurrencyBinanceOrderBookTicker orderBookTicker) {

        this.orderBookTicker = orderBookTicker;

    }


    public List<CryptocurrencyBinanceOrderBookTicker> getOrderBookTickerList() {

        return orderBookTickerList;

    }


    public void setOrderBookTickerList(List<CryptocurrencyBinanceOrderBookTicker> orderBookTickerList) {

        this.orderBookTickerList = orderBookTickerList;

    }


    public CryptocurrencyBinancePriceTicker getPriceTicker() {

        return priceTicker;

    }


    public void setPriceTicker(CryptocurrencyBinancePriceTicker priceTicker) {

        this.priceTicker = priceTicker;

    }


    public List<CryptocurrencyBinancePriceTicker> getPriceTickerList() {

        return priceTickerList;

    }


    public void setPriceTickerList(List<CryptocurrencyBinancePriceTicker> priceTickerList) {

        this.priceTickerList = priceTickerList;

    }


    public Object getTestConnection() {

        return testConnection;

    }


    public void setTestConnection(Object testConnection) {

        this.testConnection = testConnection;

    }


    public CryptocurrencyBinanceTradeOrder getTradeOrder() {

        return tradeOrder;

    }


    public void setTradeOrder(CryptocurrencyBinanceTradeOrder tradeOrder) {

        this.tradeOrder = tradeOrder;

    }


    public List<CryptocurrencyBinanceTradeOrder> getTradeOrderList() {

        return tradeOrderList;

    }


    public void setTradeOrderList(List<CryptocurrencyBinanceTradeOrder> tradeOrderList) {

        this.tradeOrderList = tradeOrderList;

    }


}
