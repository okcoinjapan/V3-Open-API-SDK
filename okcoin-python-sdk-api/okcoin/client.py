import requests
import json
import datetime
from . import consts as c, utils, exceptions


class Client(object):

    def __init__(self, api_key, api_secret_key, passphrase, use_server_time=False, test=False, first=False):

        self.API_KEY = api_key
        self.API_SECRET_KEY = api_secret_key
        self.PASSPHRASE = passphrase
        self.use_server_time = use_server_time
        self.first = first
        self.test = test

    def _request(self, method, request_path, params, cursor=False):
        if method == c.GET:
            request_path = request_path + utils.parse_params_to_str(params)
        # url
        url = c.API_URL + request_path

        # get local timestamp
        timestamp = utils.get_timestamp()

        # sign & header
        if self.use_server_time:
            # get server timestamp
            timestamp = self._get_timestamp()

        body = json.dumps(params) if method == c.POST else ''
        sign = utils.sign(utils.pre_hash(timestamp, method, request_path, str(body)), self.API_SECRET_KEY)
        header = utils.get_header(self.API_KEY, sign, timestamp, self.PASSPHRASE)

        if self.test:
            header['x-simulated-trading'] = '1'
        if self.first:
            print("url:", url)
            self.first = False

        # print('Request:', f'({datetime.datetime.now()})')
        # print('  url:', url)
        # print('  method:', method)
        # print('  headers:', header)
        # print('  body:', params)

        # send request
        response = None
        if method == c.GET:
            response = requests.get(url, headers=header)
        elif method == c.POST:
            response = requests.post(url, data=body, headers=header)
        elif method == c.DELETE:
            response = requests.delete(url, headers=header)

        # print('Response:', f'({datetime.datetime.now()})')
        # print('  status:', response.status_code)
        # print('  headers:', response.headers)
        # exception handle
        if not str(response.status_code).startswith('2'):
            raise exceptions.OkcoinAPIException(response)
        try:
            res_header = response.headers
            if cursor:
                r = dict()
                try:
                    r['before'] = res_header['OK-BEFORE']
                    r['after'] = res_header['OK-AFTER']
                except:
                    pass
                return response.json(), r
            else:
                return response.json()

        except ValueError:
            raise exceptions.OkcoinRequestException('Invalid Response: %s' % response.text)

    def _request_without_params(self, method, request_path):
        return self._request(method, request_path, {})

    def _request_with_params(self, method, request_path, params, cursor=False):
        return self._request(method, request_path, params, cursor)

    def _get_timestamp(self):
        url = c.API_URL + c.SERVER_TIMESTAMP_URL
        response = requests.get(url)
        if response.status_code == 200:
            return response.json()['iso']
        else:
            return ""
