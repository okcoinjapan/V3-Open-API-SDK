from .consts import *
import asyncio
import websockets
import json
import requests
import dateutil.parser as dp
import hmac
import base64
import zlib
import datetime


def get_timestamp():
    return datetime.datetime.now()


def get_server_time():
    url = API_URL + "/api/general/v3/time"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()['iso']
    else:
        return ""


def server_timestamp():
    server_time = get_server_time()
    parsed_t = dp.parse(server_time)
    timestamp = parsed_t.timestamp()
    return timestamp


def login_params(timestamp, api_key, passphrase, secret_key):
    message = timestamp + 'GET' + '/users/self/verify'

    mac = hmac.new(bytes(secret_key, encoding='utf8'), bytes(message, encoding='utf-8'), digestmod='sha256')
    d = mac.digest()
    sign = base64.b64encode(d)

    login_param = {"op": "login", "args": [api_key, passphrase, timestamp, sign.decode("utf-8")]}
    login_str = json.dumps(login_param)
    return login_str


def inflate(data):
    decompress = zlib.decompressobj(
            -zlib.MAX_WBITS  # see above
    )
    inflated = decompress.decompress(data)
    inflated += decompress.flush()
    return inflated


def partial(res, timestamp):
    data_obj = res['data'][0]
    bids = data_obj['bids']
    asks = data_obj['asks']
    instrument_id = data_obj['instrument_id']
    # print(timestamp + ' aggregated bids: ' + str(bids))
    # print('depth: ' + str(len(bids)))
    # print(timestamp + ' aggregated asks: ' + str(asks))
    # print('depth: ' + str(len(asks)))
    return bids, asks, instrument_id


def update_bids(res, bids_p, timestamp):
    # incremental bids data
    bids_u = res['data'][0]['bids']
    print(timestamp, ' incremental bids: ' + str(bids_u))
    # print('depth: ' + str(len(bids_u)))
    # bids aggregation
    for i in bids_u:
        bid_price = i[0]
        for j in bids_p:
            if bid_price == j[0]:
                if i[1] == '0':
                    bids_p.remove(j)
                    break
                else:
                    del j[1]
                    j.insert(1, i[1])
                    break
        else:
            if i[1] != "0":
                bids_p.append(i)
    else:
        bids_p.sort(key=lambda price: sort_num(price[0]), reverse=True)
        # print(timestamp + ' aggregated bids: ' + str(bids_p) + ', depth: ' + str(len(bids_p)))
    return bids_p


def update_asks(res, asks_p, timestamp):
    # incremental asks data
    asks_u = res['data'][0]['asks']
    print(timestamp, ' incremental asks: ' + str(asks_u))
    # print('depth: ' + str(len(asks_u)))
    # asks aggregation
    for i in asks_u:
        ask_price = i[0]
        for j in asks_p:
            if ask_price == j[0]:
                if i[1] == '0':
                    asks_p.remove(j)
                    break
                else:
                    del j[1]
                    j.insert(1, i[1])
                    break
        else:
            if i[1] != "0":
                asks_p.append(i)
    else:
        asks_p.sort(key=lambda price: sort_num(price[0]))
        # print(timestamp + ' aggregated asks: ' + str(asks_p) + ', depth: ' + str(len(asks_p)))
    return asks_p


def sort_num(n):
    if n.isdigit():
        return int(n)
    else:
        return float(n)


def check(bids, asks):
    # bid str
    bids_l = []
    bid_l = []
    count_bid = 1
    while count_bid <= 25:
        if count_bid > len(bids):
            break
        bids_l.append(bids[count_bid-1])
        count_bid += 1
    for j in bids_l:
        str_bid = ':'.join(j[0 : 2])
        bid_l.append(str_bid)
    # ask str
    asks_l = []
    ask_l = []
    count_ask = 1
    while count_ask <= 25:
        if count_ask > len(asks):
            break
        asks_l.append(asks[count_ask-1])
        count_ask += 1
    for k in asks_l:
        str_ask = ':'.join(k[0 : 2])
        ask_l.append(str_ask)
    # concatenate str
    num = ''
    if len(bid_l) == len(ask_l):
        for m in range(len(bid_l)):
            num += bid_l[m] + ':' + ask_l[m] + ':'
    elif len(bid_l) > len(ask_l):
        # bid > ask
        for n in range(len(ask_l)):
            num += bid_l[n] + ':' + ask_l[n] + ':'
        for l in range(len(ask_l), len(bid_l)):
            num += bid_l[l] + ':'
    elif len(bid_l) < len(ask_l):
        # ask > bid
        for n in range(len(bid_l)):
            num += bid_l[n] + ':' + ask_l[n] + ':'
        for l in range(len(bid_l), len(ask_l)):
            num += ask_l[l] + ':'

    new_num = num[:-1]
    int_checksum = zlib.crc32(new_num.encode())
    fina = change(int_checksum)
    return fina


def change(num_old):
    num = pow(2, 31) - 1
    if num_old > num:
        out = num_old - num * 2 - 2
    else:
        out = num_old
    return out


# subscribe channels un_need login
async def subscribe_without_login(url, channels):
    l = []
    while True:
        try:
            async with websockets.connect(url) as ws:
                sub_param = {"op": "subscribe", "args": channels}
                sub_str = json.dumps(sub_param)
                print(get_timestamp(), 'send:', sub_str)
                await ws.send(sub_str)

                while True:
                    try:
                        res_b = await asyncio.wait_for(ws.recv(), timeout=25)
                    except (asyncio.TimeoutError, websockets.exceptions.ConnectionClosed) as e:
                        try:
                            print(get_timestamp(), 'send: ping')
                            await ws.send('ping')
                            res_b = await ws.recv()
                            timestamp = get_timestamp()
                            res = inflate(res_b).decode('utf-8')
                            print(timestamp, 'receive:', res)
                            continue
                        except Exception as e:
                            timestamp = get_timestamp()
                            print(timestamp, " reconnecting...")
                            print(e)
                            break

                    timestamp = get_timestamp()
                    res = inflate(res_b).decode('utf-8')
                    print(timestamp, 'receive:', res)

                    res = eval(res)
                    if 'event' in res:
                        continue
                    for i in res:
                        if 'depth' in res[i] and 'depth5' not in res[i]:
                            # depth channel
                            if res['action'] == 'partial':
                                for m in l:
                                    if res['data'][0]['instrument_id'] == m['instrument_id']:
                                        l.remove(m)
                                # all depth data
                                bids_p, asks_p, instrument_id = partial(res, timestamp)
                                d = {}
                                d['instrument_id'] = instrument_id
                                d['bids_p'] = bids_p
                                d['asks_p'] = asks_p
                                l.append(d)

                                # validate checksum
                                checksum = res['data'][0]['checksum']
                                # print(timestamp + ' received checksum: ' + str(checksum))
                                check_num = check(bids_p, asks_p)
                                # print(timestamp + ' validated checksum: ' + str(check_num))
                                if check_num == checksum:
                                    print('Checksum result: True')
                                else:
                                    print('Checksum result: False, reconnecting...')

                                    # unsubscribe
                                    await unsubscribe_without_login(url, channels, timestamp)
                                    # re-subscribe
                                    async with websockets.connect(url) as ws:
                                        sub_param = {"op": "subscribe", "args": channels}
                                        sub_str = json.dumps(sub_param)
                                        await ws.send(sub_str)
                                        timestamp = get_timestamp()
                                        print(timestamp, f"send: {sub_str}")

                            elif res['action'] == 'update':
                                for j in l:
                                    if res['data'][0]['instrument_id'] == j['instrument_id']:
                                        # all data
                                        bids_p = j['bids_p']
                                        asks_p = j['asks_p']
                                        # aggregated data
                                        bids_p = update_bids(res, bids_p, timestamp)
                                        asks_p = update_asks(res, asks_p, timestamp)

                                        # validate checksum
                                        checksum = res['data'][0]['checksum']
                                        # print(timestamp + ' received checksum: ' + str(checksum))
                                        check_num = check(bids_p, asks_p)
                                        # print(timestamp + ' validated checksum: ' + str(check_num))
                                        if check_num == checksum:
                                            print('Checksum result: True')
                                        else:
                                            print('Checksum result: False, reconnecting...')

                                            # unsubscribe
                                            await unsubscribe_without_login(url, channels, timestamp)
                                            # re-subscribe
                                            async with websockets.connect(url) as ws:
                                                sub_param = {"op": "subscribe", "args": channels}
                                                sub_str = json.dumps(sub_param)
                                                await ws.send(sub_str)
                                                timestamp = get_timestamp()
                                                print(timestamp, f"send: {sub_str}")
        except Exception as e:
            timestamp = get_timestamp()
            print(timestamp, "Disconnected, reconnecting...")
            print(e)
            continue


# subscribe channels need login
async def subscribe(url, api_key, passphrase, secret_key, channels):
    while True:
        try:
            async with websockets.connect(url) as ws:
                # login
                timestamp = str(server_timestamp())
                login_str = login_params(timestamp, api_key, passphrase, secret_key)
                await ws.send(login_str)
                time = get_timestamp()
                print(time, f"send: {login_str}")
                res_b = await ws.recv()
                res = inflate(res_b).decode('utf-8')
                time = get_timestamp()
                print(time, 'receive:', res)

                # subscribe
                sub_param = {"op": "subscribe", "args": channels}
                sub_str = json.dumps(sub_param)
                await ws.send(sub_str)
                time = get_timestamp()
                print(time, f"send: {sub_str}")

                while True:
                    try:
                        res_b = await asyncio.wait_for(ws.recv(), timeout=25)
                    except (asyncio.TimeoutError, websockets.exceptions.ConnectionClosed) as e:
                        try:
                            print(get_timestamp(), 'send: ping')
                            await ws.send('ping')
                            res_b = await ws.recv()
                            time = get_timestamp()
                            res = inflate(res_b).decode('utf-8')
                            print(time, 'receive:', res)
                            continue
                        except Exception as e:
                            time = get_timestamp()
                            print(time, ' reconnecting...')
                            print(e)
                            break

                    time = get_timestamp()
                    res = inflate(res_b).decode('utf-8')
                    print(time, 'receive:', res)

        except Exception as e:
            time = get_timestamp()
            print(time, "Disconnected, reconnecting...")
            print(e)
            continue


# unsubscribe channels
async def unsubscribe(url, api_key, passphrase, secret_key, channels):
    async with websockets.connect(url) as ws:
        # login
        timestamp = str(server_timestamp())
        login_str = login_params(str(timestamp), api_key, passphrase, secret_key)
        await ws.send(login_str)
        time = get_timestamp()
        print(time, f"send: {login_str}")

        res_1 = await ws.recv()
        res = inflate(res_1).decode('utf-8')
        time = get_timestamp()
        print(time, 'receive:', res)

        # unsubscribe
        sub_param = {"op": "unsubscribe", "args": channels}
        sub_str = json.dumps(sub_param)
        await ws.send(sub_str)
        time = get_timestamp()
        print(time, f"send: {sub_str}")

        res_1 = await ws.recv()
        res = inflate(res_1).decode('utf-8')
        time = get_timestamp()
        print(time, 'receive:', res)


# unsubscribe channels
async def unsubscribe_without_login(url, channels, timestamp):
    async with websockets.connect(url) as ws:
        # unsubscribe
        sub_param = {"op": "unsubscribe", "args": channels}
        sub_str = json.dumps(sub_param)
        await ws.send(sub_str)
        print(timestamp, f"send: {sub_str}")

        res_1 = await ws.recv()
        res = inflate(res_1).decode('utf-8')
        print(timestamp, f"receive: {res}")
