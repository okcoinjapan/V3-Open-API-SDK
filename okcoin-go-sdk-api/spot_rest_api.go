package okcoin

import "strings"

/*
 Account Information
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/accounts
*/
func (client *Client) GetSpotAccounts() (*[]map[string]string, error) {
	r := []map[string]string{}

	if _, err := client.Request(GET, SPOT_ACCOUNTS, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Get Currency
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/accounts/<currency>
*/
func (client *Client) GetSpotAccountsByCurrency(currency string) (*map[string]interface{}, error) {
	r := map[string]interface{}{}
	uri := GetCurrencyUri(SPOT_ACCOUNTS_CURRENCY, currency)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Bills Details
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/accounts/<currency>/ledger
*/
func (client *Client) GetSpotAccountsCurrencyLeger(currency string, optionalParams *map[string]string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	baseUri := GetCurrencyUri(SPOT_ACCOUNTS_CURRENCY_LEDGER, currency)
	uri := baseUri
	if optionalParams != nil && len(*optionalParams) > 0 {
		uri = BuildParams(baseUri, *optionalParams)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Order List
 Limit: 10 requests per 2 seconds
 GET Request: /api/spot/v3/orders
*/
func (client *Client) GetSpotOrders(instrument_id, state string, options *map[string]string) (*[]interface{}, error) {
	r := []interface{}{}

	fullOptions := NewParams()
	fullOptions["instrument_id"] = instrument_id
	fullOptions["state"] = state
	if options != nil && len(*options) > 0 {
		fullOptions["before"] = (*options)["before"]
		fullOptions["after"] = (*options)["after"]
		fullOptions["limit"] = (*options)["limit"]
	}

	uri := BuildParams(SPOT_ORDERS, fullOptions)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Open Orders
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/orders_pending
*/
func (client *Client) GetSpotOrdersPending(instrumentId string, options *map[string]string) (*[]interface{}, error) {
	r := []interface{}{}

	fullOptions := NewParams()
	fullOptions["instrument_id"] = instrumentId

	if options != nil && len(*options) > 0 {

		for k, v := range *options {
			if v != "" && len(v) > 0 {
				fullOptions[k] = v
			}
		}
	}

	uri := BuildParams(SPOT_ORDERS_PENDING, fullOptions)
	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}

	return &r, nil
}

/*
 Order Details
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/orders/<order_id> or <client_oid>
*/
func (client *Client) GetSpotOrdersById(instrumentId, orderOrClientId string) (*map[string]string, error) {
	r := map[string]string{}
	uri := strings.Replace(SPOT_ORDERS_BY_ID, "{order_client_id}", orderOrClientId, -1)
	options := NewParams()
	options["instrument_id"] = instrumentId
	uri = BuildParams(uri, options)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Transaction Details
 Limit: 10 requests per 2 seconds
 GET Request: /api/spot/v3/fills
*/
func (client *Client) GetSpotFills(instrument_id string, options *map[string]string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	fullOptions := NewParams()
	fullOptions["instrument_id"] = instrument_id
	if options != nil && len(*options) > 0 {
		fullOptions["order_id"] = (*options)["order_id"]
		fullOptions["before"] = (*options)["before"]
		fullOptions["after"] = (*options)["after"]
		fullOptions["limit"] = (*options)["limit"]
	}

	uri := BuildParams(SPOT_FILLS, fullOptions)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Trade Fee
 Limit: 1 requests per 10 seconds
 GET Request: /api/spot/v3/trade_fee
*/
func (client *Client) GetSpotTradeFee() (*[]map[string]string, error) {
	r := []map[string]string{}

	if _, err := client.Request(GET, SPOT_TRADE_FEE, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Trading Pairs
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments
*/
func (client *Client) GetSpotInstruments() (*[]map[string]string, error) {
	r := []map[string]string{}

	if _, err := client.Request(GET, SPOT_INSTRUMENTS, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Order Book
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments/<instrument_id>/book
*/
func (client *Client) GetSpotInstrumentBook(instrumentId string, optionalParams *map[string]string) (*map[string]interface{}, error) {
	r := map[string]interface{}{}
	uri := GetInstrumentIdUri(SPOT_INSTRUMENT_BOOK, instrumentId)
	if optionalParams != nil && len(*optionalParams) > 0 {
		optionals := NewParams()
		optionals["size"] = (*optionalParams)["size"]
		optionals["depth"] = (*optionalParams)["depth"]
		uri = BuildParams(uri, optionals)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Ticker
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments/ticker
*/
func (client *Client) GetSpotInstrumentsTicker() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, SPOT_INSTRUMENTS_TICKER, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Trading Pair Information
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments/<instrument_id>/ticker
*/
func (client *Client) GetSpotInstrumentTicker(instrument_id string) (*map[string]interface{}, error) {
	r := map[string]interface{}{}

	uri := GetInstrumentIdUri(SPOT_INSTRUMENT_TICKER, instrument_id)
	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Filled Orders
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments/<instrument_id>/trades
*/
func (client *Client) GetSpotInstrumentTrade(instrument_id string, options *map[string]string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := GetInstrumentIdUri(SPOT_INSTRUMENT_TRADES, instrument_id)
	fullOptions := NewParams()
	if options != nil && len(*options) > 0 {
		fullOptions["limit"] = (*options)["limit"]
		uri = BuildParams(uri, fullOptions)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Public - Market Data
 Limit: 20 requests per 2 seconds
 GET Request: /api/spot/v3/instruments/<instrument_id>/candles
*/
func (client *Client) GetSpotInstrumentCandles(instrument_id string, options *map[string]string) (*[]interface{}, error) {
	r := []interface{}{}

	uri := GetInstrumentIdUri(SPOT_INSTRUMENT_CANDLES, instrument_id)
	fullOptions := NewParams()
	if options != nil && len(*options) > 0 {
		fullOptions["start"] = (*options)["start"]
		fullOptions["end"] = (*options)["end"]
		fullOptions["granularity"] = (*options)["granularity"]
		uri = BuildParams(uri, fullOptions)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Place Order
 Limit: 100 requests per 2 seconds
 POST Request: /api/spot/v3/orders
*/
func (client *Client) PostSpotOrders(instrument_id, side string, optionalOrderInfo *map[string]string) (result *map[string]interface{}, err error) {

	r := map[string]interface{}{}
	postParams := NewParams()
	postParams["side"] = side
	postParams["instrument_id"] = instrument_id

	if optionalOrderInfo != nil && len(*optionalOrderInfo) > 0 {

		for k, v := range *optionalOrderInfo {
			postParams[k] = v
		}

		if postParams["type"] == "limit" {
			postParams["price"] = (*optionalOrderInfo)["price"]
			postParams["size"] = (*optionalOrderInfo)["size"]

		} else if postParams["type"] == "market" {
			postParams["size"] = (*optionalOrderInfo)["size"]
			postParams["notional"] = (*optionalOrderInfo)["notional"]

		}
	}

	if _, err := client.Request(POST, SPOT_ORDERS, postParams, &r); err != nil {
		return nil, err
	}

	return &r, nil
}

/*
 Batch Orders
 Limit: 50 requests per 2 seconds
 POST Request: /api/spot/v3/batch_orders
*/
func (client *Client) PostSpotBatchOrders(orderInfos *[]map[string]string) (*map[string]interface{}, error) {
	r := map[string]interface{}{}
	if _, err := client.Request(POST, SPOT_BATCH_ORDERS, orderInfos, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
 Cancel Order
 Limit: 100 requests per 2 seconds
 POST Request: /api/spot/v3/cancel_orders/<order_id> or <client_oid>
*/
func (client *Client) PostSpotCancelOrders(instrumentId, orderOrClientId string) (*map[string]interface{}, error) {
	r := map[string]interface{}{}

	uri := strings.Replace(SPOT_CANCEL_ORDERS_BY_ID, "{order_client_id}", orderOrClientId, -1)
	options := NewParams()
	options["instrument_id"] = instrumentId

	if _, err := client.Request(POST, uri, options, &r); err != nil {
		return nil, err
	}
	return &r, nil

}

/*
 Cancel Multiple Orders
 Limit: 20 requests per 2 seconds
 POST Request: /api/spot/v3/cancel_batch_orders
*/
func (client *Client) PostSpotCancelBatchOrders(orderInfos *[]map[string]interface{}) (*map[string]interface{}, error) {
	r := map[string]interface{}{}
	if _, err := client.Request(POST, SPOT_CANCEL_BATCH_ORDERS, orderInfos, &r); err != nil {
		return nil, err
	}
	return &r, nil
}
