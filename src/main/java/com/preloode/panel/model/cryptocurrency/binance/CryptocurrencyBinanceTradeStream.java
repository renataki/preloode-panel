package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


public class CryptocurrencyBinanceTradeStream {


    private String e;

    private Date E;

    private String s;

    private BigInteger t;

    private BigDecimal p;

    private BigDecimal q;

    private BigInteger b;

    private BigInteger a;

    private Date T;

    private boolean m;

    private boolean M;


    public CryptocurrencyBinanceTradeStream() {


    }


    public CryptocurrencyBinanceTradeStream(String e, Date E, String s, BigInteger t, BigDecimal p, BigDecimal q, BigInteger b, BigInteger a, Date T, boolean m, boolean M) {

        this.e = e;
        this.E = E;
        this.s = s;
        this.t = t;
        this.p = p;
        this.q = q;
        this.b = b;
        this.a = a;
        this.T = T;
        this.m = m;
        this.M = M;

    }


    public String gete() {

        return e;

    }


    public void sete(String e) {

        this.e = e;

    }


    public Date getE() {

        return E;

    }


    public void setE(Date e) {

        this.E = E;

    }


    public String getS() {

        return s;

    }


    public void setS(String s) {

        this.s = s;

    }


    public BigInteger gett() {

        return t;

    }


    public void sett(BigInteger t) {

        this.t = t;

    }


    public BigDecimal getP() {

        return p;

    }


    public void setP(BigDecimal p) {

        this.p = p;

    }


    public BigDecimal getQ() {

        return q;

    }


    public void setQ(BigDecimal q) {

        this.q = q;

    }


    public BigInteger getB() {

        return b;

    }


    public void setB(BigInteger b) {

        this.b = b;

    }


    public BigInteger getA() {

        return a;

    }


    public void setA(BigInteger a) {

        this.a = a;

    }


    public Date getT() {

        return T;

    }


    public void setT(Date T) {

        this.T = T;

    }


    public boolean ism() {

        return m;

    }


    public void setm(boolean m) {

        this.m = m;

    }


    public boolean isM() {

        return M;

    }


    public void setM(boolean M) {

        this.M = M;

    }


}
