from .client import Client
from .consts import *


class AccountAPI(Client):

    def __init__(self, api_key, api_secret_key, passphrase, use_server_time=False, test=False, first=False):
        Client.__init__(self, api_key, api_secret_key, passphrase, use_server_time, test, first)

    # get all currencies list
    def get_currencies(self):
        return self._request_without_params(GET, CURRENCIES_INFO)

    # get wallet info
    def get_wallet(self):
        return self._request_without_params(GET, WALLET_INFO)

    # get specific currency info
    def get_currency(self, currency):
        return self._request_without_params(GET, CURRENCY_INFO + str(currency))

    # coin withdraw
    def coin_withdraw(self, currency, destination, amount, to_address, trade_pwd, fee, chain, reason, usage_agreement):
        params = {'currency': currency, 'amount': amount, 'destination': destination, 'to_address': to_address, 'trade_pwd': trade_pwd, 'fee': fee, 'chain': chain, 'reason': reason, 'usage_agreement': usage_agreement}
        return self._request_with_params(POST, COIN_WITHDRAW, params)

    # query the fee of coin withdraw
    def get_coin_fee(self, currency=''):
        params = {}
        if currency:
            params['currency'] = currency
        return self._request_with_params(GET, COIN_FEE, params)

    # query all recently coin withdraw record
    def get_coins_withdraw_record(self):
        return self._request_without_params(GET, COINS_WITHDRAW_RECORD)

    # query specific coin withdraw record
    def get_coin_withdraw_record(self, currency):
        return self._request_without_params(GET, COIN_WITHDRAW_RECORD + str(currency))

    # query ledger record
    def get_ledger_record(self, currency='', after='', before='', limit='', type=''):
        params = {'after': after, 'before': before, 'limit': limit, 'type': type}
        if currency:
            params['currency'] = currency
        return self._request_with_params(GET, LEDGER_RECORD, params, cursor=True)

    # query top up address
    def get_top_up_address(self, currency):
        params = {'currency': currency}
        return self._request_with_params(GET, TOP_UP_ADDRESS, params)

    def get_asset_valuation(self, account_type=''):
        params = {}
        if account_type:
            params['account_type'] = account_type
        return self._request_with_params(GET, ASSET_VALUATION, params)

    # query top up records
    def get_top_up_records(self):
        return self._request_without_params(GET, COIN_TOP_UP_RECORDS)

    # query top up record
    def get_top_up_record(self, currency):
        return self._request_without_params(GET, COIN_TOP_UP_RECORD + str(currency))

    # transfer
    def transfer(self, currency, amount, account_from, account_to):
        params = {'currency': currency, 'amount': amount, 'from': account_from, 'to': account_to}
        return self._request_with_params(POST, COIN_TRANSFER, params)

    # fiat withdraw
    def fiat_withdraw(self, amount, trade_pwd, bank_card_id):
        params = {'amount': amount, 'trade_pwd': trade_pwd, 'bank_card_id': bank_card_id}
        return self._request_with_params(POST, FIAT_WITHDRAW, params)

    # query fiat withdraw history
    def fiat_withdraw_history(self):
        return self._request_without_params(GET, FIAT_WITHDRAW_RECORD)

    # query fiat deposit history
    def fiat_deposit_history(self):
        return self._request_without_params(GET, FIAT_TOP_UP_RECORD)

    # query bank card list
    def get_bank_card_list(self):
        return self._request_without_params(GET, BANK_CARD_LIST)