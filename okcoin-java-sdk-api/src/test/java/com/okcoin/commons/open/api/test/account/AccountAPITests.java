package com.okcoin.commons.open.api.test.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.open.api.bean.account.param.JpyWithdraw;
import com.okcoin.commons.open.api.bean.account.param.Transfer;
import com.okcoin.commons.open.api.bean.account.param.Withdraw;
import com.okcoin.commons.open.api.bean.account.result.Currency;
import com.okcoin.commons.open.api.bean.account.result.Wallet;
import com.okcoin.commons.open.api.bean.account.result.WithdrawFee;
import com.okcoin.commons.open.api.service.account.AccountAPIService;
import com.okcoin.commons.open.api.service.account.impl.AccountAPIServiceImpl;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AccountAPITests extends AccountAPIBaseTests {

    private static final Logger LOG = LoggerFactory.getLogger(AccountAPITests.class);

    private AccountAPIService accountAPIService;

    @Before
    public void before() {
        this.config = this.config();
        this.accountAPIService = new AccountAPIServiceImpl(this.config);
    }

    /**
     * 资金账户信息
     * 限速规则：6次/s
     * GET /api/account/v3/wallet
     */
    @Test
    public void getWallet() {
        //所有的资金账户信息
        List<Wallet> result = this.accountAPIService.getWallet();
        this.toResultString(AccountAPITests.LOG, "result", result);
        //单一币种账户信息
//        List<Wallet> result2 = this.accountAPIService.getWallet("JPY");
//        this.toResultString(AccountAPITests.LOG, "result", result2);
    }


    /**资金划转
      *OKCoin站内在资金账户、交易账户之间进行资金划转
     * 限速规则：1次/2s（每个币种）
     * POST /api/account/v3/transfer
     */
    @Test
    public void transfer() {
        Transfer transfer = new Transfer();
        transfer.setFrom("6");
        transfer.setTo("1");
        transfer.setCurrency("LTC");
        transfer.setAmount("1");

        JSONObject result = this.accountAPIService.transfer(transfer);
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 暗号資産出庫
     * Rate Limit：1 request/10s
     * POST /api/account/v3/withdrawal
     */
    @Test
    public void withdraw() {
        Withdraw withdraw = new Withdraw();
        withdraw.setAmount("0.01");
        withdraw.setCurrency("BTC");
        withdraw.setDestination("-1");
        withdraw.setTo_address("17DKe3kkkkiiiiTvAKKi2vMPbm1Bz3CMKw");
        withdraw.setFee("0.001");
        withdraw.setTrade_pwd("12345678");
        withdraw.setReason("1");
        withdraw.setUsage_agreement("0");
        JSONObject result = this.accountAPIService.withdraw(withdraw);
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * JPY資産出庫
     * Rate Limit：1 request/120s
     * POST /api/account/v3/jpywithdrawal
     */
    @Test
    public void jpyWithdraw() {
        JpyWithdraw jpyWithdraw = new JpyWithdraw();
        jpyWithdraw.setAmount("10000");
        jpyWithdraw.setTrade_pwd("12345678");
        JSONObject result = this.accountAPIService.jpyWithdraw(jpyWithdraw);
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 账单流水查询
     * 限速规则：6次/s
     * GET /api/account/v3/ledger
     */
    @Test
    public void getLedger() {
        JSONArray result = this.accountAPIService.getLedger("",null,"","","");
        this.toResultString(AccountAPITests.LOG, "result", result);
    }



    /**
     * 获取充值地址
     * 获取各个币种的充值地址，包括曾使用过的老地址。
     * 限速规则：6次/s
     * GET /api/account/v3/deposit/address
     */
    @Test
    public void getDepositAddress() {
        JSONArray result = this.accountAPIService.getDepositAddress("BTC");
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 获取账户资产估值
     * 按照btc或法币计价单位，获取账户总资产的估值。
     * 限速规则：1次/30s
     * GET/api/account/v3/asset-valuation
     */
    @Test
    public void testGetAllAcccount(){
        JSONObject result = this.accountAPIService.getAllAccount("0");
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 获取所有币种充值记录
     * 获取所有币种的充值记录，为最近一百条数据。
     * 限速规则：6次/s
     * GET /api/account/v3/deposit/history
     */
    @Test
    public void getDepositHistory() {
        JSONArray result = this.accountAPIService.getDepositHistory();
        this.toResultString(AccountAPITests.LOG, "result", result);
        JSONArray result2 = this.accountAPIService.getDepositHistory("ltc");
        this.toResultString(AccountAPITests.LOG, "result", result2);
    }

    /**
     * 获取所有币种充值记录
     * 获取所有币种的充值记录，为最近一百条数据。
     * 限速规则：6次/s
     * GET /api/account/v3/deposit/history
     */
    @Test
    public void getJpyDepositHistory() {
        JSONArray result = this.accountAPIService.getJpyDepositHistory();
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 查询所有/单个币种的提币记录
     * 获取所有币种的充值记录，为最近一百条数据。
     * 限速规则：6次/s
     * GET /api/account/v3/withdrawal/history
     */
    @Test
    public void getWithdrawalHistory() {
        JSONArray result = this.accountAPIService.getWithdrawalHistory();
        this.toResultString(AccountAPITests.LOG, "result", result);
        JSONArray result2 = this.accountAPIService.getWithdrawalHistory("eth");
        this.toResultString(AccountAPITests.LOG, "result", result2);
    }

    /**
     * JPY出金履歴の取得
     * Rate Limit：6 request/1s
     * GET /api/account/v3/jpyWithdrawal/history
     */
    @Test
    public void getJpyWithdrawalHistory() {
        JSONArray result = this.accountAPIService.getJpyWithdrawalHistory();
        this.toResultString(AccountAPITests.LOG, "result", result);
    }


    /**
     * 获取币种列表
     * 限速规则：6次/s
     * GET /api/account/v3/currencies
     */
    @Test
    public void getCurrencies() {
        List<Currency> result = this.accountAPIService.getCurrencies();
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

    /**
     * 提币手续费
     * 查询提现到数字货币地址时，建议网络手续费信息。手续费越高，网络确认越快。
     * 限速规则：6次/s
     * GET /api/account/v3/withdrawal/fee
     */
    @Test
    public void getWithdrawFee() {
        List<WithdrawFee> result = this.accountAPIService.getWithdrawFee("BTC");
        this.toResultString(AccountAPITests.LOG, "result", result);
    }

}
