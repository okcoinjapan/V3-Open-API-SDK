package com.okcoin.commons.open.api.test.ws.spot;

import com.okcoin.commons.open.api.test.ws.spot.config.WebSocketClient;
import com.okcoin.commons.open.api.test.ws.spot.config.WebSocketConfig;
import com.okcoin.commons.open.api.utils.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * 公共频道
 * public channel
 *
 * @author oker
 * @create 2019-06-12 14:45
 **/
public class SpotPublicChannelTest {

    private static final WebSocketClient webSocketClient = new WebSocketClient();

    @Before
    public void connect() {
        WebSocketConfig.publicConnect(webSocketClient);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        System.out.println(DateFormatUtils.format(new Date() , DateUtils.TIME_STYLE_S4) + " close connect!");
        webSocketClient.closeConnection();
    }

    /**
     * 公共-ticker频道
     * Ticker Channel
     */
    @Test
    public void tickerChannel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/ticker:LTC-JPY");
        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-k线频道
     * Kline Channel
     */
    @Test
    public void klineChannel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/candle60s:ETH-JPY");

        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-交易频道
     * Trade Channel
     */
    @Test
    public void tradeChannel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/trade:LTC-JPY");
        //list.add("spot/trade:ETH-JPY");

        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-5档深度
     * Depth5 Channel
     */
    @Test
    public void depth5Channel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        //list.add("spot/depth5:BTC-JPY");
        list.add("spot/depth5:ETH-JPY");
        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-400档深度
     * Depth Channel
     */
    @Test
    public void depthChannel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        //list.add("spot/depth:OKB-JPY");
        list.add("spot/depth:LTC-JPY");
        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }


}
