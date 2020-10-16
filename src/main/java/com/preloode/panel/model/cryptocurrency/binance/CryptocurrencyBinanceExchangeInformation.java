package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceAccountType;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderType;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceSymbolStatus;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyBinanceExchangeInformation extends Base {


    @Indexed
    private String symbol;

    private List<CryptocurrencyBinanceRateLimiterReference> rateLimits;

    @Indexed
    private BinanceSymbolStatus status;

    @Indexed
    private String baseAsset;

    @Indexed
    private BigInteger baseAssetPrecision;

    @Indexed
    private BigInteger baseCommissionPrecision;

    @Indexed
    private String quoteAsset;

    @Indexed
    private BigInteger quotePrecision;

    @Indexed
    private BigInteger quoteAssetPrecision;

    @Indexed
    private BigInteger quoteCommissionPrecision;

    @Indexed
    private boolean quoteOrderQtyMarketAllowed;

    private List<BinanceOrderType> orderTypes;

    @Indexed
    private boolean icebergAllowed;

    @Indexed
    private boolean ocoAllowed;

    @Indexed
    private boolean isSpotTradingAllowed;

    @Indexed
    private boolean isMarginTradingAllowed;

    private List<CryptocurrencyBinanceFilterReference> filters;

    private List<BinanceAccountType> permissions;


    public CryptocurrencyBinanceExchangeInformation() {


    }


    public CryptocurrencyBinanceExchangeInformation(String id, TimestampReference created, TimestampReference modified, String symbol, List<CryptocurrencyBinanceRateLimiterReference> rateLimits, BinanceSymbolStatus status, String baseAsset, BigInteger baseAssetPrecision, BigInteger baseCommissionPrecision, String quoteAsset, BigInteger quotePrecision, BigInteger quoteAssetPrecision, BigInteger quoteCommissionPrecision, boolean quoteOrderQtyMarketAllowed, List<BinanceOrderType> orderTypes, boolean icebergAllowed, boolean ocoAllowed, boolean isSpotTradingAllowed, boolean isMarginTradingAllowed, List<CryptocurrencyBinanceFilterReference> filters, List<BinanceAccountType> permissions) {

        super(id, created, modified);
        this.symbol = symbol;
        this.rateLimits = rateLimits;
        this.status = status;
        this.baseAsset = baseAsset;
        this.baseAssetPrecision = baseAssetPrecision;
        this.baseCommissionPrecision = baseCommissionPrecision;
        this.quoteAsset = quoteAsset;
        this.quotePrecision = quotePrecision;
        this.quoteAssetPrecision = quoteAssetPrecision;
        this.quoteCommissionPrecision = quoteCommissionPrecision;
        this.quoteOrderQtyMarketAllowed = quoteOrderQtyMarketAllowed;
        this.orderTypes = orderTypes;
        this.icebergAllowed = icebergAllowed;
        this.ocoAllowed = ocoAllowed;
        this.isSpotTradingAllowed = isSpotTradingAllowed;
        this.isMarginTradingAllowed = isMarginTradingAllowed;
        this.filters = filters;
        this.permissions = permissions;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public List<CryptocurrencyBinanceRateLimiterReference> getRateLimits() {

        return rateLimits;

    }


    public void setRateLimits(List<CryptocurrencyBinanceRateLimiterReference> rateLimits) {

        this.rateLimits = rateLimits;

    }


    public BinanceSymbolStatus getStatus() {

        return status;

    }


    public void setStatus(BinanceSymbolStatus status) {

        this.status = status;

    }


    public String getBaseAsset() {

        return baseAsset;

    }


    public void setBaseAsset(String baseAsset) {

        this.baseAsset = baseAsset;

    }


    public BigInteger getBaseAssetPrecision() {

        return baseAssetPrecision;

    }


    public void setBaseAssetPrecision(BigInteger baseAssetPrecision) {

        this.baseAssetPrecision = baseAssetPrecision;

    }


    public BigInteger getBaseCommissionPrecision() {

        return baseCommissionPrecision;

    }


    public void setBaseCommissionPrecision(BigInteger baseCommissionPrecision) {

        this.baseCommissionPrecision = baseCommissionPrecision;

    }


    public String getQuoteAsset() {

        return quoteAsset;

    }


    public void setQuoteAsset(String quoteAsset) {

        this.quoteAsset = quoteAsset;

    }


    public BigInteger getQuotePrecision() {

        return quotePrecision;

    }


    public void setQuotePrecision(BigInteger quotePrecision) {

        this.quotePrecision = quotePrecision;

    }


    public BigInteger getQuoteAssetPrecision() {

        return quoteAssetPrecision;

    }


    public void setQuoteAssetPrecision(BigInteger quoteAssetPrecision) {

        this.quoteAssetPrecision = quoteAssetPrecision;

    }


    public BigInteger getQuoteCommissionPrecision() {

        return quoteCommissionPrecision;

    }


    public void setQuoteCommissionPrecision(BigInteger quoteCommissionPrecision) {

        this.quoteCommissionPrecision = quoteCommissionPrecision;

    }


    public boolean isQuoteOrderQtyMarketAllowed() {

        return quoteOrderQtyMarketAllowed;

    }


    public void setQuoteOrderQtyMarketAllowed(boolean quoteOrderQtyMarketAllowed) {

        this.quoteOrderQtyMarketAllowed = quoteOrderQtyMarketAllowed;

    }


    public List<BinanceOrderType> getOrderTypes() {

        return orderTypes;

    }


    public void setOrderTypes(List<BinanceOrderType> orderTypes) {

        this.orderTypes = orderTypes;

    }


    public boolean isIcebergAllowed() {

        return icebergAllowed;

    }


    public void setIcebergAllowed(boolean icebergAllowed) {

        this.icebergAllowed = icebergAllowed;

    }


    public boolean isOcoAllowed() {

        return ocoAllowed;

    }


    public void setOcoAllowed(boolean ocoAllowed) {

        this.ocoAllowed = ocoAllowed;

    }


    public boolean isIsSpotTradingAllowed() {

        return isSpotTradingAllowed;

    }


    public void setIsSpotTradingAllowed(boolean isSpotTradingAllowed) {

        isSpotTradingAllowed = isSpotTradingAllowed;

    }


    public boolean isIsMarginTradingAllowed() {

        return isMarginTradingAllowed;

    }


    public void setIsMarginTradingAllowed(boolean isMarginTradingAllowed) {

        isMarginTradingAllowed = isMarginTradingAllowed;

    }


    public List<CryptocurrencyBinanceFilterReference> getFilters() {

        return filters;

    }


    public void setFilters(List<CryptocurrencyBinanceFilterReference> filters) {

        this.filters = filters;

    }


    public List<BinanceAccountType> getPermissions() {

        return permissions;

    }


    public void setPermissions(List<BinanceAccountType> permissions) {

        this.permissions = permissions;

    }


}
