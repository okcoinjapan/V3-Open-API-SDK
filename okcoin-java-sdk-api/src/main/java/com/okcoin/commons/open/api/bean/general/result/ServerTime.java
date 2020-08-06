package com.okcoin.commons.open.api.bean.general.result;

/**
 * Time of the server running okcoin's REST API.
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/8 20:58
 */
public class ServerTime {
    private String iso;
    private String epoch;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }
}
