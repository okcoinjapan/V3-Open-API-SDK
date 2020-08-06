package com.okcoin.commons.open.api.test.ws.spot.config;

/**
 * config
 *
 * @author oker
 * @create 2019-06-12 15:06
 **/
public class WebSocketConfig {

    // okcoin webSocket url
    private static final String SERVICE_URL = "wss://connect.okcoin.jp:443/ws/v3";
    private static final String API_KEY = "";
    private static final String SECRET_KEY = "";
    private static final String PASSPHRASE = "";


    public static void publicConnect(WebSocketClient webSocketClient) {
        webSocketClient.connection(SERVICE_URL);
    }

    public static void loginConnect(WebSocketClient webSocketClient) {
        //サーバーと接続します
        webSocketClient.connection(SERVICE_URL);
        //api-key，passphrase,secret—keyを使用します。
        webSocketClient.login(API_KEY , PASSPHRASE , SECRET_KEY);
    }
}
