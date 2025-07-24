import okcoin.account_api as account
import okcoin.spot_api as spot
import json

api_key = ''
secret_key = ''
passphrase = ''


# param use_server_time's value is False if is True will use server timestamp
# param test's value is False if is True will use simulative trading

######################
# Funding Account API
######################
accountAPI = account.AccountAPI(api_key, secret_key, passphrase, False)

""" Get Balance
    Limit: 6 requests per second
    GET Request: /api/account/v3/wallet
"""
# result = accountAPI.get_wallet()

""" Get Currency
    Limit: 6 requests per second
    GET Request: /api/account/v3/wallet/<currency>
"""
# result = accountAPI.get_currency('BTC')

""" Get Currencies
    Limit: 6 requests per second
    GET Request: /api/account/v3/currencies
"""
# result = accountAPI.get_currencies()

""" Get Asset Valuation
    Limit: 1 request per 30 seconds
    GET Request: /api/account/v3/asset-valuation
"""
# result = accountAPI.get_asset_valuation(account_type='')

""" Funds Transfer
    Limit: 1 request per 2 seconds (per currency)
    POST Request: /api/account/v3/transfer
"""
# result = accountAPI.transfer(currency='BTC', amount='', account_from='', account_to='')

""" Withdrawal
    Limit: 1 requests per 10 seconds
    POST Request: /api/account/v3/withdrawal
"""
# result = accountAPI.coin_withdraw(currency='PLT', destination='-1', amount='', to_address='', trade_pwd='', fee='', chain='Ethereum(ePLT)', reason='1', usage_agreement='0')

""" Withdrawal History
    Limit: 6 requests per second
    GET Request: /api/account/v3/withdrawal/history
"""
# result = accountAPI.get_coins_withdraw_record()

""" Withdrawal History of a Currency
    Limit: 6 requests per second
    GET Request: /api/account/v3/withdrawal/history/<currency>
"""
# result = accountAPI.get_coin_withdraw_record('BTC')

""" Bills Details
    Limit: 20 requests per 2 seconds
    GET Request: /api/account/v3/ledger
"""
# result = accountAPI.get_ledger_record(currency='BTC', after='', before='', limit='', type='')

""" Fiat Withdrawal
    Limit: 1 requests per 120 seconds
    POST Request: /api/account/v3/jpywithdrawal
"""
# result = accountAPI.fiat_withdraw(amount='1', trade_pwd='212qA*PmE5Hu%38', bank_card_id='')

""" Fiat Withdrawal History
    Limit: 6 requests per second
    GET Request: /api/account/v3/jpyWithdrawal/history
"""
# result = accountAPI.fiat_withdraw_history()

""" Fiat Deposit History
    Limit: 6 requests per second
    GET Request: /api/account/v3/jpyDeposit/history
"""
# result = accountAPI.fiat_deposit_history()

""" Deposit Address
    Limit: 20 requests per 2 seconds
    GET Request: /api/account/v3/deposit/address
"""
# result = accountAPI.get_top_up_address('BTC')

""" Deposit History
    Limit: 6 requests per second
    GET Request: /api/account/v3/deposit/history
"""
# result = accountAPI.get_top_up_records()

""" Deposit History of a Currency
    Limit: 6 requests per second
    GET Request: /api/account/v3/deposit/history/<currency>
"""
# result = accountAPI.get_top_up_record(currency='BTC')

""" Withdrawal Fees
    Limit: 6 requests per second
    GET Request: /api/account/v3/withdrawal/fee
"""
# result = accountAPI.get_coin_fee('')

""" Query bank card list
    Limit: Limit: 6 requests per second
    GET Request: /api/account/v3/bank-card-list
"""
result = accountAPI.get_bank_card_list()

######################
# Spot Trading API
######################
spotAPI = spot.SpotAPI(api_key, secret_key, passphrase, False)

""" Account Information
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/accounts
"""
# result = spotAPI.get_account_info()

""" Get Currency
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/accounts/<currency>
"""
# result = spotAPI.get_coin_account_info('')

""" Bills Details
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/accounts/<currency>/ledger
"""
# result = spotAPI.get_ledger_record(currency='BTC', after='', before='', limit='', type='')

""" Place Order
    Limit: 100 requests per 2 seconds
    POST Request: /api/spot/v3/orders
"""
# result = spotAPI.take_order(instrument_id='BTC-JPY', side='sell', client_oid='', type='limit', size='', price='', order_type='0', notional='')

""" Batch Orders
    Limit: 50 requests per 2 seconds
    POST Request: /api/spot/v3/batch_orders
"""
# result = spotAPI.take_orders([
#     {'instrument_id': 'BTC-JPY', 'side': 'buy', 'type': 'market', 'price': '', 'size': '', 'order_type': '0', 'client_oid': '', 'notional': ''},
#     {'instrument_id': 'BTC-JPY', 'side': 'buy', 'type': 'market', 'price': '', 'size': '', 'order_type': '0', 'client_oid': '', 'notional': ''}
# ])

""" Cancel Order
    Limit: 100 requests per 2 seconds
    POST Request: /api/spot/v3/cancel_orders/<order_id> or <client_oid>
"""
# result = spotAPI.revoke_order(instrument_id='BTC-JPY', order_id='', client_oid='')

""" Cancel Multiple Orders
    Limit: 20 requests per 2 seconds
    POST Request: /api/spot/v3/cancel_batch_orders
"""
# result = spotAPI.revoke_orders([
#     {'instrument_id': 'BTC-JPY', 'order_ids': ['', '']},
#     {'instrument_id': 'BTC-JPY', 'client_oids': ['','']}
# ])

""" Order List
    Limit: 10 requests per 2 seconds
    GET Request: /api/spot/v3/orders
"""
# result = spotAPI.get_orders_list(instrument_id='BTC-JPY', state='', after='', before='', limit='')

""" Open Orders
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/orders_pending
"""
# result = spotAPI.get_orders_pending(instrument_id='BTC-JPY', after='', before='', limit='')

""" Order Details
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/orders/<order_id> or <client_oid>
"""
# result = spotAPI.get_order_info(instrument_id='BTC-JPY', order_id='', client_oid='')

""" Transaction Details
    Limit: 10 requests per 2 seconds
    GET Request: /api/spot/v3/fills
"""
# result = spotAPI.get_fills(instrument_id='BTC-JPY', order_id='', after='', before='', limit='')

""" Place Algo Order
    Limit: 40 requests per 2 seconds
    POST Request: /api/spot/v3/order_algo
"""
# result = spotAPI.take_order_algo(instrument_id='ETH-JPY', mode='1', order_type='5', size='0.1', side='sell', tp_trigger_price='487600', tp_price='487601', tp_trigger_type='1', sl_trigger_type='1', sl_trigger_price='4', sl_price='3')

""" Cancel Algo Order
    Limit: 20 requests per 2 seconds
    POST Request: /api/spot/v3/cancel_batch_algos
"""
# result = spotAPI.cancel_algos(instrument_id='ETH-JPY', algo_ids=['7992531393232896'], order_type='5')

""" Get Algo Order List
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/algo
"""
# result = spotAPI.get_order_algos(instrument_id='ETH-JPY', order_type='5', status='1', algo_id='', before='', after='', limit='')

""" Trade Fee
    Limit: 1 requests per 10 seconds
    GET Request: /api/spot/v3/trade_fee
"""
# result = spotAPI.get_trade_fee()

""" Trade Fee All
    Limit: 1 requests per 10 seconds
    GET Request: /api/spot/v3/trade_fee_all
"""
# result = spotAPI.get_trade_fee_all()

""" Trade Fee By instrumentId
    Limit: 1 requests per 10 seconds
    GET Request: /api/spot/v3/trade_fee_by_instrumentId
"""
# result = spotAPI.get_trade_fee_by_instrumentId(instrument_id='BTC-JPY')

""" Public - Trading Pairs
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments
"""
# result = spotAPI.get_coin_info()

""" Public - Order Book
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments/<instrument_id>/book
"""
# result = spotAPI.get_depth(instrument_id='BTC-JPY', size='', depth='')

""" Public - Ticker
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments/ticker
"""
# result = spotAPI.get_ticker()

""" Public - Trading Pair Information
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments/<instrument_id>/ticker
"""
# result = spotAPI.get_specific_ticker('BTC-JPY')

""" Public - Filled Orders
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments/<instrument_id>/trades
"""
# result = spotAPI.get_deal(instrument_id='BTC-JPY', limit='')

""" Public - Market Data
    Limit: 20 requests per 2 seconds
    GET Request: /api/spot/v3/instruments/<instrument_id>/candles
"""
# result = spotAPI.get_kline(instrument_id='BTC-JPY', start='', end='', granularity='300')

print(json.dumps(result, ensure_ascii=False))
