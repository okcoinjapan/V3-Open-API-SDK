import asyncio
import okcoin.websocket as ws


url = 'wss://connect.okcoin.jp:443/ws/v3'

api_key = ''
secret_key = ''
passphrase = ''

# User Spot Account
# 用户币币账户频道
# channels = ["spot/account:BTC"]

# User Orders
# 用户交易频道
# channels = ["spot/order:BTC-JPY"]

# User Algo Orders
# 用户委托策略频道
# channels = ["spot/order_algo:BTC-JPY"]

# Public-Ticker
# 公共-Ticker频道
# channels = ["spot/ticker:BTC-JPY"]

# Public-Candlesticks
# 公共-K线频道
# channels = ["spot/candle60s:BTC-JPY"]

# Public-Trade
# 公共-交易频道
# channels = ["spot/trade:BTC-JPY"]

# Public-Depth5
# 公共-5档深度频道
# channels = ["spot/depth5:BTC-JPY"]

# Public-Depth400
# 公共-400档深度频道
# channels = ["spot/depth:BTC-JPY"]


loop = asyncio.get_event_loop()

# For Public Channel
loop.run_until_complete(ws.subscribe_without_login(url, channels))

# For Private Channel
# loop.run_until_complete(ws.subscribe(url, api_key, passphrase, secret_key, channels))

loop.close()
