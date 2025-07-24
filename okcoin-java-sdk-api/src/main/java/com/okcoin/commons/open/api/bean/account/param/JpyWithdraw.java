package com.okcoin.commons.open.api.bean.account.param;

public class JpyWithdraw {
    private String amount;

    private String trade_pwd;

    private String bank_card_id;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTrade_pwd() {
        return trade_pwd;
    }

    public void setTrade_pwd(String trade_pwd) {
        this.trade_pwd = trade_pwd;
    }

    public String getBank_card_id() {
        return bank_card_id;
    }

    public void setBank_card_id(String bank_card_id) {
        this.bank_card_id = bank_card_id;
    }
}
