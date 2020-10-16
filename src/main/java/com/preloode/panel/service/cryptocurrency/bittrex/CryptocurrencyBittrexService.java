package com.preloode.panel.service.cryptocurrency.bittrex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.component.RestComponent;
import com.preloode.panel.component.WebSocketComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.HttpMethod;
import com.preloode.panel.enumeration.global.RestSource;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexBalance;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexCurrency;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexMarketTicker;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexResponse;
import com.preloode.panel.model.global.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CryptocurrencyBittrexService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyBittrexService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private RestComponent rest;

    @Autowired
    private WebSocketComponent webSocket;

    private HttpHeader header;


    @PostConstruct
    public void initialize() {

        List<String> headerName = new ArrayList<>() {
            {
                add("Api-Key");
                add("Api-Timestamp");
            }
        };
        List<Object> headerValue = new ArrayList<>() {
            {
                add(global.getApi().getBittrex().getApiKey());
                add(System.currentTimeMillis());
            }
        };
        header = new HttpHeader(headerName, headerValue);

    }


    public CryptocurrencyBittrexResponse loadBalance() {

        CryptocurrencyBittrexResponse result = new CryptocurrencyBittrexResponse() {
            {
                setResponse("Failed to load cryptocurrency bittrex balance");
                setResult(false);
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BittrexSigned, global.getApi().getBittrex().getUrl().getBase(), "/balances", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBittrexBalance> bittrexBalance = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setBalanceList(bittrexBalance);

                result.setResponse("Cryptocurrency bittrex balance loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBittrexResponse loadCurrency() {

        CryptocurrencyBittrexResponse result = new CryptocurrencyBittrexResponse() {
            {
                setResponse("Failed to load cryptocurrency bittrex currency");
                setResult(false);
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BittrexSigned, global.getApi().getBittrex().getUrl().getBase(), "/currencies", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBittrexCurrency> bittrexCurrency = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setCurrencyList(bittrexCurrency);

                result.setResponse("Cryptocurrency bittrex currency loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBittrexResponse loadMarketTicker() {

        CryptocurrencyBittrexResponse result = new CryptocurrencyBittrexResponse() {
            {
                setResponse("Failed to load cryptocurrency bittrex market ticker");
                setResult(false);
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BittrexSigned, global.getApi().getBittrex().getUrl().getBase(), "/markets/tickers", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBittrexMarketTicker> bittrexMarketTicker = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setMarketTickerList(bittrexMarketTicker);

                result.setResponse("Cryptocurrency bittrex market ticker loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


}
