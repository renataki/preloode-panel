package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceCoinInformation;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformation;
import com.preloode.panel.model.global.BaseResponse;

import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyTriangularArbitrageResponse extends BaseResponse {


    private List<CryptocurrencyAsset> cryptocurrencyAssetList;

    private List<CryptocurrencyBinanceCoinInformation> cryptocurrencyBinanceCoinInformationList;

    private List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList;

    private BigInteger cryptocurrencyBinanceWebsocketId;

    private List<String> cryptocurrencyBinanceWebsocketParameter;

    private List<CryptocurrencyExchanger> cryptocurrencyExchangerList;

    private CryptocurrencyTriangularArbitrage cryptocurrencyTriangularArbitrage;

    private CryptocurrencyTriangularArbitrageFilter cryptocurrencyTriangularArbitrageFilter;

    private List<CryptocurrencyTriangularArbitrage> cryptocurrencyTriangularArbitrageList;


    public CryptocurrencyTriangularArbitrageResponse() {


    }


    public CryptocurrencyTriangularArbitrageResponse(String response, boolean result, List<CryptocurrencyAsset> cryptocurrencyAssetList, List<CryptocurrencyBinanceCoinInformation> cryptocurrencyBinanceCoinInformationList, List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList, BigInteger cryptocurrencyBinanceWebsocketId, List<String> cryptocurrencyBinanceWebsocketParameter, List<CryptocurrencyExchanger> cryptocurrencyExchangerList, CryptocurrencyTriangularArbitrage cryptocurrencyTriangularArbitrage, CryptocurrencyTriangularArbitrageFilter cryptocurrencyTriangularArbitrageFilter, List<CryptocurrencyTriangularArbitrage> cryptocurrencyTriangularArbitrageList) {

        super(response, result);
        this.cryptocurrencyAssetList = cryptocurrencyAssetList;
        this.cryptocurrencyBinanceCoinInformationList = cryptocurrencyBinanceCoinInformationList;
        this.cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationList;
        this.cryptocurrencyBinanceWebsocketId = cryptocurrencyBinanceWebsocketId;
        this.cryptocurrencyBinanceWebsocketParameter = cryptocurrencyBinanceWebsocketParameter;
        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;
        this.cryptocurrencyTriangularArbitrage = cryptocurrencyTriangularArbitrage;
        this.cryptocurrencyTriangularArbitrageFilter = cryptocurrencyTriangularArbitrageFilter;
        this.cryptocurrencyTriangularArbitrageList = cryptocurrencyTriangularArbitrageList;

    }


    public List<CryptocurrencyAsset> getCryptocurrencyAssetList() {

        return cryptocurrencyAssetList;

    }


    public void setCryptocurrencyAssetList(List<CryptocurrencyAsset> cryptocurrencyAssetList) {

        this.cryptocurrencyAssetList = cryptocurrencyAssetList;

    }


    public List<CryptocurrencyBinanceCoinInformation> getCryptocurrencyBinanceCoinInformationList() {

        return cryptocurrencyBinanceCoinInformationList;

    }


    public void setCryptocurrencyBinanceCoinInformationList(List<CryptocurrencyBinanceCoinInformation> cryptocurrencyBinanceCoinInformationList) {

        this.cryptocurrencyBinanceCoinInformationList = cryptocurrencyBinanceCoinInformationList;

    }


    public List<CryptocurrencyBinanceExchangeInformation> getCryptocurrencyBinanceExchangeInformationList() {

        return cryptocurrencyBinanceExchangeInformationList;

    }


    public void setCryptocurrencyBinanceExchangeInformationList(List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList) {

        this.cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationList;

    }


    public BigInteger getCryptocurrencyBinanceWebsocketId() {

        return cryptocurrencyBinanceWebsocketId;

    }


    public void setCryptocurrencyBinanceWebsocketId(BigInteger cryptocurrencyBinanceWebsocketId) {

        this.cryptocurrencyBinanceWebsocketId = cryptocurrencyBinanceWebsocketId;

    }


    public List<String> getCryptocurrencyBinanceWebsocketParameter() {

        return cryptocurrencyBinanceWebsocketParameter;

    }


    public void setCryptocurrencyBinanceWebsocketParameter(List<String> cryptocurrencyBinanceWebsocketParameter) {

        this.cryptocurrencyBinanceWebsocketParameter = cryptocurrencyBinanceWebsocketParameter;

    }


    public List<CryptocurrencyExchanger> getCryptocurrencyExchangerList() {

        return cryptocurrencyExchangerList;

    }


    public void setCryptocurrencyExchangerList(List<CryptocurrencyExchanger> cryptocurrencyExchangerList) {

        this.cryptocurrencyExchangerList = cryptocurrencyExchangerList;

    }


    public CryptocurrencyTriangularArbitrageFilter getCryptocurrencyTriangularArbitrageFilter() {

        return cryptocurrencyTriangularArbitrageFilter;

    }


    public void setCryptocurrencyTriangularArbitrageFilter(CryptocurrencyTriangularArbitrageFilter cryptocurrencyTriangularArbitrageFilter) {

        this.cryptocurrencyTriangularArbitrageFilter = cryptocurrencyTriangularArbitrageFilter;

    }


    public CryptocurrencyTriangularArbitrage getCryptocurrencyTriangularArbitrage() {

        return cryptocurrencyTriangularArbitrage;

    }


    public void setCryptocurrencyTriangularArbitrage(CryptocurrencyTriangularArbitrage cryptocurrencyTriangularArbitrage) {

        this.cryptocurrencyTriangularArbitrage = cryptocurrencyTriangularArbitrage;

    }


    public List<CryptocurrencyTriangularArbitrage> getCryptocurrencyTriangularArbitrageList() {

        return cryptocurrencyTriangularArbitrageList;

    }


    public void setCryptocurrencyTriangularArbitrageList(List<CryptocurrencyTriangularArbitrage> cryptocurrencyTriangularArbitrageList) {

        this.cryptocurrencyTriangularArbitrageList = cryptocurrencyTriangularArbitrageList;

    }


}
