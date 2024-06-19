package com.okcoin.commons.open.api.bean.spot.param;

import java.util.List;

public class OrderAlgoParam {
    private String instrument_id;
    private String mode;
    private String order_type;
    private String size;
    private String side;
    private String tp_trigger_price;
    private String tp_price;
    private String tp_trigger_type;
    private String sl_trigger_type;
    private String sl_trigger_price;
    private String sl_price;

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getTp_trigger_price() {
        return tp_trigger_price;
    }

    public void setTp_trigger_price(String tp_trigger_price) {
        this.tp_trigger_price = tp_trigger_price;
    }

    public String getTp_price() {
        return tp_price;
    }

    public void setTp_price(String tp_price) {
        this.tp_price = tp_price;
    }

    public String getTp_trigger_type() {
        return tp_trigger_type;
    }

    public void setTp_trigger_type(String tp_trigger_type) {
        this.tp_trigger_type = tp_trigger_type;
    }

    public String getSl_trigger_type() {
        return sl_trigger_type;
    }

    public void setSl_trigger_type(String sl_trigger_type) {
        this.sl_trigger_type = sl_trigger_type;
    }

    public String getSl_trigger_price() {
        return sl_trigger_price;
    }

    public void setSl_trigger_price(String sl_trigger_price) {
        this.sl_trigger_price = sl_trigger_price;
    }

    public String getSl_price() {
        return sl_price;
    }

    public void setSl_price(String sl_price) {
        this.sl_price = sl_price;
    }
}
