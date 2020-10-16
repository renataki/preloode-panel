package com.preloode.panel.service.cryptocurrency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.cryptocurrency.Exchanger;
import com.preloode.panel.enumeration.cryptocurrency.ExchangerStatus;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceSymbolStatus;
import com.preloode.panel.model.cryptocurrency.*;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceAveragePriceTicker;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformation;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceResponse;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexResponse;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexSymbolDetail;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexCurrency;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexResponse;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenAssetPair;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.repository.cryptocurrency.CryptocurrencyAssetRepository;
import com.preloode.panel.repository.cryptocurrency.binance.CryptocurrencyBinanceAveragePriceTickerRepository;
import com.preloode.panel.repository.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformationRepository;
import com.preloode.panel.repository.cryptocurrency.bitfinex.CryptocurrencyBitfinexSymbolDetailRepository;
import com.preloode.panel.repository.cryptocurrency.bittrex.CryptocurrencyBittrexCurrencyRepository;
import com.preloode.panel.repository.cryptocurrency.kraken.CryptocurrencyKrakenAssetPairRepository;
import com.preloode.panel.service.cryptocurrency.binance.CryptocurrencyBinanceService;
import com.preloode.panel.service.cryptocurrency.bitfinex.CryptocurrencyBitfinexService;
import com.preloode.panel.service.cryptocurrency.bittrex.CryptocurrencyBittrexService;
import com.preloode.panel.service.cryptocurrency.kraken.CryptocurrencyKrakenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class CryptocurrencyService {


    private static final Logger logger = LoggerFactory.getLogger(CryptocurrencyService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private CryptocurrencyBinanceService cryptocurrencyBinanceService;

    @Autowired
    private CryptocurrencyBitfinexService cryptocurrencyBitfinexService;

    @Autowired
    private CryptocurrencyBittrexService cryptocurrencyBittrexService;

    @Autowired
    private CryptocurrencyKrakenService cryptocurrencyKrakenService;

    @Autowired
    private CryptocurrencyAssetRepository cryptocurrencyAssetRepository;

    @Autowired
    private CryptocurrencyBinanceAveragePriceTickerRepository cryptocurrencyBinanceAveragePriceTickerRepository;

    @Autowired
    private CryptocurrencyBinanceExchangeInformationRepository cryptocurrencyBinanceExchangeInformationRepository;

    @Autowired
    private CryptocurrencyBitfinexSymbolDetailRepository cryptocurrencyBitfinexSymbolDetailRepository;

    @Autowired
    private CryptocurrencyBittrexCurrencyRepository cryptocurrencyBittrexCurrencyRepository;

    @Autowired
    private CryptocurrencyKrakenAssetPairRepository cryptocurrencyKrakenAssetPairRepository;


    public CryptocurrencyResponse initializeData() {

        CryptocurrencyResponse result = new CryptocurrencyResponse() {
            {
                setResponse("Failed to initialize cryptocurrency data");
                setResult(false);
            }
        };

        List<String> assetName = Arrays.asList(global.getSetting().getCryptocurrency().getAsset().getPrimary().replace("|", ",").split(","));
        result.setCryptocurrencyAssetList(cryptocurrencyAssetRepository.findInName(new HashSet(assetName)));

        result.setCryptocurrencyExchangerList(initializeExchangerInformation());

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        List<CryptocurrencyBinanceExchangeInformation> cryptocurrencyBinanceExchangeInformationList = cryptocurrencyBinanceExchangeInformationRepository.findByStatusSort(BinanceSymbolStatus.TRADING, Sort.by(sort));

        result.setCryptocurrencyBinanceExchangeInformationList(cryptocurrencyBinanceExchangeInformationList);

        List<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerList = cryptocurrencyBinanceAveragePriceTickerRepository.findAll();

        if(!cryptocurrencyBinanceAveragePriceTickerList.isEmpty()) {

            long dateDifference = Math.abs(new Date().getTime() - cryptocurrencyBinanceAveragePriceTickerList.get(0).getCreated().getTimestamp().getTime());

            if(TimeUnit.HOURS.convert(dateDifference, TimeUnit.MILLISECONDS) < 1) {

                result.setCryptocurrencyBinanceAveragePriceTickerList(cryptocurrencyBinanceAveragePriceTickerList);

            }

        }

        if(result.getCryptocurrencyBinanceAveragePriceTickerList() == null) {

            cryptocurrencyBinanceAveragePriceTickerRepository.deleteAll();

            CryptocurrencyBinanceResponse cryptocurrencyBinanceAveragePriceTicker = cryptocurrencyBinanceService.loadAveragePriceTicker("");

            if(cryptocurrencyBinanceAveragePriceTicker.isResult()) {

                ArrayList<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerArrayList = new ArrayList<>();

                for(CryptocurrencyBinanceAveragePriceTicker averagePriceTicker : cryptocurrencyBinanceAveragePriceTicker.getAveragePriceTickerList()) {

                    averagePriceTicker.setCreated(data.initializeTimestampReference("0", "System", null));
                    averagePriceTicker.setModified(data.initializeTimestampReference("0", "System", null));
                    cryptocurrencyBinanceAveragePriceTickerArrayList.add(averagePriceTicker);

                }

                cryptocurrencyBinanceAveragePriceTickerRepository.saveAll(cryptocurrencyBinanceAveragePriceTickerArrayList);

                result.setCryptocurrencyBinanceAveragePriceTickerList(cryptocurrencyBinanceAveragePriceTickerList);

            }

        }

        result.setResponse("Cryptocurrency data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public List<CryptocurrencyExchanger> initializeExchangerInformation() {

        List<CryptocurrencyExchanger> result = new ArrayList<>() {
            {
                add(new CryptocurrencyExchanger(
                        new CryptocurrencyExchangerFeeReference(
                                global.getApi().getBinance().getFee().getCommission(),
                                new CryptocurrencyExchangerFeeDepositReference(
                                        global.getApi().getBinance().getFee().getDeposit().getBtc(),
                                        global.getApi().getBinance().getFee().getDeposit().getEth(),
                                        global.getApi().getBinance().getFee().getDeposit().getEur()
                                ),
                                new CryptocurrencyExchangerFeeWithdrawalReference(
                                        global.getApi().getBinance().getFee().getWithdrawal().getBtc(),
                                        global.getApi().getBinance().getFee().getWithdrawal().getEth(),
                                        global.getApi().getBinance().getFee().getWithdrawal().getEur()
                                )
                        ),
                        Exchanger.Binance,
                        ExchangerStatus.Initialized
                ));
                add(new CryptocurrencyExchanger(
                        new CryptocurrencyExchangerFeeReference(
                                global.getApi().getBitfinex().getFee().getCommission(),
                                new CryptocurrencyExchangerFeeDepositReference(
                                        global.getApi().getBitfinex().getFee().getDeposit().getBtc(),
                                        global.getApi().getBitfinex().getFee().getDeposit().getEth(),
                                        global.getApi().getBitfinex().getFee().getDeposit().getEur()
                                ),
                                new CryptocurrencyExchangerFeeWithdrawalReference(
                                        global.getApi().getBitfinex().getFee().getWithdrawal().getBtc(),
                                        global.getApi().getBitfinex().getFee().getWithdrawal().getEth(),
                                        global.getApi().getBitfinex().getFee().getWithdrawal().getEur()
                                )
                        ),
                        Exchanger.Bitfinex,
                        ExchangerStatus.Initialized
                ));
                add(new CryptocurrencyExchanger(
                        new CryptocurrencyExchangerFeeReference(
                                global.getApi().getBittrex().getFee().getCommission(),
                                new CryptocurrencyExchangerFeeDepositReference(
                                        global.getApi().getBittrex().getFee().getDeposit().getBtc(),
                                        global.getApi().getBittrex().getFee().getDeposit().getEth(),
                                        global.getApi().getBittrex().getFee().getDeposit().getEur()
                                ),
                                new CryptocurrencyExchangerFeeWithdrawalReference(
                                        global.getApi().getBittrex().getFee().getWithdrawal().getBtc(),
                                        global.getApi().getBittrex().getFee().getWithdrawal().getEth(),
                                        global.getApi().getBittrex().getFee().getWithdrawal().getEur()
                                )
                        ),
                        Exchanger.Bittrex,
                        ExchangerStatus.Initialized
                ));
                add(new CryptocurrencyExchanger(
                        new CryptocurrencyExchangerFeeReference(
                                global.getApi().getKraken().getFee().getCommission(),
                                new CryptocurrencyExchangerFeeDepositReference(
                                        global.getApi().getKraken().getFee().getDeposit().getBtc(),
                                        global.getApi().getKraken().getFee().getDeposit().getEth(),
                                        global.getApi().getKraken().getFee().getDeposit().getEur()
                                ),
                                new CryptocurrencyExchangerFeeWithdrawalReference(
                                        global.getApi().getKraken().getFee().getWithdrawal().getBtc(),
                                        global.getApi().getKraken().getFee().getWithdrawal().getEth(),
                                        global.getApi().getKraken().getFee().getWithdrawal().getEur()
                                )
                        ),
                        Exchanger.Kraken,
                        ExchangerStatus.Initialized
                ));
            }
        };

        logger.info("Cryptocurrency exchanger information initialized");

        return result;

    }


    public BaseResponse initializeInformation() {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to initialize cryptocurrency information");
                setResult(false);
            }
        };

        CryptocurrencyBinanceResponse binanceExchangeInformation = cryptocurrencyBinanceService.loadExchangeInformation();

        CryptocurrencyBitfinexResponse bitfinexSymbolDetail = cryptocurrencyBitfinexService.loadSymbolDetail();

        CryptocurrencyBittrexResponse bittrexCurrency = cryptocurrencyBittrexService.loadCurrency();

        CryptocurrencyKrakenResponse krakenAssetPair = cryptocurrencyKrakenService.loadAssetPair();

        if(binanceExchangeInformation.isResult() && bitfinexSymbolDetail.isResult() && bittrexCurrency.isResult() && krakenAssetPair.isResult()) {

            cryptocurrencyAssetRepository.deleteAll();
            cryptocurrencyBinanceExchangeInformationRepository.deleteAll();
            cryptocurrencyBitfinexSymbolDetailRepository.deleteAll();
            cryptocurrencyBittrexCurrencyRepository.deleteAll();
            cryptocurrencyKrakenAssetPairRepository.deleteAll();

            BaseResponse saveExchangeInformation = saveAssetExchangeInformation(binanceExchangeInformation.getExchangeInformationList(), bitfinexSymbolDetail.getCryptocurrencyBitfinexSymbolDetailList(), bittrexCurrency.getCurrencyList(), krakenAssetPair.getCryptocurrencyKrakenAssetPairList());

            if(saveExchangeInformation.isResult()) {

                result.setResponse("Cryptocurrency information initialized");
                result.setResult(true);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyResponse loadExchangerList() {

        CryptocurrencyResponse result = new CryptocurrencyResponse() {
            {
                setCryptocurrencyExchangerList(new ArrayList<>());
                setResponse("Failed to load exchanger list");
                setResult(false);
            }
        };

        CryptocurrencyExchanger cryptocurrencyExchanger = new CryptocurrencyExchanger(
                new CryptocurrencyExchangerFeeReference(),
                Exchanger.Binance,
                ExchangerStatus.Uninitialized
        );

        if(cryptocurrencyBinanceExchangeInformationRepository.findAll().size() > 0) {

            cryptocurrencyExchanger.setStatus(ExchangerStatus.Initialized);

        }

        result.getCryptocurrencyExchangerList().add(cryptocurrencyExchanger);

        cryptocurrencyExchanger = new CryptocurrencyExchanger(
                new CryptocurrencyExchangerFeeReference(),
                Exchanger.Bitfinex,
                ExchangerStatus.Uninitialized
        );

        if(cryptocurrencyBitfinexSymbolDetailRepository.findAll().size() > 0) {

            cryptocurrencyExchanger.setStatus(ExchangerStatus.Initialized);

        }

        result.getCryptocurrencyExchangerList().add(cryptocurrencyExchanger);

        cryptocurrencyExchanger = new CryptocurrencyExchanger(
                new CryptocurrencyExchangerFeeReference(),
                Exchanger.Bittrex,
                ExchangerStatus.Uninitialized
        );

        if(cryptocurrencyBittrexCurrencyRepository.findAll().size() > 0) {

            cryptocurrencyExchanger.setStatus(ExchangerStatus.Initialized);

        }

        result.getCryptocurrencyExchangerList().add(cryptocurrencyExchanger);

        cryptocurrencyExchanger = new CryptocurrencyExchanger(
                new CryptocurrencyExchangerFeeReference(),
                Exchanger.Kraken,
                ExchangerStatus.Uninitialized
        );

        if(cryptocurrencyKrakenAssetPairRepository.findAll().size() > 0) {

            cryptocurrencyExchanger.setStatus(ExchangerStatus.Initialized);

        }

        result.getCryptocurrencyExchangerList().add(cryptocurrencyExchanger);

        result.setResponse("Exchanger list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CryptocurrencyResponse loadList(CryptocurrencyFilter filter) {

        CryptocurrencyResponse result = new CryptocurrencyResponse() {
            {
                setResponse("Failed to load cryptocurrency list");
                setResult(false);
            }
        };

        List<CryptocurrencyBinanceAveragePriceTicker> cryptocurrencyBinanceAveragePriceTickerList = cryptocurrencyBinanceAveragePriceTickerRepository.findAll();
        cryptocurrencyBinanceAveragePriceTickerList = cryptocurrencyBinanceAveragePriceTickerList.stream().filter(averagePriceTicker -> filter.getTradeInformationList().contains(averagePriceTicker.getSymbol())).collect(Collectors.toList());
        result.setCryptocurrencyBinanceAveragePriceTickerList(cryptocurrencyBinanceAveragePriceTickerList);

        result.setResponse("Cryptocurrency list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private BaseResponse saveAssetExchangeInformation(List<CryptocurrencyBinanceExchangeInformation> binanceExchangeInformation, List<CryptocurrencyBitfinexSymbolDetail> bitfinexSymbolDetail, List<CryptocurrencyBittrexCurrency> bittrexCurrency, Map<String, Object> krakenAssetPair) {

        BaseResponse result = new BaseResponse(
                "Failed to save asset exchange information",
                false
        );

        List<CryptocurrencyAsset> insertDataCryptocurrencyAsset = new ArrayList<>();

        for(CryptocurrencyBinanceExchangeInformation cryptocurrencyBinanceExchangeInformation : binanceExchangeInformation) {

            List<CryptocurrencyAsset> assetByName = insertDataCryptocurrencyAsset.stream().filter(asset -> asset.getName().equals(cryptocurrencyBinanceExchangeInformation.getBaseAsset())).collect(Collectors.toList());

            if(assetByName.isEmpty()) {

                insertDataCryptocurrencyAsset.add(new CryptocurrencyAsset() {
                    {
                        setName(cryptocurrencyBinanceExchangeInformation.getBaseAsset());
                        setPrecision(cryptocurrencyBinanceExchangeInformation.getBaseAssetPrecision());
                    }
                });

            }

        }

        cryptocurrencyAssetRepository.saveAll(insertDataCryptocurrencyAsset);
        cryptocurrencyBinanceExchangeInformationRepository.saveAll(binanceExchangeInformation);
        cryptocurrencyBitfinexSymbolDetailRepository.saveAll(bitfinexSymbolDetail);
        cryptocurrencyBittrexCurrencyRepository.saveAll(bittrexCurrency);

        List<CryptocurrencyKrakenAssetPair> insertDataCryptocurrencyKrakenAssetPair = new ArrayList<>();

        for(Map.Entry<String, Object> krakenAssetPairMap : krakenAssetPair.entrySet()) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                CryptocurrencyKrakenAssetPair cryptocurrencyKrakenAssetPair = objectMapper.convertValue(krakenAssetPairMap.getValue(), new TypeReference<>() {
                });
                cryptocurrencyKrakenAssetPair.setPairname(krakenAssetPairMap.getKey());
                insertDataCryptocurrencyKrakenAssetPair.add(cryptocurrencyKrakenAssetPair);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        cryptocurrencyKrakenAssetPairRepository.saveAll(insertDataCryptocurrencyKrakenAssetPair);

        result.setResponse("Cryptocurrency asset exchange information saved");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


}
