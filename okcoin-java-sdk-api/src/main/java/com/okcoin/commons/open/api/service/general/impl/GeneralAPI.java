package com.okcoin.commons.open.api.service.general.impl;

import com.okcoin.commons.open.api.bean.general.result.ServerTime;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GeneralAPI {
    @GET("/api/general/v3/time")
    Call<ServerTime> getServerTime();
}
