package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderSide;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderStatus;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderType;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceTimeInforce;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


public class CryptocurrencyBinanceTradeOrder {


    private String symbol;

    private BigInteger orderId;

    private BigInteger orderListId;

    private String clientOrderId;

    private BigDecimal price;

    private BigDecimal origQty;

    private BigDecimal executedQty;

    private BigDecimal cummulativeQuoteQty;

    private BinanceOrderStatus status;

    private BinanceTimeInforce timeInForce;

    private BinanceOrderType type;

    private BinanceOrderSide side;

    private BigDecimal stopPrice;

    private BigDecimal icebergQty;

    private Date time;

    private Date updateTime;

    private boolean isWorking;

    private BigDecimal origQuoteOrderQty;


    public CryptocurrencyBinanceTradeOrder() {


    }


    public CryptocurrencyBinanceTradeOrder(String symbol, BigInteger orderId, BigInteger orderListId, String clientOrderId, BigDecimal price, BigDecimal origQty, BigDecimal executedQty, BigDecimal cummulativeQuoteQty, BinanceOrderStatus status, BinanceTimeInforce timeInForce, BinanceOrderType type, BinanceOrderSide side, BigDecimal stopPrice, BigDecimal icebergQty, Date time, Date updateTime, boolean isWorking, BigDecimal origQuoteOrderQty) {

        this.symbol = symbol;
        this.orderId = orderId;
        this.orderListId = orderListId;
        this.clientOrderId = clientOrderId;
        this.price = price;
        this.origQty = origQty;
        this.executedQty = executedQty;
        this.cummulativeQuoteQty = cummulativeQuoteQty;
        this.status = status;
        this.timeInForce = timeInForce;
        this.type = type;
        this.side = side;
        this.stopPrice = stopPrice;
        this.icebergQty = icebergQty;
        this.time = time;
        this.updateTime = updateTime;
        this.isWorking = isWorking;
        this.origQuoteOrderQty = origQuoteOrderQty;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BigInteger getOrderId() {

        return orderId;

    }


    public void setOrderId(BigInteger orderId) {

        this.orderId = orderId;

    }


    public BigInteger getOrderListId() {

        return orderListId;

    }


    public void setOrderListId(BigInteger orderListId) {

        this.orderListId = orderListId;

    }


    public String getClientOrderId() {

        return clientOrderId;

    }


    public void setClientOrderId(String clientOrderId) {

        this.clientOrderId = clientOrderId;

    }


    public BigDecimal getPrice() {

        return price;

    }


    public void setPrice(BigDecimal price) {

        this.price = price;

    }


    public BigDecimal getOrigQty() {

        return origQty;

    }


    public void setOrigQty(BigDecimal origQty) {

        this.origQty = origQty;

    }


    public BigDecimal getExecutedQty() {

        return executedQty;

    }


    public void setExecutedQty(BigDecimal executedQty) {

        this.executedQty = executedQty;

    }


    public BigDecimal getCummulativeQuoteQty() {

        return cummulativeQuoteQty;

    }


    public void setCummulativeQuoteQty(BigDecimal cummulativeQuoteQty) {

        this.cummulativeQuoteQty = cummulativeQuoteQty;

    }


    public BinanceOrderStatus getStatus() {

        return status;

    }


    public void setStatus(BinanceOrderStatus status) {

        this.status = status;

    }


    public BinanceTimeInforce getTimeInForce() {

        return timeInForce;

    }


    public void setTimeInForce(BinanceTimeInforce timeInForce) {

        this.timeInForce = timeInForce;

    }


    public BinanceOrderType getType() {

        return type;

    }


    public void setType(BinanceOrderType type) {

        this.type = type;

    }


    public BinanceOrderSide getSide() {

        return side;

    }


    public void setSide(BinanceOrderSide side) {

        this.side = side;

    }


    public BigDecimal getStopPrice() {

        return stopPrice;

    }


    public void setStopPrice(BigDecimal stopPrice) {

        this.stopPrice = stopPrice;

    }


    public BigDecimal getIcebergQty() {

        return icebergQty;

    }


    public void setIcebergQty(BigDecimal icebergQty) {

        this.icebergQty = icebergQty;

    }


    public Date getTime() {

        return time;

    }


    public void setTime(Date time) {

        this.time = time;

    }


    public Date getUpdateTime() {

        return updateTime;

    }


    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;

    }


    public boolean isIsWorking() {

        return isWorking;

    }


    public void setIsWorking(boolean isWorking) {

        this.isWorking = isWorking;
    }


    public BigDecimal getOrigQuoteOrderQty() {

        return origQuoteOrderQty;
    }


    public void setOrigQuoteOrderQty(BigDecimal origQuoteOrderQty) {

        this.origQuoteOrderQty = origQuoteOrderQty;
    }


}
