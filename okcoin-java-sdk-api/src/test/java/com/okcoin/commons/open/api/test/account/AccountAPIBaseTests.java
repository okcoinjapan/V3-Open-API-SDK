package com.okcoin.commons.open.api.test.account;

import com.okcoin.commons.open.api.config.APIConfiguration;
import com.okcoin.commons.open.api.enums.I18nEnum;
import com.okcoin.commons.open.api.test.BaseTests;

/**
 * Account api basetests
 *
 * @author hucj
 * @version 1.0.0
 * @date 2018/7/04 18:23
 */
public class AccountAPIBaseTests extends BaseTests {

    public APIConfiguration config() {
        APIConfiguration config = new APIConfiguration();

        config.setEndpoint("https://dev-1-exchange.okdev.work/");

        // 以下にapiKey,apiSecret,Passphraseを設定してください。
        config.setApiKey("25e0ef38-db71-4706-8142-93d1713f71bc");
        config.setSecretKey("D97375D425C45561DAB93DBF4578AF5C");
        config.setPassphrase("123456");

        config.setPrint(true);
        config.setI18n(I18nEnum.ENGLISH);

        return config;
    }


}
