package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinancePriceTicker;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexMarketTicker;
import com.preloode.panel.model.global.BaseResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class CryptocurrencyArbitrageResponse extends BaseResponse {


    private List<CryptocurrencyBinancePriceTicker> cryptocurrencyBinancePriceTickerList;

    private List<List<Object>> cryptocurrencyBitfinexTickerList;

    private List<CryptocurrencyBittrexMarketTicker> cryptocurrencyBittrexMarketTickerList;

    private List<String> cryptocurrencyCurrencyList;

    private List<CryptocurrencyExchanger> cryptocurrencyExchangerList;

    private Map<String, Object> cryptocurrencyKrakenTickerInformationList;

    private BigDecimal cryptocurrencyLotSize;

    private List<String> cryptocurrencySymbolList;


    public CryptocurrencyArbitrageResponse() {


    }


    public CryptocurrencyArbitrageResponse(String response, boolean result, List<CryptocurrencyBinancePriceTicker> cryptocurrencyBinancePriceTickerList, List<List<Object>> cryptocurrencyBitfinexTickerList, List<CryptocurrencyBittrexMarketTicker> cryptocurrencyBittrexMarketTickerList, List<String> cryptocurrencyCurrencyList, List<CryptocurrencyExchanger> cryptocurrencyExchangerList, Map<String, Object> cryptocurrencyKrakenTickerInformationList, BigDecimal cryptocurrencyLotSize, List<String> cryptocurrencySymbolList) {

        super(response, result);
        this.cryptocurrencyBinancePriceTickerList = cryptocurrencyBinancePriceTickerList;
        this.cryptocurrencyBitfinexTickerList = cryptocurrencyBitfinexTickerList;
        this.cryptocurrencyBittrexMarketTickerList = cryptocurrencyBittrexMarketTickerList;
        this.cryptocurrencyCurrencyList = cryptocurrencyCurrencyList;
        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;
        this.cryptocurrencyKrakenTickerInformationList = cryptocurrencyKrakenTickerInformationList;
        this.cryptocurrencyLotSize = cryptocurrencyLotSize;
        this.cryptocurrencySymbolList = cryptocurrencySymbolList;

    }


    public List<CryptocurrencyBinancePriceTicker> getCryptocurrencyBinancePriceTickerList() {

        return cryptocurrencyBinancePriceTickerList;

    }


    public void setCryptocurrencyBinancePriceTickerList(List<CryptocurrencyBinancePriceTicker> cryptocurrencyBinancePriceTickerList) {

        this.cryptocurrencyBinancePriceTickerList = cryptocurrencyBinancePriceTickerList;

    }


    public List<List<Object>> getCryptocurrencyBitfinexTickerList() {

        return cryptocurrencyBitfinexTickerList;

    }


    public void setCryptocurrencyBitfinexTickerList(List<List<Object>> cryptocurrencyBitfinexTickerList) {

        this.cryptocurrencyBitfinexTickerList = cryptocurrencyBitfinexTickerList;

    }


    public List<CryptocurrencyBittrexMarketTicker> getCryptocurrencyBittrexMarketTickerList() {

        return cryptocurrencyBittrexMarketTickerList;

    }


    public void setCryptocurrencyBittrexMarketTickerList(List<CryptocurrencyBittrexMarketTicker> cryptocurrencyBittrexMarketTickerList) {

        this.cryptocurrencyBittrexMarketTickerList = cryptocurrencyBittrexMarketTickerList;

    }


    public List<String> getCryptocurrencyCurrencyList() {

        return cryptocurrencyCurrencyList;

    }


    public void setCryptocurrencyCurrencyList(List<String> cryptocurrencyCurrencyList) {

        this.cryptocurrencyCurrencyList = cryptocurrencyCurrencyList;

    }


    public List<CryptocurrencyExchanger> getCryptocurrencyExchangerList() {

        return cryptocurrencyExchangerList;

    }


    public void setCryptocurrencyExchangerList(List<CryptocurrencyExchanger> cryptocurrencyExchangerList) {

        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;

    }


    public Map<String, Object> getCryptocurrencyKrakenTickerInformationList() {

        return cryptocurrencyKrakenTickerInformationList;

    }


    public void setCryptocurrencyKrakenTickerInformationList(Map<String, Object> cryptocurrencyKrakenTickerInformationList) {

        this.cryptocurrencyKrakenTickerInformationList = cryptocurrencyKrakenTickerInformationList;

    }


    public BigDecimal getCryptocurrencyLotSize() {

        return cryptocurrencyLotSize;

    }


    public void setCryptocurrencyLotSize(BigDecimal cryptocurrencyLotSize) {

        this.cryptocurrencyLotSize = cryptocurrencyLotSize;

    }


    public List<String> getCryptocurrencySymbolList() {

        return cryptocurrencySymbolList;

    }


    public void setCryptocurrencySymbolList(List<String> cryptocurrencySymbolList) {

        this.cryptocurrencySymbolList = cryptocurrencySymbolList;

    }


}
