package com.okcoin.commons.open.api.service.spot;

import com.alibaba.fastjson.JSONArray;
import com.okcoin.commons.open.api.bean.spot.result.Book;
import com.okcoin.commons.open.api.bean.spot.result.Product;
import com.okcoin.commons.open.api.bean.spot.result.Ticker;
import com.okcoin.commons.open.api.bean.spot.result.Trade;

import java.util.List;

public interface SpotProductAPIService {

    /**
     * 单个币对行情 / Single currency pair market
     *
     * @param instrument_id
     * @return
     */
    Ticker getTickerByProductId(String instrument_id);

    /**
     *
     * 行情列表 / Quote list
     *
     * @return
     */
    //List<Ticker> getTickers();
    String getTickers();

    List<Ticker> getTickers1();

    /**
     * @param instrument_id
     * @param size
     * @param depth
     * @return
     */
    Book bookProductsByProductId(String instrument_id, String size, String depth);

    /**
     * 币对列表 / List of currency pairs
     *
     * @return
     */
    List<Product> getProducts();

    /**
     * 交易列表 / Transaction list
     *
     * @param instrument_id
     * @param limit
     * @return
     */
    List<Trade> getTrades(String instrument_id, String limit);

    /**
     * @param instrument_id
     * @param granularity
     * @param start
     * @param end
     * @return
     */
    JSONArray getCandles(String instrument_id, String granularity, String start, String end);

    List<String[]> getCandles_1(String product, String granularity, String start, String end);





}
