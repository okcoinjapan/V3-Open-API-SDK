package com.okcoin.commons.open.api.bean.spot.result;

public class Account {

    private String currency;
    private String balance;
    private String available;
    private String hold;

    public String getHold() {
        return this.hold;
    }

    public void setHold(final String hold) {
        this.hold = hold;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public String getAvailable() {
        return this.available;
    }

    public void setAvailable(final String available) {
        this.available = available;
    }


    @Override
    public String toString() {
        return "Account{" +
                "currency='" + currency + '\'' +
                ", balance='" + balance + '\'' +
                ", available='" + available + '\'' +
                ", hold='" + hold + '\'' +
                '}';
    }
}
