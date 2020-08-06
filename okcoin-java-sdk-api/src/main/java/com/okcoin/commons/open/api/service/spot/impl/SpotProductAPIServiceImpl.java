package com.okcoin.commons.open.api.service.spot.impl;

import com.alibaba.fastjson.JSONArray;
import com.okcoin.commons.open.api.bean.spot.result.Book;
import com.okcoin.commons.open.api.bean.spot.result.Product;
import com.okcoin.commons.open.api.bean.spot.result.Ticker;
import com.okcoin.commons.open.api.bean.spot.result.Trade;
import com.okcoin.commons.open.api.client.APIClient;
import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.service.spot.SpotProductAPIService;

import java.util.List;

/**
 * 公共数据相关接口 / Public data related interface
 *
 **/
public class SpotProductAPIServiceImpl implements SpotProductAPIService {

    private final APIClient client;
    private final SpotProductAPI spotProductAPI;

    public SpotProductAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.spotProductAPI = this.client.createService(SpotProductAPI.class);
    }


    @Override
    public Ticker getTickerByProductId(final String instrument_id) {
        return this.client.executeSync(this.spotProductAPI.getTickerByProductId(instrument_id));
    }

    @Override
    public List<Ticker> getTickers1() {
        return this.client.executeSync(this.spotProductAPI.getTickers1());
    }
    @Override
    public String getTickers() {
        return this.client.executeSync(this.spotProductAPI.getTickers());
    }

    @Override
    public Book bookProductsByProductId(final String instrument_id, final String size, final  String depth) {
        return this.client.executeSync(this.spotProductAPI.bookProductsByProductId(instrument_id, size, depth));
    }

    @Override
    public List<Product> getProducts() {
        return this.client.executeSync(this.spotProductAPI.getProducts());
    }

    @Override
    public List<Trade> getTrades(final String instrument_id, final String limit) {
        return this.client.executeSync(this.spotProductAPI.getTrades(instrument_id,limit));
    }

    @Override
    public JSONArray getCandles(final String instrument_id, final String granularity, final String start, final String end) {
        return this.client.executeSync(this.spotProductAPI.getCandles(instrument_id, granularity, start, end));
    }

    @Override
    public List<String[]> getCandles_1(final String product, final String granularity, final String start, final String end) {
        return this.client.executeSync(this.spotProductAPI.getCandles_1(product, granularity, start, end));
    }





}
