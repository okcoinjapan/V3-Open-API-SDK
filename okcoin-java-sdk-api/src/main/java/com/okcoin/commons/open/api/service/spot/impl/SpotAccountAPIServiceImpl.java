package com.okcoin.commons.open.api.service.spot.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.open.api.bean.spot.result.Account;
import com.okcoin.commons.open.api.bean.spot.result.ServerTimeDto;
import com.okcoin.commons.open.api.client.APIClient;
import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.service.spot.SpotAccountAPIService;

import java.util.List;
import java.util.Map;

public class SpotAccountAPIServiceImpl implements SpotAccountAPIService {

    private final APIClient client;
    private final SpotAccountAPI api;

    public SpotAccountAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(SpotAccountAPI.class);
    }

    @Override
    public ServerTimeDto time() {
        return this.client.executeSync(this.api.time());
    }

    @Override
    public Map<String, Object> getMiningData() {
        return this.client.executeSync(this.api.getMiningdata());
    }

    @Override
    public List<Account> getAccounts() {
        return this.client.executeSync(this.api.getAccounts());
    }

    @Override
    public JSONArray getLedgersByCurrency(final String currency, final String before, final String after, final String limit,String type) {
        return this.client.executeSync(this.api.getLedgersByCurrency(currency, before, after, limit,type));
    }

    @Override
    public Account getAccountByCurrency(final String currency) {
        return this.client.executeSync(this.api.getAccountByCurrency(currency));
    }

    @Override
    public JSONArray getTradeFee() {
        return this.client.executeSync(this.api.getTradeFee());
    }

    @Override
    public JSONObject getTradeFeeByInstrumentId(String instrumentId) {
        return this.client.executeSync(this.api.getTradeFeeByInstrumentId(instrumentId));
    }

}
