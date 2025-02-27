package com.okcoin.commons.open.api.service.spot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.open.api.bean.spot.result.Account;
import com.okcoin.commons.open.api.bean.spot.result.ServerTimeDto;

import java.util.List;
import java.util.Map;

/**
 * 币币资产相关接口 / Coin asset related interface
 */
public interface SpotAccountAPIService {

    /**
     * 系统时间 / system time
     *
     * @return
     */
    ServerTimeDto time();

    /**
     * 挖矿相关数据 / Mining related data
     *
     * @return
     */
    Map<String, Object> getMiningData();


    /**
     * 账户资产列表 / Account asset list
     *
     * @return
     */
    List<Account> getAccounts();

    /**
     * 币币账单流水 / Currency bill flow
     * @param currency
     * @param before
     * @param after
     * @param limit
     * @param type
     * @param
     * @return
     */
    JSONArray getLedgersByCurrency(String currency, String before, String after, String limit,String type);

    /**
     * 单币资产 / Single currency asset
     *
     * @param currency
     * @return
     */
    Account getAccountByCurrency(final String currency);

    JSONArray getTradeFee();

    JSONObject getTradeFeeByInstrumentId(String instrumentId);


}
