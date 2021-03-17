package okcoin

import (
	"fmt"
	"hash/crc32"
	"testing"
	"time"

	"github.com/stretchr/testify/assert"
)

func TestPublicChannels(t *testing.T) {
	agent := OKWSAgent{}
	config := GetDefaultConfig()

	agent.Start(config)

	// Public-Ticker
	agent.Subscribe(CHNL_SPOT_TICKER, "BTC-JPY", DefaultDataCallBack)
	time.Sleep(1 * time.Minute)
	agent.UnSubscribe(CHNL_SPOT_TICKER, "BTC-JPY")
	time.Sleep(1 * time.Second)

	// Public-Trade
	// agent.Subscribe(CHNL_SPOT_TRADE, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_TRADE, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	// Public-Candlesticks
	// agent.Subscribe(CHNL_SPOT_CANDLE180S, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_CANDLE180S, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	// Public-Depth5
	// agent.Subscribe(CHNL_SPOT_DEPTH5, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_DEPTH5, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	// Public-Depth400
	// agent.Subscribe(CHNL_SPOT_DEPTH, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_DEPTH, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	// Public-400Depth Incremental Data
	// agent.Subscribe(CHNL_SPOT_DEPTH_L2_TBT, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_DEPTH_L2_TBT, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	agent.Stop()
}

func TestPrivateChannels(t *testing.T) {
	agent := OKWSAgent{}
	config := GetDefaultConfig()

	agent.Start(config)

	agent.Login(config.ApiKey, config.Passphrase)
	time.Sleep(1 * time.Second)

	// User Spot Account
	agent.Subscribe(CHNL_SPOT_ACCOUNT, "BTC", DefaultDataCallBack)
	time.Sleep(1 * time.Minute)
	agent.UnSubscribe(CHNL_SPOT_ACCOUNT, "BTC")
	time.Sleep(1 * time.Second)

	// User Orders
	// agent.Subscribe(CHNL_SPOT_ORDER, "BTC-JPY", DefaultDataCallBack)
	// time.Sleep(1 * time.Minute)
	// agent.UnSubscribe(CHNL_SPOT_ORDER, "BTC-JPY")
	// time.Sleep(1 * time.Second)

	agent.Stop()
}

func TestOKWSAgent_mergeDepths(t *testing.T) {
	oldDepths := [][4]interface{}{
		{"5088.59", "34000", 0, 1},
		{"7200", "1", 0, 1},
		{"7300", "1", 0, 1},
	}

	// Case1.
	newDepths1 := [][4]interface{}{
		{"5088.59", "32000", 0, 1},
	}
	expectedMerged1 := [][4]interface{}{
		{"5088.59", "32000", 0, 1},
		{"7200", "1", 0, 1},
		{"7300", "1", 0, 1},
	}

	m1, e1 := mergeDepths(oldDepths, newDepths1)
	assert.True(t, e1 == nil)
	assert.True(t, len(*m1) == len(expectedMerged1) && (*m1)[0][1] == expectedMerged1[0][1] && (*m1)[0][1] == "32000")

	// Case2.
	newDepths2 := [][4]interface{}{
		{"7200", "0", 0, 1},
	}
	expectedMerged2 := [][4]interface{}{
		{"5088.59", "34000", 0, 1},
		{"7300", "1", 0, 1},
	}
	m2, e2 := mergeDepths(oldDepths, newDepths2)
	assert.True(t, e2 == nil)
	assert.True(t, len(*m2) == len(expectedMerged2) && (*m2)[0][1] == expectedMerged2[0][1] && (*m2)[0][1] == "34000")

	// Case3.
	newDepths3 := [][4]interface{}{
		{"5000", "1", 0, 1},
		{"7400", "1", 0, 1},
	}
	expectedMerged3 := [][4]interface{}{
		{"5000", "1", 0, 1},
		{"5088.59", "34000", 0, 1},
		{"7200", "1", 0, 1},
		{"7300", "1", 0, 1},
		{"7400", "1", 0, 1},
	}
	m3, e3 := mergeDepths(oldDepths, newDepths3)
	assert.True(t, e3 == nil)
	assert.True(t, len(*m3) == len(expectedMerged3) && (*m3)[0][1] == expectedMerged3[0][1] && (*m3)[0][1] == "1")

}

func TestOKWSAgent_calCrc32(t *testing.T) {

	askDepths := [][4]interface{}{
		{"5088.59", "34000", 0, 1},
		{"7200", "1", 0, 1},
		{"7300", "1", 0, 1},
	}

	bidDepths1 := [][4]interface{}{
		{"3850", "1", 0, 1},
		{"3800", "1", 0, 1},
		{"3500", "1", 0, 1},
		{"3000", "1", 0, 1},
	}

	crcBuf1, caled1 := calCrc32(&askDepths, &bidDepths1)
	assert.True(t, caled1 != 0 && crcBuf1.String() == "3850:1:3800:1:3500:1:3000:1:5088.59:34000:7200:1:7300:1")

	bidDepths2 := [][4]interface{}{
		{"3850", "1", 0, 1},
		{"3800", "1", 0, 1},
		{"3500", "1", 0, 1},
	}

	crcBuf2, caled2 := calCrc32(&askDepths, &bidDepths2)
	assert.True(t, caled2 != 0 && crcBuf2.String() == "3850:1:5088.59:34000:3800:1:7200:1:3500:1:7300:1")
}

func TestArray(t *testing.T) {

	t1 := [4]int{1, 2, 3, 4}
	t2 := [][4]int{
		{1, 2, 3, 4},
	}
	t3 := [4][]int{
		{1, 2, 3, 4},
	}

	r1, _ := Struct2JsonString(t1)
	r2, _ := Struct2JsonString(t2)
	r3, _ := Struct2JsonString(t3)

	println(len(t1), r1)
	println(len(t2), r2)
	println(len(t3), r3)

	fmt.Printf("%+v\n", t1[0:len(t1)-1])
}

func TestCrc32(t *testing.T) {
	str1 := "3366.1:7:3366.8:9:3366:6:3368:8"
	r := crc32.ChecksumIEEE([]byte(str1))
	println(r)
	assert.True(t, int32(r) == -1881014294)

	str2 := "3366.1:7:3366.8:9:3368:8:3372:8"
	r = crc32.ChecksumIEEE([]byte(str2))
	println(r)
	assert.True(t, int32(r) == 831078360)
}

func TestFmtSprintf(t *testing.T) {
	a := [][]interface{}{
		{"199", "10"},
		{199.0, 10.0},
	}

	for _, v := range a {
		s1 := fmt.Sprintf("%v:%v", v[0], v[1])
		s2 := fmt.Sprintf("%s:%s", v[0], v[1])
		println(s1)
		println(s2)
		assert.True(t, s1 != "" && s2 != "")
	}

}
