package com.okcoin.commons.open.api.test.general;

import com.okcoin.commons.open.api.bean.general.result.ServerTime;
import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.service.general.GeneralAPIService;
import com.okcoin.commons.open.api.service.general.impl.GeneralAPIServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * General API Tests
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/12 14:34
 */
public class GeneralAPITests extends GeneralAPIBaseTests {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralAPITests.class);

    private GeneralAPIService generalAPIService;
    APIConfiguration config = new APIConfiguration();

    @Before
    public void before() {
        config = config();
        generalAPIService = new GeneralAPIServiceImpl(config);
    }


    @Test
    public void testServerTime() {
         ServerTime time = generalAPIService.getServerTime();
        toResultString(LOG, "ServerTime", time);
    }

}
