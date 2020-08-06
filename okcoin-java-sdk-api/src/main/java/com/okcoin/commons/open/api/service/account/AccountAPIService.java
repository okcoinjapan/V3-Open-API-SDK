package com.okcoin.commons.open.api.service.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okcoin.commons.open.api.bean.account.param.Transfer;
import com.okcoin.commons.open.api.bean.account.result.Currency;
import com.okcoin.commons.open.api.bean.account.result.Wallet;
import com.okcoin.commons.open.api.bean.account.result.WithdrawFee;

import java.math.BigDecimal;
import java.util.List;


public interface AccountAPIService {

    List<Wallet> getWallet();

    List<Wallet> getWallet(String currency);

    JSONObject transfer(Transfer transfer);

    List<Currency> getCurrencies();

    JSONArray getLedger(String type, String currency, String before, String after, String limit);

    JSONArray getDepositAddress(String currency);

    List<WithdrawFee> getWithdrawFee(String currency);

    JSONArray getOnHold(String currency);

    JSONObject lock(String currency, BigDecimal amount);

    JSONObject unlock(String currency, BigDecimal amount);

    JSONArray getDepositHistory();

    JSONArray getDepositHistory(String currency);

    JSONArray getWithdrawalHistory();

    JSONArray getWithdrawalHistory(String currency);

    JSONObject getAllAccount(String account_type);

}
