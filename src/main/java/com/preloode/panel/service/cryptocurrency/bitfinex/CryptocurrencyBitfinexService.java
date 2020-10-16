package com.preloode.panel.service.cryptocurrency.bitfinex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.component.RestComponent;
import com.preloode.panel.component.WebSocketComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.HttpMethod;
import com.preloode.panel.enumeration.global.RestSource;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexResponse;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexSymbolDetail;
import com.preloode.panel.model.global.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CryptocurrencyBitfinexService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyBitfinexService.class);

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
                add("bfx-apikey");
            }
        };
        List<Object> headerValue = new ArrayList<>() {
            {
                add(global.getApi().getBitfinex().getApiKey());
            }
        };
        header = new HttpHeader(headerName, headerValue);

    }


    public CryptocurrencyBitfinexResponse loadSymbolDetail() {

        CryptocurrencyBitfinexResponse result = new CryptocurrencyBitfinexResponse() {
            {
                setResponse("Failed to load cryptocurrency bitfinex symbol detail");
                setResult(false);
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BitfinexPublic, global.getApi().getBitfinex().getUrl().getBaseAuthenticated(), "/v1/symbols_details", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBitfinexSymbolDetail> bitfinexSymbolDetail = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setCryptocurrencyBitfinexSymbolDetailList(bitfinexSymbolDetail);

                result.setResponse("Cryptocurrency bitfinex symbol detail loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBitfinexResponse loadTicker(List<String> symbolList) {

        CryptocurrencyBitfinexResponse result = new CryptocurrencyBitfinexResponse() {
            {
                setResponse("Failed to load cryptocurrency bitfinex ticker");
                setResult(false);
            }
        };

        String symbolString = "";

        for(String symbol : symbolList) {

            symbolString += "," + symbol;

        }

        String symbols = symbolString.replaceFirst(",", "");

        List<Map<String, Object>> parameter = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("symbols", symbols);
                    }
                });
            }
        };
        List<List<Object>> response = rest.send(RestSource.BitfinexPublic, global.getApi().getBitfinex().getUrl().getBasePublic(), "/v2/tickers", HttpMethod.GET, header, parameter);

        if(response != null) {

            result.setCryptocurrencyBitfinexTickerList(response);

            result.setResponse("Cryptocurrency bitfinex ticker loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


}
