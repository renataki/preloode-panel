package com.preloode.panel.service.cryptocurrency;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.cryptocurrency.CryptocurrencyAction;
import com.preloode.panel.enumeration.cryptocurrency.CryptocurrencyArbitrageStatus;
import com.preloode.panel.enumeration.cryptocurrency.Exchanger;
import com.preloode.panel.enumeration.cryptocurrency.binance.*;
import com.preloode.panel.model.cryptocurrency.*;
import com.preloode.panel.model.cryptocurrency.binance.*;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.repository.cryptocurrency.CryptocurrencyAssetRepository;
import com.preloode.panel.repository.cryptocurrency.CryptocurrencyTriangularArbitrageRepository;
import com.preloode.panel.repository.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformationRepository;
import com.preloode.panel.service.cryptocurrency.binance.CryptocurrencyBinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CryptocurrencyTriangularArbitrageService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyTriangularArbitrageService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private CryptocurrencyBinanceService cryptocurrencyBinanceService;

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    private CryptocurrencyAssetRepository cryptocurrencyAssetRepository;

    @Autowired
    private CryptocurrencyBinanceExchangeInformationRepository cryptocurrencyBinanceExchangeInformationRepository;

    @Autowired
    private CryptocurrencyTriangularArbitrageRepository cryptocurrencyTriangularArbitrageRepository;

    private List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList;


    private BigDecimal calculateExchangeFee(BigDecimal lotSize, BigDecimal minimumQuantity) {

        BigDecimal result = lotSize.subtract(lotSize.multiply(global.getApi().getBinance().getFee().getCommission()).divide(new BigDecimal("100")));
        result = result.divide(minimumQuantity, 0, RoundingMode.FLOOR).multiply(minimumQuantity);

        logger.info("Cryptocurrency binance exchange fee calculated");

        return result;

    }


    private CryptocurrencyTriangularArbitrage calculateTriangularArbitragePercentage(List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList, CryptocurrencyTriangularArbitrage triangularArbitrage, BigDecimal lotSize) {

        CryptocurrencyTriangularArbitrage result = triangularArbitrage;

        result.getStart().getQuantity().setMinimum(loadSymbolMinimumQuantity(exchangeInformationList, result.getStart().getSymbol()));

        if(result.getStart().getAction() == CryptocurrencyAction.Bid) {

            lotSize = lotSize.divide(triangularArbitrage.getStart().getPrice(), 8, RoundingMode.FLOOR);

        }

        if(result.getStart().getQuantity().getMinimum().compareTo(lotSize) == -1) {

            result.getStart().getCredit().setStart(lotSize.add(lotSize.multiply(global.getApi().getBinance().getFee().getCommission()).divide(new BigDecimal("100"))));
            result.getStart().getQuantity().setOrder(lotSize);

            if(result.getStart().getAction() == CryptocurrencyAction.Ask) {

                result.getStart().getCredit().setEnd(lotSize.multiply(result.getStart().getPrice()).setScale(8, RoundingMode.FLOOR));

            } else {

                result.getStart().getCredit().setEnd(lotSize);

            }

            result.getMiddle().getCredit().setStart(result.getStart().getCredit().getEnd());
            result.getMiddle().getQuantity().setMinimum(loadSymbolMinimumQuantity(exchangeInformationList, result.getMiddle().getSymbol()));

            BigDecimal exchangeableCredit = result.getMiddle().getCredit().getStart().divide(result.getMiddle().getQuantity().getMinimum(), 0, RoundingMode.FLOOR).multiply(result.getMiddle().getQuantity().getMinimum());
            result.getMiddle().getQuantity().setOrder(calculateExchangeFee(exchangeableCredit, result.getMiddle().getQuantity().getMinimum()));

            if(result.getMiddle().getAction() == CryptocurrencyAction.Ask) {

                result.getMiddle().getCredit().setEnd(result.getMiddle().getQuantity().getOrder().multiply(result.getMiddle().getPrice()).setScale(8, RoundingMode.FLOOR));

            } else {

                result.getMiddle().getCredit().setEnd(result.getMiddle().getQuantity().getOrder().divide(result.getMiddle().getPrice(), 8, RoundingMode.FLOOR));

            }

            result.getEnd().getCredit().setStart(result.getMiddle().getCredit().getEnd());
            result.getEnd().getQuantity().setMinimum(loadSymbolMinimumQuantity(exchangeInformationList, result.getEnd().getSymbol()));

            exchangeableCredit = result.getEnd().getCredit().getStart().divide(result.getEnd().getQuantity().getMinimum(), 0, RoundingMode.FLOOR).multiply(result.getEnd().getQuantity().getMinimum());
            result.getEnd().getQuantity().setOrder(calculateExchangeFee(exchangeableCredit, result.getEnd().getQuantity().getMinimum()));

            if(result.getEnd().getAction() == CryptocurrencyAction.Ask) {

                result.getEnd().getCredit().setEnd(result.getEnd().getQuantity().getOrder().multiply(result.getEnd().getPrice()).setScale(8, RoundingMode.FLOOR));

            } else {

                result.getEnd().getCredit().setEnd(result.getEnd().getQuantity().getOrder().divide(result.getEnd().getPrice(), 8, RoundingMode.FLOOR));

            }

            result.setPercentage(result.getEnd().getCredit().getEnd().subtract(result.getStart().getCredit().getStart()).divide(result.getStart().getCredit().getStart(), 8, RoundingMode.FLOOR).multiply(new BigDecimal(100).setScale(2, RoundingMode.FLOOR)));

        }

        logger.info("Cryptocurrency binance triangular arbitrage percentage calculated");

        return result;

    }


    public CryptocurrencyTriangularArbitrageResponse initializeData() {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to initialize cryptocurrency triangular arbitrage data");
                setResult(false);
            }
        };

        List<String> assetName = Arrays.asList(global.getSetting().getCryptocurrency().getAsset().getPrimary().replace("|", ",").split(","));
        result.setCryptocurrencyAssetList(cryptocurrencyAssetRepository.findInName(new HashSet(assetName)));

        result.setCryptocurrencyExchangerList(cryptocurrencyService.initializeExchangerInformation());

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationRepository.findByStatusSort(BinanceSymbolStatus.TRADING, Sort.by(sort));
        result.setCryptocurrencyBinanceExchangeInformationList(cryptocurrencyBinanceExchangeInformationList);

        result.setCryptocurrencyTriangularArbitrageList(cryptocurrencyTriangularArbitrageRepository.findNotStatusSort(CryptocurrencyArbitrageStatus.Completed, Sort.by(new Sort.Order(Sort.Direction.ASC, "created.timestamp"))));

        if(!result.getCryptocurrencyTriangularArbitrageList().isEmpty()) {

            result.setCryptocurrencyTriangularArbitrageFilter(new CryptocurrencyTriangularArbitrageFilter(
                    null,
                    result.getCryptocurrencyTriangularArbitrageList().get(0).getExchanger(),
                    result.getCryptocurrencyTriangularArbitrageList().get(0).getStart().getQuantity().getOrder(),
                    BigDecimal.ZERO,
                    null,
                    null
            ));

            if(result.getCryptocurrencyTriangularArbitrageList().get(0).getStart().getAction() == CryptocurrencyAction.Ask) {

                result.getCryptocurrencyTriangularArbitrageFilter().setAsset(result.getCryptocurrencyTriangularArbitrageList().get(0).getStart().getAsset().getBase());

            } else if(result.getCryptocurrencyTriangularArbitrageList().get(0).getStart().getAction() == CryptocurrencyAction.Bid) {

                result.getCryptocurrencyTriangularArbitrageFilter().setAsset(result.getCryptocurrencyTriangularArbitrageList().get(0).getStart().getAsset().getQuote());

            }

        }

        result.setResponse("Cryptocurrency triangular arbitrage data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private CryptocurrencyTriangularArbitrageFilter initializeFilter(CryptocurrencyTriangularArbitrageFilter filter) {

        CryptocurrencyTriangularArbitrageFilter result = filter;

        if(result.getLotSize() == null) {

            result.setLotSize(BigDecimal.ZERO);

        }

        if(result.getMinimumPercentage() == null) {

            result.setMinimumPercentage(BigDecimal.ZERO);

        }

        if(result.getStreamList() == null) {

            result.setStreamList(new ArrayList<>());

        }

        logger.info("Cryptocurrency binance triangular arbitrage filter initialized");

        return result;

    }


    private CryptocurrencyTriangularArbitrage initializeStep(String asset, CryptocurrencyBinancePriceTicker baseAsset, CryptocurrencyBinancePriceTicker quoteAsset, CryptocurrencyAction startAction) {

        CryptocurrencyTriangularArbitrage result = new CryptocurrencyTriangularArbitrage(
                null,
                null,
                null,
                new CryptocurrencyTriangularArbitrageStepReference(),
                Exchanger.Binance,
                new CryptocurrencyTriangularArbitrageStepReference(),
                BigDecimal.ZERO,
                new CryptocurrencyTriangularArbitrageStepReference(),
                CryptocurrencyArbitrageStatus.Pending
        );

        if(startAction == CryptocurrencyAction.Ask) {

            result.getStart().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference(
                    asset,
                    baseAsset.getSymbol().replace(asset, "")
            ));
            result.getStart().setPrice(baseAsset.getPrice());
            result.getStart().setSymbol(baseAsset.getSymbol());

        } else if(startAction == CryptocurrencyAction.Bid) {

            result.getStart().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference(
                    quoteAsset.getSymbol().replace(asset, ""),
                    asset
            ));
            result.getStart().setPrice(quoteAsset.getPrice());
            result.getStart().setSymbol(quoteAsset.getSymbol());

        }

        result.getStart().setAction(startAction);
        result.getStart().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
        result.getStart().setOrderId(BigInteger.ZERO);
        result.getStart().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());
        result.getStart().setStatus(CryptocurrencyArbitrageStatus.Pending);
        result.getStart().setType("Market");

        result.getMiddle().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference());
        result.getMiddle().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
        result.getMiddle().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());

        result.getEnd().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference());
        result.getEnd().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
        result.getEnd().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());

        logger.info("Cryptocurrency binance triangular arbitrage step initialized");

        return result;

    }


    private CryptocurrencyTriangularArbitrage loadBinanceData(List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList, List<CryptocurrencyBinancePriceTicker> priceTickerList, CryptocurrencyTriangularArbitrageFilter filter) {

        CryptocurrencyTriangularArbitrage result = new CryptocurrencyTriangularArbitrage(
                null,
                null,
                null,
                new CryptocurrencyTriangularArbitrageStepReference(),
                Exchanger.Binance,
                new CryptocurrencyTriangularArbitrageStepReference(),
                BigDecimal.ZERO,
                new CryptocurrencyTriangularArbitrageStepReference(),
                CryptocurrencyArbitrageStatus.Pending
        );

        List<CryptocurrencyBinancePriceTicker> priceTickerStart = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(filter.getStreamList().get(0))).collect(Collectors.toList());

        if(!priceTickerStart.isEmpty()) {

            result.getStart().setAction(CryptocurrencyAction.Ask);
            result.getStart().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference(
                    filter.getAsset(),
                    priceTickerStart.get(0).getSymbol().replace(filter.getAsset(), "")
            ));
            result.getStart().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
            result.getStart().setPrice(priceTickerStart.get(0).getPrice());
            result.getStart().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());
            result.getStart().setSymbol(priceTickerStart.get(0).getSymbol());
            result.getStart().setType("Market");

        }

        List<CryptocurrencyBinancePriceTicker> priceTickerEnd = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(filter.getStreamList().get(2))).collect(Collectors.toList());

        if(!priceTickerEnd.isEmpty()) {

            result.getEnd().setAction(CryptocurrencyAction.Ask);
            result.getEnd().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference(
                    priceTickerEnd.get(0).getSymbol().replace(filter.getAsset(), ""),
                    filter.getAsset()
            ));
            result.getEnd().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
            result.getEnd().setPrice(priceTickerEnd.get(0).getPrice());
            result.getEnd().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());
            result.getEnd().setSymbol(priceTickerEnd.get(0).getSymbol());
            result.getEnd().setType("Market");

        }

        List<CryptocurrencyBinancePriceTicker> priceTickerMiddle = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(filter.getStreamList().get(0).replace(filter.getAsset(), "") + filter.getStreamList().get(2).replace(filter.getAsset(), ""))).collect(Collectors.toList());

        if(!priceTickerMiddle.isEmpty()) {

            result.getMiddle().setAction(CryptocurrencyAction.Ask);

        } else {

            priceTickerMiddle = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(filter.getStreamList().get(2).replace(filter.getAsset(), "") + filter.getStreamList().get(0).replace(filter.getAsset(), ""))).collect(Collectors.toList());

            if(!priceTickerMiddle.isEmpty()) {

                result.getMiddle().setAction(CryptocurrencyAction.Bid);

            }

        }

        if(result.getMiddle().getAction() != null) {

            result.getMiddle().setAsset(new CryptocurrencyTriangularArbitrageStepAssetReference(
                    result.getStart().getAsset().getQuote(),
                    result.getEnd().getAsset().getQuote()
            ));
            result.getMiddle().setCredit(new CryptocurrencyTriangularArbitrageStepCreditReference());
            result.getMiddle().setPrice(priceTickerMiddle.get(0).getPrice());
            result.getMiddle().setQuantity(new CryptocurrencyTriangularArbitrageStepQuantityReference());
            result.getMiddle().setSymbol(priceTickerMiddle.get(0).getSymbol());
            result.getMiddle().setType("Market");

            result = calculateTriangularArbitragePercentage(exchangeInformationList, result, filter.getLotSize());

        }

        logger.info("Cryptocurrency triangular arbitrage binance data loaded");

        return result;

    }


    private List<CryptocurrencyTriangularArbitrage> loadBinanceList(String asset, List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList, List<CryptocurrencyBinancePriceTicker> priceTickerList, CryptocurrencyTriangularArbitrageFilter filter) {

        List<CryptocurrencyTriangularArbitrage> result = new ArrayList<>();

        List<CryptocurrencyBinancePriceTicker> priceTickerByBaseAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().startsWith(asset)).collect(Collectors.toList());
        List<CryptocurrencyBinancePriceTicker> priceTickerByQuoteAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().endsWith(asset)).collect(Collectors.toList());

        for(CryptocurrencyBinancePriceTicker baseAsset : priceTickerByBaseAsset) {

            for(CryptocurrencyBinancePriceTicker quoteAsset : priceTickerByQuoteAsset) {

                CryptocurrencyTriangularArbitrage triangularArbitrage = initializeStep(asset, baseAsset, null, CryptocurrencyAction.Ask);

                List<CryptocurrencyBinancePriceTicker> priceTickerByMiddleAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(baseAsset.getSymbol().replace(asset, "") + quoteAsset.getSymbol().replace(asset, ""))).collect(Collectors.toList());

                triangularArbitrage.getMiddle().setAction(CryptocurrencyAction.Ask);

                triangularArbitrage.getEnd().setAction(CryptocurrencyAction.Ask);

                if(priceTickerByMiddleAsset.isEmpty()) {

                    priceTickerByMiddleAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(quoteAsset.getSymbol().replace(asset, "") + baseAsset.getSymbol().replace(asset, ""))).collect(Collectors.toList());

                    if(!priceTickerByMiddleAsset.isEmpty()) {

                        triangularArbitrage.getMiddle().setAction(CryptocurrencyAction.Bid);

                    }

                }

                if(!priceTickerByMiddleAsset.isEmpty()) {

                    triangularArbitrage.getMiddle().setSymbol(priceTickerByMiddleAsset.get(0).getSymbol());
                    triangularArbitrage.getMiddle().getAsset().setBase(triangularArbitrage.getStart().getAsset().getQuote());
                    triangularArbitrage.getMiddle().getAsset().setQuote(triangularArbitrage.getMiddle().getSymbol().replace(triangularArbitrage.getStart().getAsset().getQuote(), ""));
                    triangularArbitrage.getMiddle().setPrice(priceTickerByMiddleAsset.get(0).getPrice());

                    triangularArbitrage.getEnd().setSymbol(quoteAsset.getSymbol());
                    triangularArbitrage.getEnd().getAsset().setBase(quoteAsset.getSymbol().replace(asset, ""));
                    triangularArbitrage.getEnd().getAsset().setQuote(asset);
                    triangularArbitrage.getEnd().setPrice(quoteAsset.getPrice());

                    triangularArbitrage = calculateTriangularArbitragePercentage(exchangeInformationList, triangularArbitrage, filter.getLotSize());

                    if(triangularArbitrage.getPercentage().compareTo(filter.getMinimumPercentage()) >= 0) {

                        result.add(triangularArbitrage);

                    }

                }

            }

        }

        for(CryptocurrencyBinancePriceTicker quoteAsset : priceTickerByQuoteAsset) {

            for(CryptocurrencyBinancePriceTicker baseAsset : priceTickerByBaseAsset) {

                CryptocurrencyTriangularArbitrage triangularArbitrage = initializeStep(asset, null, quoteAsset, CryptocurrencyAction.Bid);

                List<CryptocurrencyBinancePriceTicker> priceTickerByMiddleAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(quoteAsset.getSymbol().replace(asset, "") + baseAsset.getSymbol().replace(asset, ""))).collect(Collectors.toList());

                triangularArbitrage.getMiddle().setAction(CryptocurrencyAction.Ask);

                triangularArbitrage.getEnd().setAction(CryptocurrencyAction.Bid);

                if(priceTickerByMiddleAsset.isEmpty()) {

                    priceTickerByMiddleAsset = priceTickerList.stream().filter(priceTicker -> priceTicker.getSymbol().equals(baseAsset.getSymbol().replace(asset, "") + quoteAsset.getSymbol().replace(asset, ""))).collect(Collectors.toList());

                    if(!priceTickerByMiddleAsset.isEmpty()) {

                        triangularArbitrage.getMiddle().setAction(CryptocurrencyAction.Bid);

                    }

                }

                if(!priceTickerByMiddleAsset.isEmpty()) {

                    triangularArbitrage.getMiddle().setSymbol(priceTickerByMiddleAsset.get(0).getSymbol());
                    triangularArbitrage.getMiddle().getAsset().setBase(triangularArbitrage.getStart().getAsset().getQuote());
                    triangularArbitrage.getMiddle().getAsset().setQuote(triangularArbitrage.getMiddle().getSymbol().replace(triangularArbitrage.getStart().getAsset().getQuote(), ""));
                    triangularArbitrage.getMiddle().setPrice(priceTickerByMiddleAsset.get(0).getPrice());

                    triangularArbitrage.getEnd().setSymbol(baseAsset.getSymbol());
                    triangularArbitrage.getEnd().getAsset().setBase(baseAsset.getSymbol().replace(asset, ""));
                    triangularArbitrage.getEnd().getAsset().setQuote(asset);
                    triangularArbitrage.getEnd().setPrice(baseAsset.getPrice());

                    triangularArbitrage = calculateTriangularArbitragePercentage(exchangeInformationList, triangularArbitrage, filter.getLotSize());

                    if(triangularArbitrage.getPercentage().compareTo(filter.getMinimumPercentage()) >= 0) {

                        result.add(triangularArbitrage);

                    }

                }

            }

        }

        logger.info("Cryptocurrency triangular arbitrage binance list by asset loaded");

        return result;

    }


    public CryptocurrencyTriangularArbitrageResponse loadCoinInformation(Exchanger exchanger) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency triangular arbitrage coin information");
                setResult(false);
            }
        };

        if(exchanger == Exchanger.Binance) {

            CryptocurrencyBinanceResponse cryptocurrencyBinanceCoinInformation = cryptocurrencyBinanceService.loadCoinInformation();

            if(cryptocurrencyBinanceCoinInformation.isResult()) {

                result.setCryptocurrencyBinanceCoinInformationList(cryptocurrencyBinanceCoinInformation.getCoinInformationList());

                result.setResponse("Cryptocurrency triangular arbitrage coin information loaded");
                result.setResult(true);

            }

        }

        logger.info((result.getResponse()));

        return result;

    }


    public CryptocurrencyTriangularArbitrageResponse loadDataStream(CryptocurrencyTriangularArbitrageFilter filter) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency triangular arbitrage data stream");
                setResult(false);
            }
        };

        filter = initializeFilter(filter);

        if(filter.getExchanger() == Exchanger.Binance) {

            String stream = "";
            List<String> params = new ArrayList<>();

            for(String filterStream : filter.getStreamList()) {

                stream += "/" + filterStream.toLowerCase();
                params.add(filterStream.toLowerCase() + "@ticker");

            }

            stream = stream.replaceFirst("/", "");

            CryptocurrencyBinanceStreamMessage message = new CryptocurrencyBinanceStreamMessage(
                    BinanceLoadStreamMethod.SUBSCRIBE,
                    params,
                    new BigInteger((String.valueOf(System.currentTimeMillis())))
            );

            BaseResponse cryptocurrencyBinanceStream = cryptocurrencyBinanceService.subscribeStream(stream, null, message);

            if(cryptocurrencyBinanceStream.isResult()) {

                result.setCryptocurrencyBinanceWebsocketId(message.getId());
                result.setCryptocurrencyBinanceWebsocketParameter(filter.getStreamList());

                result.setResponse("Cryptocurrency triangular arbitrage data stream loaded");
                result.setResult(true);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyTriangularArbitrageResponse loadList(CryptocurrencyTriangularArbitrageFilter filter) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to load cryptocurrency triangular arbitrage list");
                setResult(false);
            }
        };

        filter = initializeFilter(filter);

        List<CryptocurrencyTriangularArbitrage> triangularArbitrageList = new ArrayList<>();

        if(filter.getExchanger() == Exchanger.Binance) {

            List<String> exchangeInformationSymbolList = cryptocurrencyBinanceExchangeInformationList.stream().map(CryptocurrencyBinanceExchangeInformation::getSymbol).collect(Collectors.toList());

            CryptocurrencyBinanceResponse cryptocurrencyBinancePriceTickerList = cryptocurrencyBinanceService.loadPriceTicker("");

            if(!filter.getStreamList().isEmpty()) {

                List<String> filterTicker = filter.getStreamList();
                List<CryptocurrencyBinancePriceTicker> priceTickerBySymbol = cryptocurrencyBinancePriceTickerList.getPriceTickerList().stream().filter(priceTicker -> filterTicker.contains(priceTicker.getSymbol())).collect(Collectors.toList());
                triangularArbitrageList.add(loadBinanceData(cryptocurrencyBinanceExchangeInformationList, priceTickerBySymbol, filter));

            } else {

                List<CryptocurrencyBinancePriceTicker> priceTickerBySymbol = cryptocurrencyBinancePriceTickerList.getPriceTickerList().stream().filter(priceTicker -> exchangeInformationSymbolList.contains(priceTicker.getSymbol())).collect(Collectors.toList());
                triangularArbitrageList = loadBinanceList(filter.getAsset(), cryptocurrencyBinanceExchangeInformationList, priceTickerBySymbol, filter);

            }

        }

        if(!triangularArbitrageList.isEmpty()) {

            triangularArbitrageList.sort(Comparator.comparing(CryptocurrencyTriangularArbitrage::getPercentage).reversed());

            result.setCryptocurrencyTriangularArbitrageList(triangularArbitrageList);

        }

        result.setResponse("Cryptocurrency triangular arbitrage list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private BigDecimal loadSymbolMinimumQuantity(List<CryptocurrencyBinanceExchangeInformation> exchangeInformationList, String symbol) {

        BigDecimal result = BigDecimal.ZERO;

        List<CryptocurrencyBinanceExchangeInformation> exchangeInformationByPrice = exchangeInformationList.stream().filter(exchangeInformation -> exchangeInformation.getSymbol().equals(symbol)).collect(Collectors.toList());

        if(!exchangeInformationByPrice.isEmpty()) {

            for(CryptocurrencyBinanceFilterReference cryptocurrencyBinanceFilter : exchangeInformationByPrice.get(0).getFilters()) {

                if(cryptocurrencyBinanceFilter.getFilterType() == BinanceFilterType.LOT_SIZE) {

                    result = cryptocurrencyBinanceFilter.getMinQty();

                    break;

                }

            }

        }

        logger.info("Cryptocurrency binance symbol minimum quantity loaded");

        return result;

    }


    public CryptocurrencyTriangularArbitrageResponse tradeBinance(CryptocurrencyTriangularArbitrage triangularArbitrage) {

        CryptocurrencyTriangularArbitrageResponse result = new CryptocurrencyTriangularArbitrageResponse() {
            {
                setResponse("Failed to trade cryptocurrency triangular arbitrage binance");
                setResult(false);
            }
        };

        if(triangularArbitrage.getStatus() != CryptocurrencyArbitrageStatus.End && triangularArbitrage.getStatus() != CryptocurrencyArbitrageStatus.Completed) {

            String symbol = "";
            BinanceOrderSide side = BinanceOrderSide.BUY;
            BinanceOrderType type = BinanceOrderType.MARKET;
            BigDecimal price = BigDecimal.ZERO;
            BigDecimal quantity = null;
            BigDecimal quoteQuantity = null;
            BinanceTimeInforce timeInforce = BinanceTimeInforce.GTC;

            if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Pending) {

                price = triangularArbitrage.getStart().getPrice();
                quantity = triangularArbitrage.getStart().getQuantity().getOrder();
                symbol = triangularArbitrage.getStart().getSymbol();

                if(triangularArbitrage.getStart().getAction() == CryptocurrencyAction.Ask) {

                    side = BinanceOrderSide.SELL;

                }

                type = BinanceOrderType.valueOf(triangularArbitrage.getStart().getType().toUpperCase());

            } else if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Start) {

                price = triangularArbitrage.getMiddle().getPrice();
                quantity = triangularArbitrage.getMiddle().getQuantity().getOrder();
                symbol = triangularArbitrage.getMiddle().getSymbol();

                if(triangularArbitrage.getMiddle().getAction() == CryptocurrencyAction.Ask) {

                    side = BinanceOrderSide.SELL;

                }

                type = BinanceOrderType.valueOf(triangularArbitrage.getMiddle().getType().toUpperCase());

            } else if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Middle) {

                price = triangularArbitrage.getEnd().getPrice();
                quantity = triangularArbitrage.getEnd().getQuantity().getOrder();
                symbol = triangularArbitrage.getEnd().getSymbol();

                if(triangularArbitrage.getEnd().getAction() == CryptocurrencyAction.Ask) {

                    side = BinanceOrderSide.SELL;

                }

                type = BinanceOrderType.valueOf(triangularArbitrage.getEnd().getType().toUpperCase());

            }

            boolean trade = true;

            if(type == BinanceOrderType.MARKET) {

                CryptocurrencyBinanceResponse cryptocurrencyBinancePriceTicker = cryptocurrencyBinanceService.loadPriceTicker(symbol);

                if(cryptocurrencyBinancePriceTicker.isResult()) {

                    if(cryptocurrencyBinancePriceTicker.getPriceTicker().getPrice().compareTo(price) != 0) {

                        trade = false;

                    }

                }

            }

            if(trade) {

                CryptocurrencyBinanceResponse newOrder = cryptocurrencyBinanceService.newOrder(symbol, side, type, quantity, quoteQuantity, price, timeInforce);

                if(newOrder.isResult()) {

                    if(newOrder.getNewOrder().getStatus() == BinanceOrderStatus.FILLED) {

                        if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Pending) {

                            if(type == BinanceOrderType.MARKET) {

                                triangularArbitrage.getStart().setStatus(CryptocurrencyArbitrageStatus.Completed);

                            }

                            triangularArbitrage.setStatus(CryptocurrencyArbitrageStatus.Start);

                        } else if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Start) {

                            if(type == BinanceOrderType.MARKET) {

                                triangularArbitrage.getMiddle().setStatus(CryptocurrencyArbitrageStatus.Completed);

                            }

                            triangularArbitrage.setStatus(CryptocurrencyArbitrageStatus.Middle);

                        } else if(triangularArbitrage.getStatus() == CryptocurrencyArbitrageStatus.Middle) {

                            if(type == BinanceOrderType.MARKET) {

                                triangularArbitrage.getEnd().setStatus(CryptocurrencyArbitrageStatus.Completed);

                            }

                            triangularArbitrage.setStatus(CryptocurrencyArbitrageStatus.Completed);

                        }

                        triangularArbitrage.setCreated(data.initializeTimestampReference("0", "System", null));
                        triangularArbitrage.setModified(data.initializeTimestampReference("0", "System", null));
                        triangularArbitrage = cryptocurrencyTriangularArbitrageRepository.save(triangularArbitrage);

                        result.setResponse("Cryptocurrency triangular arbitrage " + triangularArbitrage.getStatus().toString().toLowerCase() + " traded");
                        result.setResult(true);

                    }

                }

            } else {

                result.setResponse("Cryptocurrency triangular arbitrage binance price changed");

            }

            result.setCryptocurrencyTriangularArbitrage(triangularArbitrage);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse unsubscribeStream(CryptocurrencyTriangularArbitrageFilter filter) {

        BaseResponse result = new BaseResponse(
                "Failed to unsubscribe cryptocurrency triangular arbitrage stream",
                false
        );

        filter = initializeFilter(filter);

        if(filter.getExchanger() == Exchanger.Binance) {

            if(!filter.getStreamList().isEmpty()) {

                List<String> params = new ArrayList<>();

                for(String filterStream : filter.getStreamList()) {

                    params.add(filterStream.toLowerCase() + "@ticker");

                }

                CryptocurrencyBinanceStreamMessage message = new CryptocurrencyBinanceStreamMessage(
                        BinanceLoadStreamMethod.UNSUBSCRIBE,
                        params,
                        filter.getWebsocketId()
                );

                result = cryptocurrencyBinanceService.unsubscibeStream(message);

            } else {

                result.setResponse("Cryptocurrency triangular arbitrage stream doesn't exist");
                result.setResult(true);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


}
