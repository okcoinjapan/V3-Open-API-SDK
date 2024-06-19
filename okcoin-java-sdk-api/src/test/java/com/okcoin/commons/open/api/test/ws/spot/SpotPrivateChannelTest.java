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
 * 需要登录的频道
 * private channel
 *
 * @author oker
 * @create 2019-06-12 14:44
 **/
public class SpotPrivateChannelTest {
    private static final WebSocketClient webSocketClient = new WebSocketClient();

    @Before
    public void connect() {
        WebSocketConfig.loginConnect(webSocketClient);
        while (true) {
            if (webSocketClient.getIsLogin()) {
                return;
            } else {
                try {
                    Thread.sleep(200);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                if (!webSocketClient.getIsConnect()) {
                    return;
                }
            }
        }

    }

    @After
    public void close() {
        System.out.println(DateFormatUtils.format(new Date() , DateUtils.TIME_STYLE_S4) + " close connect!");
        webSocketClient.closeConnection();
    }

    /**
     * 用户币币账户频道
     * Account Channel
     */
    @Test
    public void spotAccountChannel() {
        if (!webSocketClient.getIsConnect()) {
            return;
        }
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/account:JPY");
        list.add("spot/account:BTC");
        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户交易频道
     * Order Channel
     */
    @Test
    public void orderChannel() {
        //リストを作成し、講読したいチャンネルを追加します。
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/order:BTC-JPY");
        webSocketClient.subscribe(list);
        //テストを終了しないようにするため、スリープさせます。
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户委托策略频道
     * Order Channel
     */
    @Test
    public void algoOrderChannel() {
        //创建一个list集合，添加要订阅的频道名称
        final ArrayList<String> list = new ArrayList<>();
        list.add("spot/order_algo:ETH-JPY");
        webSocketClient.subscribe(list);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
