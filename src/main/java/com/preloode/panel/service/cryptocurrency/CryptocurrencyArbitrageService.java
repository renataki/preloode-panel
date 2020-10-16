package com.preloode.panel.service.cryptocurrency;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyArbitrageResponse;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceResponse;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexResponse;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexResponse;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenResponse;
import com.preloode.panel.service.cryptocurrency.binance.CryptocurrencyBinanceService;
import com.preloode.panel.service.cryptocurrency.bitfinex.CryptocurrencyBitfinexService;
import com.preloode.panel.service.cryptocurrency.bittrex.CryptocurrencyBittrexService;
import com.preloode.panel.service.cryptocurrency.kraken.CryptocurrencyKrakenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CryptocurrencyArbitrageService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyArbitrageService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CryptocurrencyBinanceService cryptocurrencyBinanceService;

    @Autowired
    private CryptocurrencyBitfinexService cryptocurrencyBitfinexService;

    @Autowired
    private CryptocurrencyBittrexService cryptocurrencyBittrexService;

    @Autowired
    private CryptocurrencyKrakenService cryptocurrencyKrakenService;

    @Autowired
    private CryptocurrencyService cryptocurrencyService;


    public CryptocurrencyArbitrageResponse initializeData() {

        CryptocurrencyArbitrageResponse result = new CryptocurrencyArbitrageResponse() {
            {
                setResponse("Failed to initialize cryptocurrency arbitrage data");
                setResult(false);
            }
        };

        result.setCryptocurrencyExchangerList(cryptocurrencyService.initializeExchangerInformation());
        result.setCryptocurrencyCurrencyList(Arrays.asList(global.getSetting().getCryptocurrency().getCurrency().getPrimary().replace("|", ",").split(",")));
        result.setCryptocurrencySymbolList(Arrays.asList(global.getSetting().getCryptocurrency().getSymbol().getPrimary().replace("|", ",").split(",")));
        result.setCryptocurrencyLotSize(global.getSetting().getCryptocurrency().getLot().getEur());

        List<String> currencyName = Arrays.asList(global.getSetting().getCryptocurrency().getCurrency().getPrimary().replace("|", ",").split(","));

        CryptocurrencyBinanceResponse cryptocurrencyBinanceSymbolPriceTicker = cryptocurrencyBinanceService.loadPriceTicker("");

        if(cryptocurrencyBinanceSymbolPriceTicker.isResult()) {

            result.setCryptocurrencyBinancePriceTickerList(cryptocurrencyBinanceSymbolPriceTicker.getPriceTickerList().stream().filter(priceTicker -> priceTicker.getSymbol().startsWith(currencyName.get(0)) || priceTicker.getSymbol().endsWith(currencyName.get(0))).collect(Collectors.toList()));

        }

        List<String> symbolList = new ArrayList<>() {
            {
                add("ALL");
            }
        };
        CryptocurrencyBitfinexResponse cryptocurrencyBitfinexTicker = cryptocurrencyBitfinexService.loadTicker(symbolList);

        if(cryptocurrencyBitfinexTicker.isResult()) {

            result.setCryptocurrencyBitfinexTickerList(cryptocurrencyBitfinexTicker.getCryptocurrencyBitfinexTickerList());

        }

        CryptocurrencyBittrexResponse cryptocurrencyBittrexMarketTicker = cryptocurrencyBittrexService.loadMarketTicker();

        if(cryptocurrencyBittrexMarketTicker.isResult()) {

            result.setCryptocurrencyBittrexMarketTickerList(cryptocurrencyBittrexMarketTicker.getMarketTickerList());

        }

        List<String> pairName = cryptocurrencyKrakenService.generatePairNameFromAssetName("Z" + currencyName.get(0));
        CryptocurrencyKrakenResponse cryptocurrencyKrakenTickerInformation = cryptocurrencyKrakenService.loadTickerInformation(pairName);

        if(cryptocurrencyKrakenTickerInformation.isResult()) {

            result.setCryptocurrencyKrakenTickerInformationList(cryptocurrencyKrakenTickerInformation.getCryptocurrencyKrakenTickerInformationList());

        }

        result.setResponse("Cryptocurrency arbitrage data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyArbitrageResponse loadCoinInformation() {

        CryptocurrencyArbitrageResponse result = new CryptocurrencyArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency arbitrage coin information");
                setResult(false);
            }
        };

        CryptocurrencyBinanceResponse cryptocurrencyBinanceCoinInformation = cryptocurrencyBinanceService.loadCoinInformation();

        CryptocurrencyBittrexResponse cryptocurrencyBittrexBalance = cryptocurrencyBittrexService.loadBalance();

        CryptocurrencyKrakenResponse cryptocurrencyKrakenAccountBalance = cryptocurrencyKrakenService.loadAccountBalance();

        if(cryptocurrencyBinanceCoinInformation.isResult() && cryptocurrencyBittrexBalance.isResult() && cryptocurrencyKrakenAccountBalance.isResult()) {

            result.setResponse("Cryptocurrency arbitrage coin information loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


}
