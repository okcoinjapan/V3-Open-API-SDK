package com.okcoin.commons.open.api.test.spot;

import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.enums.I18nEnum;
import com.okcoin.commons.open.api.test.BaseTests;

public class SpotAPIBaseTests extends BaseTests {

    public APIConfiguration config() {
        final APIConfiguration config = new APIConfiguration();

        config.setEndpoint("https://www.okcoin.jp/");

        // 以下にapiKey,apiSecret,Passphraseを設定してください。
        config.setApiKey("");
        config.setSecretKey("");
        config.setPassphrase("");

        config.setPrint(true);
        config.setI18n(I18nEnum.ENGLISH);

        return config;
    }

}
