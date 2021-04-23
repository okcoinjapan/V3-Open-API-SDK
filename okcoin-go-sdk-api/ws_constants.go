package okcoin

import "errors"

const (
	CHNL_SPOT_TICKER        = "spot/ticker"        // 行情数据频道
	CHNL_SPOT_CANDLE60S     = "spot/candle60s"     // 1分钟k线数据频道
	CHNL_SPOT_CANDLE180S    = "spot/candle180s"    // 3分钟k线数据频道
	CHNL_SPOT_CANDLE300S    = "spot/candle300s"    // 5分钟k线数据频道
	CHNL_SPOT_CANDLE900S    = "spot/candle900s"    // 15分钟k线数据频道
	CHNL_SPOT_CANDLE1800S   = "spot/candle1800s"   // 30分钟k线数据频道
	CHNL_SPOT_CANDLE3600S   = "spot/candle3600s"   // 1小时k线数据频道
	CHNL_SPOT_CANDLE7200S   = "spot/candle7200s"   // 2小时k线数据频道
	CHNL_SPOT_CANDLE14400S  = "spot/candle14400s"  // 4小时k线数据频道
	CHNL_SPOT_CANDLE21600   = "spot/candle21600"   // 6小时k线数据频道
	CHNL_SPOT_CANDLE43200S  = "spot/candle43200s"  // 12小时k线数据频道
	CHNL_SPOT_CANDLE86400S  = "spot/candle86400s"  // 1day k线数据频道
	CHNL_SPOT_CANDLE604800S = "spot/candle604800s" // 1week k线数据频道
	CHNL_SPOT_TRADE         = "spot/trade"         // 交易信息频道
	CHNL_SPOT_DEPTH         = "spot/depth"         // 深度数据频道，首次200档，后续增量
	CHNL_SPOT_DEPTH5        = "spot/depth5"        // 深度数据频道，每次返回前5档

	CHNL_SPOT_ACCOUNT = "spot/account" // 用户币币账户信息频道
	CHNL_SPOT_ORDER   = "spot/order"   // 用户交易数据频道

	CHNL_EVENT_SUBSCRIBE   = "subscribe"
	CHNL_EVENT_UNSUBSCRIBE = "unsubscribe"
)

var (
	ERR_WS_SUBSCRIOTION_PARAMS = errors.New(`ws subscription parameter error`)
	ERR_WS_CACHE_NOT_MATCH     = errors.New(`ws hot cache not matched`)
)

var (
	DefaultDataCallBack = defaultPrintData
)
