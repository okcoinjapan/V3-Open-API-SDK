OKCoin Japan V3 Open Api 利用方法
--------------
### サポートしているpythonバージョン

`python：3.6+`

#### 1.SDKのダウンロードと依存するライブラリーのインストール

1.1 `python SDK`をダウンロードする
* SDKをローカルPCに`Clone`もしくは`Download`して、`okcoin-python-sdk-api`をご使用ください。

1.2 依存するライブラリーのインストール
```python
pip install requests
pip install websockets==6.0
```

#### 2.API情報を設定する

2.1 APIはお持ちでない場合、[こちら](https://www.okcoin.jp/account/users/myApi)からご申請ください。

2.2 必要な情報を`rest_example.py（RestAPI）`と`websocket_example.py（WebSocketAPI）`にご記載ください。
```python
api_key = ""
secret_key = ""
passphrase = ""
```
#### 3.APIを呼び出す。

* RestAPI
    
    * 呼び出す関数のコメントを外した上で、引数を設定する

    * `rest_example.py`を実行
    
* WebSocketAPI
    
    * Public/Privateによって、関数のコメントを外した上で、引数を設定する

    * `websocket_example.py`を実行
    
    ```python
    # Public channel, no need to login
    loop.run_until_complete(ws.subscribe_without_login(url, channels))
    
    # Private channel, must login
    loop.run_until_complete(ws.subscribe(url, api_key, passphrase, secret_key, channels))
    ```

補足事項：

* `WebSocketAPI`をご利用の際に、以下もご参考ください。

    * `asyncio`、`websockets`ドキュメント/`github`：
    
            https://docs.python.org/3/library/asyncio-dev.html
            https://websockets.readthedocs.io/en/stable/intro.html
            https://github.com/aaugustin/websockets
    
    * `code=1006`について：
    
            https://github.com/Rapptz/discord.py/issues/1996
            https://github.com/aaugustin/websockets/issues/587