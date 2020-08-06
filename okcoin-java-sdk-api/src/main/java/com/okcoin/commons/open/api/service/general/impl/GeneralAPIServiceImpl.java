package com.okcoin.commons.open.api.service.general.impl;

import com.okcoin.commons.open.api.bean.general.result.ServerTime;
import com.okcoin.commons.open.api.client.APIClient;
import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.service.general.GeneralAPIService;

/**
 * General api
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 10/03/2018 19:28
 */
public class GeneralAPIServiceImpl implements GeneralAPIService {

    private APIClient client;
    private GeneralAPI api;

    public GeneralAPIServiceImpl(APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = client.createService(GeneralAPI.class);
    }

    @Override
    public ServerTime getServerTime() {
        return this.client.executeSync(this.api.getServerTime());
    }
}
