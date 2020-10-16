package com.preloode.panel.model.cryptocurrency.kraken;

import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyKrakenAssetPair extends Base {


    private String pairname;

    private String altname;

    private String wsname;

    private String aclass_base;

    private String base;

    private String aclass_quote;

    private String quote;

    private String lot;

    private BigInteger pair_decimals;

    private BigInteger lot_decimals;

    private BigInteger lot_multiplier;

    private List<BigInteger> leverage_buy;

    private List<BigInteger> leverage_sell;

    private List<List<BigDecimal>> fees;

    private List<List<BigDecimal>> fees_maker;

    private String fee_volume_currency;

    private BigInteger margin_call;

    private BigInteger margin_stop;

    private BigDecimal ordermin;


    public CryptocurrencyKrakenAssetPair() {


    }


    public CryptocurrencyKrakenAssetPair(String id, TimestampReference created, TimestampReference modified, String pairname, String altname, String wsname, String aclass_base, String base, String aclass_quote, String quote, String lot, BigInteger pair_decimals, BigInteger lot_decimals, BigInteger lot_multiplier, List<BigInteger> leverage_buy, List<BigInteger> leverage_sell, List<List<BigDecimal>> fees, List<List<BigDecimal>> fees_maker, String fee_volume_currency, BigInteger margin_call, BigInteger margin_stop, BigDecimal ordermin) {

        super(id, created, modified);
        this.pairname = pairname;
        this.altname = altname;
        this.wsname = wsname;
        this.aclass_base = aclass_base;
        this.base = base;
        this.aclass_quote = aclass_quote;
        this.quote = quote;
        this.lot = lot;
        this.pair_decimals = pair_decimals;
        this.lot_decimals = lot_decimals;
        this.lot_multiplier = lot_multiplier;
        this.leverage_buy = leverage_buy;
        this.leverage_sell = leverage_sell;
        this.fees = fees;
        this.fees_maker = fees_maker;
        this.fee_volume_currency = fee_volume_currency;
        this.margin_call = margin_call;
        this.margin_stop = margin_stop;
        this.ordermin = ordermin;

    }


    public String getPairname() {

        return pairname;

    }


    public void setPairname(String pairname) {

        this.pairname = pairname;

    }


    public String getAltname() {

        return altname;

    }


    public void setAltname(String altname) {

        this.altname = altname;

    }


    public String getWsname() {

        return wsname;

    }


    public void setWsname(String wsname) {

        this.wsname = wsname;

    }


    public String getAclass_base() {

        return aclass_base;

    }


    public void setAclass_base(String aclass_base) {

        this.aclass_base = aclass_base;

    }


    public String getBase() {

        return base;

    }


    public void setBase(String base) {

        this.base = base;

    }


    public String getAclass_quote() {

        return aclass_quote;

    }


    public void setAclass_quote(String aclass_quote) {

        this.aclass_quote = aclass_quote;

    }


    public String getQuote() {

        return quote;

    }


    public void setQuote(String quote) {

        this.quote = quote;

    }


    public String getLot() {

        return lot;

    }


    public void setLot(String lot) {

        this.lot = lot;

    }


    public BigInteger getPair_decimals() {

        return pair_decimals;

    }


    public void setPair_decimals(BigInteger pair_decimals) {

        this.pair_decimals = pair_decimals;

    }


    public BigInteger getLot_decimals() {

        return lot_decimals;

    }


    public void setLot_decimals(BigInteger lot_decimals) {

        this.lot_decimals = lot_decimals;

    }


    public BigInteger getLot_multiplier() {

        return lot_multiplier;

    }


    public void setLot_multiplier(BigInteger lot_multiplier) {

        this.lot_multiplier = lot_multiplier;

    }


    public List<BigInteger> getLeverage_buy() {

        return leverage_buy;

    }


    public void setLeverage_buy(List<BigInteger> leverage_buy) {

        this.leverage_buy = leverage_buy;

    }


    public List<BigInteger> getLeverage_sell() {

        return leverage_sell;

    }


    public void setLeverage_sell(List<BigInteger> leverage_sell) {

        this.leverage_sell = leverage_sell;

    }


    public List<List<BigDecimal>> getFees() {

        return fees;

    }


    public void setFees(List<List<BigDecimal>> fees) {

        this.fees = fees;

    }


    public List<List<BigDecimal>> getFees_maker() {

        return fees_maker;

    }


    public void setFees_maker(List<List<BigDecimal>> fees_maker) {

        this.fees_maker = fees_maker;

    }


    public String getFee_volume_currency() {

        return fee_volume_currency;

    }


    public void setFee_volume_currency(String fee_volume_currency) {

        this.fee_volume_currency = fee_volume_currency;

    }


    public BigInteger getMargin_call() {

        return margin_call;

    }


    public void setMargin_call(BigInteger margin_call) {

        this.margin_call = margin_call;

    }


    public BigInteger getMargin_stop() {

        return margin_stop;

    }


    public void setMargin_stop(BigInteger margin_stop) {

        this.margin_stop = margin_stop;

    }


    public BigDecimal getOrdermin() {

        return ordermin;

    }


    public void setOrdermin(BigDecimal ordermin) {

        this.ordermin = ordermin;

    }


}
