OKCoin Japan open api v3 go sdk usage:
-----

### 1.Downloads or updates OKCoin code's dependencies, in your command line:

```
go get -u github.com/okcoinjapan/V3-Open-API-SDK/okcoin-go-sdk-api
```
### 2.Write the go file. warm tips: test go file, must suffix *_test.go, eg: okcoin_open_api_v3_test.go
```
package gotest

import (
	"fmt"
	"github.com/okcoinjapan/V3-Open-API-SDK/okcoin-go-sdk-api"
	"testing"
)

func TestOKCoinServerTime(t *testing.T) {
	serverTime, err := NewOKCoinClient().GetServerTime()
	if err != nil {
		t.Error(err)
	}
	fmt.Println("OKCoin's server time: ", serverTime)
}

func NewOKCoinClient() *okcoin.Client {
	var config okcoin.Config
	config.Endpoint = "https://www.okcoin.jp/"
	config.ApiKey = ""
	config.SecretKey = ""
	config.Passphrase = ""
	config.TimeoutSecond = 45
	config.IsPrint = true
	config.I18n = okcoin.ENGLISH

	client := okcoin.NewClient(config)
	return client
}
```
### 3. run test go:
```
go test -v -run TestOKCoinServerTime okcoin_open_api_v3_test.go
```
