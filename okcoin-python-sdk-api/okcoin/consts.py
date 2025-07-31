
API_URL = 'https://www.okcoin.jp'

CONTENT_TYPE = 'Content-Type'
OK_ACCESS_KEY = 'OK-ACCESS-KEY'
OK_ACCESS_SIGN = 'OK-ACCESS-SIGN'
OK_ACCESS_TIMESTAMP = 'OK-ACCESS-TIMESTAMP'
OK_ACCESS_PASSPHRASE = 'OK-ACCESS-PASSPHRASE'

ACCEPT = 'Accept'
COOKIE = 'Cookie'
LOCALE = 'locale=en_US'

APPLICATION_JSON = 'application/json; charset=UTF-8'

GET = "GET"
POST = "POST"
DELETE = "DELETE"

SERVER_TIMESTAMP_URL = '/api/general/v3/time'

# account
WALLET_INFO = '/api/account/v3/wallet'
CURRENCY_INFO = '/api/account/v3/wallet/'
COIN_TRANSFER = '/api/account/v3/transfer'
COIN_WITHDRAW = '/api/account/v3/withdrawal'
LEDGER_RECORD = '/api/account/v3/ledger'
TOP_UP_ADDRESS = '/api/account/v3/deposit/address'
ASSET_VALUATION = '/api/account/v3/asset-valuation'
COINS_WITHDRAW_RECORD = '/api/account/v3/withdrawal/history'
COIN_WITHDRAW_RECORD = '/api/account/v3/withdrawal/history/'
COIN_TOP_UP_RECORDS = '/api/account/v3/deposit/history'
COIN_TOP_UP_RECORD = '/api/account/v3/deposit/history/'
CURRENCIES_INFO = '/api/account/v3/currencies'
COIN_FEE = '/api/account/v3/withdrawal/fee'
FIAT_WITHDRAW = '/api/account/v3/jpywithdrawal'
FIAT_WITHDRAW_RECORD = '/api/account/v3/jpyWithdrawal/history'
FIAT_TOP_UP_RECORD = '/api/account/v3/jpyDeposit/history'
BANK_CARD_LIST = '/api/account/v3/bank-card-list'

# spot
SPOT_ACCOUNT_INFO = '/api/spot/v3/accounts'
SPOT_COIN_ACCOUNT_INFO = '/api/spot/v3/accounts/'
SPOT_LEDGER_RECORD = '/api/spot/v3/accounts/'
SPOT_ORDER = '/api/spot/v3/orders'
SPOT_ORDERS = '/api/spot/v3/batch_orders'
SPOT_REVOKE_ORDER = '/api/spot/v3/cancel_orders/'
SPOT_REVOKE_ORDERS = '/api/spot/v3/cancel_batch_orders/'
SPOT_ORDERS_LIST = '/api/spot/v3/orders'
SPOT_ORDERS_PENDING = '/api/spot/v3/orders_pending'
SPOT_ORDER_INFO = '/api/spot/v3/orders/'
SPOT_FILLS = '/api/spot/v3/fills'
SPOT_ORDER_ALGO = '/api/spot/v3/order_algo'
SPOT_CANCEL_ALGOS = '/api/spot/v3/cancel_batch_algos'
SPOT_GET_ORDER_ALGOS = '/api/spot/v3/algo'
SPOT_TRADE_FEE = '/api/spot/v3/trade_fee'
SPOT_TRADE_FEE_ALL = '/api/spot/v3/trade_fee_all'
SPOT_TRADE_FEE_BY_INSTRUMENTID = '/api/spot/v3/trade_fee/'
SPOT_COIN_INFO = '/api/spot/v3/instruments'
SPOT_DEPTH = '/api/spot/v3/instruments/'
SPOT_TICKER = '/api/spot/v3/instruments/ticker'
SPOT_SPECIFIC_TICKER = '/api/spot/v3/instruments/'
SPOT_DEAL = '/api/spot/v3/instruments/'
SPOT_KLINE = '/api/spot/v3/instruments/'

