package com.okcoin.commons.open.api.test.general;

import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.enums.I18nEnum;
import com.okcoin.commons.open.api.test.BaseTests;

public class GeneralAPIBaseTests extends BaseTests {
    public APIConfiguration config() {
        APIConfiguration config = new APIConfiguration();

        config.setEndpoint("https://www.okcoin.jp/");
        config.setApiKey("");
        config.setSecretKey("");
        config.setPassphrase("");

        config.setPrint(true);
        config.setI18n(I18nEnum.ENGLISH);
        return config;
    }
}
