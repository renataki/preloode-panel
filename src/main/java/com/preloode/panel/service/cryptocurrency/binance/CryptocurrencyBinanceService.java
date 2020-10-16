package com.preloode.panel.service.cryptocurrency.binance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.component.RestComponent;
import com.preloode.panel.component.WebSocketComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderSide;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderType;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceTimeInforce;
import com.preloode.panel.enumeration.global.HttpMethod;
import com.preloode.panel.enumeration.global.RestSource;
import com.preloode.panel.enumeration.global.WebSocketAction;
import com.preloode.panel.model.cryptocurrency.binance.*;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class CryptocurrencyBinanceService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyBinanceService.class);

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
                add("X-MBX-APIKEY");
            }
        };
        List<Object> headerValue = new ArrayList<>() {
            {
                add(global.getApi().getBinance().getApiKey());
            }
        };
        header = new HttpHeader(headerName, headerValue);

    }


    public BaseResponse checkAccountStatus() {

        BaseResponse result = new BaseResponse(
                "Failed to check cryptocurrency binance account status",
                false
        );

        List<Map<String, Object>> parameter = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("recvWindow", global.getApi().getBinance().getRecvWindow());
                    }
                });
            }
        };
        Map<String, Object> response = rest.send(RestSource.BinanceSigned, global.getApi().getBinance().getUrl().getBase(), "/wapi/v3/accountStatus.html", HttpMethod.GET, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyBinanceAccountStatus accountStatus = objectMapper.convertValue(response, new TypeReference<>() {
                });

                if(accountStatus.isSuccess()) {

                    result.setResponse("Cryptocurrency binance account status checked");
                    result.setResult(true);

                }

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse checkSystemStatus() {

        BaseResponse result = new BaseResponse(
                "Failed to check cryptocurrency binance system status",
                false
        );

        Map<String, Object> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/wapi/v3/systemStatus.html", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyBinanceSystemStatus systemStatus = objectMapper.convertValue(response, new TypeReference<>() {
                });

                if(systemStatus.getStatus() == 0) {

                    result.setResponse("Cryptocurrency binance system status checked");
                    result.setResult(true);

                }

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadAveragePriceTicker(String symbol) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance average price ticker");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>();

        if(!symbol.isEmpty()) {

            parameter.add(new HashMap<>() {
                {
                    put("symbol", symbol);
                }
            });

        }

        List<Map<String, Object>> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/ticker/24hr", HttpMethod.GET, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBinanceAveragePriceTicker> averagePriceTicker = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setAveragePriceTickerList(averagePriceTicker);

                result.setResponse("Cryptocurrency binance average price ticker loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadCoinInformation() {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance coin information");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("recvWindow", global.getApi().getBinance().getRecvWindow());
                    }
                });
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BinanceSigned, global.getApi().getBinance().getUrl().getBase(), "/sapi/v1/capital/config/getall", HttpMethod.GET, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBinanceCoinInformation> coinInformation = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setCoinInformationList(coinInformation);

                result.setResponse("Cryptocurrency binance coin information loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadExchangeInformation() {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance exchange information");
                setResult(false);
            }
        };

        Map<String, Object> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/exchangeInfo", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyBinanceExchangeInformationResponse binanceExchangeInformation = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setExchangeInformationList(binanceExchangeInformation.getSymbols());

                result.setResponse("Cryptocurrency binance exchange information loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadOpenOrder(String symbol) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance open order");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>();

        if(!symbol.isEmpty()) {

            parameter.add(new HashMap<>() {
                {
                    put("symbol", symbol);
                }
            });

        }

        parameter.add(new HashMap<>() {
            {
                put("recvWindow", global.getApi().getBinance().getRecvWindow());
            }
        });

        List<Map<String, Object>> response = rest.send(RestSource.BinanceSigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/openOrders", HttpMethod.GET, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();

                if(!symbol.isEmpty()) {

                    List<CryptocurrencyBinanceTradeOrder> tradeOrderList = objectMapper.convertValue(response, new TypeReference<>() {
                    });

                    result.setTradeOrderList(tradeOrderList);

                } else {

                    CryptocurrencyBinanceTradeOrder tradeOrder = objectMapper.convertValue(response, new TypeReference<>() {
                    });

                    result.setTradeOrder(tradeOrder);

                }

                result.setResponse("Cryptocurrency binance open order loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadTradeOrder(String symbol, BigInteger orderId) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance trade order");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("symbol", symbol);
                    }
                });
                add(new HashMap<>() {
                    {
                        put("orderId", orderId);
                    }
                });
                add(new HashMap<>() {
                    {
                        put("recvWindow", global.getApi().getBinance().getRecvWindow());
                    }
                });
            }
        };
        Map<String, Object> response = rest.send(RestSource.BinanceSigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/order", HttpMethod.GET, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyBinanceTradeOrder tradeOrder = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setTradeOrder(tradeOrder);

                result.setResponse("Cryptocurrency binance trade order loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadOrderBookTicker(String symbol) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to load cryptocurrency binance order book ticker");
                setResult(false);
            }
        };

        List<Map<String, Object>> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/ticker/bookTicker", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                List<CryptocurrencyBinanceOrderBookTicker> orderBookTickerList = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setOrderBookTickerList(orderBookTickerList);

                result.setResponse("Cryptocurrency binance order book ticker loaded");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse loadPriceTicker(String symbol) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setPriceTickerList(new ArrayList<>());
                setResponse("Failed to load cryptocurrency binance price ticker");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>();

        if(!symbol.isEmpty()) {

            parameter.add(new HashMap<>() {
                {
                    put("symbol", symbol);
                }
            });

        }

        if(!symbol.isEmpty()) {

            Map<String, Object> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/ticker/price", HttpMethod.GET, header, parameter);

            if(response != null) {

                try {

                    ObjectMapper objectMapper = new ObjectMapper();
                    CryptocurrencyBinancePriceTicker priceTicker = objectMapper.convertValue(response, new TypeReference<>() {
                    });

                    result.setPriceTicker(priceTicker);

                    result.setResponse("Cryptocurrency binance price ticker loaded");
                    result.setResult(true);

                } catch(Exception exception) {

                    logger.error(exception.getMessage());

                }

            }

        } else {

            List<Map<String, Object>> response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/ticker/price", HttpMethod.GET, header, parameter);

            if(response != null) {

                try {

                    ObjectMapper objectMapper = new ObjectMapper();
                    List<CryptocurrencyBinancePriceTicker> priceTickerList = objectMapper.convertValue(response, new TypeReference<>() {
                    });

                    result.setPriceTickerList(priceTickerList);

                    result.setResponse("Cryptocurrency binance price ticker loaded");
                    result.setResult(true);

                } catch(Exception exception) {

                    logger.error(exception.getMessage());

                }

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse newOrder(String symbol, BinanceOrderSide side, BinanceOrderType type, BigDecimal quantity, BigDecimal quoteOrderQuantity, BigDecimal price, BinanceTimeInforce timeInForce) {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to new cryptocurrency binance order");
                setResult(false);
            }
        };

        List<Map<String, Object>> parameter = new ArrayList<>() {
            {
                add(new HashMap<>() {
                    {
                        put("symbol", symbol);
                    }
                });
                add(new HashMap<>() {
                    {
                        put("side", side);
                    }
                });
                add(new HashMap<>() {
                    {
                        put("type", type);
                    }
                });
            }
        };

        if(type == BinanceOrderType.MARKET) {

            if(quantity != null) {

                parameter.add(new HashMap<>() {
                    {
                        put("quantity", quantity);
                    }
                });

            } else if(quoteOrderQuantity != null) {

                parameter.add(new HashMap<>() {
                    {
                        put("quoteOrderQty", quoteOrderQuantity);
                    }
                });

            }

        } else if(type == BinanceOrderType.LIMIT) {

            parameter.add(new HashMap<>() {
                {
                    put("price", price);
                }
            });
            parameter.add(new HashMap<>() {
                {
                    put("quantity", quantity);
                }
            });
            parameter.add(new HashMap<>() {
                {
                    put("timeInForce", timeInForce);
                }
            });

        }

        parameter.add(new HashMap<>() {
            {
                put("recvWindow", global.getApi().getBinance().getRecvWindow());
            }
        });

        Map<String, Object> response = rest.send(RestSource.BinanceSigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/order", HttpMethod.POST, header, parameter);

        if(response != null) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyBinanceNewOrder order = objectMapper.convertValue(response, new TypeReference<>() {
                });

                result.setNewOrder(order);

                result.setResponse("Cryptocurrency binance order new");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse subscribeStream(String stream, String header, CryptocurrencyBinanceStreamMessage message) {

        BaseResponse result = new BaseResponse(
                "Failed to subscribe cryptocurrency binance stream",
                false
        );

        webSocket.setAction(WebSocketAction.BinanceStream);
        webSocket.setHeader(header);
        webSocket.setMessage(webSocket.messageToString(message));
        webSocket.connect(global.getApi().getBinance().getUrl().getWebsocket(), "/stream?streams=" + stream, global.getApi().getBinance().getRecvWindow());

        try {

            TimeUnit.SECONDS.sleep((global.getApi().getBinance().getRecvWindow() / 1000));

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        if(webSocket.isConnected()) {

            result.setResponse("Cryptocurrency binance stream subscribed");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyBinanceResponse testConnection() {

        CryptocurrencyBinanceResponse result = new CryptocurrencyBinanceResponse() {
            {
                setResponse("Failed to test cryptocurrency binance connection");
                setResult(false);
            }
        };

        HttpHeader header = new HttpHeader(new ArrayList<>(), new ArrayList<>());
        Object response = rest.send(RestSource.BinanceUnsigned, global.getApi().getBinance().getUrl().getBase(), "/api/v3/ping", HttpMethod.GET, header, new ArrayList<>());

        if(response != null) {

            result.setTestConnection(response);

            result.setResponse("Cryptocurrency binance connection tested");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse unsubscibeStream(CryptocurrencyBinanceStreamMessage message) {

        BaseResponse result = new BaseResponse(
                "Failed to unsubscribe cryptocurrency binance stream",
                false
        );

        if(webSocket.getWebSocket() != null) {

            webSocket.setMessage(webSocket.messageToString(message));

            webSocket.getWebSocket().send(webSocket.messageToString(message));

            result.setResponse("Cryptocurrency binance stream unsubscribed");
            result.setResult(true);

        } else {

            result.setResponse("Cryptocurrency binance stream unsubscribed");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


}
