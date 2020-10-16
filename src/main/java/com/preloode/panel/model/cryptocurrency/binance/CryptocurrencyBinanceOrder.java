package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderSide;
import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceOrderType;

import java.math.BigDecimal;


public class CryptocurrencyBinanceOrder {


    private BigDecimal quantity;

    private BigDecimal quoteOrderQuantity;

    private String symbol;

    private BinanceOrderSide side;

    private BinanceOrderType type;


    public CryptocurrencyBinanceOrder() {


    }


    public CryptocurrencyBinanceOrder(BigDecimal quantity, BigDecimal quoteOrderQuantity, String symbol, BinanceOrderSide side, BinanceOrderType type) {

        this.quantity = quantity;
        this.quoteOrderQuantity = quoteOrderQuantity;
        this.symbol = symbol;
        this.side = side;
        this.type = type;

    }


    public BigDecimal getQuantity() {

        return quantity;

    }


    public void setQuantity(BigDecimal quantity) {

        this.quantity = quantity;

    }


    public BigDecimal getQuoteOrderQuantity() {

        return quoteOrderQuantity;

    }


    public void setQuoteOrderQuantity(BigDecimal quoteOrderQuantity) {

        this.quoteOrderQuantity = quoteOrderQuantity;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BinanceOrderSide getSide() {

        return side;

    }


    public void setSide(BinanceOrderSide side) {

        this.side = side;

    }


    public BinanceOrderType getType() {

        return type;

    }


    public void setType(BinanceOrderType type) {

        this.type = type;

    }


}
