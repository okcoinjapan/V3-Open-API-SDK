package com.okcoin.commons.open.api.service.general;


import com.okcoin.commons.open.api.bean.general.result.ServerTime;

/**
 * OKCoin Japan general api
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/9 16:06
 */
public interface GeneralAPIService {
    /**
     * Time of the server running OKCoin's REST API.
     */
    ServerTime getServerTime();

}
