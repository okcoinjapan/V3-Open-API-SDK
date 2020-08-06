package com.okcoin.commons.open.api.bean.other;

public interface OrderBookItem<T> {
    String getPrice();

    T getSize();
}
