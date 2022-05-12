package com.okcoin.commons.open.api.bean.account.result;

import java.math.BigDecimal;

public class WithdrawFee {

    private BigDecimal min_fee;

    private BigDecimal max_fee;
    private String chain;
    private String currency;

    public BigDecimal getMin_fee() {
        return min_fee;
    }

    public void setMin_fee(BigDecimal min_fee) {
        this.min_fee = min_fee;
    }

    public BigDecimal getMax_fee() {
        return max_fee;
    }

    public void setMax_fee(BigDecimal max_fee) {
        this.max_fee = max_fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    @Override
    public String toString() {
        return "WithdrawFee{" +
                "min_fee=" + min_fee +
                ", max_fee=" + max_fee +
                ", chain=" + chain +
                ", currency='" + currency + '\'' +
                '}';
    }
}
