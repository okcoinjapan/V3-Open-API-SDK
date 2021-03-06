package com.okcoin.commons.open.api.enums;

/**
 * internationalization
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/8 16:20
 */
public enum I18nEnum {
    ENGLISH("en_US"),
    SIMPLIFIED_CHINESE("zh_CN");

    private String i18n;

    I18nEnum(String i18n) {
        this.i18n = i18n;
    }

    public String i18n() {
        return i18n;
    }
}
