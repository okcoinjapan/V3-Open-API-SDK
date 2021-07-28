package com.okcoin.commons.open.api.bean.account.param;

public class Withdraw {
    private String amount;

    private String currency;

    private String destination;

    private String to_address;

    private String trade_pwd;

    private String fee;

    private String reason;

    private String usage_agreement;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getReason() { return reason; }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUsage_agreement() { return usage_agreement; }

    public void setUsage_agreement(String usage_agreement) { this.usage_agreement = usage_agreement; }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrade_pwd() {
        return trade_pwd;
    }

    public void setTrade_pwd(String trade_pwd) {
        this.trade_pwd = trade_pwd;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

}
