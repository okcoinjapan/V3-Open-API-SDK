package okcoin

import (
	"testing"
)

/*
 Get Currencies
 Limit: 6 requests per second
 GET Request: /api/account/v3/currencies
*/
func TestGetAccountCurrencies(t *testing.T) {
	NewTestClient().GetAccountCurrencies()
}

/*
 Get Balance
 Limit: 6 requests per second
 GET Request: /api/account/v3/wallet
*/
func TestGetAccountWallet(t *testing.T) {
	NewTestClient().GetAccountWallet()
}

/*
 Withdrawal Fees
 Limit: 6 requests per second
 GET Request: /api/account/v3/withdrawal/fee
*/
func TestGetAccountWithdrawalFeeByCurrency(t *testing.T) {
	c := NewTestClient()
	c.GetAccountWithdrawalFeeByCurrency("BTC")
	c.GetAccountWithdrawalFeeByCurrency("")
}

/*
 Withdrawal History
 Limit: 6 requests per second
 GET Request: /api/account/v3/withdrawal/history
*/
func TestGetAccountWithdrawalHistory(t *testing.T) {
	NewTestClient().GetAccountWithdrawalHistory()
}

/*
 Withdrawal History of a Currency
 Limit: 6 requests per second
 GET Request: /api/account/v3/withdrawal/history/<currency>
*/
func TestGetAccountWithdrawalHistoryByCurrency(t *testing.T) {
	NewTestClient().GetAccountWithdrawalHistoryByCurrency("BTC")
}

/*
 Deposit Address
 Limit: 20 requests per 2 seconds
 GET Request: /api/account/v3/deposit/address
*/
func TestGetAccountDepositAddress(t *testing.T) {
	NewTestClient().GetAccountDepositAddress("BTC")
}

/*
 Deposit History
 Limit: 6 requests per second
 GET Request: /api/account/v3/deposit/history
*/
func TestGetAccountDepositHistory(t *testing.T) {
	NewTestClient().GetAccountDepositHistory()
}

/*
 Deposit History of a Currency
 Limit: 6 requests per second
 GET Request: /api/account/v3/deposit/history/<currency>
*/
func TestGetAccountDepositHistoryByCurrency(t *testing.T) {
	NewTestClient().GetAccountDepositHistoryByCurrency("BTC")
}

/*
 Bills Details
 Limit: 20 requests per 2 seconds
 GET Request: /api/account/v3/ledger
*/
func TestGetAccountLeger(t *testing.T) {
	c := NewTestClient()
	c.GetAccountLeger(nil)

	optionals := NewParams()
	optionals["type"] = "1"
	c.GetAccountLeger(&optionals)
}

/*
 Get Asset Valuation
 Limit: 1 request per 30 seconds
 GET Request: /api/account/v3/asset-valuation
*/
func TestGetAccountAssetValuation(t *testing.T) {
	NewTestClient().GetAccountAssetValuation("")
}

/*
 Get Currency
 Limit: 6 requests per second
 GET Request: /api/account/v3/wallet/<currency>
*/
func TestGetAccountWalletByCurrency(t *testing.T) {
	NewTestClient().GetAccountWalletByCurrency("BTC")
}

/*
 Withdrawal
 Limit: 1 requests per 10 seconds
 POST Request: /api/account/v3/withdrawal
*/
func TestPostAccountWithdrawal(t *testing.T) {
	NewTestClient().PostAccountWithdrawal("BTC", "-1", "0.001", "", "", "0.0005", "", "1", "1")
}

/*
 Funds Transfer
 Limit: 1 request per 2 seconds (per currency)
 POST Request: /api/account/v3/transfer
*/
func TestPostAccountTransfer(t *testing.T) {
	NewTestClient().PostAccountTransfer("ETH", "1", "6", "1")
}

/*
 Fiat Withdrawal
 Limit: 1 requests per 120 seconds
 POST Request: /api/account/v3/jpywithdrawal
*/
func TestPostAccountFiatWithdrawal(t *testing.T) {
	NewTestClient().PostAccountFiatWithdrawal("", "")
}

/*
 Fiat Withdrawal History
 Limit: 6 requests per second
 GET Request: /api/account/v3/jpyWithdrawal/history
*/
func TestGetAccountFiatWithdrawalHistory(t *testing.T) {
	NewTestClient().GetAccountFiatWithdrawalHistory()
}

/*
 Fiat Deposit History
 Limit: 6 requests per second
 GET Request: /api/account/v3/jpyDeposit/history
*/
func TestGetAccountFiatDepositHistory(t *testing.T) {
	NewTestClient().GetAccountFiatDepositHistory()
}
