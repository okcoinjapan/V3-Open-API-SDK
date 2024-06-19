package com.okcoin.commons.open.api.test.spot;

import com.okcoin.commons.open.api.bean.spot.param.CancelAlgoParam;
import com.okcoin.commons.open.api.bean.spot.param.OrderAlgoParam;
import com.okcoin.commons.open.api.bean.spot.param.OrderParamDto;
import com.okcoin.commons.open.api.bean.spot.param.PlaceOrderParam;
import com.okcoin.commons.open.api.bean.spot.result.CancelAlgoResult;
import com.okcoin.commons.open.api.bean.spot.result.Fills;
import com.okcoin.commons.open.api.bean.spot.result.OrderAlgoResult;
import com.okcoin.commons.open.api.bean.spot.result.OrderInfo;
import com.okcoin.commons.open.api.bean.spot.result.OrderResult;
import com.okcoin.commons.open.api.bean.spot.result.PendingOrdersInfo;
import com.okcoin.commons.open.api.service.spot.SpotOrderAPIServive;
import com.okcoin.commons.open.api.service.spot.impl.SpotOrderApiServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpotOrderAPITest extends SpotAPIBaseTests {

    private static final Logger LOG = LoggerFactory.getLogger(SpotOrderAPITest.class);

    private SpotOrderAPIServive spotOrderAPIServive;

    @Before
    public void before() {
        this.config = this.config();
        this.spotOrderAPIServive = new SpotOrderApiServiceImpl(this.config);
    }

    /**
     * 下单
     * OKCoin币币交易提供限价单和市价单和高级限价单下单模式。只有当您的账户有足够的资金才能下单。
     * 一旦下单，您的账户资金将在订单生命周期内被冻结。被冻结的资金以及数量取决于订单指定的类型和参数。
     * POST /api/spot/v3/orders
     * 限速规则：100次/2s
     */
    @Test
    public void addOrder() {
        final PlaceOrderParam order = new PlaceOrderParam();
        order.setClient_oid("0422test1");
        order.setInstrument_id("BTC-JPY");
        order.setType("limit");
        order.setSide("sell");
        order.setOrder_type("0");

        //限价单
        order.setPrice("0.2");
        order.setSize("1");

        //市价单 买入金额必填notional，卖出必填size（卖出数量）
        //order.setNotional("1");
        //order.setSize("20");

        final OrderResult orderResult = this.spotOrderAPIServive.addOrder(order);
        this.toResultString(SpotOrderAPITest.LOG, "orders", orderResult);
    }

    /**
     * 批量下单
     * 下指定币对的多个订单（每次只能下最多4个币对且每个币对可批量下10个单）
     * POST /api/spot/v3/batch_orders
     * 限速规则：50次/2s
     */
    @Test
    public void batchAddOrder() {
        final List<PlaceOrderParam> list = new ArrayList<>();
        final PlaceOrderParam order = new PlaceOrderParam();
        order.setClient_oid("CTTBTC1226TEST01");
        order.setInstrument_id("BTC-JPY");
        order.setPrice("0.19");
        order.setType("limit");
        order.setSide("sell");
        order.setSize("1");
        order.setOrder_type("0");
        list.add(order);

        final PlaceOrderParam order1 = new PlaceOrderParam();
        order1.setClient_oid("CTTBTC1226TEST02");
        order1.setInstrument_id("BTC-JPY");
        order1.setPrice("0.19");
        order1.setType("limit");
        order1.setSide("sell");
        order1.setSize("1");
        order1.setOrder_type("0");
        list.add(order1);

       /* final PlaceOrderParam order2 = new PlaceOrderParam();
        order2.setClient_oid("CTTBTC1226TEST01");
        order2.setInstrument_id("BTC-JPY");
        order2.setPrice("0.21");
        order2.setType("limit");
        order2.setSide("sell");
        order2.setSize("1");
        order2.setOrder_type("0");
        list.add(order2);

        final PlaceOrderParam order3 = new PlaceOrderParam();
        order3.setClient_oid("CTTBTC1226TEST02");
        order3.setInstrument_id("BTC-JPY");
        order3.setPrice("0.23");
        order3.setType("limit");
        order3.setSide("sell");
        order3.setSize("1");
        order3.setOrder_type("0");
        list.add(order3);*/



        final Map<String, List<OrderResult>> orderResult = this.spotOrderAPIServive.addOrders(list);
        this.toResultString(SpotOrderAPITest.LOG, "orders", orderResult);
    }


    /**
     * 撤销指定订单 post协议
     * POST /api/spot/v3/cancel_orders/<order_id> or <client_oid>
     * 限速规则：100次/2s
     */
    @Test
    public void cancleOrderByOrderId_post() {
        final PlaceOrderParam order = new PlaceOrderParam();
        order.setInstrument_id("BTC-JPY");
        final OrderResult orderResult = this.spotOrderAPIServive.cancleOrderByOrderId_post(order, "4772154574397440");
        this.toResultString(SpotOrderAPITest.LOG, "cancleOrder", orderResult);
    }

    @Test
    public void cancleOrderByClientOid_post() {
        final PlaceOrderParam order = new PlaceOrderParam();
        order.setInstrument_id("BTC-JPY");
        final OrderResult orderResult = this.spotOrderAPIServive.cancleOrderByClientOid(order, "testspot1");
        this.toResultString(SpotOrderAPITest.LOG, "cancleOrder", orderResult);
    }


    /**
     * 批量撤单 post协议
     * 撤销指定的某一种或多种币对的未完成订单（每次只能下最多4个币对且每个币对可批量下10个单）。
     * POST /api/spot/v3/cancel_batch_orders
     * 限速规则：50次/2s
     */
    //根据order_id进行批量撤单
    @Test
    public void batchCancleOrders_post() {
        final List<OrderParamDto> cancleOrders = new ArrayList<>();
        final OrderParamDto dto = new OrderParamDto();
        dto.setInstrument_id("BTC-JPY");
        final List<String> order_ids = new ArrayList<>();
        order_ids.add("4585322868184070");
        //order_ids.add("");

        dto.setOrder_ids(order_ids);
        cancleOrders.add(dto);

        /*final OrderParamDto dto1 = new OrderParamDto();
        dto1.setInstrument_id("BTC-JPY");
        final List<String> order_ids1 = new ArrayList<>();
        order_ids1.add("4096139176250370");
        order_ids1.add("4096139176250371");

        dto1.setOrder_ids(order_ids1);
        cancleOrders.add(dto1);*/

        final Map<String, Object> orderResult = this.spotOrderAPIServive.cancleOrders_post(cancleOrders);
        this.toResultString(SpotOrderAPITest.LOG, "cancleOrders", orderResult);
    }

    //根据client_oid批量撤单
    @Test
    public void batchCancleOrders_postByClient_oid1() {
        List<OrderParamDto> list = new ArrayList<>();

        OrderParamDto param1 = new OrderParamDto();
        param1.setInstrument_id("BTC-JPY");
        List<String> client_oid = new ArrayList<>();
        client_oid.add("CTTBTC1226TEST01");
        client_oid.add("CTTBTC1226TEST02");
        param1.setClient_oids(client_oid);
        list.add(param1);

        /*OrderParamDto param2 = new OrderParamDto();
        param2.setInstrument_id("BTC-JPY");
        List<String> client_oid1 = new ArrayList<>();
        client_oid1.add("CTTBTC1226TEST01");
        client_oid1.add("CTTBTC1226TEST02");

        param2.setClient_oids(client_oid1);

        list.add(param2);*/

        final Map<String, Object> orderResult = this.spotOrderAPIServive.batch_orderCle(list);
        this.toResultString(SpotOrderAPITest.LOG, "cancleOrders", orderResult);
    }


    /**
     * 获取指定订单信息
     * 通过订单ID获取单个订单信息。可以获取近3个月订单信息。已撤销的未成交单只保留2个小时。
     * GET /api/spot/v3/orders/<order_id>
     * 限速规则：20次/2s
     */
    @Test
    public void getOrderByOrderId() {
        final OrderInfo orderInfo = this.spotOrderAPIServive.getOrderByOrderId("BTC-JPY", "4765535439447040");
        this.toResultString(SpotOrderAPITest.LOG, "orderInfo", orderInfo);
    }

    @Test
    public void getOrderByClientOid() {
        final OrderInfo orderInfo = this.spotOrderAPIServive.getOrderByClientOid("BTC-JPY","testspot1");
        this.toResultString(SpotOrderAPITest.LOG, "orderInfo", orderInfo);
    }

    /**
     * 查询订单列表
     * 列出您当前的订单信息（本接口能查询最近3个月订单信息）。
     * 这个请求支持分页，并且按委托时间倒序排序和存储，最新的排在最前面。
     * GET /api/spot/v3/orders
     * 限速规则：10次/2s
     */
    @Test
    public void getOrders() {
        final List<OrderInfo> orderInfoList = this.spotOrderAPIServive.getOrders("BTC-JPY", "0", "", "", "");
            this.toResultString(SpotOrderAPITest.LOG, "orderInfoList", orderInfoList);
    }

    /**
     * 获取所有未成交订单
     * 列出您当前所有的订单信息。这个请求支持分页，并且按时间倒序排序和存储，
     * 最新的排在最前面。请参阅分页部分以获取第一页之后的其他纪录。
     * GET /api/spot/v3/orders_pending
     * 限速规则：20次/2s
     */
    @Test
    public void getPendingOrders() {
        final List<PendingOrdersInfo> orderInfoList = this.spotOrderAPIServive.getPendingOrders("", "", "", "BTC-JPY");
        this.toResultString(SpotOrderAPITest.LOG, "orderInfoList", orderInfoList);
    }

    /**
     * 获取成交明细
     * 获取最近的成交明细表。这个请求支持分页，并且按成交时间倒序排序和存储，最新的排在最前面。
     * 请参阅分页部分以获取第一页之后的其他记录。 本接口能查询最近3月的数据。
     * 限速规则：10次/2s
     */
    @Test
    public void getFills() {
        final List<Fills> fillsList = this.spotOrderAPIServive.getFills("", "BTC-JPY", "", "", "");
        this.toResultString(SpotOrderAPITest.LOG, "fillsList", fillsList);
    }

    /**
     * 策略委托下单
     * POST /api/spot/v3/order_algo
     * 限速规则：40次/2s
     */
    @Test
    public void addOrderAlgo() {
        final OrderAlgoParam order = new OrderAlgoParam();
        //公共参数
        order.setInstrument_id("ETH-JPY");
        order.setMode("1");
        order.setOrder_type("5");
        order.setSize("0.1");
        order.setSide("sell");

        //止盈止损参数
        order.setTp_trigger_price("487600");
        order.setTp_price("487601");
        order.setTp_trigger_type("1");
        order.setSl_trigger_price("487300");
        order.setSl_price("487299");
        order.setSl_trigger_type("1");

        final OrderAlgoResult orderAlgoResult = this.spotOrderAPIServive.addOrderAlgo(order);
        this.toResultString(SpotOrderAPITest.LOG, "orders", orderAlgoResult);
    }

    /**
     * 策略委托撤单
     * 根据指定的algo_id撤销某个币的未完成订单，每次最多可撤10个。
     * POST /api/spot/v3/cancel_batch_algos
     * 限速规则：20 次/2s
     */
    @Test
    public void cancelOrderAlgo(){
        final CancelAlgoParam cancelAlgoParam = new CancelAlgoParam();
        List<String> ids = new ArrayList<>();
        ids.add("7992019908149248");
        ids.add("7988108725761024");
        cancelAlgoParam.setInstrument_id("ETH-JPY");
        cancelAlgoParam.setOrder_type("5");
        cancelAlgoParam.setAlgo_ids(ids);
        final CancelAlgoResult cancelAlgoResult = this.spotOrderAPIServive.cancelOrderAlgo(cancelAlgoParam);
        this.toResultString(SpotOrderAPITest.LOG, "cancelOrder", cancelAlgoResult);

    }

    /**
     * 获取委托单列表
     * 列出您当前所有的订单信息。
     * GET /api/spot/v3/algo
     * 限速规则：20次/2s
     */
    @Test
    public void getAlgoOrder(){
        final Map<String, Object> findAlgOrderResults = this.spotOrderAPIServive.getAlgoOrder("ETH-JPY", "5", null, "", null, null, null);
        this.toResultString(SpotOrderAPITest.LOG, "getAlgoOrderResults", findAlgOrderResults);
    }
}
