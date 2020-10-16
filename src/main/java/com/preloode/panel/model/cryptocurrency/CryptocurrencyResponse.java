package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceAveragePriceTicker;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformation;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;


public class CryptocurrencyResponse extends BaseResponse {


    private List<CryptocurrencyAsset> cryptocurrencyAssetList;

    private List<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerList;

    private List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList;

    private List<CryptocurrencyExchanger> cryptocurrencyExchangerList;


    public CryptocurrencyResponse() {


    }


    public CryptocurrencyResponse(String response, boolean result, List<CryptocurrencyAsset> cryptocurrencyAssetList, List<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerList, List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList, List<CryptocurrencyExchanger> cryptocurrencyExchangerList) {

        super(response, result);
        this.cryptocurrencyAssetList = cryptocurrencyAssetList;
        this.cryptocurrencyBinanceAveragePriceTickerList = cryptocurrencyBinanceAveragePriceTickerList;
        this.cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationList;
        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;

    }


    public List<CryptocurrencyAsset> getCryptocurrencyAssetList() {

        return cryptocurrencyAssetList;

    }


    public void setCryptocurrencyAssetList(List<CryptocurrencyAsset> cryptocurrencyAssetList) {

        this.cryptocurrencyAssetList = cryptocurrencyAssetList;

    }


    public List<CryptocurrencyBinanceAveragePriceTicker> getCryptocurrencyBinanceAveragePriceTickerList() {

        return cryptocurrencyBinanceAveragePriceTickerList;

    }


    public void setCryptocurrencyBinanceAveragePriceTickerList(List<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerList) {

        this.cryptocurrencyBinanceAveragePriceTickerList = cryptocurrencyBinanceAveragePriceTickerList;

    }


    public List<CryptocurrencyBinanceExchangeInformation> getCryptocurrencyBinanceExchangeInformationList() {

        return cryptocurrencyBinanceExchangeInformationList;

    }


    public void setCryptocurrencyBinanceExchangeInformationList(List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList) {

        this.cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationList;

    }


    public List<CryptocurrencyExchanger> getCryptocurrencyExchangerList() {

        return cryptocurrencyExchangerList;

    }


    public void setCryptocurrencyExchangerList(List<CryptocurrencyExchanger> cryptocurrencyExchangerList) {

        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;

    }


}
