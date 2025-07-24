package okcoin

/*
Get Currencies
Limit: 6 requests per second
GET Request: /api/account/v3/currencies
*/
func (client *Client) GetAccountCurrencies() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_CURRENCIES, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Get Balance
Limit: 6 requests per second
GET Request: /api/account/v3/wallet
*/
func (client *Client) GetAccountWallet() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_WALLET, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Get Currency
Limit: 6 requests per second
GET Request: /api/account/v3/wallet/<currency>
*/
func (client *Client) GetAccountWalletByCurrency(currency string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := GetCurrencyUri(ACCOUNT_WALLET_CURRENCY, currency)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Get Asset Valuation
Limit: 1 request per 30 seconds
GET Request: /api/account/v3/asset-valuation
*/
func (client *Client) GetAccountAssetValuation(account_type string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := ACCOUNT_ASSET_VALUATION
	if len(account_type) > 0 {
		params := NewParams()
		params["account_type"] = account_type
		uri = BuildParams(uri, params)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Fiat Withdrawal
Limit: 1 requests per 120 seconds
POST Request: /api/account/v3/jpywithdrawal
*/
func (client *Client) PostAccountFiatWithdrawal(amount, trade_pwd string, bank_card_id string) (*map[string]interface{}, error) {

	r := map[string]interface{}{}

	body := map[string]interface{}{}
	body["amount"] = amount
	body["trade_pwd"] = trade_pwd
	body["bank_card_id"] = bank_card_id
	if _, err := client.Request(POST, ACCOUNT_FIAT_WITHDRAWAL, body, &r); err != nil {
		return nil, err
	}

	return &r, nil
}

/*
Fiat Withdrawal History
Limit: 6 requests per second
GET Request: /api/account/v3/jpyWithdrawal/history
*/
func (client *Client) GetAccountFiatWithdrawalHistory() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_FIAT_WITHDRAWAL_HISTORY, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Fiat Deposit History
Limit: 6 requests per second
GET Request: /api/account/v3/jpyDeposit/history
*/
func (client *Client) GetAccountFiatDepositHistory() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_FIAT_DEPOSIT_HISTORY, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Withdrawal Fees
Limit: 6 requests per second
GET Request: /api/account/v3/withdrawal/fee
*/
func (client *Client) GetAccountWithdrawalFeeByCurrency(currency string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := ACCOUNT_WITHDRAWAL_FEE
	if len(currency) > 0 {
		params := NewParams()
		params["currency"] = currency
		uri = BuildParams(uri, params)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Withdrawal History
Limit: 6 requests per second
GET Request: /api/account/v3/withdrawal/history
*/
func (client *Client) GetAccountWithdrawalHistory() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_WITHDRAWAL_HISTORY, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Withdrawal History of a Currency
Limit: 6 requests per second
GET Request: /api/account/v3/withdrawal/history/<currency>
*/
func (client *Client) GetAccountWithdrawalHistoryByCurrency(currency string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := GetCurrencyUri(ACCOUNT_WITHDRAWAL_HISTORY_CURRENCY, currency)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Deposit Address
Limit: 20 requests per 2 seconds
GET Request: /api/account/v3/deposit/address
*/
func (client *Client) GetAccountDepositAddress(currency string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}
	params := NewParams()
	params["currency"] = currency

	uri := BuildParams(ACCOUNT_DEPOSIT_ADDRESS, params)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Deposit History
Limit: 6 requests per second
GET Request: /api/account/v3/deposit/history
*/
func (client *Client) GetAccountDepositHistory() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_DEPOSIT_HISTORY, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Deposit History of a Currency
Limit: 6 requests per second
GET Request: /api/account/v3/deposit/history/<currency>
*/
func (client *Client) GetAccountDepositHistoryByCurrency(currency string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	uri := GetCurrencyUri(ACCOUNT_DEPOSIT_HISTORY_CURRENCY, currency)

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Bills Details
Limit: 20 requests per 2 seconds
GET Request: /api/account/v3/ledger
*/
func (client *Client) GetAccountLeger(optionalParams *map[string]string) (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}
	uri := ACCOUNT_LEDGER
	if optionalParams != nil && len(*optionalParams) > 0 {
		uri = BuildParams(uri, *optionalParams)
	}

	if _, err := client.Request(GET, uri, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}

/*
Withdrawal
Limit: 1 requests per 10 seconds
POST Request: /api/account/v3/withdrawal
*/
func (client *Client) PostAccountWithdrawal(
	currency, destination, amount, to_address, trade_pwd, fee, chain, reason, usage_agreement string) (*map[string]interface{}, error) {

	r := map[string]interface{}{}

	withdrawlInfo := map[string]interface{}{}
	withdrawlInfo["currency"] = currency
	withdrawlInfo["destination"] = destination
	withdrawlInfo["amount"] = amount
	withdrawlInfo["to_address"] = to_address
	withdrawlInfo["trade_pwd"] = trade_pwd
	withdrawlInfo["fee"] = fee
	withdrawlInfo["chain"] = chain
	withdrawlInfo["reason"] = reason
	withdrawlInfo["usage_agreement"] = usage_agreement

	if _, err := client.Request(POST, ACCOUNT_WITHDRAWAL, withdrawlInfo, &r); err != nil {
		return nil, err
	}

	return &r, nil
}

/*
Funds Transfer
Limit: 1 request per 2 seconds (per currency)
POST Request: /api/account/v3/transfer
*/
func (client *Client) PostAccountTransfer(
	currency string, from, to string, amount string) (*map[string]interface{}, error) {

	r := map[string]interface{}{}

	transferInfo := map[string]interface{}{}
	transferInfo["amount"] = amount
	transferInfo["from"] = from
	transferInfo["to"] = to
	transferInfo["currency"] = currency

	if _, err := client.Request(POST, ACCOUNT_TRANSFER, transferInfo, &r); err != nil {
		return nil, err
	}

	return &r, nil
}

/*
Bank Card List
Limit: 6 request per 1 seconds (per currency)
GET Request: /api/account/v3/bank-card-list
*/
func (client *Client) GetBankCardList() (*[]map[string]interface{}, error) {
	r := []map[string]interface{}{}

	if _, err := client.Request(GET, ACCOUNT_BAKN_CARD_LIST, nil, &r); err != nil {
		return nil, err
	}
	return &r, nil
}
