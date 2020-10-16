package com.preloode.panel.service.cryptocurrency.kraken;

import com.preloode.panel.component.RestComponent;
import com.preloode.panel.component.WebSocketComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.HttpMethod;
import com.preloode.panel.enumeration.global.RestSource;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenAssetPair;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenResponse;
import com.preloode.panel.model.global.HttpHeader;
import com.preloode.panel.repository.cryptocurrency.kraken.CryptocurrencyKrakenAssetPairRepository;
import com.preloode.panel.service.cryptocurrency.binance.CryptocurrencyBinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CryptocurrencyKrakenService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyBinanceService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private RestComponent rest;

    @Autowired
    private WebSocketComponent webSocket;

    @Autowired
    private CryptocurrencyKrakenAssetPairRepository cryptocurrencyKrakenAssetPairRepository;

    private HttpHeader header;


    @PostConstruct
    public void initialize() {

        List<String> headerName = new ArrayList<>() {
            {
                add("API-Key");
            }
        };
        List<Object> headerValue = new ArrayList<>() {
            {
                add(global.getApi().getKraken().getApiKey());
            }
        };
        header = new HttpHeader(headerName, headerValue);

    }


    public List<String> generatePairNameFromAssetName(String assetName) {

        List<String> result = new ArrayList<>();

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "pairname"));
            }
        };
        List<CryptocurrencyKrakenAssetPair> cryptocurrencyKrakenAssetPairByBaseOrQuote = cryptocurrencyKrakenAssetPairRepository.findByBaseOrQuoteSort(assetName, Sort.by(sort));

        for(CryptocurrencyKrakenAssetPair cryptocurrencyKrakenAssetPair : cryptocurrencyKrakenAssetPairByBaseOrQuote) {

            result.add(cryptocurrencyKrakenAssetPair.getPairname());

        }

        logger.info("Cryptocurrency kraken pair name from asset name generated");

        return result;

    }


    public CryptocurrencyKrakenResponse loadAssetPair() {

        CryptocurrencyKrakenResponse result = new CryptocurrencyKrakenResponse() {
            {
                setResponse("Failed to load cryptocurrency kraken asset pair");
                setResult(false);
            }
        };

        Map<String, Object> response = rest.send(RestSource.KrakenPublic, global.getApi().getKraken().getUrl().getBase(), "/0/public/AssetPairs", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            if(response.containsKey("result")) {

                result.setCryptocurrencyKrakenAssetPairList((Map<String, Object>) response.get("result"));

            }

            result.setResponse("cryptocurrency kraken asset pair loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyKrakenResponse loadTickerInformation(List<String> pairList) {

        CryptocurrencyKrakenResponse result = new CryptocurrencyKrakenResponse() {
            {
                setResponse("Failed to load cryptocurrency kraken ticker information");
                setResult(false);
            }
        };

        String pairName = "";

        for(String pair : pairList) {

            pairName += "," + pair;

        }

        String pair = pairName.replaceFirst(",", "");

        List<Map<String, Object>> dataList = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("pair", pair);
                    }
                });
            }
        };
        Map<String, Object> response = rest.send(RestSource.KrakenPublic, global.getApi().getKraken().getUrl().getBase(), "/0/public/Ticker", HttpMethod.GET, header, dataList);

        if(response != null) {

            if(response.containsKey("result")) {

                result.setCryptocurrencyKrakenTickerInformationList((Map<String, Object>) response.get("result"));

            }

        }

        result.setResponse("cryptocurrency kraken ticker information loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyKrakenResponse loadAccountBalance() {

        CryptocurrencyKrakenResponse result = new CryptocurrencyKrakenResponse() {
            {
                setResponse("Failed to load cryptocurrency kraken account balance");
                setResult(false);
            }
        };

        Map<String, Object> response = rest.sendHttps(RestSource.KrakenPrivate, global.getApi().getKraken().getUrl().getBase(), "/0/private/Balance", HttpMethod.POST, header, new ArrayList<>());

        if(response != null) {

            if(response.containsKey("result")) {

                result.setCryptocurrencyKrakenAssetPairList((Map<String, Object>) response.get("result"));

            }

            result.setResponse("cryptocurrency kraken account balance loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


}
